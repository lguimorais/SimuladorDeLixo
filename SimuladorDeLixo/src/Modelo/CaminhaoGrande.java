package Modelo;

public abstract class CaminhaoGrande {
  protected int capacidadeMaxima = 20000;
  protected int cargaAtual;
  protected int tempoEspera;
  protected int toleranciaEspera;
  protected boolean emUso;

  public abstract void carregar(int quantidade);


  public void incrementarEspera() {
    tempoEspera++;
  }

  public boolean passouTolerancia() {
    return tempoEspera >= toleranciaEspera;
  }

  public void resetarEspera() {
    tempoEspera = 0;
  }

  public void ocupar() {
    emUso = true;
  }

  public void liberar() {
    emUso = false;
  }

  public boolean estaEmUso() {
    return emUso;
  }

  public boolean prontoParaPartir() {
    return cargaAtual >= capacidadeMaxima;
  }

  public void descarregar(int tempoEspera) {
    if (cargaAtual == 0) {

    }

    System.out.println("Caminhão grande partiu para o aterro com " + cargaAtual + "kg.");
    cargaAtual = 0;
    resetarEspera();
    liberar();
  }
  
  public int getCapacidadeMaxima() {
    return capacidadeMaxima;
  }

  public int getCargaAtual() {
    return cargaAtual;
  }


}