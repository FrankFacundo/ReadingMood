
# Copyright 2015 Google Inc. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# [START gae_flex_quickstart]


import logging

from flask import Flask
import mysql.connector
from flask import request
app = Flask(__name__)
from researchDataBase import *
from uploadAndDownload import *
from traiteur import finalthing
from opener import *
from insertData import *



@app.route('/')
def hello():
    """Return a friendly HTTP greeting."""
    return 'Hello World, we are on the ReadingMood web server'

@app.route('/searchbook/<bookName>')
def searccBok(bookName):
    conn = mysql.connector.connect(host="34.76.124.119",user="root", password="telecomparistech",database="Books")
    cursor=conn.cursor()
    y= searchBook(bookName,cursor)
    conn.commit()
    return y
    

@app.route('/searchauthor/<authorName>')
def searchThing(authorName):
    conn = mysql.connector.connect(host="34.76.124.119",user="root", password="telecomparistech",database="Books")
    cursor=conn.cursor()
    y=searchAuthor(authorName,cursor)
    conn.commit()
    return str(y)

@app.route('/exists/<path:thingyy>')
def existant(thingyy):
    return str(existsInModified(thingyy))

@app.route('/searchfletterbook/<fbook>')
def searchfbook(fbook):
    conn = mysql.connector.connect(host="34.76.124.119",user="root",password="telecomparistech",database="Books")
    cursor=conn.cursor()
    y=searchfletterbook(fbook,cursor)
    conn.commit()
    return y

@app.route('/treatxt/<path:txtlink>')
def treattxt(txtlink):
    bol=existsInModified(txtlink)
    if bol:
        download(txtlink)
        u=open('bufferForReading.txt','r')
        return u.read()
    else:
        return treatLinkTxt(txtlink)

@app.route('/getTxt/<path:url>')
def f1(url):
    return getTextFromUrl(url)

@app.route('/getmodified/<path:urltxt>')
def f2(urltxt):
    if existsInModified(urltxt):
        download(urltxt)
        u=open('bufferForReading.txt','r')
        return u.read()
    else:
        return treatLinkTxt(txtlink)


@app.route('/searchfletterauthor/<fauthor>')
def searchfauthor(fauthor):
    conn = mysql.connector.connect(host="34.76.124.119",user="root",password="telecomparistech",database="Books")
    cursor=conn.cursor()
    y=searchfletterauthor(fauthor,cursor)
    conn.commit()
    return y 


@app.errorhandler(500)
def server_error(e):
    logging.exception('An error occurred during a request.')
    return """
    An internal error occurred: <pre>{}</pre>
    See logs for full stacktrace.
    """.format(e), 500




if __name__ == '__main__':
    # This is used when running locally. Gunicorn is used to run the
    # application on Google App Engine. See entrypoint in app.yaml.
    app.run(host='127.0.0.1', port=8080, debug=True)
# [END gae_flex_quickstart]
