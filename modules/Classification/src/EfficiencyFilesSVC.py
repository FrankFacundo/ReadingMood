import time as cl
import random as rd
import pickle
# Arbres binaires
import math as m


def stockageTree(T):
    L = T.tr
    M=[]
    for n in range(len(L)):
        if L[n]!=None:
            M.append((L[n],n))
    return M


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



ident=['Administration', 'Anger', 'Attic', 'Bathroom', 'Bedroom', 'Calm', 'Car', 'Castle', 'Cave', 'Cemetery', 'Church', 'Circus', 'Company', 'CountryTown', 'Desert', 'Fear', 'Field', 'Fight', 'Fog', 'Forest', 'Garden', 'Gunshot', 'Gym', 'Happy', 'Hospital', 'Industry', 'Jail', 'Kitchens', 'Library', 'LivingRoom', 'Love', 'Meadow', 'Mine', 'Mountain', 'Ocean', 'Plane', 'Rain', 'Sad', 'School', 'Snow', 'Sun', 'Thunder', 'TrainStation', 'Wind']


class Tree:
    """
    * Un arbre arrayList où on fait tout
    * La racine
    * La position courante
    * L'élément courant
    * Fils gauche
    * Fils droit
    """
    
    
    def __init__(self):
        self.tr = []
        self.racine = None
        self.profondeur = 0
        self.pos = None
        self.actual = None



    def __vide__(self):
        if self.racine == None:
            return True
        else:
            return False
            
    def __approfondit__(self):
        for k in range(len(self.tr)):
            self.tr.append(None)
        self.profondeur += 1

    def __ajoutD__(self,elem):
        newpos = 2*self.pos+ 2
        while newpos >=  len(self.tr):
            self.__approfondit__()
        self.tr[newpos] = elem
        self.pos = newpos
        
    def __ajoutG__(self,elem):
        newpos = 2*self.pos +1
        while newpos >=  len(self.tr):
            self.__approfondit__()
        self.tr[newpos] = elem
        self.pos = newpos    
            
    def ajout(self,elem,dir):
        if self.__vide__():
            self.racine = elem
            (self.tr).append(self.racine)
            self.pos = 0
            self.__actualisePeople__()
        else:
            if dir == 'd':
                self.__ajoutD__(elem)
                self.__actualisePeople__()
            elif dir == 'g':
                self.__ajoutG__(elem)
                self.__actualisePeople__()

            
    def __estFeuille__(self):
        return ((2*self.pos + 2 >= len(self.tr)) or (self.tr[self.pos*2+2] == None and self.tr[self.pos*2 +1] == None))
        
        
    
            
    def __retireFeuille__(self):
        if __estFeuille__():
            self.tr[self.pos] = None
            self.pos = self.pos // 2
            self.actual = self.tr[self.pos]
        else:
            raise Exception('You must manage the children of this nodle before suppressing this element.')
    
    def __actualisePeople__(self):
        self.actual = self.tr[self.pos]
        if self.__estFeuille__():

            self.fGauche = None
            self.fDroit = None
        else:
            self.fGauche = self.tr[2*self.pos +1]
            self.fDroit = self.tr[2*self.pos +2]


    
    def remonte(self):
        
        if self.pos==0:
            # print("Je suis à la racine, je ne fais rien")
            return None
            
        self.pos = (self.pos-1) // 2
        posfG = 2*self.pos +1
        posfD = 2*self.pos +2
        self.__actualisePeople__()
        
    def descend(self,dir):
        
        if self.__estFeuille__():
            # print("Nous sommes à une feuille, je ne fais rien")
            return None
        if dir == 'd':
            self.__descendD__()
        if dir == 'g':
            self.__descendG__()
        
    def __descendD__(self):
        self.pos = self.pos * 2 + 2
        self.__actualisePeople__()    
            
    def __descendG__(self):
        self.pos = self.pos * 2  +1
        self.__actualisePeople__()
        
    def Top(self):
        self.pos = 0
        self.__actualisePeople__()

    


    
    def DeepParcours(self):
        L = []
        actions = []

        L.append(self.actual)
        actionsG = []
        LG=[]
        actionsD = []
        LD=[]

        if self.__estFeuille__():
            None
        else:
            actions.append("G")
            self.descend("g")
            if self.__estFeuille__():
                L.append(self.actual)
                actions.append("R")
                self.remonte()
            else:
                coupleG = self.DeepParcours()
                self.remonte()
                LG, actionsG = coupleG
                actionsG.append("R")
            actions = actions + actionsG
            L = L + LG
            
            if self.tr[self.pos * 2 + 2] != None:
                actions.append("D")
                self.descend("d")
                if self.__estFeuille__():
                    LD=[self.actual]
                    actionsD=["R"]
                    self.remonte()
                else:
                    coupleD = self.DeepParcours()
                    self.remonte()
                    LD,actionsD = coupleD
                    actionsD.append("R")
                actions = actions + actionsG
                L = L + LD
                
        return L,actions
            

                
        
                
        return L,actions

    def __SeekDown__(self,posinit,elem):
        self.pos = posinit
        self.__actualisePeople__()
        L = []
        dermv = ""
        if self.__estFeuille__():
            return L
        desD = "D"
        desG = "G"
        if self.fGauche == None:
            L.append(desD)
            self.__descendD__()
        else:
            L.append(desG)
            self.__descendG__()
            
        while self.actual != elem or L!=[]:
            if self.__estFeuille__():
                dermv = L.pop()
                self.remonte()

            if dermv == desG:
                L.append(desD)
                self.__descendD__()
            else:
                L.append(desG)
                self.__descendG__()
        return L
    
    def rev(L):
        Lr = []
        for x in L:
            Lr.append(x)
    
    def SeekPath(self,elemi,elemf):
        posinit = 0
        while self.tr[posinit] != elem:
            posinit+=1        
        posfin = 0
        while self.tr[posfin] != elem:
            posfin+=1
        
        Li = self.__SeekDown__(0,elemi)
        Lf = self.__SeekDown__(0,elemf)
        k = 0
        if len(Lf) == 0:
            return rev(Li)
        if len(Lf)==0:
            return Lf
        while Li[k] == Lf[k]:
            Li.pop(0)
            Lf.pop(0)
            if Li==[]:
                return Lf
            if Lf==[]:
                return rev(Li)
        Li=rev(Li)
        return Li + Lf
    
    def __reinit__(self):
        self.tr = []
        self.racine = None
        self.profondeur = 0
        self.pos = None
        self.actual = None
    
    def stockageTree(self):
        L = self.tr

        M=[]
        for n in range(len(L)):
            if L[n]!=None:
                M.append((L[n],n))
        return M
    def pasteTree(self,Ltree):
        if Ltree == []:
            return None
        self.pos = 0
        taille=tailleTree(Ltree)
        L=[]
        for k in range(taille):
            L.append(None)
        for x in Ltree:
            L[x[1]]=x[0]
        self.tr = L
        self.__actualisePeople__()
         
    def copyTree(self,TreeI):
        TreeI.pos = 0
        TreeI.__actualisePeople__()            
        if TreeI.actual == None:
            self.Top()
            return None
        
        self.ajout()
        n=1
        PileH = []
        while n<3:

            self.tr[self.pos] = TreeI.actual
            
            if TreeI.__estFeuille__():
                self.remonte()
                TreeI.remonte()
                if self.pos == 0:
                    n+=1
            else:
                if self.tr[self.pos * 2 + 1] ==None:
                    TreeI.descend('g')
                    elem = TreeI.actual
                    self.ajout(elem[0],elem[1],'g')
                else:
                    TreeI.descend('d')
                    elem = TreeI.actual
                    self.ajout(elem[0],elem[1],'d')
                    
                
    def selectNeighMood(self,ambiance):
        posinit= 0
        while self.tr[posinit] == None or not(ambiance in self.tr[posinit]):
            posinit+=1


        self.pos = posinit
        self.__actualisePeople__()


        
        L=[]
        L.append(self.actual)
        self.descend('d')
        self.__actualisePeople__()
        L.append(self.actual)

        self.remonte()
        self.descend('g')
        self.__actualisePeople__()
        L.append(self.actual)

        self.remonte()
        self.remonte()
        self.__actualisePeople__()
        L.append(self.actual)

        M=[]
        for x in L:
            if x ==None:
                M.append(None)
            else:
                M.append(traductionINDtoMood(x))
        return M
        
    def copy(self):
        Tcopy = Tree()
        L = self.tr.copy()
        Tcopy.pos,Tcopy.tr = 0,L
        Tcopy.__actualisePeople__()
        return Tcopy

