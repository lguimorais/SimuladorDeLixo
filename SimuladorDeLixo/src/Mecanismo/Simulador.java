package Mecanismo;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import Modelo.*;
import EstruturasDeDados.*;

public class Simulador implements Serializable {
  private static final long serialVersionUID = 1L;

  private transient Timer timer;
  private int tempoSimulado = 0;
  private boolean pausado = false;

  public Lista<CaminhaoPequenoPadrao> lista_caminhoes = new Lista<CaminhaoPequenoPadrao>();

  public void iniciar() {
    System.out.println("Simulação iniciada...");
    this.geraCaminhoesPequenos(4);
    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        if (!pausado) {
          tempoSimulado++;
          atualizarSimulacao();
        }
      }
    }, 0, 1000);
  }

  private void geraCaminhoesPequenos(int qtd) {
    for (int i = 0; i < qtd; i++) {
      CaminhaoPequenoPadrao novo_caminhao = new CaminhaoPequenoPadrao();
      this.lista_caminhoes.add(novo_caminhao);
    }

  }

  public void pausar() {
    System.out.println("Simulação pausada.");
    pausado = true;
  }

  public void continuarSimulacao() {
    System.out.println("Simulação retomada.");
    pausado = false;
  }

  public void encerrar() {
    System.out.println("Simulação encerrada.");
    if (timer != null)
      timer.cancel();
  }

  public void gravar(String caminho) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
      oos.writeObject(this);
      System.out.println("Simulação salva.");
    }
  }

  public static Simulador carregar(String caminho) throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
      Simulador sim = (Simulador) ois.readObject();
      sim.timer = new Timer();
      return sim;
    }
  }

  private void atualizarSimulacao() {
    System.out.println("Tempo simulado: " + tempoSimulado + " minutos");
    this.lista_caminhoes.imprimir();

  }
}
// criar uma lista de zonas (Lista DInamica)
// colocar os caminhoes
// timer
// lista de aterros
// aqui tera q assim q for criado os caminhoes tera q ser decidido a quantidade
// de viagens maxima pra nao precisar repetir isso varias vezes
