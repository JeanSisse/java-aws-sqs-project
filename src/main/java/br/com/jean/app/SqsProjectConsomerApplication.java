package br.com.jean.app;

import br.com.jean.app.services.SQSServiceConsomer;

public class SqsProjectConsomerApplication {

	public static void main(String[] args) {
		System.out.println("Lendo mensagens ...");
    while(true){
        SQSServiceConsomer.messageReader();
    }
	}
}
