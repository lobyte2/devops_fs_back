# Sistema de Gestión de Emergencias Municipales (Backend)

Este proyecto es una plataforma basada en una **Arquitectura de Microservicios** diseñada para gestionar de manera eficiente las emergencias a nivel municipal (bomberos, rescates y operaciones).

El sistema está completamente contenerizado, garantizando un despliegue ágil y estandarizado en cualquier entorno de desarrollo o producción.

## Arquitectura del Sistema

El proyecto se compone de 5 microservicios de negocio y 2 servicios de orquestación central:

1. **API Gateway (`ms-gateway`):** Único punto de entrada al sistema (Puerto 9090). Enruta dinámicamente las peticiones usando balanceo de cargas.
2. **Eureka Server (`ms-eureka`):** Servidor de descubrimiento (Service Discovery). Registra y localiza dinámicamente cada microservicio activo.
3. **Microservicio de Alertas:** Gestión y emisión de alertas de emergencia.
4. **Microservicio de Monitoreo:** Supervisión en tiempo real de los recursos y eventos.
5. **Microservicio de Reportes:** Generación de estadísticas y reportes de incidentes.
6. **Microservicio de Usuarios:** Gestión de personal, bomberos y roles de acceso.
7. **Microservicio de Historial:** Auditoría y registro histórico de las emergencias atendidas.

## Stack Tecnológico

* **Backend:** Java 17, Spring Boot 3.x
* **Seguridad:** JWT (JSON Web Tokens)
* **Orquestación y Red:** Spring Cloud (Netflix Eureka, Spring Cloud Gateway)
* **Base de Datos:** PostgreSQL (Alojada en la nube mediante Supabase)
* **Infraestructura:** Docker, Docker Compose (Multi-stage builds)
* **Gestión de Dependencias:** Maven
* **Testing y Calidad:** JaCoCo para la cobertura de código
* **Documentación:** JavaDocs

---

## Guía de Despliegue (Local)

Gracias a la Infraestructura como Código (IaC), levantar el sistema completo requiere un solo comando. No es necesario instalar Java ni Maven en la máquina anfitriona, ya que la compilación se realiza internamente en Docker.

### Requisitos Previos
* [Git](https://git-scm.com/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) (Asegurarse de tener habilitado WSL 2 en Windows).

### Pasos de Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/lobyte2/Caso_semetral_FS.git
   ```
   Después, entrar a la carpeta del backend (`bomberos`):
   ```bash
   cd Caso_semetral_FS/bomberos
   ```

2. **Levantar el sistema con Docker Compose:**
   ```bash
   docker compose up -d --build
   ```

3. **Acceder a la aplicación:**
   - **Eureka Server:** [http://localhost:8761/](http://localhost:8761/)
   - **API Gateway:** `http://localhost:9090/`

4. **Detener el sistema:**
   ```bash
   docker compose down
   ```

---

## Seguridad y Autenticación
El sistema implementa un modelo de seguridad robusto mediante **JWT (JSON Web Tokens)**. Esto asegura que la comunicación a través del API Gateway esté protegida y permite la autorización de los endpoints, garantizando que sólo el personal autenticado y con los roles adecuados (ej. administradores, bomberos) pueda acceder a los recursos o gestionar alertas.

## Calidad de Código
Se ha integrado la herramienta **JaCoCo** (Java Code Coverage) al flujo del proyecto para medir y analizar la cobertura de las pruebas unitarias e integrales del backend. Esto asegura que la lógica de negocio, reglas y validaciones estén debidamente respaldadas por tests, brindando gran fiabilidad al sistema ante cambios futuros.

## Documentación
Todo el código fuente y sus API internas han sido documentados utilizando el estándar de **JavaDocs**. Se incluyen explicaciones claras de las clases principales, responsabilidades, métodos y sus parámetros, facilitando considerablemente el entendimiento del proyecto y el mantenimiento por parte del equipo de desarrollo.
