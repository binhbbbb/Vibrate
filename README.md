[![Published on Vaadin  Directory](https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg)](https://vaadin.com/directory/component/vibrate)
[![Stars on Vaadin Directory](https://img.shields.io/vaadin-directory/star/vibrate.svg)](https://vaadin.com/directory/component/vibrate)

Vibrate Vaadin UI Extension Add On
==================================

Vibrare offers server side access to Vibrate API

Demo application: http://siika.fi:8080/VibrateDemo/
Source code: https://github.com/alump/Vibrate
Vaadin Directory: https://vaadin.com/directory#addon/vibrate
License: Apache License 2.0

This project can be imported to Eclipse with m2e.

Simple Maven tutorials:


### How to compile add on jar package for your project
```
cd vibrate-addon
mvn package
```

add on can be found at: picker-addon/target/Vibrate-<version>.jar
zip package used at Vaadin directory can be found at:
picker-addon/target/Vibrate-<version>.zip

### How to install Vibrate to your Maven repository

To install addon to your local repository, run:
```
cd vibrate-addon
mvn install
```

### How to run test application

First compile and install addon (if not already installed)
```
cd vibrate-addon
mvn install
```

Then compile demo widgetset and start HTTP server
```
cd ../vibrate-demo
mvn vaadin:compile
mvn jetty:run
```

Demo application is running at http://localhost:8080/vibrate



### How to compile test application WAR 

First compile and install addon (if not already installed)
```
cd vibrate-addon
mvn install
```

Then construct demo package (this should automatically compile widgetset)
```
cd ../vibrate-demo
mvn package
```

War package can be now found at vibrate-demo/target/VibrateDemo.war
