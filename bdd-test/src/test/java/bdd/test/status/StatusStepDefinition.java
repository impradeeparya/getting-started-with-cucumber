package bdd.test.status;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StatusStepDefinition {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    private ResponseEntity<String> response;

    @When("^the status checker calls (.*)$")
    public void statusCheckerCallsStatusAPI(String healthCheckURL) {
        response = testRestTemplate.getForEntity(healthCheckURL, String.class);
    }

    @Then("^the status checker should receive (\\d+) as http status code$")
    public void shouldReceive200AsHttpStatus(int httpCode) {
        HttpStatus responseCode = response.getStatusCode();
        Assert.assertEquals("status check http response is non 200", httpCode, responseCode.value());
    }

    @And("^response as (.+)$")
    public void shouldReceiveStatusAsUpAndRunning(String status) {
        String body = response.getBody();
        Assert.assertNotNull("response is null", body);
        Assert.assertTrue("response is not " + status, body.contains(status));
    }
}
