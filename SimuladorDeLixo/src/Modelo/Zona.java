package Modelo;
public class Zona {
  private String nome;
  private double intervaloMin;
  private double intervaloMax;
  private boolean horarioDePico;
  private double quantidadeLixoGerada;
  private double capacidadeLixoMax;

  public Zona(String nome, double intervaloMin, double intervaloMax, boolean horarioDePico,
      double quantidadeLixoGerada, double capacidadeLixoMax) {
    this.nome = nome;
    this.intervaloMin = intervaloMin;
    this.intervaloMax = intervaloMax;
    this.horarioDePico = horarioDePico;
    this.quantidadeLixoGerada = quantidadeLixoGerada;
    this.capacidadeLixoMax = capacidadeLixoMax;
  }

}
