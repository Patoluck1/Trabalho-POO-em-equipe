package Personagens;

public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected int vidaMax;
    protected int ataque;
    protected int defesa;
    protected double chanceDesvio;

    public Personagem(String nome, int vida, int ataque, int defesa, double chanceDesvio) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMax = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.chanceDesvio = chanceDesvio;
    }

    // Polimorfismo: cada classe calcula seu golpe especial/mágico de um jeito
    public abstract int calcularDanoEspecial();

    public void receberDano(int dano) {
        this.vida = Math.max(0, this.vida - dano);
    }

    public void curar(int quantidade) {
        this.vida = Math.min(this.vidaMax, this.vida + quantidade);
    }

    public void aumentarAtaque(int bonus) {
        this.ataque += bonus;
    }

    public void aumentarDefesa(int bonus) {
        this.defesa += bonus;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.min(vidaMax, Math.max(0, vida)); }
    public int getVidaMax() { return vidaMax; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public double getChanceDesvio() { return chanceDesvio; }
}