package Mecanismo;

import EstruturasDeDados.*;
import Modelo.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
//nao utilizei a classe simulador disponibilizada pelo prefoessor mas esta aqui caso ele queira usa-la

// Classe principal da simulação, que gerencia tempo, caminhões e persistência
public class Simulador implements Serializable {
  private static final long serialVersionUID = 1L;

  // Timer para controlar a simulação em tempo real
  private transient Timer timer;

  // Representa o tempo decorrido na simulação, em minutos
  private int tempoSimulado = 0;

  // Indica se a simulação está pausada
  private boolean pausado = false;

  // Lista de caminhões pequenos padrão utilizados na simulação
  public Lista<CaminhaoPequenoPadrao> lista_caminhoes = new Lista<CaminhaoPequenoPadrao>();

  // Lista de caminhões pequenos padrão utilizados na simulação
  public Lista<CaminhaoGrandePadrao> lista_caminhoes_grandes = new Lista<CaminhaoGrandePadrao>();
  public Lista<Zona> listaZonas = new Lista<>();
  private EstacaoPadrao estacao1;
  private EstacaoPadrao estacao2;

  // Inicia a simulação
  public void iniciar() {
    System.out.println("Simulação iniciada...");

    // Gera 4 caminhões pequenos 2 toneladas
    this.geraCaminhoesPequenos(4, 2, 4);
    // Gera 4 caminhões pequenos 2 toneladas
    this.geraCaminhoesPequenos(4, 4, 4);
    // Gera 4 caminhões pequenos 2 toneladas
    this.geraCaminhoesPequenos(4, 8, 4);
    // Gera 4 caminhões pequenos 2 toneladas
    this.geraCaminhoesPequenos(4, 10, 4);
    // Gera 2 caminhões pequenos 2 toneladas
    this.geraCaminhoesPequenos(2, 2, 4);
    // Gera 4 caminhões grandes 20 toneladas
    this.geraCaminhoesGrandes(10, 20, 5);

    // gera as zona sul
    Zona zonaSul = new Zona("Sul", 20, 40);
    zonaSul.setCaminhoneirosPorPeriodo(PeriodoDia.MANHA, 5);
    zonaSul.setCaminhoneirosPorPeriodo(PeriodoDia.PICO, 10);
    zonaSul.setCaminhoneirosPorPeriodo(PeriodoDia.TARDE, 5);
    // gera as zonas norte
    Zona zonaNorte = new Zona("norte", 20, 40);
    zonaNorte.setCaminhoneirosPorPeriodo(PeriodoDia.MANHA, 5);
    zonaNorte.setCaminhoneirosPorPeriodo(PeriodoDia.PICO, 10);
    zonaNorte.setCaminhoneirosPorPeriodo(PeriodoDia.TARDE, 5);
    // gera as zonas leste
    Zona zonaLeste = new Zona("leste", 20, 40);
    zonaLeste.setCaminhoneirosPorPeriodo(PeriodoDia.MANHA, 5);
    zonaLeste.setCaminhoneirosPorPeriodo(PeriodoDia.PICO, 10);
    zonaLeste.setCaminhoneirosPorPeriodo(PeriodoDia.TARDE, 5);
    // gera as zonas oeste
    Zona zonaOeste = new Zona("sudeste", 20, 40);
    zonaOeste.setCaminhoneirosPorPeriodo(PeriodoDia.MANHA, 5);
    zonaOeste.setCaminhoneirosPorPeriodo(PeriodoDia.PICO, 10);
    zonaOeste.setCaminhoneirosPorPeriodo(PeriodoDia.TARDE, 5);
    // gera as zonas do dirceu
    Zona zonaCentral = new Zona("centro", 30, 60);
    zonaCentral.setCaminhoneirosPorPeriodo(PeriodoDia.MANHA, 5);
    zonaCentral.setCaminhoneirosPorPeriodo(PeriodoDia.PICO, 10);
    zonaCentral.setCaminhoneirosPorPeriodo(PeriodoDia.TARDE, 5);
    this.estacao1 = new EstacaoPadrao("dirceu", 0);
    this.estacao2 = new EstacaoPadrao("socopo", 0);
    zonaSul.setEstacaoDestino(estacao1);
    zonaNorte.setEstacaoDestino(estacao1);
    zonaLeste.setEstacaoDestino(estacao2);
    zonaOeste.setEstacaoDestino(estacao2);
    zonaCentral.setEstacaoDestino(estacao1);

    listaZonas.add(zonaSul);
    listaZonas.add(zonaNorte);
    listaZonas.add(zonaLeste);
    listaZonas.add(zonaOeste);
    listaZonas.add(zonaCentral);
    // Instancia e configura o timer para avançar o tempo a cada segundo (1000 ms)
    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        // Só avança o tempo se a simulação não estiver pausada
        if (!pausado) {
          tempoSimulado++;
          atualizarSimulacao();
        }
      }
    }, 0, 1000);
    distribuirCaminhoesPorZona();
  }

  private void distribuirCaminhoesPorZona() {
    int numZonas = listaZonas.getTamanho();

    for (int i = 0; i < lista_caminhoes.getTamanho(); i++) {
      CaminhaoPequenoPadrao cam = lista_caminhoes.get(i).getValor();
      Zona zona = listaZonas.get(i % numZonas).getValor();
      zona.adicionarCaminhaoPequeno(cam);
    }
  }

  // Gera a quantidade especificada de caminhões pequenos e adiciona à lista
  private void geraCaminhoesPequenos(int qtd, int capacidade, int maxViagensPorDia) {
    for (int i = 0; i < qtd; i++) {
      CaminhaoPequenoPadrao novo_caminhao = new CaminhaoPequenoPadrao(capacidade, maxViagensPorDia);
      this.lista_caminhoes.add(novo_caminhao);
    }
  }

  // Gera a quantidade especificada de caminhões pequenos e adiciona à lista
  private void geraCaminhoesGrandes(int qtd, int capacidade, int toleranciaEspera) {
    for (int i = 0; i < qtd; i++) {
      CaminhaoGrandePadrao novo_caminhao = new CaminhaoGrandePadrao(capacidade, toleranciaEspera);
      this.lista_caminhoes_grandes.add(novo_caminhao);
    }
  }

  // Pausa a simulação
  public void pausar() {
    System.out.println("Simulação pausada.");
    pausado = true;
  }

  // Retoma a simulação após uma pausa
  public void continuarSimulacao() {
    System.out.println("Simulação retomada.");
    pausado = false;
  }

  // Encerra a simulação e cancela o timer
  public void encerrar() {
    System.out.println("Simulação encerrada.");
    if (timer != null)
      timer.cancel();
  }

  // Salva o estado atual da simulação em um arquivo
  public void gravar(String caminho) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
      oos.writeObject(this);
      System.out.println("Simulação salva.");
    }
  }

  // Carrega uma simulação salva anteriormente a partir de um arquivo
  public static Simulador carregar(String caminho) throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
      Simulador sim = (Simulador) ois.readObject();
      // Recria o timer após desserialização (por ser transient)
      sim.timer = new Timer();
      return sim;
    }
  }

  // Atualiza o estado da simulação a cada minuto simulado
  private void atualizarSimulacao() {
    System.out.println("Tempo simulado: " + tempoSimulado + " minutos");

    int horaAtual = tempoSimulado % 24;

    for (int i = 0; i < listaZonas.getTamanho(); i++) {
      Zona zona = listaZonas.get(i).getValor();

      int qtdEsperada = zona.caminhoneirosParaHora(horaAtual);
      Lista<CaminhaoPequenoPadrao> caminhoes = zona.getCaminhoesPequenos();
      int enviados = 0;

      for (int j = 0; j < caminhoes.getTamanho(); j++) {
        CaminhaoPequenoPadrao cam = caminhoes.get(j).getValor();
      

        if (!cam.estaCheio() && cam.podeViajar()) {
          double lixo = zona.gerarLixo();
          cam.coletar((int) lixo);
          cam.registrarViagem();
          enviados++;

          System.out.println("Zona " + zona.getNome() + " → Caminhão coletou " + (int) lixo + "kg");

          if (cam.estaCheio() || !cam.podeViajar()) {
            zona.getEstacaoDestino().receberCaminhaoPequeno(cam);
            System.out.println("Caminhão enviado para estação " + zona.getEstacaoDestino().getNome());
          }

          if (enviados >= qtdEsperada)
            break;
        }
      }
    }

    estacao1.processar(tempoSimulado);
    estacao2.processar(tempoSimulado);
  }
}

// Comentários adicionais deixados no código original:
// - criar uma lista de zonas (Lista Dinamica)
// - colocar os caminhoes
// - timer
// - lista de aterros
// - aqui tera q assim q for criado os caminhoes tera q ser decidido a
// quantidade
// de viagens maxima pra nao precisar repetir isso varias vezes
