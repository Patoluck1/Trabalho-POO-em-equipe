package Personagens;

public class Ladino extends Personagem {
    public Ladino() {
        super("Ladino", 100, 15, 10, 0.45);
    }

    @Override
    public int calcularDanoEspecial() {
        // Ataque furtivo (1.6x de dano)
        return (int) (this.ataque * 1.6);
    }
}