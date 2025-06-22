package progkorny.motorrent.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import progkorny.motorrent.model.Customer;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerRESTControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateGetAndDeleteCustomer() throws Exception {
        Customer customer = new Customer("Anna", "Nagy", "anna.nagy@example.com", "+3612345678", "DL998877", "HU");
        String json = this.objectMapper.writeValueAsString(customer);
        String response = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/customers", new Object[0]).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id", new Object[0]).exists()).andExpect(MockMvcResultMatchers.jsonPath("$.email", new Object[0]).value("anna.nagy@example.com")).andReturn().getResponse().getContentAsString();
        Customer savedCustomer = (Customer)this.objectMapper.readValue(response, Customer.class);
        Integer savedId = savedCustomer.getId();
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/" + savedId, new Object[0])).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.email", new Object[0]).value("anna.nagy@example.com")).andExpect(MockMvcResultMatchers.jsonPath("$.firstName", new Object[0]).value("Anna"));
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/" + savedId, new Object[0])).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("customer with id " + savedId + " is deleted"));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/" + savedId, new Object[0])).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}