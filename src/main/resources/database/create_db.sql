CREATE TABLE  IF NOT EXISTS classes
(
    id   INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS ions
(
    id       INTEGER IDENTITY PRIMARY KEY,
    valence  INTEGER NOT NULL,
    notation VARCHAR(32),
    type     VARCHAR(32)
);
CREATE TABLE  IF NOT EXISTS formulas
(
    id       INTEGER IDENTITY  PRIMARY KEY,
    anion    INTEGER NOT NULL,
    cation   INTEGER NOT NULL,
    notation VARCHAR(32),
    foreign key  (anion) REFERENCES  ions(id) ON DELETE CASCADE,
    foreign key  (cation) REFERENCES  ions(id) ON DELETE CASCADE
);
CREATE TABLE  IF NOT EXISTS substances
(
    id        INTEGER IDENTITY  PRIMARY KEY,
    formulaId INTEGER NOT NULL,
    classId   INTEGER NOT NULL,
    foreign key (classId) REFERENCES classes(id) ON DELETE CASCADE,
    foreign key (formulaId) REFERENCES formulas(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS SYSTEM_LOG(
                                         id INTEGER IDENTITY PRIMARY KEY,
                                         timestamp DATETIME NOT NULL,
                                         message VARCHAR(128),
                                         msgType varchar(16) NOT NULL
);
