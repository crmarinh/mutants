# Magneto X-Men

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.

/Mutant

Atravez de la API /mutant detecta si un humano es mutante basandose en su secuencia de ADN,
la API recibe como argumento un parametro de array de strings que representa cada fila de
una tabla de (NxN) con la secuencia del ADN.

En caso de que se verifique un mutante la API retornara un HTTP 200-OK, en caso contrario un 403-Forbidden

Esta API tiene las siguientes validaciones de lista blanca:
1. No permite arreglo vacio
2. No permite arrelo nulo
3. No permite texto de longitudes diferentes a la longitud del array
4. Solo permite texto que contenga las letras (A,T,C,G)

/stats

Atravez de la API /stats retorna un Json con las estadisticas de las verificaciones de ADN, por ejemplo:
```
{“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
```

### Comó usar este proyecto local

1. Clone el repositorio
```
https://github.com/cristian3140421/mutants.git
```

2. importelo en su IDE, de preferencia Intellij, como un proyecto gradle

3. Si no tiene habilitado la auto descarga de dependencias de un proyecto 
   gradle hagalo manual, en caso contrario espere a que este termine de descargar todas las dependencias:

4. Ten en cuenta usar Java 11

4. Ejecute la clase MutantsApplication.java que se encuentra en el paquete com.mutants


## Usar API mutant

### Use una herramienta que le permita el envio de peticiones HTTP Rest como Postmant o Insomnia
###### Request URL 
```
POST -> http://localhost:8080/mutant
```
###### Request Body

Si quieres hacer la prueba con un:

###### ADN mutante
```
{
	"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```
###### ADN Humano
```
{
	"dna":["GTGCTA","CAGTGC","TGAAGT","AGTGGG","CGCCTA","TCACAT"]
}
```

## Usar API Stats

### Use una herramienta que le permita el envio de peticiones HTTP Rest como Postmant o Insomnia
###### Request URL 
```
GET -> http://localhost:8080/stats
```


Tambien puede usar este servicio que se encuentra hosteado en **Amazon Web Services** con las siguientes URLs
```
POST -> http://ec2-3-21-230-136.us-east-2.compute.amazonaws.com/mutant
GET -> http://ec2-3-21-230-136.us-east-2.compute.amazonaws.com/stats
```

#Docker

Puedes usar la imagen publica **cristian159/mutants** 
https://hub.docker.com/repository/docker/cristian159/mutants

Puedes usar el siguiente comando docker:
```
docker run -d -p 8080:8080 cristian159/mutants
```
Con esto se creara un contenedor con el aplicativo el cual se podra acceder por medio de docker host que puede ser la URL **http://localhost:8080**
esto depende si tienes docker for windows para windows 10 pro o si tienes docker con virtuabox el cual te proveerá una URL diferente.


### Crear imagen local
 
Tambien puedes crear la imagen local.

1. Construyes el proyecto, posicionado en la raiz del proyecto
```
gradle build
``` 
2. Ejecutas el Dockerfile
```
docker build -t <nombre de imagen> .
``` 