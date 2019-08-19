#!/usr/bin/env python
import sys
import os
import csv
def print2csv(content):
    with open('episodes.csv', 'wb') as f:
        writer = csv.writer(f)
        writer.writerows(content)
def log2csv(content, starting):
    results = []
    lines = [l.split()[0:2] for l in content]
    first_episode_duration = calculateDuration(starting, lines[0][0])
    results.append([lines[0][1], round(first_episode_duration,2)])
    for idx, line in enumerate(lines):
        if idx < len(lines)-1:
            start = lines[idx][0]
            end = lines[idx+1][0]
            duration = calculateDuration(start, end)
            episode_type = lines[idx+1][1]
            d = round(duration,2)
            results.append([episode_type,d])
    print2csv(results)


def calculateDuration(start, end):
    start = int(start)/1000
    end = int(end)/1000
    difference_in_seconds = end - start
    minutes = difference_in_seconds / float(60)
    return minutes

def parseEpisodes(content):
    lines = [l.split() for l in content]
    listOfCategories = [el[1] for el in lines]
    return listOfCategories

def countHeusticTDD(events):
    #events = events[::-1]  #reverse the list AND returns it
    TFCounter = 0
    TLCounter = 0
    refactoringCounter = 0
    TF = False
    TL = False
    while(events):
        el = events.pop()
        if el == "test-first":
            TFCounter +=1
            TF=True
            TL=False
        elif el == "test-last":
            TLCounter+=1
            TF=False
            TL=True
        elif el == "refactoring":
            refactoringCounter+=1
        else:
            if TF:
                TFCounter+=1
            else:
                TLCounter+=1

    result = {"TF": TFCounter, "TL": TLCounter, "Refactoring": refactoringCounter}
    return result

def mergeZorroEpisodes(dir):
    buff = []
    for zorroDir in os.listdir(dir):
        epdisode_file = dir+"/"+zorroDir+'/zorroEpisodes.txt'
        if os.path.isfile(epdisode_file):
            with open(epdisode_file) as f:
                buff= buff + f.readlines()
    return buff

def getStartingAction(besouro_dir):
    action_timestamps = []
    for besouroDir in os.listdir(besouro_dir):
        action_file = besouro_dir + "/" + besouroDir + '/actions.txt'
        if os.path.isfile(action_file) and os.stat(action_file).st_size>0:
            with open(action_file) as f:
                action_timestamps.append(f.readline().split()[1])
    return min(action_timestamps)

def main(besouro_dir):
    print "========================="
    for dir in os.listdir(besouro_dir):
        if os.path.isdir(besouro_dir+"/"+dir):
            content = mergeZorroEpisodes(besouro_dir)
            cats  = parseEpisodes(content)
            starting_time = getStartingAction(besouro_dir)
            log2csv(content, starting_time)
            denominator = len(cats)
            res = countHeusticTDD(cats)
            numerator = float(res["TF"])
            print "number of eposides %s" % denominator
            if denominator > 0:
                print "Conformance level: {0:.0f}%".format(numerator/denominator*100)
                print "Refactoring episodes: %s" %res['Refactoring']
            else:
                print "No episodes present"
            print "========================="

if __name__ == "__main__":
    if (len(sys.argv)>1):
        main(sys.argv[1])
    else:
        print "Usage: zorro_episode <path to besouro folder> (Project/.besouro)"
