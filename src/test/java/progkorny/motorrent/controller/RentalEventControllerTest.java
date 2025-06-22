package progkorny.motorrent.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import progkorny.motorrent.model.Customer;
import progkorny.motorrent.model.RentalEvent;
import progkorny.motorrent.service.RentalEventService;

@WebMvcTest({RentalEventController.class})
public class RentalEventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RentalEventService rentalEventService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRentalEvents() throws Exception {
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        RentalEvent event1 = new RentalEvent();
        event1.setId(1L);
        event1.setRentalCustomer(customer1);
        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        RentalEvent event2 = new RentalEvent();
        event2.setId(2L);
        event2.setRentalCustomer(customer2);
        List<RentalEvent> mockEvents = Arrays.asList(event1, event2);
        Mockito.when(this.rentalEventService.getAllRentalEvents()).thenReturn(mockEvents);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/rentalevents", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(this.objectMapper.writeValueAsString(mockEvents)));
    }
}
