package Modelo;

import java.util.Random;

public class Zona {
  private String nome; // Nome da zona (ex: Zona Norte, Zona Sul, etc.)
  private double lixoMin; // Quantidade mínima de lixo que pode ser gerada na zona
  private double lixoMax; // Quantidade máxima de lixo que pode ser gerada na zona
  private Random random = new Random(); // Gerador de valores aleatórios

  // Construtor que define o nome da zona e os limites de geração de lixo
  public Zona(String nome, double lixoMin, double lixoMax) {
    this.nome = nome;
    this.lixoMin = lixoMin;
    this.lixoMax = lixoMax;
  }

  // Gera uma quantidade aleatória de lixo dentro do intervalo definido para a
  // zona
  public double gerarLixo() {
    return lixoMin + (lixoMax - lixoMin) * random.nextDouble();
  }

  // Retorna o nome da zona
  public String getNome() {
    return nome;
  }
}
