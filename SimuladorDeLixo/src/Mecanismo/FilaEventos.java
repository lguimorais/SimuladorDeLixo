package Mecanismo;

import EstruturasDeDados.Lista;
import java.util.Comparator;

// Classe que gerencia a fila de eventos, mantendo-os ordenados por tempo (instante)
public class FilaEventos {
  // Lista de eventos ordenada pelo instante em que cada evento deve ocorrer
  private Lista<Evento> eventos = new Lista<>();

  // Adiciona um novo evento na lista de forma ordenada, baseado no instante do
  // evento
  public void adicionar(Evento evento) {
    // Insere o evento na posição correta utilizando um comparador de instantes
    eventos.inserirOrdenado(evento, new Comparator<Evento>() {
      @Override
      public int compare(Evento a, Evento b) {
        // Compara os instantes dos dois eventos para definir a ordem
        return Double.compare(a.getInstante(), b.getInstante());
      }
    });
  }

  // Retorna e remove o evento com o menor instante (mais próximo a ocorrer)
  public Evento proximo() {
    return eventos.removerInicio(); // o início da lista sempre tem o evento mais próximo
  }

  // Verifica se ainda existem eventos na fila
  public boolean estaVazia() {
    return eventos.getTamanho() == 0; // retorna true se a lista estiver vazia
  }
}
