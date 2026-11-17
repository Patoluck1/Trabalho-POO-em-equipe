package Loja;

public abstract class Armadura implements Equipavel {
    protected String nome;
    protected int preco;
    protected int bonusDefesa;
    protected boolean disponivel;
    protected String personagemCompativel;

    public Armadura(String nome, int preco, int bonusDefesa, String personagemCompativel) {
        this.nome = nome;
        this.preco = preco;
        this.bonusDefesa = bonusDefesa;
        this.disponivel = true;
        this.personagemCompativel = personagemCompativel;
    }

    @Override
    public String getNome() { return nome; }

    @Override
    public int getPreco() { return preco; }

    @Override
    public int getBonus() { return bonusDefesa; }

    public int getBonusDefesa() { return bonusDefesa; }

    @Override
    public boolean isDisponivel() { return disponivel; }

    @Override
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String getPersonagemCompativel() { return personagemCompativel; }
}