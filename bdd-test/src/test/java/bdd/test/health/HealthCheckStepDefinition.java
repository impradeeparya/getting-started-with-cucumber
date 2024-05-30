package bdd.test.health;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HealthCheckStepDefinition {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    private ResponseEntity<String> response;

    @When("^the health checker calls (.*)$")
    public void healthCheckerCallsHealthAPI(String healthCheckURL) {
        response = testRestTemplate.getForEntity(healthCheckURL, String.class);
    }

    @Then("^the health checker should receive (\\d+) as http status code$")
    public void shouldReceive200AsHttpStatus(int httpCode) {
        HttpStatus responseCode = response.getStatusCode();
        Assert.assertEquals("health check http response is non 200", httpCode, responseCode.value());
    }

    @And("^status as (.+)$")
    public void shouldReceiveStatusAsUP(String status) {
        String body = response.getBody();
        Assert.assertNotNull("response is null", body);
        Assert.assertTrue("health check status is not UP", body.contains(status));
    }
}
