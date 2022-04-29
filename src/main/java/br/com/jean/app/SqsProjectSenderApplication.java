package br.com.jean.app;

import java.time.LocalDate;

import br.com.jean.app.services.SQSServiceSender;

public class SqsProjectSenderApplication {

	public static void main(String[] args) {
		SQSServiceSender.sendMessage("Messagem de teste " + LocalDate.now());
	}

}
