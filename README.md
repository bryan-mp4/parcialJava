# Habitat UTS

Aplicación web inmobiliaria desarrollada con JSP, Java Servlet, MariaDB, Bootstrap 5 y Bootstrap Icons. Permite consultar propiedades, gestionar favoritos, agendar visitas, registrar solicitudes y administrar la operación inmobiliaria.

## Tecnologías

- Java y Servlets.
- JSP y fragmentos JSP (`.jspf`).
- Apache Tomcat 9.
- MariaDB.
- JDBC con `mariadb-java-client-3.5.10.jar`.
- Bootstrap 5.3.3 y Bootstrap Icons.
- HTML, CSS y JavaScript del navegador.

## Estructura del proyecto

```text
parcialEntrega/
├── sql/
│   └── 01_modelo_inmobiliaria_mariadb.sql
├── src/
│   └── com/uts/inmobiliaria/FiltroControlAcceso.java
├── web/
│   ├── WEB-INF/
│   │   ├── classes/              # Clases compiladas
│   │   ├── jspf/                 # Fragmentos JSP compartidos
│   │   ├── lib/                  # Driver JDBC de MariaDB
│   │   └── web.xml               # Configuración de la aplicación
│   ├── administrador/            # Módulo del administrador
│   ├── cliente/                  # Módulo del cliente
│   ├── inmobiliaria/             # Módulo de inmobiliaria
│   ├── css/estilos.css           # Estilos globales
│   └── *.jsp                     # Páginas públicas
└── README.md
```

## Funcionamiento general

1. El usuario entra al inicio y puede consultar el catálogo público de propiedades.
2. Para gestionar favoritos, citas, solicitudes o datos personales debe registrarse e iniciar sesión.
3. `acceso.jsp` valida el correo, la contraseña cifrada con SHA-256 y el estado de la cuenta.
4. Al iniciar sesión se guardan en la sesión el identificador, nombre y rol del usuario.
5. El filtro `FiltroControlAcceso` protege las rutas `/cliente/*`, `/inmobiliaria/*` y `/administrador/*`.
6. Cada página protegida define sus roles permitidos y usa `seguridad.jspf` como segunda validación.
7. Las páginas JSP consultan o actualizan MariaDB mediante JDBC y los fragmentos compartidos centralizan la conexión, utilidades, seguridad, cabecera y pie.

## Páginas públicas

- `index.jsp`: inicio, búsqueda rápida y presentación del sistema.
- `propiedades.jsp`: catálogo con filtros por texto, ciudad y tipo de propiedad.
- `detalle.jsp`: información completa de una propiedad y acciones disponibles.
- `login.jsp`: formulario de inicio de sesión.
- `registro.jsp` y `registro_guardar.jsp`: creación de cuentas de usuario.
- `logout.jsp`: cierre de sesión.
- `acceso-denegado.jsp`: respuesta cuando el usuario no tiene permisos.

## Roles y permisos

### Cliente

El cliente busca propiedades y gestiona sus propios procesos:

- Consultar y filtrar propiedades.
- Guardar y eliminar propiedades favoritas.
- Agendar visitas.
- Consultar sus citas.
- Crear solicitudes de compra o arriendo.
- Adjuntar o gestionar documentos asociados a sus solicitudes.
- Consultar el estado de sus solicitudes.
- Actualizar su perfil y datos de contacto.

Rutas principales: `/cliente/panel.jsp`, `favoritos.jsp`, `favorito.jsp`, `agendar.jsp`, `citas.jsp`, `solicitudes.jsp`, `nueva_solicitud.jsp`, `guardar_solicitud.jsp`, `perfil.jsp` y `guardar_perfil.jsp`.

### Inmobiliaria

La inmobiliaria administra las propiedades que tiene a su cargo y atiende la operación comercial:

- Crear, consultar, editar y desactivar propiedades.
- Administrar información, precio, ubicación, características e imágenes de sus propiedades.
- Consultar y atender citas de clientes.
- Actualizar el estado de las citas.
- Consultar solicitudes relacionadas con sus propiedades.
- Actualizar el estado de las solicitudes.
- Consultar reportes operativos.

Rutas principales: `/inmobiliaria/panel.jsp`, `propiedades.jsp`, `guardar_propiedad.jsp`, `guardar_propiedad_accion.jsp`, `baja_propiedad.jsp`, `citas.jsp`, `actualizar_cita.jsp`, `solicitudes.jsp`, `actualizar_solicitud.jsp` y `reportes.jsp`.

### Administrador

El administrador tiene funciones de control general del sistema:

