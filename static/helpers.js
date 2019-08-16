
    function CreateTableFromJSON() {
    var myGlossary = [
        {
            "Term": "Epistemology",
            "Defined": "whatttt"

        },
        {"Term": "Box-and-whisker plot", "Defined": "FIXME"},
{"Term": "Console", "Defined": "FIXME"},
{"Term": "Comma-separated values (CSV)", "Defined": "FIXME"},
{"Term": "Dataframe", "Defined": "FIXME"},
{"Term": "Descriptive statistics", "Defined": "FIXME"},
{"Term": "Inferential statistics", "Defined": "FIXME"},
{"Term": "Inter-quartile-range", "Defined": "FIXME"},
{"Term": "Library", "Defined": "FIXME"},
{"Term": "Mann-whitney-u-test", "Defined": "FIXME"},
{"Term": "Mean", "Defined": "FIXME"},
{"Term": "Null-hypothesis", "Defined": "FIXME"},
{"Term": "Outlier", "Defined": "FIXME"},
{"Term": "P-value", "Defined": "FIXME"},
{"Term": "Package", "Defined": "FIXME"}


    ]

    function compareStrings(a, b) {
      // Assuming you want case-insensitive comparison
      a = a.toLowerCase();
      b = b.toLowerCase();

      return (a < b) ? -1 : (a > b) ? 1 : 0;
    }

    myGlossary.sort(function(a, b) {
      return compareStrings(a.Term, b.Term);
    })

    // EXTRACT VALUE FOR HTML HEADER.
    var col = [];
    for (var i = 0; i < myGlossary.length; i++) {
        for (var key in myGlossary[i]) {
            if (col.indexOf(key) === -1) {
                col.push(key);
            }
        }
    }

    // CREATE DYNAMIC TABLE.
    var table = document.createElement("table");
    table.className +="table table-hover";


    // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

    var tr = table.insertRow(-1);                   // TABLE ROW.

    for (var i = 0; i < col.length; i++) {
        var th = document.createElement("th");
        th.scope="row" ;
          // TABLE HEADER.
        th.innerHTML = col[i];
        tr.appendChild(th);
    }

    // ADD JSON DATA TO THE TABLE AS ROWS.
    for (var i = 0; i < myGlossary.length; i++) {

        tr = table.insertRow(-1);
        tr.style="font-weight: bold;";

        for (var j = 0; j < col.length; j++) {
            var tabCell = tr.insertCell(-1);
            tabCell.style="font-weight: bold;";
            tabCell.id = myGlossary[i][col[j]];
            if(j==1){
              tabCell.style="font-weight:normal;"
            }
            tabCell.innerHTML = myGlossary[i][col[j]];

        }

    }

    // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
    var divContainer = document.getElementById("showData");
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
    }

CreateTableFromJSON();
