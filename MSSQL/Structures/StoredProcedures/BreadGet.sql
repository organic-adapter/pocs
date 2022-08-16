USE [Food]
GO
IF EXISTS (select * from sys.procedures WHERE name = 'BreadGet')
BEGIN
	DROP PROCEDURE [dbo].[BreadGet]
END
GO
CREATE PROCEDURE [dbo].[BreadGet]
@id varchar(36)
, @type varchar(255)
AS
BEGIN
	SELECT
	id
	, [type]
	, displayName
	, quantity
	FROM [dbo].[Bread] as B
	WHERE COALESCE(@id, B.id) = B.id
	AND COALESCE(@type, B.[type]) = B.[type]
END
GO
--DO NOT ADD THIS PRIOR TO THE END or GO
GRANT EXECUTE ON BreadGet TO api_user_access