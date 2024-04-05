# ZGW Playwright Test Automation Library

![github-actions-ci](https://github.com/CommonGround-Testing/zgw-playwright-base/actions/workflows/ci.yml/badge.svg) ![Maven Central](https://img.shields.io/maven-central/v/io.github.commonground-testing/zgw-playwright-base)

## Let's get started

The intention of this library is to provide an easy setup and execution for playwright tests for the zgw-platform (zaak
gericht werken). This mainly entails implementations of three applications Gzac, Openforms and Klantportaal.

### Testrunner

To start off with the library we need to create a class that will be responsible for your implementation specific setup.
To refer to this class, we'll name it a "runner".
So, create a class and call it <nameOfYourApp>Runner.class. In this class extends it with the class ZGWTestRunner. This
is a class in the library that contains basic Playwright setup.
Example:

```java

import runner.ZGWTestRunner;

public class OpenFormsTestRunner extends ZGWTestRunner {

}
```

Next you need to supply the baseUrl of your application. See below.

```java

import runner.ZGWTestRunner;

public class OpenFormsTestRunner extends ZGWTestRunner {

    public OpenFormsTestRunner() {
        super("https://openformulieren-zgw.test.denhaag.nl/aanvraag-formulier-ooievaarspas");
    }
}
```

It is worth noting that if you want to run tests on different environment (e.g. test & acceptance) instead of entering a
hardcoded url.

### Steps

Next thing you will want to do is setup your steps. Steps are classes that contain easily readable methods that perform
actions in the browser. We are going to initialize these steps in a method and using a Junit Annotation "@BeforeEach".
This just means the junit runner know to perform this before each test.

```java

import org.junit.jupiter.api.BeforeEach;
import runner.ZGWTestRunner;
import steps.gui.openforms.OpenFormsSteps;

public class OpenFormsTestRunner extends ZGWTestRunner {

    protected OpenFormsSteps formsSteps;

    public OpenFormsTestRunner() {
        super("https://openformulieren-zgw.test.denhaag.nl/aanvraag-formulier-ooievaarspas");
    }

    @BeforeEach
    public void setupSteps() {
        formsSteps = new OpenFormsSteps(page);
    }
}
```

General info: https://medium.com/@efthymiou.dimitrios1/how-to-publish-your-library-to-maven-central-3923139967e1

Deploy naar Maven Central
mvn clean deploy -DskipTests

Check the following:

- the pom.xml has the correct version number for the library (1 higher than the latest one as this will become the new
  one)
- keep your gpg password at hand because you need it during the deploy