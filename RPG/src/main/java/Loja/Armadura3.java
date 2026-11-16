package Loja;

public class Armadura3 {
    private String nome;
    private int preco;
    private int bonusDefesa;
    private boolean disponivel;
    private String personagemCompativel;

    public Armadura3() {
        this.nome = "Traje de Sombra Leve";
        this.preco = 20;
        this.bonusDefesa = 4;
        this.disponivel = true;
        this.personagemCompativel = "Ladino";
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public int getBonusDefesa() { return bonusDefesa; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public String getPersonagemCompativel() { return personagemCompativel; }
}