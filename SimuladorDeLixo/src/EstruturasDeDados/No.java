package EstruturasDeDados;

// Classe genérica para representar um nó(elemento)de uma estrutura encadeada.
public class No<T> {
  private T valor; // valor genérico armazenado.
  private No<T> prox; // proximo elemento(Nó).

  // Construtor recebe o valor inicial
  public No(T novoValor) {
    this.valor = novoValor;
    this.prox = null;
  }

  // Getters e Setters para valor e próximo
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
