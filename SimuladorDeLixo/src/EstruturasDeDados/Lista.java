package EstruturasDeDados;

import java.util.Comparator;

//lista genérica encadeada.
public class Lista<T> {
  private No<T> head; // primeiro
  private No<T> tail; // ultimo
  private int tamanho; // tamanho da lista

  public Lista() {
    this.tamanho = 0;
    this.head = null;
    this.tail = null;
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

  // remove do inicio
  public T removerInicio() {
    if (head == null)
      return null;
    T valor = head.getValor();
    head = head.getProx();
    if (head == null) {
      tail = null;
    }
    tamanho--;
    return valor;
  }

  // adiciona ao final.
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

  // imprime todos os valores.
  public void imprimir() {
    for (int i = 0; i < this.getTamanho(); i++) {
      System.out.println(this.get(i).getValor());

    }
  }

  // remove nó com valor igual.
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

  // usado para manter eventos ordenados por tempo.
  public void inserirOrdenado(T novoValor, Comparator<T> comparador) {
    No<T> novoNo = new No<>(novoValor);

    if (head == null || comparador.compare(novoValor, head.getValor()) < 0) {
      novoNo.setProx(head);
      head = novoNo;
      if (tail == null) {
        tail = novoNo;
      }
    } else {
      No<T> atual = head;
      while (atual.getProx() != null && comparador.compare(novoValor, atual.getProx().getValor()) >= 0) {
        atual = atual.getProx();
      }
      novoNo.setProx(atual.getProx());
      atual.setProx(novoNo);
      if (novoNo.getProx() == null) {
        tail = novoNo;
      }
    }
    tamanho++;
  }

  // retorna nó na posição.
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