def creationTomega(tree):
    print(tree.pos)

    if tree.__estFeuille__():
        return tree.actual
        
    else:
        tree.descend('g')
        filsG = creationTomega(tree)
        tree.remonte()
        filsD = ""
        if tree.tr[tree.pos * 2 + 2] != None:
            tree.descend('d')
            filsD = creationTomega(tree)
            tree.remonte()
        tree.actual = tree.actual + filsG + filsD
        tree.tr[tree.pos] = tree.actual

        
        return tree.actual



def recherche(L,elem):
    for k in range(len(L)):
        if L[k] == elem:
            return k
            
def traductionMoodtoIND(Mood):
    # return recherche(ident,Mood)
    return Mood

def traductionINDtoMood(IND):
    # return ident[IND]
    return IND
    
    
def tailleTree(L):
    M=[]
    for x in L:
        M.append(x[1])
    print(M)
    Max = max(M)
    Taille = 2**(int(m.log2(Max)) +1)
    return Taille

def simplify(L):
    M = []
    for x in L:
        if not(x in M or x == None):
            M.append(x)
    return M


"""
L=[('Ambiances', 0), ('Sun', 1), ('Sad', 2), ('Happy', 3), ('Calm', 4), ('Fear', 5), ('Rage', 6), ('Agitation', 7), ('Love', 8), ('WindPlane', 9), ('BedroomBathroom', 10), ('Fog', 11), ('RainThunder', 12), ('FightAngerJail', 13), ('Gunshot', 14), ('CountryTown', 15), ('SchoolLibrary', 16), ('MountainSnowMine', 19), ('Church', 21), ('Cemetery', 23), ('Hospital', 29), ('Administration', 31), ('Foule', 32), ('MeadowFieldGarden', 39), ('CaveAttic', 47), ('Industry', 63), ('Gym', 65), ('LivingRoomKitchens', 66), ('Castle', 79), ('Ocean', 80), ('CarTrainStation', 127), ('Company', 128), ('Circus', 131), ('Forest', 159), ('Desert', 160)]
"""### LinearSVC:

