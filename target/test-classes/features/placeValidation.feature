Feature: Validating Place API's'

@AddPlace
Scenario: Verify if Place is being Sucessfully added using AddPlaceAPI
   Given Add Place Payload
   When user calls "AddPlaceAPI" with post http request
   Then API call got sucess with status code 200
   And "status" in response body is "OK"
   And "scope" in response body is "APP"
   
   @Test2
   Scenario Outline: Verify if Place is being Sucessfully added using AddPlaceAPI outline
   Given Add Place Payload with "<name>"  "<language>"  "<address>"
   When user calls "AddPlaceAPI" with post http request
   Then API call got sucess with status code 200
   And "status" in response body is "OK"
   And "scope" in response body is "APP"
   And verify place_Id created maps to "<name>" using "GetPlaceAPI"
 
   Examples:
       | name    |  |language| |address|
       | SkHouse |  |Englishh| |USA Street 123|
       
       
       
       
       
@Test11
Scenario: Verify if Place is being Sucessfully added using AddPlaceAPI
   Given Add Place Payload
   When user calls "AddPlaceAPI" with "post" http request
   Then API call got sucess with status code 200
   And "status" in response body is "OK"
   And "scope" in response body is "APP"       
   
   
   
   
   
   
   
   
   
   