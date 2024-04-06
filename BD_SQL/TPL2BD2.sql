DROP TABLE GROUPE;
DROP TABLE JOUR;
DROP TABLE CHAINE;
DROP TABLE FONCTION;
DROP TABLE HORAIRE;
DROP TABLE PERSONNE;
DROP TABLE AVIS;
DROP TABLE EMISSION;
DROP TABLE Diffuser;
DROP TABLE Intervenir;

CREATE TABLE GROUPE
(
    NomG VARCHAR2(30),
    DateCreationG DATE,
    CONSTRAINT ck_nomG PRIMARY KEY(NomG)
);

CREATE TABLE JOUR
(
    DateJ DATE,
    CONSTRAINT ck_dateJ PRIMARY KEY(DateJ)
);

CREATE TABLE CHAINE
(
    NomCH VARCHAR2(30),
    DateCreationCH DATE,
    nomG VARCHAR(30),
    CONSTRAINT ck_nomCH PRIMARY KEY(NomCH),
    CONSTRAINT fk_chaine_groupe FOREIGN KEY (NomG) REFERENCES GROUPE(nomG)
);

CREATE TABLE HORAIRE
(
    HeureDebut VARCHAR2(5),
    CONSTRAINT pk_heureSize CHECK (LENGTHB(HeureDebut)=5),
    CONSTRAINT ck_heureDebut PRIMARY KEY (HeureDebut)
);

CREATE TABLE FONCTION
(
    NomF VARCHAR2(30),
    CONSTRAINT pk_nomF CHECK (NomF='r�alisateur' OR NomF='acteur' OR NomF='journaliste' OR NomF='animateur' OR NomF='cr�ateur' OR NomF='narrateur'),
    CONSTRAINT ck_nomF PRIMARY KEY (NomF)
);

CREATE TABLE PERSONNE
(
    NumP NUMBER(20),
    Nom VARCHAR2(30) NOT NULL,
    Prenom VARCHAR2(30) NOT NULL,
    CONSTRAINT ck_numP PRIMARY KEY (NumP)
);

CREATE TABLE AVIS
(
    NumA NUMBER(20),
    DescriptionA VARCHAR2(100),
    Note NUMBER(1),
    CONSTRAINT ck_note CHECK (Note>=0 AND Note<=4),
    CONSTRAINT ck_numA PRIMARY KEY (NumA)
);

CREATE TABLE EMISSION
(
    ReferenceE VARCHAR2(30),
    Titre VARCHAR2(30),
    ResumeE VARCHAR(300),
    TypeE VARCHAR(30),
    Duree VARCHAR2(5),
    nomG VARCHAR2(30),
    numA NUMBER(20),
    CONSTRAINT ck_referenceE PRIMARY KEY (ReferenceE),
    CONSTRAINT fk_emission_groupe FOREIGN KEY (nomG) REFERENCES GROUPE(NomG),
    CONSTRAINT fk_emission_avis FOREIGN KEY (numA) REFERENCES AVIS(NumA)
);

CREATE TABLE Diffuser
(
    nomCH VARCHAR2(30),
    dateJ DATE,
    heureDebut VARCHAR2(5),
    referenceE VARCHAR2(30),
    HeureFin VARCHAR2(5),
    CONSTRAINT ck_ndh PRIMARY KEY (nomCH, dateJ, heureDebut),
    CONSTRAINT fk_diffuser_chaine FOREIGN KEY (nomCH) REFERENCES CHAINE(NomCH),
    CONSTRAINT fk_diffuser_jour FOREIGN KEY (dateJ) REFERENCES JOUR(DateJ),
    CONSTRAINT fk_diffuser_horaire FOREIGN KEY (heureDebut) REFERENCES HORAIRE(HeureDebut),
    CONSTRAINT fk_diffuser_emission FOREIGN KEY (referenceE) REFERENCES EMISSION(ReferenceE)
);

