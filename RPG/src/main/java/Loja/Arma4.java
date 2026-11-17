package Loja;

public class Arma4 {
    private String nome = "Cajado Arcano";
    private int preco = 35;
    private int bonusAtaque = 10;
    private String personagemCompativel = "Mago";
    private boolean disponivel = true;

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public int getBonusAtaque() { return bonusAtaque; }
    public String getPersonagemCompativel() { return personagemCompativel; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}