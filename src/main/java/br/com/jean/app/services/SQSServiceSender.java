package br.com.jean.app.services;

import br.com.jean.app.services.utils.Utils;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class SQSServiceSender {
	public static void sendMessage(String message) {
		SqsClient sqsClient = Utils.getSqsClient();
		GetQueueUrlResponse createResult = Utils.getQueueUrl(sqsClient);
		
		SendMessageRequest sendMsgRequest = SendMessageRequest.builder().queueUrl(createResult.queueUrl())
				// .messageGroupId("Pedidos") // Para filas fifo
				.messageBody(message).build();
		sqsClient.sendMessage(sendMsgRequest);

		sqsClient.close();
	}
}
