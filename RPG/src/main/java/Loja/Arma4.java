package Loja;

// Arma avançada para o Mago: Cajado Avançado.

public class Arma4 {
    private String nome;
    private int preco;
    private int bonusAtaque;
    private boolean disponivel;
    private String personagemCompativel;

    public Arma4() {
        this.nome = "Cajado Avançado";
        this.preco = 20;
        this.bonusAtaque = 9;
        this.disponivel = true;
        this.personagemCompativel = "Mago";
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

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }

    public String getPersonagemCompativel() {
        return personagemCompativel;
    }
}