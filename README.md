=====================
Java Academy Technical Task
=====================

===========================================================
Note: this project is written with openjdk 16 2021-03-16
Java version in pom.xml is set to 16
===========================================================

Table of Contents

1. Project idea
2. Assumptions
3. Used technologies
4. Project structure

---

1. Project idea

This project is technical task for Java Academy. Application has simple GUI to make it easy to use. User can enter keyword and data file to analyze. App accepts files with multiple line, and displays result one by one, after clicking "Next result" button.

2. Assumptions

There are no special assumptions other than the given. In the first screen user has to enter keyword (like "logic"), and choose a file with data that he wants to analyze. It doesn't matter whether data contain special characters or not, or whether there is an empty line between sentences.

3. Used technologies

Project is written in Java and uses Maven for required dependencies and packaging into JAR file. GUI was made in JavaFX with FXML and CSS.

4.Project structure

Main JAR file is located at target directory and is named "JavaAcademy_GUI-1.0-SNAPSHOT-shaded.jar"
In the target directory there's also Launchet.bat, which is made to easily open the app.

Project contains Main class and two main packages.

Package fx has everything connected with JavaFX. App class is main class of the application. UserData represents data chosen by the user in start screen. UserDataHolder is Singleton class that is responsible to store user data, in order to access them in the main screen.

Package task has all classed that are responsible for solving given problem.

Main: class that calls run() method from App to open GUI

fx.App: Class that is main class of the application. It is responsible for launching GUI. Also contains static method getWindow(), so that other classes may easily access stage.

fx.UserData: Represents data that user enters in the start screen.

fx.UserDataHolder: Singleton class used to store user data. With this approach data can be used from main screen.

fx.controllers.StartScreenController: Controller for start screen. It contains logic of getting data from user.

fx.controllers.MainScreenController: Controller for main screen with results. It implements Initializable interface, so the results are calculated right after entering the screen.

task.Solution: This class contains whole logic of project. It is responsible for dividing words into groups, gathering results and sending them back to main screen.

task.WordGroup: Class that represents group of words.

task.Record: Represents one record, that is used to create table view record. This class implements Comparable interface, so that the records can be sorted from the lowest frequency to the highest.

task.TableViewRecord: Represents record stored in table view.
