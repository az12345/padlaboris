package com.instinctools.padlaboris.controller;

import com.instinctools.padlaboris.dto.PatientProcedureDTO;
import com.instinctools.padlaboris.model.PatientProcedure;
import com.instinctools.padlaboris.service.DefaultPatientProcedureService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "api/patientprocedure", produces = APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientProcedureController {

    @Autowired
    DefaultPatientProcedureService procedureService;
    @Autowired
    private final DozerBeanMapper dozerBeanMapper;


    @GetMapping("/{id}")
    public ResponseEntity getPatientProcedureEntityById(@PathVariable final Integer id){

        final PatientProcedure patientProcedure = procedureService.fetchById(id);

        final PatientProcedureDTO patientProcedureDTO = dozerBeanMapper.map(patientProcedure, PatientProcedureDTO.class);

        return ResponseEntity.ok().body(patientProcedureDTO);

    }

    @GetMapping
    public  ResponseEntity getPatientProcedureEntity(){

        return ResponseEntity.ok().body(procedureService.fetchAll());

    }

    @PostMapping
    public ResponseEntity createPatientProcedure(@RequestBody PatientProcedureDTO patientProcedureDTO){

        final PatientProcedure patientProcedureCreate = dozerBeanMapper.map(patientProcedureDTO, PatientProcedure.class);

        final PatientProcedure patientProcedure = procedureService.create(patientProcedureCreate);

        final PatientProcedureDTO procedureDTO = dozerBeanMapper.map(patientProcedure, PatientProcedureDTO.class);

        return ResponseEntity.ok().body(procedureDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity updatePatientProcedure(@RequestBody PatientProcedureDTO patientProcedureDTO, @PathVariable final Integer id){

        final PatientProcedure patientProcedureCreate = dozerBeanMapper.map(patientProcedureDTO, PatientProcedure.class);

        final PatientProcedure patientProcedure = procedureService.updateById( id, patientProcedureCreate);

        final PatientProcedureDTO procedureDTO = dozerBeanMapper.map(patientProcedure, PatientProcedureDTO.class);

        return ResponseEntity.ok().body(procedureDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatientProcedure(@PathVariable  final Integer id){

        final PatientProcedure patientProcedure = procedureService.fetchById(id);

        final PatientProcedureDTO patientProcedureDTO = dozerBeanMapper.map(patientProcedure, PatientProcedureDTO.class);

        procedureService.deleteById(id);

        return ResponseEntity.ok().body(patientProcedureDTO);

    }

}
