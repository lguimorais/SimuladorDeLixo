package Modelo;
public abstract  class CaminhaoPequeno {
  protected int capacidade;
  protected int cargaAtual;
  protected int maxViagensPorDia;
  protected int viagensRealizadas;

  public abstract boolean coletar(int quantidade);
  public int  getCapacidade(){
  return capacidade;
  }

  public boolean estaCheio() {
    return cargaAtual >= capacidade;
  }

  public boolean podeViajar() {
    return viagensRealizadas < maxViagensPorDia;
  }

  public void registrarViagem() {
    viagensRealizadas++;
  }

  public int descarregar() {
    int carga = cargaAtual;
    cargaAtual = 0;
    return carga;
  }

  public int getCargaAtual() {
    return cargaAtual;
  }
}
