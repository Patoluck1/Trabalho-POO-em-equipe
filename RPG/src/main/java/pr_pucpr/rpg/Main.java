package pr_pucpr.rpg;

import Inimigos.*;
import Personagens.*;
import Loja.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Main extends Application {

    private Stage primaryStage;
    private Scene sceneSelecao, sceneMenu, sceneExploracao, sceneCombate, sceneLoja;

    private final String ESTILO_BOTAO =
            "-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px; " +
                    "-fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 8px;";

    private final String ESTILO_BOTAO_DESTAQUE =
            "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px; " +
                    "-fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 8px;";

    private final String ESTILO_FUNDO =
            "-fx-background-color: #1e1e2f; -fx-alignment: center;";

    // ==========================================
    // POLIMORFISMO: OBJETO DO HERÓI ATUAL
    // ==========================================
    private Personagem heroiAtual;

    private boolean estaQueimando = false;
    private boolean estaEnvenenado = false;
    private boolean estaLento = false;

    // Polimorfismo & Coleção de Objetos
    private Inimigo inimigoAtual;
    private SenhorBatata chefeSecretoAtual;
    private boolean enfrentandoChefe = false;
    private List<Equipavel> equipamentosAdquiridos = new ArrayList<>();

    private Label lblStatusCombate, lblStatusMenu;
    private Button btnBater, btnMagia, btnUsarItens, btnFugir, btnIrParaMenu;
    private Random random = new Random();

    // Loja / Inventário (classes dos colegas intactas)
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
        primaryStage.setTitle("RPG de Turno - JavaFX (POO)");

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

        Label lblAvisoLoad = new Label();
        lblAvisoLoad.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");

        Button btnLadino = criarBotaoEstilizado("Ladino (Melhor Desvio, Ataque Furtivo)", ESTILO_BOTAO);
        btnLadino.setOnAction(e -> {
            heroiAtual = new Ladino();
            primaryStage.setScene(sceneMenu);
        });

        Button btnArqueiro = criarBotaoEstilizado("Arqueiro (Bom Desvio, Flecha Perfurante)", ESTILO_BOTAO);
        btnArqueiro.setOnAction(e -> {
            heroiAtual = new Arqueiro();
            primaryStage.setScene(sceneMenu);
        });

        Button btnMago = criarBotaoEstilizado("Mago (Desvio Baixo, Bola de Fogo)", ESTILO_BOTAO);
        btnMago.setOnAction(e -> {
            heroiAtual = new Mago();
            primaryStage.setScene(sceneMenu);
        });

        Button btnBarbaro = criarBotaoEstilizado("Bárbaro (Pior Desvio, Fúria Selvagem)", ESTILO_BOTAO);
        btnBarbaro.setOnAction(e -> {
            heroiAtual = new Barbaro();
            primaryStage.setScene(sceneMenu);
        });

        Button btnCarregar = criarBotaoEstilizado("Carregar Jogo Salvo", ESTILO_BOTAO_DESTAQUE);
        btnCarregar.setOnAction(e -> {
            try {
                Properties p = GerenciadorSave.carregarJogo();
                String classeSalva = p.getProperty("classe");

                if (classeSalva.equalsIgnoreCase("Ladino")) heroiAtual = new Ladino();
                else if (classeSalva.equalsIgnoreCase("Arqueiro")) heroiAtual = new Arqueiro();
                else if (classeSalva.equalsIgnoreCase("Mago")) heroiAtual = new Mago();
                else heroiAtual = new Barbaro();

                // Restaura os bônus de ataque e defesa acumulados
                int atqSalvo = Integer.parseInt(p.getProperty("ataque"));
                int defSalva = Integer.parseInt(p.getProperty("defesa"));
                if (atqSalvo > heroiAtual.getAtaque()) {
                    heroiAtual.aumentarAtaque(atqSalvo - heroiAtual.getAtaque());
                }
                if (defSalva > heroiAtual.getDefesa()) {
                    heroiAtual.aumentarDefesa(defSalva - heroiAtual.getDefesa());
                }

                heroiAtual.setVida(Integer.parseInt(p.getProperty("vida")));
                this.moedas = Integer.parseInt(p.getProperty("moedas"));
                this.qtdCura1 = Integer.parseInt(p.getProperty("c1"));
                this.qtdCura2 = Integer.parseInt(p.getProperty("c2"));
                this.qtdCura3 = Integer.parseInt(p.getProperty("c3"));
                this.qtdCura4 = Integer.parseInt(p.getProperty("c4"));
                this.armaEquipada = p.getProperty("arma");
                this.armaduraEquipada = p.getProperty("armadura");

                lblStatusMenu.setText("Progresso carregado com sucesso!");
                primaryStage.setScene(sceneMenu);
            } catch (IOException | NumberFormatException ex) {
                lblAvisoLoad.setText("Erro ao carregar: " + ex.getMessage());
            }
        });

        layout.getChildren().addAll(titulo, btnLadino, btnArqueiro, btnMago, btnBarbaro, btnCarregar, lblAvisoLoad);
        sceneSelecao = new Scene(layout, 650, 540);
    }

    private void resetarJogoCompleto() {
        heroiAtual = null;
        moedas = 50;
        qtdCura1 = 0; qtdCura2 = 0; qtdCura3 = 0; qtdCura4 = 0;
        armaEquipada = "Nenhuma";
        armaduraEquipada = "Nenhuma";
        equipamentosAdquiridos.clear();

        armaArqueiro.setDisponivel(true);
        armaBarbaro.setDisponivel(true);
        armaLadino.setDisponivel(true);
        armaMago.setDisponivel(true);

        armaduraArqueiro.setDisponivel(true);
        armaduraBarbaro.setDisponivel(true);
        armaduraLadino.setDisponivel(true);
        armaduraMago.setDisponivel(true);

        if (itemBatata.isNoInventario() || !itemBatata.isDisponivel()) {
            itemBatata = new batata();
        }

        estaQueimando = false;
        estaEnvenenado = false;
        estaLento = false;
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

        lblStatusMenu = new Label();
        lblStatusMenu.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");

        Button btnExplorar = criarBotaoEstilizado("Explorar (Batalhar)", ESTILO_BOTAO_DESTAQUE);
        btnExplorar.setPrefWidth(240);
        btnExplorar.setOnAction(e -> {
            lblStatusMenu.setText("");
            primaryStage.setScene(sceneExploracao);
        });

        Button btnLoja = criarBotaoEstilizado("Loja", ESTILO_BOTAO);
        btnLoja.setPrefWidth(240);
        btnLoja.setOnAction(e -> {
            lblStatusMenu.setText("");
            sortearItensLoja();
            atualizarLoja();
            lblMsgLoja.setText("");
            primaryStage.setScene(sceneLoja);
        });

        Button btnSalvar = criarBotaoEstilizado("Salvar Progresso", ESTILO_BOTAO);
        btnSalvar.setPrefWidth(240);
        btnSalvar.setOnAction(e -> {
            try {
                GerenciadorSave.salvarJogo(
                        heroiAtual.getNome(), heroiAtual.getVida(), heroiAtual.getVidaMax(),
                        heroiAtual.getAtaque(), heroiAtual.getDefesa(), heroiAtual.getChanceDesvio(),
                        moedas, qtdCura1, qtdCura2, qtdCura3, qtdCura4,
                        armaEquipada, armaduraEquipada
                );
                lblStatusMenu.setText("Jogo salvo em 'save_rpg.properties'!");
            } catch (IOException ex) {
                lblStatusMenu.setText("Falha ao salvar: " + ex.getMessage());
            }
        });

        layout.getChildren().addAll(titulo, btnExplorar, btnLoja, btnSalvar, lblStatusMenu);
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
    // 3. MODO COMBATE (Polimorfismo Herói x Inimigo)
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

        btnMagia = criarBotaoEstilizado("Habilidade Especial", ESTILO_BOTAO);
        btnMagia.setPrefWidth(220);
        btnMagia.setOnAction(e -> executarTurnoCombate("especial"));

        btnUsarItens = criarBotaoEstilizado("Usar Itens", ESTILO_BOTAO);
        btnUsarItens.setPrefWidth(220);
        btnUsarItens.setOnAction(e -> usarItemCombate());

        btnFugir = criarBotaoEstilizado("Fugir", ESTILO_BOTAO);
        btnFugir.setPrefWidth(220);
        btnFugir.setOnAction(e -> primaryStage.setScene(sceneMenu));

        btnIrParaMenu = criarBotaoEstilizado("Retornar ao Menu Principal", ESTILO_BOTAO_DESTAQUE);
        btnIrParaMenu.setPrefWidth(260);
        btnIrParaMenu.setVisible(false);

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
            enfrentandoChefe = true;
            chefeSecretoAtual = new SenhorBatata();
            lblStatusCombate.setText("Chefe Secreto Encontrado: " + chefeSecretoAtual.getNome() +
                    "!\nHP " + heroiAtual.getNome() + ": " + heroiAtual.getVida() + " | HP Chefe: " + chefeSecretoAtual.getVida());
            return;
        }

        enfrentandoChefe = false;
        List<Inimigo> bestiario = new ArrayList<>();
        bestiario.add(new Aranha());
        bestiario.add(new Goblin());
        bestiario.add(new Esqueleto());
        bestiario.add(new Ciclope());

        inimigoAtual = bestiario.get(random.nextInt(bestiario.size()));

        String avisoStatus = "";
        if (estaQueimando) avisoStatus += " (Queimando!)";
        if (estaEnvenenado) avisoStatus += " (Envenenado!)";
        if (estaLento) avisoStatus += " (Lento!)";

        lblStatusCombate.setText("Um(a) " + inimigoAtual.getNome() + " apareceu!" + avisoStatus +
                "\nHP " + heroiAtual.getNome() + ": " + heroiAtual.getVida() + " | HP Inimigo: " + inimigoAtual.getVida());
    }

    private void finalizarCombate(String mensagemFinal, boolean morreu) {
        lblStatusCombate.setText(mensagemFinal);
        btnBater.setVisible(false);
        btnMagia.setVisible(false);
        btnUsarItens.setVisible(false);
        btnFugir.setVisible(false);
        btnIrParaMenu.setVisible(true);

        if (morreu) {
            btnIrParaMenu.setText("Voltar à Seleção de Herói");
            btnIrParaMenu.setOnAction(e -> resetarJogoCompleto());
        } else {
            btnIrParaMenu.setText("Retornar ao Menu Principal");
            btnIrParaMenu.setOnAction(e -> primaryStage.setScene(sceneMenu));
        }
    }

    private void executarTurnoCombate(String tipoAcao) {
        int vidaAlvo = enfrentandoChefe ? chefeSecretoAtual.getVida() : inimigoAtual.getVida();
        if (vidaAlvo <= 0 || heroiAtual.getVida() <= 0) return;

        StringBuilder relatorio = new StringBuilder();
        String nomeAlvo = enfrentandoChefe ? chefeSecretoAtual.getNome() : inimigoAtual.getNome();
        int defesaAlvo = enfrentandoChefe ? 15 : inimigoAtual.getDefesa();
        double desvioAlvo = enfrentandoChefe ? 0.05 : inimigoAtual.getChanceDesvio();

        // Turno do Jogador (Polimorfismo no cálculo especial de cada classe)
        if (random.nextDouble() < desvioAlvo) {
            relatorio.append("O(a) ").append(nomeAlvo).append(" desviou do seu ataque!\n");
        } else {
            int ataqueBrutoHeroi = tipoAcao.equals("bater")
                    ? heroiAtual.getAtaque()
                    : heroiAtual.calcularDanoEspecial();

            int danoCausado = Math.max(1, ataqueBrutoHeroi - defesaAlvo);

            if (enfrentandoChefe) {
                chefeSecretoAtual.setVida(chefeSecretoAtual.getVida() - danoCausado);
                vidaAlvo = chefeSecretoAtual.getVida();
            } else {
                inimigoAtual.setVida(inimigoAtual.getVida() - danoCausado);
                vidaAlvo = inimigoAtual.getVida();
            }
            relatorio.append("Você causou ").append(danoCausado).append(" de dano.\n");
        }

        if (vidaAlvo <= 0) {
            int ganho = 20 + random.nextInt(21);
            moedas += ganho;
            finalizarCombate("Vitória! Você derrotou o(a) " + nomeAlvo + "!\nGanhou " + ganho + " moedas.", false);
            return;
        }

        // Efeitos persistentes
        if (estaQueimando) {
            heroiAtual.receberDano(15);
            relatorio.append("O fogo queimou você em 15 de dano!\n");
        }
        if (estaEnvenenado) {
            int danoVeneno = 5 + random.nextInt(6);
            heroiAtual.receberDano(danoVeneno);
            relatorio.append("O veneno causou ").append(danoVeneno).append(" de dano.\n");
        }

        if (heroiAtual.getVida() <= 0) {
            finalizarCombate("Game Over! Você foi derrotado...", true);
            return;
        }

        // Turno do Inimigo (Polimórfico)
        double desvioEfetivo = estaLento ? (heroiAtual.getChanceDesvio() / 2) : heroiAtual.getChanceDesvio();

        if (random.nextDouble() < desvioEfetivo) {
            relatorio.append("Você desviou do ataque do(a) ").append(nomeAlvo).append("!\n");
        } else {
            int ataqueBruto = enfrentandoChefe
                    ? (int) (chefeSecretoAtual.getAtaque() * (0.8 + random.nextDouble() * 0.4))
                    : inimigoAtual.calcularDanoAtaque();

            int danoRecebido = Math.max(1, ataqueBruto - heroiAtual.getDefesa());
            heroiAtual.receberDano(danoRecebido);
            relatorio.append("O(a) ").append(nomeAlvo).append(" te atingiu causando ").append(danoRecebido).append(" de dano.\n");

            if (!enfrentandoChefe) {
                String efeito = inimigoAtual.acaoEspecial();
                if (efeito.equals("queimadura")) {
                    estaQueimando = true;
                    relatorio.append("Você foi incendiado! (-15 HP/turno)\n");
                } else if (efeito.equals("veneno")) {
                    estaEnvenenado = true;
                    relatorio.append("Você foi envenenado!\n");
                } else if (efeito.equals("lento")) {
                    estaLento = true;
                    relatorio.append("Você ficou Lento (Desvio reduzido)!\n");
                }
            }
        }

        if (heroiAtual.getVida() <= 0) {
            finalizarCombate("Game Over! Você foi derrotado...", true);
            return;
        }

        lblStatusCombate.setText(relatorio.toString() + String.format("\nHP %s: %d | HP %s: %d",
                heroiAtual.getNome(), heroiAtual.getVida(), nomeAlvo, vidaAlvo));
    }

    // ==========================================
    // 4. TELA DA LOJA (Exceções + Interface Equipavel)
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
        pool.add("cura1"); pool.add("cura2"); pool.add("cura3"); pool.add("cura4");

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
                    if (!escolhido.startsWith("cura")) usadosUnicos.add(escolhido);
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
                btn.setOnAction(e -> tentarComprarPocao(1, pocao1.getPreco(), pocao1.getNome()));
                break;
            case "cura2":
                btn.setText(pocao2.getNome() + " - Preço: " + pocao2.getPreco() + " moedas | " + pocao2.getDescricao());
                btn.setOnAction(e -> tentarComprarPocao(2, pocao2.getPreco(), pocao2.getNome()));
                break;
            case "cura3":
                btn.setText(pocao3.getNome() + " - Preço: " + pocao3.getPreco() + " moedas | " + pocao3.getDescricao());
                btn.setOnAction(e -> tentarComprarPocao(3, pocao3.getPreco(), pocao3.getNome()));
                break;
            case "cura4":
                btn.setText(pocao4.getNome() + " - Preço: " + pocao4.getPreco() + " moedas | " + pocao4.getDescricao());
                btn.setOnAction(e -> tentarComprarPocao(4, pocao4.getPreco(), pocao4.getNome()));
                break;
            case "arma1":
                configurarBotaoArma(btn, armaArqueiro); break;
            case "arma2":
                configurarBotaoArma(btn, armaBarbaro); break;
            case "arma3":
                configurarBotaoArma(btn, armaLadino); break;
            case "arma4":
                configurarBotaoArma(btn, armaMago); break;
            case "armadura1":
                configurarBotaoEquipavel(btn, armaduraArqueiro); break;
            case "armadura2":
                configurarBotaoEquipavel(btn, armaduraBarbaro); break;
            case "armadura3":
                configurarBotaoEquipavel(btn, armaduraLadino); break;
            case "armadura4":
                configurarBotaoEquipavel(btn, armaduraMago); break;
            case "batata":
                btn.setText(itemBatata.getNome() + " - Preço: " + itemBatata.getPreco() + " moedas | Cura 1 de vida");
                btn.setOnAction(e -> tentarComprarBatata(btn));
                break;
        }
        return btn;
    }

    private void configurarBotaoEquipavel(Button btn, Equipavel item) {
        btn.setText(item.getNome() + " (" + item.getPersonagemCompativel() + ") - Preço: " + item.getPreco() + " moedas | +" + item.getBonus() + " Defesa");
        btn.setOnAction(e -> {
            try {
                validarCompra(item.getPreco(), item.getPersonagemCompativel(), item.getNome());
                moedas -= item.getPreco();
                heroiAtual.aumentarDefesa(item.getBonus());
                armaduraEquipada = item.getNome() + " (+" + item.getBonus() + " DEF)";
                item.setDisponivel(false);
                equipamentosAdquiridos.add(item);
                btn.setDisable(true);
                lblMsgLoja.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
                lblMsgLoja.setText("Você equipou " + item.getNome() + "! Defesa atual: " + heroiAtual.getDefesa());
                atualizarLoja();
            } catch (SaldoInsuficienteException | ClasseIncompativelException ex) {
                lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
                lblMsgLoja.setText(ex.getMessage());
            }
        });
    }

    private void configurarBotaoArma(Button btn, Object armaObj) {
        String nome = "", comp = "";
        int preco = 0, bonus = 0;
        if (armaObj instanceof Arma1 a) { nome = a.getNome(); comp = a.getPersonagemCompativel(); preco = a.getPreco(); bonus = a.getBonusAtaque(); }
        else if (armaObj instanceof Arma2 a) { nome = a.getNome(); comp = a.getPersonagemCompativel(); preco = a.getPreco(); bonus = a.getBonusAtaque(); }
        else if (armaObj instanceof Arma3 a) { nome = a.getNome(); comp = a.getPersonagemCompativel(); preco = a.getPreco(); bonus = a.getBonusAtaque(); }
        else if (armaObj instanceof Arma4 a) { nome = a.getNome(); comp = a.getPersonagemCompativel(); preco = a.getPreco(); bonus = a.getBonusAtaque(); }

        final String fNome = nome, fComp = comp;
        final int fPreco = preco, fBonus = bonus;

        btn.setText(fNome + " (" + fComp + ") - Preço: " + fPreco + " moedas | +" + fBonus + " Ataque");
        btn.setOnAction(e -> {
            try {
                validarCompra(fPreco, fComp, fNome);
                moedas -= fPreco;
                heroiAtual.aumentarAtaque(fBonus);
                armaEquipada = fNome + " (+" + fBonus + " ATQ)";
                if (armaObj instanceof Arma1 a) a.setDisponivel(false);
                else if (armaObj instanceof Arma2 a) a.setDisponivel(false);
                else if (armaObj instanceof Arma3 a) a.setDisponivel(false);
                else if (armaObj instanceof Arma4 a) a.setDisponivel(false);
                btn.setDisable(true);
                lblMsgLoja.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
                lblMsgLoja.setText("Você equipou " + fNome + "! Ataque atual: " + heroiAtual.getAtaque());
                atualizarLoja();
            } catch (SaldoInsuficienteException | ClasseIncompativelException ex) {
                lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
                lblMsgLoja.setText(ex.getMessage());
            }
        });
    }

    private void validarCompra(int preco, String classeCompativel, String nomeItem)
            throws SaldoInsuficienteException, ClasseIncompativelException {
        if (classeCompativel != null && !heroiAtual.getNome().equalsIgnoreCase(classeCompativel)) {
            throw new ClasseIncompativelException("Apenas " + classeCompativel + " pode equipar " + nomeItem + "!");
        }
        if (moedas < preco) {
            throw new SaldoInsuficienteException("Moedas insuficientes para comprar " + nomeItem + "!");
        }
    }

    private void tentarComprarPocao(int tipo, int preco, String nome) {
        try {
            validarCompra(preco, null, nome);
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
        } catch (SaldoInsuficienteException | ClasseIncompativelException ex) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText(ex.getMessage());
        }
    }

    private void tentarComprarBatata(Button btnOrigem) {
        try {
            validarCompra(itemBatata.getPreco(), null, itemBatata.getNome());
            if (itemBatata.comprar()) {
                moedas -= itemBatata.getPreco();
                btnOrigem.setDisable(true);
                lblMsgLoja.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
                lblMsgLoja.setText("Você comprou: " + itemBatata.getNome() + "!");
                atualizarLoja();
            }
        } catch (SaldoInsuficienteException | ClasseIncompativelException ex) {
            lblMsgLoja.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            lblMsgLoja.setText(ex.getMessage());
        }
    }

    private Button criarBotaoEstilizado(String texto, String estiloBase) {
        Button btn = new Button(texto);
        btn.setStyle(estiloBase);
        btn.setOnMouseEntered(e -> btn.setStyle(estiloBase.replace("-fx-background-color: #2c3e50;", "-fx-background-color: #34495e;").replace("-fx-background-color: #e74c3c;", "-fx-background-color: #c0392b;")));
        btn.setOnMouseExited(e -> btn.setStyle(estiloBase));
        return btn;
    }

    private void atualizarLoja() {
        lblMoedasLoja.setText("Suas Moedas: " + moedas + " | Herói: " + heroiAtual.getNome() +
                " (ATQ: " + heroiAtual.getAtaque() + " | DEF: " + heroiAtual.getDefesa() + ")");
        String statusBatataStr = itemBatata.isNoInventario() ? "1 (No Inventário)" : "0";
        lblInventarioLoja.setText(String.format(
                "=== SEU INVENTÁRIO (%d equipamentos) ===\n• %s: %d  |  • %s: %d\n• %s: %d  |  • %s: %d\n• Arma: %s\n• Armadura: %s\n• Batata: %s",
                equipamentosAdquiridos.size(),
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
            int novaVida = heroiAtual.getVida();
            switch (tipo) {
                case 1:
                    novaVida = pocao1.curar(heroiAtual.getVida(), heroiAtual.getVidaMax());
                    qtdCura1--; nomeItem = pocao1.getNome(); limpaStatus = pocao1.isRemoveStatus(); break;
                case 2:
                    novaVida = pocao2.curar(heroiAtual.getVida(), heroiAtual.getVidaMax());
                    qtdCura2--; nomeItem = pocao2.getNome(); limpaStatus = pocao2.isRemoveStatus(); break;
                case 3:
                    novaVida = pocao3.curar(heroiAtual.getVida(), heroiAtual.getVidaMax());
                    qtdCura3--; nomeItem = pocao3.getNome(); limpaStatus = pocao3.isRemoveStatus(); break;
                case 4:
                    novaVida = pocao4.curar(heroiAtual.getVida(), heroiAtual.getVidaMax());
                    qtdCura4--; nomeItem = pocao4.getNome(); limpaStatus = pocao4.isRemoveStatus(); break;
            }
            heroiAtual.setVida(novaVida);
            if (limpaStatus) {
                estaQueimando = false;
                estaEnvenenado = false;
                estaLento = false;
            }
        } else if (tipo == 5) {
            int curaBatata = itemBatata.usar();
            heroiAtual.curar(curaBatata);
            nomeItem = itemBatata.getNome();
        }

        String nomeAlvo = enfrentandoChefe ? chefeSecretoAtual.getNome() : inimigoAtual.getNome();
        int vidaAlvo = enfrentandoChefe ? chefeSecretoAtual.getVida() : inimigoAtual.getVida();

        lblStatusCombate.setText("Você usou " + nomeItem + "!" + (limpaStatus ? " Status limpos." : "") +
                "\nHP " + heroiAtual.getNome() + ": " + heroiAtual.getVida() + " | HP " + nomeAlvo + ": " + vidaAlvo);
    }

    public static void main(String[] args) {
        launch(args);
    }
}