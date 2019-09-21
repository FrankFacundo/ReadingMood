Last login: Fri May 10 17:08:45 on ttys000
MacBook-Pro-de-Thiziri:~ NAIT$ ls
AnacondaProjects		Public
Applications			TP info numpy
DM 1 IPT PSI.ipynb		TP1 IPT PSI.ipynb
DM Thiziri info			TP2 IPT PSI.ipynb
Desktop				Untitled.ipynb
Documents			Untitled1.ipynb
Downloads			anaconda
Library				classification
Movies				eclipse-workspace
Music				nltk_data
PACT - Vectorization.ipynb	regWinMac.rw3
Pictures			resultats acetone.rw3
MacBook-Pro-de-Thiziri:~ NAIT$ cd classification/pact35/modules/
MacBook-Pro-de-Thiziri:modules NAIT$ ls
Android		Classification	RaspberryPi	TestIntegration
BaseDeDonnees	ComClientServ	SES
MacBook-Pro-de-Thiziri:modules NAIT$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   ../.DS_Store
	modified:   .DS_Store
	modified:   Classification/.DS_Store
	modified:   ../rapport/.DS_Store

no changes added to commit (use "git add" and/or "git commit -a")
MacBook-Pro-de-Thiziri:modules NAIT$ git add .
MacBook-Pro-de-Thiziri:modules NAIT$ git commit-m "Rapport modifié"
git: 'commit-m' is not a git command. See 'git --help'.

Did you mean this?
	commit-tree
MacBook-Pro-de-Thiziri:modules NAIT$ git commit -m "Rapport modifié"
[master 532f512] Rapport modifié
 2 files changed, 0 insertions(+), 0 deletions(-)
MacBook-Pro-de-Thiziri:modules NAIT$ git pull
remote: Enumerating objects: 1107, done.
remote: Counting objects: 100% (1107/1107), done.
remote: Compressing objects: 100% (385/385), done.
remote: Total 1107 (delta 563), reused 1074 (delta 545)
Receiving objects: 100% (1107/1107), 239.25 MiB | 15.45 MiB/s, done.
Resolving deltas: 100% (563/563), done.
From gitlab.enst.fr:pact/2018-2019/pact35
   562e0b6..8730289  master     -> origin/master
error: Your local changes to the following files would be overwritten by merge:
	.DS_Store
Please commit your changes or stash them before you merge.
Aborting
MacBook-Pro-de-Thiziri:modules NAIT$ git status
On branch master
Your branch and 'origin/master' have diverged,
and have 1 and 58 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   ../.DS_Store
	modified:   ../rapport/.DS_Store

no changes added to commit (use "git add" and/or "git commit -a")
MacBook-Pro-de-Thiziri:modules NAIT$ git pull
error: Your local changes to the following files would be overwritten by merge:
	.DS_Store
Please commit your changes or stash them before you merge.
Aborting
MacBook-Pro-de-Thiziri:modules NAIT$ git add .
MacBook-Pro-de-Thiziri:modules NAIT$ git commit -m "Merging"
On branch master
Your branch and 'origin/master' have diverged,
and have 1 and 58 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)
Changes not staged for commit:
	modified:   ../.DS_Store
	modified:   ../rapport/.DS_Store

no changes added to commit
MacBook-Pro-de-Thiziri:modules NAIT$ git checkout
M	.DS_Store
M	rapport/.DS_Store
Your branch and 'origin/master' have diverged,
and have 1 and 58 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)
MacBook-Pro-de-Thiziri:modules NAIT$ git branch
* master
MacBook-Pro-de-Thiziri:modules NAIT$ git merge
error: Your local changes to the following files would be overwritten by merge:
	.DS_Store
Please commit your changes or stash them before you merge.
Aborting
MacBook-Pro-de-Thiziri:modules NAIT$ git status
On branch master
Your branch and 'origin/master' have diverged,
and have 1 and 58 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   ../.DS_Store
	modified:   ../rapport/.DS_Store

