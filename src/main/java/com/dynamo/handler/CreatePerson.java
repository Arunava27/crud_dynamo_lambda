package com.dynamo.handler;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.dynamo.dto.PersonRequest;
import com.dynamo.dto.PersonResponse;

public class CreatePerson extends PersonHandler {

	private void createData(PersonRequest personRequest, DynamoDB dynamoDB)
			throws ConditionalCheckFailedException {
        Table table = dynamoDB.getTable(PersonHandler.DYNAMODB_TABLE_NAME);
	
        table.putItem(new PutItemSpec().withItem(
        	    new Item().withNumber("id", personRequest.getId())
        	               .withNumber("age", personRequest.getAge())
        	               .withString("firstName", personRequest.getFirstName())
        	               .withString("lastName", personRequest.getLastName())
        		           .withString("address", personRequest.getAddress())));
         }

	@Override
	public PersonResponse handleRequest(PersonRequest personRequest, Context context) {
		DynamoDB dynamoDB = getClient();

		createData(personRequest, dynamoDB);

		PersonResponse personResponse = new PersonResponse();
		personResponse.setMessage("Saved Successfully!!!");
		return personResponse;
	}
	
}
