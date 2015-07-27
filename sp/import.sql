/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP 1000 [LogID]
      ,[LogEvent]
      ,[LogClass]
      ,[LogDate]
      ,[AccountID]
      ,[BookingIDInserted]
      ,[BookingIDEdited]
      ,[BookingIDDeleted]
      ,[AccountIDDeleted]
      ,[AccountIDCreated]
  FROM [BookingSystem].[dbo].[logdata]




set IDENTITY_INSERT tblLogs on
set IDENTITY_INSERT logdata on

INSERT INTO tblLogs
SELECT LogEvent, LogClass, CONVERT(Date,LogDate,121), AccountID, BookingIDInserted, BookingIDEdited, BookingIDDeleted, AccountIDDeleted, AccountIDCreated FROM logdata

INSERT INTO tblBookings
SELECT BookingDay, CONVERT(Date,BookingDate,121), CONVERT(time,BookingStartTime,114), CONVERT(time,BookingCollectionTime,114),BookingLocation, BookingHolder, BookingEquipment,'0' FROM bookingdata

INSERT INTO tblAccount
Select AccountUserLevel, AccountUsername, AccountSaltedPassword, AccountSalt from accountdata

INSERT INTO tblEquipment
Select EquipmentName, EquipmentDescription, CONVERT(int, 
CASE 
WHEN EquipmentUsageStatistics like '%[^0-9]%' THEN NULL
ELSE EquipmentUsageStatistics
END) from equipmentdata