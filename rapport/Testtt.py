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
def testPredictionPerMood(testDataDirectoryPath):
    listRate = []
    compteur = 0
    nbText = 0
    listAmbiance = ["Administration", "Anger", "Attic", "Bathroom", "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery", "Church", "Circus", "Company", "CountryTown", "Desert", "Fear", "Field", "Fight", "Fog", "Forest", "Garden", "Gunshot", "Gym", "Happy", "Hospital", "Industry", "Jail", "Kitchens", "Library", "LivingRoom", "Love", "Meadow", "Mine", "Mountain", "Ocean", "Plane", "Rain", "Sad", "School", "Snow", "Sun", "Thunder", "TrainStation", "Wind"]
    for element in os.listdir(testDataDirectoryPath)
        c1 = cl.clock()
        nbText +=1
        with open(element, "r", encoding = "utf-8") as file:
            text = file.read()
            text.encode('utf-8')
            print(text)
            if (classifier.stringtoMood(text)) is ambiance:
                compteur = compteur + 1
        c2 = cl.clock()
    return c2-c1

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

if __name__ == "__main__":
    import sys
    testDataDirectoryPath = sys.argv[1]
    print(testPredictionPerMood(testDataDirectoryPath))
