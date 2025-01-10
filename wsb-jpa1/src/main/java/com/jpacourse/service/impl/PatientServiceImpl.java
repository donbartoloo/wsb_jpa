package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao pPatientDao) { patientDao = pPatientDao; }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }
    @Override
    public List<PatientTO> findByLastName(String lastName) {
        List<PatientEntity> patients = patientDao.findByLastName(lastName);
        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<VisitTO> findVisitsByPatientId(Long patientId) {
        List<VisitEntity> visits = patientDao.findVisitsByPatientId(patientId);
        return visits.stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<PatientTO> findPatientsWithMoreThanXVisits(int numberOfVisits) {
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(numberOfVisits);
        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientTO> findPatientsWithWeightLessThan(double weight) {
        List<PatientEntity> patients = patientDao.findPatientsWithWeightLessThan(weight);
        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
