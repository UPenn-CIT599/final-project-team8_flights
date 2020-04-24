# final-project-team8_flights

## Pre-requisites: Setting up Java build environment:
**1. Git clone the entire repository including external libraries and chromedriver_download folders to your local project workspace**

**2. External downloads**
* All required libraries and driver are included in the repository. You should not need to download these libraries and drivers. The included libraries are JSoup, Selenium client/webdriver binding, and the included driver is chromedriver.
* Libraries and driver download links are listed below.

**3. Library setup**
* Eclipse's external library is setup with relative path in .classpath file. You should not need to setup separately.

**4. Chrome Driver Setup**

**MAC User**
* Extract chromedriver_mac64.zip file from chromedriver_download folder.
* Move chromedriver file to project directory

**Windows User**
* Extract chromedriver_win32.zip file from chromedriver_download folder.
* Move chromedriver file to project directory

**Linux User**
* Extract chromedriver_linux64.zip file from chromedriver_download folder.
* Move chromedriver file to project directory

**5. Selecting the correct text-file encoding setting on Eclipse**

* To ensure compatability of the text files across mac/windows programs, set the textfile encoding on eclipse to UTF-8. This can be done as shown in the figure below by selecting Window->Preferences->Workspace (on left of panel) -> Text File Encoding -> Other: UTF-8.

<p align="center">
  <img width="625" height="500" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/eclipse_text_setting.JPG?raw=true">
</p>


## External download links (reference only)
### JSoup
* JSoup lib - version 1.13.1
external download link: https://jsoup.org/packages/jsoup-1.13.1.jar
### Selenium 
* Selenium Client & WebDriver Language Bindings
external download link: download link: https://www.selenium.dev/downloads/
### Chrome driver
external download link: https://chromedriver.storage.googleapis.com/index.html?path=81.0.4044.69/

## Classes:
* ActiveFlightWebScraping Class
* AirportCode Class
* DataReader Class
* Flights Class
* FlightWebScraping Class
* FlightWebScraping Runner Class
* GraphicalUserInterface Class
* Itinerary Class
* Main Class
* Recommender Class
* ReportGenerator Class

### Test Classes:
**CHRIS TO ADD**

# User Guide

## Introduction

Team 8's final project is a flight recommender system. The program takes in a set of flight parameters specified by the user, scrapes the web to search for flights satisfying the user's requirements, and generates a curated report of the top flights.

Note that it is intended for this program to be treated as a minimum viable product (MVP) with several limitations outlined throughout this user guide. 

## Running the program

### User Input

Prior to running the program, please ensure you have all required libraries and drivers outlined above in the project folder. 

The program is run from the main class. Upon running the main class, a graphical user interface (GUI) window will open as shown in the figure below.

<p align="center">
  <img width="318" height="240" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/Capture0.PNG?raw=true">
</p>

The user then enters the following information:
* Departure and return dates from the drop-down fields
* Departure and destination cities in the text fields
* Maximum number of layovers from the dropdown menu. 
* Check the "direct flights only" checkbox if only direct flights are desired. The program will ignore the max layover field in this case.
* Maximum budget fare for total trip, input as a positive integer.

The figure below shows an example of the final state of the GUI upon entering information for all required fields. At this stage, the user clicks the "SEARCH FOR FLIGHTS" button, upon which the rest of the program executes.

<p align="center">
  <img width="318" height="240" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/gui_final_state.PNG?raw=true">
</p>

#### Viewing the GUI on a MAC

If running this program on a MAC, the user may initially see the GUI as a blank window as shown below. 

<p align="center">
  <img width="318" height="240" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/mac_gui_screencapture1.png?raw=true">
</p>

The user should slightly expand the size of the window in order to view the components of the GUI as intended. 

<p align="center">
  <img width="318" height="240" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/mac_gui_screencapture2.png?raw=true">
</p>


#### Handling input errors

The program handles the following user input errors:
* No dates selected - sets default departure date to today's date, and return date to one month from today's date
* Incorrect spelling / invalid cities entered - program print's a message to the terminal and exists. User to retry with valid city names
* At present, the program reads in city names and maps them to an airport code based on a predefined list of cities and airport codes. Occasionally, a city will have several airport codes, which the program automatically selects somewhat arbitrarily. If the user would prefer a particular airport code to be used for a city, they can enter cityname.airport code in the GUI city field. For example, entering Stockholm.arn in the departure city field would ensure that Stockholm Arlanda Airport is used as the airport for the departure city.
* Max layovers not selected - defaults to value of 1
* Max budget not entered - defaults to 5000

#### Limitations

It is assumed that the user will:
* Input a return date that is after the departure date
* Enter a positive integer for the maximum budget

### Execution of the Program

Upon clicking the "SEARCH FOR FLIGHTS" GUI button with valid inputs, the rest of the program will execute, opening up a Google Chrome window as shown below, searching flight data with parameters specified by the user.  

Note the message at the top of the window informing the user that Chrome is being operated by test software. Please do not click anywhere in this browser while the data is being webscraped. Upon completion of webscraping, the Chrome browser window will close itself.

<p align="center">
  <img width="600" height=440" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/chrome_flightscraping.png?raw=true">
</p>


The state of the terminal will look similar to the screenshot below. Note that the messages in red are expected. The program may take upto a few minutes to scrape the web for flight data. Upon completion of web scraping, the terminal will print the message "Scraping complete". 

<p align="center">
  <img width="600" height=440" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/program_running.PNG?raw=true">
</p>
                                                                                                                                         
### Generation of Report

Once the webscraping is complete, the program will generate a report of the top five flights (or less if there aren't a sufficient number of flights meeting the user's criteria) in a report.txt file. The user will be prompted with a message once the report has been generated, as shown below.

<p align="center">
  <img width="600" height=150" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/program_done.PNG?raw=true">
</p>

Finally, the report.txt file can be found in the project folder. This file contains summaries of the top flights, details, and web links to book, as shown below. If no valid flights are found, a message is displayed to the user asking to revise some of the input parameters and to retry. 

<p align="center">
  <img width="1200" height=550" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/final_report.PNG?raw=true">
</p>

At this stage the program is complete, and the user may then proceed to select their desired flight and book through the booking link provided.



