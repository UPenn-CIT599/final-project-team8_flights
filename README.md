# final-project-team8_flights

## Pre-requisites setting up Java build environment:
**1. Git clone the entire repository including external libraries and chromedriver_download folders to your local project workspace**

**2. External downloads**
* All required libraries and driver are included in the repository. You should not need to download these libraries and drivers. The included libraries are JSoup, Selenium client/webdriver binding, and the included driver is chromedriver.
* Libraries and driver download links are listed below.

**3. Library setup**
* Eclipse's external library is setup with relative path in .classpath file. You should not need to setup separately.

**4. chrome driver setup**

**MAC User**
* Extract chromedriver_mac64.zip file from chromedriver_download folder.
* Move chromedriver file to project directory

**Windows User**
* Extract chromedriver_win32.zip file from chromedriver_download folder.
* Move chromedriver file to project directory

**Linux User**
* Extract chromedriver_linux64.zip file from chromedriver_download folder.
* Move chromedriver file to project directory

**5. You're all set!**

## External download links (reference only)
### JSoup
* JSoup lib - version 1.13.1
external download link: https://jsoup.org/packages/jsoup-1.13.1.jar
### Selenium 
* Selenium Client & WebDriver Language Bindings
external download link: download link: https://www.selenium.dev/downloads/
### Chrome driver
external download link: https://chromedriver.storage.googleapis.com/index.html?path=81.0.4044.69/

## Main Classes:
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

Prior to running the program, please ensure you have all required libraries and drivers outlined above in the project folder. 

The program is run from the main class. Upon running the main class, a graphical user interface window will open as shown in the figure below.

<p align="center">
  <img width="318" height="240" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/Capture0.PNG?raw=true">
</p>

The user then enters the following information:
* Departure and return dates from the drop-down fields
* Departure and destination cities in the text fields
* Maximum number of layovers from the dropdown menu. 
* Check the "direct flights only" checkbox if only direct flights are desired. The program will ignore the max layover field in this case.
* Maximum budget fare for total trip, input as a positive integer.


**1 heading 1**
***heading 2***
****heading 3****

### testing third subheading
Hello 123
