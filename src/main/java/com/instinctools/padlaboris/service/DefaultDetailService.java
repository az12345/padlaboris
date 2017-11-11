package com.instinctools.padlaboris.service;

import com.instinctools.padlaboris.model.Detail;
import com.instinctools.padlaboris.model.Patient;
import com.instinctools.padlaboris.repository.DetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * class that implements the Detail's work and databases.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDetailService implements DetailService {

    private final DetailRepository detailRepository;

    private final PatientService patientService;

    @Override
    public Detail create(final Integer id, final Detail detail) {

        log.info("Detail added to database.");

        final Patient patient = patientService.fetch(id);

        patient.setDetails(detail);

        patientService.updateById(id, patient);

        return detailRepository.save(detail);
    }

    @Override
    public Detail fetch(final Integer id) {

        log.info("Detail displayed.");

        return detailRepository.findOne(id);
    }

    @Override
    public void delete(final Integer id) {

        log.info("Detail deleted from database.");

        detailRepository.delete(id);
    }

    @Override
    public List<Detail> findByRhesusFactor(final String findByRhesusFactor) {

        log.info("Detail were find by findByRhesusFactor and displayed.");

        return detailRepository.findByRhesusFactor(findByRhesusFactor);
    }


    @Override
    public List<Detail> findByBloodType(final Integer bloodType) {

        log.info("Detail were find by blood type and displayed.");

        return detailRepository.findByBloodType(bloodType);
    }

    @Override
    public List<Detail> listDetails() {

        log.info("All patients were displayed");

        return detailRepository.findAll();
    }

    @Override
    public Detail updateById(final Integer id, final Detail detail) {

        log.info("Detail updated.");

        final Detail saved = detailRepository.findOne(id);

        detailRepository.updateById(id,
                detail.getHeight() == 0 ? saved.getHeight() : detail.getHeight(),
                detail.getWeight() == 0 ? saved.getWeight() : detail.getWeight(),
                detail.getBmi() == 0 ? saved.getBmi() : detail.getBmi(),
                detail.getBloodType() == 0 ? saved.getBloodType() : detail.getBloodType(),
                Objects.isNull(detail.getRhesusFactor()) ? saved.getRhesusFactor() : detail.getRhesusFactor(),
                detail.getDegreeOfDisability() == 0
                        ? saved.getDegreeOfDisability() : detail.getDegreeOfDisability());

        return saved;
    }
}
