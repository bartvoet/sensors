create table Sensors (
	sensorId varchar(50) primary key,
	agentName varchar(50)
);

create table SensorFunctionality (
	fk_sensorId varchar(50) FOREIGN KEY REFERENCES Sensors(sensorId),
	functionality varchar(50)
);

create table SensorMeassurement (
	sensorId varchar(50),
	measurementTime datetime,
	functionality varchar(50),
	measurementType varchar(50),
	measurementValue numeric(12,4)
);

create table SensorConfiguration (
	fk_sensorId varchar(50) FOREIGN KEY REFERENCES Sensors(sensorId),
	configurationCounter numeric(12),
	registrationTime datetime,
	confirmed numeric(1),
	primary key(fk_sensorId,configurationCounter)
);

create table SensorConfigurationValue (
	fk_configuritionId varchar(50),
	measurementTime datetime,
	configurationLabel varchar(50),
	configurationType varchar(50),
	configurationValue numeric(12,4)
);

