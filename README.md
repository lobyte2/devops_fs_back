# 🚒 Sistema de Gestión de Emergencias Municipales (Backend)

Este proyecto es una plataforma basada en una **Arquitectura de Microservicios** diseñada para gestionar de manera eficiente las emergencias a nivel municipal (bomberos, rescates y operaciones).

El sistema está completamente contenerizado, garantizando un despliegue ágil y estandarizado en cualquier entorno de desarrollo o producción.

## 🏗️ Arquitectura del Sistema

El proyecto se compone de 5 microservicios de negocio y 2 servicios de orquestación central:

1. **API Gateway (`ms-gateway`):** Único punto de entrada al sistema (Puerto 9090). Enruta dinámicamente las peticiones usando balanceo de cargas.
2. **Eureka Server (`ms-eureka`):** Servidor de descubrimiento (Service Discovery). Registra y localiza dinámicamente cada microservicio activo.
3. **Microservicio de Alertas:** Gestión y emisión de alertas de emergencia.
4. **Microservicio de Monitoreo:** Supervisión en tiempo real de los recursos y eventos.
5. **Microservicio de Reportes:** Generación de estadísticas y reportes de incidentes.
6. **Microservicio de Usuarios:** Gestión de personal, bomberos y roles de acceso.
7. **Microservicio de Historial:** Auditoría y registro histórico de las emergencias atendidas.

## 🛠️ Stack Tecnológico

* **Backend:** Java 17, Spring Boot 3.x
* **Orquestación y Red:** Spring Cloud (Netflix Eureka, Spring Cloud Gateway)
* **Base de Datos:** PostgreSQL (Alojada en la nube mediante Supabase)
* **Infraestructura:** Docker, Docker Compose (Multi-stage builds)
* **Gestión de Dependencias:** Maven

---

## 🚀 Guía de Despliegue (Local)

Gracias a la Infraestructura como Código (IaC), levantar el sistema completo requiere un solo comando. No es necesario instalar Java ni Maven en la máquina anfitriona, ya que la compilación se realiza internamente en Docker.

### Requisitos Previos
* [Git](https://git-scm.com/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) (Asegurarse de tener habilitado WSL 2 en Windows).

### Pasos de Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/lobyte2/Caso_semetral_FS.git
   
   Despues entrar a la carpeta bomberos:
   cd bomberos
2. **Levantar el sistema con Docker Compose:**
   ```bash
   docker compose up -d --build
   ```
3. **Acceder a la aplicación:**
    ```bash
   Eureka-server: "http://localhost:8761/"

4. **Detener el sistema:**
   ```bash
   docker compose down
   ```