no changes added to commit (use "git add" and/or "git commit -a")
MacBook-Pro-de-Thiziri:modules NAIT$ git add .
MacBook-Pro-de-Thiziri:modules NAIT$ git commit -m "Merging"
On branch master
Your branch and 'origin/master' have diverged,
and have 1 and 58 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)
Changes not staged for commit:
	modified:   ../.DS_Store
	modified:   ../rapport/.DS_Store

no changes added to commit
MacBook-Pro-de-Thiziri:modules NAIT$ git show
commit 532f512fd1089b1556a3ef25909ef2c34a07e5c3
Author: Thiziri NAIT SAADA <thiziri.naitsaada@telecom-paristech.fr>
Date:   Fri May 10 17:10:20 2019 +0200

    Rapport modifié

diff --git a/modules/.DS_Store b/modules/.DS_Store
index 8503721..a8b2e7a 100644
Binary files a/modules/.DS_Store and b/modules/.DS_Store differ
diff --git a/modules/Classification/.DS_Store b/modules/Classification/.DS_Store
index 68fa3cd..116b70d 100644
Binary files a/modules/Classification/.DS_Store and b/modules/Classification/.DS_Store differ
MacBook-Pro-de-Thiziri:modules NAIT$ git pull
error: Your local changes to the following files would be overwritten by merge:
	.DS_Store
Please commit your changes or stash them before you merge.
Aborting
MacBook-Pro-de-Thiziri:modules NAIT$ pwd
/Users/NAIT/classification/pact35/modules
MacBook-Pro-de-Thiziri:modules NAIT$ cd Classification/
MacBook-Pro-de-Thiziri:Classification NAIT$ git pull
error: Your local changes to the following files would be overwritten by merge:
	.DS_Store
Please commit your changes or stash them before you merge.
Aborting
MacBook-Pro-de-Thiziri:Classification NAIT$ git branch
* master
MacBook-Pro-de-Thiziri:Classification NAIT$ git checkout thiziri
error: pathspec 'thiziri' did not match any file(s) known to git.
MacBook-Pro-de-Thiziri:Classification NAIT$ git branch
* master
MacBook-Pro-de-Thiziri:Classification NAIT$ git status
On branch master
Your branch and 'origin/master' have diverged,
and have 1 and 58 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   ../../.DS_Store
	modified:   ../../rapport/.DS_Store

no changes added to commit (use "git add" and/or "git commit -a")
MacBook-Pro-de-Thiziri:Classification NAIT$ cd ../../rapport/
MacBook-Pro-de-Thiziri:rapport NAIT$ ls
README.adoc			proposition
References.adoc			rapportPAN2Android
annexes				rapportPAN2SES
architecture			rapportPan2Classification
images				scenario
organisation
MacBook-Pro-de-Thiziri:rapport NAIT$ git add .
MacBook-Pro-de-Thiziri:rapport NAIT$ git commit -m'Merging'
[master 44012f5] Merging
 1 file changed, 0 insertions(+), 0 deletions(-)
MacBook-Pro-de-Thiziri:rapport NAIT$ git pull
error: Your local changes to the following files would be overwritten by merge:
	.DS_Store
