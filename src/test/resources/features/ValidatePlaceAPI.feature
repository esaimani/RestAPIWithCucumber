Feature: Validating Place API's

  Scenario Outline: Verify if place is being successfully added using Add Place API
    Given Prepare Add Place Payload with "<name>" "<language>" "<address>"
    When user calls the "<AddPlaceAPI>" with post call
    Then Place added with success code 200
    And "status" in response body is "OK"
    Examples:
      | AddPlaceAPI             | name          | language | address                   |
      | maps/api/place/add/json | Omara Lane SE | English  | 67, side layout, cohen 09 |