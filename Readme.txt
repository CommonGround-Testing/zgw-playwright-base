General info: https://medium.com/@efthymiou.dimitrios1/how-to-publish-your-library-to-maven-central-3923139967e1

Deploy naar Maven Central
mvn clean deploy -DskipTests

Check the following:
1 - the pom.xml has the correct version number for the library (1 higher than the latest one as this will become the new one)
2 - keep your gpg password at hand because you need it during the deploy