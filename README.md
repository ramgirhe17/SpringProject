# API Automation Testing for User Account Creation

This repository contains automated test cases for the user account creation API using **Postman** and **TestNG** with **Rest-Assured**. The purpose is to validate the API's functionality and ensure it handles different scenarios appropriately.

## Table of Contents

1. [Introduction](#introduction)
2. [API Details](#api-details)
3. [Test Scenarios](#test-scenarios)
4. [Prerequisites](#prerequisites)
5. [Setup Instructions](#setup-instructions)
6. [Running the Tests](#running-the-tests)
7. [Understanding the Test Cases](#understanding-the-test-cases)
8. [Troubleshooting](#troubleshooting)
9. [Contributing](#contributing)
10. [License](#license)

## Introduction

This project provides automated testing for the user account creation API to ensure its reliability and functionality. The API accepts user details and creates a new account while enforcing unique constraints on the `phoneNumber` and `emailId` fields.

## API Details

- **Endpoint:** `https://bfhldevapigw.healthrx.co.in/automation-campus/create/user`
- **Method:** POST
- **Headers:**
  - `roll-number` (required)
  - `Content-Type: application/json`
- **Body Parameters (JSON):**
  - `firstName` (required)
  - `lastName` (required)
  - `phoneNumber` (required, must be unique)
  - `emailId` (required, must be unique)

## Test Scenarios

The following scenarios are covered in the automated tests:

1. **Valid user creation**: Tests a successful user account creation.
2. **Duplicate phone number**: Tests creation with an existing phone number, expecting a 400 error.
3. **Duplicate email ID**: Tests creation with an existing email, expecting a 400 error.
4. **Missing roll-number header**: Tests the response when the `roll-number` header is missing, expecting a 401 error.
5. **Missing mandatory fields**: Tests the API with missing required fields, expecting a 400 error.
6. **Invalid phone number format**: Tests creation with an invalid phone number format, expecting a 400 error.
7. **Invalid email format**: Tests creation with an invalid email format, expecting a 400 error.
8. **Empty body**: Tests the response when the body is empty, expecting a 400 error.
9. **Extra fields**: Tests the API with additional, non-required fields.
10. **Fields with null values**: Tests the API with null values in the body, expecting a 400 error.

## Prerequisites

Ensure you have the following installed on your machine:

- [Java JDK (version 8 or higher)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Apache Maven](https://maven.apache.org/)
- [Postman](https://www.postman.com/downloads/)
- [TestNG](https://testng.org/doc/)
- [Rest-Assured](https://rest-assured.io/)

## Setup Instructions

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/api-automation.git
   cd api-automation
