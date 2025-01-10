-- ADDRESS
insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'Cytrynowa', '10', 'Poznań', '62-030');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (2, 'Rynek', '2', 'Wrocław', '50-072');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (3, 'Złota', '44', 'Warszawa', '00-120');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (4, 'Zamek Wawel', '2', 'Kraków', '31-001');
insert into address (id, address_line1, address_line2, city, postal_code)
            values (5, 'Piotrkowska', '12', 'Łódź', '90-406');

-- DOCTOR
insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
            values (1, 'Jan', 'Kowalski', '555333222',
                    'jan.kowalski@domain.com','1', 'SURGEON', 1);
insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
            values (2, 'Beata', 'Nowak', '666111444',
                    'beata.nowak@domain.com','2', 'OCULIST', 2);

-- PATIENT
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, blood_type)
            values (1, 'Krzysztof', 'Wiśniewski', '888777333',
                    'krzysztof.wisniewski@domain.com', '1', '1985-06-15', 3, 'AB_NEGATIVE');
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, blood_type)
            values (2, 'Zofia', 'Mazur', '884727333',
                    'zofia.mazur@domain.com', '2', '1995-12-13', 3, 'A_POSITIVE');
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, blood_type)
            values (3, 'Adam', 'Mazur', '854857123',
                    'adam.mazur@domain.com', '1', '1993-11-10', 3, 'A_POSITIVE');

-- VISIT
insert into visit(id, description, time, doctor_id, patient_id)
            values (1, 'Wizyta', '2024-11-25 10:30:00',1,1);
insert into visit(id, description, time, doctor_id, patient_id)
            values (2, 'Wizyta', '2024-11-26 12:15:00',2,2);
insert into visit(id, description, time, doctor_id, patient_id)
            values (3, 'Wizyta', '2024-11-26 12:17:00',1,2);

-- MEDICAL TREATMENT
insert into medical_treatment(id, description, type, visit_id)
            values (1,'Badanie','USG',1);
insert into medical_treatment(id, description, type, visit_id)
            values (2,'Badanie','RTG',2);