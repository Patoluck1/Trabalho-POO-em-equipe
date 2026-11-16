package Loja;

//Arma avançada para o Arqueiro: Arco Avançado.

public class Arma1 {
    private String nome;
    private int preco;       // TODO: definir o preço
    private int bonusAtaque;
    private boolean disponivel;
    private String personagemCompativel;

    public Arma1() {
        this.nome = "Arco Avançado";
        this.preco = 20;
        this.bonusAtaque = 8;
        this.disponivel = true;
        this.personagemCompativel = "Arqueiro";
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