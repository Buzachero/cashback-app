# Aplicação de cálculo de *cashback*
Aplicação que registra vendas de discos, utilizando cálculo de *cashback*.

## Tecnologias utilizadas
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [H2](https://www.h2database.com/html/main.html)

## Requerimentos
Para realizar o build do projeto, é necessário:
* JDK 1.8
* Maven

## Configuração

A configuracão da aplicação pode ser realizada em dois arquivos:
1. *application-test.properties* 
2. *application-prod.properties* 

Na execução com **application-test.properties**, as informações de discos e as vendas foram numeradas por nome *(Disco 1,2,3.... Venda 1,2,3...)*, 
ou seja, a origem desses dados é da própria aplicação.

Na execução com **application-prod.properties**, as informações de discos e as vendas são oriundas da [Spotify Web API](https://developer.spotify.com/documentation/web-api/), 
ou seja, a origem desses dados é externa (solução ainda não implementada).

A tabela de cashback pode ser configurada, como no exemplo abaixo:
```
param.cshback_pop_domingo = 0.25 
param.cshback_mpb_segunda = 0.05 
```
O primeiro parâmetro configura 25% de cashback para disco POP se comprado no domingo.

Já o segundo configura 5% de cashback para disco MPB se comprado na segunda.

## Execução

### GET	
* Para obter informações do disco pelo seu identificador:
```
GET /discos/<id>
``` 
* Para obter informações dos discos de forma paginada:
```
GET /discos/page?genero=[POP,MPB,CLASSIC,ROCK]&pagina=[1,2,3...]&itensPorPagina=[1,2,3...]
```
* Para obter informações da venda pelo seu identificador:
```
GET /vendas/<id>
```
* Para obter informações das vendas de forma paginada dado um intervalo de datas:
```
/vendas/page?dataVendaInicial=dd/MM/yyyy&dataVendaFinal=dd/MM/yyyy&pagina=[1,2,3...]&itensPorPagina=[1,2,3...]
```
#### Exemplos com GET:	
```
http://localhost:8080/discos/1
http://localhost:8080/vendas/1
http://localhost:8080/discos/page?genero=ROCK&pagina=0&itensPorPagina=10
http://localhost:8080/vendas/page?dataVendaInicial=01/04/2019&dataVendaFinal=07/04/2019&itensPorPagina=5
```
### POST
* Inserir uma nova venda na aplicacao
```
/vendas 
```
#### Exemplo com POST:
```
{
    "itens": [
        {
            "quantidade": 1,
            "disco": {"id": 1 }
        },
        {
            "quantidade": 2,
            "disco": {"id": 10 }
        }
   ]
}
```





