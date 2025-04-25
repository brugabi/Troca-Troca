# 🛒 Sistema de Trocas entre Usuários

## Descrição
Aplicação web onde usuários podem cadastrar produtos e anunciar trocas com outros usuários. O sistema simula uma plataforma de negociação, permitindo o envio e gerenciamento de propostas. Possui autenticação e interface interativa.

## Tecnologias Utilizadas
- **Backend**: Java, Spring Boot  
- **Frontend**: React  
- **Banco de Dados**: MySQL  
- **Containerização**: Docker  

## Funcionalidades
- Cadastro e login de usuários com autenticação.
- Criação, visualização e exclusão de anúncios.
- Envio de propostas de troca entre usuários.
- Aceitação ou recusa de propostas.
- Interface em React para interação com as funcionalidades do sistema.
- Banco de dados containerizado com Docker para ambiente de desenvolvimento.

## Como Executar
1. Clone o repositório.
2. Inicie o container MySQL com Docker:
   ```bash
   docker-compose up -d
   ```
3. Configure o `application.properties` com as credenciais do banco.
4. Execute a aplicação Spring Boot.
5. Na pasta do frontend (`/frontend`), rode:
   ```bash
   npm install
   npm start
   ```

## Observações
- É necessário ter Docker e Node.js instalados.
- O banco de dados inicializa automaticamente com as tabelas necessárias.
