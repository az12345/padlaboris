package com.instinctools.padlaboris.application.repository;

import com.instinctools.padlaboris.model.PatientProcedure;
import com.instinctools.padlaboris.repository.PatientProcedueRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientProcedueRepositoryTest {
    PatientProcedure patientProcedure1;
    PatientProcedure patientProcedure2;
    PatientProcedure patientProcedure3;
    PatientProcedure patientProcedure4;
    List<PatientProcedure> patientProcedureList;

    @Autowired
    PatientProcedueRepository patientProcedueRepository;

    @Before
    public void setUp() throws Exception {
        patientProcedueRepository.deleteAll();

        patientProcedure1 = new PatientProcedure();
        patientProcedure2 = new PatientProcedure();
        patientProcedure3 = new PatientProcedure();
        patientProcedure4 = new PatientProcedure();

        patientProcedure1.setDate(new GregorianCalendar(2011, Calendar.JANUARY, 10).getTime());
        patientProcedure2.setDate(new GregorianCalendar(2012, Calendar.JANUARY, 10).getTime());
        patientProcedure3.setDate(new GregorianCalendar(2013, Calendar.JANUARY, 10).getTime());
        patientProcedure4.setDate(new GregorianCalendar(2014, Calendar.JANUARY, 10).getTime());

        patientProcedueRepository.save(patientProcedure1);
        patientProcedueRepository.save(patientProcedure2);
        patientProcedueRepository.save(patientProcedure3);
        patientProcedueRepository.save(patientProcedure4);

        patientProcedureList = new ArrayList<>();

        patientProcedureList.add(patientProcedure1);
        patientProcedureList.add(patientProcedure2);
        patientProcedureList.add(patientProcedure3);
        patientProcedureList.add(patientProcedure4);
    }

    @Test
    public void getPatientProcedureByDateAfter() throws Exception {
        assertEquals(patientProcedueRepository.getPatientProcedureByDateAfter(new GregorianCalendar(2011, Calendar.JANUARY, 11).getTime()).size(), 3);

    }

    @Test
    public void updateOneById() throws Exception {
        int id = patientProcedure1.getUniqueId();
        patientProcedueRepository.updateOneById(id, "procedurename", (new GregorianCalendar(2011, Calendar.JANUARY, 10).getTime()), "md");
        assertEquals("procedurename", patientProcedueRepository.findOne(id).getProcedureName());
    }

}