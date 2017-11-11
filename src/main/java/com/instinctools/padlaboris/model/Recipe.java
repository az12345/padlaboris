package com.instinctools.padlaboris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Persistable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * Entity of Recipe.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe")
public class Recipe implements Persistable<Integer> {

    private static final long serialVersionUID = 5202257027329539131L;

    @Id
    @Column(name = "recipe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date")
    @NonNull
    private Date issueDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "expire_date")
    @NonNull
    private Date expireDate;

    @Column(name = "medicine_name")
    @NonNull
    private String medicineName;

    @Column(name = "dosage")
    @NonNull
    private String dosage;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "MEDICAL_DOCTOR")
//    private String md;

}
