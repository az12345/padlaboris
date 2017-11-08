package com.instinctools.padlaboris.service;


import com.instinctools.padlaboris.model.PatientProcedure;
import com.instinctools.padlaboris.repository.PatientProcedueRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultPatientProcedureServiceTest {
    @Autowired
    DefaultPatientProcedureService procedureService;
    @Autowired
    PatientProcedueRepository patientProcedueRepository;


    PatientProcedure patientProcedure;
    PatientProcedure patientProcedure2;
    PatientProcedure patientProcedure3;
    int id;
    List<PatientProcedure> procedureList;
    @Before
    public void setUp(){
        patientProcedueRepository.deleteAll();
        patientProcedure = new PatientProcedure();
        patientProcedure.setProcedureName("procedure");
        patientProcedure.setDate(new GregorianCalendar(2011, Calendar.JANUARY, 10).getTime());
        patientProcedure.setMd("md");
        patientProcedure2 = new PatientProcedure();
        patientProcedure2.setProcedureName("procedure");
        patientProcedure2.setDate(new GregorianCalendar(2012, Calendar.JANUARY, 10).getTime());
        patientProcedure2.setMd("md");
        patientProcedure3 = new PatientProcedure();
        patientProcedure3.setProcedureName("procedure");
        patientProcedure3.setDate(new GregorianCalendar(2013, Calendar.JANUARY, 10).getTime());
        patientProcedure3.setMd("md");
        id = patientProcedueRepository.save(patientProcedure).getUniqueId();
        patientProcedueRepository.save(patientProcedure2);
        patientProcedueRepository.save(patientProcedure3);
    }

    @Test
    public void create() throws Exception {
        PatientProcedure create = procedureService.create(patientProcedure);
        assertEquals(create.getProcedureName(), patientProcedure.getProcedureName());
    }

    @Test
    public void fetchById() throws Exception {
        assertEquals(patientProcedure, procedureService.fetchById(id));
    }

    @Test
    public void fetchAll() throws Exception {
        List<PatientProcedure> patientProcedures = procedureService.fetchAll();
        assertEquals(patientProcedures.size(), 3);
    }

    @Test
    public void updateById() throws Exception {

    PatientProcedure create = patientProcedure;
    create.setMd("new");
    procedureService.updateById(id, create);
    assertEquals(create.getMd(), procedureService.fetchById(id).getMd());
    }

    @Test
    public void deleteById() throws Exception {
        procedureService.deleteById(id);
        assertEquals(null, procedureService.fetchById(id));
    }

    @Test
    public void getPatientProcedureByDateAfter() throws Exception {
        List<PatientProcedure> patientProcedures = procedureService.getPatientProcedureByDateAfter(patientProcedure.getDate());
        assertEquals(patientProcedures.size(), 2);
    }

}