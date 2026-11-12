package Personagens;

public class Barbaro {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    private int fureor; // Exemplo de atributo único

    public Barbaro() {
        this.nome = "Bárbaro";
        this.vidaMaxima = 150;
        this.vida = 150;
        this.ataque = 20;
        this.defesa = 15;
    }

    public void atacar() {
        // Lógica de ataque corpo a corpo pesado
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, Math.min(vida, vidaMaxima)); }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
}