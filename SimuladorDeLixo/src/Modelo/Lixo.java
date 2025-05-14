package Modelo;

import java.util.Random;

public class Lixo {
  private double pesoResiduos;
  private String tipoResiduos;

  private static final String[] TIPOS = {
      "Orgânico", "Plástico", "Metal", "Vidro", "Papel", "Eletrônico"
  };

  private static final double PESO_MIN = 100.0; // mínimo 100 kg
  private static final double PESO_MAX = 1000.0; // máximo 1000 kg

  private static final Random random = new Random();

  public Lixo() {
    this.pesoResiduos = gerarPesoAleatorio();
    this.tipoResiduos = gerarTipoAleatorio();
  }

  public Lixo(double pesoResiduo, String tipoResiduo) {
    this.pesoResiduos = pesoResiduo;
    this.tipoResiduos = tipoResiduo;
  }

  private double gerarPesoAleatorio() {
    return PESO_MIN + (PESO_MAX - PESO_MIN) * random.nextDouble();
  }

  private String gerarTipoAleatorio() {
    return TIPOS[random.nextInt(TIPOS.length)];
  }

  public double getPesoResiduos() {
    return pesoResiduos;
  }

  public void setPesoResiduos(double pesoResiduo) {
    this.pesoResiduos = pesoResiduo;
  }

  public String getTipoResiduos() {
    return tipoResiduos;
  }

  public void setTipoResiduos(String tipoResiduo) {
    this.tipoResiduos = tipoResiduo;
  }

  @Override
  public String toString() {
    return tipoResiduos + " - " + String.format("%.2f", pesoResiduos) + " kg";
  }
}
