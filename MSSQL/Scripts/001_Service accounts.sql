IF NOT EXISTS(select * from sys.sysusers WHERE name = 'api_user_access')
BEGIN
	CREATE LOGIN [api_user_access] WITH PASSWORD = '*****************************'
END
GO

USE [Food]
IF NOT EXISTS(select * from sys.database_principals WHERE TYPE NOT IN ('A','G','R','X') and name = 'api_user_access')
BEGIN
	CREATE USER [api_user_access] FOR LOGIN [api_user_access]
END
GO