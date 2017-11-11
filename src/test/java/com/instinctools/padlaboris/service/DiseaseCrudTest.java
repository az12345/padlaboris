package com.instinctools.padlaboris.service;

import com.instinctools.padlaboris.model.Disease;
import com.instinctools.padlaboris.repository.DiseaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DiseaseCrudTest {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Before
    public void init() {
        final Disease disease = new Disease(
                null,
                "cancer",
                "123",
                "you're dead",
                "hardcore");

        diseaseRepository.save(disease);
    }

    @Test
    public void testFind() {
        final List list = diseaseRepository.findAll();
        assertEquals(1, list.size());
    }

    @Test
    public void testFindByParameter() {
        List list = diseaseRepository.findByDiseaseName("cancer");
        assertEquals(1, list.size());

        list = diseaseRepository.findByDiseaseClass("hard");
        assertEquals(0, list.size());
    }

    @Test
    public void testUpdate() {
        Disease disease = diseaseRepository.findOne(1);
        disease.setDiseaseName("ASJDNAIEUFOEF");
        diseaseRepository.save(disease);

        disease = diseaseRepository.findOne(1);
        assertNotEquals("cancer", disease.getDiseaseName());
    }

    @Test
    public void delete() {
        diseaseRepository.delete(1);
        assertEquals(0, diseaseRepository.findAll().size());
    }
}
