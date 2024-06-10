# Sistemas-de-Troca
Uma plataforma web de sistemas de troca de usuario

# Back-End
## Execução dos containers
Executar no terminal e na pasta root do projeto (Vai iniciar a aplicação - até então só temos o banco de dados mysql):
~~~bash
docker compose up -d
~~~

## Execução do Gerenciador de container (Dica)
~~~bash
docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:latest
~~~