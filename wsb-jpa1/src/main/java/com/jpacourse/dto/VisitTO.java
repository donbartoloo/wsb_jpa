package com.jpacourse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class VisitTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    private String doctorFirstName;
    private String doctorLastName;
    private List<String> medicalTreatments;

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public String getDoctorFirstName() {
        return doctorFirstName;
    }
    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }
    public String getDoctorLastName() {
        return doctorLastName;
    }
    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }
    public List<String> getMedicalTreatments() {
        return medicalTreatments;
    }
    public void setMedicalTreatments(List<String> medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }
}
