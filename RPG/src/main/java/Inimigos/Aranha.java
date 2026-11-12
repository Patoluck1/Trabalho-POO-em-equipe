package Inimigos;

public class Aranha {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private boolean venenosa;

    public Aranha() {
        this.nome = "Aranha Gigante";
        this.vida = 50;
        this.ataque = 12;
        this.defesa = 4;
        this.venenosa = true;
    }

    public void acaoEspecial() {
        // Aplicar efeito de veneno no turno
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, vida); }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public boolean isVenenosa() { return venenosa; }
}