# ZGW Playwright Test Automation Library

![github-actions-ci](https://github.com/CommonGround-Testing/zgw-playwright-base/actions/workflows/ci.yml/badge.svg) ![Maven Central](https://img.shields.io/maven-central/v/io.github.commonground-testing/zgw-playwright-base)

## Let's get started

The intention of this library is to provide an easy setup and execution for playwright tests for the zgw-platform (zaak
gericht werken). This mainly entails implementations of three applications Gzac, Openforms and Klantportaal.

### Testrunner

To start off with the library we need to create a class that will be responsible for your implementation specific setup.
To refer to this class, we'll name it a "runner".
So, create a class and call it <nameOfYourApp>Runner.class. This class should extend the class ZGWTestRunner. This
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

It is worth noting that you will probably want to run tests on different environment (e.g. test & acceptance) instead of
entering a
hardcoded url. You will need to set this up.

### Steps

Next thing you will want to do is setup your steps. Steps are classes that contain easily readable methods that perform
actions in the browser. We are going to initialize these steps in a method and using a Junit Annotation "@BeforeEach".
This just means the junit runner knows to perform this before each test.

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
        formsSteps.login_via_digid();
    }
}
```

In this example we initialize the OpenFormsSteps. Let's look at how we can start a test now. In the same package create
a new class and extend the OpenFormsTestRunner.

```java

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import users.User;
import users.ZGWDigidUser;

public class SomeOpenFormsTest extends OpenFormsTestRunner {

    @Test
    public void someTest() {
        User user = ZGWDigidUser.builder().username("zgwdigiduser1").password("somepassword").build();
        formsSteps.login_via_digid(user);
    }
}
```

In this example we initialized a user and logged into a openforms form. Now it's up to you to add asserts! For example:

```java

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import users.User;
import users.ZGWDigidUser;

public class SomeOpenFormsTest extends OpenFormsTestRunner {

    @Test
    public void someTest() {
        User user = ZGWDigidUser.builder().username("zgwdigiduser1").password("somepassword").build();
        formsSteps.login_via_digid(user);

        PlaywrightAssertions.assertThat(page.locator("//h1")).isVisible();
    }
}
```

Perhaps this is a simplistic assert, but here we test if the h1 element is visible after logging in. You might also want
to register this element in a page object, but it shows how easy it is to setup a test.

## Test execution

When executing the tests, there are a couple of commandline options that can be set.

| parameter | type      | function                | default | 
|-----------|-----------|-------------------------|---------|
| headless  | `boolean` | Runs the tests headless | false   |
|           |           |                         |         |

Example:

```shell
mvn clean verify -Dheadless=true
```

## Storing authenticated state

With Playwright it is possible to store your authenticated state. In order to store your authenticated state we wrote a
few convenience classes. Depending on what you need or want, we have an appropiate solution.

### CacheLogin

The first mechanism would be the CacheLogin class, which has a static method `session`. It was designed so that you can
use it in a @BeforeEach in a testclass. You supply two parameters. First is a name under which your cookies are stored
or are to be stored and the method will either login and set the cookies or retrieve the stored cookies. The second is a
Runnable (we would advice a lambda function) which will contain the actions in order to login. For example:

```java

import org.junit.jupiter.api.BeforeEach;
import steps.gui.klantportaal.MijnZakenSteps;
import utils.CacheLogin;


public class TestThis {

    MijnZakenSteps mijnZakenSteps = new MijnZakenSteps(page);

    @BeforeEach
    public void setupLogin() {
        CacheLogin.session("user1Klantportaal", () -> {
            mijnZakenSteps.Login_via_digid(digidUser);
        });
    }
}
```

So here we store cookies under "user1Klantportaal" and supply a Runnable that logs in to klantportaal and navigates to
"/zaken".

| parameter   | type       | function                                                                   |  
|-------------|------------|----------------------------------------------------------------------------|
| storageName | `String`   | A String in order to store cookies of application and to identify later on |
| runnable    | `Runnable` | A Runnable which contains the login sequence.                              |

### LoginCacheHelper

A second mechanism provided is the LoginCacheHelper which can be used in the runner of your application and you will
only need to define it once. LoginCacheHelper has a constructor which takes a consumer as a parameter with type Page.
Add it as a private static variable and initialize is in order to store the authenticated state of the application (so
this is not only the cookies!). For example:

```java
import org.junit.jupiter.api.BeforeEach;
import runner.LoginCacheHelper;
import runner.ZGWTestRunner;
import steps.gui.openforms.OpenFormsSteps;

public class OpenFormsTestRunner extends ZGWTestRunner {

    protected OpenFormsSteps formsSteps;

    private static LoginCacheHelper storeLogin = new LoginCacheHelper((page) -> {
        formsSteps = new OpenFormsSteps(page);
        formsSteps.login_via_digid();
    });

    public OpenFormsTestRunner() {
        super("https://openformulieren-zgw.test.denhaag.nl/aanvraag-formulier-ooievaarspas");
    }

    @BeforeEach
    public void setupSteps() {
        formsSteps = new OpenFormsSteps(page);
        formsSteps.login_via_digid();
    }
}
```

| parameter  | type             | function                                                                           |  
|------------|------------------|------------------------------------------------------------------------------------|
| loginSteps | `Consumer<Page>` | A lambda function that takes a parameter Page and performs the login steps defined |

## Info for Developers: deployment to maven central

General info: https://medium.com/@efthymiou.dimitrios1/how-to-publish-your-library-to-maven-central-3923139967e1
Info about publishing via OSSRH (we use this instead of maven central): https://central.sonatype.org/register/legacy/
Info about your settings.xml file which needs to be in your .m2 directory: https://central.sonatype.org/publish/publish-guide/


Deploy naar Maven Central
mvn clean deploy -DskipTests

Check the following:

- the pom.xml has the correct version number for the library (1 higher than the latest one as this will become the new
  one)
- keep your gpg password at hand because you need it during the deploy
