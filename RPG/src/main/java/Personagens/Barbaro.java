package Personagens;

public class Barbaro extends Personagem {
    public Barbaro() {
        super("Bárbaro", 150, 20, 10, 0.05);
    }

    @Override
    public int calcularDanoEspecial() {
        // Fúria selvagem (1.3x de dano)
        return (int) (this.ataque * 1.3);
    }
}