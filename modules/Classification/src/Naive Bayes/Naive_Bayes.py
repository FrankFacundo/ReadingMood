# Import Library of Gaussian Naive Bayes model
from sklearn.naive_bayes import GaussianNB
import numpy as np
import pylab as pl

"""
Méthode de base pour utiliser Naive Bayes


#assigning predictor and target variables
x= np.array([[-3,7],[1,5], [1,2], [-2,0], [2,3], [-4,0], [-1,1], [1,1], [-2,2], [2,7], [-4,1], [-2,7]])
y = np.array([3, 3, 3, 3, 4, 3, 3, 4, 3, 4, 4, 4])

#Create a Gaussian Classifier
model = GaussianNB()

# Train the model using the training sets 
model.fit(x, y)

#Predict Output 
predicted= model.predict([[1,2],[3,4]])
print(predicted)

"""

import random as rd

# Partition de tous les vecteurs
# Y est la liste correspondante aux ambiances des textes de X 
def partitionproba(X,Y,p):
    #X et Y des listes de même taille
    nX = []
    nY = []
    nXn = []
    nYn = []
    n = len(X)
    if len(Y) != n:
        raise Exception
        
    samp = []
    sampn = []
    for k in range(n):
        w = rd.random()
        if w < p:
            nX.append(X[k])
            nY.append(Y[k])
            samp.append(k)
        else:
            nXn.append(X[k])
            nYn.append(Y[k])
            sampn.append(k)
    return nX, nY, samp, nXn, nYn, sampn



def effic1(X,Y,pas, iteration):
    P = np.linspace(0.4,0.8,pas)
    # P = P[1:(pas-1)]
    res = []
    for p in P:
        c1 = cl.clock()
        T=[]
        for k in range(iteration):
            nX, nY, samp, nXn, nYn, sampn = partitionproba(X,Y,p)
            T.append(test(nX, nY, nXn, nYn))
        res.append(moyenne(T))
        c2 = cl.clock()
        print(c2-c1)
    pl.plot(P,res)
    pl.show()
    return P,res


### Maintenant avec des échantilons d'entrainement homogènes.


def GenPermutation(n):  # Création d'une permutation de [0,1,2, ..., n-1]
    L1 = list(range(n))
    L = []
    m = n
    for k in range(n):
        nouv = rd.randint(0,m-1)
        m -= 1
        L.append(L1.pop(nouv))
    return L
    
        
        

def PartitionHomogene(X,ident,p):   
# Utiliser la fonction VectorisationAmb pour avoir X et ident
    deb = 0
    nX = []
    nY = []
    nXn = []
    nYn = []
    n = 0
    for couple in ident:
        nbTextes = couple[1]
        TailleSample = int(nbTextes * p)
        L = GenPermutation(nbTextes)
        
        for k in range(TailleSample):
            nX.append(X[ deb+L[k] ])
            nY.append(n)
        
        
        for k in range(TailleSample,nbTextes):
            nXn.append(X[ deb + L[k] ])
            nYn.append(n)
        
        deb += nbTextes
        n+=1
    return nX, nY, nXn, nYn
    
    
"""

def test(model, X, samp):
    instant = 0
    init = samp[instant]
    n = len(X)
    tab = []
    for k in range(n):
        if k == init:
            instant += 1
            init = samp[instant]
        else:
            tab.append(model.predict(X[k]))
    return tab
"""

def testNaiveBayes(nX, nY, nXn, nYn):
    #nX et nY les parties d'entraînement
    #nXn et nYn les parties de test
    model = GaussianNB()
    model.fit(nX,nY)
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

def moyenne(X):
    n = len(X)
    tot = 0
    for x in X:
        tot+=x
    moy = tot/n
    variance = 0
    for x in X:
        elem = (moy-x)**2
        variance += elem/n
    ecartType = variance*(1/2)
    incertitude = ecartType/(n**(1/2))
    return moy,incertitude

# It's measuring time    
def effic2(X,ident,pas,iteration):
    P = np.linspace(0.2,0.5,pas)
    P = P[1:(pas-1)]    # On enlève les cas triviaux 0 et 1
    res = []
    for p in P:
        c1 = cl.clock()
        T=[]
        for k in range(iteration):
            nX, nY, nXn, nYn = PartitionHomogene(X,ident,p)
            T.append(test(nX, nY, nXn, nYn))
        res.append(moyenne(T))
        c2 = cl.clock()
        print("Pour la proportion p = ", p , ", on met un temps de ", (c2-c1) ," secondes")
    ResP = []
    ResM = []
    for couple in res:
        ResM.append(couple[0]-couple[1])
        ResP.append(couple[0]+couple[1])
    pl.plot(P,ResP)
    pl.plot(P,ResM)
    pl.show()
    return P,res
  
  
def efficNaiveBayes(Banque):
    P = np.linspace(0,1,11)
    P = P[1:10]    # On enlève les cas triviaux 0 et 1
    res = []
    OrdonneeP = []
    for i in range(len(P)):
        c1 = cl.clock()
        EnsemblePartitionP = Banque[i]
        p =P[i]
        Z = []
        
        for (nX, nY, nXn, nYn) in EnsemblePartitionP:
            zi = testNaiveBayes(nX, nY, nXn, nYn)
            Z.append(zi)
        z,incertitude = moyenne(Z)
        OrdonneeP.append((z,incertitude))
        c2 = cl.clock()
        print("Pour la proportion p = ", p , ", on met un temps de ", (c2-c1) ," secondes")
    return P,OrdonneeP

"""
Pnb=np.array([ 0.1,  0.2,  0.3,  0.4,  0.5,  0.6,  0.7,  0.8,  0.9])
Ordnb = [(0.22977137176938373, 5.395383878198053e-06), (0.2682190760059612, 9.54615869806632e-06), (0.2860272224585282, 3.773455054670915e-06), (0.29243551587301586, 5.467334247130833e-06), (0.2960942158616577, 7.177618322174317e-06), (0.3033382789317508, 8.71179813962755e-06), (0.30550639134709934, 1.2842988584754836e-05), (0.30926470588235294, 1.7822293536062895e-05), (0.3094285714285714, 3.231802738092551e-05)]
"""
    
### Pour Test et Intégration

def entraine(nX,nY):
    model = GaussianNB()
    model.fit(nX,nY)
    return model

def classifieUnTxt(monTextetxt,model): # Doit prendre 1 texte et 1 model entrainé et doit renvoyer l'ambiance associée
    return None
    