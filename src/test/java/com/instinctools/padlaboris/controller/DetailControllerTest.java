package com.instinctools.padlaboris.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instinctools.padlaboris.dto.DetailDto;
import com.instinctools.padlaboris.model.Detail;
import com.instinctools.padlaboris.model.Patient;
import com.instinctools.padlaboris.repository.DetailRepository;
import com.instinctools.padlaboris.repository.PatientRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DetailControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webAppConfiguration;

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private PatientRepository patientRepository;

    private DetailDto detailDto;

    private Detail detail;

    private Integer id;

    private Integer patientId;

    private Integer bloodType;

    private String rh;

    @Before
    public void setUp() throws Exception {

        final Patient patient = new Patient();

        patientId = patientRepository.save(patient).getId();

        detail = new Detail();
        detail.setBloodType(1);
        detail.setBmi(2);
        detail.setRhesusFactor("+");

        id = detailRepository.save(detail).getId();

        bloodType = detailRepository.findOne(id).getBloodType();

        rh = detailRepository.findOne(id).getRhesusFactor();

        detailDto = new DetailDto();
        detailDto.setBmi(2);
        detailDto.setBloodType(2);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppConfiguration)
                .build();
    }

    @Test
    public void createDetail() throws Exception {

        mockMvc.perform(request(POST, "/patients/" + patientId + "/details")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(detailDto))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bmi", is(detailDto.getBmi())))
                .andExpect(jsonPath("$.bloodType", is(detailDto.getBloodType())));

    }

    @Test
    public void fetchDetail() throws Exception {

        mockMvc.perform(request(GET, "/patients/" + patientId + "/details/" + id)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bmi", is(detail.getBmi())))
                .andExpect(jsonPath("$.bloodType", is(detail.getBloodType())));

    }

    @Test
    public void fetchAllDetails() throws Exception {

        mockMvc.perform(request(GET, "/details")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].bmi", is(detail.getBmi())))
                .andExpect(jsonPath("$.[0].bloodType", is(detail.getBloodType())));

    }

    @Test
    public void deleteDetail() throws Exception {

        mockMvc.perform(request(DELETE, "/patients/" + patientId + "/details/" + id)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(detailRepository.exists(id), Is.is(false));

    }

    @Test
    public void fetchByBloodType() throws Exception {

        mockMvc.perform(request(GET, "/patients/" + patientId + "/details/bloodType/" + bloodType)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].bmi", is(detail.getBmi())))
                .andExpect(jsonPath("$.[0].bloodType", is(detail.getBloodType())));

    }

    @Test
    public void fetchByBMI() throws Exception {

        mockMvc.perform(request(GET, "/patients/" + patientId + "/details/rh/" + rh)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].rhesusFactor", is(detail.getRhesusFactor())))
                .andExpect(jsonPath("$.[0].bloodType", is(detail.getBloodType())));

    }


}
