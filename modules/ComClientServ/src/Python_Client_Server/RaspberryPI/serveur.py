# coding: utf-8

import socket               # Import socket module

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

host = "10.30.216.173" # Get local machine name
port = 9877             # Reserve a port for your service.
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

