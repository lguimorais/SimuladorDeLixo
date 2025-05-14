package Mecanismo;

// Define todos os tipos possíveis de eventos que o simulador entende.
// Cada tipo representa uma ação específica no fluxo da simulação.
public enum TipoEvento {
  GERAR_LIXO, // Evento em que uma nova carga de lixo é gerada em uma zona
  CAMINHAO_ENCHEU, // Evento quando um caminhão atinge sua capacidade máxima
  CAMINHAO_CHEGOU_ESTACAO, // Evento quando o caminhão chega a uma estação de transferência
  TEMPO_ESPERA_EXCEDIDO, // Evento disparado quando o tempo de espera máximo de um caminhão é
                         // ultrapassado
  CAMINHAO_PARTE_ATERRO // Evento que indica que o caminhão partiu da estação rumo ao aterro
}
