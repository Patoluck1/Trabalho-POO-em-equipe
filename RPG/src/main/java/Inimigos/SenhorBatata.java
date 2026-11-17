package Inimigos;

public class SenhorBatata extends Inimigo {

    public SenhorBatata() {
        // Nome, Vida, Ataque, Defesa, Chance de Desvio
        super("Senhor Batata", 450, 28, 15, 0.05);
    }

    @Override
    public String acaoEspecial() {
        // 30% de chance de aplicar um efeito aleatório no jogador
        if (random.nextDouble() < 0.30) {
            int sorteio = random.nextInt(3);
            if (sorteio == 0) return "queimadura";
            if (sorteio == 1) return "veneno";
            return "lento";
        }
        return "nenhum";
    }
}