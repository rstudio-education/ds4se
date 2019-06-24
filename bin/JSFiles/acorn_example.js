var acorn = require("acorn");
var i;
var line_count = 0;
var func_count=0;
var test = function(){
  var x = 2;
}
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
    console.log(func_count);
  });
require('fs').createReadStream(process.argv[2])
  .on('data', function(chunk) {
    for (i=0; i < chunk.length; ++i)
      if (chunk[i] == 10) line_count++;
  })
  .on('end', function() {
    console.log(line_count);
  });
