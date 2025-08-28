# 📌 Aplicativo de Compras com Cartão de Crédito

Este é um projeto simples, desenvolvido em Java, que simula um aplicativo de compras interativo via console. O objetivo principal é gerenciar o limite de um cartão de crédito, permitindo que o usuário adicione compras, verifique o saldo disponível e visualize o histórico de transações.

## 🛠️ Tecnologias Utilizadas
* **Linguagem de Programação**: Java
* **Paradigma**: Programação Orientada a Objetos (POO)
* **Estrutura de Dados**: `ArrayList` para armazenar a lista de compras

## ⚙️ Como Compilar e Executar

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
    ```
2.  **Abra o projeto:**
    Abra a pasta do projeto em sua IDE Java preferida (como IntelliJ IDEA, Eclipse ou VS Code). O projeto já possui a estrutura de módulos padrão.
3.  **Execute o `Main.java`:**
    Execute a classe `Main.java` para iniciar o aplicativo. A interação ocorrerá diretamente no console.

## 🧾 Descrição das Classes

### `Main.java`
Esta é a classe principal do aplicativo. Ela contém o método `main()` que controla o fluxo de execução. É responsável por:
* Inicializar o `Scanner` para receber entradas do usuário.
* Criar uma instância da classe `CreditCard`.
* Gerenciar o loop principal de interação, solicitando a descrição e o preço de cada compra.
* Chamar o método `launchPurchase()` do `CreditCard` para processar a compra.
* Imprimir o histórico de compras e o saldo final do cartão.

### `CreditCard.java`
A classe `CreditCard` representa o cartão de crédito. Ela gerencia o limite, o saldo e a lista de compras do cartão.
* **Atributos**: `limit` (limite total), `balance` (saldo atual) e `purchases` (uma lista de objetos `Purchase`).
* **Método `launchPurchase(Purchase purchase)`**: Este método verifica se o saldo (`balance`) é maior que o preço da compra. Se for, a compra é adicionada à lista e o saldo é subtraído.

### `Purchase.java`
A classe `Purchase` representa um item de compra individual.
* **Atributos**: `description` (descrição do produto) e `price` (preço do produto).
* **Métodos `getDescription()` e `getPrice()`**: Fornecem acesso aos atributos da compra.
* **`toString()`**: Sobrescrito para retornar uma string formatada com a descrição e o preço da compra.

## ▶️ Exemplo de Uso

Ao executar o programa, o console terá a seguinte interação:

Digite o limite do cartao:
1000
Digite a descrição da compra:
Tenis
Digite o preço da compra:
350
Compra realizada\!
Digite 0 para sair, ou digite 1 para continuar.
1
Digite a descrição da compra:
Camiseta
Digite o preço da compra:
150
Compra realizada\!
Digite 0 para sair, ou digite 1 para continuar.
0

-----

COMPRAS REALIZADAS:
Tenis - 350.0
Camiseta - 150.0

-----

Saldo atual do cartão: 500.0


👨‍💻 Autor
Igor Hermann Salgado
