# language: es

Característica: Solicitud de una película aleatoria de Star Wars a la API y verificación en Wikipedia

  Escenario: Solicitud de película aleatoria, búsqueda en Wikipedia, y verificación de la página de edición
    Dado que soy un usuario que solicita una película aleatoria a la API de Star Wars
    Cuando obtengo la respuesta de la API
    Entonces busco el título de la película en Wikipedia
    Y navego hasta la página del artículo correspondiente
    Cuando hago clic en el botón de edición
    Entonces compruebo que en el título de la página de edición incluye el título de la película