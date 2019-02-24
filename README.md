# SEG Major
Team Pacane

# Project Structure

### Default project structure to follow:
* [src](./src)
    * [main](./src/main)
        * [java](./src/main/java)
            * [seg/major](./src/main/java/seg/major)
                * [controller](./src/main/java/seg/major/controller) <--- Code for controllers
                * [model](./src/main/java/seg/major/model) <--- Backend logic goes here
        * [resources](./src/main/resources)
            * [views](./src/main/resources/views) <--- views go here
    * [test](./src/test)
        * [java/seg/major](./src/test/java/seg/major) <--- tests go in here
        * [resources](./src/test/resources) <--- test resources go in here


## To build the project, run the following commands from the main folder:

For Linux/MacOS:

    gradlew build

or

    ./gradlew build

For Windows:

    gradlew.bat build

## To run the project, run the following commands from the main folder:

For Linux/MacOS

    gradlew run

or

    ./gradlew run

For Windows:

    gradlew.bat run

# Project 1: Blood test diary
Client: Dr Marianne Samyn, King's College Hospital

Background: Patients of the paediatric liver service at King's College Hospital need to undergo regular blood tests to enable the service to monitor their condition. Currently, the service employs an Excel spreadsheet to track when blood tests are due, chase patients who are overdue to get a blood test and chase local laboratories who are overdue to produce test results. This solution does not scale well with the number of patients (about 850) in the spreadsheet. Data entry, setting the next due dates of blood tests/results, identification of overdue tests/results, communication with patients and their carers, laboratories are all done manually by means of the spreadsheet. It is time-consuming and prone to errors.

Objective: This project aims to produce a desktop or web application (to be negotiated with the client) to replace the spreadsheet-based blood test diary. The new system should facilitate data entry, support automatic or semi-automatic setting of blood test due dates, automate sending reminders by text or email, facilitate chasing results, support identification of complex patients, simplify results sharing with patients, their carers and GPs.

Challenges: This project involves the development of an administrative system with a substantial number of stakeholders (850 patients, their carers, their GPs, a substantial number of laboratories, King's College Hospital staff). While all Software Engineering Group Project students will be familiar with the technologies involved with developing such a system, requirements engineering will be challenging in this project. You will be working in a complex organisational setting with experts who may not fully appreciate what you have or have not understood. If you fail to build the system to the correct requirements, it is not likely to be especially useful to the client. Ideally, you should build multiple iterations of the system, so that you maximise opportunities to seek feedback from the client or stakeholders.

Type of project: Development of an administrative information systems (desktop or web-based) with a database back-end.

Intellectual property arrangements: This work is to be delivered under a free software license. 
