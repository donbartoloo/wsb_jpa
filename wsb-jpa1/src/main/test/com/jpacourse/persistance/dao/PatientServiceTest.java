package com.jpacourse.persistance.dao;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Transactional
    @Test
    public void testShouldFindAllVisitsByPatientId() {
        // given
        Long patientId = 2L; // Pacjent Zofia Mazur w data.sql

        // when
        List<VisitTO> visits = patientService.findVisitsByPatientId(patientId);

        // then
        assertThat(visits).isNotNull();
        assertThat(visits.size()).isGreaterThan(0);
        assertThat(visits.get(0).getDoctorFirstName()).isNotEmpty(); // Przykład sprawdzenia szczegółów wizyty
        assertThat(visits.get(0).getTime()).isNotNull(); // Sprawdzenie, czy czas wizyty nie jest pusty
    }
}