## Features

This is a sample application to showcase the use of Spring Cloud Function on top of Azure Functions.
The application consumes an external API (https://www.gov.uk/bank-holidays.json") and generate a .csv England and Wales bank holidays according to year param.

## Getting Started

### Prerequisites

This project uses the Maven Wrapper, so all you need is Java installed.

### Installation

- Build the project: `mvn clean package`

### Quickstart

Once the application is built, you can run it locally using the Azure Function Maven plug-in:

`mvn azure-functions:run`

And you can test it using a cURL command:

`curl http://localhost:7071/api/englandwalesbankholidays?year=2019`

## Deploying to Azure Functions

Deploy the application on Azure Functions with the Azure Function Maven plug-in:

`mvn azure-functions:deploy`

