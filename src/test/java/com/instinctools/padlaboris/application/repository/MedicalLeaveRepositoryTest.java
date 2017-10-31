package com.instinctools.padlaboris.application.repository;

import com.instinctools.padlaboris.application.model.MedicalLeave;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MedicalLeaveRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MedicalLeaveRepository medicalLeaveRepository;

    @Test
    public void findByStartDate() {

        final Date date = new Date(new Date().getTime());

        final MedicalLeave medicalLeave = new MedicalLeave(date,
                new Date(new Date().getTime()));

        entityManager.persist(medicalLeave);

        final List<MedicalLeave> medicalLeaves = medicalLeaveRepository.findByStartDate(date);

        for (MedicalLeave foundMedicalLeave : medicalLeaves) {
            assert medicalLeave.getStartDate().equals(foundMedicalLeave.getStartDate());
        }
    }
}
