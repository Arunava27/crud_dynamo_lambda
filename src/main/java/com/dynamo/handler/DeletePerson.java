package com.dynamo.handler;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.dynamo.dto.PersonRequest;
import com.dynamo.dto.PersonResponse;

public class DeletePerson extends PersonHandler {

	@Override
	public PersonResponse handleRequest(PersonRequest personRequest, Context context) {
		DynamoDB dynamoDB = getClient();
		PersonResponse personResponse = new PersonResponse();
		
		DeleteItemOutcome item = deleteData(personRequest, dynamoDB);

		
		if(item != null) {
			personResponse.setMessage("Item with id " +personRequest.getId()+" has been deleted ");
		}else {
			personResponse.setMessage("Item not found");
		}
		
		return personResponse;
	}
	
	private DeleteItemOutcome deleteData(PersonRequest personRequest, DynamoDB dynamoDB)
			throws ConditionalCheckFailedException {
          Table table = dynamoDB.getTable(PersonHandler.DYNAMODB_TABLE_NAME);
          return table.deleteItem("id",personRequest.getId());
	}

}
