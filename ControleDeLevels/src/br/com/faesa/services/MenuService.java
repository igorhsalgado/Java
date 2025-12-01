package br.com.faesa.services;

import br.com.faesa.models.Jogador;
import br.com.faesa.models.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuService {
    private Scanner scanner;
    private GerenciadorArquivos gerenciador;
    private final String ARQ_JOGADORES = "jogadores.txt";
    private final String ARQ_LEVELS = "levels.txt";

    public MenuService(Scanner scanner) {
        this.scanner = scanner;
        this.gerenciador = new GerenciadorArquivos();
    }

    public void iniciar() {
        int opcao = 0;
        do {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: alterarLevel(); break;
                case 3: alterarJogador(); break;
                case 4: excluirLevel(); break;
                case 5: excluirJogador(); break;
                case 6: consultaGeral(); break;
                case 7: consultaEspecifica(); break;
                case 8: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 8);
    }

    private void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Inserir Dados (Jogador e/ou Level)");
        System.out.println("2. Alterar Dados de Level");
        System.out.println("3. Alterar Dados de Jogador"); // <--- Adicionado
        System.out.println("4. Excluir Level");
        System.out.println("5. Excluir Jogador (E seus levels)");
        System.out.println("6. Consulta Geral");
        System.out.println("7. Consulta Específica");
        System.out.println("8. Sair");
        System.out.print("Escolha: ");
    }

    private void cadastrar() {
        System.out.println("1 - Cadastrar Jogador");
        System.out.println("2 - Cadastrar Level (Vinculado a um jogador)");
        System.out.print("Opção: ");
        String op = scanner.nextLine();

        if (op.equals("1")) {
            System.out.print("ID do Jogador (Unico): ");
            int id = Integer.parseInt(scanner.nextLine());
            if (buscarJogadorPorId(id) != null) {
                System.out.println("Erro: ID já existe!");
                return;
            }
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Nickname: ");
            String nick = scanner.nextLine();

            Jogador j = new Jogador(id, nome, nick);
            gerenciador.salvarLinha(ARQ_JOGADORES, j.toCsv());
            System.out.println("Jogador cadastrado!");

        } else if (op.equals("2")) {
            System.out.print("ID do Level (Unico): ");
            int id = Integer.parseInt(scanner.nextLine());
            if (buscarLevelPorId(id) != null) {
                System.out.println("Erro: ID do Level já existe!");
                return;
            }

            System.out.print("ID do Jogador dono deste level: ");
            int idJog = Integer.parseInt(scanner.nextLine());
            if (buscarJogadorPorId(idJog) == null) {
                System.out.println("Erro: Jogador não encontrado. Cadastre o jogador antes.");
                return;
            }

            System.out.print("Nome da Fase: ");
            String fase = scanner.nextLine();
            System.out.print("Pontuação: ");
            int pontos = Integer.parseInt(scanner.nextLine());
            System.out.print("Status (ex: Completo): ");
            String status = scanner.nextLine();

            Level l = new Level(id, fase, pontos, status, idJog);
            gerenciador.salvarLinha(ARQ_LEVELS, l.toCsv());
            System.out.println("Level cadastrado!");
        }
    }

    private void alterarJogador() {
        System.out.print("Digite o ID do Jogador que deseja alterar: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        List<Jogador> jogadores = listarJogadores();
        boolean encontrado = false;

        for (Jogador j : jogadores) {
            if (j.getId() == id) {
                encontrado = true;
                System.out.println("Jogador encontrado: " + j.getNome());

                System.out.print("Novo Nome (Enter mantém '" + j.getNome() + "'): ");
                String novoNome = scanner.nextLine();
                if (!novoNome.isEmpty()) j.setNome(novoNome);

                System.out.print("Novo Nickname (Enter mantém '" + j.getNickname() + "'): ");
                String novoNick = scanner.nextLine();
                if (!novoNick.isEmpty()) j.setNickname(novoNick);
            }
        }

        if (encontrado) {
            List<String> linhas = jogadores.stream().map(Jogador::toCsv).collect(Collectors.toList());
            gerenciador.salvarTodasLinhas(ARQ_JOGADORES, linhas);
            System.out.println("Dados do jogador atualizados com sucesso!");
        } else {
            System.out.println("Jogador com ID " + id + " não encontrado.");
        }
    }

    private void alterarLevel() {
        System.out.print("Digite o ID do Level que deseja alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        List<Level> levels = listarLevels();
        boolean encontrado = false;

        for (Level l : levels) {
            if (l.getId() == id) {
                encontrado = true;
                System.out.println("Level encontrado: " + l);

                System.out.print("Novo Nome da Fase (Enter mantém '" + l.getNomeFase() + "'): ");
                String novoNome = scanner.nextLine();
                if (!novoNome.isEmpty()) l.setNomeFase(novoNome);

                System.out.print("Nova Pontuação (0 ou negativo mantém " + l.getPontuacao() + "): ");
                try {
                    String ptStr = scanner.nextLine();
                    if(!ptStr.isEmpty()) {
                        int novosPontos = Integer.parseInt(ptStr);
                        if (novosPontos > 0) l.setPontuacao(novosPontos);
                    }
                } catch(NumberFormatException ignored) {}

                System.out.print("Novo Status (Enter mantém '" + l.getStatus() + "'): ");
                String novoStatus = scanner.nextLine();
                if (!novoStatus.isEmpty()) l.setStatus(novoStatus);
            }
        }

        if (encontrado) {
            List<String> linhas = levels.stream().map(Level::toCsv).collect(Collectors.toList());
            gerenciador.salvarTodasLinhas(ARQ_LEVELS, linhas);
            System.out.println("Level atualizado com sucesso!");
        } else {
            System.out.println("Level não encontrado.");
        }
    }

    private void excluirJogador() {
        System.out.print("Digite o ID do Jogador que deseja excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        List<Jogador> jogadores = listarJogadores();
        List<Jogador> novaListaJog = new ArrayList<>();
        boolean removido = false;

        for (Jogador j : jogadores) {
            if (j.getId() == id) {
                removido = true;
            } else {
                novaListaJog.add(j);
            }
        }

        if (!removido) {
            System.out.println("Jogador não encontrado.");
            return;
        }

        List<Level> levels = listarLevels();
        List<Level> novaListaLevels = new ArrayList<>();
        int levelsRemovidos = 0;

        for (Level l : levels) {
            if (l.getIdJogador() == id) {
                levelsRemovidos++;
            } else {
                novaListaLevels.add(l);
            }
        }

        List<String> linhasJog = novaListaJog.stream().map(Jogador::toCsv).collect(Collectors.toList());
        gerenciador.salvarTodasLinhas(ARQ_JOGADORES, linhasJog);

        if (levelsRemovidos > 0) {
            List<String> linhasLev = novaListaLevels.stream().map(Level::toCsv).collect(Collectors.toList());
            gerenciador.salvarTodasLinhas(ARQ_LEVELS, linhasLev);
        }

        System.out.println("Jogador excluído com sucesso!");
    }

    private void excluirLevel() {
        System.out.print("Digite o ID do Level que deseja excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        List<Level> levels = listarLevels();
        List<Level> novaLista = new ArrayList<>();
        boolean removido = false;

        for (Level l : levels) {
            if (l.getId() == id) {
                removido = true;
                System.out.println("Removendo level: " + l.getNomeFase());
            } else {
                novaLista.add(l);
            }
        }

        if (removido) {
            List<String> linhas = novaLista.stream().map(Level::toCsv).collect(Collectors.toList());
            gerenciador.salvarTodasLinhas(ARQ_LEVELS, linhas);
            System.out.println("Level excluído!");
        } else {
            System.out.println("ID não encontrado.");
        }
    }

    private void consultaGeral() {
        System.out.println("\n--- LISTA DE JOGADORES ---");
        List<Jogador> jogadores = listarJogadores();
        if (jogadores.isEmpty()) System.out.println("Nenhum jogador cadastrado.");
        jogadores.forEach(System.out::println);

        System.out.println("\n--- LISTA DE LEVELS ---");
        List<Level> levels = listarLevels();
        if (levels.isEmpty()) System.out.println("Nenhum level registrado.");
        levels.forEach(System.out::println);
    }

    private void consultaEspecifica() {
        System.out.print("Digite o ID do Jogador para ver seus levels: ");
        int idBusca = Integer.parseInt(scanner.nextLine());

        Jogador j = buscarJogadorPorId(idBusca);

        if (j == null) {
            System.out.println("Jogador não encontrado!");
        } else {
            System.out.println("\nJogador Localizado: " + j.getNome() + " (" + j.getNickname() + ")");
            System.out.println("Buscando levels associados...");

            List<Level> levelsDoJogador = new ArrayList<>();
            for (Level l : listarLevels()) {
                if (l.getIdJogador() == j.getId()) {
                    levelsDoJogador.add(l);
                }
            }

            if (levelsDoJogador.isEmpty()) {
                System.out.println("Este jogador ainda não tem registros de level.");
            } else {
                System.out.println("Histórico de Levels:");
                for (Level l : levelsDoJogador) {
                    System.out.println("  -> Fase: " + l.getNomeFase() + " | Pontos: " + l.getPontuacao() + " | Status: " + l.getStatus());
                }
            }
        }
    }

    private List<Jogador> listarJogadores() {
        List<String> linhas = gerenciador.lerArquivo(ARQ_JOGADORES);
        return linhas.stream().map(Jogador::fromCsv).collect(Collectors.toList());
    }

    private List<Level> listarLevels() {
        List<String> linhas = gerenciador.lerArquivo(ARQ_LEVELS);
        return linhas.stream().map(Level::fromCsv).collect(Collectors.toList());
    }

    private Jogador buscarJogadorPorId(int id) {
        return listarJogadores().stream().filter(j -> j.getId() == id).findFirst().orElse(null);
    }

    private Level buscarLevelPorId(int id) {
        return listarLevels().stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }
}