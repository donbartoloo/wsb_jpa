package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Test
    public void testShouldFindPatientsByLastName() {
        // given
        String lastName = "Mazur";

        // when
        List<PatientEntity> patients = patientDao.findByLastName(lastName);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isGreaterThan(0);
        assertThat(patients.get(0).getLastName()).isEqualTo(lastName);
    }

    @Transactional
    @Test
    public void testShouldFindPatientsWithMoreThanXVisits() {
        // given
        int minVisits = 1; // Oczekujemy pacjentów z więcej niż jedną wizytą (z danych w data.sql)

        // when
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(minVisits);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isGreaterThan(0); // Lista powinna zawierać co najmniej jednego pacjenta
        assertThat(patients.get(0).getVisits().size()).isGreaterThan(minVisits); // Pierwszy pacjent musi mieć więcej niż 1 wizytę
    }

    @Transactional
    @Test
    public void testShouldFindPatientsWithWeightLessThan() {
        // given
        double maxWeight = 70.0; // Szukamy pacjentów o wadze mniejszej niż 70.0

        // when
        List<PatientEntity> patients = patientDao.findPatientsWithWeightLessThan(maxWeight);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isGreaterThan(0); // Lista powinna zawierać co najmniej jednego pacjenta
        assertThat(patients.get(0).getWeight()).isLessThan(maxWeight); // Pierwszy pacjent musi mieć wagę < 70.0
    }

    @Transactional
    @Test
    public void testShouldThrowOptimisticLockException() {
        // given
        Long patientId = 1L; // Zakładamy, że pacjent o ID 1 istnieje
        PatientEntity patient1 = entityManager.find(PatientEntity.class, patientId);
        PatientEntity patient2 = entityManager.find(PatientEntity.class, patientId);

        // when
        patient1.setWeight(70.0); // Zmiana wagi w jednym kontekście
        entityManager.persist(patient1);
        entityManager.flush(); // Zatwierdzenie zmian z pierwszego kontekstu

        // then
        patient2.setWeight(75.0); // Próba zmiany w drugim kontekście
        assertThatThrownBy(() -> {
            entityManager.persist(patient2);
            entityManager.flush(); // Zatwierdzenie zmian z drugiego kontekstu
        }).isInstanceOf(OptimisticLockException.class);
    }
}