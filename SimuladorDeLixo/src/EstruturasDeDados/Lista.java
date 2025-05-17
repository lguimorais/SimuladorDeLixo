package EstruturasDeDados;

import java.util.Comparator;

// Lista genérica encadeada (LinkedList), implementada manualmente.
public class Lista<T> {
  private No<T> head; // Primeiro nó (início da lista)
  private No<T> tail; // Último nó (fim da lista)
  private int tamanho; // Quantidade de elementos na lista

  // Construtor: inicializa uma lista vazia
  public Lista() {
    this.tamanho = 0;
    this.head = null;
    this.tail = null;
  }

  // Getters e Setters básicos
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

  // Remove o primeiro elemento da lista e retorna seu valor
  public T removerInicio() {
    if (head == null)
      return null;

    T valor = head.getValor();
    head = head.getProx();

    // Se a lista ficou vazia após a remoção
    if (head == null) {
      tail = null;
    }

    tamanho--;
    return valor;
  }

  // Adiciona um novo elemento ao final da lista
  public void add(T novoValor) {
    No<T> novoNo = new No<T>(novoValor);

    if (head == null && tail == null) {
      // Lista vazia: novo nó vira head e tail
      this.head = novoNo;
      this.tail = novoNo;
    } else {
      // Encadeia o novo nó no final
      this.tail.setProx(novoNo);
      this.tail = novoNo;
    }

    this.tamanho++;
  }

  // Imprime todos os elementos da lista usando o método toString dos objetos
  public void imprimir() {
    for (int i = 0; i < this.getTamanho(); i++) {
      System.out.println(this.get(i).getValor());
    }
  }

  // Remove o primeiro nó que contenha o valor igual ao informado
  public void remove(T valorProcurado) {
    No<T> ant = null;
    No<T> atual = this.head;

    for (int i = 0; i < this.getTamanho(); i++) {
      if (atual.getValor().equals(valorProcurado)) {
        if (atual == head && atual == tail) {
          // Único nó da lista
          this.head = null;
          this.tail = null;
        } else if (atual == head) {
          // Nó a ser removido é o primeiro
          this.head = atual.getProx();
          atual.setProx(null);
        } else if (atual == tail) {
          // Nó a ser removido é o último
          this.tail = ant;
          ant.setProx(null);
        } else {
          // Nó está no meio da lista
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

  // Insere um novo valor em ordem, de acordo com o comparador fornecido
  public void inserirOrdenado(T novoValor, Comparator<T> comparador) {
    No<T> novoNo = new No<>(novoValor);

    if (head == null || comparador.compare(novoValor, head.getValor()) < 0) {
      // Inserir no início da lista
      novoNo.setProx(head);
      head = novoNo;
      if (tail == null) {
        tail = novoNo;
      }
    } else {
      // Procurar posição correta
      No<T> atual = head;
      while (atual.getProx() != null &&
          comparador.compare(novoValor, atual.getProx().getValor()) >= 0) {
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

  // Retorna o nó na posição informada (começa em 0)
  public No<T> get(int posicao) {
    No atual = this.head;

    for (int i = 0; i < posicao; i++) {
      if (atual.getProx() != null) {
        atual = atual.getProx();
      }
    }

    return atual;
  }
  
  public T getValor(int posicao) {
    No<T> atual = this.head;
    int i = 0;
    while (atual != null && i < posicao) {
      atual = atual.getProx();
      i++;
    }
    return atual != null ? atual.getValor() : null;
  }
}
