public record Adress(String logradouro, String cep, String localidade,
                     String bairro, String complemento, String uf) {

    @Override
    public String toString() {
        return String.format("""
                CEP: %s
                Logradouro: %s
                Bairro: %s
                Localidade: %s
                UF: %s
                Complemento: %s
                """, cep, logradouro, bairro, localidade, uf, complemento);
    }
}