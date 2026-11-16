package Loja;

public class cura4 {
    private String nome;
    private int preco;
    private int cura;
    private boolean removeStatus; // remove queimadura e veneno
    private String descricao;

    public cura4() {
        this.nome = "Elixir Supremo";
        this.preco = 120;
        this.cura = 250;
        this.removeStatus = true;
        this.descricao = "Restaura 250 HP e remove queimadura e veneno.";
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