from sklearn.svm import LinearSVC
from sklearn.datasets import make_classification
import random as rd
import pylab as pl
import numpy as np

# GZone 
# NMF


def entraineSVC(nX,nY,c):
    model = LinearSVC(C=c, class_weight=None, dual=False, fit_intercept=True,intercept_scaling=1, loss='squared_hinge', max_iter=1000,multi_class='ovr', penalty='l2', random_state=0, tol=1e-05, verbose=0)
    model.fit(nX,nY)
    return model
    
    
#C = la valeur de la marge (réel positif)
#dual = False     
#random_state has no impact
#tol donne l'erreur  10^-5 près
#verbose is for output

### Test et efficacité pré-PAN3

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


def efficSVC(X,ident,p0,p1,pas,iteration):  # C constant
    abs = np.linspace(p0,p1,pas)
    
    # On enlève les cas triviaux pathologiques 0 et 1
    if p0 == 0:
        abs = abs[1:]    
    if p1 == 1:
        abs = abs[:(pas-2)]
    res = []
    
    for p in abs:
        c1 = cl.clock()
        T=[]
        for k in range(iteration):
            nX, nY, nXn, nYn = PartitionHomogene(X,ident,p)
            T.append(test(nX, nY, nXn, nYn))
        res.append(moyenne(T))
        c2 = cl.clock()
        print("Pour la proportion p = ", p , ", on met un temps de ", (c2-c1) ," secondes")
    pl.plot(abs,res)
    pl.show()
    return abs,res
    
    
