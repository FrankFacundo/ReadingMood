import Atmosphere


class Livre(object) :

    def __init__(self,title,author,summary,epub):
        self.title = title
        self.author = author
        self.summary = summary
        self.text = epub
        self.ambiance
        self.currentText


    def get_items(self):
        # Returns all the datas of the book
        return self.title, self.author, self.summary, self.text

    def get_ambiance(self):
        # Returns the ambiance spread by this text, a piece of the book
        return self.ambiance

    def set_ambiance(self, newAmbiance):
        # Changes the atmosphere

        self.ambiance =  newAmbiance

    def get_currentText(self):
        # Returns the current text that is read by the user

        return self.currentText

