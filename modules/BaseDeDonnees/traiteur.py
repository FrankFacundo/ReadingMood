
from classifier import stringtoMood


def findNextPoint(index,longString):
    N=len(longString)
    k=0
    while(index+k<N):
        if longString[index+k]=='.':
            return (True,index+k)
        k+=1
    return (False,index)

def findNextSpace(index,longString):
    N=len(longString)
    k=0
    while(index+k<N):
        if longString[index+k]==' ':
            return (True,index+k)
        k+=1
    return (False,index)


def cutter(longString):
    N=len(longString)
    SubStrings=[]
    index=0
    while(index+15000<N):
        nextPointExists,nextIndex=findNextPoint(index+15000,longString)
        nextSpaceExists,possibleIndex=findNextSpace(index+15000,longString)
        if(nextPointExists):
            SubStrings.append(longString[index:nextIndex])
            index=nextIndex
        else:
            if(nextSpaceExists):
                SubStrings.append(longString[index:possibleIndex])
                index=possibleIndex
            else:
                SubStrings.append(longString[index:index+15000])
                index+=15000
    return SubStrings


def normalizeur(string):
    line=string.replace('\n', ' ')
    line=line.replace('\r', ' ')
    line=line.replace('\t', ' ')
    line=line.replace('-', ' ')
    line=line.replace('_',' ')
    return line
def treat(longString):
    cuts=cutter(longString)
    result=[]
    for x in cuts:
        line=normalizeur(x)
        ambiance=stringtoMood(line)
        result.append( (ambiance,x)) 
    return result

def changeur(ambiance,x):
    debutthing="/$&BERNARDDEBUT-"
    thing="\$&"
    endthing="/$&BERNARDEND\$&"
    result=debutthing+ambiance+thing+x+endthing
    return result

def finalthing(text):
    result=""
    y=treat(text)
    for x in y:
        result+=changeur(x[0],x[1])
    return result

