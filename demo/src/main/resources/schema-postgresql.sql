-- DROP TABLE IF EXISTS survey;
-- DROP TABLE IF EXISTS response;
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
 
CREATE TABLE patient (
    patient_id Bigserial PRIMARY KEY NOT NULL,
    firstname varchar(36),
    lastname varchar(100),
    gender varchar(100),
    age INTEGER NOT NULL,
    identityNumber varchar(100),
    isChurn BOOLEAN,
    result varchar(200),
    registeredDateTime timestamp,
    calledTime timestamp,
    finishTreatmentTime timestamp,
    getMedicineTime timestamp,
    doctor_id INTEGER
);

CREATE TABLE medicine (

    id Bigserial PRIMARY KEY NOT NULL,
    patient_Id INTEGER,
    medicine VARCHAR(36),
    total INTEGER
);

CREATE TABLE doctor(
    id Bigserial PRIMARY KEY NOT NULL,
    name varchar(100),
    username varchar(100),
    password varchar(100)
);

CREATE TABLE admins(
    id Bigserial PRIMARY KEY NOT NULL,
    name varchar(100),
    username varchar(100),
    password varchar(100)
);

CREATE TABLE apoteker(
    id Bigserial PRIMARY KEY NOT NULL,
    name varchar(100),
    username varchar(100),
    password varchar(100)
);


ALTER TABLE medicine
  ADD CONSTRAINT patient_fkey FOREIGN KEY (patient_Id)
      REFERENCES patient (patient_Id)
      ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE patient
  ADD CONSTRAINT doctor_fkey FOREIGN KEY (doctor_id)
      REFERENCES doctor (id)
      ON UPDATE CASCADE ON DELETE CASCADE;


-- init doctor
 INSERT INTO DOCTOR(name, username, password)
 VALUES('Prof. Dr. L. A. Lesmana, Ph.D, FACG','lesmana','lesmana');
 INSERT INTO DOCTOR(name, username, password)
 VALUES('Prof. Dr. Suwandhi Widjaja, Ph.D','suwandhi','suwandhi');
 INSERT INTO DOCTOR(name, username, password)
 VALUES('Prof. Dr. T. Santoso S., MD, FACC, FESC','santoso','santoso');
 INSERT INTO DOCTOR(name, username, password)
 VALUES('Prof. Dr. Abdul Muthalib, SpPD,KHOM','abdul','abdul');

-- init admin
 INSERT INTO admins(name, username, password)
 VALUES('Rena Siregar','siregar','siregar');
 INSERT INTO admins(name, username, password)
 VALUES('Weny Musdalifa','weny', 'weny');
 INSERT INTO admins(name, username, password)
 VALUES('Ardi Setyawan','ardi','ardi');
 INSERT INTO admins(name, username, password)
 VALUES('Bagus Gilang','bagus','bagus');

-- init apoteker
 INSERT INTO apoteker(name, username, password)
 VALUES('Muhammad Yusro','yusro','yusro');
 INSERT INTO apoteker(name, username, password)
 VALUES('Diah Ajeng','ajeng','ajeng');
 INSERT INTO apoteker(name, username, password)
 VALUES('Dita Anugrah','dita','dita');
 INSERT INTO apoteker(name, username, password)
 VALUES('Basya Niar','basya','basya');


-- init patient

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Muhammad','Solihin',85,'M','21345',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Bati','Muljadi',86,'F','424235',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Moh','Talim',86,'M','23421',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Kaliman','',85,'M','23144',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Komari','',85,'F','5234',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Risna','Wati',24,'F','34244',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Dewi','Marlina',27,'F','34244',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Rini','Febriani',30,'F','23444',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Eni','Murni',24,'F','74566',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Desi','Mailina',22,'F','3421345',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Arda','Wiliam',25,'M','34235',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Nova','Ryando',31,'M','4324',false,CURRENT_TIMESTAMP);

 insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
 values('Nasrul','Hamdi',45,'M','23123',false,CURRENT_TIMESTAMP);

  insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
  values('Aditya','Saputra',25,'M','4243234',false,CURRENT_TIMESTAMP);

  insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
  values('Ria','Indra',24,'F','243423',false,CURRENT_TIMESTAMP);

  insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
  values('Lani','Maria',22,'F','55322',false,CURRENT_TIMESTAMP);

  insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
  values('Bangun','Tapan',25,'M','23423',false,CURRENT_TIMESTAMP);

  insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
  values('Niko','Ryando',31,'M','234234',false,CURRENT_TIMESTAMP);

  insert into patient(firstname,lastname,age,gender,identitynumber,ischurn,registereddatetime)
  values('Harun','Hamdi',45,'M','324243',false,CURRENT_TIMESTAMP);
