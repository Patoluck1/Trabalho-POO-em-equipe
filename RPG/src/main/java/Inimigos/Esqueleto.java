package Inimigos;

public class Esqueleto {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;

    public Esqueleto() {
        this.nome = "Esqueleto Guerreiro";
        this.vida = 70;
        this.ataque = 15;
        this.defesa = 8;
    }

    public void acaoEspecial() {
        // Chance de autorreparação ou bloqueio extra
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, vida); }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
}