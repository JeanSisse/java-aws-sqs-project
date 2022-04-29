package br.com.jean.app.services;

import java.util.List;

import br.com.jean.app.services.utils.Utils;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

public class SQSServiceConsomer {

	public static void messageReader() {
				
		SqsClient sqsClient = Utils.getSqsClient();
		GetQueueUrlResponse createResult = Utils.getQueueUrl(sqsClient);

		List<Message> messages = receiveMessages(sqsClient, createResult.queueUrl());
		
		for (Message message : messages) {
			System.out.println("Mensagem: " + message.body());
		}

		deleteMessages(sqsClient, createResult.queueUrl(), messages);

		sqsClient.close();
	}

	public static List<Message> receiveMessages(SqsClient sqsClient, String queueUrl) {
		ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder().queueUrl(queueUrl).waitTimeSeconds(20)
				.maxNumberOfMessages(5).build();
		List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
		return messages;
	}

	public static void deleteMessages(SqsClient sqsClient, String queueUrl, List<Message> messages) {
		for (Message message : messages) {
			DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder().queueUrl(queueUrl)
					.receiptHandle(message.receiptHandle()).build();
			sqsClient.deleteMessage(deleteMessageRequest);
		}
	}
}
