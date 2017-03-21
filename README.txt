TDD (Test Driven Development) is a project dedicated to exploring various unit testing frameworks and practices.
At time of writing (21/03/2017) it includes a basic Restful web service 'TDDRestService' with associated classes,
a simple business object 'StringCalculator', and a test package to exercise both classes.

The test package includes code and tests to experiment with the following APIs
junit
Mockito
REST Assured
maven split testing plugins (maven-failsafe and maven-surefire)

The ultimate goal of this project is to develop a maven based project with continuous integration build testing.
This is yet to be achieved due to issues getting maven to deploy the war to tomcat and start the server.
Testing thus far is limited to manually deploying the war and starting tomcat and then running the maven build 
to execute the tests.

I have configured eclipse to start/stop tomcat 8.5.4 so at least I don't have to manually do that as well.
