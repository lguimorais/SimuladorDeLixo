package Modelo;

public class CaminhaoGrandePadrao extends CaminhaoGrande {
    public CaminhaoGrandePadrao(int capacidade ,int toleranciaEspera) {
        this.capacidadeMaxima = capacidade;
        this.cargaAtual = 0;
        this.tempoEspera = 0;
        this.toleranciaEspera = toleranciaEspera;
        this.emUso = false;
     
    }
    
    public int getCargaAtual() {
        return this.cargaAtual;
    }

    @Override
    public void carregar(int quantidade) {
        cargaAtual += quantidade;
        if (cargaAtual > capacidadeMaxima) {
            cargaAtual = capacidadeMaxima;
        }
    }

}