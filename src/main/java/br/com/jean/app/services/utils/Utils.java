package br.com.jean.app.services.utils;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.SqsException;

public class Utils {
	
	public static AwsCredentialsProvider getAWSCredentials() {
		return new AwsCredentialsProvider() {
			@Override
			public AwsCredentials resolveCredentials() {
				return new AwsCredentials() {
					@Override
					public String accessKeyId() {
						return System.getenv("AWS_ACCESS_KEY");
					}

					@Override
					public String secretAccessKey() {
						return System.getenv("AWS_SECRET_KEY");
					}
				};
			}
		};
	}
		
	public static SqsClient getSqsClient() {
		try {
			AwsCredentialsProvider credentialsProvider = getAWSCredentials();
			return SqsClient.builder().region(Region.US_EAST_1).credentialsProvider(credentialsProvider).build();
		} catch (SqsException ex) {
			System.err.println(ex.awsErrorDetails().errorMessage());
			return null;
		}
	}
	
	private static GetQueueUrlRequest getQueueUrlRequest() {
		return GetQueueUrlRequest.builder()
			// .queueName("fila-jean.fifo") // envia para fila fifo
			.queueName("Fila-JP") // envia para fila padrão
			.queueOwnerAWSAccountId(System.getenv("AWS_ACCOUNT_ID")).build();
	}

	public static GetQueueUrlResponse getQueueUrl(SqsClient sqsClient) {
		try {			
			GetQueueUrlRequest request = getQueueUrlRequest();
			return sqsClient.getQueueUrl(request);
		} catch (SqsException ex) {
			System.err.println(ex.awsErrorDetails().errorMessage());
			return null;
		}
	}
}
