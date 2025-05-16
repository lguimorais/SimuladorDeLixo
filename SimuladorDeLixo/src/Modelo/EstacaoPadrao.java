package Modelo;

import EstruturasDeDados.Fila;
import EstruturasDeDados.Lista;

public class EstacaoPadrao extends EstacaoTransferencia {

    private int lixoArmazenado;
    private Fila<CaminhaoPequenoPadrao> filaPequenos;
    private Lista<CaminhaoGrandePadrao> caminhoesGrandes;

    public EstacaoPadrao(String nome, int lixoArmazenado) {
        super(nome);
        this.lixoArmazenado = lixoArmazenado;
        this.filaPequenos = new Fila<>();
        this.caminhoesGrandes = new Lista<>();
    }

    @Override
    public void receberCaminhaoPequeno(CaminhaoPequeno caminhao) {
        if (caminhao instanceof CaminhaoPequenoPadrao) {
            filaPequenos.add((CaminhaoPequenoPadrao) caminhao);
            System.out.println("Estação " + nome + ": Caminhão pequeno entrou na fila.");
        }
    }

    @Override
    public void descarregarParaCaminhaoGrande(CaminhaoGrande caminhao) {
        caminhao.carregar(lixoArmazenado);
        System.out.println("Estação " + nome + " carregou caminhão grande com " + lixoArmazenado + "kg.");
        lixoArmazenado = 0;
    }

    public void adicionarCaminhaoGrande(CaminhaoGrandePadrao caminhao) {
        caminhoesGrandes.add(caminhao);
    }

    public void processar(int minutoSimulado) {
        // 1. Processar um caminhão pequeno da fila por vez (se houver)
        if (!filaPequenos.estaVazia()) {
            CaminhaoPequenoPadrao pequeno = filaPequenos.remove();
            int descarregado = pequeno.descarregar();
            lixoArmazenado += descarregado;
            System.out.println(
                    "Estação " + nome + " recebeu " + descarregado + "kg de " + pequeno.getClass().getSimpleName());
        }

        // 2. Processar caminhões grandes
        if (caminhoesGrandes.getTamanho() > 0 && lixoArmazenado > 0) {
            CaminhaoGrandePadrao primeiro = caminhoesGrandes.get(0).getValor();

            if (!primeiro.estaEmUso()) {
                primeiro.ocupar();
            } else {
                primeiro.incrementarEspera();
            }

            int cargaAntes = primeiro.getCargaAtual();
            primeiro.carregar(lixoArmazenado);
            int cargaDepois = primeiro.getCargaAtual();
            int carregadoAgora = cargaDepois - cargaAntes;

            lixoArmazenado -= carregadoAgora;

            System.out.println("Estação " + nome + " transferiu " + carregadoAgora + "kg para caminhão grande.");

            if (primeiro.devePartir()) {
                primeiro.descarregar(minutoSimulado);
                System.out.println("Caminhão grande partiu do " + nome);
                // Liberar para próximo uso (exemplo)
                primeiro.liberar();
            }
        }
    }

    public int getLixoArmazenado() {
        return lixoArmazenado;
    }

    public Fila<CaminhaoPequenoPadrao> getFilaPequenos() {
        return filaPequenos;
    }

    public Lista<CaminhaoGrandePadrao> getCaminhoesGrandes() {
        return caminhoesGrandes;
    }
}