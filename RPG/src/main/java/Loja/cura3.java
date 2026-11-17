package Loja;

public class cura3 {
    private String nome = "Antídoto Purificador";
    private int preco = 15;
    private String descricao = "Recupera 15 HP e limpa veneno/fogo/lentidão";
    private boolean removeStatus = true;

    public int curar(int vidaAtual, int vidaMax) {
        return Math.min(vidaMax, vidaAtual + 15);
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public String getDescricao() { return descricao; }
    public boolean isRemoveStatus() { return removeStatus; }
}