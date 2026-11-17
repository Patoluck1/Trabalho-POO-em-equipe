package Loja;

public class cura4 {
    private String nome = "Elixir Supremo";
    private int preco = 35;
    private String descricao = "Recupera 100% do HP e limpa todos os status";
    private boolean removeStatus = true;

    public int curar(int vidaAtual, int vidaMax) {
        return vidaMax;
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public String getDescricao() { return descricao; }
    public boolean isRemoveStatus() { return removeStatus; }
}