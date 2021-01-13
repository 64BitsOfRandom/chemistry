INSERT INTO CLASSES (NAME)
VALUES ('кислота'),
       ('щёлочь'),
       ('соль'),
       ('спирт'),
       ('кетон'),
       ('эфир');
INSERT INTO IONS (VALENCE, NOTATION, TYPE)
values (1, 'OH', 'анион'),
       (1, 'Cl', 'анион'),
       (1, 'F', 'анион'),
       (1, 'C2H5', 'катион'),
       (1, 'CH3', 'анион'),
       (1, 'COH', 'анион'),
       (1, 'CN', 'анион'),
       (1, 'NO2', 'анион'),
       (1, 'NO3', 'анион'),
       (2, 'CO3', 'анион'),
       (2, 'SO4', 'анион'),
       (3, 'PO4', 'анион'),
       (3, 'S2O3', 'анион'),
       (1, 'NH4', 'катион'),
       (1, 'H', 'катион'),
       (1, 'Na', 'катион'),
       (1, 'Li', 'катион'),
       (1, 'К', 'катион'),
       (2, 'Ca', 'катион'),
       (2, 'Mg', 'катион'),
       (2, 'Ba', 'катион'),
       (3, 'Fe', 'катион'),
       (3, 'Cr', 'катион'),
       (4, 'Ti', 'катион');
INSERT INTO FORMULAS(CATION, ANION, NOTATION)
VALUES (14, 1, 'хлористый водород'),
       (14, 2, 'фтористый водород'),
       (14, 7, 'азотистая кислота'),
       (14, 8, 'азотная кислота'),
       (3, 0, 'этиловый спирт'),
       (17, 0, 'едкий кали'),
       (15, 1, 'хлористый натрий'),
       (18, 10, 'сернокислый кальций'),
       (23, 6, 'цианид титана');

INSERT INTO substances(formulaid, classid)
VALUES (0, 0),
       (1, 0),
       (2, 0),
       (3, 0),
       (4, 3),
       (5, 1),
       (6, 2),
       (7, 2),
       (8, 2);

-- CREATE TABLE  IF NOT EXISTS formulas
-- (
--     id       INTEGER IDENTITY  PRIMARY KEY,
--     anion    INTEGER NOT NULL,
--     cation   INTEGER NOT NULL,
--     notation VARCHAR(32),
--     foreign key  (anion) REFERENCES  ions(id) ON DELETE CASCADE,
--     foreign key  (cation) REFERENCES  ions(id) ON DELETE CASCADE
-- );
-- CREATE TABLE  IF NOT EXISTS substances
-- (
--     id        INTEGER IDENTITY  PRIMARY KEY,
--     formulaId INTEGER NOT NULL,
--     classId   INTEGER NOT NULL,
--     FOREIGN KEY (classId) REFERENCES classes(id) ON DELETE CASCADE,
--     FOREIGN KEY (formulaId) REFERENCES formulas(id) ON DELETE CASCADE
-- );
