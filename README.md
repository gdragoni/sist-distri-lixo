# Sis Lixo 🗑
Trabalho de Sistemas Distribuídos - Faculdade FHO Uniararas 🇧🇷

# RESTful + MQTT
Trabalho desenvolvido em **Java**

O sistema utiliza dois tipo de protocolos de comunicação, RESTful e MQTT.

## RESTful
Realiza a comunicação entre o cliente e o banco de dados, através das requisições, o cliente consegue visualizar, criar, alterar e deletar os dados do dados do banco.

### API Restful
<details>
  <summary>Lixeira</summary>

- **GET**

  * Todas: `http://localhost:8080/restful/webresources/lixeira`
  * Por tipo: `http://localhost:8080/restful/webresources/lixeira?tipo=tipoDaLixeira`
  * Por id: `http://localhost:8080/restful/webresources/lixeira/detail/idDaLixeira`

- **POST**

  * Criar: `http://localhost:8080/restful/webresources/lixeira`
    ```
    Body: {
      "id": Integer,
      "lat": Double,
      "lng": Double,
      "tipo": String,
      "ambiente": String
    }
  
- **PUT**

  * Editar: `http://localhost:8080/restful/webresources/lixeira`
    ```
    Body: {
      "id": Integer,
      "lat": Double,
      "lng": Double,
      "tipo": String,
      "ambiente": String
    }
  
- **DELETE**

  Deletar: `http://localhost:8080/restful/webresources/lixeira/idDaLixeira`
</details>

---

<details>
  <summary>Funcionário</summary>

  - **GET**

    * Todos: `http://localhost:8080/restful/webresources/funcionario`
    * Por id: `http://localhost:8080/restful/webresources/funcionario/detail/idDoFuncionario`

  - **POST**

    * Criar: `http://localhost:8080/restful/webresources/funcionario`
      ```
      Body: {
        "id": Integer,
        "nome": String
      }

  - **PUT**

    * Editar: `http://localhost:8080/restful/webresources/funcionario`
      ```
      Body: {
        "id": Integer,
        "nome": String
      }

  - **DELETE**
  
    * Deletar: `http://localhost:8080/restful/webresources/funcionario/idDoFuncionario`
</details>
 
 ---

<details>
  <summary>Eventos</summary>

  - **GET**
    * Todas: `http://localhost:8080/restful/webresources/evento`

      **Formato da data**: yyyy-MM-dd
    * Por data: `http://localhost:8080/restful/webresources/evento/data`
    * Por periódo: `http://localhost:8080/restful/webresources/evento/dataInicial/dataFinal`

  - **POST**
    * Criar: `http://localhost:8080/restful/webresources/evento`
      ```
      Body: {
        "id_lixeira": Integer,
        "id_usuario": Integer,
        "descricao": String
      }

  - **PUT**
    * Editar: `http://localhost:8080/restful/webresources/evento`
      ```
      Body: {
        "id_evento": Integer,
        "id_lixeira": Integer,
        "id_usuario": Integer,
        "descricao": String
      }

  - **DELETE**
    * Deletar: `http://localhost:8080/restful/webresources/evento/idDoEvento`
</details>

  ---
  
<details>
<summary>Historico Capacidade</summary>

  - **GET**
    * Todas: `http://localhost:8080/restful/webresources/historicoCapacidade`

      **Formato da data**: yyyy-MM-dd
    * Por data: `http://localhost:8080/restful/webresources/historicoCapacidade/data`
    * Por periódo: `http://localhost:8080/restful/webresources/historicoCapacidade/dataInicial/dataFinal`

  - **POST**
    * Criar: `http://localhost:8080/restful/webresources/historicoCapacidade`
      ```
      Body: {
        "id_lixeira": Number,
        "capacidade": Double,
      }

  - **PUT**
    * Editar: `http://localhost:8080/restful/webresources/historicoCapacidade`
      ```
      Body: {
        "id_historico_capacidade": Number,
        "id_lixeira": Number,
        "capacidade": Double,
      }

  - **DELETE**
    * Deletar: `http://localhost:8080/restful/webresources/historicoCapacidade/idDoHistorico`
</details>

  ---
  
<details>
<summary>Historico Tampa</summary>

  - **GET**
    * Todas: `http://localhost:8080/restful/webresources/historicoTampa`

      **Formato da data**: yyyy-MM-dd
    * Por data: `http://localhost:8080/restful/webresources/historicoTampa/data`
    * Por periódo: `http://localhost:8080/restful/webresources/historicoTampa/dataInicial/dataFinal`

  - **POST**
    * Criar: `http://localhost:8080/restful/webresources/historicoTampa`
      ```
      Body: {
        "id_lixeira": Number,
        "valor": Boolean,
      }

  - **PUT**
    * Editar: `http://localhost:8080/restful/webresources/historicoTampa`
      ```
      Body: {
        "id_historico_tampa": Number,
        "id_lixeira": Number,
        "valor": Boolean,
      }

  - **DELETE**
    * Deletar: `http://localhost:8080/restful/webresources/historicoTampa/idDoHistorico`
</details>

## MQTT
Realiza a comunicação entre o provedor (sensor) -> broker -> REST -> Banco de dados e cliente -> broker -> dados, através de tópicos.
Após cadastrar o sensor no sistema, o mesmo é incrito em um tópico no  **mosquitto broker**, esse tópico por padrão foi definido como **"topic/idDaLixeira"**, no tópico é enviado o histórico da tampa, histórico de capacidade e a ação de esvaziar lixeira, podendo ser implementado inúmeras ações e inseridas no tópico. Para simular foi definido alguns parâmetros.
- Tampa:  Abrirá de 2 em 2 minutos, publicando no broker a ação e gravando no banco de dados chamando a API RESTful descrita acima.

- Capacidade: Irá crescer em 0.1 de 10 em 10 minutos até atingir o valor 1, voltando depois para o valor 0 e realizando o loop novamente, assim publicando no broker e gravando no banco de dados chamando a API RESTful descrita acima.

**Ação de esvaziar lixeira**
Para tal ação é necessário instalar o client em um dispositivo na lixeira que tenha um leitor RFID ou teclado digitar o id do usuário, assim quando o funcionário digitar/passar o RFID automaticamente será criado um evento como "Esvaziou a lixeira", publicado no broker e gravando no banco de dados chamando a API RESTful descrita acima.

**No MQTT tem 2 pacotes**

1. Pacode do cliente
  
    - Este serviço deve ser utilizado para monitorar as lixeiras através do cadastro no broker, ou seja o cliente vai digitar ou passar seu RFID (id), digitar qual tópico deseja se cadastrar.
    
      **Lembrando que o tópico é gerado pot topic/idDaLixeira**

    Após isso o mesmo terá acesso através do broker a todos os eventos publicados daquela lixeira.


2. Pacode do provedor

    - Este serviço deve ser utilizado pelo dispositivo que está na lixeira e conectado diretamente com os sensores, a onde quando o sensor emitir um sinal o memso vai acionar a função correspondente do sensor e realizar a ação, publicando no evento e gravando a ação no banco.
