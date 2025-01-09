package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

import java.util.List;

public interface PatientService {

    public PatientTO findById(final Long id);
    List<PatientTO> findByLastName(String lastName);
}
