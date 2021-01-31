# Instructions


### Run the microservice
To run the microservice, open a terminal and go to the directory where the project recides.

#### Install dependencies for the first run
Run the following command in a terminal window.

```bash
./gradlew assemble --refresh-dependencies
````

### Running the microservice
Run the following command in a terminal window.

```bash
./gradlew bootRun
````

### Run with docker
Build the project `before` running docker
```bash
./gradlew build
````

Building the docker image with a tag. The tag of the image in this case will be `inter/vue-giphy`.
```bash
docker build -t inter/vue-giphy . 
````
_Don't forget the dot (.) --------^_


Launch the container tagged as `inter/vue-giphy`
```bash
docker run -p 8080:8080 inter/vue-giphy . 
````
_Don't forget the dot (.) --------^_

### Search for giphy's
Run `curl` command in a terminal window.
```bash
curl --request GET 'localhost:8080/api/v1/giphy/query?searchTerm=ronaldinho&searchTerm=dinho'
````

### Service health check
Run `curl` command in a terminal window.
```bash
curl 'localhost:8080/api/actuator/health'
````

### Future improvements
- Add the ability to cache similar requests. 
	- Distributed cache to store responses from Giphy API for a short period of time.
- Add security
- Adding a mechanism of retrying request in case of giphy api is having a hiccup.
	- When fail fast, check cache before returning an empty/null value.
- Circuit brackers to fail fast in case of something goes wrong.
- Add a rate limiter to stablish a limit or incoming request
- Add a global exception handler. 
