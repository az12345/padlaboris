package com.instinctools.padlaboris.service;


import com.instinctools.padlaboris.model.PatientDisease;
import com.instinctools.padlaboris.repository.PatientDiseaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultPatientDesiaseServiceTest {

    @Autowired
    PatientDiseaseRepository patientDiseaseRepository;
    @Autowired
    PatientDesiaseService patientDesiaseService;

    PatientDisease patientDisease;
    PatientDisease patientDisease2;
    int id;
    List<PatientDisease> patientDiseaseList;
    @Before
    public void upload(){
        patientDiseaseRepository.deleteAll();
        patientDisease = new PatientDisease();
        patientDisease.setStartDate(new GregorianCalendar(2010, Calendar.JANUARY, 10).getTime());
        patientDisease.setEndDate(new GregorianCalendar(2011, Calendar.JANUARY, 10).getTime());
        patientDisease.setDeseaseCode("deseaseCode");
        patientDisease.setDeseaseDescription("desisseDescription");

        patientDisease2 = new PatientDisease();
        patientDisease2.setStartDate(new GregorianCalendar(2012, Calendar.JANUARY, 10).getTime());
        patientDisease2.setEndDate(new GregorianCalendar(2013, Calendar.JANUARY, 10).getTime());
        patientDisease2.setDeseaseCode("deseaseCode");
        patientDisease2.setDeseaseDescription("desisseDescription");

        patientDiseaseRepository.save(patientDisease);
        patientDiseaseRepository.save(patientDisease2);
        id = patientDisease.getUniqueId();
    }

    @Test
    public void create() throws Exception {
        PatientDisease created = patientDesiaseService.create(patientDisease);
        assertEquals(created.getDeseaseCode(),patientDisease.getDeseaseCode());
    }

    @Test
    public void fetchById() throws Exception {

        assertEquals(patientDisease, patientDesiaseService.fetchById(id));
    }

    @Test
    public void fetchAll() throws Exception {
        patientDiseaseList = (List<PatientDisease>) patientDesiaseService.fetchAll();
        assertEquals(patientDiseaseList.size(), 2);
    }

    @Test
    public void updateById() throws Exception {
    PatientDisease patientDiseaseNew = patientDisease;
    patientDiseaseNew.setDeseaseCode("1");
    patientDiseaseNew.setDeseaseDescription("2");
    patientDesiaseService.updateById(id, patientDiseaseNew.getStartDate(), patientDiseaseNew.getEndDate(), patientDiseaseNew.getDeseaseCode(), patientDiseaseNew.getDeseaseDescription());
    assertEquals(patientDiseaseNew.getDeseaseCode(), patientDesiaseService.fetchById(id).getDeseaseCode());
    assertEquals(patientDiseaseNew.getDeseaseDescription(), patientDesiaseService.fetchById(id).getDeseaseDescription());
    }

    @Test
    public void deleteById() throws Exception {
        patientDesiaseService.deleteById(id);
        assertEquals(null, patientDesiaseService.fetchById(id) );

    }

    @Test
    public void findStartDateBetween() throws Exception {
        List<Date> dateList = patientDesiaseService.findStartDateBetween(patientDisease.getStartDate(), patientDisease2.getStartDate());
        assertEquals(dateList.size(), 2);
    }

    @Test
    public void findFinishDateBetween() throws Exception {
        List<Date> dateList = patientDesiaseService.findFinishDateBetween(patientDisease.getEndDate(), patientDisease2.getEndDate());
        assertEquals(dateList.size(), 2);
    }

}