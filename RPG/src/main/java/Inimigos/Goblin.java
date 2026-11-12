package Inimigos;

public class Goblin {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;

    public Goblin() {
        this.nome = "Goblin Ligeiro";
        this.vida = 60;
        this.ataque = 14;
        this.defesa = 5;
    }

    public void acaoEspecial() {
        // Ataque rápido ou roubo de item/ouro
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, vida); }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
}