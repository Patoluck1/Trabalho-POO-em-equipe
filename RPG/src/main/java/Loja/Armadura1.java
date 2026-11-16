package Loja;

public class Armadura1 {
    private String nome;
    private int preco;
    private int bonusDefesa;
    private boolean disponivel;
    private String personagemCompativel;

    public Armadura1() {
        this.nome = "Armadura de Couro Reforçado";
        this.preco = 25;
        this.bonusDefesa = 5;
        this.disponivel = true;
        this.personagemCompativel = "Arqueiro";
    }

    public String getNome() { return nome; }
    public int getpreco() { return preco; }
    public int getPreco() { return preco; }
    public int getBonusDefesa() { return bonusDefesa; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public String getPersonagemCompativel() { return personagemCompativel; }
}