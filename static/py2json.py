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
