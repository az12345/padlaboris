package com.instinctools.padlaboris.application.repository;

import com.instinctools.padlaboris.model.MedicalLeave;
import com.instinctools.padlaboris.repository.MedicalLeaveRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MedicalLeaveRepositoryTest {

    @Autowired
    private MedicalLeaveRepository medicalLeaveRepository;

    @Before
    public void setUp() {

        final MedicalLeave medicalLeave = new MedicalLeave();

        medicalLeave.setStartDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
        medicalLeave.setEndDate(new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime());

        medicalLeaveRepository.save(medicalLeave);
    }


    @Test
    public void findByStartDate() {

        final Date startDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        final List<MedicalLeave> medicalLeaves = medicalLeaveRepository.findByStartDate(startDate);

        assertThat(medicalLeaves.get(0).getStartDate(), is(startDate));
    }
}
