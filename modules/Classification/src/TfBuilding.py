def creationTomega(tree):

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
        
def reinitTf(T,Tomega,Tf):
    Tf = Tree()
    T.Top()
    Tomega.Top()
    
    Tf.ajout(Tomega.actual,'g')
    
    Tomega.descend('g')
    Tf.ajout(Tomega.actual,'g')
    Tomega.remonte()
    Tf.remonte()
    
    Tomega.descend('d')
    Tf.ajout(Tomega.actual,'d')
    Tomega.remonte()
    Tf.remonte()
    
    Tomega.descend('g')
    Tf.descend('g')
    T.descend('g')
    

def creationTf(T,Tomega,Tf):
    if Tomega.__estFeuille__():
        return
    
    #Est-ce que l'on est à la bonne ambiance?
    Tf.ajout(T.actual,'d')
    Tf.remonte()
    reste = Tomega.actual[len(T.actual):]
    Tf.ajout(reste,'g')
    
    #Groupe de gauche
    Tomega.descend('g')
    filsG = Tomega.actual
    Tf.ajout(filsG,'g')

    #Récursivité Gauche  
    T.descend('g')
    creationTf(T,Tomega,Tf)
    T.remonte()
    Tomega.remonte()
    Tf.remonte()
    
    if Tomega.tr[Tomega.pos *2 + 2] != None:    #On a bien un fils droit
            
        #Groupe de droite
        Tomega.descend('d')
        filsD = Tomega.actual
        Tf.ajout(filsD,'d')
        
        #Récursivité Droite  
        T.descend('d')
        creationTf(T,Tomega,Tf)
        T.remonte()
        Tomega.remonte()
        Tf.remonte()
    Tf.remonte()
    return None