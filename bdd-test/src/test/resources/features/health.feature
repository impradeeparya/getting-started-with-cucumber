Feature: check application health
  Scenario: health checker makes a call to GET /actuator/health
    When the health checker calls /actuator/health
    Then the health checker should receive 200 as http status code
    And status as UP