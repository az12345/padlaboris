package com.instinctools.padlaboris.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Intermediate class of Disease and controller work.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseDto implements Serializable {

    private static final long serialVersionUID = 2710050838305766544L;

    private int id;
    private String diseaseName;
    private String diseaseCode;
    private String diseaseDescription;
    private String diseaseClass;
}
