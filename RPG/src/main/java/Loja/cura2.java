package Loja;

public class cura2 {
    private String nome = "Poção de Vida Grande";
    private int preco = 20;
    private String descricao = "Recupera 60 HP";
    private boolean removeStatus = false;

    public int curar(int vidaAtual, int vidaMax) {
        return Math.min(vidaMax, vidaAtual + 60);
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public String getDescricao() { return descricao; }
    public boolean isRemoveStatus() { return removeStatus; }
}