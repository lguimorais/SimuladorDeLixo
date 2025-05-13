package EstruturasDeDados;

public class Fila<T> {
  private No<T> head; // primeiro
  private No<T> tail; // ultimo
  private int tamanho;

  public Fila() {
    this.tamanho = 0;
    this.head = null;
    this.tail = null;
  }
  
  public boolean estaVazia() {
    return tamanho == 0;
  }

  public void add(T novoValor) {
    No<T> novoNo = new No<T>(novoValor);
    if (estaVazia()) {
      this.head = novoNo;
      this.tail = novoNo;
    } else {
      this.tail.setProx(novoNo);
      this.tail = novoNo;
    }
    this.tamanho++;
  }
  
  public T desenfileirar() {
    if (estaVazia())
      return null;
    T valor = head.getValor();
    head = head.getProx();
    if (head == null) {
      tail = null;
    }
    tamanho--;
    return valor;
  }



  public void remove() {
    No<T> atual = this.head;
    if (estaVazia()) {
      return;
    } else if (atual == head) {
      this.head = atual.getProx();
      atual.setProx(null);
      tamanho--;
      return;
    }

  }

  public No getPrimeiro() {
    No atual = this.head;
    return atual;
  }

}
