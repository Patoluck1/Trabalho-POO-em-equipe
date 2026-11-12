package Personagens;

public class Mago {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    private int mana;

    public Mago() {
        this.nome = "Mago";
        this.vidaMaxima = 80;
        this.vida = 80;
        this.ataque = 5;
        this.defesa = 5;
        this.mana = 100;
    }

    public void atacar() {
        // Lógica de ataque ou magia do Mago para o JavaFX
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, Math.min(vida, vidaMaxima)); }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = Math.max(0, Math.min(mana, 100)); }
}