# Spring security basic auth
Projeto basico usando Spring Security, usando o Basic Auth

## Rota livre para teste
`http://localhost:8080/user/livre`

## Rota bloqueada, somenet USER pode acessar
`http://localhost:8080/user/userAuth`

## Rota bloqueada, somenet ADMIN pode acessar
`http://localhost:8080/user/userAdmin`

## Rota para cadastro
`http://localhost:8080/user/register`
~~~JSON
{
  "username":"user",
  "password":"senha",
  "role":"USER"
}
~~~
>Role user devem ser em MAIUSCULA, exemplo: USER, ADMIN. 
