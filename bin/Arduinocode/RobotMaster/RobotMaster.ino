
#include <Wire.h>
int richtingMotor1 = 4;
int PWMMotor1 = 5;
boolean gasjebij = false;

int richtingMotor2 = 7;
int PWMMotor2 = 6;
int kraanafstand = 550;

int positieY;
int positieX;

int bestemmingX;
int bestemmingY;

int x = 0;

boolean horizontaalCheck;
boolean verticaalCheck;
boolean oppakCheck;
boolean startCheck;
boolean bezigCheck;

String bezigheid = "";

boolean afleverHorizontaalCheck;
boolean afleverVerticaalCheck;
boolean afleverBezigCheck;
boolean afleverStartCheck;
boolean afleverStap1Check;
boolean afleverStap2Check;

//int stappen = 0;
int aantalStappen;
String richting;


boolean wit = true;
boolean zwart = false;
boolean begonnen = false;

const int QRD1114_PIN = A3; // Sensor output voltage

void setup() {
  Serial.begin(9600);
  Wire.begin();
  pinMode(QRD1114_PIN, INPUT);

  positieX = 1;
  positieY = 1;
}

void armHelemaalVooruit()
{
  armVooruit();
  armVooruit();
  armVooruit();
}

void pakketjesAfleveren()
{
  delay(1000);
  armAchteruit(220);
  Serial.write('a');
  delay(1000);
  procesBPP();
  delay(1000);
  armAchteruit(130);
  Serial.write('a');
    delay(1000);
  procesBPP();
  delay(1000);
  armAchteruit(130);
    delay(1000);
  procesBPP();
  delay(1000);
}

void procesBPP()
{
  String b = Serial.readString();
  if(b == "bl")
    {
      Wire.beginTransmission(9); // transmit to device #9
      Wire.write('v');              // sends x
      Wire.endTransmission();    // stop transmitting
    }
    else if(b == "br")
    {
      Wire.beginTransmission(9); // transmit to device #9
      Wire.write('a');              // sends x
      Wire.endTransmission();    // stop transmitting
    }
    else if(b == "ll")
    {
      Wire.beginTransmission(9); // transmit to device #9
      Wire.write('L');              // sends x
      Wire.endTransmission();    // stop transmitting
    }
    else if(b == "lr")
    {
      Wire.beginTransmission(9); // transmit to device #9
      Wire.write('R');              // sends x
      Wire.endTransmission();    // stop transmitting
    }
}

void afleverLoop()
{
  if (afleverStartCheck && !afleverVerticaalCheck && !afleverBezigCheck)
  {
    Serial.println("start verticaal afleveren");
    afleverBezigCheck = true;
    beweegVerticaal();
    afleverBezigCheck = false;
  }
  if (afleverVerticaalCheck && !afleverHorizontaalCheck && !afleverBezigCheck)
  {
    Serial.println("start horizontaal afleveren");
    afleverBezigCheck = true;
    beweegHorizontaal();
  }
  if (afleverHorizontaalCheck && !afleverStap1Check && !afleverBezigCheck)
  {
    Serial.println("start stap 1");
    afleverBezigCheck = true;
    armHelemaalVooruit();
    afleverBezigCheck = false;
    afleverStap1Check = true;
  }
  if (afleverStap1Check && !afleverStap2Check && !afleverBezigCheck)
  {
    Serial.println("start stap 2");
    bezigCheck = true;
    bestemmingY = 1;
    beweegVerticaal();
    afleverStap2Check = true;
    bezigCheck = false;
  }
  if (afleverStartCheck && afleverStap2Check && !bezigCheck)
  {
    Serial.println("start afleveren");
    pakketjesAfleveren();
    afleverStartCheck = false;
    bezigheid = "null";
    Serial.write("g");
  }
}

void afleveren()
{
  afleverHorizontaalCheck = false;
  afleverVerticaalCheck = false;
  afleverBezigCheck = false;
  afleverStap1Check = false;
  afleverStap2Check = false;

  bestemmingX = 6;
  bestemmingY = 2;

  afleverStartCheck = true;
  afleverBezigCheck = false;
  bezigheid = "afleveren";
}

