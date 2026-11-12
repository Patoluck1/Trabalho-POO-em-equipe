package Inimigos;

public class Ciclope {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;

    public Ciclope() {
        this.nome = "Ciclope";
        this.vida = 180;
        this.ataque = 35;
        this.defesa = 10;
    }

    public void acaoEspecial() {
        // Ataque esmagador com dano bruto
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, vida); }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
}