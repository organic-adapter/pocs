USE [Food]
GO

IF NOT EXISTS(SELECT * FROM [dbo].[Bread] WHERE [type] = 'DemoBread')
BEGIN
	INSERT INTO [dbo].[Bread]
	(
		id
		, [type]
		, displayName
		, quantity
	)
	VALUES
	(
		'DemoUnique'
		, 'DemoBread'
		, 'This is Demo Bread'
		, 5
	)
END

IF NOT EXISTS(SELECT * FROM [dbo].[Bread] WHERE [type] = 'MilletBread')
BEGIN
	INSERT INTO [dbo].[Bread]
	(
		id
		, [type]
		, displayName
		, quantity
	)
	VALUES
	(
		'ABC123'
		, 'MilletBread'
		, 'This is Millet Bread'
		, 27
	)
END