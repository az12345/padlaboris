package com.instinctools.padlaboris.model;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.util.Objects;

/**
 * Entity which details the Patient.
 */
@Entity
@Data
@Table(name = "details")
public class Detail implements Persistable<Integer> {

    private static final long serialVersionUID = 529475928140022917L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer id;

    @Column(name = "height")
    private double height;

    @Column(name = "weight")
    private double weight;

    @Column(name = "bmi")
    private double bmi;

    @Column(name = "blood_type")
    private int bloodType;

    @Column(name = "RH")
    private String rhesusFactor;

    @Column(name = "degree_of_disability")
    private int degreeOfDisability;

    @Column(name = "owner_id")
    private Integer patientId;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }
}
