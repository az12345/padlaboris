package com.instinctools.padlaboris.application.repository;

import com.instinctools.padlaboris.model.Detail;
import com.instinctools.padlaboris.repository.DetailRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DetailRepositoryTest {

    @Autowired
    private DetailRepository detailRepository;

    @Before
    public void setUp() throws Exception {

        final Detail detail = new Detail();

        detail.setRhesusFactor("+");
        detail.setBloodType(1);

        detailRepository.save(detail);
    }

    @Test
    public void findByRhesusFactor() throws Exception {

        final String content = "+";

        final List<Detail> detail = detailRepository.findByRhesusFactor(content);

        assertThat(detail.get(0).getRhesusFactor(), Is.is(content));
    }

    @Test
    public void findByBloodType() throws Exception {

        final Integer content = 1;

        final List<Detail> detail = detailRepository.findByBloodType(content);

        assertThat(detail.get(0).getBloodType(), Is.is(content));
    }

}
