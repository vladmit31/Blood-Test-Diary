CREATE TABLE IF NOT EXISTS patient (
    id INT NOT NULL AUTO_INCREMENT,
    vnumber VARCHAR(30) NOT NULL,
    fname VARCHAR(30) NOT NULL,
    sname VARCHAR(30) NOT NULL,
    dob DATE NOT NULL,
    local_clinic VARCHAR(30) NOT NULL,
    next_appointment DATE NOT NULL,
    refresh_rate DOUBLE NOT NULL,
    PRIMARY KEY(id),
);

CREATE TABLE if NOT EXISTS appointment (
    app_id INT NOT NULL AUTO_INCREMENT,
    status INT NOT NULL,
    due_date DATE NOT NULL,
    patient_id INT NOT NULL,
    PRIMARY KEY(app_id),
    FOREIGN KEY(patient_id) REFERENCES patient(id)
);

CREATE TABLE if NOT EXISTS contacts (
    id INTEGER NOT NULL PRIMARY KEY,
    e-mail VARCHAR(30) NOT NULL,
    relation_with_patient VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    patient_V_no VARCHAR(30) NOT NULL
);

