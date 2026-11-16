package Loja;

public class Armadura4 {
    private String nome;
    private int preco;
    private int bonusDefesa;
    private boolean disponivel;
    private String personagemCompativel;

    public Armadura4() {
        this.nome = "Manto Místico Encantado";
        this.preco = 25;
        this.bonusDefesa = 3;
        this.disponivel = true;
        this.personagemCompativel = "Mago";
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public int getBonusDefesa() { return bonusDefesa; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public String getPersonagemCompativel() { return personagemCompativel; }
}