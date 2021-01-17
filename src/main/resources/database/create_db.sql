CREATE TABLE  IF NOT EXISTS classes
(
    id   INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS ions
(
    id       INTEGER IDENTITY PRIMARY KEY,
    valence  INTEGER NOT NULL,
    notation VARCHAR(32) UNIQUE,
    type     VARCHAR(32)
);
CREATE TABLE  IF NOT EXISTS formulas
(
    id       INTEGER IDENTITY  PRIMARY KEY,
    cation   INTEGER NOT NULL,
    anion    INTEGER NOT NULL,
    notation VARCHAR(32),
    foreign key  (anion) REFERENCES  ions(id) ON DELETE CASCADE,
    foreign key  (cation) REFERENCES  ions(id) ON DELETE CASCADE
);
CREATE TABLE  IF NOT EXISTS substances
(
    id        INTEGER IDENTITY  PRIMARY KEY,
    formulaId INTEGER NOT NULL,
    classId   INTEGER NOT NULL,
    FOREIGN KEY (classId) REFERENCES classes(id) ON DELETE CASCADE,
    FOREIGN KEY (formulaId) REFERENCES formulas(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS SYSTEM_LOG(
    id INTEGER IDENTITY PRIMARY KEY,
    time DATETIME NOT NULL,
    logger VARCHAR(32) NOT NULL,
    type varchar(16) NOT NULL,
    message VARCHAR(256) NOT NULL
);


CREATE VIEW formulas_substances AS
select FORMULAS.ID       as id,
       FORMULAS.NOTATION as notation,
       CLASSES.NAME      as className,

       CATIONS.VALENCE   as cation_valence,
       CATIONS.NOTATION  as cation_notation,

       ANIONS.VALENCE    as anion_valence,
       ANIONS.NOTATION   as anion_notation
from FORMULAS
         inner join SUBSTANCES on FORMULAS.ID = SUBSTANCES.ID
    and FORMULAS.ID = SUBSTANCES.FORMULAID
         inner join CLASSES on SUBSTANCES.CLASSID = CLASSES.ID
         inner join IONS ANIONS on ANIONS.ID = FORMULAS.ANION
         inner join IONS CATIONS on CATIONS.ID = FORMULAS.CATION;

CREATE PROCEDURE delete_substance(IN substanceId INTEGER) MODIFIES SQL DATA
BEGIN ATOMIC
DELETE FROM SUBSTANCES WHERE id = substanceId;
DELETE FROM FORMULAS WHERE id = substanceId;
END;