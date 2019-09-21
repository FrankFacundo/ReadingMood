# coding: utf-8

# In[ ]:

import classifier
import os
import time

# TODO: Vérifier les adresses des fichiers, utiliser la fonction register pour récupérer un fichier binaire
import pickle
from collections import Counter
from nltk.stem import SnowballStemmer as stm
from nltk.stem import WordNetLemmatizer
import nltk
import time as cl
from sklearn.feature_extraction.text import TfidfVectorizer
import glob
import os as os
from collections import Counter
from sklearn.svm import LinearSVC
from sklearn.datasets import make_classification
import random as rd
import numpy as np
# nltk.download('all')
# vocab = eval(readtxt("C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Vocabulaire.txt"))
#Obtention de vocab dans la rubrique consacrée au vocabulaire

### Affichage du fichier
nltk.download('wordnet')

# In[82]:

from nltk.tokenize import WordPunctTokenizer
def readbinary(adresse):
    with open(adresse, "rb") as file:
        s = file.read()
    return s

def register(Banque,direction):
    serialBanque = pickle.dumps(Banque)
    fichiertxt = open(direction,mode="xb")
    fichiertxt.write(serialBanque)
    fichiertxt.close()
def recuperation(direction):
    serial_Banque= readbinary(direction)
    Banque= pickle.loads(serial_Banque)
    return Banque

listAmbiance = ["Administration", "Anger", "Attic", "Bathroom", "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery", "Church", "Circus", "Company", "CountryTown", "Desert", "Fear", "Field", "Fight", "Fog", "Forest", "Garden", "Gunshot", "Gym", "Happy", "Hospital", "Industry", "Jail", "Kitchens", "Library", "LivingRoom", "Love", "Meadow", "Mine", "Mountain", "Ocean", "Plane", "Rain", "Sad", "School", "Snow", "Sun", "Thunder", "TrainStation", "Wind"]

def testPredictionPerMood():
    listRate = []
    listAmbiance = ["Administration", "Anger", "Attic", "Bathroom", "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery", "Church", "Circus", "Company", "CountryTown", "Desert", "Fear", "Field", "Fight", "Fog", "Forest", "Garden", "Gunshot", "Gym", "Happy", "Hospital", "Industry", "Jail", "Kitchens", "Library", "LivingRoom", "Love", "Meadow", "Mine", "Mountain", "Ocean", "Plane", "Rain", "Sad", "School", "Snow", "Sun", "Thunder", "TrainStation", "Wind"]
    for ambiance in listAmbiance:
        compteur = 0
        nbText = 0
        for element in os.listdir('/Users/NAIT/Documents/Textes Ambiance' + '/' + ambiance):
            c1 = cl.clock()
            nbText +=1
            with open('/Users/NAIT/Documents/Textes Ambiance' + '/' + ambiance + '/' + element, "r", encoding = "utf-8") as file:
                text = file.read()
                #print(classifier.stringtoMood(text))
                if ((classifier.stringtoMood(text)) == ambiance):
                    compteur = compteur + 1
            c2 = cl.clock()
        listRate.append([ambiance, compteur,nbText, c2-c1])

    import matplotlib.pyplot as plt
    import numpy as np

    fig = plt.figure()

    x = [i for i in range(len(listAmbiance))]
    height = [100*listRate[i][1]/listRate[i][2] for i in range(len(listAmbiance))]
    width = 0.6
    plt.bar(x, height, width, color='b' )
# Add title and axis names
    plt.title('Test du classifieur sur les différentes classes')
    plt.xlabel('Classes')
    plt.ylabel("Classifior's accuracy (%)")


# Create names
    plt.xticks(x, listAmbiance)
    plt.xticks(rotation=90)

# Show graphic
    plt.savefig('Test_classifieur.png')
    plt.show()    

    fig2 = plt.figure()
    height2 = [listRate[i][2] for i in range(len(listAmbiance))]
    width = 0.6
    plt.bar(x, height2, width, color='b' )
# Add title and axis names
    plt.title('Nombre de textes testés pour chaque classe')
    plt.xlabel('Classes')
    plt.ylabel("Number of texts tested")


# Create names
    plt.xticks(x, listAmbiance)
    plt.xticks(rotation=90)

# Show graphic
    plt.savefig('Test_classifieur2.png')
    plt.show()

    fig3 = plt.figure()
    height3 = [listRate[i][3] for i in range(len(listAmbiance))]
    width = 0.6
    plt.bar(x, height3, width, color='b' )
# Add title and axis names
    plt.title("Temps d'exécution du classifieur pour chaque classe")
    plt.xlabel('Classes')
    plt.ylabel("Execution time (seconds)")
    

# Create names
    plt.xticks(x, listAmbiance)
    plt.xticks(rotation=90)

# Show graphic
    plt.savefig('Test_classifieur3.png')
    plt.show()

    moyenne = 0
    for i in range(len(height)):
         moyenne += height[i]
    print (moyenne/len(height))
    return (listRate)

def testPredictionTotal(testDataDirectoryPath):
    sumAccuracy = 0
    listRatePerMood = testPredictionPerMood(testDataDirectoryPath)
    n = len(listRatePerMood)
    for i in range(n) :
        sumAccuracy += listRatePerMood[1]
    return sumAccuracy/(n)


def getiningTime():
    c1 = cl.clock()
    classifier.stringtoMood('')
    c2 = cl.clock()
    return c2-c1

import matplotlib.pyplot as plt
import numpy as np

#fig = plt.figure()

#x = [i for i in range(len(listAmbiance))]
#height = [100*listRate[i][1]/listRate[i][2] for i in range(len(listAmbiance))]
#width = 1.0
#plt.bar(x, height, width, color='b' )
# Add title and axis names
#plt.title('Test du classifieur sur les différentes classes')
#plt.xlabel('Classes')
#plt.ylabel("Classifior's accuracy")
 
 
# Create names
#plt.xticks(x, listAmbiance)
 
# Show graphic
#plt.savefig('Test_classifieur.png')
#plt.show()


if __name__ == "__main__":
    import sys
    #mood = sys.argv[1]
    print(testPredictionPerMood())
