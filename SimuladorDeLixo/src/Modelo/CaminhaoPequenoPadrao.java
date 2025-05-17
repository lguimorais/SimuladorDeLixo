package Modelo;

public class CaminhaoPequenoPadrao extends CaminhaoPequeno {

    public CaminhaoPequenoPadrao(int capacidade, int maxViagensPorDia) {
        this.capacidade = capacidade;
        this.cargaAtual = 0;
        this.maxViagensPorDia = maxViagensPorDia;
        this.viagensRealizadas = 0;
    }

    @Override
    public boolean coletar(int quantidade) {
      
        if (cargaAtual + quantidade <= capacidade) {
            cargaAtual += quantidade;
            System.out.println("Caminhão pequeno coletou " + quantidade + "kg. Carga atual: " + cargaAtual);
            return true;
        }
        return false;
    }
    
    public boolean estaDisponivel() {
        return this.viagensRealizadas < this.maxViagensPorDia && this.cargaAtual < this.capacidade;
    }
}