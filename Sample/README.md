# Dependencies
For this particular sample, to connect to a real SQL instance you will need docker

## MSSQL
Execute the run commands in this article:
https://docs.microsoft.com/en-us/sql/linux/quickstart-install-connect-docker?view=sql-server-ver16&pivots=cs1-bash

Execute the scripts found in ../MSSQL

## PostgreSQL
Get the latest PostgreSQL image from Docker Hub

Execute the run commands in this article:
https://hub.docker.com/_/postgres

You may have to include a port mapping -p 5432:5432

- Create a user for api_user_access
- Execute the scripts found in ../POSTGRESQL
