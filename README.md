# AI for Developers training

## Introduction
The training aims to familiarize users with AI tools for software development. 
The primary tool is GitHub Copilot, complemented by ChatGPT. The training is 
designed to be mostly practical, with participants working in pairs to solve 
individual tasks. Initially, tasks are tackled without AI tools and then 
repeated with their assistance. In addition to the principles of working
with AI, another main focus of the training is on creating testable software 
and adhering to best practices for writing high-quality software (such as the 
SOLID principles, etc.). 

The training content is planned for 8 hours but may
be shorter depending on the expertise of the participants.

## Tech stack
The training is conducted in the Java programming language using the Spring 
Boot and Hibernate ORM frameworks. The application uses Oracle Database for 
data persistence, and access to the application is provided through a public 
API (both application and REST). The public API documentation is created using 
the Swagger library.

### Database Setup
The application requires Oracle Database. For development purposes, you can use:
- Oracle Database XE (Express Edition) - free for development
- Oracle Database running in Docker container
- Oracle Cloud Always Free Tier

**Database Configuration:**
- Default connection: `jdbc:oracle:thin:@localhost:1521:XE`
- Required database user: `bank_user` with password `bank_password`
- The user needs privileges to create sequences and tables

**Setup Instructions:**
1. Install Oracle Database XE or run Oracle in Docker:
   ```bash
   docker run -d -p 1521:1521 -e ORACLE_PASSWORD=admin gvenzl/oracle-xe
   ```
2. Create the database user:
   ```sql
   CREATE USER bank_user IDENTIFIED BY bank_password;
   GRANT CONNECT, RESOURCE TO bank_user;
   GRANT CREATE SEQUENCE TO bank_user;
   ```
3. Run the application - it will automatically execute schema.sql and data.sql

## Prerequisites
Participants of the training should have the following set up:
 - A development environment that supports working with GitHub Copilot 
        (preferably IntelliJ IDEA)
 - An established GitHub account (either a personal or a temporary one for 
      the purpose of the training)
 - The GitHub Copilot plugin for the development environment, including an 
      active license (an individual trial version is sufficient)