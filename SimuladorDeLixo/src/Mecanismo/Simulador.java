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
  private EstacaoPadrao estacao1;
  private EstacaoPadrao estacao2;
  private Fila<CaminhaoGrande> filaGrandes = new Fila<>();

  // Inicia a simulação
  public void iniciar() {
    System.out.println("\n================ INÍCIO DA SIMULAÇÃO ================");
    estacao1 = new EstacaoPadrao("Estação Norte", 0);
    estacao2 = new EstacaoPadrao("Estação Sul", 0);
    // Gera 4 caminhões pequenos 8 toneladas
    this.geraCaminhoesPequenos(10, 8, 4);
    // Gera 4 caminhões pequenos 4 toneladas
    this.geraCaminhoesPequenos(10, 4, 4);
    // Gera 4 caminhões pequenos 10 toneladas
    this.geraCaminhoesPequenos(10, 10, 4);
    // Gera 4 caminhões pequenos 2 toneladas
    this.geraCaminhoesPequenos(10, 2, 4);
    // Gera 2 caminhões pequenos 2 toneladas
    this.geraCaminhoesPequenos(10, 2, 4);
    // Gera 4 caminhões grandes 20 toneladas
    this.geraCaminhoesGrandes(10, 20, 5);
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
    EstacaoPadrao estacao1 = new EstacaoPadrao("dirceu", 0);
    EstacaoPadrao estacao2 = new EstacaoPadrao("dirceu", 0);
    // Instancia e configura o timer para avançar o tempo a cada segundo (1000 ms)

    // {
    // public void run() {
    // // Só avança o tempo se a simulação não estiver pausada

    // }
    // }
    long tempoLimite = 100 * 1000;
    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        if (!pausado) {
          tempoSimulado++;
          atualizarSimulacao();

        }
        encerrar();
        System.out.println("=============== FIM DA SIMULAÇÃO AUTOMÁTICO ===============");

      }
    }, 0, 1000);

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

  // Atualiza o estado da simulação a cada minuto simulado
  private void atualizarSimulacao() {
    System.out.println("\n---------------- TEMPO SIMULADO: " + tempoSimulado + " min ----------------");

    // 1. Geração e coleta de lixo
    for (int i = 0; i < listaZonas.getTamanho(); i++) {
      Zona zona = listaZonas.getValor(i);
      double lixoGerado = zona.gerarLixo();
      System.out.printf(" > Zona %s: lixo gerado = %d T\n", zona.getNome(), (int) lixoGerado);

      for (int j = 0; j < lista_caminhoes.getTamanho(); j++) {
        CaminhaoPequenoPadrao caminhao = lista_caminhoes.getValor(j);

        if (caminhao.estaDisponivel() && lixoGerado > 0) {
          int capacidadeDisponivel = caminhao.getCapacidade() - caminhao.getCargaAtual();
          int quantidadeColetar = (int) Math.min(lixoGerado, capacidadeDisponivel);

          if (quantidadeColetar > 0 && caminhao.coletar(quantidadeColetar)) {
            lixoGerado -= quantidadeColetar;
            caminhao.registrarViagem();
            System.out.printf("   - Caminhão %d coletou %d T (Carga atual: %d/%d T)\n",
                j, quantidadeColetar, caminhao.getCargaAtual(), caminhao.getCapacidade());
            System.out.println("______________________________________________________");
            if (caminhao.estaCheio()) {
              enviarParaEstacao(caminhao);
            }
          }
        }
      }
    }

    // 2. Processar estações de transferência
    processarEstacoes();

    // 3. Atualizar caminhões grandes
    atualizarCaminhoesGrandes();

    System.out.println("-----------------------------------------");

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
