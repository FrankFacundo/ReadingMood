from BookToDownload import *
import Classifior
import time
import urllib
from six.moves.urllib.request import urlopen
import matplotlib.pyplot as plt
import numpy as np


class Utilisateur(object) :

    def __init__(self):
        self.classifieur
        # The classifior to get the atmospheres
        self.book
        # The current book that is choosen to read by the user
        self.listOfBook
        # The list of books that is saved by the user
        self.currentBookDownload
        # The book that will be downloaded and saved by the user

    def getAllBooks(self):
        # Returns all the books available
        return self.listOfBook

    def getBookDownloaded(self) :
        # Returns the book that the user choosed to download
        return self.currentBookDownload

    def getTextCurrentlyRead(self) :
    # Returns the text that the user is currently reading
        return self.book.get_currentText

    def lecture_ambiance_sonore(self,i):
        #Plays a song whether the raspberry pi is connected or not
        if (self.is_connected_RP ):
            # TO BE COMPLETED

            self.display_RP(i)
        else :

            # TO BE COMPLETED
            self.display(i)

        return None

    def display_available_books(self):
        # Returns a list of the book available

        # TO BE COMPLETED

        return None


    ### Raspberry Pi

    def lecture_ambiance_olfactive(self, listAmbiance):
        # TO BE COMPLETED


        return None

    def display(self, name):
        # Plays and audio file without the raspberry pi

        # TO BE COMPLETED

        return None


    def display_RP(self, name):
        #Plays an audio file in the raspberry pi

        # TO BE COMPLETED


        return None


    def is_connected_RP(self):
        # Returns true if it is connected to the raspberry pi

        # TO BE COMPLETED

        return False

    def connection_to_RP(self):
        # Connects to the raspberry Pi

        # TO BE COMPLETED

        return None

    def getDisplayed_RP(self):
        # Returns the atmosphere that is currently displayed bien the RP

        return None

    ### Classification

    def getClassifieur(self):
        # Initialise the classifior

        # TO BE COMPLETED

        return None

    def getAmbiance(self, text):
        # Takes a passage and gives the atmosphere
        ambiance = self.classifieur.predict(text)

        return ambiance

    def getAmbianceBook(self, Livre):
        # Take a book and returns two lists
        # The first one the atmosphere
        # The second one the lines of atmosphere associated
        list_ambiance = []
        list_passage = []
        for x in Livre :
            # x is a passage
            list_ambiance.append(self.getAmbiance(x))
            list_passage.append(x)
        return list_ambiance,list_passage




### Tests


###     Tests Classifieur


### tests Raspberry pi

def isGoodConnection(user):
    # Returns if the connection of the user is the same as the RP displays
    user.connection_to_RP()
    return (user.is_connected_RP()==True)

def isWellDisplayed(user) :
    # Returns if the song that is displayed by the RP is the good one
    text = user.getTextCurrentlyRead
    ambiance = user.getAmbiance(text)
    user.display_RP(ambiance)
    return (user.getDisplayed_RP == ambiance)

### Tests BDD





link = "https://readingmood-239210.appspot.com/searchfletterbook/a"
reponse = urlopen(link)
content = reponse.read()




# Test on Book

def getLinkBookByLetter():
    #Returns the link to download for all the letters
    letters = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]
    listResult =[]
    base = "https://readingmood-239210.appspot.com/searchfletterbook/"
    for x in letters :
        listResult.append( base + x )
    return listResult


def getLinkAuthorByLetter() :
    # Returns the link from author for all the letters
    letters = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u","v", "w", "x", "y", "z"]
    listResult = []
    base = "https://readingmood-239210.appspot.com/searchfletterauthor/"
    for x in letters:
        listResult.append(base + x)
    return listResult


def getPourcentageAllLettersR() :
    # Returns the percentage of good data from book by letter
    listPourc = []
    listLink = getLinkBookByLetter()
    for x in listLink :
        pourc = getPourcentageRelatif(x)
        listPourc.append(pourc)
    return listPourc

def getPourcentageAllAuthorR() :
    # Returns the percentage of good data from author by letter
    listPourc = []
    listLink = getLinkAuthorByLetter()
    for x in listLink:
        pourc = getPourcentageRelatifAuthor(x)
        listPourc.append(pourc)
    return listPourc

def getPourcentageAllLettersAuthor() :
    # Returns the percentage of all good data from book by letter
    #All good means that every data is usefull
    listPourc = []
    listLink = getLinkAuthorByLetter()
    for x in listLink :
        pourc = getPourcentageAbsoluAuthor(x)
        listPourc.append(pourc)
    return listPourc



letters = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]

def displayGraphBar(liste, legendOrdonnee, legendAbsc) :
    #Displays statistics in bar graphic
    plt.bar(letters, liste, align='center', alpha=0.5)
    plt.ylabel(legendOrdonnee)
    plt.xlabel(legendAbsc)
    plt.title("Result of data considering as correct")
    plt.show()

def displayLettersR() :
    #Displays the graphics of percentage
    legendX = "Search by letter"
    legendY = "Percent of data good"
    listeR = getPourcentageAllLettersR()
    displayGraphBar(listeR,legendY,legendX)


def displayLettersA() :
    #Displays the graphics of percentage for authors
    listA = getPourcentageAllLettersAuthor()
    legendX = "Search by letter"
    legendY = "Percent of all data good"
    displayGraphBar(listA,legendY,legendX)


