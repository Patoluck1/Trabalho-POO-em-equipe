package Loja;

public class Arma2 {
    private String nome = "Machado de Guerra Duplo";
    private int preco = 45;
    private int bonusAtaque = 12;
    private String personagemCompativel = "Bárbaro";
    private boolean disponivel = true;

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public int getBonusAtaque() { return bonusAtaque; }
    public String getPersonagemCompativel() { return personagemCompativel; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}