Please commit your changes or stash them before you merge.
Aborting
MacBook-Pro-de-Thiziri:rapport NAIT$ cd
MacBook-Pro-de-Thiziri:~ NAIT$ ls
cAnacondaProjects		Public
Applications			TP info numpy
DM 1 IPT PSI.ipynb		TP1 IPT PSI.ipynb
DM Thiziri info			TP2 IPT PSI.ipynb
Desktop				Untitled.ipynb
Documents			Untitled1.ipynb
Downloads			anaconda
Library				classification
Movies				eclipse-workspace
Music				nltk_data
PACT - Vectorization.ipynb	regWinMac.rw3
Pictures			resultats acetone.rw3
MacBook-Pro-de-Thiziri:~ NAIT$ git clone git@gitlab.enst.fr:pact/2018-2019/pact35.git
Cloning into 'pact35'...
remote: Enumerating objects: 235, done.
remote: Counting objects: 100% (235/235), done.
remote: Compressing objects: 100% (89/89), done.
remote: Total 10171 (delta 115), reused 235 (delta 115)
Receiving objects: 100% (10171/10171), 678.37 MiB | 15.35 MiB/s, done.
Resolving deltas: 100% (4192/4192), done.
Checking out files: 100% (1092/1092), done.
MacBook-Pro-de-Thiziri:~ NAIT$ ls
AnacondaProjects		TP info numpy
Applications			TP1 IPT PSI.ipynb
DM 1 IPT PSI.ipynb		TP2 IPT PSI.ipynb
DM Thiziri info			Untitled.ipynb
Desktop				Untitled1.ipynb
Documents			anaconda
Downloads			classification
Library				eclipse-workspace
Movies				nltk_data
Music				pact35
PACT - Vectorization.ipynb	regWinMac.rw3
Pictures			resultats acetone.rw3
Public
MacBook-Pro-de-Thiziri:~ NAIT$ cd pact35/
MacBook-Pro-de-Thiziri:pact35 NAIT$ ls
README.md	modules		rapport
MacBook-Pro-de-Thiziri:pact35 NAIT$ cd modules/
MacBook-Pro-de-Thiziri:modules NAIT$ ls
Android		Classification	RaspberryPi	TestIntegration
BaseDeDonnees	ComClientServ	SES
MacBook-Pro-de-Thiziri:modules NAIT$ cd BaseDeDonnees/
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ open ..
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ classifier.py
-bash: classifier.py: command not found
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ python classifier.py
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
Traceback (most recent call last):
  File "classifier.py", line 293, in <module>
    Text = sys.argv[1]
IndexError: list index out of range
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ cat classifier.py


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



fileSW=open("stopWords.txt","r")
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


adressePCA = "pca"
pca = recuperation(adressePCA)

def ReductionDim(pca,X):
    Y = X.copy()
    Transforme = pca.transform(Y)
    return Transforme




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

"""
Paramètres optimaux choisis:

# p = 0.8 et C = 89
BinaryFolder = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\BinaryFiles"
DirModelSVC = BinaryFolder + "\\modelSVC"
modelSVC = recuperation(DirModelSVC)

"""


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


#############################################
#############################################



# Vocabulaire: Vérifier le chemin du fichier du vocabulaire dans gitlab

AdresseVocab = "Vocabulaire.txt"
def readtxt(adresse):
    with open(adresse,"r") as file:
        s = file.read()
    return s

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

    BinaryFolder = ""
    DirModelSVC = BinaryFolder + "modelSVC"
    modelSVC = recuperation(DirModelSVC)
    VecteurNormalise=np.array([Vecteur])
    VecteurNormalise.reshape(-1,1)
    result = modelSVC.predict(VecteurNormalise)
    return ident[result[0]]

if __name__ == "__main__":
    import sys
    Text = sys.argv[1]
    print(stringtoMood(Text))
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ vim classifier.py
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ python classifier.py
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
Traceback (most recent call last):
  File "classifier.py", line 293, in <module>
    Text = sys.argv[1]
