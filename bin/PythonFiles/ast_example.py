'''
This is a program that calls upon Python's AST (Abstract Syntax Tree). I have used a bash script to call this program on a directory of Python programs, which writes the number of lines and number of functions to a Stats/python_stats.csv file. Currently, it touches the file twice, which is a shame. First, to count the number of functions, next to count the number of lines. I couldn't get them to read simultaneously.

Python Files: https://github.com/TheAlgorithms/Python.git

Bash script:
Inside the directory with all your python files, run this. It will write to "Stats/python_stats.csv"

 for f in *.py; do python3 ast_example.py "$f"; done

 To clear the Stats/python_stats.csv file, run:
 cat /dev/null > Stats/python_stats.csv
'''

import ast
from pprint import pprint
import sys



def main():
    with open(sys.argv[1], "r") as source:
        count = len(source.readlines( ))
    with open(sys.argv[1], "r") as source:
        tree = ast.parse(source.read())
    analyzer = Analyzer()
    analyzer.visit(tree)
    analyzer.stats["lines"]=count
    analyzer.report()

    with open("Stats/python_stats.csv","a") as f:
        report = str(analyzer.stats["lines"]) + ", " + str(analyzer.stats["functions"])
        f.write(report)
        f.write("\n")


class Analyzer(ast.NodeVisitor):
    def __init__(self):
        self.stats = {"lines": 0, "functions": 0}
        self.nodecount = 0


    def visit_FunctionDef(self, node):

        self.stats["functions"]+=1
        self.generic_visit(node)


    def report(self):
        pprint(self.stats)




if __name__ == "__main__":
    main()
