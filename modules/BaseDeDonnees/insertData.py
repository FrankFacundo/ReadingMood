
import mysql.connector
from uploadAndDownload import upload
from traiteur import finalthing

from opener import *



def treatLinkTxt(linkTxt):
    y=finalthing(getTextFromUrl(linkTxt))
    return y


