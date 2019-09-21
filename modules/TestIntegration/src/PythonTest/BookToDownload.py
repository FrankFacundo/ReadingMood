import Atmosphere
from six.moves.urllib.request import urlopen


class Livre(object) :

    def __init__(self,title,author,date, linkToDownload, linkEpub):
        self.title = title
        self.author = author
        self.date = date
        self.linkToDownload = linkToDownload
        self.summary = None
        self.text = None
        self.ambiance = None
        self.currentText = None
        self.linkEpub = linkEpub

    def get_items(self):
        # Returns all the datas of the book
        return [self.title, self.author, self.date, self.linkToDownload, self.linkEpub]
    def set_author(self, author):
        self.author = author
    def set_title(self, title):
        self.title = title
    def set_date(self, date):
        self.date = date

    def isAvailable(self, data):
        # Check if the data is not null

        if (  (data.find("None") != -1) or (data.find("Anonymous")  != -1)) :
            # print(data)
            return False
        return (len(data) > 2)
    def isAvailableDate(self, data):
        return (len(data) >= 5)
    def get_ambiance(self):
        # Returns the ambiance spread by this text, a piece of the book
        return self.ambiance

    def set_ambiance(self, newAmbiance):
        # Changes the atmosphere

        self.ambiance = newAmbiance

    def get_currentText(self):
        # Returns the current text that is read by the user

        return self.currentText


def getStringFromData(url) :
    reponse = urlopen(url)
    content = reponse.read()
    content = content.split("(")
    content = [x.split(")") for x in content]
    result = []
    for x in content :
        m = x[0].split(",")
        inter = []
        for y in m :
            n = y.replace("'", "")
            inter.append(n)
        result.append(inter)
    l=[]
    for x in result :
        if x[0]!="":
            l.append(x)
    return l





def createBookFromUrl(url) :
    listFirst = getStringFromData(url)
    listResult = []
    for y in listFirst :
        if (len(y)>=5) :
            listResult.append(Livre(y[0],y[1],y[2],y[3],y[4]))

    return listResult


def createAuthorFromUrl(url) :

    listFirst = getStringFromData(url)
    listResult = []
    for y in listFirst:
        if (len(y)<2 ):
            listResult.append(Livre("nothing", y[0], "0", "nothing", "nothing"))
        else :
            listResult.append(Livre("nothing", y[0], y[1], "nothing", "nothing"))

    return listResult


def getDatasTest(url, k) :
    # k could be equal to 0, 1, 2, 3 or 4 in order to test the title, author, name or url
    listBook = createBookFromUrl(url)
    n = len(listBook)
    compteur = 0
    for x in listBook :
        if (k!=2) :

            if (x.isAvailable(x.get_items()[k])) :
                compteur +=1
        else :
            if (x.isAvailableDate(x.get_items()[k])) :
                compteur +=1

    return (float(compteur)/float(n) * 100)


def getPourcentageAbsolu(url) :
    listBook = createBookFromUrl(url)
    n =  len(listBook)
    compteur = 0
    for x in listBook:
        listeInter = x.get_items()
        if (x.isAvailable(listeInter[0]) and x.isAvailable(listeInter[1]) and x.isAvailable(listeInter[3]) and x.isAvailable(listeInter[4])  and x.isAvailableDate(listeInter[2] )) :
            compteur+=1
    return (float(compteur) / float(n) * 100)

def getPourcentageRelatif(url) :
    listBook = createBookFromUrl(url)
    n = 5 * len(listBook)
    compteur = 0
    for x in listBook:
        listeInter = x.get_items()

        if (x.isAvailable(listeInter[0])) :
            compteur+=1
        if x.isAvailable(listeInter[1]) :
            compteur +=1
        if ( x.isAvailable(listeInter[3]) ) :
            compteur +=1
        if (x.isAvailableDate(listeInter[2] )) :
            compteur+=1
        if (x.isAvailable(listeInter[4])) :
            compteur+=1
    return (float(compteur) / float(n) * 100)


def getPourcentageRelatifAuthor(url) :
    listBook = createAuthorFromUrl(url)
    n = 5 * len(listBook)
    compteur = 0
    for x in listBook:
        listeInter = x.get_items()
        if x.isAvailable(listeInter[1]):
            compteur += 1
        if (x.isAvailableDate(listeInter[2])):
            compteur += 1
    return (float(compteur) / float(n) * 100)




def getPourcentageAbsoluAuthor(url) :
    listBook = createAuthorFromUrl(url)
    n =  len(listBook)
    compteur = 0
    for x in listBook:
        listeInter = x.get_items()
        if (x.isAvailable(listeInter[1]) and x.isAvailableDate(listeInter[2] )) :
            compteur+=1
    return (float(compteur) / float(n) * 100)



def maximumListe(liste) :
    max = liste[0]
    index = 0
    for i, x in enumerate(liste) :
        if (x>max) :
            max = x
            index = i
    return index,max

def minimumListe(liste) :
    min = liste[0]
    index = 0
    for i, x in enumerate(liste) :
        if (x<min) :
            min = x
            index = i
    return index, min



def getPourcentageSingleDatas(url, k) :
    listBook = createBookFromUrl(url)
    compteur = 0
    n = len(listBook)
    for x in listBook :
        listeInter = x.get_items()
        if (k!=2) :

            if (x.isAvailable(listeInter[k])):
                compteur += 1
        else :
            if (x.isAvailableDate(listeInter[k])):
                compteur += 1
    return (1.0 * compteur / n * 100)


def getLinkEpubFromBook(linkBook) :
    baseEpub = "https://readingmood-239210.appspot.com/treatepub/https://"
    new_link = baseEpub + linkBook.lstrip()
    return new_link

def hideUselessInList(liste):
    new_liste = []
    for x in liste :
        if (len(x) > 3) :
            new_liste.append(x)
    return new_liste

