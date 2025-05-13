package Mecanismo;

public class MotorSimulacao {
  private double tempoAtual = 0;
  private FilaEventos filaEventos = new FilaEventos();

  public void agendarEvento(Evento evento) {
    filaEventos.adicionar(evento);
  }

  public void executar() {
    while (!filaEventos.estaVazia()) {
      Evento evento = filaEventos.proximo();
      tempoAtual = evento.getInstante();
      processarEvento(evento);
    }
  }

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

  public double getTempoAtual() {
    return tempoAtual;
  }
}