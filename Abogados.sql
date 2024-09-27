CREATE TABLE tbAbogado(
UUID_Abogado VARCHAR2(100),
Nombre_Abogado VARCHAR2(100),
Edad_Abogado INT,
Peso_Abogado NUMBER,
Correo_Abogado VARCHAR2(100));

INSERT INTO tbAbogado(UUID_Abogado, Nombre_Abogado, Edad_Abogado, Peso_Abogado, Correo_Abogado) VALUES (?, ?, ?, ?, ?)