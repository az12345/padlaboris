package com.instinctools.padlaboris.repository;

import com.instinctools.padlaboris.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Interface for working with the database.
 */
@Transactional
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    /**
     * Method find patients by last name.
     *
     * @param lastName Patient lastName.
     * @return List of Patient class objects with this last name.
     */
    @Query(value = "SELECT * FROM patients WHERE last_name=?1", nativeQuery = true)
    List<Patient> findByLastName(String lastName);

    /**
     * Method find patients by gender.
     *
     * @param gender Patient gender.
     * @return List of Patient class objects with this gender.
     */
    @Query(value = "SELECT * FROM patients WHERE gender=?1", nativeQuery = true)
    List<Patient> findByGender(String gender);


    @Modifying
    @Query(value = "UPDATE patients SET last_name=?2,"
            + "first_name=?3,"
            + "gender=?4,"
            + "home_number=?5,"
            + "mobile_number=?6,"
            + "death_date=?7"
            + " WHERE patient_id = ?1", nativeQuery = true)
    void updateById(Integer id, String lastName, String firstName,
                    String gender, String homeNumber,
                    String mobileNumber, Date deathDate);
}
