package Inimigos;

public class Aranha extends Inimigo {
    private boolean venenosa;

    public Aranha() {
        super("Aranha Gigante", 50, 12, 4, 0.20);
        this.venenosa = true;
    }

    @Override
    public String acaoEspecial() {
        if (venenosa && random.nextDouble() < 0.50) {
            return random.nextBoolean() ? "veneno" : "lento";
        }
        return "nenhum";
    }

    public boolean isVenenosa() { return venenosa; }
}