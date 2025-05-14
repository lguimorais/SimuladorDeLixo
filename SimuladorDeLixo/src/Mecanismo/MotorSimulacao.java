package Mecanismo;

// Classe responsável por executar a simulação, controlando o tempo e processando os eventos
public class MotorSimulacao {
  // Representa o tempo atual da simulação
  private double tempoAtual = 0;

  // Fila de eventos que ocorrerão durante a simulação, ordenados pelo instante
  private FilaEventos filaEventos = new FilaEventos();

  // Agenda um novo evento para ser processado posteriormente
  public void agendarEvento(Evento evento) {
    filaEventos.adicionar(evento);
  }

  // Executa a simulação processando todos os eventos na ordem cronológica
  public void executar() {
    // Enquanto houver eventos na fila
    while (!filaEventos.estaVazia()) {
      // Retira o próximo evento (com menor instante)
      Evento evento = filaEventos.proximo();

      // Atualiza o tempo atual para o instante do evento
      tempoAtual = evento.getInstante();

      // Processa o evento conforme seu tipo
      processarEvento(evento);
    }
  }

  // Método responsável por tratar cada tipo de evento
  private void processarEvento(Evento evento) {
    switch (evento.getTipo()) {
      case GERAR_LIXO:
        System.out.println("[" + tempoAtual + "] Evento: GERAR_LIXO");
        break;
      case CAMINHAO_ENCHEU:
        System.out.println("[" + tempoAtual + "] Evento: CAMINHAO_ENCHEU");
        break;
      case CAMINHAO_CHEGOU_ESTACAO:
        System.out.println("[" + tempoAtual + "] Evento: CAMINHAO_CHEGOU_ESTACAO");
        break;
      case TEMPO_ESPERA_EXCEDIDO:
        System.out.println("[" + tempoAtual + "] Evento: TEMPO_ESPERA_EXCEDIDO");
        break;
      case CAMINHAO_PARTE_ATERRO:
        System.out.println("[" + tempoAtual + "] Evento: CAMINHAO_PARTE_ATERRO");
        break;
    }
  }

  // Retorna o tempo atual da simulação (último evento processado)
  public double getTempoAtual() {
    return tempoAtual;
  }
}
