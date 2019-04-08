###################################
APLICACAO DE CALCULA DE CASHBACK
###################################
_______________________
BUILD E EXECUCAO

Antes de realizar o build do projeto, é necessário:
- JRE 1.8
- Maven (versao testada: 3.5.2)

Para compilar o projeto, execute o comando:
mvn clean install

Para executá-lo, entre no diretorio "target" e execute o comando:
java -jar cashback-app-1.0.0.jar

Pronto! O servidor backend estará executando na porta 8080

_______________________
PARAMETROS DO PROJETO

A configuracão da aplicação pode ser realizada em dois arquivos:
1. application-test.properties 
2. application-prod.properties 

A escolha de qual usar é configurada no arquivo application.properties, na variavel spring.profiles.active

No cenário do primeiro properties, as informações de discos e as vendas foram numeradas por nome (Disco 1,2,3.... Venda 1,2,3...), 
ou seja, a origem desses dados é da própria aplicação.

No cenário do segundo properties, as informações de discos e as vendas são oriundas da web api do Spotify, 
ou seja, a origem desses dados é externa.

No properties também pode ser configurado a tabela de cashback. 
Exemplo:
param.cshback_pop_domingo = 0.25 (25% de cashback para disco POP se comprado no domingo)
param.cshback_mpb_segunda = 0.05 (5% de cashback para disco MPB se comprado na segunda)

_______________________
REALIZACAO DOS TESTES

http://<ip_do_servidor>:<porta_servidor>
GET 
/discos/{id} : para obter informações do disco pelo id
/discos/page : para obter informações dos discos de forma paginada
	parametros que podem ser utilizados:
	genero=[POP,MPB,CLASSIC,ROCK] - mandatório
	pagina=[1,2,3...]
	itensPorPagina=[1,2,3...]
GET
/vendas/{id} : para obter informações da venda pelo id
/vendas/page : para obter informações das vendas de forma paginada para um intervalo de datas
	parametros que podem ser utilizados:
	dataVendaInicial=[dd/MM/yyyy] - mandatório
	dataVendaFinal=[dd/MM/yyyy]	- mandatório
	pagina=[1,2,3...]
	itensPorPagina=[1,2,3...]
	
EXEMPLOS GET
http://localhost:8080/discos/1
http://localhost:8080/vendas/1
http://localhost:8080/discos/page?genero=ROCK&pagina=0&itensPorPagina=10
http://localhost:8080/vendas/page?dataVendaInicial=01/04/2019&dataVendaFinal=07/04/2019&itensPorPagina=5
	
POST
/vendas : inserir uma nova venda na aplicacao
Exemplo HTTP body para POST:
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






