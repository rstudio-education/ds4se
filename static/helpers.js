

function grabStatus(list){
  //looks in the yaml headers of the RMarkdown files to find the status
  // will populate that list item with the correct status badge
  var ul = document.getElementById(list);
  var items = ul.getElementsByTagName("li");
  for (var i = 0; i < items.length; ++i) {
  //for each list item, look it up in the dictionary
  //append its status badge
  //append its badge badges

    var filepath = items[i].getElementsByTagName("a")[0].getAttribute("href");

    var to_lookup = filepath.replace("./","");
    to_lookup = to_lookup.replace("/index.html","");
    var status = files_status_badges[to_lookup][0].replace("status: ","");

    //make all the status badges
    console.log(status);
    var status_badge=makeStatusBadge(status);
    items[i].appendChild(status_badge);

    //make all the badge badges lol yep that's their name

    //WARMUPS
    if(files_status_badges[to_lookup][1].includes("warmup")){
      console.log(files_status_badges[to_lookup][1]);
      var warmup_badge = makeWarmupBadge();

      items[i].appendChild(warmup_badge);
    }

    //ACADEMIC
    if(files_status_badges[to_lookup][1].includes("academic")){
      console.log(files_status_badges[to_lookup][1]);
      var academic_badge = makeAcademicBadge();

      items[i].appendChild(academic_badge);
    }

    //INDUSTRY
    if(files_status_badges[to_lookup][1].includes("industry")){
      console.log(files_status_badges[to_lookup][1]);
      var industry_badge = makeIndustryBadge();

      items[i].appendChild(industry_badge);
    }


  }

}


  function makeStatusBadge(status){
    var status_badge = document.createElement("SPAN");
    status_badge.classList.add("badge");
    status_badge.classList.add("badge-info");
    status_badge.innerHTML=(status);
    return (status_badge);
  }
  function makeWarmupBadge(){
    var warmup_badge = document.createElement("SPAN");
    warmup_badge.classList.add("badge");
    warmup_badge.classList.add("badge-success");
    warmup_badge.innerHTML=("warmup");
    warmup_badge.style="margin-left:1%;"
    return (warmup_badge);
  }

  function makeAcademicBadge(){
    var academic_badge = document.createElement("SPAN");
    academic_badge.classList.add("badge");
    academic_badge.classList.add("badge-dark");
    academic_badge.innerHTML=("academic");
    academic_badge.style="margin-left:1%;"
    return (academic_badge);
  }

  function makeIndustryBadge(){
    var industry_badge = document.createElement("SPAN");
    industry_badge.classList.add("badge");
    industry_badge.classList.add("badge-secondary");
    industry_badge.innerHTML=("industry");
    industry_badge.style="margin-left:1%;"
    return (industry_badge);
  }

function CreateTableFromJSON() {


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
            tabCell.style="font-weight: bold;width:30%;";
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
