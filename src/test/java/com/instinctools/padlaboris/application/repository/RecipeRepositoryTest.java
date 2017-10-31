package com.instinctools.padlaboris.application.repository;

import com.instinctools.padlaboris.application.model.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void findByMedicineName() {

        final Date date = new Date(new Date().getTime());

        final Recipe recipe = new Recipe(date,
                date, "abc", "abc");

        entityManager.persist(recipe);

        final List<Recipe> recipes = recipeRepository.findByMedicineName("abc");

        for (Recipe foundRecipe : recipes) {
            assert recipe.getMedicineName().equals(foundRecipe.getMedicineName());
        }
    }
}
