package Loja;

//Arma avançada para o Bárbaro: Machado Avançado.

public class Arma2 {
    private String nome;
    private int preco;
    private int bonusAtaque;
    private boolean disponivel;
    private String personagemCompativel;

    public Arma2() {
        this.nome = "Machado Avançado";
        this.preco = 30;
        this.bonusAtaque = 12;
        this.disponivel = true;
        this.personagemCompativel = "Bárbaro";
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