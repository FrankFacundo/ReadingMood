import os 
import logging 
from google.cloud import storage
from opener import *
##CLOUD_STORAGE_BUCKET=os.environ['CLOUD_STORAGE_BUCKET']

CLOUD_STORAGE_BUCKET='readingmooddefinitive.appspot.com'

def upload(filename,urlTXT):
    gcs=storage.Client()
    bucket=gcs.get_bucket(CLOUD_STORAGE_BUCKET)
    y=urlTXT.strip(".utf-8")
    blob=bucket.blob(y)
    blob.upload_from_file(open(filename,'r'))

def existsInModified(urlTXT):
    gcs=storage.Client()
    bucket=gcs.get_bucket(CLOUD_STORAGE_BUCKET)
    y=urlTXT.strip(".utf-8")
    blob=bucket.blob(y)
    return blob.exists()


def download(urlTXT):
    gcs=storage.Client()
    bucket=gcs.get_bucket(CLOUD_STORAGE_BUCKET)
    y=urlTXT.strip(".utf-8")
    blob=bucket.blob(y)
    u=open('bufferForReading.txt','w')
    blob.download_to_file(u)



