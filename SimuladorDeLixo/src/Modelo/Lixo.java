package Modelo;
public class Lixo {
  private double pesoResiduos;
  private String tipoResiduos;

  public Lixo(double  pesoResiduo , String tipoResiduo){
    this.pesoResiduos = pesoResiduo;
    this.tipoResiduos = tipoResiduo;
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

}
