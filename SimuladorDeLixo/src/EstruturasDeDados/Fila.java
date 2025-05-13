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

  // verificaçao se a lista esta vazia
  public boolean estaVazia() {
    return tamanho == 0;
  }

  // insere no final.
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
  // remove o primeiro da fila.
  // public T desenfileirar() {
  // if (estaVazia())
  // return null;
  // T valor = head.getValor();
  // head = head.getProx();
  // if (head == null) {
  // tail = null;
  // }
  // tamanho--;
  // return valor;
  // } exemplo do GPT

  
  // remove o primeiro da fila.
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

  // retorna o primeiro sem remover.
  public No getPrimeiro() {
    No atual = this.head;
    return atual;
  }

}
