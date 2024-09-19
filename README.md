# Proyecto de Gestión de Franquicias

Este proyecto es una API de Spring Boot con WebFlux para gestionar franquicias, sucursales y productos. Incluye endpoints para agregar, eliminar, modificar y consultar las entidades mencionadas, además de permitir modificar el stock de los productos y listar productos por sucursales.

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Docker](https://www.docker.com/get-started)
- [Terraform](https://www.terraform.io/downloads) (si deseas usar la infraestructura como código para aprovisionar la base de datos)
- CLI de AWS habilitado para crear infraestructura

## Configuración del proyecto

1. **Clonar el repositorio**

   Clona el repositorio del proyecto en tu máquina local:

   ```bash
   git clone git@github.com:Eriklp/franquicias.git
   cd franquicias
## Levantar la infraestructura para la base de datos
```bash
   cd terraform
```
crear el archivo `terraform.tfvars` y setear las variables con las cuales se levantara la infra de RDS: 
```HCL 
   db_username = "Admin"
   db_password = "Admin1234"
```
Luego se aprovisiona la infra con:
```bash
   terraform init
   terraform plan
   terraform apply
```
Al finalizar debe tener una salida similar: 
```bash
    Outputs:   
    db_endpoint = "terraform-20240919174939510200000001.c1eswu0ek9cf.us-east-1.rds.amazonaws.com:3306"
    db_name = "franquiciasdb"
```
con el output se debe setear en el application.properties los valores respectivos: 
```text
spring.datasource.url=jdbc:mysql://host.docker.internal:3306/franquiciasdb <--aqui el endpoint
spring.datasource.username=${db_username}
spring.datasource.password=${db_password}
```

si se desea ahi mismo se ponen las credenciales con las que se levanto la instancia de RDS, o si no tambien se puede pasar como variables de entorno al correr el contenedor

## Ejecutando la aplicacion

en el directorio raiz: 
```bash
docker build -t franquicias .
#que correra lo tests y buildeara la aplicacion
docker run -p 8080:8080 -e db_username=Admin -e db_password=Admin1234 franquicias
#para correr la aplicacion pasandole las variables de entorno previamente configuradas
```
## Uso de la Aplicación

### Endpoints

#### Franquicia (Franchise)

- **Agregar una nueva franquicia**:

    ```http
    POST /franquicia
    ```

  **Cuerpo de la solicitud**:
    ```json
    {
      "nombre": "Nombre de la Franquicia"
    }
    ```

- **Obtener todas las franquicias**:

    ```http
    GET /franquicia
    ```

  **Respuesta**:
    ```json
    [
      {
        "id": 1,
        "nombre": "Nombre de la Franquicia"
      }
    ]
    ```

- **Eliminar una franquicia por ID**:

    ```http
    DELETE /franquicia/{id}
    ```

#### Sucursal (Branch)

- **Agregar una nueva sucursal**:

    ```http
    POST /sucursal
    ```

  **Cuerpo de la solicitud**:
    ```json
    {
      "nombre": "Nombre de la Sucursal",
      "direccion": "Dirección",
      "franquiciaId": 1
    }
    ```

- **Obtener todas las sucursales**:

    ```http
    GET /sucursal
    ```

  **Respuesta**:
    ```json
    [
      {
        "id": 1,
        "nombre": "Nombre de la Sucursal",
        "direccion": "Dirección",
        "franquicia": {
          "id": 1,
          "nombre": "Nombre de la Franquicia"
        }
      }
    ]
    ```

- **Eliminar una sucursal por ID**:

    ```http
    DELETE /sucursal/{id}
    ```

#### Producto (Product)

- **Agregar un nuevo producto**:

    ```http
    POST /producto
    ```

  **Cuerpo de la solicitud**:
    ```json
    {
      "nombre": "Nombre del Producto",
      "precio": 100.0,
      "sucursalId": 1
    }
    ```

- **Obtener todos los productos**:

    ```http
    GET /producto
    ```

  **Respuesta**:
    ```json
    [
      {
        "id": 1,
        "nombre": "Nombre del Producto",
        "precio": 100.0,
        "sucursal": {
          "id": 1,
          "nombre": "Nombre de la Sucursal"
        }
      }
    ]
    ```

- **Actualizar el stock de un producto**:

    ```http
    PATCH /producto/{id}/stock
    ```

  **Cuerpo de la solicitud**:
    ```json
    {
      "stock": 50
    }
    ```

- **Eliminar un producto por ID**:

    ```http
    DELETE /producto/{id}
    ```


