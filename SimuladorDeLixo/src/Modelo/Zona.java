package Modelo;

import java.util.Random;

public class Zona {
  private String nome;
  private double lixoMin;
  private double lixoMax;
  private Random random = new Random();

  public Zona(String nome, double lixoMin, double lixoMax) {
    this.nome = nome;
    this.lixoMin = lixoMin;
    this.lixoMax = lixoMax;
  }

  public double gerarLixo() {
    return lixoMin + (lixoMax - lixoMin) * random.nextDouble();
  }

  public String getNome() {
    return nome;
  }
}