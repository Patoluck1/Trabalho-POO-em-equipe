package Inimigos;

import java.util.Random;

public abstract class Inimigo {
    protected String nome;
    protected int vida;
    protected int ataque;
    protected int defesa;
    protected double chanceDesvio;
    protected Random random = new Random();

    public Inimigo(String nome, int vida, int ataque, int defesa, double chanceDesvio) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.chanceDesvio = chanceDesvio;
    }

    // Polimorfismo: cálculo de dano com variação entre 80% e 120%
    public int calcularDanoAtaque() {
        double fatorVariacao = 0.8 + (random.nextDouble() * 0.4);
        return (int) (this.ataque * fatorVariacao);
    }

    // Método abstrato obrigatório para especialização de cada inimigo
    public abstract String acaoEspecial();

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, vida); }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public double getChanceDesvio() { return chanceDesvio; }
}