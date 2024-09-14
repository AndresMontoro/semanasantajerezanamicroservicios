
![Logo](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/th5xamgrr6se0x5ro4g6.png)


# Semana Santa Jerezana Microservicios

API sobre la Semana Santa jerezana desarrollada en una arquitectura de microservicios.

## Características

- Arquitectura de microservicios.
- Autenticación de usuarios y requests con gestión de permisos.
- Gestión de información sobre la Semana Santa.
- Obtención de estadísticas sobre la Semana Santa.
- Notificación de eventos.


## Stack de desarrollo

**Framework:** Spring Boot, Mockito, Spring web, Spring test...

**Herramientas:** VsCode, Insomnia, Git y GitHub.


## API Reference

#### Bandas

```http
  GET /api/v1/banda
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.


## Documentation

[Documentation](https://linktodocumentation)

