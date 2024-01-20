

Feature: Validating Place APIs

	@AddPlace @Regression
  Scenario Outline: Verify place is being added successfully using AddPlaceAPI
    Given Add Place Payload with <lat> <lng> <address> <language> <name> <phoneNumber>
    When User calls "AddPlaceAPI" with "POST" http request
    Then API call is successful with status code 200
    And "status" in Response body is "OK"
    And User verifies place_id created has the "name" as <name> using "GetPlaceAPI"
    
    Examples:
    |	lat			|	lng			|	address							|	language	|	name		|	phoneNumber		|
    |	-11.108	|	81.271	|	"Debian street, UK"	|	"English"	|	"test1"	|	"01082021001"	|
    |	49.573	|	-50.228	|	"Liton square, USA"	|	"Eng-US"	|	"test2"	|	"05045021002"	|
  
  @DeletePlace @Regression
	Scenario: Verify if place is being deleted successfully using DeletePlaceAPI
		Given Delete place payload
		When User calls "DeletePlaceAPI" with "POST" http request
		Then API call is successful with status code 200
    And "status" in Response body is "OK"