void armVooruit() {
  digitalWrite(richtingMotor2, LOW);
  analogWrite(PWMMotor2, 80);
  delay(120);
  analogWrite(PWMMotor2, 0);

}

void armAchteruit(int d) {
  digitalWrite(richtingMotor2, HIGH);
  analogWrite(PWMMotor2, 90);
  delay(d);
  analogWrite(PWMMotor2, 0);
}

void kraanOmhoog() {
  delay(150);
  digitalWrite(richtingMotor1, LOW);
  analogWrite(PWMMotor1, 255);
  delay(kraanafstand);
  analogWrite(PWMMotor1, 0);
  positieY = positieY + 1;
}

void kraanOmlaag() {
  digitalWrite(richtingMotor1, HIGH);
  analogWrite(PWMMotor1, 100);
  delay(kraanafstand);
  analogWrite(PWMMotor1, 0);
  positieY = positieY - 1;
}

void optillen() {
  digitalWrite(richtingMotor1, LOW);
  analogWrite(PWMMotor1, 255);
  delay(125);
  analogWrite(PWMMotor1, 0);
}

void zakken() {
  digitalWrite(richtingMotor1, HIGH);
  analogWrite(PWMMotor1, 90);
  delay(120);
  analogWrite(PWMMotor1, 0);
}

void pakPakketje() {
  armVooruit();
  delay(150);
  armVooruit();
  delay(150);
  armVooruit();
  delay(150);
  optillen();
  delay(150);
  armAchteruit(100);
  delay(150);
  armAchteruit(100);
  delay(150);
  armAchteruit(100);
  armAchteruit(100);
  delay(150);
  zakken();
  delay(150);
  bezigCheck = false;
  oppakCheck = true;
  startCheck = false;
  Serial.write("g");
}


void zoekPaaltje() {
  //          Serial.println(positieX);

  if (begonnen) {
    if (positieX != bestemmingX) {
      // Read in the ADC and convert it to a voltage:
      int proximityADC = analogRead(QRD1114_PIN);
      float proximityV = (float)proximityADC * 5.0 / 1023.0;
      //Serial.println(proximityV);


      if (begonnen && wit)
      {
        if (proximityV > 3)
        {
          wit = false;
          zwart = true;
          gasjebij = false;
        }
      }
      if (begonnen && zwart)
      {
        if (proximityV < 3)
        {
          zwart = false;
          wit = true;
          if (richting == "r") {
            positieX = positieX + 1;
          } else if (richting == "l") {
            positieX = positieX - 1;
          }
        }
      }
    } else if (positieX == bestemmingX) {
      begonnen = false;
      Wire.beginTransmission(9); // transmit to device #9
      Wire.write('s');              // sends x
      Wire.endTransmission();    // stop transmitting
      if(bezigheid.equals("oppakken"))
      {
        horizontaalCheck = true;
        bezigCheck = false;
      }
      if(bezigheid.equals("afleveren"))
      {
        afleverHorizontaalCheck = true;
        afleverBezigCheck = false;
      } 
    }
  }
}

void beweegHorizontaal()
{
  if ((bestemmingX - positieX) < 0) { //links
    Wire.beginTransmission(9); // transmit to device #9
    Wire.write('l');              // sends x
    Wire.endTransmission();    // stop transmitting
    begonnen = true;
    richting = "l";
  } else if ((bestemmingX - positieX) > 0) { //rechts
    Wire.beginTransmission(9); // transmit to device #9
    Wire.write('r');              // sends x
    Wire.endTransmission();    // stop transmitting
    richting = "r";
    begonnen = true;
  } else {
    begonnen = true;
  }
}

void beweegVerticaal() {
  if (bestemmingY - positieY > 0) { //omhoog
    while (positieY != bestemmingY) {
      kraanOmhoog();
      //      delay(50);
    }   
  } else if (bestemmingY - positieY < 0) { //omlaag
    while (positieY != bestemmingY) {
      kraanOmlaag();
      delay(50);
    }
  }
  if(bezigheid.equals("oppakken"))
  {
    verticaalCheck = true;
    bezigCheck = false;
  }
  if(bezigheid.equals("afleveren"))
  {
    afleverVerticaalCheck = true;
    afleverBezigCheck = false;
  } 
}

