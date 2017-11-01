package com.instinctools.padlaboris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "diseases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disease implements Persistable<Integer> {

    private static final long serialVersionUID = -7297453128549936965L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "disease_name")
    private String diseaseName;

    @Column(name = "disease_code")
    private String diseaseCode;

    @Column(name = "disease_description")
    private String diseaseDescription;

    @Column(name = "disease_class")
    private String diseaseClass;

    @Override
    public boolean isNew() {
        return id != null;
    }
}
