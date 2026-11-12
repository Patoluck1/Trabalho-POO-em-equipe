package pr_pucpr.rpg;

import Inimigos.Aranha;
import Inimigos.Ciclope;
import Inimigos.Esqueleto;
import Inimigos.Goblin;
import Personagens.Arqueiro;
import Personagens.Barbaro;
import Personagens.Ladino;
import Personagens.Mago;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private Stage primaryStage;
    private Scene sceneSelecao, sceneMenu, sceneExploracao, sceneCombate;

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

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("RPG de Turno - IntelliJ / JavaFX");

        criarTelaSelecaoPersonagem();
        criarMenuPrincipal();
        criarTelaExploracao();
        criarTelaCombate();

        primaryStage.setScene(sceneSelecao);
        primaryStage.show();
    }

    // ==========================================
    // 0. SELEÇÃO DE PERSONAGEM
    // ==========================================
    private void criarTelaSelecaoPersonagem() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Label titulo = new Label("=== ESCOLHA SEU HERÓI ===");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button btnLadino = new Button("Ladino (Melhor Desvio, Críticos)");
        btnLadino.setPrefWidth(280);
        btnLadino.setOnAction(e -> {
            Ladino l = new Ladino();
            definirHeroi(l.getNome(), l.getVida(), l.getAtaque(), l.getDefesa(), 0.35);
            primaryStage.setScene(sceneMenu);
        });

        Button btnArqueiro = new Button("Arqueiro (Bom Desvio, Equilibrado)");
        btnArqueiro.setPrefWidth(280);
        btnArqueiro.setOnAction(e -> {
            Arqueiro a = new Arqueiro();
            definirHeroi(a.getNome(), a.getVida(), a.getAtaque(), a.getDefesa(), 0.25);
            primaryStage.setScene(sceneMenu);
        });

        Button btnMago = new Button("Mago (Desvio Baixo, Magia Forte)");
        btnMago.setPrefWidth(280);
        btnMago.setOnAction(e -> {
            Mago m = new Mago();
            definirHeroi(m.getNome(), m.getVida(), m.getAtaque(), m.getDefesa(), 0.15);
            primaryStage.setScene(sceneMenu);
        });

        Button btnBarbaro = new Button("Bárbaro (Pior Desvio, Muita Vida)");
        btnBarbaro.setPrefWidth(280);
        btnBarbaro.setOnAction(e -> {
            Barbaro b = new Barbaro();
            definirHeroi(b.getNome(), b.getVida(), b.getAtaque(), b.getDefesa(), 0.05);
            primaryStage.setScene(sceneMenu);
        });

        layout.getChildren().addAll(titulo, btnLadino, btnArqueiro, btnMago, btnBarbaro);
        sceneSelecao = new Scene(layout, 600, 450);
    }

    private void definirHeroi(String nome, int vida, int ataque, int defesa, double desvio) {
        this.nomePersonagem = nome;
        this.vidaMaxJogador = vida;
        this.vidaJogador = vida;
        this.ataqueJogador = ataque;
        this.defesaJogador = defesa;
        this.chanceDesvioJogador = desvio;
    }

    // ==========================================
    // 1. MENU PRINCIPAL (Loja / Explorar)
    // ==========================================
    private void criarMenuPrincipal() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Label titulo = new Label("=== RPG DE TURNO ===");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button btnExplorar = new Button("Explorar (Batalhar)");
        btnExplorar.setPrefWidth(200);
        btnExplorar.setOnAction(e -> primaryStage.setScene(sceneExploracao));

        Button btnLoja = new Button("Loja (Espaço para seus colegas)");
        btnLoja.setPrefWidth(200);
        btnLoja.setOnAction(e -> System.out.println("Loja aberta. Integre os itens aqui!"));

        Button btnTrocarHeroi = new Button("Trocar Personagem");
        btnTrocarHeroi.setPrefWidth(200);
        btnTrocarHeroi.setOnAction(e -> primaryStage.setScene(sceneSelecao));

        layout.getChildren().addAll(titulo, btnExplorar, btnLoja, btnTrocarHeroi);
        sceneMenu = new Scene(layout, 600, 400);
    }

    // ==========================================
    // 2. TELA DE EXPLORAÇÃO
    // ==========================================
    private void criarTelaExploracao() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Label lblInfo = new Label("Você está explorando as terras sombrias...");
        lblInfo.setStyle("-fx-font-size: 14px;");

        Button btnProcurar = new Button("Procurar Inimigo");
        btnProcurar.setPrefWidth(200);
        btnProcurar.setOnAction(e -> {
            sortearInimigo();
            primaryStage.setScene(sceneCombate);
        });

        Button btnVoltar = new Button("Voltar ao Menu");
        btnVoltar.setPrefWidth(200);
        btnVoltar.setOnAction(e -> primaryStage.setScene(sceneMenu));

        layout.getChildren().addAll(lblInfo, btnProcurar, btnVoltar);
        sceneExploracao = new Scene(layout, 600, 400);
    }

    // ==========================================
    // 3. MODO COMBATE
    // ==========================================
    private void criarTelaCombate() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Label lblTituloCombate = new Label("--- MODO COMBATE ---");
        lblTituloCombate.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: darkred;");

        lblStatusCombate = new Label("Um inimigo selvagem apareceu!");
        lblStatusCombate.setStyle("-fx-font-size: 14px;");

        btnBater = new Button("Bater (Ataque Físico)");
        btnBater.setPrefWidth(200);
        btnBater.setOnAction(e -> executarTurnoCombate("bater"));

        btnMagia = new Button("Magia");
        btnMagia.setPrefWidth(200);
        btnMagia.setOnAction(e -> executarTurnoCombate("magia"));

        btnUsarItens = new Button("Usar Itens");
        btnUsarItens.setPrefWidth(200);
        btnUsarItens.setOnAction(e -> {
            vidaJogador = Math.min(vidaMaxJogador, vidaJogador + 30);
            estaQueimando = false;
            estaEnvenenado = false;
            lblStatusCombate.setText("Você usou um item: Curou HP e limpou status negativos!");
        });

        btnFugir = new Button("Fugir");
        btnFugir.setPrefWidth(200);
        btnFugir.setOnAction(e -> {
            resetarStatusEfeitos();
            primaryStage.setScene(sceneMenu); // Volta direto para o menu principal
        });

        // Botão para retornar ao menu após o fim da batalha
        btnIrParaMenu = new Button("Retornar ao Menu Principal");
        btnIrParaMenu.setPrefWidth(220);
        btnIrParaMenu.setVisible(false); // Fica oculto durante o combate
        btnIrParaMenu.setOnAction(e -> {
            resetarStatusEfeitos();
            primaryStage.setScene(sceneMenu);
        });

        layout.getChildren().addAll(lblTituloCombate, lblStatusCombate, btnBater, btnMagia, btnUsarItens, btnFugir, btnIrParaMenu);
        sceneCombate = new Scene(layout, 600, 450);
    }

    private void sortearInimigo() {
        resetarStatusEfeitos();

        // Garante que os botões de ação reapareçam e o de menu suma ao iniciar novo combate
        btnBater.setVisible(true);
        btnMagia.setVisible(true);
        btnUsarItens.setVisible(true);
        btnFugir.setVisible(true);
        btnIrParaMenu.setVisible(false);

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
                ataqueInimigo = 20; // Força bruta pura
                defesaInimigo = c.getDefesa();
                chanceDesvioInimigo = 0.02;
                break;
        }

        lblStatusCombate.setText("Um(a) " + nomeInimigo + " apareceu!\nHP Herói: " + vidaJogador + " | HP Inimigo: " + vidaInimigo);
    }

    private void resetarStatusEfeitos() {
        estaQueimando = false;
        estaEnvenenado = false;
        estaLento = false;
    }

    // Método chamado ao finalizar a luta para esconder os botões de luta e liberar a volta ao menu
    private void finalizarCombate(String mensagemFinal) {
        lblStatusCombate.setText(mensagemFinal);

        // Esconde botões de ação e mostra o botão para voltar ao menu (Loja/Explorar)
        btnBater.setVisible(false);
        btnMagia.setVisible(false);
        btnUsarItens.setVisible(false);
        btnFugir.setVisible(false);
        btnIrParaMenu.setVisible(true);
    }

    // ==========================================
    // MOTOR DE COMBATE E REGRAS DE TURNO
    // ==========================================
    private void executarTurnoCombate(String tipoAcao) {
        if (vidaInimigo <= 0 || vidaJogador <= 0) return;

        StringBuilder relatorio = new StringBuilder();

        // --- 1. AÇÃO DO JOGADOR ---
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

        // Verifica se o inimigo morreu
        if (vidaInimigo <= 0) {
            finalizarCombate("🏆 Vitória! Você derrotou o(a) " + nomeInimigo + "!\nClique abaixo para retornar ao Menu Principal.");
            return;
        }

        // --- 2. EFEITOS DE STATUS NO JOGADOR ---
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
            finalizarCombate("💀 Game Over! Você foi derrotado pelo(a) " + nomeInimigo + "...\nClique abaixo para retornar ao Menu Principal.");
            return;
        }

        // --- 3. TURNO DO INIMIGO ---
        double desvioEfetivo = estaLento ? (chanceDesvioInimigo / 2) : chanceDesvioInimigo;

        if (random.nextDouble() < desvioEfetivo) {
            relatorio.append("Você desviou do ataque do(a) ").append(nomeInimigo).append("!\n");
        } else {
            int danoRecebido = Math.max(1, ataqueInimigo - defesaJogador);
            vidaJogador -= danoRecebido;
            relatorio.append("O(a) ").append(nomeInimigo).append(" te atingiu causando ").append(danoRecebido).append(" de dano.\n");

            // Regras específicas de cada inimigo
            if (tipoInimigoStr.equals("esqueleto")) {
                if (random.nextDouble() < 0.40) {
                    estaQueimando = true;
                    relatorio.append("🔥 Você foi incendiado pelo Esqueleto! (-15 HP/turno)\n");
                }
            } else if (tipoInimigoStr.equals("aranha")) {
                if (random.nextDouble() < 0.50) {
                    if (random.nextBoolean()) {
                        estaEnvenenado = true;
                        relatorio.append("🧪 A Aranha te envenenou! (5 a 10 de dano/turno)\n");
                    } else {
                        estaLento = true;
                        relatorio.append("❄️ A Aranha te deixou Lento! (Desvio reduzido)\n");
                    }
                }
            }
        }

        // Verifica Game Over após o ataque do inimigo
        if (vidaJogador <= 0) {
            finalizarCombate("💀 Game Over! Você foi derrotado pelo(a) " + nomeInimigo + "...\nClique abaixo para retornar ao Menu Principal.");
            return;
        }

        lblStatusCombate.setText(relatorio.toString() + String.format("\nHP %s: %d | HP %s: %d",
                nomePersonagem, vidaJogador, nomeInimigo, vidaInimigo));
    }

    public static void main(String[] args) {
        launch(args);
    }
}