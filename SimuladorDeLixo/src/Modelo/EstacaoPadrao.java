package Modelo;

import EstruturasDeDados.Fila;
import EstruturasDeDados.Lista;

public class EstacaoPadrao extends EstacaoTransferencia {
    private Fila<CaminhaoPequenoPadrao> filaCaminhoes = new Fila<>();
    private int lixoArmazenado;

    public EstacaoPadrao(String nome, int lixoArmazenado) {
        super(nome);
        this.lixoArmazenado = lixoArmazenado;
    }

    public Fila<CaminhaoPequenoPadrao> getFilaCaminhoes() {
        return filaCaminhoes;
    }
    public void transferirLixoParaCaminhoesGrandes(
            Lista<CaminhaoPequeno> caminhoesPequenos,
            Fila<CaminhaoGrande> filaGrandes) {

        // Percorre todos os caminhões pequenos usando sua Lista<T>
        for (int i = 0; i < caminhoesPequenos.getTamanho(); i++) {
            CaminhaoPequeno pequeno = caminhoesPequenos.getValor(i);

            if (pequeno == null)
                continue;

            int lixoRestante = pequeno.descarregar();
            System.out.println("Caminhão pequeno descarregou " + lixoRestante + "kg na estação " + nome);

            while (lixoRestante > 0) {
                if (filaGrandes.estaVazia()) {
                    System.out.println("Nenhum caminhão grande disponível! Criando novo caminhão.");
                    CaminhaoGrande novo = new CaminhaoGrandePadrao(20000, 5);
                    filaGrandes.add(novo);
                }

                // Obtém o primeiro caminhão sem remover
                CaminhaoGrande grandeAtual = (CaminhaoGrande) filaGrandes.getPrimeiro().getValor();
                int espacoDisponivel = grandeAtual.getCapacidadeMaxima() - grandeAtual.getCargaAtual();

                if (espacoDisponivel >= lixoRestante) {
                    grandeAtual.carregar(lixoRestante);
                    System.out.println("Caminhão grande carregou " + lixoRestante + "kg.");
                    lixoRestante = 0;

                    if (grandeAtual.prontoParaPartir()) {
                        System.out.println("Caminhão grande partiu para o aterro cheio.");
                        filaGrandes.remove(); // Remove usando seu método remove()
                        grandeAtual.descarregar(0);
                    }
                } else {
                    grandeAtual.carregar(espacoDisponivel);
                    lixoRestante -= espacoDisponivel;
                    System.out.println("Caminhão grande carregou " + espacoDisponivel + "kg e partiu cheio.");
                    filaGrandes.remove(); // Remove usando seu método remove()
                    grandeAtual.descarregar(0);
                }
            }
        }
    }

    @Override
    public void receberCaminhaoPequeno(CaminhaoPequeno caminhao) {
        if (caminhao instanceof CaminhaoPequenoPadrao) {
            filaCaminhoes.add((CaminhaoPequenoPadrao) caminhao);
            System.out.println("Estação " + nome + " recebeu caminhão pequeno");
        }
    }
    @Override
    public void descarregarParaCaminhaoGrande(CaminhaoGrande caminhao) {
        caminhao.carregar(lixoArmazenado);
        System.out.println("Estação " + nome + " carregou caminhão grande com " + lixoArmazenado + "kg.");
        lixoArmazenado = 0;
    }
}