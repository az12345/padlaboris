package com.instinctools.padlaboris.application.repository;

import com.instinctools.padlaboris.model.Patient;
import com.instinctools.padlaboris.repository.PatientRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Before
    public void setUp() {

        final Patient patient = new Patient();

        patient.setLastName("lastName");
        patient.setGender("male");

        patientRepository.save(patient);
    }

    @Test
    public void findByGender() throws Exception {

        final String content = "male";

        final List<Patient> patients = patientRepository.findByGender(content);

        assertThat(patients.get(0).getGender(), Is.is(content));
    }

    @Test
    public void findByLastName() throws Exception {

        final String content = "lastName";

        final List<Patient> patients = patientRepository.findByLastName(content);

        assertThat(patients.get(0).getLastName(), Is.is(content));
    }
}