void beweeg()
{
  if (startCheck && !horizontaalCheck && !bezigCheck)
  {
    Serial.println("s h");
    beweegHorizontaal();
    bezigCheck = true;
  }
  if (horizontaalCheck && !verticaalCheck && !bezigCheck)
  {
    Serial.println("s v");
    bezigCheck = true;
    beweegVerticaal();
  }
  if (verticaalCheck && !oppakCheck && !bezigCheck)
  {
    Serial.println("s o");
    pakPakketje();
    bezigCheck = true;
  }
}

void start(int X, int Y)
{
  horizontaalCheck = false;
  verticaalCheck = false;
  oppakCheck = false;
  bezigCheck = false;

//  positieX = 3;
//  positieY = 1;

  bestemmingX = X;
  bestemmingY = Y;

  startCheck = true;
  bezigheid = "oppakken";
}

void loop() {

  zoekPaaltje();
  beweeg();
  afleverLoop();

  if (Serial.available())
  {
    String b = Serial.readString();
        
    if (b == "omhoog")
    {
      kraanOmhoog();
      positieY = positieY + 1;
    }
    else if (b == "omlaag")
    {
      kraanOmlaag();
      positieY = positieY - 1;
    }
    else if (b == "vooruit")
    {
      armVooruit();
    }
    else if (b == "achteruit")
    {
      armAchteruit(100);
    } else if (b == "optillen") {
      optillen();
    }
    else if (b == "zakken")
    {
      zakken();
    }
    else if (b == "basis")
    {
      while (positieY > 0)
      {
        kraanOmlaag();
      }
      while (positieX > 0)
      {
        Wire.beginTransmission(9); // transmit to device #9
        Wire.write('l');              // sends x
        Wire.endTransmission();    // stop transmitting
        begonnen = true;
      }
      if (positieY == 0)
      {
        zakken();
        zakken();
      }
    }
    else if(b == "bl")
    {
      Wire.beginTransmission(9); // transmit to device #9
      Wire.write('v');              // sends x
      Wire.endTransmission();    // stop transmitting
    }
    else if(b == "br")
    {
      Wire.beginTransmission(9); // transmit to device #9
      Wire.write('a');              // sends x
      Wire.endTransmission();    // stop transmitting
    }
    else if(b == "ll")
    {
      Wire.beginTransmission(9); // transmit to device #9
      Wire.write('L');              // sends x
      Wire.endTransmission();    // stop transmitting
    }
    else if(b == "lr")
    {
      Wire.beginTransmission(9); // transmit to device #9
      Wire.write('R');              // sends x
      Wire.endTransmission();    // stop transmitting
    }
    else if(b == "bpp")
    {
      Serial.println("start bpp");
      afleveren();
    }
    else if(b == "s11")
    {
      start(1,1);
    }
    else if(b == "s12")
    {
      start(1,2);
    }
    else if(b == "s13")
    {
      start(1,3);
    }
    else if(b == "s14")
    {
      start(1,4);
    }
    else if(b == "s15")
    {
      start(1,5);
    }
    else if(b == "s21")
    {
      start(2,1);
    }
    else if(b == "s22")
    {
      start(2,2);
    }
    else if(b == "s23")
    {
      start(2,3);
    }
    else if(b == "s24")
    {
      start(2,4);
    }
    else if(b == "s25")
    {
      start(2,5);
    }
    else if(b == "s31")
    {
      start(3,1);
    }
    else if(b == "s32")
    {
      start(3,2);
    }
    else if(b == "s33")
    {
      start(3,3);
    }
    else if(b == "s34")
    {
      start(3,4);
    }
    else if(b == "s35")
    {
      start(3,5);
    }
    else if(b == "s41")
    {
      start(4,1);
    }
    else if(b == "s42")
    {
      start(4,2);
    }
    else if(b == "s43")
    {
      start(4,3);
    }
    else if(b == "s44")
    {
      start(4,4);
    }
    else if(b == "s45")
    {
      start(4,5);
    }
    else if(b == "s51")
    {
      start(5,1);
    }
    else if(b == "s52")
    {
      start(5,2);
    }
    else if(b == "s53")
    {
      start(5,3);
    }
    else if(b == "s54")
    {
      start(5,4);
    }
    else if(b == "s55")
    {
      start(5,5);
    }
  }
}




