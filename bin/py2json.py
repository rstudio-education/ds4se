def py2json():
    words = ["Box-and-whisker plot",
    "Console",
    "Comma-separated values (CSV)",
    "Dataframe",
    "Descriptive statistics",
    "Inferential statistics",
    "Inter-quartile-range",
    "Library",
    "Mann-whitney-u-test",
    "Mean",
    "Null-hypothesis",
    "Outlier",
    "P-value",
    "Package"]

    the_json = ""


    for i,w in enumerate(words):
        if i<len(words)-1:
            the_string ="{\"Term\": \"" + w + "\", \"Defined\": \"FIXME\"},"

        elif i==len(words)-1:
            the_string ="{\"Term\": \"" + w + "\", \"Defined\": \"FIXME\"}"
        the_json+=(the_string)

    the_json = "var myGlossary =[" + str(the_json) + " ]"
    out = open("../static/myglossary.js","w")
    out.write(str(the_json))
    out.close()

#we need all the files in the main directory that have index.Rmd
# next we need to extract the status line with grep
# next we need to store those statuses with the file name and write some js that can populate the html with badges
def getStatus():
    import glob
    import re
    import os
    #get all the Rmd index files
    returnthis ={}
    for f in glob.glob("../*/*.Rmd"):
        #open and read the file
        file = open(f, 'r')
        f=f.replace('\\','')
        f=f.replace('/','')
        f=f.replace('index.Rmd','')
        f=f.replace('..','')
        returnthis[f]=[]


        for line in file.readlines():
            if re.search('status:', line, re.I):

                returnthis[f].append(line[:-1])
            if re.search('badges:', line, re.I):
                returnthis[f].append(line[:-1])



    returnthis = "var files_status_badges = " + str(returnthis)
    out = open("../static/status_and_badges.js","w")
    out.write(str(returnthis))
    out.close()



def main():
    py2json()
    print("The new glossary json is now located in static/myglossary.js")
    getStatus()
    print("The new status and badge values are now in static/status_and_badges.js")
if __name__ == '__main__':
    main()
