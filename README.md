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

- na raiz do projeto rodar o comando:

```shell
./buld.sh
```

- na raiz do projeto rodar o programa consomer:

```shell
./start_consomer.sh
```

- (de preferência em terminal diferente) na raiz do projeto rodar o programa producer/sender:

```shell
./start_producer.sh
```
