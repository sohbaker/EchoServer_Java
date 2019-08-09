# echoServer

## Tasks
- [X] Build a simple single-threaded echoServer
- [X] Extend the echoServer to be multi-threaded

## Tests
This application uses [JUnit](https://junit.org/junit5/) for testing, and [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html).
- Tests can be run using the command `./gradlew test`.

## Running the server
Select a PORT number to host your server on locally. (Note: if no port is chosen, the default port is 1122).

- To launch the echoServer with a port of your choice: `./gradlew --console plain run --args="PORT"`
- To launch with the default port: `./gradlew --console plain run`

Open a new terminal window and connect a client by typing `nc localhost PORT`.

Type messages into the client window and it should be echoed back until you type `exit`, which will terminate the connection for that client.