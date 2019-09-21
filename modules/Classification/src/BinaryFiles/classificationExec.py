

# TODO: Vérifier les adresses des fichiers, utiliser la fonction register pour récupérer un fichier binaire

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
import pylab as pl
import numpy as np
# nltk.download('all')
# vocab = eval(readtxt("C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Vocabulaire.txt"))
#Obtention de vocab dans la rubrique consacrée au vocabulaire

### Affichage du fichier


# In[82]:

from nltk.tokenize import WordPunctTokenizer

def readtxt(adresse):
        
    with open(adresse, "r") as file:
        s = file.read()
    return s


### Tokenisation du texte

#### a)  Lemmatization de la liste de string

# In[83]:
def tokenizeSTR(s):
    word_punct_tokenizer = WordPunctTokenizer()
    wordsList = word_punct_tokenizer.tokenize(s)
    WL=[]
    punctEscape = ',.:;.?,?!()-&éà%$€£<>[]+^¨*ù/â|…1234567890–‘’”“#'
    punctEscape = punctEscape + "\"" + "\\"
    for word in wordsList:
        mark = True
        for punct in punctEscape:
            if punct in word:
                mark = False
        if mark:
            WL.append(word)
    return WL

# Normalise les mots en leurs radicaux
def lemmatization(texte):    # texte est une liste de str
    wordnet_lemmatizer = WordNetLemmatizer()
    Lem=wordnet_lemmatizer.lemmatize
    snowball_stemmer = stm("english")
    Normal=[]
    
    
    for mot in texte:
        if snowball_stemmer.stem(mot) != mot:
            Normal.append(snowball_stemmer.stem(mot))
        elif Lem(mot, pos = 'n') != mot:
            Normal.append(Lem(mot, pos = 'n'))
        elif Lem(mot, pos = 'v')!=mot:
            Normal.append(Lem(mot, pos = 'v'))
        elif Lem(mot, pos = 'a') != mot:
            Normal.append(Lem(mot, pos = 'a'))
        else:
            Normal.append(mot)
    return Normal       # List de str


#### b) Problèmes des Stop Words (SW)

##### i) Création d'une liste de SW

# In[86]:

# fileSW = open("/Users/NAIT/Desktop/stopWords.txt", "r")




#TODO: Vérifier les chemins des fichiers (ils sont tous sur gitlab)



fileSW=open("C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Classification\stopWords.txt","r")
lines = fileSW.readlines()

stopWords = []
for word in lines:
    word = word.rstrip('\n')
    stopWords.append(word)
#print(stopWords)


##### ii) Élimination des SW

# In[87]:
# Transforme une liste de str en une liste de str sans les SW
def StopWordsElimination(list):
    List = [word for word in list if word not in stopWords or word.isdigit() == True]
    finalString =""
    for a in List:
        finalString = finalString + " " + a
    return finalString


### Définition d'une norme

def TFIDF(corpus, vocab):
    vectorizer = TfidfVectorizer(vocabulary=vocab)
    response = vectorizer.fit_transform(corpus)
    CorpusVect = response.toarray()
    return CorpusVect
    
# On a besoin d'une liste de mots pour initialiser la TFIDF avec l'attribut vocab
# Ce vocabulaire sera récupéré en annexe dans une fonction Vocabulary()

#### Conclusion


def Vectorize(Text):          # Vectorisation d'1 string

    wordsList = tokenizeSTR(Text)
    Normal = lemmatization(wordsList)
    ListeReduite = StopWordsElimination(Normal)
    Vecteur = TFIDF([ListeReduite],vocab)
    return Vecteur

#### Réduction de dimensions: PCA


from sklearn.decomposition import PCA
import numpy as np

# TODO: Vérifier l'adresse du fichier binaire pca


adressePCA = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\BinaryFiles\pca"
pca = recuperation(adressePCA)


def ReductionDim(pca,X):
    Y = X.copy()
    Transformé = pca.transform(Y)
    return Transformé




#############################################
#############################################



### LinearSVC:

def entraineSVC(nX,nY,c):
    model = LinearSVC(C=c, class_weight=None, dual=False, fit_intercept=True,intercept_scaling=1, loss='squared_hinge', max_iter=1000,multi_class='ovr', penalty='l2', random_state=0, tol=1e-05, verbose=0)
    model.fit(nX,nY)
    return model
    
    
#C = la valeur de la marge (réel positif)
#dual = False     
#random_state has no impact
#tol donne l'erreur  10^-5 près
#verbose is for output



def testSVC(nX, nY, nXn, nYn,c):
    #nX et nY les parties d'entraînement
    #nXn et nYn les parties de test
    model = entraineSVC(nX,nY,c)
    n = len(nXn)
    if n == 0:
        return -1
    goal = 0
    failure = 0
    
    for k in range(n):
        prediction = model.predict([nXn[k]])
        if prediction[0] == nYn[k]:
            goal+=1
        else:
            failure +=1
    return goal/n


   

# TODO: Vérifier la direction du fichier binaire modelSVC    


# Paramètres optimaux choisis:

# p = 0.8 et C = 89
BinaryFolder = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\BinaryFiles"
DirModelSVC = BinaryFolder + "\\modelSVC"
modelSVC = recuperation(DirModelSVC)




#############################################
#############################################
    
    
    
### CREATION DES ECHANTILLONS DE REFERENCE

import time as cl
import random as rd
import pickle



    
# Extraction d'un fichier binaire
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
    c1 = cl.clock()

    serial_Banque= readbinary(direction)
    
    Banque= pickle.loads(serial_Banque)
    c2 = cl.clock()
    # print(c2-c1)
    return Banque


#############################################
#############################################



# Vocabulaire: Vérifier le chemin du fichier du vocabulaire dans gitlab

AdresseVocab = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Vocabulaire.txt"
vocab = eval(readtxt(AdresseVocab))
ident = ['Administration', 'Anger', 'Attic', 'Bathroom', 'Bedroom', 'Calm', 'Car', 'Castle', 'Cave', 'Cemetery', 'Church', 'Circus', 'Company', 'CountryTown', 'Desert', 'Fear', 'Field', 'Fight', 'Fog', 'Forest', 'Garden', 'Gunshot', 'Gym', 'Happy', 'Hospital', 'Industry', 'Jail', 'Kitchens', 'Library', 'LivingRoom', 'Love', 'Meadow', 'Mine', 'Mountain', 'Ocean', 'Plane', 'Rain', 'Sad', 'School', 'Snow', 'Sun', 'Thunder', 'TrainStation', 'Wind']

"""
    * Text est la chaine de caractères du texte
    * La sortie est un string de l'ambiance du Text
"""

# TODO: vérifier l'adresse de modelSVC 


def stringtoMood(Text):

        
    wordsList = tokenizeSTR(Text)
    Normal = lemmatization(wordsList)
    ListeReduite = StopWordsElimination(Normal)
    Vecteur = TFIDF([ListeReduite],vocab)

    n=30
    Vecteur = ReductionDim(pca,Vecteur)[0]

    

    BinaryFolder = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\BinaryFiles"
    DirModelSVC = BinaryFolder + "\\modelSVC"
    modelSVC = recuperation(DirModelSVC)
    VecteurNormalise = np.array([Vecteur])
    VecteurNormalise.reshape(-1,1)
    result = modelSVC.predict(VecteurNormalise)
    return ident[result[0]]
