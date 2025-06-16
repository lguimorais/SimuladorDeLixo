package Modelo;

public class CaminhaoPequenoPadrao extends CaminhaoPequeno {

    public CaminhaoPequenoPadrao(int capacidade, int maxViagensPorDia) {
        this.capacidade = capacidade;
        this.cargaAtual = 0;
        this.maxViagensPorDia = maxViagensPorDia;
        this.viagensRealizadas = 0;
        this.totalColetado = 0;
        this.nome = "CP-" + (contador++);
    }


    @Override
    public boolean coletar(int quantidade) {
        if (cargaAtual + quantidade <= capacidade) {
            cargaAtual += quantidade;
            totalColetado += quantidade;
            System.out.println("Caminhão pequeno coletou " + quantidade + "T. Carga atual: " + cargaAtual);
            return true;
        }
        return false;
    }
    
    public String getNome() {
        return nome;
    }
    public int getTotalColetado() {
        return totalColetado;
    }

    public int getViagensRealizadas() {
        return viagensRealizadas;
    }

    public boolean estaDisponivel() {
        return this.viagensRealizadas < this.maxViagensPorDia && this.cargaAtual < this.capacidade;
    }
}