#include <SoftwareSerial.h>
#include <DHT.h>
#include "DHT.h"
#define DHTPIN 4   
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);

SoftwareSerial espSerial(2, 3); // RX, TX
//Crossover the RX/TX so Arduinos RX (2) goes to ESP TX.

const int redPin = 7; //  ~D2
const int greenPin = 6; // ~D6
const int bluePin = 5; // ~D5

void setup() {
  String hello[] = {"AT", "AT+CWMODE=1", "AT+CWJAP=\"AlexS9\",\"darkfruits1\"", "AT+CIFSR"};
  Serial.begin(9600);
  soil();
  rhtemp();
  pinMode(redPin, OUTPUT);
  pinMode(greenPin, OUTPUT);
  pinMode(bluePin, OUTPUT);
  // color while waiting to connect
  analogWrite(redPin, 280);
  analogWrite(greenPin, 300);
  analogWrite(bluePin, 300);
  
  set_esp8266_baud_rate(9600);
  espSerial.begin(9600);
  dht.begin();
  for (int n =0; n != 5; n++){ 
    espSerial.println(hello[n]);
    delay(5000);
  }
  delay(1000);
  up();
}
void loop() {
  if (espSerial.available()) {
    Serial.write(espSerial.read());
  }
  if (Serial.available()) {
    espSerial.write(Serial.read());
  }
}
String soil(){
  Serial.println("Soil Humidity Value:");
  int v = analogRead(A0);
  Serial.println(v, DEC); 
  delay(100);
  if(0>= 125){
    analogWrite(redPin, 3000);
    analogWrite(greenPin, 0);
    analogWrite(bluePin, 0);
  }
  if (v>=250){
    analogWrite(redPin, 0);
    analogWrite(greenPin, 0);
    analogWrite(bluePin, 1000);
  }
  return String(v);
}

String rhtemp(){
  // Wait a few seconds between measurements.
  delay(2000);
  float h = dht.readHumidity();
  float t = dht.readTemperature();
  float f = dht.readTemperature(true);
  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println("Failed to read from DHT sensor!");
    return "";
  }
  float hif = dht.computeHeatIndex(f, h);
  float hic = dht.computeHeatIndex(t, h, false);
  Serial.println("Room Humidity: ");
  Serial.println(String (h) + " %\t");
  Serial.println("Temperature: ");
  Serial.println(String(t) + " *C ");
  Serial.println("Heat index: ");
  Serial.println(String(hic)+ " *C ");
  return "&room_hum=" + String(h) + "&temp=" + String(t);
}

void set_esp8266_baud_rate(long int baud_rate){
  long int baud_rate_array[] = {1200,2400,4800,9600,19200,38400,57600,74880,115200,230400};
  int i, j, pause=10;
  String response;

  Serial.println("Setting ESP8266 baud rate...");
  for (j=0; j<5; j++){
    for (int i=0; i<10; i++){
      espSerial.begin(baud_rate_array[i]);
      espSerial.print("AT\r\n");
      delay(pause);
      if (espSerial.available()) {
        response=espSerial.readString();
        response.trim();
        if (response.endsWith("OK")) {
          espSerial.print("AT+UART_CUR=");
          espSerial.print(baud_rate);
          espSerial.println(",8,1,0,0");
          delay(pause);
          if (espSerial.available()) {
            response=espSerial.readString();
          }
          espSerial.begin(9600);
          delay(pause);
          espSerial.println("AT");
          delay(pause);
          if (espSerial.available()) {
            response=espSerial.readString();
            response.trim();
            if (response.endsWith("OK"))
              break;
            else {
              Serial.println("Trying again...");
            }
          }
          else {
            Serial.println("Trying again...");
          }
        }
      }
    }
    if (response.endsWith("OK"))
      break;
  }
  espSerial.begin(9600);
  delay(pause);
  espSerial.println("AT");
  delay(pause);
  if (espSerial.available()) {
    response=espSerial.readString();
    response.trim();
    if (response.endsWith("OK")) {
      Serial.print("\r\nBaud rate is now 9600");
    }
    else {
      Serial.println("Sorry - could not set baud rate");
      Serial.println("Try powering off and on again");
      Serial.println("Don't try to use 115200");
    }
  }
}

void up(){
  delay(1000);
  String server = "54.213.142.174"; 
  String uri = "/add.php";
  String data = "humidity=" + soil()+ rhtemp();
  espSerial.println("AT+CIPSTART=\"TCP\",\"" + server + "\",80");//start a TCP connection.
  delay(1000);
  String postRequest =
      "POST " + uri + " HTTP/1.0\r\n" +
      "Host: " + server + "\r\n" +
        "Accept: *" + "/" + "*\r\n" +
        "Content-Length: " + data.length() + "\r\n" +
        "Content-Type: application/x-www-form-urlencoded\r\n" +
        "\r\n" + data;
  
  String sendCmd = "AT+CIPSEND=";
  espSerial.print(sendCmd);
  espSerial.println(postRequest.length() );
  delay(500);
  espSerial.print(postRequest);
  Serial.println("Packet sent");
  while (espSerial.available()) {
    String tmpResp = espSerial.readString();
    Serial.println(tmpResp);
  }
}
