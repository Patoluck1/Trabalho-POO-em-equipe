package Inimigos;

public class Ciclope extends Inimigo {
    public Ciclope() {
        super("Ciclope", 180, 20, 10, 0);
    }

    @Override
    public String acaoEspecial() {
        return "nenhum";
    }
}