- Consultar y administrar usuarios.
- Asignar o actualizar roles y activar o desactivar cuentas.
- Administrar catálogos de ciudades, tipos de propiedad y características.
- Consultar la auditoría de operaciones.
- Consultar reportes generales.

Rutas principales: `/administrador/panel.jsp`, `usuarios.jsp`, `actualizar_usuario.jsp`, `catalogos.jsp`, `guardar_catalogo.jsp`, `auditoria.jsp` y `reportes.jsp`.

## Seguridad y control de acceso

El control de acceso tiene dos niveles:

- `FiltroControlAcceso.java`: intercepta las rutas protegidas desde `web.xml`, exige una sesión válida y compara el rol con la ruta solicitada.
- `seguridad.jspf`: valida dentro de cada JSP que el usuario tenga uno de los roles permitidos por esa página.

Las contraseñas se almacenan como SHA-256 usando la combinación `correo:contraseña`. Las consultas se ejecutan con `PreparedStatement` para evitar concatenar directamente los datos recibidos del usuario.

Los fragmentos comunes están en `web/WEB-INF/jspf/`:

- `conexion.jspf`: driver, conexión, cierre de recursos y rollback.
- `utilidades.jspf`: cifrado, conversión de datos, formato de pesos, escape HTML y colores de estado.
- `seguridad.jspf`: autorización por rol.
- `cabecera.jspf`: HTML base, navegación, Bootstrap, Bootstrap Icons y UTF-8.
- `pie.jspf`: cierre del layout y carga de Bootstrap JavaScript.

## Base de datos

El archivo `sql/01_modelo_inmobiliaria_mariadb.sql` crea la base `inmobiliaria_uts`, sus tablas, relaciones y datos iniciales. El modelo contiene:

- Usuarios, roles, perfiles y entidades inmobiliarias.
- Ciudades, tipos de propiedad y características.
- Propiedades e imágenes.
- Citas y solicitudes.
- Documentos de solicitudes.
- Favoritos.
- Auditoría de operaciones.

### Configuración local

La conexión actual está definida en `web/WEB-INF/jspf/conexion.jspf`:

```java
jdbc:mariadb://localhost:3306/inmobiliaria_uts?useUnicode=true&characterEncoding=UTF-8
usuario: bryan
clave: root
```

Antes de usar el proyecto fuera de un entorno académico, se deben cambiar las credenciales y evitar guardarlas directamente en el código fuente.

## Instalación

### 1. Crear la base de datos

Ejecutar el script desde MariaDB:

```bash
mysql -u root -p < sql/01_modelo_inmobiliaria_mariadb.sql
```

Verificar que el usuario configurado en `conexion.jspf` tenga permisos sobre `inmobiliaria_uts`.

### 2. Compilar el filtro

Con Tomcat instalado, localizar el API Servlet y compilar la clase:

```bash
SERVLET_JAR=/usr/share/java/tomcat-servlet-4.0-api.jar
javac -cp "$SERVLET_JAR" \
	-d web/WEB-INF/classes \
	src/com/uts/inmobiliaria/FiltroControlAcceso.java
```

### 3. Desplegar en Tomcat

```bash
TOMCAT_WEBAPPS=/var/lib/tomcat/webapps
sudo rm -rf "$TOMCAT_WEBAPPS/parcialEntrega"
sudo cp -r web "$TOMCAT_WEBAPPS/parcialEntrega"
sudo chown -R tomcat:tomcat "$TOMCAT_WEBAPPS/parcialEntrega"
```

Iniciar o reiniciar Tomcat y abrir:

```text
http://localhost:8080/parcialEntrega/
```

El proyecto usa UTF-8 en `web.xml` y en la cabecera JSP para mostrar correctamente tildes, eñes y demás caracteres del español.

## Usuarios de prueba

El script SQL inserta estas cuentas, todas con la contraseña `Clave123`:

| Rol | Correo |
|---|---|
| Cliente | `ana@ejemplo.com` |
| Cliente | `luis@ejemplo.com` |
| Inmobiliaria | `agente1@ejemplo.com` |
| Inmobiliaria | `agente2@ejemplo.com` |
| Administrador | `admin@ejemplo.com` |

También se crean usuarios adicionales de demostración en el script SQL.

## Consideraciones de desarrollo

- Los archivos `.jsp` se compilan dinámicamente por Tomcat.
- Los fragmentos `.jspf` se incluyen textualmente en cada JSP; por eso sus variables deben tener nombres que no colisionen con las variables de la página que los incluye.
- `web.xml` es la fuente única de registro del filtro de acceso.
- El driver MariaDB debe estar en `web/WEB-INF/lib`.
- Los cambios en JSP normalmente requieren limpiar la caché de trabajo de Tomcat si el servidor continúa mostrando una versión anterior.
