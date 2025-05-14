package Modelo;

import java.util.Random;

public class Lixo {
  // Peso dos resíduos (em kg)
  private double pesoResiduos;

  // Tipo do resíduo (ex: Orgânico, Plástico, etc.)
  private String tipoResiduos;

  // Tipos possíveis de resíduos
  private static final String[] TIPOS = {
      "Orgânico", "Plástico", "Metal", "Vidro", "Papel", "Eletrônico"
  };

  // Peso mínimo e máximo para o lixo gerado aleatoriamente
  private static final double PESO_MIN = 100.0; // mínimo 100 kg
  private static final double PESO_MAX = 1000.0; // máximo 1000 kg

  // Gerador de números aleatórios
  private static final Random random = new Random();

  // Construtor que gera lixo com peso e tipo aleatórios
  public Lixo() {
    this.pesoResiduos = gerarPesoAleatorio();
    this.tipoResiduos = gerarTipoAleatorio();
  }

  // Construtor que recebe valores específicos de peso e tipo
  public Lixo(double pesoResiduo, String tipoResiduo) {
    this.pesoResiduos = pesoResiduo;
    this.tipoResiduos = tipoResiduo;
  }

  // Gera um peso aleatório dentro do intervalo definido
  private double gerarPesoAleatorio() {
    return PESO_MIN + (PESO_MAX - PESO_MIN) * random.nextDouble();
  }

  // Seleciona aleatoriamente um tipo de resíduo
  private String gerarTipoAleatorio() {
    return TIPOS[random.nextInt(TIPOS.length)];
  }

  // Getter para o peso dos resíduos
  public double getPesoResiduos() {
    return pesoResiduos;
  }

  // Setter para o peso dos resíduos
  public void setPesoResiduos(double pesoResiduo) {
    this.pesoResiduos = pesoResiduo;
  }

  // Getter para o tipo de resíduo
  public String getTipoResiduos() {
    return tipoResiduos;
  }

  // Setter para o tipo de resíduo
  public void setTipoResiduos(String tipoResiduo) {
    this.tipoResiduos = tipoResiduo;
  }

  // Representação textual do objeto Lixo (ex: "Plástico - 530.25 kg")
  @Override
  public String toString() {
    return tipoResiduos + " - " + String.format("%.2f", pesoResiduos) + " kg";
  }
}
