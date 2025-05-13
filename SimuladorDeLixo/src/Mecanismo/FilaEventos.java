package Mecanismo;

import EstruturasDeDados.Lista;
import java.util.Comparator;

public class FilaEventos {
  private Lista<Evento> eventos = new Lista<>();

  public void adicionar(Evento evento) { // insere na lista ordenada por tempo.
    eventos.inserirOrdenado(evento, new Comparator<Evento>() {
      @Override
      public int compare(Evento a, Evento b) {
        return Double.compare(a.getInstante(), b.getInstante());
      }
    });
  }

  public Evento proximo() { // retorna e remove o evento mais próximo (menor tempo).
    return eventos.removerInicio();
  }

  public boolean estaVazia() { // verifica se a lista esta vazia.
    return eventos.getTamanho() == 0;
  }
}