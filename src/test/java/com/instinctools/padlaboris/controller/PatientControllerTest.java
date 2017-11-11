package com.instinctools.padlaboris.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instinctools.padlaboris.dto.PatientDto;
import com.instinctools.padlaboris.model.Patient;
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
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webAppConfiguration;

    @Autowired
    private PatientRepository patientRepository;

    private PatientDto patientDto;

    private Patient patient;

    private Integer id;

    private String gender;

    private String lastName;

    @Before
    public void setUp() throws Exception {

        patient = new Patient();

        patient.setLastName("patientLastName");
        patient.setFirstName("patientFirstName");
        patient.setGender("m");

        id = patientRepository.save(patient).getId();
        lastName = patientRepository.findOne(id).getLastName();
        gender = patientRepository.findOne(id).getGender();

        patientDto = new PatientDto();

        patientDto.setFirstName("dtoFirstName");
        patientDto.setLastName("dtoLastName");

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppConfiguration)
                .build();
    }

    @Test
    public void createPatient() throws Exception {

        mockMvc.perform(request(POST, "/patients")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(patientDto))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(patientDto.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(patientDto.getLastName())));

    }

    @Test
    public void deletePatient() throws Exception {

        mockMvc.perform(request(DELETE, "/patients/" + id)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(patientRepository.exists(id), Is.is(false));

    }

    @Test
    public void fetchPatient() throws Exception {

        mockMvc.perform(request(GET, "/patients/" + id)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(patient.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(patient.getLastName())));

    }

    @Test
    public void fetchByGender() throws Exception {

        mockMvc.perform(request(GET, "/patients/gender/" + gender)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].gender", is(patient.getGender())));

    }

    @Test
    public void fetchByLastName() throws Exception {

        mockMvc.perform(request(GET, "/patients/lastName/" + lastName)
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].lastName", is(patient.getLastName())));
    }

    @Test
    public void fetchAll() throws Exception {

        mockMvc.perform(request(GET, "/patients")
                .accept(APPLICATION_JSON_UTF8_VALUE)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].lastName", is(patient.getLastName())))
                .andExpect(jsonPath("$.[0].firstName", is(patient.getFirstName())))
                .andExpect(jsonPath("$.[0].gender", is(patient.getGender())));

    }

}