def displayAllLetters() :
    #Displays the graphics of percentage for all letters
    listA = getPourcentageAllLettersAuthor()
    listeR = getPourcentageAllLettersR()
    plt.subplot(3,1,1)
    legendX = "Search by letter"
    legendY = "Percent of all data good"
    plt.xlabel(legendX)
    plt.ylabel(legendY)
    plt.title("Result of data considering as correct (all the attributes)")
    plt.bar(letters, listA, align='center', alpha=0.5)
    plt.subplot(3,1,3)
    legendY = "Percent of data good"
    plt.xlabel(legendX)
    plt.ylabel(legendY)
    plt.title("Result of data considering as correct")
    plt.bar(letters, listeR, align='center', alpha=0.5)
    print("Maximum liste All :")
    index0, maxA = maximumListe(listA)
    index1 , minA = minimumListe(listA)
    print(maxA)
    print(letters[index0])
    print("Miniumum liste All :")
    print(minA)
    print(letters[index1])

    print("Maximum liste relative :")
    index0, maxA = maximumListe(listeR)
    index1, minA = minimumListe(listeR  )
    print(maxA)
    print( letters[index0])
    print("Miniumum liste relative :")
    print(minA)
    print(letters[index1])
    plt.show()


def displayAllLettersAuthor() :
    #Displays the graphics of percentage for all authors
    listA = getPourcentageAllLettersAuthor()
    listeR = getPourcentageAllAuthorR()
    plt.subplot(3,1,1)
    legendX = "Search by letter"
    legendY = "Percent of all data of author good"
    plt.xlabel(legendX)
    plt.ylabel(legendY)
    plt.title("Result of data of author considering as correct (all the attributes)")
    plt.bar(letters, listA, align='center', alpha=0.5)
    plt.ylim(0, 100)
    plt.subplot(3,1,3)
    legendY = "Percent of data of author good"
    plt.xlabel(legendX)
    plt.ylabel(legendY)
    plt.title("Result of data of author considering as correct")
    plt.bar(letters, listeR, align='center', alpha=0.5)
    print("Maximum liste All :")
    index0, maxA = maximumListe(listA)
    index1 , minA = minimumListe(listA)
    print(maxA)
    print(letters[index0])
    print("Miniumum liste All :")
    print(minA)
    print(letters[index1])

    print("Maximum liste relative :")
    index0, maxA = maximumListe(listeR)
    index1, minA = minimumListe(listeR  )
    print(maxA)
    print( letters[index0])
    print("Miniumum liste relative :")
    print(minA)
    print(letters[index1])
    plt.ylim(0,100)
    plt.show()



def displayPercentageBookByLetter(letter) :
    #Displays the graphics of percentage for book sorted by letter
    nlink = "https://readingmood-239210.appspot.com/searchfletterbook/" + letter
    listeX=["Title","Author", "Date", ".txt link" ,".epub link"]
    listeY = []
    for i in range(5) :
        listeY.append(getPourcentageSingleDatas(nlink,i))

    # plt.xlabel("Data tested")
    # plt.ylabel("Percent")
    # plt.title("Result of data tested by letter " + letter)
    # plt.bar(listeX, listeY, align='center', alpha=0.5)
    # plt.ylim(0, 100)
    # plt.show()
    return listeX,listeY

def displayBookLetterAllAtributs() :
    #Displays the graphics of percentage for all book
    listeX = []
    listeY = []
    for x in letters :
        listea, listeb = displayPercentageBookByLetter(x)
        listeX.append(listea)
        listeY.append(listeb)
    for index, x in enumerate(listeX) :
        plt.xlabel("Data tested")
        plt.ylabel("Percent")
        plt.title("Result of data tested by letter " + letters[index])
        plt.bar(x, listeY[index], align='center', alpha=0.5)
        plt.ylim(0, 100)
        plt.show()
    # plt.show()

def moyenneListe(liste) :
    # Returns the average of the list
    compteur = 0
    n = len(liste)
    for x in liste :
        compteur += x
    return (compteur * 1.0 / n)

def displayAllPercentageAttributsBook() :
    #Displays the graphics of percentage for all attributs
    listeY = [[],[],[],[],[]]
    listeX = ["Title", "Author", "Date", ".txt link", ".epub link"]
    for index, x in enumerate(letters) :
        a,b = displayPercentageBookByLetter(x)
        for j, y in enumerate(b) :
                listeY[j].append(y)
    listMoy = []
    for index, x in enumerate(listeY) :
        print(listeY[0])
        print(listeY[1])
        print(x)
        moy = moyenneListe(x)
        listMoy.append(moy)
    plt.xlabel("Data tested")
    plt.ylabel("Percent")
    plt.title("Result of data tested by letter " + letters[index])
    plt.bar(listeX, listMoy, align='center', alpha=0.5)
    plt.ylim(0, 100)
    plt.show()


base = "https://readingmood-239210.appspot.com/searchfletterbook/"



def testEpubData() :
    listBook = getStringFromData(base+"a")
    link_epub = getLinkEpubFromBook(listBook[0][4])
    getAllDataFromEpubWithAtmosphere(link_epub)



testEpubData()


# displayAllPercentageAttributsBook()

# print(getLinkBookByLetter())
# displayLettersR()
# displayLettersA()

# displayAllLetters()

# link1 = "https://readingmood-239210.appspot.com/searchfletterauthor/a"
# a =getStringFromData(link1)


# displayAllLettersAuthor()

# displayPercentageBookByLetter("a")

# displayBookLetterAllAtributs()

# displayBookLetterAllAtributs()

# displayAllLetters()

