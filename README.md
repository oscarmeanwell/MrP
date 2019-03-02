# MrPlant

This presents the app of an Arduino project. An arduino gathered temperature, humidity, and soil humidity data from custom sensors - it was then uploaded to a server via a PHP script, and then pulled down to this app.

The app visually allowed users to monitor their plants health, and sent them backround notifications when it needed watering or a change in climate.

The [file](https://github.com/oscarmeanwell/MrP/blob/master/arduinocode.ino) is the Arduino code for the project. It manually configures an ESP8266 wifi chip, and then sends the data to the server.