IndexError: list index out of range
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ python classifier.py "Once upon a time, Irina told me she wa happy to be my friend and I was excited by the news."
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator LinearSVC from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
Gunshot
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ python classifier.py "Once upon a time, Irina told me she was happy to be here. She is nice, kind and funny. I'd like to have fun with her she is kind."
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator LinearSVC from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
Calm
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ vim classifier.py
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ ls
BasePAN1.adoc		app.yaml		main_test.py		requirements.txt	uploadAndDownload.py
Sons			bufferForUpload.txt	modelSVC		researchDataBase.py
TestClassifior.ipynb	classifier.py		moduleBDD.adoc		src
Vocabulaire.txt		insertData.py		opener.py		stopWords.txt
WebCrawlerKhaled.ipynb	main.py			pca			traiteur.py
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ vim TestClassifior.ipynb 
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ cdMacBook-Pro-de-Thiziri:modules NAIT$ ls TestIntegration/
Rapports et autres archives de travail	src
MacBook-Pro-de-Thiziri:modules NAIT$ cd src
-bash: cd: src: No such file or directory
MacBook-Pro-de-Thiziri:modules NAIT$ ls
Android		BaseDeDonnees	Classification	ComClientServ	RaspberryPi	SES		TestIntegration
MacBook-Pro-de-Thiziri:modules NAIT$ cd TestIntegration/
MacBook-Pro-de-Thiziri:TestIntegration NAIT$ ls
Rapports et autres archives de travail	src
MacBook-Pro-de-Thiziri:TestIntegration NAIT$ cd src/
MacBook-Pro-de-Thiziri:src NAIT$ ls
JavaTest	PythonTest	ambianceMp3
MacBook-Pro-de-Thiziri:src NAIT$ cd PythonTest/
MacBook-Pro-de-Thiziri:PythonTest NAIT$ ls
Atmosphere.py		BinaryFiles		BookToDownload.pyc	Classifior.pyc		__pycache__
Atmosphere.pyc		BookToDownload.py	Classifior.py		TestClassifior.py	test.py
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim Classifior.py
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim test.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim Classifior.py
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ open ..
MacBook-Pro-de-Thiziri:PythonTest NAIT$ cd ..
MacBook-Pro-de-Thiziri:src NAIT$ cd ..
MacBook-Pro-de-Thiziri:TestIntegration NAIT$ pwd
/Users/NAIT/pact35/modules/TestIntegration
MacBook-Pro-de-Thiziri:TestIntegration NAIT$ cd ..
MacBook-Pro-de-Thiziri:modules NAIT$ cd BaseDeDonnees/
MacBook-Pro-de-Thiziri:BaseDeDonnees NAIT$ cd Sons/
MacBook-Pro-de-Thiziri:Sons NAIT$ open ..
MacBook-Pro-de-Thiziri:Sons NAIT$ cd ../../..
MacBook-Pro-de-Thiziri:pact35 NAIT$ pwd
/Users/NAIT/pact35
MacBook-Pro-de-Thiziri:pact35 NAIT$ cd modules/
MacBook-Pro-de-Thiziri:modules NAIT$ ls
Android		BaseDeDonnees	Classification	ComClientServ	RaspberryPi	SES		TestIntegration
MacBook-Pro-de-Thiziri:modules NAIT$ cd TestIntegration/
MacBook-Pro-de-Thiziri:TestIntegration NAIT$ ls
Rapports et autres archives de travail	src
MacBook-Pro-de-Thiziri:TestIntegration NAIT$ cd src/
MacBook-Pro-de-Thiziri:src NAIT$ ls
JavaTest	PythonTest	ambianceMp3
MacBook-Pro-de-Thiziri:src NAIT$ cd PythonTest/
MacBook-Pro-de-Thiziri:PythonTest NAIT$ ls
Atmosphere.py		BinaryFiles		BookToDownload.pyc	Classifior.pyc		__pycache__
Atmosphere.pyc		BookToDownload.py	Classifior.py		TestClassifior.py	test.py
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
  File "TestClassifior.py", line 59
    nbText +=1
             ^
TabError: inconsistent use of tabs and spaces in indentation
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
  File "TestClassifior.py", line 59
    nbText +=1
             ^
TabError: inconsistent use of tabs and spaces in indentation
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
Traceback (most recent call last):
  File "TestClassifior.py", line 5, in <module>
    import classifierAlgo
