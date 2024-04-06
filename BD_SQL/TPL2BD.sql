DROP TABLE LOUER;
DROP TABLE MATERIEL;
DROP TABLE SUIVRE;
DROP TABLE COURS;
DROP TABLE SPECIALITE;
DROP TABLE ADHERENT;
DROP TABLE MONITEUR;

CREATE TABLE MONITEUR 
(
  noM NUMBER
, nomM VARCHAR2(30 BYTE) 
, prenomM VARCHAR2(30 BYTE) 
, adresse VARCHAR2(50 BYTE) 
, dateNaiss DATE
, CONSTRAINT pk_moniteur
        PRIMARY KEY(noM)
);

CREATE TABLE ADHERENT 
(
  noA NUMBER
, nomA VARCHAR2(30 BYTE) 
, prenomA VARCHAR2(30 BYTE) 
, adresseA VARCHAR2(50 BYTE) 
, telA NUMBER
, ageA NUMBER
, CONSTRAINT pk_adherent
        PRIMARY KEY(noA)
, CONSTRAINT ck_ageA CHECK(ageA>12 AND ageA<100)
);

CREATE TABLE SPECIALITE 
(
  idSpecialite NUMBER
, nomS VARCHAR2(30 BYTE) 
, CONSTRAINT pk_specialite
        PRIMARY KEY(idSpecialite)
, CONSTRAINT ck_noms CHECK(nomS IN('ski','snowboard','monoski','raquette'))
);

CREATE TABLE COURS 
(
  codeC NUMBER
, niveau NUMBER
, nbPlaces NUMBER
, dateCours DATE
, noM NUMBER
, idSpecialite NUMBER
, CONSTRAINT pk_cours
        PRIMARY KEY(codeC)
        
, CONSTRAINT fk_cours_moniteur
    FOREIGN KEY (noM)
         REFERENCES MONITEUR(noM)
         
, CONSTRAINT fk_cours_specialite
    FOREIGN KEY (idSpecialite)
         REFERENCES SPECIALITE(idSpecialite)   
, CONSTRAINT ck_nbPlaces CHECK(nbPlaces>0)
, CONSTRAINT ck_niveau CHECK(niveau IN('débutant','moyen','confirmé','compétition'))
);

CREATE TABLE SUIVRE 
(
  codeC NUMBER
, noA NUMBER
, CONSTRAINT pk_SUIVRE
        PRIMARY KEY(noA)
, CONSTRAINT fk_suivre_cours
    FOREIGN KEY (codeC)
         REFERENCES cours(codeC)
        
, CONSTRAINT fk_suivre_adherent
    FOREIGN KEY (noA)
         REFERENCES ADHERENT(noA)
);

CREATE TABLE MATERIEL 
(
  codeM NUMBER
, typeM VARCHAR2(30 BYTE) 
, marque VARCHAR2(30 BYTE) 
, prix NUMBER
, qteDispo NUMBER
, CONSTRAINT pk_materiel
        PRIMARY KEY(codeM)
, CONSTRAINT ck_qteDispo CHECK(qteDispo>0)
, CONSTRAINT ck_prix CHECK(prix>0)
);

CREATE TABLE LOUER 
(
  noA NUMBER
, codeM NUMBER
, quantite NUMBER
, CONSTRAINT pk_louer
        PRIMARY KEY(noA,codeM)
        
        
, CONSTRAINT fk_louer_materiel
    FOREIGN KEY (codeM)
         REFERENCES materiel(codeM)
        
, CONSTRAINT fk_louer_adherent
    FOREIGN KEY (noA)
         REFERENCES adherent(noA)
, CONSTRAINT ck_quantite CHECK(quantite>0)
);

INSERT INTO ADHERENT(noA,nomA,prenomA,adresseA,telA,ageA)
    VALUES(1,'Lars','Julien','12 rue principale, Muret','0666666666','13');

INSERT INTO MONITEUR(noM,nomM,prenomM,adresse,dateNaiss)
    VALUES(1,'Dupond','Jean','12 route du col, Germ','01-01-1980');

INSERT INTO MONITEUR(noM,nomM,prenomM,adresse,dateNaiss)
    VALUES(2,'Martin','Sophie','Route du lac, Loudenvielle','13-05-1988');