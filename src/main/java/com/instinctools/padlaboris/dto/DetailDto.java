package com.instinctools.padlaboris.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.io.Serializable;

/**
 * Intermediate class of Detail and controller work.
 */
@Data
@JsonTypeName(value = "detail")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailDto implements Serializable {

    private static final long serialVersionUID = 4201434315802854503L;

    @JsonProperty
    private double height;

    @JsonProperty
    private double weight;

    @JsonProperty
    private double bMI;

    @JsonProperty
    private Integer bloodType;

    @JsonProperty
    private String rhesusFactor;

    @JsonProperty
    private Integer degreeOfDisability;
}