ModuleNotFoundError: No module named 'classifierAlgo'
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
Traceback (most recent call last):
  File "TestClassifior.py", line 5, in <module>
    import classifier
  File "/Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/classifier.py", line 102, in <module>
    fileSW=open("stopWords.txt","r")
FileNotFoundError: [Errno 2] No such file or directory: 'stopWords.txt'
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
Traceback (most recent call last):
  File "TestClassifior.py", line 85, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 57, in testPredictionPerMood
    for element in os.listdir('/test/ambiance'):
FileNotFoundError: [Errno 2] No such file or directory: '/test/ambiance'
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
Traceback (most recent call last):
  File "TestClassifior.py", line 85, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 61, in testPredictionPerMood
    if classifier.stringtoMood(text)==ambiance:
  File "/Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/classifier.py", line 275, in stringtoMood
    wordsList = tokenizeSTR(Text)
  File "/Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/classifier.py", line 52, in tokenizeSTR
    wordsList = word_punct_tokenizer.tokenize(s)
  File "/Users/NAIT/anaconda/lib/python3.6/site-packages/nltk/tokenize/regexp.py", line 136, in tokenize
    return self._regexp.findall(text)
TypeError: expected string or bytes-like object
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
Traceback (most recent call last):
  File "TestClassifior.py", line 86, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 62, in testPredictionPerMood
    if classifier.stringtoMood(text)==ambiance:
  File "/Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/classifier.py", line 275, in stringtoMood
    wordsList = tokenizeSTR(Text)
  File "/Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/classifier.py", line 52, in tokenizeSTR
    wordsList = word_punct_tokenizer.tokenize(s)
  File "/Users/NAIT/anaconda/lib/python3.6/site-packages/nltk/tokenize/regexp.py", line 136, in tokenize
    return self._regexp.findall(text)
TypeError: expected string or bytes-like object
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
Traceback (most recent call last):
  File "TestClassifior.py", line 86, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 62, in testPredictionPerMood
    if (classifier.stringtoMood(text)) is ambiance:
  File "/Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/classifier.py", line 275, in stringtoMood
    wordsList = tokenizeSTR(Text)
  File "/Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/classifier.py", line 52, in tokenizeSTR
    wordsList = word_punct_tokenizer.tokenize(s)
  File "/Users/NAIT/anaconda/lib/python3.6/site-packages/nltk/tokenize/regexp.py", line 136, in tokenize
    return self._regexp.findall(text)
TypeError: expected string or bytes-like object
MacBook-Pro-de-Thiziri:PythonTest NAIT$ open pyzo
The file /Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/pyzo does not exist.
MacBook-Pro-de-Thiziri:PythonTest NAIT$ open -a pyzo
Unable to find application named 'pyzo'
MacBook-Pro-de-Thiziri:PythonTest NAIT$ open -a idle
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
Traceback (most recent call last):
  File "TestClassifior.py", line 86, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 62, in testPredictionPerMood
    if (classifier.stringtoMood(text)) is ambiance:
  File "/Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/classifier.py", line 275, in stringtoMood
    wordsList = tokenizeSTR(Text)
  File "/Users/NAIT/pact35/modules/TestIntegration/src/PythonTest/classifier.py", line 52, in tokenizeSTR
    wordsList = word_punct_tokenizer.tokenize(s)
  File "/Users/NAIT/anaconda/lib/python3.6/site-packages/nltk/tokenize/regexp.py", line 136, in tokenize
    return self._regexp.findall(text)
TypeError: expected string or bytes-like object
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
Traceback (most recent call last):
  File "TestClassifior.py", line 87, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 62, in testPredictionPerMood
    text = source.read()
  File "/Users/NAIT/anaconda/lib/python3.6/codecs.py", line 321, in decode
    (result, consumed) = self._buffer_decode(data, self.errors, final)
UnicodeDecodeError: 'utf-8' codec can't decode byte 0xc9 in position 561: invalid continuation byte
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
.DS_Store
Traceback (most recent call last):
  File "TestClassifior.py", line 88, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 63, in testPredictionPerMood
    text = source.read()
  File "/Users/NAIT/anaconda/lib/python3.6/codecs.py", line 321, in decode
    (result, consumed) = self._buffer_decode(data, self.errors, final)
