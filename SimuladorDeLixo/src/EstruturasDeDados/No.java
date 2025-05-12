package EstruturasDeDados;

public class No<T> {
  private T valor;
  private No<T> prox; // proximo elemento

  public No(T novoValor) {
    this.valor = novoValor;
    this.prox = null;
  }

  public T getValor() {
    return valor;
  }

  public void setValor(T valor) {
    this.valor = valor;
  }

  public No<T> getProx() {
    return prox;
  }

  public void setProx(No<T> prox) {
    this.prox = prox;
  }

}
