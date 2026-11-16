package Inimigos;

// Chefe secreto "Senhor Batata".
// Só aparece se o jogador tiver a Batata no inventário.
// Chance de 1 em 5 batalhas quando a condição for verdadeira.

public class SenhorBatata extends Inimigo {

    public SenhorBatata() {
        super("Senhor Batata", 500, 50);
    }
}