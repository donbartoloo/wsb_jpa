package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    void addVisit(Long patientId, Long doctorId, LocalDateTime time, String description);
    List<PatientEntity> findByLastName(String lastName);
    List<VisitEntity> findVisitsByPatientId(Long patientId);
    List<PatientEntity> findPatientsWithMoreThanXVisits(int numberOfVisits);
    List<PatientEntity> findPatientsWithWeightLessThan(double weight);
}
