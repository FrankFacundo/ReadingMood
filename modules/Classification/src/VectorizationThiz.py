# coding: ANSI

from collections import Counter
from nltk.stem import SnowballStemmer as stm
from nltk.stem import WordNetLemmatizer 
import nltk
import time as cl
from sklearn.feature_extraction.text import TfidfVectorizer
import glob
import os as os
from collections import Counter
# nltk.download('all')
# vocab = eval(readtxt("C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Vocabulaire.txt"))
#Obtention de vocab dans la rubrique consacrée au vocabulaire

### Affichage du fichier



from nltk.tokenize import WordPunctTokenizer

    

def tokenize(file):
    word_punct_tokenizer = WordPunctTokenizer()
    with open(file, "r") as file:
            s = file.read()
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
    return(WL)



### Tokenisation du texte

#### a)  Lemmatization de la liste de string

# In[83]:

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


def Dico():
    Vocabstr = readtxt("C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Textes Ambiance\Vocabulaire.txt")
    Vocab = eval(Vocabstr)
    prepDico = []
    for mot in Vocab:
        prepDico.append([mot,0])
    dico = dict(prepDico)
    return dico,Vocab

def comptage(texte):
    
    dictionnaire, Vocab= Dico()
    
    for mot in texte:
        if mot in dictionnaire:
            dictionnaire[mot]+=1
            
            
    n = len(dictionnaire)
    vecteur = np.zeros(n)

    for i in range(n):    

        vecteur[i] = dictionnaire[Vocab[i]]

    return vecteur

def TFIDF(corpus, vocab):
    vectorizer = TfidfVectorizer(vocabulary=vocab)
    response = vectorizer.fit_transform(corpus)
    CorpusVect = response.toarray()
    return CorpusVect
    
# On a besoin d'une liste de mots pour initialiser la TFIDF avec l'attribut vocab
# Ce vocabulaire sera récupéré en annexe dans une fonction Vocabulary()

#### Conclusion


def Vectorize(fichiertxt):          # Vectorisation d'1 fichier texte
    wordsList = tokenize(fichiertxt)
    Normal = lemmatization(wordsList)
    ListeReduite = StopWordsElimination(Normal)
    return ListeReduite #"str"
    

def VectorizationOfDataSet():   # La liste de tous les textes
    corpus = []
    adresseDocAmb = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Data Set"
    LAmbiances = os.listdir(adresseDocAmb)
    
    for ambiance in LAmbiances:
        PtrAmbFold = os.listdir("C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Data Set" + "\\" + ambiance)
        
        for PtrFile in PtrAmbFold:

            AdresseText = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Data Set" + "\\" + ambiance + "\\" + PtrFile
            corpus.append(Vectorize(AdresseText))

    return corpus

def VectorisationAmb(): # Vectorisation des textes d'ambiances
    c1 = cl.clock()
    def VectorizationOfDataSetTMP():   # La liste de tous les textes
        corpus = []
        adresseDocAmb = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Data Set"
        LAmbiances = os.listdir(adresseDocAmb)
        Lid = []
        for ambiance in LAmbiances:
            PtrAmbFold = os.listdir("C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Data Set" + "\\" + ambiance)
            n = 0
            for PtrFile in PtrAmbFold:
    
                AdresseText = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Data Set" + "\\" + ambiance + "\\" + PtrFile
                corpus.append(Vectorize(AdresseText))
                n+=1
            Lid.append((ambiance,n))
        return corpus,Lid
    
    corpus,ident = VectorizationOfDataSetTMP()
    reponse = TFIDF(corpus,vocab), ident
    c2 = cl.clock()
    print(c2-c1)    # Petite estimation du temps que ça prend
    return reponse
    # reponse = LVecteurs, ident
    # ident est la liste des couples (Ambiance, nbTextes de l'ambiance)

def Vectorisation(LAdresseText):
    LRed = []
    for AdresseText in LAdresseText:
        LRedi = Vectorize(AdresseText)
        LRed.append(LRedi)
    return TFIDF(LRed,vocab)
    
    

#### Réduction de dimensions: PCA


from sklearn.decomposition import PCA
import numpy as np


def ReductionDim(X,n):
    pca = PCA(n_components = n)
    pca.fit(X)
    Y = X.copy()
    Transformé = pca.transform(Y)
    return Transformé,pca



### Test de la vectorisaion


def TestPCA():
    Vec = Vectorisation()
    V = Vec.copy()
    n = 30
    return ReductionDim(V,n)



#################################################################################################################################

### Annexe


##### Vocabulaire



