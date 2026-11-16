package Loja;

public class Armadura2 {
    private String nome;
    private int preco;
    private int bonusDefesa;
    private boolean disponivel;
    private String personagemCompativel;

    public Armadura2() {
        this.nome = "Armadura de Placas Pesadas";
        this.preco = 40;
        this.bonusDefesa = 10;
        this.disponivel = true;
        this.personagemCompativel = "Bárbaro";
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public int getBonusDefesa() { return bonusDefesa; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public String getPersonagemCompativel() { return personagemCompativel; }
}