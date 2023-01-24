package com.dynamo.handler;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.dynamo.dto.PersonRequest;
import com.dynamo.dto.PersonResponse;

public class GetPerson extends PersonHandler {

	@Override
	public PersonResponse handleRequest(PersonRequest personRequest, Context context) {
		DynamoDB dynamoDB = getClient();
		PersonResponse personResponse = new PersonResponse();
		
		Item item = getData(personRequest, dynamoDB);

		
		if(item != null) {
			personResponse.setMessage(item.toJSONPretty());
		}
		
		return personResponse;
	}
	
	private Item getData(PersonRequest personRequest, DynamoDB dynamoDB)
			throws ConditionalCheckFailedException {
          Table table = dynamoDB.getTable(PersonHandler.DYNAMODB_TABLE_NAME);
          return table.getItem("id", personRequest.getId());
	
         }
}
