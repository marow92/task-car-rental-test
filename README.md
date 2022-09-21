## Car rental tests

## Setup
To run the project you need:
- Java 17,
- Maven,
- Selenium,
- Chrome Browser (best in version 105, otherwise you need to download chromedriver for your Chrome version)
- For Chrome browser in version other than 105, you need to download suitable chromedriver: https://chromedriver.chromium.org/downloads

## To run locally

The fastest way to run this project is with Intellij Idea - just import it as a Maven project.
To run tests just go to the RentalTest class and run them with the usage of IntelliJ.

Tests are configured to be run with chromedriver (Google Chrome for Selenium) and the path to chromedriver was
set up in the BaseTest class. However, there might be some problems if you use operating systems other than MacOS with M1.
In that case please download suitable chromedriver for your operating system and swap the path to the downloaded one.

For those who wants run tests in different browser, alternatively geckodriver for Mozilla [is available in the project](https://github.com/marow92/task-car-rental-test/tree/master/src/test/resources).


## Tests
Simple tests are located in[RentalTest.java.](https://github.com/marow92/task-car-rental-test/blob/master/src/test/java/com/wachala/rentaltests/RentalTest.java)
