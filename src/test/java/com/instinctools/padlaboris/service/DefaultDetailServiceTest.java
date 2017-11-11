package com.instinctools.padlaboris.service;

import com.instinctools.padlaboris.model.Detail;
import com.instinctools.padlaboris.model.Patient;
import com.instinctools.padlaboris.repository.DetailRepository;
import com.instinctools.padlaboris.repository.PatientRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultDetailServiceTest {

    @Autowired
    private DetailService detailService;

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private PatientRepository patientRepository;

    private Integer patientId;

    private Integer id;

    @Before
    public void setUp() throws Exception {

        final Patient patient = new Patient();

        patientId = patientRepository.save(patient).getId();

        final Detail detail = new Detail();

        detail.setBloodType(1);
        detail.setBmi(1.25);
        detail.setRhesusFactor("+");

        id = detailRepository.save(detail).getId();
    }

    @Test
    public void create() throws Exception {

        final Integer content = 2;

        final Detail createDetail = new Detail();

        createDetail.setBloodType(content);

        detailService.create(patientId, createDetail);

        assertThat(detailRepository.findByBloodType(content).get(0).getBloodType(), Is.is(content));
    }

    @Test
    public void fetch() throws Exception {

        final double content = 1.25;

        assertThat(detailService.fetch(id).getBmi(), Is.is(content));
    }

    @Test
    @Transactional
    public void update() throws Exception {

        final Integer content = 3;

        final Detail updateDetail = detailRepository.findOne(id);

        updateDetail.setBloodType(content);

        detailService.updateById(id, updateDetail);

        assertThat(detailRepository.findOne(id).getBloodType(), Is.is(content));
    }

    @Test
    public void findByRhesusFactor() throws Exception {

        final String content = "+";

        assertThat(detailService.findByRhesusFactor(content).get(0).getRhesusFactor(), Is.is(content));
    }

    @Test
    public void findByBloodType() throws Exception {

        final Integer content = 1;

        assertThat(detailService.findByBloodType(content).get(0).getBloodType(), Is.is(content));
    }

    @Test
    public void delete() throws Exception {

        detailService.delete(id);

        assertThat(detailRepository.exists(id), Is.is(false));
    }
}
