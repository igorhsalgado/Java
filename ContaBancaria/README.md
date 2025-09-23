# **Sistema de Gerenciamento de Saldo Bancário Simples**

Este é um projeto Java simples que simula um sistema básico de gerenciamento de saldo bancário. Ele permite ao usuário consultar seu saldo, adicionar (receber) valores e transferir valores.

## **🚀 Funcionalidades**

* **Consulta de Saldo**: Visualize o saldo atual da conta.  
* **Recebimento de Valor**: Adicione um valor ao saldo da conta.  
* **Transferência de Valor**: Retire um valor do saldo da conta, com verificação de saldo insuficiente.  
* **Menu Interativo**: Navegação fácil através de opções numeradas.  
* **Encerramento do Programa**: Opção para sair do sistema.

## **⚙️ Como Usar**

Para rodar este programa em sua máquina, siga os passos abaixo:

1. **Pré-requisitos**: Certifique-se de ter o **Java Development Kit (JDK)** instalado (versão 8 ou superior é recomendada).  
2. **Obtenha o Código**:  
   * Se você clonou este repositório do GitHub, navegue até a pasta do projeto.  
   * Caso contrário, salve o código fornecido em um arquivo chamado Main.java.

Compile o Código:  
Abra um terminal ou prompt de comando, navegue até a pasta onde o arquivo Main.java está localizado e compile-o:  
javac Main.java

3. 

Execute o Programa:  
Após a compilação, execute o programa:  
java Main

4. 

### **Exemplo de Interação:**

\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*  
Dados iniciais do cliente:   
Nome:              Igor Hermann Salgado  
Tipo de conta:     Corrente  
Saldo inicial:     R$2500.0  
\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*  
    
    
Operações  
    
1- Consultar saldos  
2- Receber valor  
3- Transferir valor  
4- Sair  
    
Digite a opção desejada:   
1  
O saldo atual é de R$2500.0  
    
Operações  
    
1- Consultar saldos  
2- Receber valor  
3- Transferir valor  
4- Sair  
    
Digite a opção desejada:   
2  
Digite o valor a ser recebido:   
500  
Após receber o valor, o saldo passa a ser de R$3000.0  
    
Operações  
    
1- Consultar saldos  
2- Receber valor  
3- Transferir valor  
4- Sair  
    
Digite a opção desejada:   
3  
Digite o valor a ser transferiddo:   
1000  
O saldo atual, após a transferência é de R$2000.0  
    
Operações  
    
1- Consultar saldos  
2- Receber valor  
3- Transferir valor  
4- Sair  
    
Digite a opção desejada:   
4  
O programa foi encerrado.

## **🛠️ Tecnologias Utilizadas**

* **Java**: Linguagem de programação principal.  
* **java.util.Scanner**: Utilizado para ler a entrada de dados do usuário via console.

## **✨ Melhorias Futuras (Opcional)**

* **Tratamento de Erros de Entrada**: Adicionar validação robusta para entradas não numéricas (usando try-catch com InputMismatchException) para evitar que o programa falhe.  
* **Formatação de Moeda**: Melhorar a formatação dos valores monetários para garantir sempre duas casas decimais (ex: R$2500.00 em vez de R$2500.0).  
* **Persistência de Dados**: Atualmente, o saldo é redefinido a cada execução. Implementar a gravação e leitura do saldo em um arquivo (CSV, TXT) ou um banco de dados simples.  
* **Múltiplas Contas**: Expandir para gerenciar mais de uma conta ou cliente.  
* **Histórico de Transações**: Registrar todas as operações (depósitos, transferências) em um histórico.

## **🤝 Contribuição**

Sinta-se à vontade para abrir "issues" ou enviar "pull requests" se tiver sugestões de melhoria, encontrar algum problema ou quiser adicionar novas funcionalidades.

