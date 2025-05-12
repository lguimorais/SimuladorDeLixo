package Modelo;

public class Caminhoes {
  private double capacidade; // Capacidade máxima de carga em Kg.
  private double cargaAtual;
  private int maxViagens;
  private int viagensRealizadas;

  public Caminhoes(double capacidade, double cargaAtual, int maxViagens, int viagensRealizadas) {
    this.capacidade = capacidade;
    this.cargaAtual = cargaAtual;
    this.maxViagens = maxViagens;
    this.viagensRealizadas = viagensRealizadas;
  }

  public double getCapacidade() {
    return capacidade;
  }

  public void setCapacidade(double capacidade) {
    this.capacidade = capacidade;
  }

  public double getCargaAtual() {
    return cargaAtual;
  }

  public void setCargaAtual(double cargaAtual) {
    this.cargaAtual = cargaAtual;
  }

  public int getMaxViagens() {
    return maxViagens;
  }

  public void setMaxViagens(int maxViagens) {
    this.maxViagens = maxViagens;
  }

  public int getViagensRealizadas() {
    return viagensRealizadas;
  }

  public void setViagensRealizadas(int viagensRealizadas) {
    this.viagensRealizadas = viagensRealizadas;
  }

}
