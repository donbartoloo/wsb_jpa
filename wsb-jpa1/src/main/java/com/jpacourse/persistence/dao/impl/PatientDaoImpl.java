package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Override
    public void addVisit(Long patientId, Long doctorId, LocalDateTime time, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) {
            throw new EntityNotFoundException(patientId);
        }
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        if (doctor == null) {
            throw new EntityNotFoundException(doctorId);
        }
        VisitEntity visit = new VisitEntity();
        visit.setTime(time);
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setDescription(description);

        patient.getVisits().add(visit);
        entityManager.merge(patient);

    }
}
