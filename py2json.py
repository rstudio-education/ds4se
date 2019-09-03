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

    the_json = []


    for w in words:
        the_string ="{\"Term\": \"" + w + "\", \"Defined\": \"FIXME\"},"
        the_json.append(the_string)
    for j in the_json:
        print(j)

#we need all the files in the main directory that have index.Rmd
# next we need to extract the status line with grep
# next we need to store those statuses with the file name and write some js that can populate the html with badges
def getStatus():
    import glob
    print(glob.glob("../*.Rmd"))


def main():
    #py2json()
    getStatus()
if __name__ == '__main__':
    main()
