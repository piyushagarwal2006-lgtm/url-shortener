# build
mvn clean package

# run
mvn spring-boot:run

Once the server starts, we can test this by doing to a POST to the below URL with the given payload:
http://localhost:8080/api/shorten -D https://www.originenergy.com.au/electricity-gas/plans.html

It would give a response like r7M8kb

Now, hitting a GET request like http://localhost:8080/api/info/r7M8kb would give info about that original long url and the short code.

http://localhost:8080/api/r7M8kb  -> would redirect to the original long URL.
