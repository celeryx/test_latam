<a name="top"></a>
# Birthday Calculator

## Índice de contenidos
* [Instrucciones](#item1)
* [Pre-Requisitos](#item2)
 
<a name="item2"></a>
### Pre-Requisitos

Tener docker instalado 
Tener docker-compose instalado
Tener GIT instalado

Puerto 80 y 8090 libres:
```     
Windows:
      CMD: 
            Para revisar ejecute el comando "netstat -a -b | findstr 80" (comando se ejecuta en CMD)
                
      POWERSHELL: 
            Para revisar ejecute el comando " Get-Process -Id (Get-NetTCPConnection -LocalPort 80).OwningProcess" (comando se ejecuta en la powershell)
        
Linux:
      TERMINAL: 
            Para revisar ejecute el comando "netstat -tulpn | grep --color :80", tambien se puede usar el comando:  "lsof -i :80 | grep LISTEN" 
```

En caso de estar los puertos en uso:
``` 
    Windows:
           CMD:
               Ejemplo de salida:
                                  TCP    0.0.0.0:80             account:0              LISTENING
                                  TCP    0.0.0.0:8090           account:0              LISTENING
            POWERSHELL: 
                Ejemplo de salida:
                      
                                  Handles  NPM(K)    PM(K)      WS(K)     CPU(s)     Id  SI ProcessName
                                  -------  ------    -----      -----     ------     --  -- -----------
                                    249      18    23124      22528       2,27   9944   1 com.docker.backend
  
   Linux:
         Terminal: 
               Ejemplo de salida: 
                                apache2   1607     root    3u  IPv4   6472      0t0  TCP *:www (LISTEN)
                                apache2   1616 www-data    3u  IPv4   6472      0t0  TCP *:www (LISTEN) 
                                apache2   1617 www-data    3u  IPv4   6472      0t0  TCP *:www (LISTEN)
```




<a name="item1"></a>
### Instrucciones

* Descargar repositorio, abriendo un terminal y pegando lo siguiente "git clone https://github.com/celeryx/test_latam.git"
* Entrar a la carpeta "test_latam"
* Abrir un terminal
* Ejecutar comando "docker-compose build"
* Cuando el comando termine de ejecutarse (puede tardarse bastante la primera vez que se ejecuta)
* Cuando el comando haya finalizado ejecutar el siguiente comando: "docker-compose up"
* En caso de fallar revise si cumple con los pre-requisitos

[Subir](#top)
