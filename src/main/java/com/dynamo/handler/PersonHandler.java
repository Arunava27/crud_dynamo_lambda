package com.dynamo.handler;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dynamo.dto.PersonRequest;
import com.dynamo.dto.PersonResponse;


public abstract class PersonHandler implements RequestHandler<PersonRequest, PersonResponse>{
	
	private DynamoDB dynamoDb;
	private AmazonDynamoDB amazonDynamoDB;

    private Regions REGION = Regions.US_WEST_2;
    
    public static String DYNAMODB_TABLE_NAME = "Person";
    
	public abstract PersonResponse handleRequest(PersonRequest personRequest, Context context);
	
	private void initDynamoDbClient() {
		this.amazonDynamoDB  = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
		this.dynamoDb = new  DynamoDB(this.amazonDynamoDB);
	}
	
	public DynamoDB getClient() {
		if(dynamoDb == null) {
			initDynamoDbClient();
		}
		
		return dynamoDb;
	}
	public AmazonDynamoDB getDynamoClient() {
		if(this.amazonDynamoDB == null) {
			initDynamoDbClient();
		}
		
		return this.amazonDynamoDB;
	}
}
