package Loja;

public class Arma1 {
    private String nome = "Arco Longo Élfico";
    private int preco = 30;
    private int bonusAtaque = 8;
    private String personagemCompativel = "Arqueiro";
    private boolean disponivel = true;

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public int getBonusAtaque() { return bonusAtaque; }
    public String getPersonagemCompativel() { return personagemCompativel; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}