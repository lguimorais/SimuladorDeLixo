package Mecanismo;

// Classe que representa um evento na simulação
public class Evento {
  // Momento exato em que o evento deve ocorrer
  private double instante;

  // Tipo do evento, que indica a ação a ser executada (ex: GERAR_LIXO,
  // CAMINHAO_ENCHEU, etc.)
  private TipoEvento tipo;

  // Dados adicionais necessários para a execução do evento
  // Pode ser qualquer objeto relacionado ao evento (ex: uma Zona, um
  // CaminhaoPequeno, etc.)
  private Object dados;

  // Construtor do Evento, inicializa os atributos com os valores passados
  public Evento(double instante, TipoEvento tipo, Object dados) {
    this.instante = instante;
    this.tipo = tipo;
    this.dados = dados;
  }

  // Retorna o instante em que o evento deve ocorrer
  public double getInstante() {
    return instante;
  }

  // Retorna o tipo do evento
  public TipoEvento getTipo() {
    return tipo;
  }

  // Retorna os dados associados ao evento
  public Object getDados() {
    return dados;
  }
}
