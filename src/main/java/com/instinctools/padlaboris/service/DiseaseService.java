package com.instinctools.padlaboris.service;

import com.instinctools.padlaboris.model.Disease;

import java.util.List;

/**
 * Service for {@link Disease}.
 */
public interface DiseaseService {

    /**
     * Fetches all diseases.
     *
     * @return a list of diseases
     */
    List<Disease> findAll();

    /**
     * Searches diseases by name.
     *
     * @param diseaseName name of a disease
     * @return a list of diseases
     */
    List<Disease> findByDiseaseName(String diseaseName);

    /**
     * Searches diseases by code.
     *
     * @param diseaseCode code of a disease
     * @return a list of diseases
     */
    List<Disease> findByDiseaseCode(String diseaseCode);

    /**
     * Searches diseases by class.
     *
     * @param diseaseClass class of a disease
     * @return a list of diseases
     */
    List<Disease> findByDiseaseClass(String diseaseClass);

    /**
     * Searches a disease by id.
     *
     * @param id id of a disease
     * @return a disease
     */
    Disease findById(Integer id);

    /**
     * Inserts or updates a disease.
     *
     * @param disease a disease to save
     * @return a saved disease
     */
    Disease save(Disease disease);

    /**
     * Removes an existing disease.
     *
     * @param disease a disease to remove
     */
    void delete(Disease disease);

    /**
     * Removes a disease by id.
     *
     * @param id id of a disease
     */
    void delete(Integer id);

    /**
     * Checks whether a disease exists.
     *
     * @param id id of a disease
     * @return true if exists, false otherwise
     */
    boolean exists(Integer id);
}
