Feature: check application status
  Scenario: status checker makes a call to GET /status
    When the status checker calls /status
    Then the status checker should receive 200 as http status code
    And response as Application is UP and running