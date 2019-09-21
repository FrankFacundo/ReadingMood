### CREATION DES ECHANTILLONS DE REFERENCE

import time as cl
import random as rd
import pickle



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
    
    
def GenEchantillons(n,p,Xt,ident):
    Xtot = []        
    c1=cl.clock()
    
    for k in range(n):
        nX,nY,nXn,nYn = PartitionHomogene(Xt,ident,p)
        Xtot.append((nX,nY,nXn,nYn))
    c2=cl.clock()
    print(c2-c1)
        
    return Xtot
    
def GenGamme(n,pas):
    Interv = np.linspace(0,1,pas)

    Banque=[]
      
    response = VectorisationAmb()
    Vec,ident = response
    X = []
    for vec in Vec:
        X.append(list(vec))
    Y = []

    for k in range(len(ident)):
        for i in range(ident[k][1]):
            Y.append(k)
    dim = 30
    Xt,pca = ReductionDim(X,dim)
    xt = []
    for a in Xt:
        xt.append(list(a))
    Xt = xt
    
    for p in Interv[1:(pas-1)]:
        Banque.append(GenEchantillons(n,p,Xt,ident))
    
    
    return Banque
        
"""
Banque = GenGamme(20,11)
"""

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
    print(c2-c1)
    return Banque

"""
BinaryFolder = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\BinaryFiles"
BanqueDir = BinaryFolder + "\\Banque"
Banque = recuperation(BanqueDir)
"""
