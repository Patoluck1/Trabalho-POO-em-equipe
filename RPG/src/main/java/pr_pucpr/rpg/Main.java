package pr_pucpr.rpg;

import Inimigos.Aranha;
import Inimigos.Ciclope;
import Inimigos.Esqueleto;
import Inimigos.Goblin;
import Inimigos.SenhorBatata;
import Personagens.Arqueiro;
import Personagens.Barbaro;
import Personagens.Ladino;
import Personagens.Mago;
import Loja.cura1;
import Loja.cura2;
import Loja.cura3;
import Loja.cura4;
import Loja.Arma1;
import Loja.Arma2;
import Loja.Arma3;
import Loja.Arma4;
import Loja.Armadura1;
import Loja.Armadura2;
import Loja.Armadura3;
import Loja.Armadura4;
import Loja.batata;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    private Stage primaryStage;
    private Scene sceneSelecao, sceneMenu, sceneExploracao, sceneCombate, sceneLoja;

    // Estilos CSS Reutilizáveis
    private final String ESTILO_BOTAO =
            "-fx-background-color: #2c3e50; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-size: 14px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 10px 20px; " +
                    "-fx-background-radius: 8px;";

    private final String ESTILO_BOTAO_DESTAQUE =
            "-fx-background-color: #e74c3c; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-size: 14px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 10px 20px; " +
                    "-fx-background-radius: 8px;";

    private final String ESTILO_FUNDO =
            "-fx-background-color: #1e1e2f; " +
                    "-fx-alignment: center;";

    // ==========================================
    // ATRIBUTOS DO JOGADOR
    // ==========================================
    private String nomePersonagem;
    private int vidaJogador;
    private int vidaMaxJogador;
    private int ataqueJogador;
    private int defesaJogador;
    private double chanceDesvioJogador;

    private boolean estaQueimando = false;
    private boolean estaEnvenenado = false;
    private boolean estaLento = false;

    // ==========================================
    // ATRIBUTOS DO INIMIGO
    // ==========================================
    private String tipoInimigoStr;
    private String nomeInimigo;
    private int vidaInimigo;
    private int ataqueInimigo;
    private int defesaInimigo;
    private double chanceDesvioInimigo;

    private Label lblStatusCombate;
    private Button btnBater, btnMagia, btnUsarItens, btnFugir, btnIrParaMenu;
    private Random random = new Random();

    // ==========================================
    // LOJA / INVENTÁRIO
    // ==========================================
    private int moedas = 50;
    private cura1 pocao1 = new cura1();
    private cura2 pocao2 = new cura2();
    private cura3 pocao3 = new cura3();
    private cura4 pocao4 = new cura4();

    private Arma1 armaArqueiro = new Arma1();
    private Arma2 armaBarbaro = new Arma2();
    private Arma3 armaLadino = new Arma3();
    private Arma4 armaMago = new Arma4();

    private Armadura1 armaduraArqueiro = new Armadura1();
    private Armadura2 armaduraBarbaro = new Armadura2();
    private Armadura3 armaduraLadino = new Armadura3();
    private Armadura4 armaduraMago = new Armadura4();

    private batata itemBatata = new batata();

    private int qtdCura1 = 0, qtdCura2 = 0, qtdCura3 = 0, qtdCura4 = 0;
    private String armaEquipada = "Nenhuma";
    private String armaduraEquipada = "Nenhuma";

    private Label lblMoedasLoja, lblInventarioLoja, lblMsgLoja;
    private VBox boxItensLoja;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("RPG de Turno - JavaFX");

        criarTelaSelecaoPersonagem();
        criarMenuPrincipal();
        criarTelaExploracao();
        criarTelaCombate();
        criarTelaLoja();

        primaryStage.setScene(sceneSelecao);
        primaryStage.show();
    }

    // ==========================================
    // 0. SELEÇÃO DE PERSONAGEM
    // ==========================================
    private void criarTelaSelecaoPersonagem() {
        VBox layout = new VBox(15);
        layout.setStyle(ESTILO_FUNDO);
        layout.setPadding(new Insets(25));

        Label titulo = new Label("=== ESCOLHA SEU HERÓI ===");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #f1c40f;");

        Button btnLadino = criarBotaoEstilizado("Ladino (Melhor Desvio, Críticos)", ESTILO_BOTAO);
        btnLadino.setOnAction(e -> {
            Ladino l = new Ladino();
            definirHeroi(l.getNome(), l.getVida(), l.getAtaque(), l.getDefesa(), 0.35);
            primaryStage.setScene(sceneMenu);
        });

        Button btnArqueiro = criarBotaoEstilizado("Arqueiro (Bom Desvio, Equilibrado)", ESTILO_BOTAO);
        btnArqueiro.setOnAction(e -> {
            Arqueiro a = new Arqueiro();
            definirHeroi(a.getNome(), a.getVida(), a.getAtaque(), a.getDefesa(), 0.25);
            primaryStage.setScene(sceneMenu);
        });

        Button btnMago = criarBotaoEstilizado("Mago (Desvio Baixo, Magia Forte)", ESTILO_BOTAO);
        btnMago.setOnAction(e -> {
            Mago m = new Mago();
            definirHeroi(m.getNome(), m.getVida(), m.getAtaque(), m.getDefesa(), 0.15);
            primaryStage.setScene(sceneMenu);
        });

        Button btnBarbaro = criarBotaoEstilizado("Bárbaro (Pior Desvio, Muita Vida)", ESTILO_BOTAO);
        btnBarbaro.setOnAction(e -> {
            Barbaro b = new Barbaro();
            definirHeroi(b.getNome(), b.getVida(), b.getAtaque(), b.getDefesa(), 0.05);
            primaryStage.setScene(sceneMenu);
        });

        layout.getChildren().addAll(titulo, btnLadino, btnArqueiro, btnMago, btnBarbaro);
        sceneSelecao = new Scene(layout, 650, 500);
    }

    private void definirHeroi(String nome, int vida, int ataque, int defesa, double desvio) {
        this.nomePersonagem = nome;
        this.vidaMaxJogador = vida;
        this.vidaJogador = vida;
        this.ataqueJogador = ataque;
        this.defesaJogador = defesa;
        this.chanceDesvioJogador = desvio;
    }

    // Reinicia completamente o progresso ao morrer
    private void resetarJogoCompleto() {
        moedas = 50;
        qtdCura1 = 0;
        qtdCura2 = 0;
        qtdCura3 = 0;
        qtdCura4 = 0;
        armaEquipada = "Nenhuma";
        armaduraEquipada = "Nenhuma";

        // Reseta disponibilidade das armas e armaduras na loja
        armaArqueiro.setDisponivel(true);
        armaBarbaro.setDisponivel(true);
        armaLadino.setDisponivel(true);
        armaMago.setDisponivel(true);

        armaduraArqueiro.setDisponivel(true);
        armaduraBarbaro.setDisponivel(true);
        armaduraLadino.setDisponivel(true);
        armaduraMago.setDisponivel(true);

        // Reseta o item batata
        // (Caso a classe batata tenha método para resetar, garante que volta a ficar disponível)
        if (itemBatata.isNoInventario() || !itemBatata.isDisponivel()) {
            itemBatata = new batata();
        }

        resetarStatusEfeitos();
        primaryStage.setScene(sceneSelecao);
    }

    // ==========================================
    // 1. MENU PRINCIPAL
    // ==========================================
    private void criarMenuPrincipal() {
        VBox layout = new VBox(15);
        layout.setStyle(ESTILO_FUNDO);
        layout.setPadding(new Insets(25));

        Label titulo = new Label("=== RPG DE TURNO ===");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #f1c40f;");

        Button btnExplorar = criarBotaoEstilizado("Explorar (Batalhar)", ESTILO_BOTAO_DESTAQUE);
        btnExplorar.setPrefWidth(240);
        btnExplorar.setOnAction(e -> primaryStage.setScene(sceneExploracao));

        Button btnLoja = criarBotaoEstilizado("Loja", ESTILO_BOTAO);
        btnLoja.setPrefWidth(240);
        btnLoja.setOnAction(e -> {
            sortearItensLoja();
            atualizarLoja();
            lblMsgLoja.setText("");
            primaryStage.setScene(sceneLoja);
        });

        layout.getChildren().addAll(titulo, btnExplorar, btnLoja);
        sceneMenu = new Scene(layout, 650, 500);
    }

    // ==========================================
    // 2. TELA DE EXPLORAÇÃO
    // ==========================================
    private void criarTelaExploracao() {
        VBox layout = new VBox(20);
        layout.setStyle(ESTILO_FUNDO);
        layout.setPadding(new Insets(25));

        Label lblInfo = new Label("Você está explorando as terras sombrias...");
        lblInfo.setStyle("-fx-font-size: 16px; -fx-text-fill: #ecf0f1;");

        Button btnProcurar = criarBotaoEstilizado("Procurar Inimigo", ESTILO_BOTAO_DESTAQUE);
        btnProcurar.setPrefWidth(220);
        btnProcurar.setOnAction(e -> {
            sortearInimigo();
            primaryStage.setScene(sceneCombate);
        });

        Button btnVoltar = criarBotaoEstilizado("Voltar ao Menu", ESTILO_BOTAO);
        btnVoltar.setPrefWidth(220);
        btnVoltar.setOnAction(e -> primaryStage.setScene(sceneMenu));

        layout.getChildren().addAll(lblInfo, btnProcurar, btnVoltar);
        sceneExploracao = new Scene(layout, 650, 500);
    }

    // ==========================================
    // 3. MODO COMBATE
    // ==========================================
    private void criarTelaCombate() {
        VBox layout = new VBox(15);
        layout.setStyle(ESTILO_FUNDO);
        layout.setPadding(new Insets(25));

        Label lblTituloCombate = new Label("--- MODO COMBATE ---");
        lblTituloCombate.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #e74c3c;");

        lblStatusCombate = new Label("Um inimigo selvagem apareceu!");
        lblStatusCombate.setStyle("-fx-font-size: 15px; -fx-text-fill: #ecf0f1; -fx-text-alignment: center;");

        btnBater = criarBotaoEstilizado("Bater (Ataque Físico)", ESTILO_BOTAO);
        btnBater.setPrefWidth(220);
        btnBater.setOnAction(e -> executarTurnoCombate("bater"));

        btnMagia = criarBotaoEstilizado("Magia", ESTILO_BOTAO);
        btnMagia.setPrefWidth(220);
        btnMagia.setOnAction(e -> executarTurnoCombate("magia"));

        btnUsarItens = criarBotaoEstilizado("Usar Itens", ESTILO_BOTAO);
        btnUsarItens.setPrefWidth(220);
        btnUsarItens.setOnAction(e -> usarItemCombate());

        btnFugir = criarBotaoEstilizado("Fugir", ESTILO_BOTAO);
        btnFugir.setPrefWidth(220);
        btnFugir.setOnAction(e -> {
            primaryStage.setScene(sceneMenu);
        });

        btnIrParaMenu = criarBotaoEstilizado("Retornar ao Menu Principal", ESTILO_BOTAO_DESTAQUE);
        btnIrParaMenu.setPrefWidth(260);
        btnIrParaMenu.setVisible(false);
        btnIrParaMenu.setOnAction(e -> {
            primaryStage.setScene(sceneMenu);
        });

        layout.getChildren().addAll(lblTituloCombate, lblStatusCombate, btnBater, btnMagia, btnUsarItens, btnFugir, btnIrParaMenu);
        sceneCombate = new Scene(layout, 650, 520);
    }

    private void sortearInimigo() {
        btnBater.setVisible(true);
        btnMagia.setVisible(true);
        btnUsarItens.setVisible(true);
        btnFugir.setVisible(true);
        btnIrParaMenu.setVisible(false);

        if (itemBatata.isNoInventario() && random.nextInt(5) == 0) {
            SenhorBatata chefe = new SenhorBatata();
            tipoInimigoStr = "senhorbatata";
            nomeInimigo = chefe.getNome();
            vidaInimigo = chefe.getVida();
            ataqueInimigo = chefe.getAtaque();
            defesaInimigo = 15;
            chanceDesvioInimigo = 0.05;
            lblStatusCombate.setText("🥔 Chefe Secreto Encontrado: " + nomeInimigo + "!\nHP Herói: " + vidaJogador + " | HP Chefe: " + vidaInimigo);
            return;
        }

        int sorteio = random.nextInt(4);

        switch (sorteio) {
            case 0:
                Aranha a = new Aranha();
                tipoInimigoStr = "aranha";
                nomeInimigo = a.getNome();
                vidaInimigo = a.getVida();
                ataqueInimigo = a.getAtaque();
                defesaInimigo = a.getDefesa();
                chanceDesvioInimigo = 0.30;
                break;
            case 1:
                Goblin g = new Goblin();
                tipoInimigoStr = "goblin";
                nomeInimigo = g.getNome();
                vidaInimigo = g.getVida();
                ataqueInimigo = g.getAtaque();
                defesaInimigo = g.getDefesa();
                chanceDesvioInimigo = 0.20;
                break;
            case 2:
                Esqueleto esq = new Esqueleto();
                tipoInimigoStr = "esqueleto";
                nomeInimigo = esq.getNome();
                vidaInimigo = esq.getVida();
                ataqueInimigo = esq.getAtaque();
                defesaInimigo = esq.getDefesa();
                chanceDesvioInimigo = 0.10;
                break;
            case 3:
                Ciclope c = new Ciclope();
                tipoInimigoStr = "ciclope";
                nomeInimigo = c.getNome();
                vidaInimigo = c.getVida();
                ataqueInimigo = 20;
                defesaInimigo = c.getDefesa();
                chanceDesvioInimigo = 0.02;
                break;
        }

        String avisoStatus = "";
        if (estaQueimando) avisoStatus += " 🔥 (Queimando!)";
        if (estaEnvenenado) avisoStatus += " 🧪 (Envenenado!)";
        if (estaLento) avisoStatus += " ❄️ (Lento!)";

        lblStatusCombate.setText("Um(a) " + nomeInimigo + " apareceu!" + avisoStatus + "\nHP Herói: " + vidaJogador + " | HP Inimigo: " + vidaInimigo);
    }

    private void resetarStatusEfeitos() {
        estaQueimando = false;
        estaEnvenenado = false;
        estaLento = false;
    }

    private void finalizarCombate(String mensagemFinal, boolean morreu) {
        lblStatusCombate.setText(mensagemFinal);
        btnBater.setVisible(false);
        btnMagia.setVisible(false);
        btnUsarItens.setVisible(false);
        btnFugir.setVisible(false);
        btnIrParaMenu.setVisible(true);

        if (morreu) {
            // Se morreu, muda a ação do botão de retornar para resetar o jogo inteiro e voltar à seleção de herói
            btnIrParaMenu.setText("Voltar à Seleção de Herói");
            btnIrParaMenu.setOnAction(e -> resetarJogoCompleto());
        } else {
            btnIrParaMenu.setText("Retornar ao Menu Principal");
            btnIrParaMenu.setOnAction(e -> primaryStage.setScene(sceneMenu));
        }
    }

    private void executarTurnoCombate(String tipoAcao) {
        if (vidaInimigo <= 0 || vidaJogador <= 0) return;

        StringBuilder relatorio = new StringBuilder();

        if (random.nextDouble() < chanceDesvioInimigo) {
            relatorio.append("O(a) ").append(nomeInimigo).append(" desviou do seu ataque!\n");
        } else {
            int danoCausado = 0;
            if (tipoAcao.equals("bater")) {
                danoCausado = Math.max(1, ataqueJogador - defesaInimigo);
            } else if (tipoAcao.equals("magia")) {
                danoCausado = Math.max(1, (int)(ataqueJogador * 1.5) - defesaInimigo);
            }
            vidaInimigo -= danoCausado;
            relatorio.append("Você causou ").append(danoCausado).append(" de dano.\n");
        }

        if (vidaInimigo <= 0) {
            int ganho = 20 + random.nextInt(21);
            moedas += ganho;
            finalizarCombate("🏆 Vitória! Você derrotou o(a) " + nomeInimigo + "!\nGanhou " + ganho + " moedas.\nClique abaixo para retornar ao Menu.", false);
            return;
        }

        if (estaQueimando) {
            vidaJogador -= 15;
            relatorio.append("🔥 O fogo queimou você em 15 de dano!\n");
        }
        if (estaEnvenenado) {
            int danoVeneno = 5 + random.nextInt(6);
            vidaJogador -= danoVeneno;
            relatorio.append("🧪 O veneno causou ").append(danoVeneno).append(" de dano.\n");
        }

        if (vidaJogador <= 0) {
            finalizarCombate("💀 Game Over! Você foi derrotado...\nClique abaixo para reiniciar o jogo.", true);
            return;
        }

        double desvioEfetivo = estaLento ? (chanceDesvioInimigo / 2) : chanceDesvioInimigo;

        if (random.nextDouble() < desvioEfetivo) {
            relatorio.append("Você desviou do ataque do(a) ").append(nomeInimigo).append("!\n");
        } else {
            double fatorVariacao = 0.8 + (random.nextDouble() * 0.4);
            int ataqueComVariacao = (int) (ataqueInimigo * fatorVariacao);

            int danoRecebido = Math.max(1, ataqueComVariacao - defesaJogador);
            vidaJogador -= danoRecebido;
            relatorio.append("O(a) ").append(nomeInimigo).append(" te atingiu causando ").append(danoRecebido).append(" de dano.\n");

            if (tipoInimigoStr.equals("esqueleto")) {
                if (random.nextDouble() < 0.40) {
                    estaQueimando = true;
                    relatorio.append("🔥 Você foi incendiado! (-15 HP/turno)\n");
                }
            } else if (tipoInimigoStr.equals("aranha")) {
                if (random.nextDouble() < 0.50) {
                    if (random.nextBoolean()) {
                        estaEnvenenado = true;
                        relatorio.append("🧪 Você foi envenenado!\n");
                    } else {
                        estaLento = true;
                        relatorio.append("❄️ Você ficou Lento (Desvio reduzido)!\n");
                    }
                }
            }
        }

        if (vidaJogador <= 0) {
            finalizarCombate("💀 Game Over! Você foi derrotado...\nClique abaixo para reiniciar o jogo.", true);
            return;
        }

        lblStatusCombate.setText(relatorio.toString() + String.format("\nHP %s: %d | HP %s: %d",
                nomePersonagem, vidaJogador, nomeInimigo, vidaInimigo));
    }

    // ==========================================
    // 4. TELA DA LOJA
    // ==========================================
    private void criarTelaLoja() {
        VBox layout = new VBox(12);
        layout.setStyle(ESTILO_FUNDO);
        layout.setPadding(new Insets(20));

        Label titulo = new Label("=== LOJA DE ITENS ===");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #f1c40f;");

        lblMoedasLoja = new Label();
        lblMoedasLoja.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #2ecc71;");

        boxItensLoja = new VBox(10);
        boxItensLoja.setAlignment(Pos.CENTER);

        lblMsgLoja = new Label();
        lblMsgLoja.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");

        lblInventarioLoja = new Label();
        lblInventarioLoja.setStyle("-fx-text-fill: #bdc3c7; -fx-font-size: 13px;");

        Button btnVoltar = criarBotaoEstilizado("Voltar ao Menu Principal", ESTILO_BOTAO);
        btnVoltar.setPrefWidth(220);
        btnVoltar.setOnAction(e -> primaryStage.setScene(sceneMenu));

        layout.getChildren().addAll(titulo, lblMoedasLoja, boxItensLoja, lblMsgLoja, lblInventarioLoja, btnVoltar);
        sceneLoja = new Scene(layout, 750, 620);
    }

    private void sortearItensLoja() {
        boxItensLoja.getChildren().clear();

        List<String> pool = new ArrayList<>();
        pool.add("cura1");
        pool.add("cura2");
        pool.add("cura3");
        pool.add("cura4");

        if (armaArqueiro.isDisponivel()) pool.add("arma1");
        if (armaBarbaro.isDisponivel()) pool.add("arma2");
        if (armaLadino.isDisponivel()) pool.add("arma3");
        if (armaMago.isDisponivel()) pool.add("arma4");

        if (armaduraArqueiro.isDisponivel()) pool.add("armadura1");
        if (armaduraBarbaro.isDisponivel()) pool.add("armadura2");
        if (armaduraLadino.isDisponivel()) pool.add("armadura3");
        if (armaduraMago.isDisponivel()) pool.add("armadura4");

        if (itemBatata.isDisponivel()) pool.add("batata");

        List<String> usadosUnicos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (pool.isEmpty()) break;
            String escolhido;
            while (true) {
                escolhido = pool.get(random.nextInt(pool.size()));
                if (escolhido.startsWith("cura") || !usadosUnicos.contains(escolhido)) {
                    if (!escolhido.startsWith("cura")) {
                        usadosUnicos.add(escolhido);
                    }
                    break;
                }
            }
            boxItensLoja.getChildren().add(criarBotaoVitrine(escolhido));
        }
    }

    private Button criarBotaoVitrine(String codigo) {
        Button btn = new Button();
        btn.setStyle(ESTILO_BOTAO);
        btn.setPrefWidth(580);
        btn.setWrapText(true);

        switch (codigo) {
            case "cura1":
                btn.setText(pocao1.getNome() + " - Preço: " + pocao1.getPreco() + " moedas | " + pocao1.getDescricao());
                btn.setOnAction(e -> comprarPocao(1, pocao1.getPreco(), pocao1.getNome()));
                break;
            case "cura2":
                btn.setText(pocao2.getNome() + " - Preço: " + pocao2.getPreco() + " moedas | " + pocao2.getDescricao());
                btn.setOnAction(e -> comprarPocao(2, pocao2.getPreco(), pocao2.getNome()));
                break;
            case "cura3":
                btn.setText(pocao3.getNome() + " - Preço: " + pocao3.getPreco() + " moedas | " + pocao3.getDescricao());
                btn.setOnAction(e -> comprarPocao(3, pocao3.getPreco(), pocao3.getNome()));
                break;
            case "cura4":
                btn.setText(pocao4.getNome() + " - Preço: " + pocao4.getPreco() + " moedas | " + pocao4.getDescricao());
                btn.setOnAction(e -> comprarPocao(4, pocao4.getPreco(), pocao4.getNome()));
                break;
            case "arma1":
                btn.setText(armaArqueiro.getNome() + " (" + armaArqueiro.getPersonagemCompativel() + ") - Preço: " + armaArqueiro.getPreco() + " moedas | +" + armaArqueiro.getBonusAtaque() + " Ataque");
                btn.setOnAction(e -> comprarArma(1, btn));
                break;
            case "arma2":
                btn.setText(armaBarbaro.getNome() + " (" + armaBarbaro.getPersonagemCompativel() + ") - Preço: " + armaBarbaro.getPreco() + " moedas | +" + armaBarbaro.getBonusAtaque() + " Ataque");
                btn.setOnAction(e -> comprarArma(2, btn));
                break;
            case "arma3":
                btn.setText(armaLadino.getNome() + " (" + armaLadino.getPersonagemCompativel() + ") - Preço: " + armaLadino.getPreco() + " moedas | +" + armaLadino.getBonusAtaque() + " Ataque");
                btn.setOnAction(e -> comprarArma(3, btn));
                break;
            case "arma4":
                btn.setText(armaMago.getNome() + " (" + armaMago.getPersonagemCompativel() + ") - Preço: " + armaMago.getPreco() + " moedas | +" + armaMago.getBonusAtaque() + " Ataque");
                btn.setOnAction(e -> comprarArma(4, btn));
                break;
            case "armadura1":
                btn.setText(armaduraArqueiro.getNome() + " (" + armaduraArqueiro.getPersonagemCompativel() + ") - Preço: " + armaduraArqueiro.getPreco() + " moedas | +" + armaduraArqueiro.getBonusDefesa() + " Defesa");
                btn.setOnAction(e -> comprarArmadura(1, btn));
                break;
            case "armadura2":
                btn.setText(armaduraBarbaro.getNome() + " (" + armaduraBarbaro.getPersonagemCompativel() + ") - Preço: " + armaduraBarbaro.getPreco() + " moedas | +" + armaduraBarbaro.getBonusDefesa() + " Defesa");
                btn.setOnAction(e -> comprarArmadura(2, btn));
                break;
            case "armadura3":
                btn.setText(armaduraLadino.getNome() + " (" + armaduraLadino.getPersonagemCompativel() + ") - Preço: " + armaduraLadino.getPreco() + " moedas | +" + armaduraLadino.getBonusDefesa() + " Defesa");
                btn.setOnAction(e -> comprarArmadura(3, btn));
                break;
            case "armadura4":
                btn.setText(armaduraMago.getNome() + " (" + armaduraMago.getPersonagemCompativel() + ") - Preço: " + armaduraMago.getPreco() + " moedas | +" + armaduraMago.getBonusDefesa() + " Defesa");
                btn.setOnAction(e -> comprarArmadura(4, btn));
                break;
            case "batata":
                btn.setText(itemBatata.getNome() + " - Preço: " + itemBatata.getPreco() + " moedas | Cura 1 de vida");
                btn.setOnAction(e -> comprarBatata(btn));
                break;
        }
        return btn;
    }

    private Button criarBotaoEstilizado(String texto, String estiloBase) {
        Button btn = new Button(texto);
        btn.setStyle(estiloBase);
        btn.setOnMouseEntered(e -> btn.setStyle(estiloBase.replace("-fx-background-color: #2c3e50;", "-fx-background-color: #34495e;").replace("-fx-background-color: #e74c3c;", "-fx-background-color: #c0392b;")));
        btn.setOnMouseExited(e -> btn.setStyle(estiloBase));
        return btn;
    }

    private void comprarPocao(int tipo, int preco, String nome) {
        if (moedas < preco) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText("Moedas insuficientes para comprar " + nome + "!");
            return;
        }
        moedas -= preco;
        switch (tipo) {
            case 1: qtdCura1++; break;
            case 2: qtdCura2++; break;
            case 3: qtdCura3++; break;
            case 4: qtdCura4++; break;
        }
        lblMsgLoja.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
        lblMsgLoja.setText("Compra realizada: " + nome + "!");
        atualizarLoja();
    }

    private void comprarArma(int tipo, Button btnOrigem) {
        String nomeArma = "", compativel = "";
        int preco = 0, bonus = 0;
        boolean disponivel = false;

        switch (tipo) {
            case 1: nomeArma = armaArqueiro.getNome(); compativel = armaArqueiro.getPersonagemCompativel(); preco = armaArqueiro.getPreco(); bonus = armaArqueiro.getBonusAtaque(); disponivel = armaArqueiro.isDisponivel(); break;
            case 2: nomeArma = armaBarbaro.getNome(); compativel = armaBarbaro.getPersonagemCompativel(); preco = armaBarbaro.getPreco(); bonus = armaBarbaro.getBonusAtaque(); disponivel = armaBarbaro.isDisponivel(); break;
            case 3: nomeArma = armaLadino.getNome(); compativel = armaLadino.getPersonagemCompativel(); preco = armaLadino.getPreco(); bonus = armaLadino.getBonusAtaque(); disponivel = armaLadino.isDisponivel(); break;
            case 4: nomeArma = armaMago.getNome(); compativel = armaMago.getPersonagemCompativel(); preco = armaMago.getPreco(); bonus = armaMago.getBonusAtaque(); disponivel = armaMago.isDisponivel(); break;
        }

        if (!disponivel) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText("Esta arma já foi comprada!");
            return;
        }
        if (!nomePersonagem.equalsIgnoreCase(compativel)) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText("Apenas " + compativel + " pode equipar " + nomeArma + "!");
            return;
        }
        if (moedas < preco) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText("Moedas insuficientes para comprar " + nomeArma + "!");
            return;
        }

        moedas -= preco;
        ataqueJogador += bonus;
        armaEquipada = nomeArma + " (+" + bonus + " ATQ)";
        btnOrigem.setDisable(true);

        switch (tipo) {
            case 1: armaArqueiro.setDisponivel(false); break;
            case 2: armaBarbaro.setDisponivel(false); break;
            case 3: armaLadino.setDisponivel(false); break;
            case 4: armaMago.setDisponivel(false); break;
        }

        lblMsgLoja.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
        lblMsgLoja.setText("Você equipou " + nomeArma + "! Ataque atual: " + ataqueJogador);
        atualizarLoja();
    }

    private void comprarArmadura(int tipo, Button btnOrigem) {
        String nomeArmadura = "", compativel = "";
        int preco = 0, bonus = 0;
        boolean disponivel = false;

        switch (tipo) {
            case 1: nomeArmadura = armaduraArqueiro.getNome(); compativel = armaduraArqueiro.getPersonagemCompativel(); preco = armaduraArqueiro.getPreco(); bonus = armaduraArqueiro.getBonusDefesa(); disponivel = armaduraArqueiro.isDisponivel(); break;
            case 2: nomeArmadura = armaduraBarbaro.getNome(); compativel = armaduraBarbaro.getPersonagemCompativel(); preco = armaduraBarbaro.getPreco(); bonus = armaduraBarbaro.getBonusDefesa(); disponivel = armaduraBarbaro.isDisponivel(); break;
            case 3: nomeArmadura = armaduraLadino.getNome(); compativel = armaduraLadino.getPersonagemCompativel(); preco = armaduraLadino.getPreco(); bonus = armaduraLadino.getBonusDefesa(); disponivel = armaduraLadino.isDisponivel(); break;
            case 4: nomeArmadura = armaduraMago.getNome(); compativel = armaduraMago.getPersonagemCompativel(); preco = armaduraMago.getPreco(); bonus = armaduraMago.getBonusDefesa(); disponivel = armaduraMago.isDisponivel(); break;
        }

        if (!disponivel) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText("Esta armadura já foi comprada!");
            return;
        }
        if (!nomePersonagem.equalsIgnoreCase(compativel)) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText("Apenas " + compativel + " pode equipar " + nomeArmadura + "!");
            return;
        }
        if (moedas < preco) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText("Moedas insuficientes para comprar " + nomeArmadura + "!");
            return;
        }

        moedas -= preco;
        defesaJogador += bonus;
        armaduraEquipada = nomeArmadura + " (+" + bonus + " DEF)";
        btnOrigem.setDisable(true);

        switch (tipo) {
            case 1: armaduraArqueiro.setDisponivel(false); break;
            case 2: armaduraBarbaro.setDisponivel(false); break;
            case 3: armaduraLadino.setDisponivel(false); break;
            case 4: armaduraMago.setDisponivel(false); break;
        }

        lblMsgLoja.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
        lblMsgLoja.setText("Você equipou " + nomeArmadura + "! Defesa atual: " + defesaJogador);
        atualizarLoja();
    }

    private void comprarBatata(Button btnOrigem) {
        if (!itemBatata.isDisponivel()) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText("Este item não está mais disponível!");
            return;
        }
        if (moedas < itemBatata.getPreco()) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText("Moedas insuficientes!");
            return;
        }
        if (itemBatata.comprar()) {
            moedas -= itemBatata.getPreco();
            btnOrigem.setDisable(true);
            lblMsgLoja.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
            lblMsgLoja.setText("Você comprou: " + itemBatata.getNome() + "!");
            atualizarLoja();
        }
    }

    private void atualizarLoja() {
        lblMoedasLoja.setText("Suas Moedas: " + moedas + " | Herói: " + nomePersonagem + " (ATQ: " + ataqueJogador + " | DEF: " + defesaJogador + ")");
        String statusBatataStr = itemBatata.isNoInventario() ? "1 (No Inventário)" : "0";
        lblInventarioLoja.setText(String.format(
                "=== SEU INVENTÁRIO ===\n• %s: %d  |  • %s: %d\n• %s: %d  |  • %s: %d\n• Arma: %s\n• Armadura: %s\n• Batata: %s",
                pocao1.getNome(), qtdCura1, pocao2.getNome(), qtdCura2,
                pocao3.getNome(), qtdCura3, pocao4.getNome(), qtdCura4,
                armaEquipada, armaduraEquipada, statusBatataStr));
    }

    // ==========================================
    // USAR ITENS NO COMBATE
    // ==========================================
    private void usarItemCombate() {
        List<String> opcoes = new ArrayList<>();
        List<Integer> tipos = new ArrayList<>();

        if (qtdCura1 > 0) { opcoes.add(pocao1.getNome() + " (x" + qtdCura1 + ")"); tipos.add(1); }
        if (qtdCura2 > 0) { opcoes.add(pocao2.getNome() + " (x" + qtdCura2 + ")"); tipos.add(2); }
        if (qtdCura3 > 0) { opcoes.add(pocao3.getNome() + " (x" + qtdCura3 + ")"); tipos.add(3); }
        if (qtdCura4 > 0) { opcoes.add(pocao4.getNome() + " (x" + qtdCura4 + ")"); tipos.add(4); }
        if (itemBatata.isNoInventario()) { opcoes.add("Usar " + itemBatata.getNome() + " (Cura 1 HP)"); tipos.add(5); }

        if (opcoes.isEmpty()) {
            lblStatusCombate.setText("Você não possui itens na bolsa! Visite a Loja.");
            return;
        }

        ChoiceDialog<String> dialogo = new ChoiceDialog<>(opcoes.get(0), opcoes);
        dialogo.setTitle("Bolsa de Itens");
        dialogo.setHeaderText("Selecione o item que deseja usar:");
        dialogo.setContentText("Item:");
        dialogo.showAndWait().ifPresent(escolha -> aplicarItem(tipos.get(opcoes.indexOf(escolha))));
    }

    private void aplicarItem(int tipo) {
        String nomeItem = "";
        boolean limpaStatus = false;

        if (tipo >= 1 && tipo <= 4) {
            switch (tipo) {
                case 1:
                    vidaJogador = pocao1.curar(vidaJogador, vidaMaxJogador);
                    qtdCura1--; nomeItem = pocao1.getNome(); limpaStatus = pocao1.isRemoveStatus(); break;
                case 2:
                    vidaJogador = pocao2.curar(vidaJogador, vidaMaxJogador);
                    qtdCura2--; nomeItem = pocao2.getNome(); limpaStatus = pocao2.isRemoveStatus(); break;
                case 3:
                    vidaJogador = pocao3.curar(vidaJogador, vidaMaxJogador);
                    qtdCura3--; nomeItem = pocao3.getNome(); limpaStatus = pocao3.isRemoveStatus(); break;
                case 4:
                    vidaJogador = pocao4.curar(vidaJogador, vidaMaxJogador);
                    qtdCura4--; nomeItem = pocao4.getNome(); limpaStatus = pocao4.isRemoveStatus(); break;
            }
            if (limpaStatus) {
                estaQueimando = false;
                estaEnvenenado = false;
                estaLento = false;
            }
        } else if (tipo == 5) {
            int curaBatata = itemBatata.usar();
            vidaJogador = Math.min(vidaMaxJogador, vidaJogador + curaBatata);
            nomeItem = itemBatata.getNome();
        }

        lblStatusCombate.setText("Você usou " + nomeItem + "!" + (limpaStatus ? " Status limpos." : "") +
                "\nHP " + nomePersonagem + ": " + vidaJogador + " | HP " + nomeInimigo + ": " + vidaInimigo);
    }

    public static void main(String[] args) {
        launch(args);
    }
}