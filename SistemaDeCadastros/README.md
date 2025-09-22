# 🐾 PetCare: Sistema Avançado de Gerenciamento de Pets

PetCare é uma aplicação de console robusta, desenvolvida em Java, que simula um sistema completo para o gerenciamento de registros de animais de estimação. O projeto implementa o ciclo CRUD (Create, Read, Update, Delete) e se destaca pela sua arquitetura modularizada e pela persistência de dados em arquivos de texto individuais, refletindo práticas sólidas de engenharia de software.

## ✨ Visão Geral das Funcionalidades

O sistema oferece um fluxo de trabalho completo para o gerenciamento de pets, com operações interativas e seguras:

* **Cadastro Inteligente (Create):** Um processo de cadastro guiado por um formulário externo (`formulario.txt`), com validação de dados em tempo real para cada campo. Regras de negócio, como limites de peso e idade, e validação de formatos com Expressões Regulares (Regex) garantem a integridade dos dados inseridos.

* **Busca Estratificada (Read):** Uma poderosa ferramenta de busca em duas etapas. O usuário primeiramente seleciona um critério obrigatório (Tipo de Animal) e, em seguida, pode aplicar um segundo nível de filtros, incluindo buscas parciais por nome, raça e endereço, ou buscas exatas por idade, peso e sexo.

* **Alteração Segura (Update):** Permite a edição dos dados de um pet existente. O sistema primeiro utiliza o mecanismo de busca para localizar o pet, exibe uma lista de resultados e, após a seleção do usuário, permite a alteração de campos específicos, reutilizando as mesmas regras de validação do cadastro para garantir a consistência.

* **Deleção com Confirmação (Delete):** Para prevenir a perda acidental de dados, a operação de deleção também utiliza a busca para encontrar o pet. Após a seleção, o sistema exige uma confirmação explícita do usuário (`SIM`) antes de remover permanentemente a ficha do animal, tanto da memória quanto do disco.

* **Persistência Individualizada:** Cada pet cadastrado é salvo como uma "ficha" individual em um arquivo `.txt`. O nome do arquivo é gerado dinamicamente com um timestamp detalhado e o nome do pet, garantindo um sistema de versionamento e evitando a sobrescrita de dados.

## 🏛️ Arquitetura e Design de Software

O PetCare foi projetado seguindo princípios de software que visam a organização, manutenibilidade e escalabilidade do código.

* **Separação de Responsabilidades (Single Responsibility Principle):** Este é o pilar da arquitetura do projeto. A lógica é rigorosamente dividida em classes especialistas:
    * **`Main`**: Atua como o "maestro", orquestrando o fluxo principal e delegando tarefas.
    * **`Menu`**: Responsável unicamente pela interface de texto e captura de opções.
    * **`Pet`**: Classe de modelo (POJO) que representa a entidade central do sistema.
    * **`GerenciadorDeArquivos`**: Centraliza todas as interações com o sistema de arquivos (leitura, escrita, deleção).
    * **`RealizadorCadastros`, `BuscadorDePets`, `AlteradorPets`, `DeletadorDePet`**: Classes de serviço, cada uma implementando a lógica de um caso de uso específico.

* **Programação Orientada a Objetos (POO):** O projeto utiliza encapsulamento para proteger os dados da classe `Pet`, e a coesão é mantida agrupando-se comportamentos relacionados em suas respectivas classes.

* **Reutilização de Código:** A lógica de validação (`RealizadorCadastros.validar()`) e a de busca (`BuscadorDePets.buscar()`) são reutilizadas pelas funcionalidades de Alteração e Deleção, evitando duplicação de código e garantindo consistência.

* **Tipagem Segura com Enums:** Os campos `Tipo` e `Sexo` são modelados como `Enums` (`TipoPets`, `SexoPets`), o que elimina "magic strings", previne erros de digitação e torna o código mais legível e seguro em tempo de compilação.

* **Gerenciamento de Estado:** O sistema carrega todos os registros de pets do disco para uma lista em memória (`List<Pet>`) na inicialização. Todas as operações (busca, alteração, deleção) são executadas de forma performática sobre esta lista. As operações de escrita (criar, alterar, deletar) são persistidas em disco para garantir a durabilidade dos dados entre as sessões.

## 🛠️ Detalhes Técnicos

* **Linguagem**: Java (desenvolvido com compatibilidade a partir do Java 8).
* **Persistência**: Arquivos de texto (`.txt`) com formato customizado.
* **APIs Java Utilizadas**:
    * **`java.nio.file` (NIO.2)**: Para uma manipulação moderna e robusta de arquivos e diretórios (`Path`, `Paths`, `Files`).
    * **`java.time`**: Para a geração de timestamps precisos e formatados para os nomes dos arquivos.
    * **`java.util.Scanner`**: Para a interface de usuário no console.
    * **`java.util.regex`**: Utilizada implicitamente no método `String.matches()` para validações complexas com Expressões Regulares.

## ⚙️ Configuração e Execução

### Pré-requisitos
* **JDK (Java Development Kit)**: Versão 8 ou superior.

### Estrutura
O projeto segue a estrutura padrão de projetos Java:
* `src/`: Contém todo o código-fonte.
* `formulario.txt`: Arquivo de configuração para as perguntas do cadastro.
* `pets_cadastrados/`: Diretório criado automaticamente para armazenar as fichas dos pets.

### Compilação e Execução (via Terminal)

1.  **Navegue até a raiz do projeto** (`SistemaDeCadastros`).

2.  **Compile o código-fonte:** O comando a seguir compila todas as classes `.java` e coloca os arquivos `.class` em um diretório de saída chamado `out`.
    ```bash
    javac -d out src/Main.java src/br/com/petcare/classes/*.java src/br/com/petcare/enums/*.java src/br/com/petcare/options/*.java
    ```

3.  **Execute a aplicação:** O comando a seguir inicia o programa, especificando que as classes compiladas estão no diretório `out`.
    ```bash
    java -cp out Main
    ```

## 🚀 Melhorias Futuras

A arquitetura modular do PetCare permite diversas expansões, como:
* **Testes Unitários:** Criar testes com JUnit para a classe `Pet` e os métodos de validação.
* **Interface Gráfica (GUI):** Substituir a interface de console por uma interface gráfica usando JavaFX ou Swing.
* **Banco de Dados:** Migrar a persistência de arquivos de texto para um banco de dados relacional (como H2, SQLite ou PostgreSQL) para permitir buscas mais complexas e eficientes.
* **Logs:** Implementar um sistema de logging (como o Log4j) para registrar as operações e facilitar a depuração.

## 👨‍💻 Autor
Igor Hermann Salgado