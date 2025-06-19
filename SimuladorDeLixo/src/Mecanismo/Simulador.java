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
  private Lista<Zona> listaZonas = new Lista<>();
  private Fila<CaminhaoGrande> filaGrandes = new Fila<>();
  private EstacaoPadrao estacao1;
  private EstacaoPadrao estacao2;

  // Inicia a simulação
  public void iniciar(int tempoLimiteMinutos) {
    System.out.println("\n================ INÍCIO DA SIMULAÇÃO ================");
    estacao1 = new EstacaoPadrao("Estação Norte", 0);
    estacao2 = new EstacaoPadrao("Estação Sul", 0);
    // Gera 4 caminhões pequenos 8 toneladas
    this.geraCaminhoesPequenos(15, 8, 4);
    // Gera 4 caminhões pequenos 4 toneladas
    this.geraCaminhoesPequenos(15, 4, 4);
    // Gera 4 caminhões pequenos 10 toneladas
    this.geraCaminhoesPequenos(15, 10, 4);
    // Gera 4 caminhões pequenos 2 toneladas
    this.geraCaminhoesPequenos(15, 2, 4);
    // Gera 2 caminhões pequenos 2 toneladas
    this.geraCaminhoesPequenos(15, 2, 4);
    // Gera 4 caminhões grandes 20 toneladas
    this.geraCaminhoesGrandes(105, 20, 5);
    for (int i = 0; i < lista_caminhoes_grandes.getTamanho(); i++) {
      filaGrandes.add(lista_caminhoes_grandes.getValor(i));
    }
    // gera as zona sul
    Zona zonaSul = new Zona("Sul", 20, 40);
    // gera as zonas norte
    Zona zonaNorte = new Zona("norte", 20, 40);
    // gera as zonas leste
    Zona zonaLeste = new Zona("leste", 20, 40);
    // gera as zonas oeste
    Zona zonaSudeste = new Zona("sudeste", 20, 40);
    // gera as zonas do dirceu
    Zona zonaCentro = new Zona("centro", 30, 60);
    listaZonas.add(zonaSul);
    listaZonas.add(zonaNorte);
    listaZonas.add(zonaLeste);
    listaZonas.add(zonaSudeste);
    listaZonas.add(zonaCentro);
    long tempoLimiteMilissegundos = tempoLimiteMinutos * 60L * 1000L;
    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        if (!pausado) {
          tempoSimulado++;
          atualizarSimulacao();

          // Verifica se o tempo simulado atingiu o limite
          if (tempoSimulado >= tempoLimiteMinutos) {
            encerrar(); // encerra a simulação
          }
        }
      }
    }, 0, 1000); // executa a cada 1 segundo (1000 ms)

  }

  // Gera a quantidade especificada de caminhões pequenos e adiciona à lista
  private void geraCaminhoesPequenos(int qtd, int capacidade, int maxViagensPorDia) {
    for (int i = 0; i < qtd; i++) {
      CaminhaoPequenoPadrao novo_caminhao = new CaminhaoPequenoPadrao(capacidade, maxViagensPorDia);
      this.lista_caminhoes.add(novo_caminhao);
    }
  }

  // Gera a quantidade especificada de caminhões grandes e adiciona à lista
  private void geraCaminhoesGrandes(int qtd, int capacidade, int toleranciaEspera) {
    for (int i = 0; i < qtd; i++) {
      CaminhaoGrandePadrao novo_caminhao_grande = new CaminhaoGrandePadrao(capacidade, toleranciaEspera);
      this.lista_caminhoes_grandes.add(novo_caminhao_grande);
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

  private void gerarRelatorioFinal() {
    System.out.println("\n======= RELATÓRIO FINAL =======");

    // Agrega por zona
    double totalGeradoZonas = 0, totalColetadoZonas = 0;
    for (int i = 0; i < listaZonas.getTamanho(); i++) {
      Zona z = listaZonas.getValor(i);
      totalGeradoZonas += z.getTotalGerado();
      totalColetadoZonas += z.getTotalColetado();
    }
    System.out.printf(" Zonas: geração total de %.0f KG | coletados %.0f KG%n",
        totalGeradoZonas * 1000, totalColetadoZonas * 1000);

    // Agrega por caminhões
    int caminhaoCount = lista_caminhoes.getTamanho();
    double totalColetadoCP = 0;
    int totalViagensCP = 0;
    for (int i = 0; i < caminhaoCount; i++) {
      CaminhaoPequenoPadrao c = lista_caminhoes.getValor(i);
      totalColetadoCP += c.getTotalColetado();
      totalViagensCP += c.getViagensRealizadas();
    }
    System.out.printf(" %d caminhões coletaram um total de %.0f KG e fizeram %d viagens%n",
        caminhaoCount, totalColetadoCP * 1000, totalViagensCP);

    System.out.println("================================\n");
  }

  // Encerra a simulação e cancela o timer
  public void encerrar() {
    System.out.println("Simulação encerrada.");
    if (timer != null)
      timer.cancel();
    gerarRelatorioFinal();
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

  // 4. Adicionar estes novos métodos auxiliares
  private void enviarParaEstacao(CaminhaoPequenoPadrao caminhao) {
    // Lógica simplificada para escolher estação
    EstacaoPadrao estacao = (Math.random() < 0.5) ? estacao1 : estacao2;
    estacao.receberCaminhaoPequeno(caminhao);
    System.out.println("Caminhão pequeno enviado para " + estacao.getNome());
    System.out.println("______________________________________________________");
  }

  private void processarEstacoes() {
    processarEstacao(estacao1);
    processarEstacao(estacao2);
  }

  private void processarEstacao(EstacaoPadrao estacao) {
    // Converter caminhões pequenos para lista genérica
    Lista<CaminhaoPequeno> caminhoesParaTransferir = new Lista<>();
    Fila<CaminhaoPequenoPadrao> fila = estacao.getFilaCaminhoes();

    while (!fila.estaVazia()) {
      caminhoesParaTransferir.add(fila.remove());
    }

    // Processar transferência
    estacao.transferirLixoParaCaminhoesGrandes(caminhoesParaTransferir, filaGrandes);
  }

  private void atualizarCaminhoesGrandes() {

    for (int i = 0; i < lista_caminhoes_grandes.getTamanho(); i++) {
      CaminhaoGrandePadrao caminhao = lista_caminhoes_grandes.getValor(i);
      caminhao.incrementarEspera();

      if (caminhao.passouTolerancia() && caminhao.getCargaAtual() > 0) {

        System.out.printf("⚠ Caminhão grande [%d] partiu por excesso de espera com %d T\n",
            caminhao.hashCode(), caminhao.getCargaAtual());
        caminhao.descarregar(0);
      }
    }

  }

  private void atualizarSimulacao() {
    System.out.println("\n---------------- simulação em andamento----------------");

    // 1. GERAÇÃO E COLETA DE LIXO NAS ZONAS URBANAS
    for (int i = 0; i < listaZonas.getTamanho(); i++) {
      Zona zona = listaZonas.getValor(i);
      double lixoGerado = zona.gerarLixo();
      System.out.printf(" > Zona %s: lixo gerado = %d T\n", zona.getNome(), (int) lixoGerado);

      // Tenta coletar o lixo com os caminhões disponíveis
      for (int j = 0; j < lista_caminhoes.getTamanho(); j++) {
        CaminhaoPequenoPadrao caminhao = lista_caminhoes.getValor(j);

        if (caminhao.estaDisponivel() && lixoGerado > 0) {
          int capacidadeDisponivel = caminhao.getCapacidade() - caminhao.getCargaAtual();
          int quantidadeColetar = (int) Math.min(lixoGerado, capacidadeDisponivel);

          if (quantidadeColetar > 0 && caminhao.coletar(quantidadeColetar)) {
            lixoGerado -= quantidadeColetar;
            zona.registrarColeta(quantidadeColetar);
            caminhao.registrarViagem();
            System.out.printf("   - Caminhão %d coletou %d T (Carga atual: %d/%d T)\n",
                j, quantidadeColetar, caminhao.getCargaAtual(), caminhao.getCapacidade());
            System.out.println("______________________________________________________");

            // Se caminhão está cheio, envia para estação de transferência
            if (caminhao.estaCheio()) {
              enviarParaEstacao(caminhao);
            }
          }
        }
      }
    }

    processarEstacoes();

    atualizarCaminhoesGrandes();

    System.out.println("-----------------------------------------");

  }

}
