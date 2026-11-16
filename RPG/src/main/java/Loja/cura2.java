package Loja;

public class cura2 {
    private String nome;
    private int preco;
    private int cura;
    private boolean removeStatus; // remove queimadura e veneno
    private String descricao;

    public cura2() {
        this.nome = "Poção de Cura Média";
        this.preco = 40;
        this.cura = 60;
        this.removeStatus = false;
        this.descricao = "Restaura 60 HP.";
    }

    // Retorna a nova vida do jogador após usar a poção (nunca passa da vida máxima)
    public int curar(int vidaAtual, int vidaMaxima) {
        return Math.min(vidaMaxima, vidaAtual + cura);
    }

    // Getters
    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public int getCura() { return cura; }
    public boolean isRemoveStatus() { return removeStatus; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() {
        return nome + " - Cura: " + cura + " HP | Preço: " + preco + " moedas";
    }
}