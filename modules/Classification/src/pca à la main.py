import numpy as np
import numpy.linalg as Lalg
import math as m

"""
Implémentation manuelle d'une fonction pca.
Optimisations et correction des débordements dans C à revoir 
"""




X=[(0,1),(1,1),(2,1),(3,2),(4,1),(5,1),(6,1),(7,2),(8,1),(9,1)]

dim = len(X)

def prodScal(c1,c2): #c1 et c2 des couples
    return c1[0]*c2[0] + c1[1]*c2[1]


def pca_manuelle(X,n_components):

    dim = len(X)
    
    
    # Construction de sigma
    
    sigma = np.zeros((dim,dim))
    for i in range(dim):
        for j in range(dim):
            sigma[i,j] = prodScal(X[i],X[j])
    
    
    # récupération des espaces propres
    E = Lalg.eig(sigma)
    
    for i in range(dim):
        e = E[0][i]
        eNormInf = max(e.real,e.imag)
        if e<10**(-4):
            E[0][i]=0
        else:
            E[0][i]=e
    
    D = np.diag(E[0])    
    P1 = np.array(E[1])
    P2 = Lalg.inv(P1)
    
            # sigma = P1 D P2
    
    sigma = np.matrix(sigma)
    D = np.matrix(D)
    P1 = np.matrix(P1)
    P2 = np.matrix(P2)
    
    # Réduction de dimension
    
    for i in range(n_components, dim):  
        D[i,i] = 0
        
    S = P1 * D * P2 * sigma
    
    for i in range(dim):
        for j in range(dim):
            S[i,j] = S[i,j].real
            
    return S