package Loja;

public class batata {
    private String nome = "Batata Misteriosa";
    private int preco = 200;
    private boolean disponivel = true;
    private boolean noInventario = false;

    public boolean comprar() {
        if (disponivel) {
            disponivel = false;
            noInventario = true;
            return true;
        }
        return false;
    }

    public int usar() {
        if (noInventario) {
            noInventario = false;
            return 1; // Cura 1 HP ao ser consumida
        }
        return 0;
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public boolean isDisponivel() { return disponivel; }
    public boolean isNoInventario() { return noInventario; }
}