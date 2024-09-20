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

A continuación se describen los endpoints disponibles para gestionar franquicias, sucursales y productos.

### Endpoints de FranquiciaController

- **Crear una franquicia**
  - **Método**: `POST`
  - **Endpoint**: `/franquicias`
  - **Descripción**: Crea una nueva franquicia.
  - **Cuerpo de la petición**:
    ```json
    {
      "nombre": "Franquicia A"
    }
    ```
  - **Respuesta exitosa**:
    ```json
    {
      "id": 1,
      "nombre": "Franquicia A",
      "sucursales": []
    }
    ```

- **Listar franquicias**
  - **Método**: `GET`
  - **Endpoint**: `/franquicias`
  - **Descripción**: Lista todas las franquicias.
  - **Respuesta**:
    ```json
    [
      {
        "id": 1,
        "nombre": "Franquicia A",
        "sucursales": []
      },
      {
        "id": 2,
        "nombre": "Franquicia B",
        "sucursales": []
      }
    ]
    ```

- **Actualizar el nombre de una franquicia**
  - **Método**: `PATCH`
  - **Endpoint**: `/franquicias/{franquiciaId}/actualizar-nombre`
  - **Descripción**: Actualiza el nombre de una franquicia específica.
  - **Parámetros**:
    - `franquiciaId`: ID de la franquicia.
    - **Query param**: `nuevoNombre`: El nuevo nombre de la franquicia.
  - **Respuesta exitosa**:
    ```json
    {
      "id": 1,
      "nombre": "Nuevo Nombre"
    }
    ```

---

### Endpoints de SucursalController

- **Agregar una sucursal**
  - **Método**: `POST`
  - **Endpoint**: `/franquicias/{franquiciaId}/sucursales`
  - **Descripción**: Agrega una nueva sucursal a una franquicia específica.
  - **Cuerpo de la petición**:
    ```json
    {
      "nombre": "Sucursal A",
      "direccion": "Calle Falsa 123"
    }
    ```
  - **Respuesta exitosa**:
    ```json
    {
      "id": 1,
      "nombre": "Sucursal A",
      "direccion": "Calle Falsa 123"
    }
    ```

- **Listar sucursales**
  - **Método**: `GET`
  - **Endpoint**: `/franquicias/{franquiciaId}/sucursales`
  - **Descripción**: Lista todas las sucursales de una franquicia.
  - **Respuesta**:
    ```json
    [
      {
        "id": 1,
        "nombre": "Sucursal A",
        "direccion": "Calle Falsa 123"
      },
      {
        "id": 2,
        "nombre": "Sucursal B",
        "direccion": "Avenida Siempre Viva 742"
      }
    ]
    ```

- **Actualizar el nombre de una sucursal**
  - **Método**: `PATCH`
  - **Endpoint**: `/franquicias/{franquiciaId}/sucursales/{sucursalId}/actualizar-nombre`
  - **Descripción**: Actualiza el nombre de una sucursal específica.
  - **Parámetros**:
    - `sucursalId`: ID de la sucursal.
    - **Query param**: `nuevoNombre`: El nuevo nombre de la sucursal.
  - **Respuesta exitosa**:
    ```json
    {
      "id": 1,
      "nombre": "Nuevo Nombre",
      "direccion": "Calle Falsa 123"
    }
    ```

---

### Endpoints de ProductoController

- **Agregar un producto a una sucursal**
  - **Método**: `POST`
  - **Endpoint**: `/sucursales/{sucursalId}/productos`
  - **Descripción**: Agrega un nuevo producto a una sucursal específica.
  - **Cuerpo de la petición**:
    ```json
    {
      "nombre": "Producto A",
      "precio": 10.5,
      "stock": 100
    }
    ```
  - **Respuesta exitosa**:
    ```json
    {
      "id": 1,
      "nombre": "Producto A",
      "precio": 10.5,
      "stock": 100
    }
    ```

- **Listar productos de una sucursal**
  - **Método**: `GET`
  - **Endpoint**: `/sucursales/{sucursalId}/productos`
  - **Descripción**: Lista todos los productos de una sucursal específica.
  - **Respuesta**:
    ```json
    [
      {
        "id": 1,
        "nombre": "Producto A",
        "precio": 10.5,
        "stock": 100
      },
      {
        "id": 2,
        "nombre": "Producto B",
        "precio": 15.0,
        "stock": 50
      }
    ]
    ```

- **Actualizar stock de un producto**
  - **Método**: `PATCH`
  - **Endpoint**: `/sucursales/{sucursalId}/productos/{productoId}/modificar-stock`
  - **Descripción**: Actualiza el stock de un producto específico.
  - **Parámetros**:
    - `sucursalId`: ID de la sucursal.
    - `productoId`: ID del producto.
    - **Query param**: `nuevoStock`: El nuevo stock del producto.
  - **Respuesta exitosa**:
    ```json
    {
      "id": 1,
      "nombre": "Producto A",
      "precio": 10.5,
      "stock": 120
    }
    ```

- **Eliminar un producto**
  - **Método**: `DELETE`
  - **Endpoint**: `/sucursales/{sucursalId}/productos/{productoId}`
  - **Descripción**: Elimina un producto de una sucursal específica.
  - **Parámetros**:
    - `sucursalId`: ID de la sucursal.
    - `productoId`: ID del producto.
  - **Respuesta exitosa**:
    ```json
    {
      "message": "Producto eliminado exitosamente"
    }
    ```

- **Actualizar el nombre de un producto**
  - **Método**: `PATCH`
  - **Endpoint**: `/sucursales/{sucursalId}/productos/{productoId}/actualizar-nombre`
  - **Descripción**: Actualiza el nombre de un producto específico.
  - **Parámetros**:
    - `sucursalId`: ID de la sucursal.
    - `productoId`: ID del producto.
    - **Query param**: `nuevoNombre`: El nuevo nombre del producto.
  - **Respuesta exitosa**:
    ```json
    {
      "id": 1,
      "nombre": "Nuevo Nombre",
      "precio": 10.5,
      "stock": 100
    }
    ```




