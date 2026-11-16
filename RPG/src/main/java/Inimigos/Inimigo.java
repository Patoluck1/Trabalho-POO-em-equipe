package Inimigos;


public abstract class Inimigo {
    private String nome;
    private int vida;
    private int ataque;

    public Inimigo(String nome, int vida, int ataque) {
        this.nome = nome;
        this.vida = Math.max(0, vida);
        this.ataque = Math.max(0, ataque);
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = Math.max(0, vida);
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = Math.max(0, ataque);
    }
}