package com.instinctools.padlaboris.repository;

import com.instinctools.padlaboris.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Interface for working with the database.
 */
@Transactional
public interface DetailRepository extends JpaRepository<Detail, Integer> {
    /**
     * Method find details by rhesusFactor.
     *
     * @param rhesusFactor Detail rhesusFactor.
     * @return List of Detail class objects with this rhesusFactor.
     */
    @Query(value = "SELECT * FROM details WHERE RH=?1", nativeQuery = true)
    List<Detail> findByRhesusFactor(String rhesusFactor);

    /**
     * Method find details by blood type.
     *
     * @param bloodType Detail bloodType.
     * @return List of Detail class objects with this blood type.
     */
    @Query(value = "SELECT * FROM details WHERE blood_type=?1", nativeQuery = true)
    List<Detail> findByBloodType(Integer bloodType);

    @Modifying
    @Query(value = "UPDATE details SET height=?2,"
            + "weight=?3,bmi=?4,blood_type=?5,RH=?6,"
            + "degree_of_disability=?7 WHERE detail_id = ?1", nativeQuery = true)
    void updateById(Integer id, double height, double weight, double bMI,
                    int bloodType, String rH, int degreeOfDisability);
}
