# Sprint 1: Cimientos y acceso

**Fechas:** 26 de agosto - 1 de septiembre de 2026  
**Roles Scrum simulados:** docente como Product Owner, estudiante como Development Team y estudiante como Scrum Master.

## Sprint Planning

El objetivo fue dejar disponible el acceso inicial a Habitat UTS y una base de datos que soportara los siguientes incrementos.

| Historia | Estimación |
|---|---:|
| **HU1.** Como visitante, quiero una página de aterrizaje atractiva para conocer la inmobiliaria y buscar propiedades rápidamente. | 1 día |
| **HU2.** Como usuario, quiero registrarme con un correo único y validado. | 2 días |
| **HU3.** Como usuario registrado, quiero iniciar y cerrar sesión de forma segura y que me lleve al panel de mi rol. | 2 días |
| Trabajo técnico habilitador: MER/modelo relacional, datos de prueba y conexión JDBC centralizada. | 2 días |
| **Total** | **7 días** |

### Tareas técnicas

- Diseñar el modelo relacional para `usuario`, `rol`, `usuario_rol`, `perfil`, `inmobiliaria`, `ciudad`, `tipo_propiedad`, `propiedad` y las entidades de operación que se usarían después.
- Crear `sql/01_modelo_inmobiliaria_mariadb.sql`, incluyendo claves foráneas, restricciones de unicidad y datos iniciales de roles, ciudades, tipos, usuarios y propiedades.
- Crear `web/index.jsp`, `web/propiedades.jsp` y `web/detalle.jsp` como entrada pública y catálogo consultable.
- Implementar `registro.jsp` y `registro_guardar.jsp` con validación de correo, confirmación de contraseña, correo único y asignación transaccional del rol `CLIENTE`.
- Implementar `login.jsp`, `acceso.jsp` y `logout.jsp`; guardar en sesión `idUsuario`, `nombre` y `rol`.
- Centralizar conexión, cierre de recursos y rollback en `WEB-INF/jspf/conexion.jspf`; utilizar el driver MariaDB de `WEB-INF/lib`.
- Preparar `web.xml` y la clase `FiltroControlAcceso` para proteger las rutas por rol, además de `seguridad.jspf` como validación dentro de cada JSP.

## Sprint Review

### Funcional y demostrable

- Un visitante puede entrar a la landing, ver la propuesta de Habitat UTS y usar la búsqueda rápida que lleva al catálogo.
- El catálogo público consulta propiedades activas y disponibles y permite filtrar por texto, ciudad y tipo; el detalle muestra información, características y galería.
- El registro valida formato de correo, longitud mínima de contraseña y coincidencia de confirmación. La tabla `usuario` impone correo único y la transacción crea también la relación con el rol `CLIENTE`.
- El inicio de sesión valida correo, contraseña cifrada con SHA-256 a partir de `correo:contraseña` y cuenta activa. El cierre destruye la sesión.
- El filtro exige sesión para `/cliente/*`, `/inmobiliaria/*` y `/administrador/*`, compara la ruta con el rol y redirige a login o a `acceso-denegado.jsp` cuando corresponde.
- La configuración de `web.xml` establece `index.jsp` como bienvenida, codificación UTF-8 y timeout de sesión de 30 minutos. La conexión JDBC está reutilizada por los fragmentos JSP compartidos.

### Pendientes o alcance diferido

- Los paneles y operaciones específicas de cliente, inmobiliaria y administrador todavía eran trabajo de los sprints siguientes; en este incremento el catálogo público no sustituye esos paneles.
- El correo único está respaldado por la base de datos, pero no hay verificación por enlace de correo electrónico. La contraseña usa SHA-256, una decisión académica del proyecto que debería evolucionar a un hash adaptativo en producción.
- La evidencia git disponible no registra commits dentro de cada día del intervalo del sprint: el commit funcional amplio aparece el 13 de septiembre. Por eso las fechas son la planificación Scrum solicitada y no una reconstrucción exacta de actividad diaria.

## Sprint Retrospective

### Qué funcionó bien

- Se estableció temprano una frontera clara entre páginas públicas y módulos protegidos.
- El modelo relacional incluyó desde el comienzo roles, relaciones y datos de prueba suficientes para recorrer el flujo de acceso.
- La conexión, utilidades, cabecera, pie y seguridad quedaron centralizadas en `WEB-INF/jspf`, evitando repetir toda la infraestructura en cada JSP.

### Qué mejorar

- El ritmo de commits fue concentrado hacia el final de la etapa, no distribuido en incrementos diarios verificables.
- Debió existir una matriz de aceptación para registro, credenciales inválidas, cuenta inactiva, rol incorrecto y acceso sin sesión antes de avanzar al catálogo.
- Las credenciales de MariaDB quedaron en `conexion.jspf`; el propio README marca que deben cambiarse antes de un despliegue real.

### Acción aplicada en el sprint siguiente

Se acordó dividir el incremento en entregas pequeñas y comprobables: primero panel y autorización, luego CRUD de propiedades y finalmente relaciones de imágenes/características y filtros. También se documentaron rutas y datos de prueba en el README para facilitar la revisión.
