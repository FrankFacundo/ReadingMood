# coding: utf-8

import socket               # Import socket module

import RPi.GPIO as GPIO
from gpiozero import PWMOutputDevice
from time import sleep
GPIO.setmode(GPIO.BCM)

#BCM 26 est le pin servo 0
#down : arret le moteur si la masse est connecte
#up   : arret le moteur si 3.3v est connecte
GPIO.setup(13, GPIO.IN,pull_up_down=GPIO.PUD_DOWN)
GPIO.setup(6, GPIO.OUT,initial = GPIO.LOW)

pwm = PWMOutputDevice(4)





AgriculturalField = str("AgriculturalField\n")
Christmas = str("Christmas\n")
Cook = str("Cook\n")
Forest = str("Forest\n")
FloralGarden = str("FloralGarden\n")
Ocean = str("Ocean\n")
sound = str("sound\n")
Exit= str("exit\n")

available = True
soc = socket.socket()         # Create a socket object
#host = "192.168.0.105" # Get local machine name
host = "192.168.43.45" # Get local machine name
port = 9881             # Reserve a port for your service.
soc.bind((host, port))       # Bind to the port
soc.listen(5)                 # Now wait for client connection.
while available:
    conn, addr = soc.accept()     # Establish connection with client.
    print ("Got connection from",addr)
    msg = conn.recv(1024)
    text = str(msg)

    conn.sendall(msg)

    print(msg)
    if ( text == str("Hello\n") ):
        print("Hii everyone")
    elif( text == Exit ):
        print("Closing server")
        conn.close
        soc.close
        available = False
    elif( text == AgriculturalField ):
        print("Motor1")
    elif( text == Christmas ):
        print("Motor2")
    elif( text == Cook ):
        print("Motor3")
        motor1()
    elif( text == Forest ):
        print("Motor4")
    elif( text == FloralGarden ):
        print("Motor5")
    elif( text == Ocean ):
        print("Motor6")
    elif( text == sound ):
        print("Sound")
    else:
        print("Go away\n\n ")



def motor1:
    GPIO.output(6,GPIO.HIGH)
    pwm.on()
    sleep(0.5)
    depart = millis()
    while temp < 3000 :
        if GPIO.input(13):
            GPIO.output(6,GPIO.HIGH)
            pwm.on()
        else:
            GPIO.output(6,GPIO.LOW)
            pwm.off()
        current = millis()
        temp = current - depart
