[![Build Status](https://travis-ci.org/perseacado/feedback-ui.svg?branch=master)](https://travis-ci.org/perseacado/feedback-ui)
![MIT License](https://img.shields.io/badge/license-MIT-blue.svg)

# feedback-ui
A Spring Boot module that adds feedback functionality to a web user-interface.

## Getting started
```feedback-ui``` uses Spring's auto-configuration functionality. This means that you only have to add the Maven dependencies and configure a couple of properties. That's it!
Please refer to the next sections to integrate ```feedback-ui``` into your aplication.

## Maven
### Release
There has not been a release of ```feedback-ui``` yet. Until the first release is published to Maven Central you can use a snapshot version.
```xml
<dependency>
    <groupId>com.github.perseacado.feedbackui</groupId>
    <artifactId>feedback-ui-slack</artifactId>
    <version>X.Y.Z</version>
</dependency>
```

### Snapshot
Step 1. Add the snapshot repository to your ```pom.xml```.
```xml
<repository>
  <id>snapshots-repo</id>
  <url>https://oss.sonatype.org/content/repositories/snapshots</url>
</repository>
```
Step 2. Add the following dependency to enable feedback-ui integration via Slack.
```xml
<dependency>
    <groupId>com.github.perseacado.feedbackui</groupId>
    <artifactId>feedback-ui-slack</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Configuration
This project uses Spring's auto-configuration functionality, thus one has to only configure a couple of properties to get ```feedback-ui``` to work. Add the following lines to your Spring application properties file (e.g., ```application.properties```):

```
# you can disable feedback-ui if you want
# feedback-ui.disabled=false
# your Slack API token. please refer to https://api.slack.com/ for more information
feedback-ui.slack.token=xoxp-12345678-12345678-12345678-abcdefghijkl
# the Slack channel to use for the feedback messages
feedback-ui.slack.channel=#feedback
# the Slack username to use for the feedback messages
feedback-ui.slack.username=my-app-feedback
```
