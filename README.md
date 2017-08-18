# xy-inc
##### Este projeto é responsável por criar APIs/projetos REST. Os serviços são criados de acordo com o desejo do usuário, tomando como base modelos.

---

### 1 - Requisitos Necessários:

* Java 8
* [MongoDb](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)
* [Gradle 2.13 (versão testada)](https://gradle.org)

**Instalação** dos requistos necessários:

* **MongoDb**

```sh
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0C49F3730359A14518585931BC711F9BA15703C6
echo "deb [ arch=amd64,arm64 ] http://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.4.list
sudo apt-get update
sudo apt-get install -y mongodb-org
sudo service mongod start
```

* **Gradle**

```sh
sudo add-apt-repository ppa:cwchien/gradle
sudo apt-get update
sudo apt-get install gradle
gradle -version
```

---

### 2 - Compilação e Execução do Projeto:
Acessar a home do projeto e  executar o seguinte comando:

```sh
gradle clean build bootRun
```

---

### 3 - Chamadas

Utilizado o **[JaSON](https://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwiXtfzx397VAhVFgpAKHV5_DCUQFggpMAA&url=https%3A%2F%2Fchrome.google.com%2Fwebstore%2Fdetail%2Fjason%2Foealdlhfjifhgbmjnenhkgffglaibojf&usg=AFQjCNHGx8x1QMCxvAIjd8GWcGd7bO2qXg)** extension do Chrome para testes de chamadas dos serviços rest.

---
### 4 - Gerenciamento da API

* **POST**

Para criação de um modelo é necessário realizar uma requisição POST na seguinte URL **http://localhost:8080/{modelo}** informando o nome do modelo e seus atributos.

Onde os tipos esperados dos atributos são: **boolean, char, character, date, double, float, int, integer, long, string, decimal**

Exemplo:

```json
{
	"nome": "Pessoa",
	"atributos": {
		"sexo": "String",
		"nome": "String",
		"idade": "int",
                "casado": "boolean"
	}
}
```

* **POST**

Para definição dos valores dos atributos criados é necessário realizar uma requisição POST na seguinte URL **http://localhost:8080/api/{modelo}** informando o nome dos atributos criados e seus valores.

Exemplo:
 
```json
{
		"sexo": "Masculino",
		"nome": "Danilo",
		"idade": 25,
       "casado": true
}
```

---
* **GET** http://localhost:8080/{modelo} - Lista todos os modelos cadastrados

Retorno:

```json
[
  {
    "id": "5995cab1c05e6757d98a1f13",
    "nome": "Pessoa",
    "atributos": {
      "idade": "int",
      "nome": "String",
      "casado": "boolean",
      "sexo": "String"
    }
  },
  {
    "id": "5995cfa1c05e6757d98a1f15",
    "nome": "Teste2",
    "atributos": {
      "idade": "int",
      "nome": "String",
      "casado": "boolean",
      "sexo": "String"
    }
  }
]
```

* **GET** - http://localhost:8080/{modelo}/{id} - Lista um modelo específico correspondente ao id cadastrado

Retorno:

```json
{
    "id": "5995cfa1c05e6757d98a1f15",
    "nome": "Teste2",
    "atributos": {
      "idade": "int",
      "nome": "String",
      "casado": "boolean",
      "sexo": "String"
}
```

* **GET** - http://localhost:8080/api/{modelo}/{id} - Lista os valores dos atributos de um modelo específico correspondente ao id cadastrado

Retorno: 

```json
{
  "sexo": "Masculino",
  "nome": "Danilo",
  "idade": 25,
  "casado": true,
  "_id": "2ec18867-f4a5-4c31-9973-ac1087ebb8f4"
}
```

---
* **DELETE** - http://localhost:8080/{modelo}/{id} - Para excluir um modelo cadastrado

Retorno:

```json
{
    "id": "5995cfa1c05e6757d98a1f15",
    "nome": "Teste2",
    "atributos": {
      "idade": "int",
      "nome": "String",
      "casado": "boolean",
      "sexo": "String"
}
```

* **DELETE** - http://localhost:8080/api/{modelo}/{id} - Para excluir os valores dos atributos do modelo criado

Retorno:

```json
{
  "sexo": "Masculino",
  "nome": "Danilo",
  "idade": 25,
  "casado": true
}
```

---


### 5 - Cobertura de Testes

Para verificar a cobertura dos testes unitários que foram implementados e executados, basta acessar o a home do projeto após a aplicação ter sido executada conforme **passo 2** e abrir o arquivo **/build/reports/tests/index.html**

---
### 6 - Próximos Passos

* Utilizar o framework swagger para documentação e especificação dos serviços.
* Utilizar o docker para implantação da aplicação.
* Implementar uma cobertura maior de testes.
