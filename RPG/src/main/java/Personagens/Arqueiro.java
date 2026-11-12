package Personagens;

public class Arqueiro {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    private int agilidade;

    public Arqueiro() {
        this.nome = "Arqueiro";
        this.vidaMaxima = 90;
        this.vida = 90;
        this.ataque = 10;
        this.defesa = 8;
        this.agilidade = 15;
    }

    public void atacar() {
        // Lógica de ataque à distância
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, Math.min(vida, vidaMaxima)); }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getAgilidade() { return agilidade; }
}