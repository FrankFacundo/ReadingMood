from sklearn import tree

def entraineDecisionTree(nX,nY):
    model = tree.DecisionTreeClassifier()
    model.fit(nX,nY)
    return model


def testDecisionTree(nX, nY, nXn, nYn):
    #nX et nY les parties d'entraînement
    #nXn et nYn les parties de test
    model = tree.DecisionTreeClassifier()
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

    
def efficDecisionTree(Banque):
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
            zi = testDecisionTree(nX, nY, nXn, nYn)
            Z.append(zi)
        z,incertitude = moyenne(Z)
        OrdonneeP.append((z,incertitude))
        c2 = cl.clock()
        print("Pour la proportion p = ", p , ", on met un temps de ", (c2-c1) ," secondes")
    return P,OrdonneeP

"""
PTree=np.array([ 0.1,  0.2,  0.3,  0.4,  0.5,  0.6,  0.7,  0.8,  0.9])

OrdTree=[(0.1370941020543406, 8.478092134235595e-06), (0.1492921013412817, 5.737876298203677e-06), (0.15767758400680565, 4.703177661247316e-06), (0.15815972222222224, 5.087160613272052e-06), (0.1620453190220632, 6.805513986774755e-06), (0.16005192878338279, 9.2653994092179e-06), (0.15526057030481807, 4.398461455379801e-06), (0.16022058823529406, 6.104576801678846e-06), (0.15942857142857142, 4.797050322342406e-05)]
"""