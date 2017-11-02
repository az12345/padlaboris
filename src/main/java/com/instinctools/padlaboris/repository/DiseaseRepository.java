package com.instinctools.padlaboris.repository;

import com.instinctools.padlaboris.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for {@link Disease}.
 */
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {

    /**
     * Searches diseases by name.
     * @param diseaseName name of a disease
     * @return a list of diseases
     */
    @Query(value = "SELECT * FROM diseases WHERE disease_name = :name", nativeQuery = true)
    List<Disease> findByDiseaseName(@Param("name") String diseaseName);


    /**
     * Searches diseases by code.
     * @param diseaseCode disease code
     * @return a list of diseases
     */
    @Query(value = "SELECT * FROM diseases WHERE disease_code = :code", nativeQuery = true)
    List<Disease> findByDiseaseCode(@Param("code") String diseaseCode);

    /**
     * Searches diseases by class.
     * @param diseaseClass disease class
     * @return a list of diseases
     */
    List<Disease> findByDiseaseClass(String diseaseClass);
}
