package Loja;

public class cura1 {
    private String nome = "Poção de Vida Pequena";
    private int preco = 10;
    private String descricao = "Recupera 25 HP";
    private boolean removeStatus = false;

    public int curar(int vidaAtual, int vidaMax) {
        return Math.min(vidaMax, vidaAtual + 25);
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public String getDescricao() { return descricao; }
    public boolean isRemoveStatus() { return removeStatus; }
}