# Dive Shop Demo on Grails

## Before run this application
You need to have following ready

#### Grails runtime
I use (old) Grails 3.3.3

#### MySQL 5.x
This application was built and tested agaisnt MySQL 5.x database, so you have to have it ready in your environment

#### Google Map API Key
This application will use Google Map Javascript API. To get one, you can use this [link](https://developers.google.com/maps/documentation/javascript/get-api-key "Get an API Key  |  Maps JavaScript API  |  Google Developers"). Remember to secure your API.

## Configure your development environment
Review the configuration file on `grails-app/conf/application.yml`
- Database url, username, and password
- GoogleAPIKey
- Image upload path

## Running your app
Just execute `grails run-app` under the source code directory

