
class Ambiance(object) :
    def __init__(self):
        # Takes a list of songs and one smell
        self.song = ["" for x in range(10)]
        self.olfactive = "smell"



    def get_atmosphere(self):
        return self.song,self.smell
