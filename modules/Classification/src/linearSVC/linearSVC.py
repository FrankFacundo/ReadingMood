### LinearSVC:

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
        AbscisseC = []
        
        
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
    

"""
P=[0.10000000000000001, 0.20000000000000001, 0.30000000000000004, 0.40000000000000002, 0.5, 0.59999999999999998, 0.70000000000000007, 0.80000000000000004, 0.90000000000000002]
C=[0.01, 0.010101010101010102, 0.01020408163265306, 0.010309278350515464, 0.010416666666666666, 0.010526315789473684, 0.010638297872340425, 0.010752688172043012, 0.010869565217391304, 0.01098901098901099, 0.011111111111111112, 0.011235955056179775, 0.011363636363636364, 0.011494252873563218, 0.011627906976744186, 0.011764705882352941, 0.011904761904761904, 0.012048192771084338, 0.012195121951219513,0.012345679012345678, 0.0125, 0.012658227848101266, 0.01282051282051282, 0.012987012987012988, 0.013157894736842105, 0.013333333333333334, 0.013513513513513514, 0.0136986301369863, 0.013888888888888888, 0.014084507042253521, 0.014285714285714285, 0.014492753623188406, 0.014705882352941176, 0.014925373134328358, 0.015151515151515152, 0.015384615384615385, 0.015625, 0.015873015873015872, 0.016129032258064516, 0.01639344262295082, 0.016666666666666666, 0.01694915254237288, 0.017241379310344827, 0.017543859649122806, 0.017857142857142856, 0.01818181818181818, 0.018518518518518517, 0.018867924528301886, 0.019230769230769232, 0.0196078431372549, 0.02, 0.02040816326530612, 0.020833333333333332, 0.02127659574468085, 0.021739130434782608, 0.022222222222222223, 0.022727272727272728, 0.023255813953488372, 0.023809523809523808, 0.024390243902439025, 0.025, 0.02564102564102564, 0.02631578947368421, 0.02702702702702703, 0.027777777777777776, 0.02857142857142857, 0.029411764705882353, 0.030303030303030304, 0.03125, 0.03225806451612903, 0.03333333333333333, 0.034482758620689655, 0.03571428571428571, 0.037037037037037035, 0.038461538461538464, 0.04, 0.041666666666666664, 0.043478260869565216, 0.045454545454545456, 0.047619047619047616, 0.05, 0.05263157894736842, 0.05555555555555555, 0.058823529411764705, 0.0625, 0.06666666666666667, 0.07142857142857142, 0.07692307692307693, 0.08333333333333333, 0.09090909090909091, 0.1, 0.1111111111111111, 0.125, 0.14285714285714285, 0.16666666666666666, 0.2, 0.25, 0.3333333333333333, 0.5, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99]
Z = recuperation('C:\\Users\\Thierry\\Documents\\Telecom ParisTech\\PACT 3.5\\Depotgit\\pact35\\modules\\Classification\\src\\linearSVC\\Résultats\\resultatscarSVC')
Z = recuperation('C:\\Users\\Thierry\\Documents\\Telecom ParisTech\\PACT 3.5\\Depotgit\\pact35\\modules\\Classification\\src\\linearSVC\\Résultats\\resultatsSVC')

# Format de Z: une liste de 9 listes de 198 couples de floats

C=[0.0004, 0.00041649312786339016, 0.00043402777777777775, 0.0004526935264825713, 0.00047258979206049145, 0.0004938271604938272, 0.0005165289256198347, 0.0005408328826392644, 0.0005668934240362811, 0.000594883997620464, 0.0006250000000000001,0.0006574621959237343, 0.0006925207756232686, 0.0007304601899196495, 0.0007716049382716049, 0.0008163265306122448, 0.0008650519031141869, 0.0009182736455463729, 0.0009765625, 0.0010405827263267429, 0.0011111111111111111, 0.0011890606420927466, 0.0012755102040816326, 0.0013717421124828531, 0.0014792899408284025, 0.0016, 0.001736111111111111, 0.0018903591682419658, 0.002066115702479339, 0.0022675736961451243, 0.0025000000000000005, 0.0027700831024930744, 0.0030864197530864196,0.0034602076124567475, 0.00390625, 0.0044444444444444444, 0.00510204081632653, 0.00591715976331361, 0.006944444444444444, 0.008264462809917356, 0.010000000000000002, 0.012345679012345678, 0.015625, 0.02040816326530612, 0.027777777777777776, 0.04000000000000001, 0.0625, 0.1111111111111111, 0.25, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225, 256, 289, 324, 361, 400, 441, 484, 529, 576, 625, 676, 729, 784, 841, 900, 961, 1024, 1089, 1156, 1225, 1296, 1369, 1444, 1521, 1600, 1681, 1764, 1849, 1936, 2025, 2116, 2209, 2304, 2401, 2500]
P=[0.10000000000000001, 0.20000000000000001, 0.30000000000000004, 0.40000000000000002, 0.5, 0.60000000000000009, 0.70000000000000007, 0.80000000000000004, 0.90000000000000002]


# p = 0.8 et C = 89
BinaryFolder = "C:\\Users\Thierry\Documents\Telecom ParisTech\PACT 3.5\BinaryFiles"
DirModelSVC = BinaryFolder + "\\modelSVC"
modelSVC = recuperation(DirModelSVC)

"""

def scores(model,LX):
    Mat = model.decisionfunction(LX)
    return Mat


    
