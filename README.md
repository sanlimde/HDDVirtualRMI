# HDDVirtualRMI
File server made with RMI technology with user authentication and file sharing options

No es consistente el servidor, hace falta mucho trabajo aún.

TODO:
- Registrar clientes en el servidor
- Posibilidad de realizar envío de un fichero del cliente al servidor (también con posibilidad de copia si se quiere)

NOTA:

Debido a la falta de tiempo y a lo que se quiere realizar optaría por un sistema como el siguiente:

Disponemos de tres clases:
- Cliente
- ServicioFicheroImpl
- HDDVirtualImpl

Dentro de HDDVirtualImpl se hace uso del ServicioFichero y este a su vez de Cliente. O lo que es lo mismo. Tendremos una
serie de clientes a los cuales les asociamos unos servicios que es gestionado por el HDDVirtualImpl. De este modo
creamos un directorio único llamado "hdd" y dentro de él un directorio para cada cliente nuevo:

hdd
|
|-cliente1
|    |-Fichero1
|    |-Fichero2
|
|-cliente2
|    |-Fichero1
|
|-clienteN


Dentro de cada directorio del cliente se administra los ficheros.

Es evidente que hay que controlar que si el cliente existe (o lo que es lo mismo, el directorio), no se puede crear nuevamente
por lo tanto se estará accediendo al directorio del cliente. No comprobamos autenticación, porque de ser así tendríamos que
usar algún método con bases de datos o similar y no es plan de meterse en tales tareas.
