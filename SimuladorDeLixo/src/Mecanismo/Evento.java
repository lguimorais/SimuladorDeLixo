package Mecanismo;

public class Evento {
  private double instante; // o momento exato em que o evento deve ocorrer.
  private TipoEvento tipo; // o que vai acontecer (ex: GERAR_LIXO, CAMINHAO_ENCHEU...).
  private Object dados; // objeto genérico com dados que o evento precisa (ex: uma Zona, um CaminhaoPequeno, etc.).pode ser qualquer entidade envolvida (caminhão, zona, carga...)

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