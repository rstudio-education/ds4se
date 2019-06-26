/*
This is a program that calls upon the acorn JavaScript parser to look at abstract syntax trees of JS programs.  I have used a bash script to call this program on a directory of JS programs, which writes the number of lines and number of functions to a Stats/js_stats.csv file. Currently, it touches the file twice, which is a shame. First, to count the number of functions, next to count the number of lines. I couldn't get them to read simultaneously.

JavaScript Files: https://github.com/trekhleb/javascript-algorithms.git

Bash script:
Inside the directory with all your python files, run this. It will write to "Stats/js_stats.csv"

 for f in Programs/*.js; do node acorn_example.js "$f" >> Stats/js_stats.csv; done

 To clear the Stats/python_stats.csv file, run:
 cat /dev/null > Stats/js_stats.csv

*/
//console.log(process.argv[2]);
var acorn = require("acorn");
var line_count = 0;
var func_count=0;
function test(){
  var x = 2;
}
function otherstuff(){
  var x =7;
}


string = require('fs').readFileSync(process.argv[2]).toString();
sourceType = new Object;
sourceType.sourceType='module';
const ast =acorn.parse(string, sourceType);
const walk = require("acorn-walk")

walk.full(ast, node => {
  //console.log(node.type);
  if(node.type == "FunctionDeclaration"){
    func_count+=1;
  }
})


require('fs').createReadStream(process.argv[2])
  .on('data', function(chunk) {
    for (i=0; i < chunk.length; ++i)
      if (chunk[i] == 10) line_count++;
  })
  .on('end', function() {
    console.log(line_count  + " , "+ func_count );
  });
