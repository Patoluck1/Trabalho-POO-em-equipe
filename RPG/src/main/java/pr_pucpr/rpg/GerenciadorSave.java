package pr_pucpr.rpg;

import java.io.*;
import java.util.Properties;

public class GerenciadorSave {
    private static final String ARQUIVO_SAVE = "save_rpg.properties";

    public static void salvarJogo(String classe, int vida, int vidaMax, int ataque, int defesa,
                                  double desvio, int moedas, int c1, int c2, int c3, int c4,
                                  String arma, String armadura) throws IOException {
        Properties props = new Properties();
        props.setProperty("classe", classe);
        props.setProperty("vida", String.valueOf(vida));
        props.setProperty("vidaMax", String.valueOf(vidaMax));
        props.setProperty("ataque", String.valueOf(ataque));
        props.setProperty("defesa", String.valueOf(defesa));
        props.setProperty("desvio", String.valueOf(desvio));
        props.setProperty("moedas", String.valueOf(moedas));
        props.setProperty("c1", String.valueOf(c1));
        props.setProperty("c2", String.valueOf(c2));
        props.setProperty("c3", String.valueOf(c3));
        props.setProperty("c4", String.valueOf(c4));
        props.setProperty("arma", arma);
        props.setProperty("armadura", armadura);

        try (FileOutputStream out = new FileOutputStream(ARQUIVO_SAVE)) {
            props.store(out, "Progresso Salvo - RPG POO");
        }
    }

    public static Properties carregarJogo() throws IOException {
        File arquivo = new File(ARQUIVO_SAVE);
        if (!arquivo.exists()) {
            throw new FileNotFoundException("Nenhum arquivo de save encontrado.");
        }
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(arquivo)) {
            props.load(in);
        }
        return props;
    }
}