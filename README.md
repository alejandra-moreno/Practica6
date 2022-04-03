
# Práctica 6: Testing de una aplicación de Spring Boot

## Objetivo de la práctica

El objetivo de esta sexta práctica de la asignatura es aplicar los conocimientos adquiridos en clase para testear una aplicación de Spring Boot. Se han realizado 2 tipos de tests: unitarios en las clases DNI y Teléfono y end-to-end para el controller de la aplicación.

Las pruebas unitarias consisten en aislar una parte del código y comprobar que funciona a la perfección. Gracias a este tipo de pruebas se detectan antes errores que no se podrían detectar hasta fases más avanzadas del desarrollo. Para esta práctica las pruebas unitarias realizadas en las clases DNITest y TelefonoTest comprueban que la función validar() de ambas clases funciona correctamente. Para ello, se han probado varios escenarios donde el DNI o el teléfono introducidos primero eran válidos y luego eran incorrectos por tener una longitud equivocada, incluir o no letras...

Las pruebas end-to-end consisten en probar una aplicación desde el punto de vista del usuario final. En este tipo de pruebas se prueban los flujos y procesos como el registro de usuario o el inicio de sesión. Para esta práctica, se ha comprobado que el ProcessController gestiona correctamente las peticiones cuando son erróneas. Para ello, se ha probado la clase ProcessControllerTest introduciendo nombre, DNI o teléfono de forma incorrecta y comprobando que la response es la adecuada.

## Referencias

- https://junit.org/junit4/
- README editado con: https://stackedit.io/
