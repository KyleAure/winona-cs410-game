# Orange Team Project
Create a puzzle slider game that allows users to select a photo to be scrambled, only to be put back together by sliding tiles around. 
When the user exits the game, the puzzle progress is saved for them to continue to finish at a later time. 
Introduce other game design features to vary game-play options and allow users to save their game for later completion.

## Getting Started

As a contributor you can clone this project by executing the following command in a bash or terminal session:

```bash
#SSH clone
git clone git@git.ng.bluemix.net:jeberhard/orange-cs410-winona.git

#HTTPS clone
git clone https://git.ng.bluemix.net/jeberhard/orange-cs410-winona.git
```

### Prerequisites

This project is written in Java.
Check to make sure that your have java installed by running the following command in terminal or command prompt:

```bash
java -version
```

You should receive an output, such as,

```bash
java version "1.8.0_161"
Java(TM) SE Runtime Environment (build 1.8.0_161-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.161-b12, mixed mode)
```

### Installing

This project uses Maven for it's build, test, and deployment cycles.  In order to install this program you will need Maven installed on your local system.

To check to make sure you have Maven installed please run the following command in your terminal or command prompt:

```bash
mvn -version
```

You should get an output similar to:

```bash
Apache Maven 3.5.2
Maven home: /usr/share/maven
Java version: 1.8.0_201, vendor: Oracle Corporation
Java home: /usr/lib/jvm/java-8-oracle/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "4.15.0-45-generic", arch: "amd64", family: "unix"
```

Once you have verified that you have a current Maven installation you can run the following command to install a local version of the PuzzleGame app by running the following Maven goal:

```bash
mvn clean install
```

If you do not have Maven installed you will need to download the Maven command tools and add them to your command terminal `PATH`.

#### Option 1
If you have `homebrew` installed on your machine you can use the terminal command `brew install maven`.

#### Option 2
If you are using macOS you will need to follow these steps:

- Download the latest Maven release here (bin-zip): (Maven Downloads)[https://maven.apache.org/download.cgi]
- Unzip this folder into a convenient directory, such as, `~/Applications/maven`.
- Add this directory to your terminal PATH variable.  
    - Open your `~./bash_profile` file.
    - Create a `MAVEN_HOME` variable by adding the line: `export MAVEN_HOME=/Applications/maven` 
    - Then add or append `:$MAVEN_HOME/bin` to you `PATH` variable.
    - In the end you should have a `PATH` line that reads something like: `export PATH=$PATH:$ANT_HOME/bin:$MAVEN_HOME/bin`
- Test to make sure you have correctly added MAVEN to your terminal path.
    - Close all currently running terminal settings.
    - Open a new terminal session and run the `mvn -version` command.

## Running the tests

To run automated tests navigate to the project directory in a terminal session.
Then run the following Maven goal:

```bash
mvn test
```

If the tests are successful you will get output similar to:

```bash
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ puzzle.game ---
[INFO] Surefire report directory: /home/kaure/orange-cs410-winona/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running edu.winona.cs.app.AppTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.022 sec

Results :

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

If a test fails you will get output similar to:

```bash
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ puzzle.game ---
[INFO] Surefire report directory: /home/kaure/orange-cs410-winona/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running edu.winona.cs.app.AppTest
Tests run: 3, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.023 sec <<< FAILURE!
intValTest(edu.winona.cs.app.AppTest)  Time elapsed: 0.002 sec  <<< FAILURE!
java.lang.AssertionError: Should be 0 expected:<1> but was:<0>
--- stack trace squashed ---

Results :

Failed tests:   intValTest(edu.winona.cs.app.AppTest): Should be 0 expected:<1> but was:<0>

Tests run: 3, Failures: 1, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```

## Deployment

App deployment is outside of the scope of this project.  
We are not using any third party deployment systems to host our java application. 
Instead, users should install a local version of this application by using the following Maven goal:

```bash
mvn clean install
```

Once this application is built navigate to the `target/` directory and locate the `puzzle.game-version.jar` file.

You can also run this appliciation directly from the terminal using the following Maven goal:

```bash
mvn exec:java -Dexec.mainClass="edu.winona.cs.app.App"
```

## Built With

This project is built using the following Maven dependencies:
1. Junit - for testing.
2. Derby - for database creation / management
3. AbsoluteLayout - for GUI layout
4. ImgScalr - for image processing
5. JGoodies - for GUI layout

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Kyle Aure** - [GitHub](https://github.com/KyleAure)
* **Travis Kruse**
* **Tristin Harvell**
* **Erika Tix**

## License

This project is licensed under the Apache License - see the [LICENSE.md](LICENSE) file for details

## Acknowledgments
