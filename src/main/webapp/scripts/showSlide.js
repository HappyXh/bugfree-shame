function createPPT(slides){
    var temp = document.createElement("form");
    temp.action = "/bugfree-shame/slide/createPPT";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("textarea");
    opt.name = "slides";
    opt.value =slides;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
}

function choose(element,id,index,slideIdStr){

    var temp = document.createElement("form");
    temp.action = "/bugfree-shame/slide/"+id+"/"+index+"/choose";
    temp.method = "post";
    temp.style.display = "none";

    var opt = document.createElement("textarea");
    opt.name = "slideIdStr";
    opt.value =slideIdStr;
    temp.appendChild(opt);

    document.body.appendChild(temp);
    temp.submit();

}

