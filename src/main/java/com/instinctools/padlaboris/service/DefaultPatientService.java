package com.instinctools.padlaboris.service;

import com.instinctools.padlaboris.model.Patient;
import com.instinctools.padlaboris.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * class that implements the Patient's work and databases.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultPatientService implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient create(final Patient patient) {

        log.info("Patient added to database.");

        return patientRepository.save(patient);
    }


    @Override
    public Patient fetch(final Integer id) {

        log.info("Patient displayed.");

        return patientRepository.findOne(id);
    }

    @Override
    public void delete(final Integer id) {

        log.info("Patient deleted from database.");

        patientRepository.delete(id);
    }

    @Override
    public List<Patient> findByLastName(final String lastName) {

        log.info("Patients were find by last name and displayed.");

        return patientRepository.findByLastName(lastName);
    }

    @Override
    public List<Patient> findByGender(final String gender) {

        log.info("Patients were find by gender and displayed");

        return patientRepository.findByGender(gender);
    }

    @Override
    public List<Patient> listPatients() {

        log.info("All patients were displayed");

        return patientRepository.findAll();
    }

    @Override
    public Patient updateById(final Integer id, final Patient patient) {

        final Patient saved = patientRepository.findOne(id);

        patientRepository.updateById(id,
                Objects.isNull(patient.getLastName()) ? saved.getLastName() : patient.getLastName(),
                Objects.isNull(patient.getFirstName()) ? saved.getFirstName() : patient.getFirstName(),
                Objects.isNull(patient.getGender()) ? saved.getGender() : patient.getGender(),
                Objects.isNull(patient.getHomeNumber()) ? saved.getHomeNumber() : patient.getHomeNumber(),
                Objects.isNull(patient.getMobileNumber()) ? saved.getMobileNumber() : patient.getMobileNumber(),
                Objects.isNull(patient.getDeathDate()) ? saved.getDeathDate() : patient.getDeathDate());

        return saved;
    }
}
