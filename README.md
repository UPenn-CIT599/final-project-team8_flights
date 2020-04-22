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


#### Handling input errors

The program handles the following user input errors:
* No dates selected - sets default departure date to today's date, and return date to one month from today's date
* Incorrect spelling / invalid cities entered - program print's a message to the terminal and exists. User to retry with valid city names
* Max layovers not selected - defaults to value of 1
* Max budget not entered - defaults to 5000

#### Limitations

It is assumed that the user will:
* input a return date that is after the departure date
* enter a positive integer for the maximum budget

### Execution of the Program

Upon clicking the "SEARCH FOR FLIGHTS" GUI button with valid inputs, the rest of the program will execute, opening up a Google Chrome window as shown below, searching flight data with parameters specified by the user.  

Note the message at the top of the window informing the user that Chrome is being operated by test software. Please do not click anywhere in this browser while the data is being webscraped. Upon completion of webscraping, the Chrome browser window will close itself.

<p align="center">
  <img width="600" height=440" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/chrome_flightscraping.png?raw=true">
</p>


The state of the terminal will look similar to the screenshot below. Note that the message in red are expected. The program may take upto a few minutes to scrape the web for flight data. Upon completion of web scraping, the terminal will print the message "Scraping complete". 

<p align="center">
  <img width="600" height=440" src="https://github.com/UPenn-CIT599/final-project-team8_flights/blob/master/program_running.PNG?raw=true">
</p>
                                                                                                                                         
                                                                                                                                         


* if no flights found, user told
