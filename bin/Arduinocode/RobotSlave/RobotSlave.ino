#include <Wire.h>
int richtingMotor1 = 4;
int PWMMotor1 = 5;
int x;


int richtingMotor2 = 7;
int PWMMotor2 = 6;
int positieX = 0;

int lampL = 13;
int lampR = 12;

int command;

void setup() {
  Wire.begin();
  pinMode(richtingMotor1, OUTPUT);
  pinMode(PWMMotor1, OUTPUT);
  pinMode(richtingMotor2, OUTPUT);
  pinMode(PWMMotor2, OUTPUT);
  pinMode(lampL, OUTPUT);
  pinMode(lampR, OUTPUT);
  //    bandAchteruit();
  Wire.begin(9);
  // Attach a function to trigger when something is received.
  Wire.onReceive(receiveEvent);
  Serial.begin(9600);
}



void bandVooruit() {
  digitalWrite(richtingMotor2, LOW);
  analogWrite(PWMMotor2, 130);
  delay(1000);
  analogWrite(PWMMotor2, 0);

}

void bandAchteruit() {
  digitalWrite(richtingMotor2, HIGH);
  analogWrite(PWMMotor2, 255);
  delay(1000);
  analogWrite(PWMMotor2, 0);
}

void kraanLinks() {
  digitalWrite(richtingMotor1, LOW);
  analogWrite(PWMMotor1, 200);
//  delay(410);
//  analogWrite(PWMMotor1, 0);
}

void kraanRechts() {
  digitalWrite(richtingMotor1, HIGH);
  analogWrite(PWMMotor1, 200);
//  delay(410);
//  analogWrite(PWMMotor1, 0);
}

void lampLaan()
{
  digitalWrite(lampL, HIGH);
  delay(1000);
  digitalWrite(lampL, LOW);
}

void lampRaan()
{
  digitalWrite(lampR, HIGH);
  delay(1000);
  digitalWrite(lampR, LOW);
}

void receiveEvent(int bytes) {
  x = Wire.read();    // read one character from the I2C

  
}



void loop()
{
  if(x == 'l')
  {
    kraanLinks();
    x=0;
  }
  else if(x == 'r')
  {
     kraanRechts();
     x=0;
  }
  else if(x == 's')
  {
    analogWrite(PWMMotor1, 0);  
    x=0;
  }
  else if(x == 'v')
  {
    bandVooruit();
    x=0;
  }
  else if(x == 'a')
  {
    bandAchteruit();
    x=0;
  }
  else if(x == 'L')
  {
    lampLaan();
    x=0;
  }
  else if(x == 'R')
  {
    lampRaan();
    x=0;
  }
  
  if (Serial.available()) {

    //      digitalWrite(led1,HIGH);

    String b = Serial.readString();
    if (b == "links") {
      kraanLinks();
      positieX = positieX - 1;
    } else if (b == "rechts") {
      kraanRechts();
      positieX = positieX + 1;
    } else if (b == "basis") {
      while (positieX > 0) {
        kraanLinks();
        positieX = positieX - 1;
      }

    } else if (b == "test") {
      while (positieX < 4) {
        kraanRechts();
        positieX = positieX + 1;
      }

    }
  }
}




