package Mecanismo;

public class Evento {
  private double instante;
  private TipoEvento tipo;
  private Object dados;

  public Evento(double instante, TipoEvento tipo, Object dados) {
    this.instante = instante;
    this.tipo = tipo;
    this.dados = dados;
  }

  public double getInstante() {
    return instante;
  }

  public TipoEvento getTipo() {
    return tipo;
  }

  public Object getDados() {
    return dados;
  }
}