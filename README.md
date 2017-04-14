# springboot-react-webpack
A demo project with Spring Boot, React and Webpack

This project includes :
* Spring Boot as backend resource
* React as client JS framework
* webpack to translate JSX to JS and manage client resources link
* maven-release-plugin
* cf-maven-plugin
* docker-maven-plugin (from Spotify)

## Profile

There are 3 profiles :
* the default one (without specification) is for development mode.
* _production_ used to generate the production ready client resources
* _docker_ to access to the Docker plugin and generate an image

## Launching

To launch this project, just use the following command line :

In development mode :
```
mvn clean spring-boot:run
npm run watch
```
In production mode :
```
mvn clean spring-boot:run -P production
```
Without a profile, you have to run the webpack watcher to deliver client resources. With the _production_ profile, no needs to launch the wepback watcher.

## Configuration
To use the CloudFoundry and Release plugins, the _settings.xml_ must contains the following lines :

```xml
  <servers>
      <server>
              <id>cloudfoundry</id>
              <username>username</username>
              <password>password</password>
          </server>
      <server>
              <id>github</id>
              <username>username</username>
              <password>password</password>
          </server>
  </servers>
```

## Spring Boot

Spring Boot is used as the backend server. It provides the HTML pages and the REST endpoints.
React is server side compiled at runtime using nashorn.

## React

React is the client side library. It's possible to write JSX which will be translate by webpack.


## Webpack

### Configuration

Webpack configuration is done by the _webpack.config.js_ file at the project's root.

### Usage

Webpack is launched at the _generate-resources_ maven phase.
In this configuration, webpack provides resources (JS and CSS) for commons librairies and custom JS scripts. It takes in account LESS. The HTML final resource is generated from a template adding the needed resources.
Using the default profile and the watcher, the HotModuleReplacement module is activated, so no need to refresh the page when updating a JS or a CSS, webpack does it.


## Plugin release

### Configuration

To manage the release process with Git, you have to replace the link in the _developerConnection_ tag with the Git project URL.

### Usage

The _maven-release-plugin_ allows to release an app tagging the repository. A release is :
* changing from SNAPSHOT to stable
* creating a tag (locally and remotely)
* increasing the SNAPSHOT version

Two steps :
```
mvn release:prepare
mvn release:perform
```

To rollback a _prepare_ :
```
mvn release:rollback
```

To test the release :
```
mvn -DdryRun=true release:prepare
mvn release:clean (test cleaning)
```

### Documentation

https://maven.apache.org/maven-release/maven-release-plugin/index.html

## Plugin CloudFoundry

### Configuration

Change the _TOFILL_ strings in the comment plugin's section.

### Usage

The _cf-maven-plugin_ plugin allows to manage the application in a CloudFoundry platform and specifically to push it :

```
mvn cf:push
```

### Documentation

http://docs.cloudfoundry.org/buildpacks/java/build-tool-int.html


## Plugin Docker

### Configuration

The _docker-maven-plugin_ (from Spotify) plugin has no configuration.

### Usage

The default project packaging (for CloudFoundry) is a WAR. In the Docker image, we use a JAR. To use this plugin (and the correct packaging), use the _docker_ profile :

```
mvn package -P docker
```

This command generates a local Docker image. To manage it, use the _docker_ profile to access the _docker-maven-plugin_ (such as push it).

### Documentation

https://github.com/spotify/docker-maven-plugin
