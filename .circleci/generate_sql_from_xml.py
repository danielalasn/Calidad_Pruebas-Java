import xml.etree.ElementTree as ET

xml_data = '''<?xml version="1.0" encoding="UTF-8"?>
<dataset>
    <Alumno id="1" nombre="alumno1" apellido="apellido1" email="hola1@hola.com" />
    <Alumno id="2" nombre="alumno2" apellido="apellido2" email="hola2@hola.com" />
    <Alumno id="3" nombre="alumno3" apellido="apellido3" email="hola3@hola.com" />
</dataset>'''

root = ET.fromstring(xml_data)
for alumno in root.findall('Alumno'):
    id = alumno.get('id')
    nombre = alumno.get('nombre')
    apellido = alumno.get('apellido')
    email = alumno.get('email')
    sql = f"INSERT INTO Alumnos (id, nombre, apellido, email) VALUES ('{id}', '{nombre}', '{apellido}', '{email}');"
    print(sql)
