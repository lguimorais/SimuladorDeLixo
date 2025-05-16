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
  
  public boolean devePartir() {
    // se está vazio, não parte
    if (cargaAtual == 0)
      return false;
    // parte se cheio ou se tempo de espera passou da tolerância
    return prontoParaPartir() || passouTolerancia();
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


}