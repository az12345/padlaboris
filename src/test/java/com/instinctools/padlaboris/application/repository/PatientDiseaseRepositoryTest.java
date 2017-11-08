package com.instinctools.padlaboris.application.repository;

import com.instinctools.padlaboris.model.PatientDisease;
import com.instinctools.padlaboris.repository.PatientDiseaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDiseaseRepositoryTest {

    private PatientDisease patientDisease1;
    private PatientDisease patientDisease2;
    private PatientDisease patientDisease3;
    private PatientDisease patientDisease4;
    List<PatientDisease> patientDiseaseList;
    private int id;

    @Autowired
    PatientDiseaseRepository patientDiseaseRepository;

    @Before
    public void setUp() throws Exception {
    patientDiseaseRepository.deleteAll();
    patientDiseaseList = new ArrayList<>();
    patientDisease1 = new PatientDisease();
    patientDisease2 = new PatientDisease();
    patientDisease3 = new PatientDisease();
    patientDisease4 = new PatientDisease();


    patientDisease1.setStartDate(new GregorianCalendar(2010, Calendar.JANUARY, 10).getTime());
    patientDisease2.setStartDate(new GregorianCalendar(2011, Calendar.JANUARY, 10).getTime());
    patientDisease3.setStartDate(new GregorianCalendar(2012, Calendar.JANUARY, 10).getTime());
    patientDisease4.setStartDate(new GregorianCalendar(2013, Calendar.JANUARY, 10).getTime());

    patientDisease1.setEndDate(new GregorianCalendar(2010, Calendar.MAY, 1).getTime());
    patientDisease2.setEndDate(new GregorianCalendar(2011, Calendar.MAY, 1).getTime());
    patientDisease3.setEndDate(new GregorianCalendar(2012, Calendar.MAY, 1).getTime());
    patientDisease4.setEndDate(new GregorianCalendar(2013, Calendar.MAY, 1).getTime());

    patientDiseaseRepository.save(patientDisease1);
    patientDiseaseRepository.save(patientDisease2);
    patientDiseaseRepository.save(patientDisease3);
    patientDiseaseRepository.save(patientDisease4);

    patientDiseaseList.add(patientDisease1);
    patientDiseaseList.add(patientDisease2);
    patientDiseaseList.add(patientDisease3);
    patientDiseaseList.add(patientDisease4);
    }

    @Test
    public void findStartDateBetween() throws Exception {
    List<Date> list = patientDiseaseRepository.findStartDateBetween(new GregorianCalendar(2010, Calendar.JANUARY, 11).getTime(), new GregorianCalendar(2013, Calendar.JANUARY, 9).getTime());
    assertEquals(list.size(), 2);
    }

    @Test
    public void findEndDateBetween() throws Exception {
        List<Date> list = patientDiseaseRepository.findEndDateBetween(new GregorianCalendar(2010, Calendar.JANUARY, 11).getTime(), new GregorianCalendar(2013, Calendar.JANUARY, 9).getTime());
        assertEquals(list.size(), 3);
    }

    @Test
    public void updateOneById() throws Exception {
        PatientDisease patientDisease = patientDiseaseRepository.save(patientDisease1);
        int id = patientDisease.getUniqueId();
        Date start = patientDisease.getStartDate();
        Date end = patientDisease.getEndDate();
        String deseaseCode = "deseaseCode";
        String deseaseDescription = "deseaseDescription";
        patientDiseaseRepository.updateOneById(id, start, end, deseaseCode, deseaseDescription);
        assertEquals(deseaseCode, patientDiseaseRepository.findOne(id).getDeseaseCode());
        assertEquals(deseaseDescription, patientDiseaseRepository.findOne(id).getDeseaseDescription());
    }

}