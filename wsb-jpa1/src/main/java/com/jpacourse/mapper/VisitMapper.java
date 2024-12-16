package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;
import java.util.stream.Collectors;

public class VisitMapper {

    public static VisitTO mapToTO(VisitEntity visitEntity) {
        if (visitEntity == null){
            return null;
        }
        VisitTO visitTO = new VisitTO();
        DoctorEntity doctorEntity = visitEntity.getDoctor();
        visitTO.setTime(visitEntity.getTime());

        if (doctorEntity != null){
            visitTO.setDoctorFirstName(doctorEntity.getFirstName());
            visitTO.setDoctorLastName(doctorEntity.getLastName());
        }
        if (visitEntity.getMedicalTreatments() != null) {
            List<String> treatmentNames = visitEntity.getMedicalTreatments().stream()
                    .map(treatment -> treatment.getType().name())
                    .collect(Collectors.toList());
            visitTO.setMedicalTreatments(treatmentNames);
        }

        return visitTO;
    }
}

