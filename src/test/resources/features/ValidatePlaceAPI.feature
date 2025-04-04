Feature: Validating Place API's

  @addPlace
  Scenario Outline: Verify if place is being successfully added using Add Place API
    Given Prepare Add Place Payload with "<name>" "<language>" "<address>"
    When user calls the "ADDPLACEAPI" with "POST" call
    Then API Call with success code 200
    And "status" in response body is "OK"
    And  verify place_id created maps to "<name>" using "GETPLACEAPI"
    Examples:
      | name           | language | address                   |
      | Omara Lane SE  | English  | 67, side layout, cohen 09 |
      | Symrna Lane WS | French   | 98, River Face, Perry 25  |

  @deletePlace
  Scenario: Verify if delete place functionality is working
    Given Prepare Delete Place Payload
    When user calls the "DELETEPLACEAPI" with "POST" call
    Then API Call with success code 200
    And "status" in response body is "OK"
