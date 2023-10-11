package au.com.telstra.simcardactivator.stepsDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

public class SimCardActivatorStepDefinitions {
    @Autowired
    private RestTemplate restTemplate;
    private String simCardIccid;
    private ResponseEntity<String> activationResponse;


    @Given("a SIM card with ICCID {string} is ready for activation")
    public void a_SIM_card_with_ICCID_is_ready_for_activation(String iccid) {
        this.simCardIccid = iccid;
    }

    @When("the SIM card is activated")
    public void the_SIM_card_is_activated() {
        // Use the RestTemplate to send an activation request to your microservice
        String activationUrl = "http://localhost:8080/activate"; // Replace with your actual URL
        ResponseEntity<String> response = restTemplate.postForEntity(activationUrl, simCardIccid, String.class);
        this.activationResponse = response;
    }

    @Then("the activation status should be {string}")
    public void the_activation_status_should_be(String status) {
        if (status.equals("successful")) {
            Assert.assertEquals(200, activationResponse.getStatusCodeValue()); // Assuming 200 for success
        } else if (status.equals("failed")) {
            Assert.assertEquals(400, activationResponse.getStatusCodeValue()); // Assuming 400 for failure
        }
    }
}
