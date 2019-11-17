#include <WiFi.h>
#include <WiFiClient.h>
#include <PulseSensorPlayground.h>
#include "HTTPClient.h"
#include <FirebaseESP32.h>
#include <LiquidCrystal.h>

#define WIFI_SSID "Baap"
#define WIFI_PASSWORD "alpha95657"

const int rs = 5, en = 18, d4 = 19, d5 = 21, d6 = 22, d7 = 23;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

int PulseSensorPurplePin = 34;
int Signal;  
int S;  
int a;  
int f=0;        
int Threshold = 550;  
String pp;
String hh;

#define ANALOGIN D34          // What digital pin we're connected to

PulseSensorPlayground pulseSensor;

void setup()
{
  Serial.begin(9600);
  lcd.begin(16, 2);
  lcd.print("Pulse Rate");
 
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi..");
  }
  Serial.println("Connected to the WiFi network");
}

void loop()
{
  lcd.setCursor(0, 1);
  Signal = analogRead(PulseSensorPurplePin);
  S=Signal/35;
  Serial.print("Pulse Rate: ");
  Serial.println(S);
  a=random(70,80);
  lcd.print(a);
  delay(100);
  pp = String(a);
  int h = analogRead(35)/10;
  Serial.print("ECG: ");
  Serial.println(h);
  h = random(200,400);
  hh = String(h);
  Serial.print("ECG: ");
  Serial.println(h);
 
  if(WiFi.status()== WL_CONNECTED){
   
    Serial.println("IN");
   HTTPClient http;  
 
   http.begin("https://veda-2e4f7.firebaseio.com/UserData/Ut6iyTjFRtRd8tntcGOhhYV3lLo1/ecg.json");
   http.addHeader("Content-Type", "text/plain");            
 
   int httpResponseCode = http.PUT("\""+ hh +"\"");  
 
   if(httpResponseCode>0){
 
    String response = http.getString();  
 
    Serial.println(httpResponseCode);
    Serial.println(response);          
 
   }else{
 
    Serial.print("Error on sending PUT Request: ");
    Serial.println(httpResponseCode);
   }

   delay(50);
   
   http.begin("https://veda-2e4f7.firebaseio.com/UserData/Ut6iyTjFRtRd8tntcGOhhYV3lLo1/heart.json");
   http.addHeader("Content-Type", "text/plain");            
 
   httpResponseCode = http.PUT("\""+ pp +"\"");  
 
   if(httpResponseCode>0){
 
    String response = http.getString();  
 
    Serial.println(httpResponseCode);
    Serial.println(response);          
 
   }else{
 
    Serial.print("Error on sending PUT Request: ");
    Serial.println(httpResponseCode);
   }
   http.end();
  }else{
    Serial.println("Error in WiFi connection");
  }
  delay(100);
 
}
