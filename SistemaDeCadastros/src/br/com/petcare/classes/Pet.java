package br.com.petcare.classes;

import br.com.petcare.enums.SexoPets;
import br.com.petcare.enums.TipoPets;

import java.nio.file.Path;
import java.util.List;


public class Pet {

    public static final String NAO_INFORMADO = "NÃO INFORMADO";

    private String nomeCompleto;
    private TipoPets tipo;
    private SexoPets sexo;
    private String rua;
    private String numeroCasa;
    private String cidade;
    private String idadeAproximada;
    private String pesoAproximado;
    private String raca;

    private transient Path caminhoDoArquivoOriginal;

    public Path getCaminhoDoArquivoOriginal() {
        return caminhoDoArquivoOriginal;
    }

    public void setCaminhoDoArquivoOriginal(Path caminhoDoArquivoOriginal) {
        this.caminhoDoArquivoOriginal = caminhoDoArquivoOriginal;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public TipoPets getTipo() {
        return tipo;
    }

    public void setTipo(TipoPets tipo) {
        this.tipo = tipo;
    }

    public SexoPets getSexo() {
        return sexo;
    }

    public void setSexo(SexoPets sexo) {
        this.sexo = sexo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getIdadeAproximada() {
        return idadeAproximada;
    }

    public void setIdadeAproximada(String idadeAproximada) {
        this.idadeAproximada = idadeAproximada;
    }

    public String getPesoAproximado() {
        return pesoAproximado;
    }

    public void setPesoAproximado(String pesoAproximado) {
        this.pesoAproximado = pesoAproximado;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    // Devolve os dados brutos do arquivo de texto em formato de um objeto Pet
    public static Pet fromFileLines(List<String> linhas) {
        Pet pet = new Pet();

        // Extrai o valor de cada linha, quebrando no "-" e pegando a segunda parte com trim().
        pet.setNomeCompleto(linhas.get(0).split("-", 2)[1].trim());
        pet.setTipo(TipoPets.valueOf(linhas.get(1).split("-", 2)[1].trim().toUpperCase()));
        pet.setSexo(SexoPets.valueOf(linhas.get(2).split("-", 2)[1].trim().toUpperCase()));

        // Para o endereço, quebramos a linha 3 primeiro no "-" e depois na vírgula.
        String enderecoCompleto = linhas.get(3).split("-", 2)[1].trim();
        String[] partesEndereco = enderecoCompleto.split(",");
        pet.setRua(partesEndereco[0].trim());
        pet.setNumeroCasa(partesEndereco[1].trim());
        pet.setCidade(partesEndereco[2].trim());

        pet.setIdadeAproximada(linhas.get(4).split("-", 2)[1].trim());
        pet.setPesoAproximado(linhas.get(5).split("-", 2)[1].trim());
        pet.setRaca(linhas.get(6).split("-", 2)[1].trim());

        return pet;
    }

    // Gera o conteúdo para que ele seja salvo no formato txt (Chamado no metódo salvarPet)
    public String gerarConteudo() {
        String enderecoCompleto = String.join(", ", this.rua, this.numeroCasa, this.cidade);

        return String.format(
                "1 - %s\n" +
                "2 - %s\n" +
                "3 - %s\n" +
                "4 - %s\n" +
                "5 - %s\n" +
                "6 - %s\n" +
                "7 - %s",
                this.nomeCompleto,
                this.tipo,
                this.sexo,
                enderecoCompleto,
                this.idadeAproximada,
                this.pesoAproximado,
                this.raca
        );
    }

    // Uma maneira mais limpa de exibir os dados do Pet, diferentemente do toString
    public String toResumoString() {
        String idadeStr = idadeAproximada.equals(Pet.NAO_INFORMADO) ? "?" : idadeAproximada;
        String pesoStr = pesoAproximado.equals(Pet.NAO_INFORMADO) ? "?" : pesoAproximado;

        return String.format("%s - %s - %s - %s, %s - %s - %s anos - %s kg - %s",
                this.nomeCompleto,
                this.tipo,
                this.sexo,
                this.rua, this.numeroCasa, this.cidade,
                idadeStr,
                pesoStr,
                this.raca
        );
    }

    @Override
    public String toString() {
        String idadeStr = idadeAproximada.equals(NAO_INFORMADO) ? NAO_INFORMADO : idadeAproximada + " anos";
        String pesoStr = pesoAproximado.equals(NAO_INFORMADO) ? NAO_INFORMADO : pesoAproximado + "  kg";


        return "Pet Cadastrado:\n" +
                "-----------------------------------\n" +
                "Nome: " + nomeCompleto + "\n" +
                "Tipo: " + tipo + "\n" +
                "Sexo: " + sexo + "\n" +
                "Idade: " + idadeStr + "\n" +
                "Peso: " + pesoStr + "\n" +
                "Raça: " + raca + "\n" +
                "Endereço: " + rua + ", " + numeroCasa + ", " + cidade + "\n" +
                "-----------------------------------";
    }
}