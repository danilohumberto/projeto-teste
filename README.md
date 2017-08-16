# xy-inc
Backend as a Service

## Pré Requisitos
* Java 8
* [MongoDb](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)
* [Gradle 2.x](https://gradle.org)

##### Instalação do MongoDb

```shell
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0C49F3730359A14518585931BC711F9BA15703C6
echo "deb [ arch=amd64,arm64 ] http://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.4.list
sudo apt-get update
sudo apt-get install -y mongodb-org
sudo service mongod start
```
##### Instalação do Gradle

```shell
sudo add-apt-repository ppa:cwchien/gradle
sudo apt-get update
sudo apt-get install gradle
gradle -version
```
___________________________________________________

## Compilação e Execução

Acessar a home do projeto e  executar o seguinte comando:

```shell
gradle clean build bootRun
```
