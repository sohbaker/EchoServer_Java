# echoServer

## Tasks
- [X] Build a simple single-threaded echoServer
- [X] Extend the echoServer to be multi-threaded

## Tests
This application uses [Gradle](https://docs.gradle.org/current/userguide/what_is_gradle.html) for build automation and [JUnit](https://junit.org/junit5/) for testing.
More specifically, this application uses [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html).

- Tests can be run using the command `./gradlew test`.

## Running the server
1. To compile`./gradlew clean build` to compile.
2. Select a PORT number to host your server on locally, and an EXIT_WORD that clients can type to terminate their connection
3. To launch the echoServer: ```./gradlew --console plain run --args="PORT EXIT_WORD"```
4. Open a new terminal window and connect a new client by typing `nc localhost PORT`. This will establish a connection.
5. You can then type messages into the echo client and it should be echoed back, until you type the exit word which terminates the connection for that client.