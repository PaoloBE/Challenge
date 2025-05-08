# challenge


API REST JAVA 21, Spring-boot.
Código de la solución de java

Instrucciones: 
- Descargar el zip enviado con el siguiente contenido:  
  > - Archivo docker-compose.yaml  
  > - Dockerfile del jar  
  > - Jar del empaquetaddo de la solución java  
  > - Carpeta con el script init de la BD postgres 
  > - Colección Postman
- Extraer el zip 
- Dentro de la carpeta extraida, abrir una consola de comandos y ejecutar
  > docker-compose up
---
## Contenido
- Controller   
  - CalcController: "api/calculate", devuelve el resultado del cálculo
  - LogsController: "api/logs/all", devuelve lista de logs guardados en la BD postgres
- Service  
  - CalcService: metodos para llamar al LogMetricrepository y realizar el calculo de dos números
  - CacheService: Service para limpieza paulatina del cache 
- Repository  
  - LogMetricRepository: Con implementación de findAll
- Entity  
  - LogMetric: Entity de la tabla log_metric
---
