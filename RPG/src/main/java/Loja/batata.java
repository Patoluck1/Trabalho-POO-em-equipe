package Loja;

// Item especial da loja: Batata.
// - Só existe 1 unidade em estoque.
// - Depois de comprada, some da loja até ser usada.
// - Ao ser usada (cura 1 ponto de saúde), volta a ficar disponível na loja.
// - Enquanto estiver no inventário, ativa a chance de encontrar o Senhor Batata.

public class batata {
    private String nome;
    private int preco;           // TODO: definir o preço
    private final int cura;
    private boolean disponivel;
    private boolean noInventario;

    public batata() {
        this.nome = "Batata";
        this.preco = 150;          // Coloque aqui o preço
        this.cura = 1;
        this.disponivel = true;
        this.noInventario = false;
    }

    public boolean comprar() {
        if (disponivel && !noInventario) {
            disponivel = false;
            noInventario = true;
            return true;
        }
        return false;
    }

    public int usar() {
        if (noInventario) {
            noInventario = false;
            disponivel = true;
            return cura;
        }
        return 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = Math.max(0, preco);
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public int getCura() {
        return cura;
    }

    public boolean isNoInventario() {
        return noInventario;
    }

    public void setNoInventario(boolean noInventario) {
        this.noInventario = noInventario;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}