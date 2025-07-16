# **Sonora**

Este é o projeto **Sonora**, uma aplicação Java que demonstra alguns conceitos fundamentais de programação orientada a objetos (POO) e manipulação de classes, métodos e objetos.

## **Sobre o Projeto**

O **Sonora** é um exemplo simples, mas eficaz, de como classes se relacionam e interagem em Java. Ele apresenta:

* **Classes:** Musica, Podcast, Audio, MinhasPreferidas.  
* **Herança:** Musica e Podcast herdam de Audio, mostrando como compartilhar atributos e comportamentos comuns.  
* **Polimorfismo:** Possibilidade de tratar objetos de diferentes classes (que herdam de uma mesma superclasse) de forma uniforme.  
* **Encapsulamento:** Propriedades dos áudios são controladas por métodos (getters e setters).  
* **Métodos Personalizados:** Métodos como curte(), reproduz() e inclui() que simulam interações com os áudios.

## **Como Rodar o Projeto**

Para executar o **Sonora** no seu ambiente local, siga estes passos:

1. **Clone o Repositório:**  
   git clone https://github.com/igorhsalgado/Java.git

2. **Navegue até o Diretório do Projeto:**  
   cd Java/Sonora/sonora

3. Compile as Classes Java:  
   Certifique-se de ter o Java Development Kit (JDK) instalado. Abra o terminal na pasta sonora e compile os arquivos .java:  
   javac \*.java

4. Execute a Aplicação:  
   Após a compilação, você pode rodar a classe principal:  
   java Principal

## **Funcionalidades e Exemplos**

O projeto simula a reprodução e curtidas de músicas e podcasts. Na classe Principal, você verá exemplos de como instanciar objetos Musica e Podcast, manipular seus atributos e interagir com eles.  
// Exemplo de uso na classe Principal.java  
Musica minhaMusica \= new Musica();  
minhaMusica.setTitulo("Nome da Música");  
minhaMusica.setArtista("Nome do Artista");  
// ... outros atributos e chamadas de métodos

Podcast meuPodcast \= new Podcast();  
meuPodcast.setTitulo("Nome do Podcast");  
meuPodcast.setHost("Nome do Host");  
// ... outros atributos e chamadas de métodos

## **Contribuições**

Sinta-se à vontade para explorar, sugerir melhorias ou até mesmo abrir um Pull Request\! Este projeto é um ótimo ponto de partida para quem está aprendendo POO em Java.

## **Autor**

* **Igor Salgado** \- [igorhsalgado](https://www.google.com/search?q=https://github.com/igorhsalgado)