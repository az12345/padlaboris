package com.instinctools.padlaboris.model;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.persistence.CascadeType;
import java.util.Date;
import java.util.Objects;


/**
 * Entity that describes the patient.
 */
@Data
@Entity
@Table(name = "patients")
public class Patient implements Persistable<Integer> {

    private static final long serialVersionUID = -3036693743601488048L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "home_number")
    private String homeNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "last_change_date")
    @UpdateTimestamp
    private Date lastChangeDate;

    @Column(name = "death_date")
    private Date deathDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "detail_id")
    private Detail details;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }
}
