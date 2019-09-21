

def searchAuthor(path,cursor):
    list=path.split('_')
    command="SELECT * FROM FinalTable WHERE auteur LIKE '%"
    for x in list:
        command+=x
        command+="%"
    command+="';"
    try: 
        cursor.execute(command)
        result=""
        for x in cursor.fetchall():
            result+=str(x)
            result+="\n"
        return result 
    except: 
        return "error in search"


def searchBook(path,cursor):
    command="SELECT * FROM FinalTable WHERE titre LIKE '"
    list=path.split('_')
    for x in list:
        command+=x
        command+="%"
    command+="';"
    try:
        cursor.execute(command)
        result=""
        for x in cursor.fetchall():
            result+=str(x)
            result+="\n"
        return result
    except:
        return "error in search"
    
def searchfletterauthor(letter,cursor):
    command="SELECT DISTINCT auteur, date FROM FinalTable WHERE auteur LIKE '"+letter+"%';"
    try:
        cursor.execute(command)
        result=""
        for x in cursor.fetchall():
            result+=str(x)
            result+="\n"
        return result
    except:
        return "error in search"

def searchfletterbook(letter, cursor):
    command="SELECT * FROM FinalTable WHERE titre LIKE '"+letter+"%';"
    try:
        cursor.execute(command)
        result=""
        for x in cursor.fetchall():
            result+=str(x)
            result+="\n"
        return result
    except:
        return "error in search"


def researchThroughUrl(urlTXT,cursor):
    command="SELECT * FROM FinalTable WHERE lienTXT LIKE '"+urlTXT+ "';"
    cursor.execute(command)
    rt=cursor.fetchall()
    if len(rt)!=0:
        return (True,rt[0])
    else:
        return (False,None)



