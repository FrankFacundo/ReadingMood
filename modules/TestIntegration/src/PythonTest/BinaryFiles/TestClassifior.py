# coding: utf-8

# In[ ]:

import classifierAlgo
import os
import time

def testPredictionPerMood(listText, listIndentAmbiance):
    compteur = 0
    nbText = 0
    listAmbiance = ['Administration', 'Anger', 'Attic', 'Bathroom', 'Bedroom', 'Calm', 'Car', 'Castle', 'Cave', 'Cemetery', 'Church', 'Circus', 'Company', 'CountryTown', 'Desert', 'Fear', 'Field', 'Fight', 'Fog', 'Forest', 'Garden', 'Gunshot', 'Gym', 'Happy', 'Hospital', 'Industry', 'Jail', 'Kitchens', 'Library', 'LivingRoom', 'Love', 'Meadow', 'Mine', 'Mountain', 'Ocean', 'Plane', 'Rain', 'Sad', 'School', 'Snow', 'Sun', 'Thunder', 'TrainStation', 'Wind'] 
    for i in listText:
        string = classifierAlgo.stringtoMood(i)
        indentAmbiance = listIndentAmbiance[nbText]
        if string == listAmbiance[indentAmbiance]:
            compteur +=1
            #listRate.append([ambiance, compteur*100/nbText])
        nbText +=1
    return compteur/nbText
    
def testPredictionTotal(testDataDirectoryPath):
    sumAccuracy = 0
    listRatePerMood = testPredictionPerMood(testDataDirectoryPath)
    n = len(listRatePerMood)
    for i in range(n) :
        sumAccuracy += listRatePerMood[1]
    return sumAccuracy/(n)
        

def getTrainingTime():
    c1 = cl.clock()
    classifierAlgo.stringtoMood('')
    c2 = cl.clock()
    return c2-c1

import os
banque = classifierAlgo.recuperation("Banque")
listText = banque[7][0][2]
listIndentAmbiance = banque[7][0][3]
accuracy = testPredictionPerMood(listText,listIndentAmbiance)
print(accuracy)
time = getTrainingTime()
print(time)

