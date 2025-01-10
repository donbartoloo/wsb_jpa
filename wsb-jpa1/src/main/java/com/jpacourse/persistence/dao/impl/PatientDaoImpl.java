package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

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

    public List<PatientEntity> findByLastName(String lastName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatientEntity> query = cb.createQuery(PatientEntity.class);
        Root<PatientEntity> root = query.from(PatientEntity.class);
        query.select(root).where(cb.equal(root.get("lastName"), lastName));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<VisitEntity> findVisitsByPatientId(Long patientId) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) {
            throw new EntityNotFoundException(patientId);
        }
        return patient.getVisits();
    }
}
