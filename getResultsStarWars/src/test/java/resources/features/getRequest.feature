# language: es

Característica:Comprobar el artículo de Wikipedia mostrando para los personajes de Star Wars

  Esquema del escenario: Solicitar nombre de personaje de Star Wars y comprobar su artículo en Wikipedia

    Dado que soy usuario solicitando el personaje SW <número> de la API de Star Wars
    Y busco el nombre del personaje en la página de búsqueda de Wikipedia
    Entonces debería ser capaz de ver la página del artículo correctamente mostrada para el personaje

    Ejemplos:
      | número |
      | 1      |
      | 2      |
      | 3      |
      | 4      |
      | 5      |