def efficSVCpara(Banque):   # Tracé de la surface de efficacité(p,c)
    p0 = 0
    p1 = 1
    pas = 11
    P = np.linspace(0,1,11)
    P = list(P)
    P = P[1:10]
    AbscisseP = []
    C = []
    for n in range(50,1,-1):
        cn = 1/n
        C.append(cn**2)
    for n in range(1,51):
        cn = n
        C.append(cn**2)
        
    

    
    
    for i in range(len(P)):             # Proportion prise dans la bibliohèque
        p = P[i]
        EnsemblePartitionP = Banque[i]
        AbscisseC = [] # A compléter
        
        
        for c in C: # C variation
            time1 = cl.clock()
            Z1 = []
            for (nX, nY, nXn, nYn) in EnsemblePartitionP:
                zi1 = testSVC(nX, nY, nXn, nYn,c)
                Z1.append(zi1)
            z1,incertitude1 = moyenne(Z1)
            AbscisseC.append((z1,incertitude1))
            time2 = cl.clock()
            print("Pour p = " + str(p) + " et c = " + str(c) + " on a pris un temps: " + str(time2-time1))
        

        AbscisseP.append(AbscisseC)
        

    return P,C,AbscisseP
    
   
def rechercheMax(Z):
    record = Z[0][0][0] 
    i,j = 0,0 
    for p in range(len(Z)):
        for c in range(len(Z[p])):
            if Z[p][c][0]>record:
                record = Z[p][c][0]
                i,j = p,c
                
    P = (i+1)/10
    if j <99:
        pos = list(range(100,1,-1))[j]
        C = 1/pos
    else:
        pos = j-99
        C = pos+1
    return record,P,C
    



### Efficacité avec la métrique des arbres

def scores(model,LX):
    Mat = model.decisionfunction(LX)
    return Mat

def select3best(L):
    Ltemp = L.copy()
    Ltemp.sort()
    B1=Ltemp[-1]
    B2=Ltemp[-2]
    B3=Ltemp[-3]
    k1= recherche(L,B1)
    k2= recherche(L,B2)
    k3 = recherche(L,B3)
    
    Lres =  [(B1,k1),(B2,k2),(B3,k3)]
    return Lres

def recherche(L,elem):
    for k in range(len(L)):
        if L[k] == elem:
            return k


def testSVC_TREE(tree,nX, nY, nXn, nYn,c):
    """
    * nX et nY les parties d'entraînement
    * nXn et nYn les parties de test
    """
    
    model = entraineSVC(nX,nY,c)
    n = len(nXn)
    if n == 0:
        return -1
    goal = 0
    failure = 0
    
    for k in range(n):
        prediction = model.predict([nXn[k]])
        Lbest = tree.selectNeighMood(ident[nYn[k]])
        Lbest = simplify(Lbest)
        if prediction==nYn[k]:
            goal += 1
        else:
            Moodprediction = ident[prediction]
            for x in Lbest:
                if Moodprediction in x:
                    goal+=0.7
            
    return goal/n


def efficSVC_TREE(Banque,tree):   # Tracé de la surface de efficacité(p,c)
    p0 = 0
    p1 = 1
    pas = 11
    P = np.linspace(0,1,11)
    P = list(P)
    P = P[1:10]
    AbscisseP = []
    C = []
    for n in range(50,1,-1):
        cn = 1/n
        C.append(cn**2)
    for n in range(1,51):
        cn = n
        C.append(cn**2)
        
    

    
    
    for i in range(len(P)):             # Proportion prise dans la bibliohèque
        p = P[i]
        EnsemblePartitionP = Banque[i]
        AbscisseC = [] # A compléter
        
        
        for c in C: # C variation
            time1 = cl.clock()
            Z1 = []
            for (nX, nY, nXn, nYn) in EnsemblePartitionP:
                zi1 = testSVC_TREE(tree,nX, nY, nXn, nYn,c)
                Z1.append(zi1)
            z1,incertitude1 = moyenne(Z1)
            AbscisseC.append((z1,incertitude1))
            time2 = cl.clock()
            print("Pour p = " + str(p) + " et c = " + str(c) + " on a pris un temps: " + str(time2-time1))
        

        AbscisseP.append(AbscisseC)
        

    return P,C,AbscisseP



