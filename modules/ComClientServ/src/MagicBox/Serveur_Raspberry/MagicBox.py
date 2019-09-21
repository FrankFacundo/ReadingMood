# coding: utf-8

# Import socket module
import socket               
#Control time 
import time
#Give access to Raspberry Pi
import RPi.GPIO as GPIO
#Give tools for threading
import threading
from gpiozero import PWMOutputDevice
from time import sleep
#Set pins with BCM configuration
GPIO.setmode(GPIO.BCM)

# Set ports in (clics)
GPIO.setup(14, GPIO.IN,pull_up_down=GPIO.PUD_DOWN) #clic1
GPIO.setup(8, GPIO.IN,pull_up_down=GPIO.PUD_DOWN)  #clic2
GPIO.setup(18, GPIO.IN,pull_up_down=GPIO.PUD_DOWN) #clic3
GPIO.setup(23, GPIO.IN,pull_up_down=GPIO.PUD_DOWN) #clic4
GPIO.setup(24, GPIO.IN,pull_up_down=GPIO.PUD_DOWN) #clic5
GPIO.setup(25, GPIO.IN,pull_up_down=GPIO.PUD_DOWN) #clic6
GPIO.setup(12, GPIO.IN,pull_up_down=GPIO.PUD_DOWN) #clic7

# Set ports out (moteurs)
GPIO.setup(13, GPIO.OUT,initial = GPIO.LOW) #moteur1
GPIO.setup(6, GPIO.OUT,initial = GPIO.LOW)  #moteur2
GPIO.setup(5, GPIO.OUT,initial = GPIO.LOW)  #moteur3
GPIO.setup(11, GPIO.OUT,initial = GPIO.LOW) #moteur4 Fun1
GPIO.setup(9, GPIO.OUT,initial = GPIO.LOW)  #moteur5 Fun2
GPIO.setup(10, GPIO.OUT,initial = GPIO.LOW) #moteur6
GPIO.setup(22, GPIO.OUT,initial = GPIO.LOW) #moteur7
GPIO.setup(27, GPIO.OUT,initial = GPIO.LOW) #moteur8
GPIO.setup(17, GPIO.OUT,initial = GPIO.LOW) #moteur9
GPIO.setup(4, GPIO.OUT,initial = GPIO.LOW)  #moteur10

#Olfactory ambiences
AgriculturalField = str("AgriculturalField\n")
Christmas = str("Christmas\n")
Cook = str("Cook\n")
Forest = str("Forest\n")
FloralGarden = str("FloralGarden\n")
Ocean = str("Ocean\n")
sound = str("sound\n")
Exit= str("exit\n")

# Raspberry PI pins BCM of motors and clics
motors = [13, 6, 5, 11, 9, 10, 22, 27, 17, 4]
clics = [14, 8, 18, 23, 24, 25, 12]

'''Functions et code'''
#Thread for first fun
def thread_fun1():
	GPIO.output(11,GPIO.HIGH)
	sleep(10)
	GPIO.output(11,GPIO.LOW)

#Thread for second fun
def thread_fun2():
	GPIO.output(9,GPIO.HIGH)
	sleep(10)
	GPIO.output(9,GPIO.LOW)
#Code for motors
def motor(motor_number,clic_number):
    GPIO.output(motor_number,GPIO.HIGH)
    sleep(0.7)
    depart = int(round(time.time()*1000))
    temp = 0
    while temp < 3000 :
        if GPIO.input(clic_number):
            GPIO.output(motor_number,GPIO.HIGH)
        else:
            GPIO.output(motor_number,GPIO.LOW)
        current = int(round(time.time()*1000))
        temp = current - depart

available = True
# Create a socket object
soc = socket.socket()         
# Get local machine name
host = "192.168.43.172" 
# Reserve a port for your service.
port = 9877             
# Bind to the port
soc.bind((host, port))       
# Now wait for client connection.
soc.listen(5)                 
while available:
    # Establish connection with client.
    conn, addr = soc.accept()     
    # Print IP address of client
    print ("Got connection from",addr)
    # The received message is limited to 1024 bits
    msg = conn.recv(1024)
    # Cast the 'msg' variable to a string
    text = str(msg)
    # Resend the same message to the client
    conn.sendall(msg)
    print(msg)
    # Take actions according to the message received
    if ( text == str("Hello\n") ):
        print("Hii everyone")
    elif( text == Exit ):
        print("Closing server")
        conn.close
        soc.close
        available = False
    elif( text == FloralGarden ):
        print("Motor1")
        threading.Thread(target=thread_fun2).start()
        motor(motors[0],clics[0])
    elif( text == Cook ):
        print("Motor2")
        threading.Thread(target=thread_fun2).start()
        motor(motors[1],clics[1])
    elif( text == AgriculturalField ):
        print("Motor3")
        threading.Thread(target=thread_fun2).start()
        motor(motors[2],clics[2])
    elif( text == Ocean ):
        print("Motor4")
        threading.Thread(target=thread_fun1).start()
        motor(motors[5],clics[3])
    elif( text == Forest ):
        print("Motor5")
        threading.Thread(target=thread_fun1).start()
        motor(motors[6],clics[4])
    elif( text == Christmas ):
        print("Motor6")
        threading.Thread(target=thread_fun1).start()
        motor(motors[7],clics[5])
    elif( text == sound ):
        print("Sound")
    else:
        print("Unknown Parameter\n\n ")
