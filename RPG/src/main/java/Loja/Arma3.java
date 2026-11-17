package Loja;

public class Arma3 {
    private String nome = "Adagas Envenenadas";
    private int preco = 25;
    private int bonusAtaque = 7;
    private String personagemCompativel = "Ladino";
    private boolean disponivel = true;

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public int getBonusAtaque() { return bonusAtaque; }
    public String getPersonagemCompativel() { return personagemCompativel; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}