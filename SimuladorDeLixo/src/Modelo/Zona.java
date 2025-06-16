package Modelo;

import EstruturasDeDados.Lista;
import java.util.Random;

public class Zona {
  private String nome;
  private double lixoMin;
  private double lixoMax;
  private Random random = new Random();
  private Lista<CaminhaoPequenoPadrao> caminhoesPequenos = new Lista<>();
  private EstacaoPadrao estacaoDestino;
  private double totalGerado = 0;
  private double totalColetado = 0;

  // Quantidade de caminhões por período

  public Zona(String nome, double lixoMin, double lixoMax) {
    this.nome = nome;
    this.lixoMin = lixoMin;
    this.lixoMax = lixoMax;

    // Inicializa com zero por padrão

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
  
  public void registrarColeta(double quantidade) {
    totalColetado += quantidade;
  }

  // Retorna a quantidade de lixo gerada aleatoriamente dentro do intervalo
  public double gerarLixo() {
    double gerado = lixoMin + (lixoMax - lixoMin) * random.nextDouble();
    totalGerado += gerado;
    return gerado;
  }
  
  public void distribuirCaminhoesParaEstacao(Lista<CaminhaoPequenoPadrao> caminhoes) {
    for (int i = 0; i < caminhoes.getTamanho(); i++) {
      CaminhaoPequenoPadrao caminhao = caminhoes.getValor(i);
      if (caminhao.estaCheio()) {
        estacaoDestino.receberCaminhaoPequeno(caminhao);
      }
    }
  }
  
  public double getTotalGerado() {
    return totalGerado;
  }

  public double getTotalColetado() {
    return totalColetado;
  }
  
  public String getNome() {
    return nome;
  }
}