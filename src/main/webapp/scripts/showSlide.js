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

function choose(element,id,index){
    var e = $(element);
    e.addClass("selectedStoryLine");
    e.siblings().removeClass('selectedStoryLine');

    var temp = document.createElement("form");
    temp.action = "/bugfree-shame/slide/"+id+"/"+index+"/choose";
    temp.method = "post";
    temp.style.display = "none";
    document.body.appendChild(temp);
    temp.submit();

}