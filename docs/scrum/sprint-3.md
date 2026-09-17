# Sprint 3: Operación y cierre

**Fechas:** 9 - 16 de septiembre de 2026  
**Roles Scrum simulados:** docente como Product Owner, estudiante como Development Team y estudiante como Scrum Master.

## Sprint Planning

El objetivo fue completar la operación posterior a la publicación, los reportes administrativos, la consulta de auditoría y el cierre documentado/desplegable.

| Historia | Estimación |
|---|---:|
| **HU8.** Como cliente, quiero marcar propiedades como favoritas. | 0,75 días |
| **HU9.** Como cliente, quiero solicitar una cita sin que se crucen las agendas. | 1 día |
| **HU10.** Como cliente, quiero radicar documentos de compra/arriendo y ver el estado. | 1 día |
| **HU11.** Como agente, quiero aprobar o rechazar solicitudes y documentos. | 1 día |
| **HU12.** Como administrador, quiero un reporte de propiedades por ciudad y estado con agregación SQL. | 0,75 días |
| **HU13.** Como administrador, quiero consultar la auditoría de accesos y cambios. | 0,5 días |
| **HU14.** Como equipo, quiero dejar una entrega desplegable y documentada, con correcciones visuales y de errores identificados durante la revisión. | 2 días |
| **Total** | **7 días** |

### Tareas técnicas

- Implementar `cliente/favorito.jsp` y `favoritos.jsp` sobre la clave primaria compuesta `(id_usuario,id_propiedad)`.
- Implementar `cliente/agendar.jsp`, `guardar_cita.jsp`, `citas.jsp` y el flujo de aprobación/rechazo de `inmobiliaria/actualizar_cita.jsp`; conservar la restricción única `uq_cita_horario` de SQL.
- Implementar `cliente/nueva_solicitud.jsp`, `guardar_solicitud.jsp` y `solicitudes.jsp` para compra/arriendo, estado inicial `EN_REVISION` y documento asociado.
- Implementar en la inmobiliaria la consulta de solicitudes y `actualizar_solicitud.jsp` para estados `APROBADA` o `RECHAZADA`.
- Implementar reportes con `GROUP BY` y `HAVING` en `administrador/reportes.jsp`, además del reporte operativo de la inmobiliaria.
- Implementar `administrador/auditoria.jsp` y consultar la tabla `auditoria` con usuario, acción, tabla, detalle y fecha.
- Ejecutar pruebas manuales de roles, duplicidad de citas, estados y consultas; ajustar visuales, errores de JSP/JDBC y preparar README, esquema y diccionario para entrega.

## Sprint Review

### Funcional y demostrable

- El cliente puede guardar una propiedad como favorita; la clave primaria compuesta evita duplicarla y `favoritos.jsp` permite consultar sus guardados.
- El cliente puede solicitar una cita con fecha/hora y observaciones. La tabla `cita` impide dos citas para la misma propiedad y hora mediante `uq_cita_horario`; la inmobiliaria puede aprobarla o marcarla como cancelada.
- El cliente puede radicar una solicitud de `COMPRA` o `ARRIENDO`, seleccionar una propiedad disponible, registrar el nombre de un documento o una URL y consultar el estado en `solicitudes.jsp`.
- La inmobiliaria ve las solicitudes de sus propiedades y puede cambiar su estado a `APROBADA` o `RECHAZADA`. La pantalla consulta el documento asociado.
- El reporte administrativo agrupa propiedades activas por ciudad y filtra grupos con `HAVING COUNT(...) > 0`; otro reporte agrupa solicitudes por estado. La inmobiliaria tiene un reporte propio de propiedades y características.
- El administrador puede consultar la tabla de auditoría, que el SQL inicializa con acciones como `CREAR`, `ACTUALIZAR` y `CONSULTAR`.
- La entrega incluye README de instalación, configuración Tomcat/JDBC, usuarios de prueba, esquema y diccionario; el historial registra correcciones de errores, pulido visual y documentación el 13, 16 y 17 de septiembre.

### Pendientes o alcance diferido

- No existe carga multipart de archivos: `guardar_solicitud.jsp` inserta en `documento_solicitud` el texto recibido en `nombre_archivo`. La URL se conserva como dato, pero el servidor no almacena ni valida físicamente el archivo.
- La HU11 permite decidir sobre la solicitud, pero no hay una pantalla separada para cambiar el estado del documento (`documento_solicitud.estado` queda en `PENDIENTE`).
- La auditoría se consulta y tiene datos iniciales en el SQL, pero en las páginas revisadas no se encontró un registro automático de cada acceso o cambio de negocio.
- El reporte administrativo solicitado “por ciudad y estado” está dividido en dos consultas: propiedades por ciudad considerando `activo=1` y solicitudes por estado. No presenta una tabla única cruzando ciudad con estado de propiedad.
- El script SQL contiene fechas de citas de octubre de 2026 y el formulario de agenda tiene un `min` fijo en `2026-09-13`; son datos de demostración y una validación que debería parametrizarse para un despliegue real.

## Sprint Retrospective

### Qué funcionó bien

- Las restricciones y estados del modelo hicieron demostrables casos útiles: favorito duplicado, horario ocupado, solicitud en revisión y decisiones de la inmobiliaria.
- El cierre incorporó artefactos de documentación reales: README, diagrama/modelo y diccionario de datos, además de los ajustes de interfaz y correcciones registrados en git.
- Las consultas agregadas con `GROUP BY/HAVING` permiten mostrar resultados resumidos sin trasladar toda la agregación a Java/JSP.

### Qué mejorar

- La validación de agenda y la fecha mínima están parcialmente fijadas en la vista; deberían calcularse con la fecha actual y validarse también en servidor.
- Debe diferenciarse claramente “registrar el nombre o URL del documento” de “subir y validar un archivo”.
- La tabla de auditoría requiere escritura consistente desde las operaciones, no solo consulta de registros sembrados.
- Aunque hubo varios commits de bug fixing y documentación, el ritmo siguió concentrado hacia el cierre; la entrega habría sido más trazable con commits pequeños y frecuentes desde el primer día de cada sprint.

### Acción aplicada en el sprint siguiente

Al ser el último sprint no hubo un sprint posterior dentro del parcial. La acción quedó registrada como compromiso de mejora: para una siguiente iteración se deben crear commits diarios o por historia terminada, agregar pruebas de aceptación para cada rol y completar carga/validación de documentos, auditoría automática y filtros por precio/características.
