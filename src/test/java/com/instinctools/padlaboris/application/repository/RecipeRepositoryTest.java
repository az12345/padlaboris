package com.instinctools.padlaboris.application.repository;

import com.instinctools.padlaboris.model.Recipe;
import com.instinctools.padlaboris.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() {

        final Recipe recipe = new Recipe();

        recipe.setIssueDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
        recipe.setExpireDate(new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime());
        recipe.setMedicineName("abc");
        recipe.setDosage("4ml");

        recipeRepository.save(recipe);
    }
    @Test
    public void findByMedicineName() {

        final String medicineName = "abc";

        final List<Recipe> recipes = recipeRepository.findByMedicineName(medicineName);

        assertThat(recipes.get(0).getMedicineName(), is(medicineName));
    }
}
