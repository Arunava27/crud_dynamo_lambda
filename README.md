# crud_dynamo_lambda
Simple lambda handlers for dynamo DB

## Steps
1. Checkout the code and change the REGION in PersonHandler.java to region of yours.
2. Run mvn clean package shade:shade from console OR just clean package shade:shade from editor ( Eclicse / IntelliJ )
3. Log in into AWS console and launch DynamoDb and then create a table name Person with Primary key name as 'id' and type as Number.
4. Launch Aws Lambda service and create 3 lambda functions ( getPerson, createPerson, deletePerson ) with below configuration -
  getPerson -- 
  handler name - com.dynamo.handler.GetPerson
  createPerson -- 
  handler name - com.dynamo.handler.CreatePerson
  deletePerson --
  handler name - com.dynamo.handler.deletePerson
  
5. Upload the -shaded.jar file present inside the target directory in the local machine. ( Jar will get generate after running step 2 ) 
  
6. start testing with the below json request -- 

createPerson - 
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "age": 30,
  "address": "United States"
}
getPerson - 
{
 "id": 1
}
deletePerson - 
{
 "id": 1
}
