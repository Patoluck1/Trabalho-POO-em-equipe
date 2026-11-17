package Personagens;

public class Mago extends Personagem {
    public Mago() {
        super("Mago", 85, 14, 5, 0.15);
    }

    @Override
    public int calcularDanoEspecial() {
        // Bola de fogo poderosa (2.0x de dano)
        return (int) (this.ataque * 2.0);
    }
}