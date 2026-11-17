package Loja;

public interface Equipavel {
    String getNome();
    int getPreco();
    int getBonus();
    String getPersonagemCompativel();
    boolean isDisponivel();
    void setDisponivel(boolean disponivel);
}