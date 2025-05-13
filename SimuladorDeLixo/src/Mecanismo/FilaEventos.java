package Mecanismo;

import EstruturasDeDados.Lista;
import java.util.Comparator;

public class FilaEventos {
  private Lista<Evento> eventos = new Lista<>();

  public void adicionar(Evento evento) {
    eventos.inserirOrdenado(evento, new Comparator<Evento>() {
      @Override
      public int compare(Evento a, Evento b) {
        return Double.compare(a.getInstante(), b.getInstante());
      }
    });
  }

  public Evento proximo() {
    return eventos.removerInicio();
  }

  public boolean estaVazia() {
    return eventos.getTamanho() == 0;
  }
}