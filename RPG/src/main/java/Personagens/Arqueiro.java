package Personagens;

public class Arqueiro extends Personagem {
    public Arqueiro() {
        super("Arqueiro", 90, 10, 8, 0.25);
    }

    @Override
    public int calcularDanoEspecial() {
        // Flecha perfurante (1.4x de dano)
        return (int) (this.ataque * 1.4);
    }
}