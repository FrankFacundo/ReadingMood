import os
import time as cl
from collections import Counter
from nltk.stem import SnowballStemmer as stm
from nltk.stem import WordNetLemmatizer 
from nltk.tokenize import WordPunctTokenizer


# Traduction d'un fichier en str
def readtxt(adresse):
        
    word_punct_tokenizer = WordPunctTokenizer()
    
    with open(adresse, "r") as file:
        s = file.read()
    return s
    


# Détection d'un texte à lire
def detectStart(codeS,i):
    n = len(codeS)
    motifStart = "data-writing-prompt=\"0\"><p>"
    if n-i > 27:
        Testé = codeS[i:i+27]
        if Testé == motifStart:
            return True
    return False

# Détection de fin de texte
def detectEnd(codeS,i):
    n = len(codeS)
    motifEnd = "</p>"
    if n-i >4:
        Testé = codeS[i:i+4]
        if Testé == motifEnd:
            return True
    return False

# Ecriture d'un texte
def Ecrit(codeS,i):
    j = 0
    T = ""
    while not(detectEnd(codeS,i+j)):
        T=T+codeS[i+j]
        j+=1
    return T

# Extraction des textes du code Source
def diviseTexte(codeS):
    Corpus = []
    i = 0
    n = len(codeS)
    while i!=n:
        if detectStart(codeS,i):
            i+=27
            Texte = Ecrit(codeS,i)
            Corpus.append(Texte)
            decal=len(Texte) + 4
            i += decal
        i+=1
    return Corpus

# Récupération des textes liés à 1 ambiance donnée
def Textes(Ambiance):
    folder = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Textes Ambiance"
    adresse = folder + '\\' + Ambiance + "\\" + Ambiance + ".txt"
    return diviseTexte(readtxt(adresse))




def Ecrituretxt(Ambiance,numero,texte):
    folder = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Textes Ambiance"
    adresse = folder + '\\' + Ambiance + "\\" + Ambiance+str(numero)+".txt"
    fichiertxt = open(adresse,mode="x")
    fichiertxt.write(texte)
    fichiertxt.close()

c1 = cl.clock()

LAmbiances =['Bathroom','Bedroom','Calm','Christmas','Fog','Kitchens','Rain','Restaurant','Snow','The Sun','Thunder','Wind']
corpusTot = []
for Ambiance in LAmbiances:
    corpusAmbiance = Textes(Ambiance)
    corpusTot.append(corpusAmbiance)
    for i in range(len(corpusAmbiance)):
        Ecrituretxt(Ambiance,i,corpusAmbiance[i])

c2 = cl.clock()

temps = c2 - c1
print(temps)