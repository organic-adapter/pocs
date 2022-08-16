USE [Food]
GO
IF EXISTS (select * from sys.procedures WHERE name = 'BreadSave')
BEGIN
	DROP PROCEDURE [dbo].[BreadSave]
END
GO
CREATE PROCEDURE [dbo].[BreadSave]
@id varchar(36),
@type varchar(255),
@display varchar(255),
@quantity float
AS
BEGIN
	MERGE dbo.Bread as tgt
	USING (
			SELECT 
			@id
			, @type
			, @display
			, @quantity
		  )
		  AS src
		  (
			  id
			  , [type]
			  , displayName
			  , quantity
		  )
	ON tgt.id = src.id AND tgt.[type] = src.[type] --Explicit declaration that type is unique
	WHEN MATCHED THEN
		UPDATE SET displayName = src.displayName
				   , quantity = src.quantity
	WHEN NOT MATCHED THEN
		INSERT (
					id
					, [type]
					, displayName
					, quantity
			   )
		VALUES	(
					src.id
					, src.[type]
					, src.displayName
					, src.quantity
				)
;
END