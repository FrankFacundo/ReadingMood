import Atmosphere

class Classifior(object) :
    def __init__(self, trainingTime, accuracyRate):
        vectorisor = ""
        self.trainingTime = trainingTime
        self.accuracyRate = accuracyRate
    
    def getTrainingTime(self):
        # Returns the training time needed for this classifior
        
        return self.trainingTime

    def getAccuracyRate(self):
        # Returns the accuracy rate reached by this classifior

        return self.accuracyRate

    def predict(self,text):
        # Returns the atmosphere related to the passage


        #TO BE COMPLETED

        myAtmosphere = Atmosphere()
        return myAtmosphere

    def convertToEpub(self,book):
        # Transfers the text to an ePub
        newText = None
        # TO BE COMPLETED
        return newText
