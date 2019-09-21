#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jun  2 21:47:02 2019

@author: frank
"""

import os
hostname = "8.8.8.8" #example
response = os.system("ping -c 1 " + hostname)

#and then check the response...
if response == 0:
  print ('up')
else:
  print ('down')