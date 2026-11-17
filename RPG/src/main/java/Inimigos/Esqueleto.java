package Inimigos;

public class Esqueleto extends Inimigo {
    public Esqueleto() {
        super("Esqueleto Guerreiro", 70, 15, 8, 0.10);
    }

    @Override
    public String acaoEspecial() {
        if (random.nextDouble() < 0.40) {
            return "queimadura";
        }
        return "nenhum";
    }
}