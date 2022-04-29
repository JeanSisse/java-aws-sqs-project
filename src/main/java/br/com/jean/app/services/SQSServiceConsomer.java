package br.com.jean.app.services;

import java.util.List;

import br.com.jean.app.services.utils.Utils;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

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
		System.out.println("\nReceive messages");
		
		try {
			ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
					.queueUrl(queueUrl)
					.waitTimeSeconds(20) // Enable long polling on a message receipt.
					.maxNumberOfMessages(5).build();
			List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();
			return messages;
			
		} catch (SqsException ex) {
			System.err.println(ex.awsErrorDetails().errorMessage());
      System.exit(1);
		}
		return null;
	}

	public static void deleteMessages(SqsClient sqsClient, String queueUrl, List<Message> messages) {
		System.out.println("\nDelete Messages");
    // snippet-start:[sqs.java2.sqs_example.delete_message]
		try {
			for (Message message : messages) {
				DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
						.queueUrl(queueUrl)
						.receiptHandle(message.receiptHandle())
						.build();
				sqsClient.deleteMessage(deleteMessageRequest);
			}
			
		} catch (SqsException ex) {
			System.err.println(ex.awsErrorDetails().errorMessage());
      System.exit(1);
		}
	}
}
