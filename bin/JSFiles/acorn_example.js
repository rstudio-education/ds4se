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
var i;
var line_count = 0;
var func_count=0;
var test = function(){
  var x = 2;
}

//TODO: this just can't be the right way to print out how many functions in a file. There's too many zeros or in the order of magnitude of thousands.
require('fs').createReadStream(process.argv[2])
  .on('data', function(chunk) {
    for (i=0; i < chunk.length; ++i){
    for (let token of acorn.tokenizer(chunk)){
      //console.log(token.type.label);
      if(token.type.label=="function"){
        func_count +=1;
      }
    }
  }
  })
  .on('end', function() {
    //console.log(func_count);
  });
require('fs').createReadStream(process.argv[2])
  .on('data', function(chunk) {
    for (i=0; i < chunk.length; ++i)
      if (chunk[i] == 10) line_count++;
  })
  .on('end', function() {
    console.log(func_count + " , " +line_count);
  });
