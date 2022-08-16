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
	WHERE COALESCE(B.id, @id) = @id
	AND COALESCE(B.[type], @type) = @type
END