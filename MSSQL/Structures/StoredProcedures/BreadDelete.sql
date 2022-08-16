USE [Food]
GO
IF EXISTS (select * from sys.procedures WHERE name = 'BreadDelete')
BEGIN
	DROP PROCEDURE [dbo].[BreadDelete]
END
GO
CREATE PROCEDURE [dbo].[BreadDelete]
@id varchar(36)
AS
BEGIN
	SET XACT_ABORT ON

	BEGIN TRANSACTION

	BEGIN TRY
		DELETE FROM [dbo].[Bread]
		WHERE id = @id

		IF(@@ROWCOUNT > 1)
		BEGIN
			ROLLBACK TRANSACTION;
			THROW 50001, 'FATAL DELETE. Where clause is busted. It is trying to delete more than 1 record.', 1;
		END
		ELSE
		BEGIN
			COMMIT TRANSACTION
		END
	END TRY
	BEGIN CATCH
		ROLLBACK TRANSACTION
	END CATCH;

	SET XACT_ABORT OFF
END