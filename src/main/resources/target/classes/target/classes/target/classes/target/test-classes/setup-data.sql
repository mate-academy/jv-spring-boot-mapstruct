INSERT INTO student_groups (id, name) VALUES (2, 'HR');
INSERT INTO student_groups (id, name) VALUES (3, 'Engineering');

INSERT INTO subjects (id, name) VALUES (2, 'Java');
INSERT INTO subjects (id, name) VALUES (3, 'Spring Boot');

INSERT INTO students (id, name, email, social_security_number, group_id) VALUES (2, 'John Doe', 'john.doe@example.com', '123-45-6789', 2);
INSERT INTO students (id, name, email, social_security_number, group_id) VALUES (3, 'Jane Doe', 'jane.doe@example.com', '987-65-4321', 3);

INSERT INTO student_subject (student_id, subject_id) VALUES (2, 2);
INSERT INTO student_subject (student_id, subject_id) VALUES (2, 3);
INSERT INTO student_subject (student_id, subject_id) VALUES (3, 3);
