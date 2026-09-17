# Product Backlog: Habitat UTS

Product Owner: docente. Los sprints son una simulación académica de tres iteraciones de siete días. La asignación refleja la distribución solicitada y el alcance comprobado en el repositorio.

| ID | Historia de usuario | Prioridad | Sprint asignado |
|---|---|---|---|
| HU1 | Como visitante, quiero una página de aterrizaje atractiva para conocer la inmobiliaria y buscar propiedades rápidamente. | Alta | Sprint 1 |
| HU2 | Como usuario, quiero registrarme con un correo único y validado. | Alta | Sprint 1 |
| HU3 | Como usuario registrado, quiero iniciar y cerrar sesión de forma segura y que me lleve al panel de mi rol. | Alta | Sprint 1 |
| HU4 | Como administrador, quiero asignar y revocar roles a los usuarios. | Alta | Sprint 2 |
| HU5 | Como cliente, quiero completar mi perfil (documento, teléfono, dirección). | Media | Sprint 2 |
| HU6 | Como agente, quiero registrar y editar propiedades con fotos, características y precio. | Alta | Sprint 2 |
| HU7 | Como cliente, quiero buscar y filtrar propiedades por ciudad, tipo, precio y características. | Alta | Sprint 2 |
| HU8 | Como cliente, quiero marcar propiedades como favoritas. | Media | Sprint 3 |
| HU9 | Como cliente, quiero solicitar una cita sin que se crucen las agendas. | Media | Sprint 3 |
| HU10 | Como cliente, quiero radicar documentos de compra/arriendo y ver el estado. | Media | Sprint 3 |
| HU11 | Como agente, quiero aprobar o rechazar solicitudes y documentos. | Media | Sprint 3 |
| HU12 | Como administrador, quiero un reporte de propiedades por ciudad y estado con agregación SQL. | Media | Sprint 3 |
| HU13 | Como administrador, quiero consultar la auditoría de accesos y cambios. | Baja | Sprint 3 |
| HU14 | Como equipo, quiero dejar una entrega desplegable y documentada, con correcciones visuales y de errores identificados durante la revisión. | Mejora propuesta | Sprint 3 |

## Notas de alcance

- HU7 quedó parcialmente implementada: existen filtros por texto, ciudad y tipo, pero no por precio ni características.
- HU10 registra un nombre de archivo o URL en `documento_solicitud`; no implementa carga física multipart.
- HU11 actualiza el estado de la solicitud. El estado del documento permanece pendiente y no tiene un flujo separado de aprobación.
- HU13 consulta auditoría y el SQL incluye datos iniciales, pero no se observó escritura automática desde cada operación JSP.
- HU14 reúne las mejoras verificables en el cierre: correcciones, pulido visual, README, esquema y diccionario de datos.
