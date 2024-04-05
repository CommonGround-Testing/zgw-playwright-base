# ZGW Playwright Test Automation Library

![github-actions-ci](https://github.com/CommonGround-Testing/zgw-playwright-base/actions/workflows/ci.yml/badge.svg) ![Maven Central](https://img.shields.io/maven-central/v/io.github.commonground-testing/zgw-playwright-base)

General info: https://medium.com/@efthymiou.dimitrios1/how-to-publish-your-library-to-maven-central-3923139967e1

Deploy naar Maven Central
mvn clean deploy -DskipTests

Check the following:

- the pom.xml has the correct version number for the library (1 higher than the latest one as this will become the new
  one)
- keep your gpg password at hand because you need it during the deploy