UnicodeDecodeError: 'utf-8' codec can't decode byte 0xc9 in position 561: invalid continuation byte
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
.DS_Store
Traceback (most recent call last):
  File "TestClassifior.py", line 88, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 62, in testPredictionPerMood
    with open(element, "r", utf-8) as file:
NameError: name 'utf' is not defined
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
.DS_Store
Traceback (most recent call last):
  File "TestClassifior.py", line 88, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 63, in testPredictionPerMood
    text = file.read()
  File "/Users/NAIT/anaconda/lib/python3.6/codecs.py", line 321, in decode
    (result, consumed) = self._buffer_decode(data, self.errors, final)
UnicodeDecodeError: 'utf-8' codec can't decode byte 0xc9 in position 561: invalid continuation byte
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
.DS_Store
Traceback (most recent call last):
  File "TestClassifior.py", line 88, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 62, in testPredictionPerMood
    with open(element, "rb", encoding = "utf-8") as file:
ValueError: binary mode doesn't take an encoding argument
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
.DS_Store
Traceback (most recent call last):
  File "TestClassifior.py", line 89, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 63, in testPredictionPerMood
    text = file.read()
  File "/Users/NAIT/anaconda/lib/python3.6/codecs.py", line 321, in decode
    (result, consumed) = self._buffer_decode(data, self.errors, final)
UnicodeDecodeError: 'utf-8' codec can't decode byte 0xc9 in position 561: invalid continuation byte
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 
MacBook-Pro-de-Thiziri:PythonTest NAIT$ python TestClassifior.py '/Users/NAIT/Documents/Textes Ambiance'
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
/Users/NAIT/anaconda/lib/python3.6/site-packages/sklearn/base.py:315: UserWarning: Trying to unpickle estimator PCA from version pre-0.18 when using version 0.18.1. This might lead to breaking code or invalid results. Use at your own risk.
  UserWarning)
[nltk_data] Downloading package wordnet to /Users/NAIT/nltk_data...
[nltk_data]   Package wordnet is already up-to-date!
<class 'str'>
.DS_Store
Traceback (most recent call last):
  File "TestClassifior.py", line 90, in <module>
    print(testPredictionPerMood(testDataDirectoryPath))
  File "TestClassifior.py", line 63, in testPredictionPerMood
    text = file.read()
  File "/Users/NAIT/anaconda/lib/python3.6/codecs.py", line 321, in decode
    (result, consumed) = self._buffer_decode(data, self.errors, final)
UnicodeDecodeError: 'utf-8' codec can't decode byte 0xc9 in position 561: invalid continuation byte
MacBook-Pro-de-Thiziri:PythonTest NAIT$ vim TestClassifior.py 

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


def testPredictionPerMood(testDataDirectoryPath):
    listRate = []
    compteur = 0
    nbText = 0
    listAmbiance = ["Administration", "Anger", "Attic", "Bathroom", "Bedroom", "Calm", "Car", "Castle", "Cave", "Cemetery", "Church", "Circus", "Company", "CountryTown", "Desert", "Fear", "Field", "Fight", "Fog", "Forest", "Garden", "Gunshot", "Gym", "Happy", "Hospital", "Industry", "Jail", "Kitchens", "Library", "LivingRoom", "Love", "Meadow", "Mine", "Mountain", "Ocean", "Plane", "Rain", "Sad", "School", "Snow", "Sun", "Thunder", "TrainStation", "Wind"]
    #for ambiance in listAmbiance :
       #print(type(ambiance))
        #for element in os.listdir(testDataDirectoryPath):
            print(element)
            c1 = cl.clock()
            nbText +=1
            with open(element, "r", encoding = "utf-8") as file:
-- INSERT --

