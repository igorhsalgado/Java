

# 🔎 Localizador de Endereço por CEP

Este projeto é uma aplicação de console em Java para buscar endereços a partir de um número de CEP. Ele utiliza uma API pública para obter os dados e, em seguida, salva o resultado em um arquivo JSON.

## 🛠️ Tecnologias Utilizadas
* **Linguagem de Programação**: Java
* **APIs e Bibliotecas**:
    * **ViaCEP API**: Serviço web público para consulta de CEP.
    * **Gson**: Biblioteca da Google para serialização e desserialização de objetos Java para JSON.

## ⚙️ Como Compilar e Executar

1.  **Estrutura do Projeto:** Certifique-se de que a estrutura do seu projeto está configurada para reconhecer as dependências externas.
2.  **Adicione as Bibliotecas:** Adicione as bibliotecas `viacep-1.2.0.jar` e `gson-2.10.1.jar` como dependências ao seu projeto.
3.  **Execute o `Main.java`:** Abra sua IDE (como o IntelliJ IDEA) e execute a classe `Main.java`. A aplicação irá rodar diretamente no console.

## 📄 Estrutura do Código

O projeto é organizado em quatro classes principais, cada uma com uma responsabilidade específica:

### `Main.java`
Esta é a classe principal do programa. Ela contém um menu interativo que permite ao usuário buscar por um CEP repetidamente ou sair da aplicação. A classe `Main` também é responsável por:
* Receber a entrada do usuário através do `Scanner`.
* Tratar possíveis erros de conexão ou CEPs inválidos com um bloco `try-catch`.
* Instanciar as classes `CepQuery` e `FileGenerator`.

### `CepQuery.java`
A classe `CepQuery` é responsável por toda a comunicação com a API do ViaCEP.
* O método `searchAdress(String cep)` constrói uma requisição HTTP para a API.
* Ele recebe a resposta em formato JSON e usa a biblioteca `Gson` para converter os dados para um objeto `Adress`.
* Lança uma exceção se a busca falhar ou o CEP não for encontrado.

### `Adress.java`
Esta classe é um `record` que modela os dados de endereço retornados pela API.
* Por ser um `record`, ele já possui construtor, getters e os métodos `equals()`, `hashCode()` e `toString()` automaticamente.
* O método `toString()` foi sobrescrito para formatar a saída de forma organizada para o usuário.

### `FileGenerator.java`
A classe `FileGenerator` lida com a criação de arquivos.
* O método `generateJson(Adress adress)` recebe um objeto `Adress`.
* Usa o `GsonBuilder` para criar um objeto JSON formatado e salva-o em um arquivo com o nome do CEP.

## ▶️ Exemplo de Uso
Ao executar o programa, o console terá a seguinte interação:

<img width="261" height="449" alt="Image" src="https://github.com/user-attachments/assets/7117bb00-2c40-4b19-bdc8-cb91c17fab7a" />

## 👨‍💻 Autor
Igor Hermann Salgado
