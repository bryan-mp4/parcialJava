# Sprint 2: Núcleo del negocio

**Fechas:** 2 - 8 de septiembre de 2026  
**Roles Scrum simulados:** docente como Product Owner, estudiante como Development Team y estudiante como Scrum Master.

## Sprint Planning

El objetivo fue convertir el catálogo inicial en una aplicación diferenciada por rol, con administración de propiedades y un perfil básico de cliente.

| Historia | Estimación |
|---|---:|
| **HU4.** Como administrador, quiero asignar y revocar roles a los usuarios. | 1,5 días |
| **HU5.** Como cliente, quiero completar mi perfil (documento, teléfono, dirección). | 1 día |
| **HU6.** Como agente, quiero registrar y editar propiedades con fotos, características y precio. | 2,5 días |
| **HU7.** Como cliente, quiero buscar y filtrar propiedades por ciudad, tipo, precio y características. | 1 día |
| Trabajo técnico: paneles diferenciados, galería 1:N, características N:M e integración/validación. | 1 día |
| **Total** | **7 días** |

### Tareas técnicas

- Construir `administrador/usuarios.jsp` y `actualizar_usuario.jsp` para cambiar el rol en `usuario_rol` y activar o desactivar la cuenta.
- Construir `cliente/perfil.jsp` y `guardar_perfil.jsp`; actualizar `usuario` y crear o actualizar el registro 1:1 de `perfil` dentro de una transacción.
- Implementar `inmobiliaria/propiedades.jsp`, `guardar_propiedad.jsp`, `guardar_propiedad_accion.jsp` y `baja_propiedad.jsp`, limitando las consultas a las propiedades de la inmobiliaria autenticada.
- Relacionar `propiedad` con `imagen_propiedad` (1:N) y `propiedad_caracteristica` con `caracteristica` (N:M); mostrar la galería y las etiquetas en `detalle.jsp`.
- Usar `PreparedStatement`, claves foráneas, matrícula única y campos de catálogo para controlar la persistencia.
- Preparar los paneles `cliente/panel.jsp`, `inmobiliaria/panel.jsp` y `administrador/panel.jsp`, con navegación y conteos/resúmenes adecuados al rol.
- Mantener los filtros públicos de texto, ciudad y tipo y dejar identificada la diferencia entre esos filtros implementados y los filtros adicionales aún pendientes.

## Sprint Review

### Funcional y demostrable

- El administrador puede consultar usuarios, cambiar su rol entre `CLIENTE`, `INMOBILIARIA` y `ADMINISTRADOR`, y activar o inactivar cuentas desde `usuarios.jsp`.
- El cliente puede actualizar nombre, teléfono y dirección. `guardar_perfil.jsp` persiste los datos de `usuario` y el registro 1:1 de `perfil` mediante `ON DUPLICATE KEY UPDATE`.
- El agente puede listar sus propiedades, crear o editar datos como matrícula, título, descripción, ciudad, tipo, dirección, precio, área, habitaciones y baños, y dar de baja una propiedad.
- El modelo ya soporta imágenes por propiedad y características mediante las tablas `imagen_propiedad`, `caracteristica` y `propiedad_caracteristica`. La pantalla de detalle muestra la galería y las características; el SQL incluye datos de prueba para ambas relaciones.
- Los tres paneles y las páginas JSP usan `seguridad.jspf`; además, el filtro de servlet impide entrar a la carpeta de otro rol.
- El catálogo filtra por texto, ciudad y tipo y solo presenta propiedades activas con estado `DISPONIBLE`.

### Pendientes o alcance diferido

- La HU7 pedía filtrar también por precio y características. En el código revisado esos dos filtros no aparecen en el formulario ni en el `WHERE` de `web/propiedades.jsp`; quedaron como ampliación pendiente.
- La interfaz de registro de propiedades no implementa carga de archivos ni administración completa de varias imágenes desde el agente. La galería existente se alimenta de URLs almacenadas en `imagen_propiedad` y del SQL de prueba.
- La asignación de roles reemplaza la relación actual por un solo rol seleccionado en el formulario; aunque el modelo permite varios roles, la pantalla no ofrece selección múltiple ni una operación separada de revocación.
- El historial muestra que la implementación funcional se concentró en el commit `0bce40c` del 13 de septiembre, después de las fechas simuladas de este sprint. Los commits de pulido y correcciones posteriores ayudaron a cerrar la versión, pero no prueban trabajo distribuido día a día.

## Sprint Retrospective

### Qué funcionó bien

- Las relaciones 1:1, 1:N y N:M quedaron reflejadas tanto en el SQL como en las consultas JSP.
- La autorización se aplicó en dos niveles y las consultas de propiedades se limitaron al usuario de la inmobiliaria autenticada.
- Los paneles por rol hacen visible el flujo de cada tipo de usuario y facilitan una demostración completa.

### Qué mejorar

- El alcance de HU7 debió dividirse explícitamente: texto/ciudad/tipo sí estaban implementados, pero precio/características no debieron considerarse terminados.
- Se necesitaban pruebas de edición, matrícula duplicada, baja, propietario incorrecto y combinaciones de filtros antes de cerrar el sprint.
- Los commits continuaron agrupados al final; faltaron commits incrementales por cada relación o módulo terminado.

### Acción aplicada en el sprint siguiente

Se priorizó cerrar los flujos de operación con transacciones y restricciones de base de datos, y se incorporaron correcciones de errores y validaciones en varios commits de bug fixing antes de la documentación final. También se dejó como límite explícito que los documentos serían registrados por nombre/URL, no subidos físicamente.
