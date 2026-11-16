package Loja;

//Arma avançada para o Ladino: Adagas Avançadas.

public class Arma3 {
    private String nome;
    private int preco;
    private int bonusAtaque;
    private boolean disponivel;
    private String personagemCompativel;

    public Arma3() {
        this.nome = "Adagas Avançadas";
        this.preco = 15;
        this.bonusAtaque = 10;
        this.disponivel = true;
        this.personagemCompativel = "Ladino";
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