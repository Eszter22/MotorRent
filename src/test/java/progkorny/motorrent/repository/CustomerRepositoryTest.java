package progkorny.motorrent.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import progkorny.motorrent.model.Customer;

@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void saveAndFindCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Béla");
        customer.setLastName("Kiss");
        customer.setEmail("bela.kiss@example.com");
        customer.setPhoneNumber("+3611223344");
        customer.setDriverLicenseNumber("DL112233");
        customer.setCountryCode("HU");
        this.customerRepository.save(customer);
        Integer id = customer.getId();
        Optional<Customer> found = this.customerRepository.findById(id);
        Assertions.assertThat(found).isPresent();
        Assertions.assertThat(((Customer)found.get()).getFirstName()).isEqualTo("Béla");
        Assertions.assertThat(((Customer)found.get()).getEmail()).isEqualTo("bela.kiss@example.com");
    }

    @Test
    void deleteCustomer_shouldRemoveFromDb() {
        Customer customer = new Customer();
        customer.setFirstName("Tom");
        customer.setLastName("Papp");
        customer.setEmail("tom.papp@ezmegaz.com");
        customer.setPhoneNumber("+3630111222");
        customer.setDriverLicenseNumber("DL334455");
        customer.setCountryCode("HU");
        this.customerRepository.save(customer);
        Integer id = customer.getId();
        this.customerRepository.deleteById(id);
        Optional<Customer> deleted = this.customerRepository.findById(id);
        Assertions.assertThat(deleted).isEmpty();
    }
}
