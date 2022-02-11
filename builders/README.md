# Contents
- [Requirements](#requirements)
- [Project structure](#project-structure)
- [Maven](#maven)
- [notes](#notes)
- [Requirements](#requirements)

## Requirements
- Only one project should be in your repository. Build scripts for different build tools should be in the root directory of the project.
- Repository MUST not contain result artifacts (jar, war).
- Build script should allow test running.

## Project structure

![image](https://user-images.githubusercontent.com/37680968/146538202-3918d32a-db98-4a7b-a9d8-c866cb2194a8.png)

## Maven

In root folder, create `pom.xml`

Add modules to build

```
    <modules>
        <module>web</module>
        <module>utils</module>
        <module>service</module>
        <module>admin</module>
    </modules>
```

and `junit` for testing

```
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

### notes

For all modules, add java 11

```
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>
```

### utils
N/A

### services

Need to add `utils`

```
        <dependency>
            <groupId>com.tuanmhoang.builders</groupId>
            <artifactId>utils</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

### web

Need to add `javax` and `services`

```
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.tuanmhoang.builders</groupId>
            <artifactId>services</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

### admin

Need to add `builders`

```
        <dependency>
            <groupId>com.tuanmhoang.builders</groupId>
            <artifactId>services</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

### build specific module and its dependencies

Reference: https://stackoverflow.com/questions/1114026/maven-modules-building-a-single-specific-module

**admin**

`mvn install -pl admin -am`

### maven assembly plugin

Reference: 
- https://www.youtube.com/watch?v=-sjefqBersY
- https://maven.apache.org/plugins/maven-assembly-plugin/index.html

Create `packaging`folder

To add to `pom.xml`

```
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <!-- MainClass in mainfest make a executable jar -->
                    <archive>
                        <manifest>
                            <mainClass>com.builders.admin.AdminEntryPoint</mainClass>
                        </manifest>
                    </archive>
                </configuration>

                <executions>
                    <execution>
                        <id>build-package</id>
                        <goals>
                            <goal>single</goal>
                        </goals>
                        <phase>package</phase>
                        <configuration>
                            <descriptors>
                                <descriptor>assembly.xml</descriptor>
                            </descriptors>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

### Instructions

Go to root folder, run `mvn clean install` to build and install to the dependencies to repository

Go to `packaging` folder, run `mvn clean package` to build jar files with the dependencies

Run `java -jar <jar file> <message>` to execute

```
java -jar .\target\packaging-app-0.0.1-SNAPSHOT-final-artifact.jar a
You say [a], I say Hello from admin! Hello from services! Hello from utils!
```

Go to root `pom.xml` run command `mvn clean test` to run the tests

## Gradle

In root folder, create `build.gradle`