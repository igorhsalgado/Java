# 🎮 Sistema de Controle de Level


Este projeto é uma aplicação Java de console desenvolvida para gerenciar o progresso de jogadores em fases (levels), utilizando persistência de dados em arquivos de texto (`.txt`) e simulando um relacionamento de banco de dados (1:N).

---

## 👥 Integrantes do Grupo
* **Igor**
* **Isaque**
* **João Henrique**

---

## 📋 Sobre o Projeto
O sistema foi projetado para atender aos requisitos de um CRUD completo (Create, Read, Update, Delete) com as seguintes características obrigatórias:
1.  **Persistência em Arquivo:** Uso de arquivos `.txt` para salvar os dados.
2.  **Relacionamento:** Interligação entre dois arquivos (`jogadores.txt` e `levels.txt`) através de chaves (ID).
3.  **Menu Interativo:** Interface de console para navegar entre as funcionalidades.

## 📘 Explicação Técnica Detalhada

Esta seção detalha a arquitetura e a lógica aplicada no desenvolvimento do sistema.

### 1. Arquitetura do Projeto
O código foi organizado seguindo o princípio de **Separação de Responsabilidades** em três pacotes principais:
* **`models` (Modelo de Dados):** Classes POJO (`Jogador`, `Level`) que representam as entidades do sistema. Elas armazenam os dados e possuem métodos para conversão CSV (Comma Separated Values).
* **`services` (Regras de Negócio):** Contém a lógica pesada.
    * `MenuService`: Gerencia a interação com o usuário e valida as regras (ex: não cadastrar level para jogador inexistente).
    * `GerenciadorArquivos`: Isola a complexidade de leitura/escrita em disco.
* **`Main` (Entrada):** Apenas inicializa a aplicação e exibe os créditos.

### 2. Estrutura de Dados e Relacionamento
Para cumprir a exigência de **"dois arquivos relacionados"**, implementamos um relacionamento **1 para Muitos (1:N)**:

* **Arquivo 1 (`jogadores.txt`):** Armazena a entidade forte.
    * *Formato:* `id;nome;nickname`
* **Arquivo 2 (`levels.txt`):** Armazena a entidade fraca.
    * *Formato:* `id;nomeFase;pontuacao;status;idJogador`
    * **Chave Estrangeira:** O campo `idJogador` é o vínculo que associa um level a um jogador específico.

### 3. Manipulação de Arquivos (`GerenciadorArquivos`)
A persistência foi implementada utilizando a biblioteca `java.nio.file`:
* **Escrita (`Files.writeString`):** Utilizamos a opção `StandardOpenOption.APPEND` para adicionar novos registros ao final do arquivo sem sobrescrever os dados existentes.
* **Leitura (`Files.readAllLines`):** Carregamos o conteúdo do arquivo para uma `List<String>` em memória, permitindo o uso de *Streams* e *Loops* para filtrar dados.

### 4. Lógica das Funcionalidades
Detalhes sobre como cada requisito do trabalho foi resolvido:

#### 🔹 Inserção (Create) 
* **Jogador:** Salva ID, Nome e Nickname diretamente.
* **Level:** Antes de salvar, o sistema **valida a integridade referencial**. Ele busca se o `ID do Jogador` informado existe no arquivo `jogadores.txt`. Se não existir, o cadastro é bloqueado.

#### 🔹 Alteração (Update)
Como arquivos de texto não permitem edição direta de uma linha específica de forma performática:
1.  O sistema carrega **todos** os levels para uma lista em memória.
2.  Localiza o objeto pelo ID e altera seus atributos (Nome da Fase, Pontuação, Status).
3.  **Sobrescreve** o arquivo original com a lista atualizada.

#### 🔹 Exclusão (Delete)
Similar à alteração:
1.  Carrega todos os dados para a memória.
2.  Gera uma nova lista contendo todos os itens, **exceto** o que foi selecionado para exclusão.
3.  Sobrescreve o arquivo com a nova lista.

#### 🔹 Consulta Geral
Lê os arquivos `jogadores.txt` e `levels.txt` sequencialmente e imprime todos os registros formatados no console.

#### 🔹 Consulta Específica (Relacional)
Esta funcionalidade demonstra o relacionamento entre os arquivos:
1.  O usuário informa o **ID do Jogador**.
2.  O sistema verifica se o jogador existe.
3.  Em seguida, varre o arquivo `levels.txt` buscando registros onde `level.idJogador == jogador.id`.
4.  Exibe o histórico de partidas exclusivo daquele jogador.

### 5. Pontos Fortes do Código
* **Integridade de Dados:** O sistema impede IDs duplicados e cadastro de levels órfãos (sem jogador).
* **Tratamento de Erros:** Blocos `try-catch` protegem o sistema contra entradas inválidas (ex: digitar letras em campos numéricos).
* **Código Limpo:** Nomes de variáveis intuitivos e métodos pequenos com responsabilidades únicas.


## 🚀 Como Executar

### Pré-requisitos
* Java JDK 8 ou superior instalado.

### Passo a Passo

1.  **Clone o repositório ou baixe os arquivos.**

2.  **Compile o código:**
    Navegue até a pasta `src` pelo terminal e execute:
    ```bash
    javac Main.java models/*.java services/*.java
    ```
3.  **Execute a aplicação:**
    ```bash
    java Main
    ```
    *(A pasta `dados/` será criada automaticamente na primeira execução)*
