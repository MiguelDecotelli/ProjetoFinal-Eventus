![Logo](https://i.imgur.com/mOkNOal.png)

# Eventus

| ![Apache](https://img.shields.io/badge/apache-%23D42029.svg?style=for-the-badge&logo=apache&logoColor=white) | ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white) | ![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white) | ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) | ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) | ![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens) |
|---------|---------|---------|---------|---------|---------|
| ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) | ![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB) | ![React Hook Form](https://img.shields.io/badge/React%20Hook%20Form-%23EC5990.svg?style=for-the-badge&logo=reacthookform&logoColor=white) | ![React Router](https://img.shields.io/badge/React_Router-CA4245?style=for-the-badge&logo=react-router&logoColor=white) | ![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) | ![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white) |
| ![Vite](https://img.shields.io/badge/vite-%23646CFF.svg?style=for-the-badge&logo=vite&logoColor=white) 



## 🔗Índice

**1.** [Descrição Geral](#descrição-geral)

**2.** [Principais Funcionalidades](#principais-funcionalidades)

**3.** [Tecnologias Utilizadas](#tecnologias-utilizadas)

**4.** [Instalações e Configurações](#instalações-e-configurações)

**5.** [Rotas](#rotas)

**6.** [Organização do Projeto](#organização-do-projeto)

**7.** [Autores](#autores)

**8.** [Licença](#licença)

# 📃Descrição Geral

**Eventus** é uma plataforma desenvolvida para conectar pessoas a experiências inesquecíveis, facilitando a busca e compra de ingressos para eventos de diversos tipos, como shows, palestras, feiras gastronômicas e festividades locais. 

Atualmente, enfrentamos **desafios** ao procurar eventos relevantes e adquirir ingressos de forma prática e segura. Dessa forma, **Eventus** surge para oferecer uma interface simples e intuitiva, permitindo visualizar e pesquisar eventos com mais praticidade e filtros eficazes, garantindo um processo de cadastro, login e compra ágil e seguro. 

Este projeto foi criado como requisito para conclusão do curso de **Desenvolvimento FullStack da +PraTi**.

## 🚀Principais Funcionalidades

- Cadastro de usuários
- Navegação e busca de eventos
- Visualização de detalhes dos eventos
- Compra de ingressos
- Contato com o time de suporte
- Autenticação e segurança




# 💻Tecnologias Utilizadas

## Linguagens de Programação
- **Java (17)**: Usada para o desenvolvimento do back-end.

## Ferramentas de Build e Performance
- **Apache Maven**: Gerenciador de dependências e build do projeto.
- **Docker**: Plataforma para containers em ambiente de desenvolvimento e produção.
- **Maven Wrapper**: Ferramenta para facilitar o build e gestão de dependências.
- **Vite**: Ferramenta para configuração rápida e otimizada de aplicações React.

## Bibliotecas e Frameworks Front-end
- **Axios**: Biblioteca para requisições HTTP.
- **Bootstrap**: Framework para estilização das páginas.
- **Drag-and-Drop**: Biblioteca para implementação de arrastar e soltar elementos (via `@hello-pangea/dnd`).
- **React**: Biblioteca para a criação de interfaces de usuário.
- **React DOM**: Biblioteca para renderização do DOM no React.
- **React Router**: Biblioteca para gerenciamento de navegação entre páginas.
- **React Hook Form**: Biblioteca para gerenciamento de formulários com validação.
- **React Icons**: Biblioteca de ícones para componentes React.
- **React OAuth Google**: Biblioteca para autenticação via Google.
- **Yup**: Biblioteca para validação de dados.

## Bibliotecas e Frameworks Back-end
- **Lombok**: Biblioteca para redução de código.
- **Spring Boot**: Framework principal para desenvolvimento da API.
- **Spring Boot OAuth2 Resource Server**: Framework para configuração de autenticação e autorização com OAuth2.
- **Spring Security**: Framework de segurança da aplicação, incluindo autenticação e autorização.
- **Spring Web**: Framework para criação de endpoints RESTful.

## Autenticação e Segurança
- **JWT (JSON Web Tokens)**: Padrão para autenticação e autorização via tokens.
- **jwt-jackson**: Integração para manipulação de tokens JWT.

## Banco de Dados
- **MySQL**: Sistema de gerenciamento de banco de dados relacional.
- **MySQL Connector**: Driver JDBC para conexão com o banco de dados.

## Ferramenta de Documentação
- **Swagger**: Ferramenta para documentação da API REST.

## Ferramentas de Testes
- **JUnit Jupiter API**: Ferramenta para criação de testes automatizados para validação do back-end.
- **Spring Boot Starter Test**: Ferramentas para testes unitários e de integração.



# 🕹 Instalações e Configurações

## Instalações

### Vite

```bash
$ npm create vite@latest eventus
$ cd my-app
$ npm install
$ npm run dev
```
[Documentação Vite](https://pt.vitejs.dev/guide/)

### React Hook Form

```bash
$ npm install react-hook-form yup @hookform/resolvers
```
[Documentação React Hook Form](https://react-hook-form.com/get-started)

### React Router DOM

```bash
$ npm install react-router-dom
```
[Documentação React Router](https://reactrouter.com/en/main)

### React Icons

```bash
$ npm install react-icons
```
[Documentação React Icons](https://react-icons.github.io/react-icons/icons/fa6/)

### Axios

```bash
$ npm install axios
```
[Documentação Axios](https://axios-http.com/ptbr/docs/intro)

### JSON Server

```bash
$ npm install json-server
$ npx json-server db.json --watch
```

Adicione ao `package.json`:

```json
"scripts": {
  "dev:backend": "npx json-server db.json --watch --port 3333"
}
```

### Drag-and-Drop

```bash
npm install @hello-pangea/dnd
```
[Documentação Drag-and-Drop](https://dnd.hellopangea.com/?path=/docs/welcome--docs)

---

# Configurações

### Ambiente Front-end

Inicializar o projeto localmente:

```bash
$ npm run dev
```

### Ambiente Back-end

1. **Configuração do Banco de Dados:**
   Antes de iniciar a API, configure o banco de dados no arquivo `application.properties`, localizado em `src/main/resources`.

2. **Inicialização da API:**
   - **No IntelliJ:** Abra o projeto, localize a classe `EventusApplication.java` e clique em **Run**.
   - **No Terminal:**
     ```bash
     $ ./mvnw spring-boot:run
     ```

3. **Documentação da API:**
   Após inicializar a aplicação, acesse os seguintes links para explorar e testar os endpoints da API:
   - [Swagger](http://localhost:8080/swagger-ui/index.html#/)
   - [API Docs](http://localhost:8080/v3/api-docs)



# 🚧Rotas

| Página           | Descrição                                                                                           | Rota         |
|-------------------|---------------------------------------------------------------------------------------------------|--------------|
| Início           | Página inicial com carrossel de eventos em destaque, cards com eventos e informações sobre a plataforma. | `.../`          |
| Próximos Eventos | Página de próximos eventos responsável por exibir uma lista deles, permitindo buscar, ordenar e paginar os itens. | `.../nextEvents` |
| Novidades        | Página com listagem das novidades.                                                                 | `.../news`      | 
| Histórico          | Página com histórico de eventos passados.                   | `.../history`   |
| Contato          | Página com formulário para envio de e-mail e informações de contato da Eventus.                   | `.../contact`   |
| Cadastrar        | Página de cadastro de usuário, incluindo formulário para captação de dados.                       | `.../signup`    |
| Login            | Página de login com formulário para verificar dados do usuário ou autenticação via Google.         | `.../login`     | 
| Detalhes            | Página com todos os detalhes de um evento.         | `.../eventdetails`     |
 Compra            | Página com formulário para compra de ingressos.                                                         | `.../purchasesimulation`         |
 | Erro             | Página de erro para rotas desconhecidas.                                                          | `.../*`         |



# 📂 Organização do Projeto

## Organização Front-end

### Estrutura de Pastas e Arquivos

- **Index:** Configurações gerais do projeto.
- **Db:** Base de dados fake simples para cadastro, login e listagem de eventos.
- **ESLint:** Correção de problemas no código.
- **Main:** Configurações do React.
- **Main Routes:** Configura as rotas do projeto.
- **Global:** Estilos globais da aplicação.
- **Components:** Componentes da aplicação: CardEvent, Carousel, Footer, Imagem Home01, Imagem Home02, Input, NavBar, PurchaseForm, TextArea, TextHome e TextTitle.
- **Context:** Gerenciamento do estado do usuário e dos eventos.
- **Imag:** Armazena arquivos de mídia.
- **Pages:** Estrutura das páginas da aplicação: EventDetails, Contact, History, Home, Login, News, NextEvents, PurchaseSimulation e SingUp.
- **Services:** Configura URL base, timeout e cabeçalhos padrão.
- **Utils:** Simplifica as chamadas à API com a função `makeRequest`.

---

## Organização Back-end

### Estrutura de Pastas e Arquivos

- **Pom:** Gerencia as informações do projeto Maven.
- **Application:** Inicializa a aplicação.
- **Controllers:** Gerencia operações com endereços, autenticação, cidades, eventos, ingressos e usuários.
- **DTOs:** Encapsula informações de autenticação, cidades, eventos, novos eventos, registro, ingressos e usuários.
- **Models:** Representa as entidades de  endereços, cidades, eventos, ingressos e usuários.
- **Repository:** Gerencia o acesso e as operações de manipulação de dados relacionadas a endereços, cidades, eventos, igressos e usuários.
- **Security:** Configura as segurança com JWT e Spring Security.
- **Service:** Lógica de negócios, como endereços,autenticação, autorização, cidades, eventos, ingressos e usuários.
- **Resources:** Configurações essenciais da aplicação, como a conexão com o banco de dados.
- **Testing:** Testes. 
- **DB:** Configurações do banco de dados com MySQL.
- **Docker Compose:** Configuração de contêineres Docker.



## 🔎Mais Detalhes

Para informações adicionais e especificações detalhadas sobre a estrutura e funcionalidade do projeto, consulte a [documentação oficial](https://www.canva.com/design/DAGWZmTus0Q/4OZC4ry7UNbouwCSsdeB2g/edit?utm_content=DAGWZmTus0Q&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton).



# 👩‍💻Autores

| ![Andressa](https://i.imgur.com/uutCApA.png)  | ![Denis](https://i.imgur.com/gVrHJBU.png) | ![Flávio](https://i.imgur.com/02pud5K.jpeg) | ![Jeferson](https://i.imgur.com/8amiHGD.jpeg)
|---------|---------|---------|---------|
**Andressa Conrado**| **Dênis Rocha** | **Flávio Soares** | **Jeferson Lanius** | 
[LinkedIn](https://www.linkedin.com/in/andressavcon/) - [GitHub](https://github.com/Andressavcon) | [LinkedIn](https://www.linkedin.com/in/deniscrocha/) - [GitHub](https://github.com/deniscrocha) | [LinkedIn](https://www.linkedin.com/in/flavsneves/) - [GitHub](https://github.com/FlavioNevesjc) | [LinkedIn](https://www.linkedin.com/in/jeferson-daniel-lanius-49b5a41b7/) - [GitHub](https://github.com/JefersonLanius)

| ![Luara](https://i.imgur.com/2hZeEjA.jpeg)  | ![Matteo](https://i.imgur.com/Cny9ub0.jpeg) | ![Miguel](https://i.imgur.com/E4dCORw.jpeg) | ![Rafael](https://i.imgur.com/OkzrCev.jpeg)
|---------|---------|---------|---------|
**Luara Andrade**| **Matteo de Vargas** | **Miguel Angelo** | **Rafael Dal Forno** | 
[LinkedIn](https://www.linkedin.com/in/luara-andrade/) - [GitHub](https://github.com/luaara) | [LinkedIn](http://linkedin.com/in/matteo-de-vargas-b51735339) - [GitHub](http://github.com/MatteoSDV) | [LinkedIn](https://www.linkedin.com/in/miguel-angelo-a-decotelli-silva/) - [GitHub](https://github.com/MiguelDecotelli/) | [LinkedIn](https://www.linkedin.com/in/rafaeldalforno/) - [GitHub](https://github.com/rafaeldalforno)

# ⚖Licença

Distribuído sob a [Licença Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0).





