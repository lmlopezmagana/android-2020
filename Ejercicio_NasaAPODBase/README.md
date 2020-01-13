# NASA Picture of the day

Este es un proyecto base para poder implementar un cliente sencillo sobre el API de la NASA; en particular, sobre el API que nos permite consultar la imagen del día, que llevan publicando ininterrumpidamente en internet desde los años 90.

# Pasos a seguir

## Paso 1: Obtener un API KEY

Para poder utilizar el API de la NASA es necesario obtener una API KEY. Es muy sencillo hacerlo a través de esta URL: [https://api.nasa.gov/](https://api.nasa.gov/)

## Paso 2: Código fuente

Se ofrecen dos clases convenientes para poder consultar este API

### NasaPicture

Se trata de un _POJO_ que envuelve la información más elemental sobre la imagen del día (se ha utilizado Lombok)

```java
@Data @AllArgsConstructor @NoArgsConstructor
public class NasaPicture {

    private String url;
    private String title;
    private String explanation;
    private String date;

}
```

### NasaApi

Esta clase es el verdadero cliente para el API de la imagen del día de la Nasa.


## Paso 3: Instanciar NasaApi

Para poder utilizar esta clase, hay que crear una instancia, pasándole una API KEY válida:

```java
NasaApi api = new NasaApi(API_KEY);
```

## Paso 4: Métodos de consulta

### Método para obtener la imagen de hoy

```java
public NasaPicture getPicOfToday();
```

### Método para obtener la imagen de una fecha concreta

El formato de la fecha debe ser `yyyy-mm-dd`

```java
public NasaPicture getPicOfAnyDate(String date);
```

### Método para obtener las imágenes de un rango de fechas

El formato de las fechas debe ser `yyyy-mm-dd`

```java
public List<NasaPicture> getPicOfDateInterval(String from, String to)
```

# Más información

Puedes obtener más información sobre el API de la Nasa a través del siguiente proyecto de Github: [https://github.com/nasa/apod-api](https://github.com/nasa/apod-api)