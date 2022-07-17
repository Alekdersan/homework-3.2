-- liquibase formatted sql

-- changeset afadeev:1
CREATE INDEX student_name_idx ON student (name);

CREATE INDEX faculty_tc_idx ON faculty (title, color);
