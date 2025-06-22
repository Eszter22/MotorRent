package progkorny.motorrent.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import progkorny.motorrent.model.Customer;
import progkorny.motorrent.repository.CustomerRepository;

@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT
)
public class MotorRESTControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        this.customerRepository.deleteAll();
    }

    @Test
    void testCreateGetAndDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Anna");
        customer.setLastName("Nagy");
        customer.setEmail("anna.nagy@example.com");
        customer.setPhoneNumber("+3612345678");
        customer.setDriverLicenseNumber("DL998877");
        customer.setCountryCode("HU");
        ResponseEntity<Customer> postResponse = this.restTemplate.postForEntity("/api/customers", customer, Customer.class, new Object[0]);
        Assertions.assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Customer createdCustomer = (Customer)postResponse.getBody();
        Assertions.assertThat(createdCustomer).isNotNull();
        Integer id = createdCustomer.getId();
        Assertions.assertThat(id).isNotNull();
        ResponseEntity<Customer> getResponse = this.restTemplate.getForEntity("/api/customers/{id}", Customer.class, new Object[]{id});
        Assertions.assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat((Customer)getResponse.getBody()).isNotNull();
        Assertions.assertThat(((Customer)getResponse.getBody()).getEmail()).isEqualTo("anna.nagy@example.com");
        this.restTemplate.delete("/api/customers/{id}", new Object[]{id});
        ResponseEntity<Customer> getAfterDelete = this.restTemplate.getForEntity("/api/customers/{id}", Customer.class, new Object[]{id});
        Assertions.assertThat(getAfterDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
