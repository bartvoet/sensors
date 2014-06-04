create table sensor_metrics (
	metric_timestamp time,
	humidity double,
	temperature double
);

create table SensorMeassurement (
	sensorId varchar(50),
	measurementTime datetime,
	functionality varchar(50),
	measurementValue double
);

