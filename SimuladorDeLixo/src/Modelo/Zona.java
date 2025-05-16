package Modelo;

import EstruturasDeDados.Lista;
import java.util.EnumMap;
import java.util.Random;

public class Zona {
  private String nome;
  private double lixoMin;
  private double lixoMax;
  private Random random = new Random();
  private Lista<CaminhaoPequenoPadrao> caminhoesPequenos = new Lista<>();
  private EstacaoPadrao estacaoDestino;

  // Quantidade de caminhões por período
  private EnumMap<PeriodoDia, Integer> caminhoneirosPorPeriodo = new EnumMap<>(PeriodoDia.class);

  public Zona(String nome, double lixoMin, double lixoMax) {
    this.nome = nome;
    this.lixoMin = lixoMin;
    this.lixoMax = lixoMax;

    // Inicializa com zero por padrão
    for (PeriodoDia periodo : PeriodoDia.values()) {
      caminhoneirosPorPeriodo.put(periodo, 0);
    }
  }
  
  public void setEstacaoDestino(EstacaoPadrao estacao) {
    this.estacaoDestino = estacao;
  }

  public EstacaoPadrao getEstacaoDestino() {
    return estacaoDestino;
  }
  public void adicionarCaminhaoPequeno(CaminhaoPequenoPadrao caminhao) {
    caminhoesPequenos.add(caminhao);
  }

  public Lista<CaminhaoPequenoPadrao> getCaminhoesPequenos() {
    return caminhoesPequenos;
  }

  public void setCaminhoneirosPorPeriodo(PeriodoDia periodo, int quantidade) {
    caminhoneirosPorPeriodo.put(periodo, quantidade);
  }

  public int getCaminhoneirosPorPeriodo(PeriodoDia periodo) {
    return caminhoneirosPorPeriodo.getOrDefault(periodo, 0);
  }

  // Retorna a quantidade de lixo gerada aleatoriamente dentro do intervalo
  public double gerarLixo() {
    return lixoMin + (lixoMax - lixoMin) * random.nextDouble();
  }

  // Retorna quantos caminhões devem sair dada a hora do dia
  public int caminhoneirosParaHora(int hora) {
    if (hora >= 6 && hora < 10) {
      return getCaminhoneirosPorPeriodo(PeriodoDia.MANHA);
    } else if (hora >= 10 && hora < 15) {
      return getCaminhoneirosPorPeriodo(PeriodoDia.PICO);
    } else if (hora >= 15 && hora < 18) {
      return getCaminhoneirosPorPeriodo(PeriodoDia.TARDE);
    } else {
      return 0; // Fora do horário operacional
    }
  }
  

  public String getNome() {
    return nome;
  }
}