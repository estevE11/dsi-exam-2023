# Examen DSI 
Tenim un sistema que conté **usuaris** i les seves **notes** (o anotacions).

Dels usuaris guardem:
* username
* name
* secondName
* email

De les notes guardem:
* title
* content
* dateCreation
* dateEdit
* userName  *// és l'usuari propietari de la nota*
* checked   *// per saber segur que el propietari existeix (ja veurem més endavant la utilitat)*

Aquest sistema ja el tenim implementat amb dos microserveis que funcionen i que segueixen (quasi) l'arquitectura hexagonal.
Com us podeu imaginar són dos microserveis molt senzills que només fan operacions CRUD amb els usuaris i notes sense tenir gens
de lògica de negoci.

#### Arquitectura: (que ja teniu feta)

* Estan implementats seguint l’arquitectura hexagonal sense mapping. Al ser solament CRUD no cal complicar-nos la vida.
* Que siguin sense mapping vol dir que tots els components usen directament els objectes del domini
* Tenen els ports d’entrada i de sortida, i adaptadors web i de persistència

#### Comunicació: (que ja teniu feta)
Ara mateix només hi ha implementada una comunicació via REST d’un cap a l’altre i està fet sense tenir en compte l’arquitectura hexagonal,
és a dir, sense usar ports i adaptadors. 

En aquesta comunicació el microservei de *users*, quan llista els usuaris també incorpora les seves notes que aconsegueix
mitjançant una crida REST al servei de notes. Semblant al *productCompose* de la pràctica

#### Exemples de crides
Les teniu al fitxer calls.http situat a l'arrel del projecte

## Què heu de fer? 
Heu d'implementar els següents exercicis. Són obligatoris els TODO 1, TODO 2 i TODO 3. I heu d'escollir si voleu fer 
el TODO 4 o el TODO 5. Per tant, si feu 4 exercicis podeu obtenir 10 punts. Si algú té temps, pot implementar els dos darrers exercicis 
i aconseguir un 12, una nota superior a 10, que pot anar bé de cara a fer la mitja!

* **TODO 1:** (Val 2 punts) Fer que la comunicació via REST esmentada anteriorment, i que ja està implementada, segueixi l’arquitectura hexagonal. És a dir que hi hagi el port i l'adaptador pertinents.
  Recorda que en aquesta comunicació el microservei d'usuaris demana les notes d'aquests al microservei de notes. [Anar a TODO 1.0 i TODO 1.1]
* **TODO 2:** (Val 3 punts) Embolcallar la comunicació del punt anterior amb un *circuit breaker* de manera que quan el circuit estigui obert  
  es retorni una llista amb una única nota que tingui per títol "Servei de notes inaccessible". A la resta de propietats de la nota poseu el
  que vulgueu. [Anar a TODO 2]
* **TODO 3:** (Val 3 punts) Volem que quan es crea una nota (crida POST al microservei de notes) aquesta s'escrigui a la bbdd amb l'atribut checked
a fals i de forma asíncrona es comprovi si l'usuari existeix. Si la resposta, també asíncrona, és afirmativa aleshores es modifica la nota de la
bbdd amb l'atribut checked a true i en cas contrari s'esborra. Per tant:
  * El microservei de notes ha d'enviar un missatge per preguntar de l'existència d'un usuari (TODO 3.1), i rebre un altre missatge com a resposta a la pregunta (TODO 3.2).
  * El microservei d'usuaris ha de rebre un missatge preguntant sobre un usuari i enviar un missatge amb la resposta (TODO 3.3) 

El missatge (tant de query com de resposta) ha de contenir username, noteId i existeix (true/false):
* username: el nom de l'usuari pel que preguntem (no seria imprescindible a la resposta)
* noteId: el necessitem per saber de quina nota es tracta. El microservei d'usuaris no el necessita, però sí el de notes quan reb la resposta
* existeix: és la resposta de si l'usuari existeix o no. No caldria al missatge de pregunta.

Us proposo que tant els missatges de query com de resposta tinguin els mateixos camps per simplificar (usar la mateixa estructura a la pregunta i la resposta) però
vosaltres decidiu. Proposo el següent record com a missatge, però el podeu implementar com més us convingui:
```
record UserNoteExistsMessage(String username, Long noteId, Boolean exists) {}
```
Dels 3 punts d'aquest exercici, 2 punts pel funcionament i 1 es valorarà que es faci en arquitectura hexagonal. Per fer-ho us proposo:
* Microservei notes:
  * Port de sortida per enviar el missatge preguntant sobre l'existència d'un usuari. També haureu de fer l'adaptador corresponent (el que realment envia el missatge).
  * Adaptador per rebre el missatge de resposta. Aquest adaptador haurà d'usar el port d'entrada *UpdateNoteUserExists* per actualitzar la nota. 
    Seria com un adaptador web, però que en lloc d'esperar crides REST espera missatges del broker.
* Microservei users:
  * Adaptador que rebrà el missatge preguntant sobre l'existència de l'usuari i que també enviarà la resposta. Per saber si un usuari existeix usarà 
    el port de sortida *UsersPort*, concretament el mètode *existsUser*. Per enviar el missatge no cal que implementeu un port de sortida ja que
    en un sol mètode podeu rebre consulta, comprovar usuari, i enviar resposta.

* **TODO 4:** (Val 2 punts) Afegir un discovery service. Us heu d'assegurar que el restTemplate del primer i segon exercici esta balancejat (loadbalance) 
* **TODO 5:** (Val 2 punts) Afegir un edge (gateway) service que redireccioni les crides /users/* cap al microservei d'usuaris i les crides
/notes/* cap al microservei de notes. 
 
Als fitxers *pom.xml* hi ha totes les dependències necessàries per als tres primers exercicis. 

