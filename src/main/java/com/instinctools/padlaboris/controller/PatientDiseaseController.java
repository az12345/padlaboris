package com.instinctools.padlaboris.controller;


import com.instinctools.padlaboris.dto.PatientDiseaseDTO;
import com.instinctools.padlaboris.model.PatientDisease;
import com.instinctools.padlaboris.service.PatientDiseaseService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "api/patientdisease", produces = APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientDiseaseController {
    @Autowired
    PatientDiseaseService patientDiseaseService;
    @Autowired
    private final DozerBeanMapper dozerBeanMapper;

    @GetMapping("/{id}")
    public ResponseEntity getPatientDiseaseEntityById(@PathVariable(name = "id") final Integer id){

        final PatientDisease patientDisease = patientDiseaseService.fetchById(id);

        final PatientDiseaseDTO patientDiseaseDTO = dozerBeanMapper.map(patientDisease, PatientDiseaseDTO.class);

        return ResponseEntity.status(OK).body(patientDiseaseDTO);

    }

    @GetMapping
    public ResponseEntity getAllPatientDesiase(){

        final List<PatientDisease> patientDiseaseList = patientDiseaseService.fetchAll();

        return ResponseEntity.ok().body(patientDiseaseList);

    }

    @PostMapping
    public ResponseEntity createPatientDesiase(@RequestBody final PatientDiseaseDTO patientDiseaseDTO){

        final PatientDisease patientDisease = dozerBeanMapper.map(patientDiseaseDTO, PatientDisease.class );

        final PatientDisease patientDiseaseToResponse = patientDiseaseService.create(patientDisease);

        final PatientDiseaseDTO patientDiseaseDTOResponse = dozerBeanMapper.map(patientDiseaseToResponse, PatientDiseaseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(patientDiseaseToResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateDetails(@RequestBody final PatientDiseaseDTO patientDiseaseDTO, @PathVariable final Integer id) {

        final PatientDisease patientDiseaseToUpdate = dozerBeanMapper.map(patientDiseaseDTO, PatientDisease.class);

        final PatientDisease patientDiseaseToResponse = patientDiseaseService.updateById(id, patientDiseaseToUpdate.getStartDate(), patientDiseaseToUpdate.getEndDate(), patientDiseaseToUpdate.getDeseaseCode(), patientDiseaseToUpdate.getDeseaseDescription());

        final PatientDiseaseDTO patientDisease = dozerBeanMapper.map(patientDiseaseToResponse, PatientDiseaseDTO.class);

        return ResponseEntity.ok().body(patientDisease);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDetails(@PathVariable final Integer id){

        final PatientDisease patientDisease = patientDiseaseService.fetchById(id);

        final PatientDiseaseDTO patientDiseaseDTO = dozerBeanMapper.map(patientDisease, PatientDiseaseDTO.class);

        patientDiseaseService.deleteById(id);

        return ResponseEntity.ok().body(patientDiseaseDTO);

    }






}
