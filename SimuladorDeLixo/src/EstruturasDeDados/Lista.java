package EstruturasDeDados;

public class Lista<T> {
  private No<T> head; // primeiro
  private No<T> tail; // ultimo
  private int tamanho;

  public Lista() {
    this.tamanho = 0;
    this.head = null;// ter ou nao o null nao faz diferenca para a execuçao do codigo
    this.tail = null;// ter ou nao o null nao faz diferenca para a execuçao do codigo

  }

  public No<T> getHead() {
    return head;
  }

  public void setHead(No<T> head) {
    this.head = head;
  }

  public No<T> getTail() {
    return tail;
  }

  public void setTail(No<T> tail) {
    this.tail = tail;
  }

  public int getTamanho() {
    return tamanho;
  }

  public void setTamanho(int tamanho) {
    this.tamanho = tamanho;
  }

  public void add(T novoValor) {

    No<T> novoNo = new No<T>(novoValor);
    if (head == null && tail == null) {
      this.head = novoNo;
      this.tail = novoNo;
    } else {
      this.tail.setProx(novoNo);
      this.tail = novoNo;
    }
    this.tamanho++;
  }

  public void imprimir() {
    for (int i = 0; i < this.getTamanho(); i++) {
      System.out.println(this.get(i).getValor());

    }
  }

  public void remove(T valorProcurado) {
    No<T> ant = null;
    No<T> atual = this.head;
    for (int i = 0; i < this.getTamanho(); i++) {
      if (atual.getValor().equals(valorProcurado)) {
        if (atual == head && atual == tail) {
          this.head = null;
          this.tail = null;

        } else if (atual == head) {
          this.head = atual.getProx();
          atual.setProx(null);

        } else if (atual == tail) {
          this.tail = ant;
          ant.setProx(null);
        } else {
          ant.setProx(atual.getProx());
          atual = null;
        }
        this.tamanho--;
        break;
      }
      ant = atual;
      atual = atual.getProx();
    }

  }

  public No get(int posicao) {
    No atual = this.head;
    for (int i = 0; i < posicao; i++) {
      if (atual.getProx() != null) {
        atual = atual.getProx();

      }

    }
    return atual;

  }

}
