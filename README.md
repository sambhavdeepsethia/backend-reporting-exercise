# Backend Reporting Exercise
The following is Avero's Reporting API business soultion for [Avero's Backend Coding Exercise.](https://github.com/AveroLLC/reporting-api-exercise)
* [Prerequisites](#prerequistes)
* [Setup](#setup)
* [Running the Application](#running-the-application)
* [Report Types](#report-types)
* [Reporting API Documentation](#reporting-api-documentation)

## Prerequisites
Before performing the setup, please ensure the following items installed in your environment and Java and Maven are set in environmet variables.
* Java JDK 1.8 or higher
* Apache Maven 3.5 or higher
* git 2.0 or higher

## Setup
Download the repo from GIT using the following command in your terminal:

```git clone git@github.com:sambhavdeepsethia/backend-reporting-exercise.git```

Change directory to `backend-reporting-exercise/`, this is going to be your **base directory** for the project and it contains the entire project with its pom.xml file. Finally, build the project by typing the command:

`mvn clean install`

Note: You have to be in the `backend-reporting-exercise/` directory for the build to work. And, if the build is successful it should create a `target/` directory which has the application's `.jar` file.

## Running the application
After the build is successful, type the following command from the base directory `backend-reporting-exercise/`

```java -jar target/backend-reporting-exercise-1.0-SNAPSHOT.jar```

This will start the application server and it may take a few minutes until the application is up and running. Alternatively you can copy the jar to a folder of your choice, which has read and write permissions and type the command `java -jar <jarname>` to run the application.

### Data Fetch
Before the application starts, a GET request is made to all the [POS APIs](https://github.com/AveroLLC/reporting-api-exercise#pos-entities) and the corresponding data is stored as .json file in `data/` folder under the base directory, the data will remain as is while the application is running and will be rewritten when the application is started.

### Logs
The application runs under `INFO` logging level and logs are stored under `logs/` directory under the base directory.


## Report Types
These are the technical definitions of the reports implemented by the reporting api and also lists the assumption made during their computation.

### Labor Cost Percentage
 - Abbreviated as **LCP**
 - Labor cost percentage is the percentage of the revenue that pays for labor. 
 - LCP is calculated as percentage of **LCP= TotalLaborCost/Sales**
 - Assumptions made while calculating LCP for a give time frame are:
	- If TotalLaborCost and Sales is zero then LCP is returned as 0.
	- If only Sales is zero then LCP is returned as 100, indicating a 100% cost during the time period.

### Food Cost Percentage 
 - Abbreviated as **FCP**
 - Food cost percentage is the difference between what it costs to produce an item and its price on the menu. 
 - FCP is calculated as percentage of : **FCP = TotalItemCost / TotalSellingPrice**
 - Assumptions made while calculating FCP for a give time frame are:
	- If TotalItemCost and TotalSellingPrice is zero then FCP is returned as 0.
	- If only TotalSellingPrice is zero then FCP is returned as 100, indicating a 100% cost during the time period.

### Employee Gross Sales
 - Abbreviated as **EGS**
 - Employee gross sales is the sum of the of the price charged for each item the employee sold. Voided Items excluded.
 - Calculate employee gross sales: **EGS = Sum(Selling Prices)**
 
 **Note:** The [System Design](https://github.com/sambhavdeepsethia/backend-reporting-exercise/blob/master/reporting-SystemDesign.pdf) document shows the interacations between the various components of the application
 
# Reporting API Documentation

## Connecting
All URIs in this document have the following base:
`http://localhost:8080`

## Content-Type
Any data in request or response bodies should be JSON.

## Authentication
Since, the application is running on a local server there is no authentication required.

### GET /reporting
Supports the following query parameters, all of which are required.
- **business_id** (uuid) - The id of the business to run this report for.
- **report** (LCP | FCP | EGS) - The abbreviated name of the report to run.
- **timeInterval** (hour | day | week | month) - The time interval to aggregate the data.
- **start** (date) - The start date used to constrain the results. ISO-8601 date
- **end** (date) - The end date used to constrain the results. ISO-8601 date.

### Report Type Abbreviations
- [Labor Cost Percentage = **LCP**](#labor-cost-percentage)
- [Food Cost Percentage = **FCP**](#food-cost-percentage )
- [Employee Gross Sales = **EGS**](#employee-gross-sales ) 


### LCP:
example uri: ```/reporting?business_id=b2aeb27b-c85c-4ad8-83d4-d9511063d418&report=LCP&timeInterval=hour&start=2018-06-02T00:00:00.000Z&end=2018-06-02T04:00:00.000Z```

example response body of LCP by hour:
```json
{
    "report": "LCP",
    "timeInterval": "hour",
    "data": [
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-02T01:00:00.000Z"
            },
            "value": 211.76470588235296
        },
        {
            "timeFrame": {
                "start": "2018-06-02T01:00:00.000Z",
                "end": "2018-06-02T02:00:00.000Z"
            },
            "value": 5.678233438485805
        },
        {
            "timeFrame": {
                "start": "2018-06-02T02:00:00.000Z",
                "end": "2018-06-02T03:00:00.000Z"
            },
            "value": 63.1578947368421
        },
        {
            "timeFrame": {
                "start": "2018-06-02T03:00:00.000Z",
                "end": "2018-06-02T04:00:00.000Z"
            },
            "value": 13.186813186813188
        }
    ]
}
```
example uri: ```/reporting?business_id=b2aeb27b-c85c-4ad8-83d4-d9511063d418&report=LCP&timeInterval=day&start=2018-06-02T00:00:00.000Z&end=2018-06-03T00:00:00.000Z```

example response body of LCP by day:    
```json
{
    "report": "LCP",
    "timeInterval": "day",
    "data": [
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 8.124118476727785
        }
    ]
}
```


### FCP:
example uri: ```/reporting?business_id=b2aeb27b-c85c-4ad8-83d4-d9511063d418&report=FCP&timeInterval=week&start=2018-06-01T00:00:00.000Z&end=2018-06-15T00:00:00.000Z```

example response body of FCP by week:
```json
{
    "report": "FCP",
    "timeInterval": "week",
    "data": [
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-06-08T00:00:00.000Z"
            },
            "value": 59.632844855049385
        },
        {
            "timeFrame": {
                "start": "2018-06-08T00:00:00.000Z",
                "end": "2018-06-15T00:00:00.000Z"
            },
            "value": 60.06820707360999
        }
    ]
}
```

### EGS:

example uri: ```/reporting?business_id=b2aeb27b-c85c-4ad8-83d4-d9511063d418&report=EGS&timeInterval=day&start=2018-06-02T00:00:00.000Z&end=2018-06-03T00:00:00.000Z```

example response body of EGS by day:
```json
{
    "report": "EGS",
    "timeInterval": "day",
    "employeeData": [
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 157,
            "employee": "Andy Bolden"
        },
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 271,
            "employee": "Casey Dondel"
        },
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 414,
            "employee": "Jamey Burr"
        },
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 573,
            "employee": "Jessica Burrey"
        },
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 399,
            "employee": "Jon Fends"
        },
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 564,
            "employee": "Kylo Ren"
        },
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 312,
            "employee": "Sasha Yii"
        },
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 502,
            "employee": "Shawn Saldana"
        },
        {
            "timeFrame": {
                "start": "2018-06-02T00:00:00.000Z",
                "end": "2018-06-03T00:00:00.000Z"
            },
            "value": 353,
            "employee": "Susan Smarts"
        }
    ]
}

```

example uri: ```/reporting?business_id=b2aeb27b-c85c-4ad8-83d4-d9511063d418&report=EGS&timeInterval=month&start=2018-06-01T00:00:00.000Z&end=2018-07-00T00:00:00.000Z```

example response body of EGS by month:
```json
{
    "report": "EGS",
    "timeInterval": "month",
    "employeeData": [
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-07-01T00:00:00.000Z"
            },
            "value": 8181,
            "employee": "Andy Bolden"
        },
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-07-01T00:00:00.000Z"
            },
            "value": 8440,
            "employee": "Casey Dondel"
        },
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-07-01T00:00:00.000Z"
            },
            "value": 13468,
            "employee": "Jamey Burr"
        },
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-07-01T00:00:00.000Z"
            },
            "value": 16280,
            "employee": "Jessica Burrey"
        },
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-07-01T00:00:00.000Z"
            },
            "value": 13729,
            "employee": "Jon Fends"
        },
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-07-01T00:00:00.000Z"
            },
            "value": 17578,
            "employee": "Kylo Ren"
        },
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-07-01T00:00:00.000Z"
            },
            "value": 8180,
            "employee": "Sasha Yii"
        },
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-07-01T00:00:00.000Z"
            },
            "value": 13585,
            "employee": "Shawn Saldana"
        },
        {
            "timeFrame": {
                "start": "2018-06-01T00:00:00.000Z",
                "end": "2018-07-01T00:00:00.000Z"
            },
            "value": 14529,
            "employee": "Susan Smarts"
        }
    ]
}

```
