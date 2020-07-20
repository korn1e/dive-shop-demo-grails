# Dive Shop Demo on Grails

## Description
Demo web-based application for "Dive Shop" management (create, update, delete, display). Integrated with Google Map API

## Before run this application
You need to have following ready:

#### Grails runtime
I use (old) Grails 3.3.3

#### MySQL 5.x
This application was built and tested agaisnt MySQL 5.x database, so you have to have it ready in your environment

#### Google Map API Key
This application will use Google Map Javascript API. You can use this [link](https://developers.google.com/maps/documentation/javascript/get-api-key "Get an API Key  |  Maps JavaScript API  |  Google Developers") to create one.
> Remember to secure your API.

## Configure your development environment
Review the configuration file on `grails-app/conf/application.yml`
- Database url, username, and password
- GoogleAPIKey
- Image upload path

## Running your app
Just execute `grails run-app` under the source code directory

## Access the app
Go to <http://localhost:8080/diveShop>

## Limitations
Following is the limitation list for this demo:
- Basic GUI (using Bootstrap)
- Basic form input method
- No form validation (e.g. email, phone, longitude, latitude, time)
- No fool proof navigation handling
- Not all fields are provided according to requirement document
- No search function