### Main

L=[('Ambiances', 0), ('Sun', 1), ('Sad', 2), ('Happy', 3), ('Calm', 4), ('Fear', 5), ('Rage', 6), ('Agitation', 7), ('Love', 8), ('WindPlane', 9), ('BedroomBathroom', 10), ('Fog', 11), ('RainThunder', 12), ('FightAngerJail', 13), ('Gunshot', 14), ('CountryTown', 15), ('SchoolLibrary', 16), ('MountainSnowMine', 19), ('Church', 21), ('Cemetery', 23), ('Hospital', 29), ('Administration', 31), ('Foule', 32), ('MeadowFieldGarden', 39), ('CaveAttic', 47), ('Industry', 63), ('Gym', 65), ('LivingRoomKitchens', 66), ('Castle', 79), ('Ocean', 80), ('CarTrainStation', 127), ('Company', 128), ('Circus', 131), ('Forest', 159), ('Desert', 160)]
Lomega = [('AmbiancesSunHappyAgitationCountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibraryLoveCalmWindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOceanBedroomBathroomChurchSadFearFogCemeteryCaveAtticRainThunderRageFightAngerJailGunshotHospital', 0), ('SunHappyAgitationCountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibraryLoveCalmWindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOceanBedroomBathroomChurch', 1), ('SadFearFogCemeteryCaveAtticRainThunderRageFightAngerJailGunshotHospital', 2), ('HappyAgitationCountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibraryLove', 3), ('CalmWindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOceanBedroomBathroomChurch', 4), ('FearFogCemeteryCaveAtticRainThunder', 5), ('RageFightAngerJailGunshotHospital', 6), ('AgitationCountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibrary', 7), ('Love', 8), ('WindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOcean', 9), ('BedroomBathroomChurch', 10), ('FogCemeteryCaveAttic', 11), ('RainThunder', 12), ('FightAngerJail', 13), ('GunshotHospital', 14), ('CountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchens', 15), ('SchoolLibrary', 16), ('MountainSnowMineMeadowFieldGardenCastleForestDesertOcean', 19), ('Church', 21), ('CemeteryCaveAttic', 23), ('Hospital', 29), ('AdministrationIndustryCarTrainStationCompany', 31), ('FouleGymCircusLivingRoomKitchens', 32), ('MeadowFieldGardenCastleForestDesertOcean', 39), ('CaveAttic', 47), ('IndustryCarTrainStationCompany', 63), ('GymCircus', 65), ('LivingRoomKitchens', 66), ('CastleForestDesert', 79), ('Ocean', 80), ('CarTrainStation', 127), ('Company', 128), ('Circus', 131), ('Forest', 159), ('Desert', 160)]
Lf = [('AmbiancesSunHappyAgitationCountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibraryLoveCalmWindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOceanBedroomBathroomChurchSadFearFogCemeteryCaveAtticRainThunderRageFightAngerJailGunshotHospital', 0), ('HappyAgitationCountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibraryLoveCalmWindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOceanBedroomBathroomChurch', 1), ('Sun', 2), ('HappyAgitationCountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibraryLove', 3), ('CalmWindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOceanBedroomBathroomChurch', 4), ('FearFogCemeteryCaveAtticRainThunderRageFightAngerJailGunshotHospital', 5), ('Sad', 6), ('AgitationCountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibraryLove', 7), ('Happy', 8), ('WindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOceanBedroomBathroomChurch', 9), ('Calm', 10), ('FearFogCemeteryCaveAtticRainThunder', 11), ('RageFightAngerJailGunshotHospital', 12), ('AgitationCountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibrary', 15), ('Love', 16), ('WindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOcean', 19), ('BedroomBathroomChurch', 20), ('FogCemeteryCaveAtticRainThunder', 23), ('Fear', 24), ('FightAngerJailGunshotHospital', 25), ('Rage', 26), ('CountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchensSchoolLibrary', 31), ('Agitation', 32), ('MountainSnowMineMeadowFieldGardenCastleForestDesertOcean', 39), ('WindPlane', 40), ('Church', 41), ('BedroomBathroom', 42), ('FogCemeteryCaveAttic', 47), ('RainThunder', 48), ('FightAngerJail', 51), ('GunshotHospital', 52), ('CountryTownAdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchens', 63), ('SchoolLibrary', 64), ('MountainSnowMineMeadowFieldGardenCastleForestDesertOcean', 79), ('Church', 83), ('CemeteryCaveAttic', 95), ('Fog', 96), ('Hospital', 105), ('Gunshot', 106), ('AdministrationIndustryCarTrainStationCompanyFouleGymCircusLivingRoomKitchens', 127), ('CountryTown', 128), ('MeadowFieldGardenCastleForestDesertOcean', 159), ('MountainSnowMine', 160), ('CemeteryCaveAttic', 191), ('Hospital', 211), ('AdministrationIndustryCarTrainStationCompany', 255), ('FouleGymCircusLivingRoomKitchens', 256), ('MeadowFieldGardenCastleForestDesertOcean', 319), ('CaveAttic', 383), ('Cemetery', 384), ('IndustryCarTrainStationCompany', 511), ('Administration',512), ('GymCircusLivingRoomKitchens', 513), ('Foule', 514), ('CastleForestDesertOcean', 639), ('MeadowFieldGarden', 640), ('CaveAttic', 767), ('IndustryCarTrainStationCompany', 1023), ('CalmWindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOceanBedroomBathroomChurch', 1024), ('GymCircus', 1027), ('LivingRoomKitchens', 1028), ('CastleForestDesert', 1279), ('Ocean', 1280), ('CarTrainStationCompany', 2047), ('Industry', 2048), ('WindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOceanBedroomBathroomChurch', 2049), ('Calm', 2050), ('Circus', 2055), ('Gym', 2056), ('ForestDesert', 2559), ('Castle', 2560), ('CarTrainStation', 4095), ('Company', 4096), ('GymCircusLivingRoomKitchens', 4097), ('SchoolLibrary', 4098), ('WindPlaneMountainSnowMineMeadowFieldGardenCastleForestDesertOcean', 4099), ('Circus', 4111), ('Forest', 5119), ('Desert', 5120), ('CarTrainStation', 8191), ('Company', 8192), ('GymCircus', 8195), ('MountainSnowMineMeadowFieldGardenCastleForestDesertOcean', 8199), ('WindPlane', 8200), ('Circus', 16391), ('LivingRoomKitchens', 16392), ('MountainSnowMineMeadowFieldGardenCastleForestDesertOcean', 16399), ('Circus', 32783), ('MeadowFieldGardenCastleForestDesertOcean', 32799), ('MountainSnowMine', 32800), ('MeadowFieldGardenCastleForestDesertOcean', 65599), ('BedroomBathroomChurch', 65600), ('CastleForestDesertOcean', 131199), ('MeadowFieldGarden', 131200), ('Church', 131201), ('BedroomBathroom', 131202), ('CastleForestDesert', 262399), ('Church', 262403), ('ForestDesert', 524799), ('Ocean', 524800), ('Forest', 1049599), ('Desert', 1049600)]


T = Tree()
Tomega = Tree()
Tf = Tree()

T.pasteTree(L)
Tomega.pasteTree(Lomega)
Tf.pasteTree(Lf)

BinaryFolder = "C:\\Users\Thierry\Documents\TelecomParisTech\PACT3.5\BinaryFiles"
BanqueDir = BinaryFolder + "\\Banque"
Banque = recuperation(BanqueDir)

# resultats = efficSVC_TREE(Banque,T)



