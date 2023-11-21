# spring-boot-crud-example
Storage for all Boot application programs. 

Database : H2 Database(In-Memory)

### Errors : 
For H2 not functioning properly and asking for Driver:

Delete the H2 jar and reinstall using mvn clean install -DskipTests  
https://stackoverflow.com/questions/40137347/h2-embeded-db-with-spring-boot-error-auto-configuring 

Annotate the Controller classes with @RestController and not @Controller  
https://stackoverflow.com/a/49854383

Running on Server Port : 5000

>Following Tutorial : https://www.youtube.com/watch?v=IucFDX3RO9U

>Spring Security : https://www.youtube.com/watch?v=tWcqSIQr6Ks

src/main/java : Contains all Java classes.  
src/main/resources : Contains the Postman Collections needed to test the application.

```
For mappings please refer the controllers or search "@RestController("
```
 

