package com.progressSoft.FXDeal.Controller;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.progressSoft.FXDeal.Model.Deal;
import com.progressSoft.FXDeal.Repo.DealRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private DealRepo dealRepo;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void makeDealSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/makeDeal").
                contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"deal_Amount_In_Ordering_Currency\":1637.344,\n" +
                        "    \"ordering_Currency\":\"USD\",\n" +
                        "    \"to_Currency_ISO_Code\":\"JOD\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void makeDealWrong() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/makeDeal").
                contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"deal_Amount_In_Ordering_Currency\":1637.344,\n" +
                        "    \"ordering_Currency\":\"USDe4\",\n" +
                        "    \"to_Currency_ISO_Code\":\"JOD223\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isCreated());
    }

}