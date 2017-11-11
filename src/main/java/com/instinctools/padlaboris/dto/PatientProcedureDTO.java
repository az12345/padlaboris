package com.instinctools.padlaboris.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.util.Date;

@Data
@JsonTypeName(value = "patientProcedure")
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientProcedureDTO {
    @JsonProperty
    private Integer uniqueId;
    @JsonProperty
    private String procedureName;
    @JsonProperty
    private Date date;
    @JsonProperty
    private String md;
}
