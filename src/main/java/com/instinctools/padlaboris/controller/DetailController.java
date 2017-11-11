package com.instinctools.padlaboris.controller;

import com.instinctools.padlaboris.dto.DetailDto;
import com.instinctools.padlaboris.model.Detail;
import com.instinctools.padlaboris.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Rest Controller for Detail.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DetailController {

    private final DetailService detailService;

    /**
     * Mapper for convert DetailDto to Detail and back.
     */
    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Method for creating Detail.
     *
     * @param detailDto DetailDto detailDto.
     * @param patientId Patient patientId.
     * @return created DetailDto detailDto.
     */
    @RequestMapping(value = "/patients/{patientId}/details", method = RequestMethod.POST)
    public ResponseEntity createDetail(@RequestBody final DetailDto detailDto, @PathVariable final Integer patientId) {

        final Detail detailToCreate = dozerBeanMapper.map(detailDto, Detail.class);

        final Detail detailToResponse = detailService.create(patientId, detailToCreate);

        final DetailDto response = dozerBeanMapper.map(detailToResponse, DetailDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for updating Detail.
     *
     * @param detailDto DetailDto detailDto.
     * @param id        Detail id.
     * @return updated DetailDto detailDto.
     */
    @RequestMapping(value = "/patients/{patientId}/details/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateDetail(@RequestBody final DetailDto detailDto, @PathVariable final Integer id) {

        final Detail detailToUpdate = dozerBeanMapper.map(detailDto, Detail.class);

        final Detail detailToResponse = detailService.updateById(id, detailToUpdate);

        final DetailDto response = dozerBeanMapper.map(detailToResponse, DetailDto.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/patients/{patientId}/details/{id}", method = RequestMethod.GET)
    public ResponseEntity fetchDetail(@PathVariable final Integer id) {

        final Detail detailToResponse = detailService.fetch(id);

        final DetailDto response = dozerBeanMapper.map(detailToResponse, DetailDto.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseEntity fetchAllDetails() {

        return ResponseEntity.ok().body(detailService.listDetails());
    }

    /**
     * Method for deleting Detail from database.
     *
     * @param id Detail id.
     * @return deleted DetailDto detailDto.
     */
    @RequestMapping(value = "/patients/{patientId}/details/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteDetail(@PathVariable final Integer id) {

        final Detail detailToResponse = detailService.fetch(id);

        final DetailDto response = dozerBeanMapper.map(detailToResponse, DetailDto.class);

        detailService.delete(id);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/patients/{patientId}/details/bloodType/{bloodType}", method = RequestMethod.GET)
    public ResponseEntity fetchByBloodType(@PathVariable final Integer bloodType) {

        return ResponseEntity.ok().body(detailService.findByBloodType(bloodType));
    }

    @RequestMapping(value = "/patients/{patientId}/details/rh/{rhesusFactor}", method = RequestMethod.GET)
    public ResponseEntity fetchByRhesusFactor(@PathVariable final String rhesusFactor) {

        return ResponseEntity.ok().body(detailService.findByRhesusFactor(rhesusFactor));
    }
}
