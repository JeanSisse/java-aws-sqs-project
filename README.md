# Projeto básico de AWS SQS para produção/envio e consumo de mensagens

# como rodar
- configurar o seu .bash_profile ou .bashrc:

```shell
code ~/.bash_profile
export AWS_ACCESS_KEY="SUA_KEY"
export AWS_SECRET_KEY="SEU_SECRET"
export AWS_ACCOUNT_ID="SEU_ID_DE_ACESSO"
source ~/.bash_profile
```

# alterar o nome da fila na classe Utils:

 - Para fila fifo: nome-da-fila.fifo;
 - descomentar a linha contendo ".messageGroupId" na classe SQSServiceSender (deixar comentado caso envio seja para fila padrão)
 - Para fila padrão: nome-da-fila

# na raiz do projeto rodar o comando:

```shell
./buld.sh
./start_consomer.sh
```

- (de preferência em terminal diferente) na raiz do projeto rodar o programa producer/sender:

```shell
./start_producer.sh
```
