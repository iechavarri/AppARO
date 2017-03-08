# Version actualizada del anteproyecto disponible en la [wiki](https://github.com/iechavarri/AppARO/wiki/Anteproyecto)

## Estado del arte
Ya existen aplicaciones para organizar eventos entre grupos de amigos ([kedadas](https://play.google.com/store/apps/details?id=com.valiantbit.kedadas&hl=es) por ejemplo) y para hacer seguimiento de rutas previamente planificadas ([wikiloc](https://play.google.com/store/apps/details?id=com.wikiloc.wikilocandroid&hl=es)) o para buscar compañeros de ruta ([esta para ciclistas](https://play.google.com/store/apps/details?id=com.moveando.ciclismo&hl=es) por ejemplo).

Pero no existe una aplicación en la que organices la ruta y agregues a tus amigos a ella. 

## Descripción del proyecto
Pretendemos crear una aplicación de Android que ayude a gente aventurera a organizar y realizar excursiones / eventos manteniendo geolocalizados a los compañeros. La aplicación servirá para crear un grupo en el cual chatear y organizar la excusrión o evento de manera privada entre los participantes, mostrarles en un mapa a los integrantes del grupo el trazado que se va a seguir y una vez llegada la fecha del evento, realizar un seguimiento sobre el mapa de cada miembro del grupo. Posteriormente, podrán compartir la ruta que han realizado, para crear nuevos eventos con otras personas o repetir el mismo.

## Objetivos
Nuestro primer objetivo es obtener una aplicación funcional, que cumpla con la descripción mínima del proyecto.
Conseguir financiación suficiente por parte de los usuarios para mantener a flote el proyecto.
Alcanzar una gran cantidad de usuarios.

### Funcionalidades
 - Sin pantalla de login, identificacion por el ID del teléfono + numero de teléfono.
 - Chat entre los invitados al evento y el organizador
 - Seguimiento de la ruta y de los compañeros sobre un mapa
 - Importado y exportado de las rutas a realizar / rutas realizadas
 - Aviso de emergencia

### Plataforma
Android Nativo. Se pretende que toda la interacción con la aplicación sea a través del móvil.

### Público objetivo
Gente aventurera, que le gusta el montañismo, el btt, el enduro… 

### Financiación
- Anuncios en la app
- Versión premium
- Ofrecerse a comunidades/asociaciones/empresas que les pueda intereasar

### Estimación del éxito
Es improbable que los usuarios la busquen en el market. El mejor medio será el boca a boca a partir de familiares, amigos y redes sociales.

## Plan de trabajo
Vease [#4](../issues/4) y [commit](../commit/cc2e4c928df1570d09d4eee748a96b5f651bcf3e)
### Metas del proyecto
Nuestro objetivo es crear una aplicación perfecta para la gente aventurera, que le gusta el montañismo, el btt, el enduro…
Esta aplicación se va a llamar kedadas. Será creada para que los usuarios puedan quedar para ir al monte, ya sea a hacer rutas de enduro, btt, alpinismo… Está aplicación además de permitir quedar, permite hacer un seguimiento de la kedada en tiempo real, con un pequeño mapita, un chat un botón para compartir contenidos multimedia y un botón para realizar una llamada de emergencia en caso de que sea necesario. 

### Objetivos a corto plazo
 - Crear la aplicación
 - Financiar la aplicación mediante el uso de anuncios de Google

### Objetivos a medio plazo
 - Diversificar los anuncios, ofreciendo a distintas empresas publicitarse con mayor o menor frecuencia, recaudando así más dinero que con los anuncios de Google.

###Objetivos a largo plazo
 - Hacer que la aplicación sea lo mas segura posible en cuanto a comunicaciones entre sus usuarios, creando una arquitectura que no guarde ningún dato sobre los usuarios, ni permita la supervisión de las comunicaciones. Así, aseguraremos a los usuarios total privacidad respecto a sus quedadas.

### Restricciones y estrategia
Una de las mayores restricciones que tenemos es el consumo de batería. Si durante una kedada tenemos que estar trackeando la posición del usuario en cada momento el consumo de batería puede ser excesivo en función del tipo de kedada que sea, la frecuencia con la que se trackea la posición del usuario será distinta.
Queremos que los usuarios no necesiten meter ningún dato para registrarse, que la aplicación les registre automáticamente, con su número de teléfono y con el identificador de teléfono Android.

### Calendario

fecha       | objetivo
------------|-------------------------------------------------------------------------
8 de marzo  | primera versión del login y la pantalla inicial
12 de abril | primera versión de la aplicación, esta versión todavía no será pública.
10 de mayo  | primera versión de la app, esta versión sí que será pública.

## Requisitos para funcionamiento
Necesitamos el servidor donde habitarán las bases de datos y las APIs de acceso a ellas.
Un teléfono Android con GPS.

## Bocetos
### Pantalla principal
![principal](https://github.com/iechavarri/AppARO/blob/master/mockups/principal.png)
### Lista de KDDS
![ListaKDDs](https://github.com/iechavarri/AppARO/blob/master/mockups/listaKDDs.png)
### Añadir amigos a KDD
![anadir](https://github.com/iechavarri/AppARO/blob/master/mockups/anadir.png)
### Chat previo a la KDD
![ChatPrevio](https://github.com/iechavarri/AppARO/blob/master/mockups/ChatPrevio.png)
### Progreso de la KDD
![progreso](https://github.com/iechavarri/AppARO/blob/master/mockups/progreso.png)