package Inimigos;

public class Goblin extends Inimigo {
    public Goblin() {
        super("Goblin Ligeiro", 60, 14, 5, 0.30);
    }

    @Override
    public String acaoEspecial() {
        return "nenhum";
    }
}