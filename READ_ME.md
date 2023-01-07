# EAA GraphQL (Victor Corbet)

1. Dieses EAA-Demo Projekt verwendet 
das Framework [Spring](https://spring.io/projects/spring-boot) 
und das Build-Management-Tool [Maven](https://maven.apache.org/).
2. Zur Umsetzung der GraphQL-API wurde die Bibliothek 
[GraphQL Java Kickstart](https://github.com/graphql-java-kickstart) verwendet.
3. Zum Start die Main-Methode in EAA_GraphQlDemoApp ausführen oder in der IDE den
grünen Pfeil drücken. Es sind keine weiteren Konfigurationen nötig.


## Web-Client

Die App beinhaltet einen Web-Server, der beim Start der Anwendung mitgestartet wird. Ruft man

```sh
$ http://localhost:8080/playground
```
im Browser auf, so wird der Web-Client an den Browser geschickt und angezeigt. Im Web-Clinet
kann  man nun GraphQL Anfragen (rechte Seite) gegen das Backend stellen. Die Response wird im Web-Client
(links Seite) angezeigt.

##GraphQL-API Visualisieren
Möchte man sich die Gesamte GraphQL-API des EAA-Demo-Projekts in einem interaktiven
Graph ansehen um diese besser zu verstehen, kann folgender 
link aufgerufen werden.
```sh
$ http://localhost:8080/voyager
```
Dafür wird die Bibliothek [graphql-voyager](https://github.com/IvanGoncharov/graphql-voyager)
verwendet
## View H2 Database (Data-Souce)

Nachdem die Anwendung gestartet wurde, kann die Datenbank unter

```sh
http://localhost:8080/h2-console/
# JDBC URL: jdbc:h2:mem:testdb
# username: sa
# password: 
```
eingesehen werden. 