CREATE TABLE Intervenir
(
    referenceE VARCHAR2(30),
    numP NUMBER(20),
    nomF VARCHAR2(30),
    CONSTRAINT ck_rnn PRIMARY KEY (referenceE, numP, nomF),
    CONSTRAINT fk_intervenir_personne FOREIGN KEY (numP) REFERENCES PERSONNE(NumP),
    CONSTRAINT fk_intervenir_fonction FOREIGN KEY (nomF) REFERENCES FONCTION(NomF),
    CONSTRAINT fk_intervenir_emission FOREIGN KEY (referenceE) REFERENCES EMISSION(ReferenceE)
);

INSERT INTO GROUPE VALUES('Groupe M6', '23/02/87');
INSERT INTO GROUPE VALUES('TF1 SA', '16/04/87');
INSERT INTO GROUPE VALUES('France T�l�visions', '07/09/92');
INSERT INTO JOUR VALUES('15/01/16');
INSERT INTO JOUR VALUES('16/01/16');
INSERT INTO CHAINE VALUES('M6', '01/03/87', 'Groupe M6');
INSERT INTO CHAINE VALUES('W9', '05/03/98', 'Groupe M6');
INSERT INTO CHAINE VALUES('6Ter', '01/12/12', 'Groupe M6');
INSERT INTO CHAINE VALUES('TF1', '06/01/75', 'TF1 SA');
INSERT INTO CHAINE VALUES('NT1', '31/03/05', 'TF1 SA');
INSERT INTO CHAINE VALUES('HD1', '12/12/12', 'TF1 SA');
INSERT INTO CHAINE VALUES('TMC', '19/11/54', 'TF1 SA');
INSERT INTO CHAINE VALUES('France 2', '07/09/92', 'France T�l�visions');
INSERT INTO CHAINE VALUES('France 3', '07/09/92', 'France T�l�visions');
INSERT INTO CHAINE VALUES('France 4', '31/03/05', 'France T�l�visions');
INSERT INTO CHAINE VALUES('France 5', '07/01/02', 'France T�l�visions');
INSERT INTO HORAIRE VALUES('21:00');
INSERT INTO HORAIRE VALUES('23:00');
INSERT INTO HORAIRE VALUES('19:00');
INSERT INTO HORAIRE VALUES('20:00');
INSERT INTO HORAIRE VALUES('19:30');
INSERT INTO HORAIRE VALUES('22:45');
INSERT INTO HORAIRE VALUES('22:40');
INSERT INTO HORAIRE VALUES('18:30');
INSERT INTO HORAIRE VALUES('22:30');
INSERT INTO HORAIRE VALUES('20:30');
INSERT INTO HORAIRE VALUES('18:45');
INSERT INTO HORAIRE VALUES('19:45');
INSERT INTO FONCTION VALUES('r�alisateur');
INSERT INTO FONCTION VALUES('acteur');
INSERT INTO FONCTION VALUES('animateur');
INSERT INTO FONCTION VALUES('narrateur');
INSERT INTO FONCTION VALUES('cr�ateur');
INSERT INTO FONCTION VALUES('journaliste');
INSERT INTO PERSONNE VALUES(1, 'Boccolini', 'Laurence');
INSERT INTO PERSONNE VALUES(2, 'Bouleau', 'Gilles');
INSERT INTO PERSONNE VALUES(3, 'Campos', 'Christophe');
INSERT INTO PERSONNE VALUES(4, 'Abril', 'Victoria');
INSERT INTO PERSONNE VALUES(5, 'Lucas', 'Lucie');
INSERT INTO PERSONNE VALUES(6, 'Bee', 'Guy Norman');
INSERT INTO PERSONNE VALUES(7, 'Patinkin', 'Mandy');
INSERT INTO PERSONNE VALUES(8, 'Gibson', 'Thomas');
INSERT INTO PERSONNE VALUES(9, 'Jenaly', 'Sylvie');
INSERT INTO PERSONNE VALUES(10, 'Savage', 'Fred');
INSERT INTO PERSONNE VALUES(11, 'Lewis', 'Phil');
INSERT INTO PERSONNE VALUES(12, 'Dennings', 'Kat');
INSERT INTO PERSONNE VALUES(13, 'Behrs', 'Beth');
INSERT INTO PERSONNE VALUES(14, 'Beaugrand', 'Christophe');
INSERT INTO PERSONNE VALUES(15, 'Fayer', 'Elsa');
INSERT INTO PERSONNE VALUES(16, 'Chulack', 'Christopher');
INSERT INTO PERSONNE VALUES(17, 'Tierney', 'Maura');
INSERT INTO PERSONNE VALUES(18, 'Stringfield', 'Sherry');
INSERT INTO PERSONNE VALUES(19, 'Thompson', 'J Lee');
INSERT INTO PERSONNE VALUES(20, 'Peck', 'Gregory');
INSERT INTO PERSONNE VALUES(21, 'Quinn', 'Anthony');
INSERT INTO PERSONNE VALUES(22, 'Niven', 'David');
INSERT INTO PERSONNE VALUES(23, 'Barth�s', 'Yann');
INSERT INTO PERSONNE VALUES(24, 'Brambilla', 'Marco');
INSERT INTO PERSONNE VALUES(25, 'Stallone', 'Sylvester');
INSERT INTO PERSONNE VALUES(26, 'Snipes', 'Wesley');
INSERT INTO PERSONNE VALUES(27, 'Bullock', 'Sandra');
INSERT INTO PERSONNE VALUES(28, 'Rousseau', 'Carole');
INSERT INTO PERSONNE VALUES(29, 'Nagui', NULL);
INSERT INTO PERSONNE VALUES(30, 'Pujadas', 'David');
INSERT INTO PERSONNE VALUES(31, 'Marchal', 'Olivier');
INSERT INTO PERSONNE VALUES(32, 'Wolkowitch', 'Bruno');
INSERT INTO PERSONNE VALUES(33, 'Molez', 'St�phanie');
INSERT INTO PERSONNE VALUES(34, 'K�bler', 'Thierry');
INSERT INTO PERSONNE VALUES(35, 'Gaessler', 'Carole');
INSERT INTO PERSONNE VALUES(36, 'Sannier', 'Henri');
INSERT INTO PERSONNE VALUES(37, 'Hassan', 'Michel');
INSERT INTO PERSONNE VALUES(38, 'Chevallier ', 'Pierre');
INSERT INTO PERSONNE VALUES(39, 'Letellier', 'Francis');
INSERT INTO PERSONNE VALUES(40, 'Senior', 'Rechard');
INSERT INTO PERSONNE VALUES(41, 'Smith ', 'Matt ');
INSERT INTO PERSONNE VALUES(42, 'Woreth', 'Eric');
INSERT INTO PERSONNE VALUES(43, 'Dul�ry ', 'Antoine');
INSERT INTO PERSONNE VALUES(44, 'Lapix', 'Anne-Sophie');
INSERT INTO PERSONNE VALUES(45, 'Cohen', 'Matthieu');
INSERT INTO PERSONNE VALUES(46, 'Roux', 'Caroline');
INSERT INTO PERSONNE VALUES(47, 'Toussaint', 'Bruce');
INSERT INTO PERSONNE VALUES(48, 'Minne', 'Olivier');
INSERT INTO PERSONNE VALUES(49, 'Zakrzewski', 'Alex');
INSERT INTO PERSONNE VALUES(50, 'Morris', 'Kathryn');
INSERT INTO PERSONNE VALUES(51, 'Plaza', 'St�phane');
INSERT INTO PERSONNE VALUES(52, 'Duquet', 'Francis');
INSERT INTO PERSONNE VALUES(53, 'De Moulins', 'Xavier');
INSERT INTO PERSONNE VALUES(54, 'De La Patelli�re', 'Alexandre');
INSERT INTO PERSONNE VALUES(55, 'Delaporte ', 'Mathieu');
INSERT INTO PERSONNE VALUES(56, 'Bruel', 'Patrick');
INSERT INTO PERSONNE VALUES(57, 'Benguigui', 'Val�rie');
INSERT INTO PERSONNE VALUES(58, 'Ferjani', 'Sophie');
INSERT INTO PERSONNE VALUES(59, 'Rivassoux', 'Emmanuelle');
INSERT INTO PERSONNE VALUES(60, 'Derennes', 'Miguel');
INSERT INTO PERSONNE VALUES(61, 'De Suzzoni', 'Marc');
INSERT INTO PERSONNE VALUES(62, 'Travolta', 'John');
INSERT INTO PERSONNE VALUES(63, 'Thurman', 'Uma');
INSERT INTO PERSONNE VALUES(64, 'Tarayre', 'Norbert');
INSERT INTO PERSONNE VALUES(65, 'Derrickson', 'Scott');
INSERT INTO PERSONNE VALUES(66, 'Reeves', 'Keanu');
INSERT INTO PERSONNE VALUES(67, 'Connelly', 'Jennifer');
INSERT INTO PERSONNE VALUES(68, 'Pidoux', 'Pidoux');
INSERT INTO PERSONNE VALUES(69, 'De Tonqu�dec', 'Guillaume');
INSERT INTO PERSONNE VALUES(70, 'Bonneton', 'Val�rie');
INSERT INTO PERSONNE VALUES(71, 'Turner', 'Jann');
INSERT INTO PERSONNE VALUES(72, 'Donnell', 'Colin');
INSERT INTO PERSONNE VALUES(73, 'Hudlin', 'Reginald');
INSERT INTO PERSONNE VALUES(74, 'Diggs', 'Taye');
INSERT INTO PERSONNE VALUES(75, 'Monnier', 'Philippe');
INSERT INTO PERSONNE VALUES(76, 'Mathy', 'Mimi');
INSERT INTO PERSONNE VALUES(77, 'Kappes', 'St�phane');
INSERT INTO PERSONNE VALUES(78, 'Keim', 'Claire');
INSERT INTO PERSONNE VALUES(79, 'Chazel', 'Marie-Anne');
INSERT INTO PERSONNE VALUES(80, 'Semel', 'David');
INSERT INTO PERSONNE VALUES(81, 'Laurie', 'Hugh');
INSERT INTO PERSONNE VALUES(82, 'Benz', 'Julie');
INSERT INTO PERSONNE VALUES(83, 'Fincher', 'David');
INSERT INTO PERSONNE VALUES(84, 'Pike', 'Rosamund');
INSERT INTO PERSONNE VALUES(85, 'Affleck', 'Ben');
INSERT INTO PERSONNE VALUES(86, 'Pernoud', 'Georges');
INSERT INTO PERSONNE VALUES(87, 'Luhrmann', 'Baz');
INSERT INTO PERSONNE VALUES(88, 'Di Caprio', 'Leonardo');
INSERT INTO PERSONNE VALUES(89, 'Maguire', 'Tobey');
INSERT INTO PERSONNE VALUES(90, 'Busnel', 'Fran�ois');
INSERT INTO PERSONNE VALUES(91, 'Etchebest', 'Philippe');
INSERT INTO PERSONNE VALUES(92, 'De La Villardi�re', 'Bernard');
INSERT INTO PERSONNE VALUES(93, 'Minghella', 'Anthony');
INSERT INTO PERSONNE VALUES(94, 'Law', 'Jude');
INSERT INTO PERSONNE VALUES(95, 'Kidman ', 'Nicole ');
INSERT INTO PERSONNE VALUES(96, 'Zellweger', 'Ren�e');
INSERT INTO PERSONNE VALUES(97, 'Drouin', 'Mathieu');
INSERT INTO PERSONNE VALUES(98, 'Berling', 'Charles');
INSERT INTO PERSONNE VALUES(99, 'Pancino', 'Lorenzo');
INSERT INTO PERSONNE VALUES(100, 'Tarentino', 'Quentin');
INSERT INTO PERSONNE VALUES(101, 'Jackson', 'Samuel L');
INSERT INTO PERSONNE VALUES(102, 'Lunel', 'Magali');
INSERT INTO AVIS VALUES(1, 'Une histoire prenante.', 5);
INSERT INTO AVIS VALUES(2, 'Le retour du p�re de Caro relance l''intrigue et malm�ne le personnage brillamment interpr�t� par Victoria Abril.', 4);
INSERT INTO AVIS VALUES(3, 'Sujet sensible trait� avec humour', 4);
INSERT INTO AVIS VALUES(4, 'Excellent. Rebondissements, action et suspense tiennent en haleine jusqu''au bout.', 5);
INSERT INTO AVIS VALUES(5, 'Une construction solide, une tr�s belle distribution, m�me si la r�alisation s''av�re un brin acad�mique.', 3);
INSERT INTO AVIS VALUES(6, 'De l''action, de la d�rision et un Sylvester Stallone impeccable. Un bon moment de cin�ma.', 4);
INSERT INTO AVIS VALUES(7, 'Un polar coup-de-poing, noir et ma�tris�', 4);
INSERT INTO AVIS VALUES(8, 'Une mise � nu touchante de ces couples � la d�rive qui m�ne � une r�flexion plus globale sur l''amour.', 2);
INSERT INTO AVIS VALUES(9, 'Les vignobles de Condrieu sur des coteaux abrupts et la transhumance vers la Camargue sont deux temps forts inoubliables.', 3);
INSERT INTO AVIS VALUES(10, 'Des r�v�lations, de l''action et des trouvailles amusantes comme les m�duses tueuses... ', 4);
INSERT INTO AVIS VALUES(11, 'Grande profondeur, tr�s noir.', 3);
INSERT INTO AVIS VALUES(12, 'Une intrigue complexe, habilement servie par une excellente distribution', 4);
INSERT INTO AVIS VALUES(13, 'Malgr� un c�t� th��tre film�, cette com�die fait mouche gr�ce � des com�diens parfaits et des r�pliques percutantes.', 4);
INSERT INTO AVIS VALUES(14, 'L''humour flirte ici avec le d�risoire. John Travolta est absolument �tonnant. Un vrai r�gal.', 5);
INSERT INTO AVIS VALUES(15, 'Spectaculaire sur la forme, ce remake du classique de Robert Wise souffre h�las d''un sc�nario in�gal.', 1);
INSERT INTO AVIS VALUES(16, 'Concilie situations r�alistes et humour avec efficacit�.', 4);
INSERT INTO AVIS VALUES(17, 'Le rythme est soutenu et la tension pr�sente.', 3);
INSERT INTO AVIS VALUES(18, 'Le d�cor naturel est magnifique et l''ambiance survolt�e', 3);
INSERT INTO AVIS VALUES(19, 'Avec son �patante ma�trise du r�cit, David Fincher construit un thriller tr�s prenant, labyrinthique et sombre.', 4);
INSERT INTO AVIS VALUES(20, 'Un film flamboyant et amer � l''image soign�e', null);
INSERT INTO AVIS VALUES(21, 'Une superbe fresque romanesque, pleine de passion, servie par des interpr�tes inspir�s.', 5);
INSERT INTO EMISSION VALUES('F1', 'Clem', 'En revenant � la boutique, Caro d�couvre que son p�re, Jos�, est avec Solange�', 'cin�ma', 120, 'TF1 SA', 2);
INSERT INTO EMISSION VALUES('F2', 'Esprits criminels', 'Appel�e au secours par les autorit�s locales, l''�quipe au grand complet s''envole pour le Mexique.', 'cin�ma', 60, 'TF1 SA', 1);
INSERT INTO EMISSION VALUES('F3', '2 Broke Girls', 'Et le pire autoportrait de l''histoire...', 'cin�ma', 120, 'TF1 SA', 3);
INSERT INTO EMISSION VALUES('F4', 'Urgences', 'Chute libre', 'cin�ma', 120, 'TF1 SA', 4);
INSERT INTO EMISSION VALUES('F5', 'Les canons de Navarone', 'En 1943, deux mille soldats britanniques sont bloqu�s sur l''�le grecque de Kh�ros, en mer Eg�e.', 'cin�ma', 120, 'TF1 SA', 5);
INSERT INTO EMISSION VALUES('F6', 'Demolition Man', 'En 2032, � San Angeles, une m�galopole californienne o� toute violence a �t� �radiqu�e, Simon Phoenix, un tueur psychopathe condamn� � une longue peine d''hibernation et de r��ducation, profite d''une visite m�dicale pour s''�vader.', 'cin�ma', 120, 'TF1 SA', 6);
INSERT INTO EMISSION VALUES('F7', 'Borderline', NULL, 'cin�ma', 100, 'France T�l�visions', 7);
INSERT INTO EMISSION VALUES('F8', 'Plus Belle La Vie', 'Feuilleton r�aliste ', 'cin�ma', 30, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('F9', 'Doctor Who', 'Amy, Rory et le Docteur, toujours � la recherche de Melody Pound, sont revenus � leur propre �poque. ', 'cin�ma', 60, 'France T�l�visions', 10);
INSERT INTO EMISSION VALUES('F10', 'Cold Case', 'Le mal triomphe', 'cin�ma', 45, 'France T�l�visions', 11);
INSERT INTO EMISSION VALUES('F11', 'Les petits meurtres d''Agatha Christie', NULL, 'cin�ma', 90, 'France T�l�visions', 12);
INSERT INTO EMISSION VALUES('F12', 'Le pr�nom', 'Vincent, riche agent immobilier, est invit� � d�ner, avec Claude, un ami, chez sa s�ur Elisabeth et son mari Pierre.', 'cin�ma', 120, 'Groupe M6', 13);
INSERT INTO EMISSION VALUES('F13', 'Pulp Fiction', 'A Los Angeles, Vincent Vega, un tueur � gages, accepte de servir de cavalier � Mia, l''�pouse de son patron, le ca�d Marsellus Wallace.', 'cin�ma', 180, 'Groupe M6', 14);
INSERT INTO EMISSION VALUES('F14', 'Le jour o� la Terre s''arr�ta', 'Une �norme sph�re lumineuse venue de l''espace se pose au c�ur de New York.', 'cin�ma', 165, 'Groupe M6', 15);
INSERT INTO EMISSION VALUES('F15', 'Fais pas ci, fais pas �a', 'Catastrophe chez les Lepic.', 'cin�ma', 60, 'Groupe M6', 16);
INSERT INTO EMISSION VALUES('F16', 'Chicago Med', NULL, 'cin�ma', 120, 'TF1 SA', 17);
INSERT INTO EMISSION VALUES('F17', 'First Murder', 'Les tempes ont chang�.', 'cin�ma', 60, 'TF1 SA', 1);
INSERT INTO EMISSION VALUES('F18', 'Bienvenue aux Edelweiss', 'Accompagn�e de son fils, Anne-Sophie se rend dans le chalet familial.', 'cin�ma', 100, 'TF1 SA', 18);
INSERT INTO EMISSION VALUES('F19', 'Dr House', 'Eange', 'cin�ma', 120, 'TF1SA', 12);
INSERT INTO EMISSION VALUES('F20', 'John Rambo', 'John Rambo vit dans l''ouest de la Tha�lande, � la fronti�re birmane, o� il survit en chassant des serpents au venin mortel qu''il revend � un animateur de spectacles.', 'cin�ma', 90, 'TF1 SA', NULL);
INSERT INTO EMISSION VALUES('F21', 'Gone Girl', 'Le jour de leur cinqui�me anniversaire de mariage, Nick d�couvre que son �pouse Amy a disparu.', 'cin�ma', 160, 'France T�l�visions', 19);
INSERT INTO EMISSION VALUES('F22', 'Gatsby le magnifique', 'Au d�but des ann�es 1920, Nick Carraway, jeune aspirant �crivain, s''installe � Ellis Island.', 'cin�ma', 150, 'France T�l�visions', 20);
INSERT INTO EMISSION VALUES('F23', 'Retour � Cold Mountain', 'Durant la guerre de S�cession, Inman, un soldat sudiste, est parti au combat convaincu du bien-fond� du conflit.', 'cin�ma', 180, 'Groupe M6', 21);
INSERT INTO EMISSION VALUES('JT1', 'Journal', NULL, 'r�gulier', 60, 'TF1 SA', NULL);
INSERT INTO EMISSION VALUES('JT2', 'Journal', NULL, 'r�gulier', 60, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('JT3', 'Journal', NULL, 'r�gulier', 60, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('JT4', 'Grand Soir 3', 'Journal', 'r�gulier', 30, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('JT5', '19:45', 'Journal', 'r�gulier', 45, 'Groupe M6', NULL);
INSERT INTO EMISSION VALUES('J1', 'Money Drop', 'Chaque soir, un duo d�fie les trappes afin de conserver, voire de doubler, les 250 000 euros mis en jeu au d�but de la partie.', 'r�gulier', 60, 'TF1 SA', NULL);
INSERT INTO EMISSION VALUES('J2', 'Super Nanny', 'Avec 10 enfants � la maison, on ne s''en sort plus !', 'r�gulier', 120, 'TF1 SA', NULL);
INSERT INTO EMISSION VALUES('J3', 'La Villa des c�urs bris�s', 'Les deux animateurs reviendront sur les diff�rentes histoires de la Villa.', 'r�gulier', 60, 'TF1 SA', NULL);
INSERT INTO EMISSION VALUES('J4', 'Chroniques criminelles', 'Magazine de soci�t� qui d�crypte des affaires criminelles c�l�bres', 'r�gulier', 120, 'TF1 SA', NULL);
INSERT INTO EMISSION VALUES('J5', 'Avant-Quotidien', 'Tous les soirs, Yann et son �quipe traitent l''actualit� � leur fa�on.', 'r�gulier', 90, 'TF1 SA', NULL);
INSERT INTO EMISSION VALUES('J6', 'Quotidien', 'Entour� de son �quipe, Yann Barth�s anime une grande session d''information qui m�le humour et impertinence', 'r�gulier', 90, 'TF1 SA', NULL);
INSERT INTO EMISSION VALUES('J7', '90'' Enqu�tes', 'Magazine d''investigation', 'r�gulier', 90, 'TF1 SA', NULL);
INSERT INTO EMISSION VALUES('J8', 'N''oubliez pas les paroles', 'Les candidats chantent en karaok� accompagn�s par un orchestre. ', 'r�gulier', 60, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('J9', 'Couple... quitte ou double ?', 'Alors qu''ils traversaient une p�riode de crise, six couples ont fait la d�marche de consulter un th�rapeute afin de trouver des solutions � leurs probl�mes. ', 'cin�ma', 60, 'France T�l�visions', 8);
INSERT INTO EMISSION VALUES('J10', 'Tout le sport', 'L''actualit� sportive quotidienne.', 'r�gulier', 30, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('J11', 'Des racines et des ailes', 'Passion patrimoine : Au fil du Rh�ne, entre Camargue et coteaux du Lyonnais', 'r�gulier', 120, 'France T�l�visions', 9);
INSERT INTO EMISSION VALUES('J12', 'C � vous', 'Install�s dans le loft de �C � vous�, l''animatrice et sa bande donnent la parole aux invit�s et font la part belle � l''actualit� la plus diverse.', 'r�gulier', 120, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('J13', 'C dans l''air', 'Cl�s pour comprendre dans sa globalit� un �v�nement ou un sujet de premi�re importance', 'r�gulier', 90, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('J14', 'Ford Boyard', 'L''�quipe qui part ce soir � l''assaut du fort et de son tr�sor bien prot�g�', 'r�gulier', 120, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('J15', 'Une saison au zoo', 'Le documentaire suit, jour apr�s jour, les soigneurs animaliers, v�t�rinaires, responsables d''h�bergement, commerciaux, jardiniers du parc zoologique de La Fl�che. ', 'r�gulier', 60, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('J16', 'Chasseurs d''appart', 'Trois agents immobiliers doivent trouver des biens � des clients dans des secteurs qu''ils ne connaissent pas forc�ment.', 'r�gulier', 60, 'Groupe M6', NULL);
INSERT INTO EMISSION VALUES('J17', 'Sc�nes de m�nages', 'S�rie humoristique autour de la vie de couple', 'r�gulier', 30, 'Groupe M6', NULL);
INSERT INTO EMISSION VALUES('J18', 'Maison � vendre', 'Conseils en mati�res d''achat et de vente de biens immobiliers', 'r�gulier', 90, 'Groupe M6', NULL);
INSERT INTO EMISSION VALUES('J19', 'Un d�ner presque parfait', 'M�lant art de recevoir, d�coration de la table et cuisine, cinq candidats habitant dans la m�me ville doivent, � tour de r�le, inviter � d�ner les quatre autres.', 'r�gulier', 60, 'Groupe M6', NULL);
INSERT INTO EMISSION VALUES('J20', 'Les Princes de l''amour', 'Le principe de l''�mission est de suivre trois s�ducteurs et trois pr�tendants qui vont essayer de trouver l''amour.', 'r�gulier', 60, 'Groupe M6', NULL);
INSERT INTO EMISSION VALUES('J21', 'Norbert, commis d''office', 'Norbert est commis d''office pour remettre des "criminels culinaires" dans le droit chemin.', 'r�gulier', 90, 'Groupe M6', NULL);
INSERT INTO EMISSION VALUES('J22', 'Thalassa ', 'Magazine de la mer', 'r�gulier', 120, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('J23', 'La grande librairie', 'Magazine litt�raire', 'r�gulier', 90, 'France T�l�visions', NULL);
INSERT INTO EMISSION VALUES('J24', 'Cauchemar en cuisine', 'Chantal et Alain ont fait appel au chef Philippe Etchebest pour tenter de sauver leur auberge', 'r�gulier', 120, 'Groupe M6', NULL);
INSERT INTO EMISSION VALUES('J25', 'Enqu�te exclusive', 'Magazine d''information', 'r�gulier', 90, 'Groupe M6', NULL);
INSERT INTO EMISSION VALUES('J26', 'Jos�phine, ange gardien', 'Jos�phine Delamarre est un ange gardien que le ciel envoie sur terre.', 'cin�ma', 90, 'TF1 SA', NULL);


SELECT NOMCH, DATED, HEUREDEBUT, REFERENCE, HEUREFIN FROM GILLES_HUBERT.TP4_TV;

SELECT * from ALL_TABLES
WHERE OWNER='GILLES_HUBERT'
AND TABLE_NAME='TP4_TV';

DESC GILLES_HUBERT.TP4_TV;

INSERT INTO PERSONNE VALUES(103, 'Remery', 'Lucas');

SELECT NOM, PRENOM FROM PERSONNE;

SELECT NUMA, DESCRIPTIONA, NOTE FROM AVIS
WHERE NOTE>=4;

