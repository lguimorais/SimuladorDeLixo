package Mecanismo;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import Modelo.*;
import EstruturasDeDados.*;

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

  // Inicia a simulação
  public void iniciar() {
    System.out.println("Simulação iniciada...");

    // Gera 4 caminhões pequenos padrão
    this.geraCaminhoesPequenos(4);

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
  }

  // Gera a quantidade especificada de caminhões pequenos e adiciona à lista
  private void geraCaminhoesPequenos(int qtd) {
    for (int i = 0; i < qtd; i++) {
      CaminhaoPequenoPadrao novo_caminhao = new CaminhaoPequenoPadrao();
      this.lista_caminhoes.add(novo_caminhao);
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
    this.lista_caminhoes.imprimir(); // Exibe os caminhões cadastrados
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
