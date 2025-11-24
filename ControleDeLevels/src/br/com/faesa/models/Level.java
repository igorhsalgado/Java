package br.com.faesa.models;

public class Level {
    private int id;
    private String nomeFase;
    private int pontuacao;
    private String status;
    private int idJogador;

    public Level(int id, String nomeFase, int pontuacao, String status, int idJogador) {
        this.id = id;
        this.nomeFase = nomeFase;
        this.pontuacao = pontuacao;
        this.status = status;
        this.idJogador = idJogador;
    }

    public int getId() { return id; }
    public String getNomeFase() { return nomeFase; }
    public void setNomeFase(String nomeFase) { this.nomeFase = nomeFase; }
    public int getPontuacao() { return pontuacao; }
    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getIdJogador() { return idJogador; }

    @Override
    public String toString() {
        return "ID: " + id + " | Fase: " + nomeFase + " | Pontos: " + pontuacao + " | Status: " + status + " | ID Jogador: " + idJogador;
    }

    public String toCsv() {
        return id + ";" + nomeFase + ";" + pontuacao + ";" + status + ";" + idJogador;
    }

    public static Level fromCsv(String linha) {
        String[] partes = linha.split(";");
        return new Level(
            Integer.parseInt(partes[0]), 
            partes[1], 
            Integer.parseInt(partes[2]), 
            partes[3], 
            Integer.parseInt(partes[4])
        );
    }
}