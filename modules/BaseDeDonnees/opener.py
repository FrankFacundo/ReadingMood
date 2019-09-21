
import requests

def getTextFromUrl(url):
    y=requests.get(url).text.encode('utf8').decode('utf8')
    return y 

