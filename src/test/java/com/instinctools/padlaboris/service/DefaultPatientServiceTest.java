package com.instinctools.padlaboris.service;

import com.instinctools.padlaboris.model.Patient;
import com.instinctools.padlaboris.repository.PatientRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultPatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    private Integer id;

    private Patient patient;

    @Before
    public void setUp() throws Exception {

        patient = new Patient();

        patient.setLastName("lastName");
        patient.setGender("male");

        id = patientRepository.save(patient).getId();
    }

    @Test
    public void create() throws Exception {

        final String content = "newPatientLastName";

        patient.setLastName(content);

        final Patient saved = patientService.create(patient);

        assertThat(saved.getLastName(), Is.is(content));
    }

    @Test
    @Transactional
    public void update() throws Exception {

        final String content = "updatePatientLastName";

        final Patient updatePatient = patientRepository.findOne(id);

        updatePatient.setLastName(content);
        patientService.update(updatePatient);

        assertThat(patientRepository.findOne(id).getLastName(), Is.is(content));
    }

    @Test
    public void fetch() throws Exception {

        final String content = "lastName";

        assertThat(patientService.fetch(id).getLastName(), Is.is(content));
    }

    @Test
    public void findByLastName() throws Exception {

        final String content = "lastName";

        final List<Patient> patients = patientService.findByLastName(content);

        assertThat(patients.get(0).getLastName(), Is.is(content));
    }

    @Test
    public void findByGender() throws Exception {

        final String content = "male";

        final List<Patient> patients = patientService.findByGender(content);

        assertThat(patients.get(0).getGender(), Is.is(content));
    }

    @Test
    public void delete() throws Exception {

        patientService.delete(id);

        assertThat(patientRepository.exists(id), Is.is(false));
    }
}
