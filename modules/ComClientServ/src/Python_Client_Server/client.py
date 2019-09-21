# -*- coding: utf-8 -*-
"""
Created on Thu Dec  6 12:15:11 2018

@author: frank
"""

import socket

hote = "localhost"
port = 15555

socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket.connect((hote, port))
print ("Connection on {}".format(port))

socket.sendall(b'Hola1')
print ('Hola1')

print ("Close")
socket.close()