def Vocabulary():   # Vectorisation de l'ensemble du corpus

    def EnsembleDesTextes():   # La liste de tous les textes
        corpus = []
        adresseDocAmb = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Data Set"
        LAmbiances = os.listdir(adresseDocAmb)
        
        for ambiance in LAmbiances:
            PtrAmbFold = os.listdir("C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Data Set" + "\\" + ambiance)
            
            for PtrFile in PtrAmbFold:
    
                AdresseText = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Data Set" + "\\" + ambiance + "\\" + PtrFile
                vec = Vectorize(AdresseText)
                corpus.append(vec)
    
        return corpus
    

    def tokenize1txt(brut):

        word_punct_tokenizer = WordPunctTokenizer()
        wordsList = word_punct_tokenizer.tokenize(brut)
        wordsList = [word for word in wordsList if word not in ["'",".",":",";",'."','?"',",","?","!","(",")","-",'"'] ]
        return(wordsList)
        


    
    def EnsembleMots(texte): #type(texte)=list(str)
        Dico=Counter(texte)   # Le dico
        Différents = []
        while Dico != Counter():
            Différents.append(Dico.popitem()[0])
        return sorted(Différents)
    
    c1 = cl.clock()
    Corp = EnsembleDesTextes()
    brut = " "
    for texte in Corp:
        brut = brut + " " + texte
    SimpleTot = tokenize1txt(brut)
    Vocabulaire = EnsembleMots(SimpleTot)
    
    c2 = cl.clock()
    print(c2-c1)
    return Vocabulaire
    
def NvTexte(texte,AdresseCible):
    fichiertxt = open(AdresseCible,mode="x")
    fichiertxt.write(texte)
    fichiertxt.close()
    
def NvGROSTexte(Tableau,AdresseCible):
    fichiertxt = open(AdresseCible,mode="x")
    fichiertxt.write("[")
    
    for i in range(len(Tableau)):
        
        if i != 0:
            fichiertxt.write(",")
        fichiertxt.write("[")
        
        for j in range(len(Tableau[i])):
            if j != 0:
                fichertxt.write(",")
            try:
                fichiertxt.write(str(Tableau[i,j]))
            except:
                return i,j
        
        fichiertxt.write("]")
    
    fichiertxt.write("]")
    fichiertxt.close()
    
"""
vocab = Vocabulary()
AdresseCible = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Vocabulaire.txt"
NvTexte(str(vocab),AdresseCible)
"""    

#################################################################################################################################

##### WebScraping: Constitution de la BDD d'ambiances

"""
Ces fonctions sont notamment adaptées à l'interface de Thierry Bécart.
On récupère les codes Source (codeS) de Descriptionari et on en extrait les textes.
On a effectivement remarqu" que sur ce site, les codes sources sont précédés de "data-writing-prompt=\"0\"><p>" et finissent par <p>

A chaque fois qu'un texte est récupéré, il est enregistré comme un fichier txt sur un dossier cible préexistant sous le format Ambiance[k]

"""

# Traduction d'un fichier en str
def readtxt(adresse):
        
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
    folder = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Textes Ambiance" # L'adresse du dossier source
    adresse = folder + '\\' + Ambiance + "\\" + Ambiance + ".txt"   #Adresse du fichier texte contenant le code source
    return diviseTexte(readtxt(adresse))


# Création des fichiers Textes extraits des codes source
def Ecrituretxt(Ambiance,numero,texte):
    folder = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Textes Ambiance"
    adresse = folder + '\\' + Ambiance + "\\" + Ambiance+str(numero)+".txt"
    fichiertxt = open(adresse,mode="x")
    fichiertxt.write(texte)
    fichiertxt.close()
    # L'adresse finale est ~\Ambiance\Ambiance(numéro).txt
    
# Crée tous les fichiers textes d'une ambiance donnée
def AjoutAmbiance(Ambiance):
    c1 = cl.clock()
    corpusAmbiance = Textes(Ambiance)
    for i in range(len(corpusAmbiance)):
        Ecrituretxt(Ambiance,i,corpusAmbiance[i])
    c2 = cl.clock()
    print(c2-c1)

# Renvoie le nombre de fichiers textes d'une ambiance donnée
def NTextAmbiances(LAmbiances):
    LNtextes = []
    for Ambiance in LAmbiances:
        corpusAmbiance = Textes(Ambiance)
        LNtextes.append(len(corpusAmbiance))
    return LNtextes

# Renvoie une liste des listes de textes joints  une même ambiance
def Corpus(LAmbiances):
    corpus = []
    for Ambiance in LAmbiances:
        corpusAmbiance = Textes(Ambiance)
        corpus.append(corpusAmbiance)
    return corpus


# AdresseVocab = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Vocabulaire.txt"
# vocab = eval(readtxt(AdresseVocab))

AdresseGitThierry = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\Depotgit\pact35"
AdresseGitThiziri = ""
user = input("Utilisateur est : Thierry (0)? Thiziri(1)? ")
if user == "0":
    AdresseGit = AdresseGitThierry
elif user == "1":
    AdresseGit = AdresseGitThiziri



