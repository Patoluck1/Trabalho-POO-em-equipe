package Personagens;

public class Ladino {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    private double chanceCritico;

    public Ladino() {
        this.nome = "Ladino";
        this.vidaMaxima = 100;
        this.vida = 100;
        this.ataque = 15;
        this.defesa = 10;
        this.chanceCritico = 0.35; // 35% de chance de crítico
    }

    public void atacar() {
        // Lógica de ataque furtivo / crítico
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, Math.min(vida, vidaMaxima)); }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public double getChanceCritico() { return chanceCritico; }
}