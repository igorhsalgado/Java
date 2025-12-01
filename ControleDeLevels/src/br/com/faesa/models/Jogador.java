package br.com.faesa.models;

public class Jogador {
    private int id;
    private String nome;
    private String nickname;

    public Jogador(int id, String nome, String nickname) {
        this.id = id;
        this.nome = nome;
        this.nickname = nickname;
    }

    public int getId() { return id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Nick: " + nickname;
    }

    public String toCsv() {
        return id + ";" + nome + ";" + nickname;
    }

    public static Jogador fromCsv(String linha) {
        String[] partes = linha.split(";");
        return new Jogador(Integer.parseInt(partes[0]), partes[1], partes[2]);
    }
}