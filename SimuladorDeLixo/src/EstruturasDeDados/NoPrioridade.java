package EstruturasDeDados;

public class NoPrioridade<T> {
  public T valor;
  public double prioridade;

  public NoPrioridade(T novoValor, double prioridade) {
    this.valor = novoValor;
    this.prioridade = prioridade;
  }
}
