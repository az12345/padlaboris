package com.instinctools.padlaboris.service;

import com.instinctools.padlaboris.model.Patient;

import java.util.List;

/**
 * Interface for Patient work with database.
 */
public interface PatientService {

    /**
     * Method for add patient to database.
     *
     * @param patient Patient patient.
     * @return object of the added Patient class.
     */
    Patient create(Patient patient);

    /**
     * Method for display patient from database by id.
     *
     * @param id Patient id.
     * @return object of patient class with this id.
     */
    Patient fetch(Integer id);

    /**
     * Method for remove patient from database.
     *
     * @param id Patient id.
     */
    void delete(Integer id);

    /**
     * Method for search patients in database by last name.
     *
     * @param lastName Patient lastName.
     * @return List of Patient objects with this last name.
     */
    List<Patient> findByLastName(String lastName);

    /**
     * Method for search patients in database by gender.
     *
     * @param gender Patient gender.
     * @return List of Patient objects with this gender.
     */
    List<Patient> findByGender(String gender);

    /**
     * Method for display all patients from data base.
     *
     * @return List of Patient objects.
     */
    List<Patient> listPatients();

    /**
     * Method for update patient from database.
     *
     * @param id      Patient id.
     * @param patient Patient patient.
     * @return updated Patient patient.
     */
    Patient updateById(Integer id, Patient patient);
}
