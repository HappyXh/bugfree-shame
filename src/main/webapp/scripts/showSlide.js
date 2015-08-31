

function createPPT(slides){
    var temp = document.createElement("form");
    temp.action = "/bugfree-shame/slide/createPPT";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("textarea");
    opt.name = "slideIdStr";
    opt.value =getSlideIdStr();
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
}

function choose(element,id,selectedSlides){
    var index=element.getAttribute("id").toString().substr(10);
    var slideIdStr=getSlideIdStr();

    var slideTitles=getSlideTitles();
    for(var i=0; i< slideTitles.length;i++){
        $(slideTitles[i]).removeClass('selectedStoryLine');
    }
    $(slideTitles[index]).addClass('selectedStoryLine');

    var form_data;
    form_data = new FormData();
    $.ajax({
        url: "/bugfree-shame/slide/"+id+"/"+index+"/select",
        dataType: 'json',
        cache: false,
        contentType: false,
        processData: false,
        crossdomain: true,
        data: form_data,
        type: 'post',
        success: function(data) {
            var objJson= JSON.parse(data[1]);
            document.getElementById("slides-box").style.display="none";
            document.getElementById("apply-div").style.display="none";
            document.getElementById("imageShow").style.display="block";


            document.getElementById("imagebg").innerHTML="";
            for(var i=0;i<objJson.length;i++){
                var obj=objJson[i];
                var str="<li data-sPic=\""+obj.filePath.substring(0,obj.filePath.indexOf('.ppt'))+obj.page+".PNG\">" +
                    "<img class=\"clickable\" src=\""+obj.filePath.substring(0,obj.filePath.indexOf('.ppt'))+""+obj.page+".PNG\"  " +
                    "style='width:100%; height:100%;' ondblclick=\"select("+id+","+index+","+obj.id+",'"+selectedSlides+"')\"" +
                    "data-toggle=\"tooltip\" data-placement=\"left\" title=\"Double Click\" /></li>";
                document.getElementById("imagebg").innerHTML+=str;
            }
            var dd=document.getElementById("imagebg").innerHTML;
            $.getScript('../../scripts/imgSlider.js',img.init());
        },
        error: function(data) {
            console.log("get data error" + data);
        },
        complete: function() {console.log("get data complete");}
    });

}

function select(id,index,slideId,selectedSlides){

    var slideArr=getSlideIdStr().split(',');
    slideArr[index]=slideId.toString();
    var slideIdStr=slideArr.toString();

    if(selectedSlides==""){
        var selectedSlidesArr=[];
        for(var i=0;i<slideArr.length;i++) selectedSlidesArr[i]=0;
    }
    else var selectedSlidesArr=selectedSlides.split(",");
    selectedSlidesArr[index]="1";

    var form_data;
    form_data = new FormData();
    form_data.append("slideIdStr",slideIdStr);
    $.ajax({
        url:"/bugfree-shame/slide/"+id+"/test?slideIdStr="+slideIdStr,
        dataType: 'json',
        cache: false,
        contentType: false,
        processData: false,
        crossdomain: true,
        data: form_data,
        type: 'post',
        success: function(data) {
            var objJson= JSON.parse(data[0]);
            document.getElementById("slides-box").style.display="block";
            document.getElementById("apply-div").style.display="block";
            document.getElementById("imageShow").style.display="none";

            $(document.getElementById("slideTitle"+index)).removeClass("selectedStoryLine")
            $(document.getElementById("slideTitle"+index)).addClass("edited")
            document.getElementById("slides-box").innerHTML="";
            for(var i=0;i<objJson.length;i++){
                var obj=objJson[i];
                document.getElementById("slides-box").innerHTML+="<div id=\"slideImgBox"+i+"\" slideId=\""+obj.id+"\" class=\"col-xs-4\">"+
                    "<div class=\"thumbnail\">"+
                        "<img  id=\"slideImg"+i+"\" class=\"slideImg\" src=\""+obj.filePath.substring(0,obj.filePath.indexOf('.ppt'))+""+obj.page+".PNG\" alt=\"...\"/></div</div>"
            }

        },
        error: function(data) {
            console.log("get data error" + data);
        },
        complete: function() {console.log("get data complete");}
    });
}


function deleteSlide(element){
    var e=element.parentNode.parentNode;
    var partE=element.parentNode.parentNode.parentNode.parentNode.parentNode;
    var flage=0;
    if($(e).siblings().length==0){
        flage=1;
    }
    var index=e.getAttribute("id").toString().substr(13);
    var slideTitles=getSlideTitles();
    document.getElementById("slideTitleBox"+index).parentNode.removeChild(document.getElementById("slideTitleBox"+index));
    document.getElementById("slideImgBox"+index).parentNode.removeChild(document.getElementById("slideImgBox"+index));
    var count=0;
    for(var i=0;i<slideTitles.length;i++){
        if(i!=index){
            document.getElementById("slideTitleBox"+i).setAttribute("id","slideTitleBox"+count);
            document.getElementById("slideTitle"+i).setAttribute("id","slideTitle"+count);
            document.getElementById("slideImgBox"+i).setAttribute("id","slideImgBox"+count);
            document.getElementById("slideImg"+i).setAttribute("id","slideImg"+count);
            count++;
        }
    }
    if(flage==1){
        var partIndex= partE.getAttribute("id").toString().substr(9);
        var slideParts=getSlideParts();

        partE.parentNode.removeChild(partE);
        var count=0;
        for(var i=0;i<slideParts.length;i++){
            if(i!=partIndex){
                document.getElementById("slidePart"+i).setAttribute("id","slidePart"+count);
                count++;
            }
        }
    }
}
function deletePart(element){
    var e=element.parentNode.parentNode.parentNode.nextElementSibling;
    while(e.getElementsByTagName("li").length!=0){
        deleteSlide(e.getElementsByTagName("li")[0]);
    }
//    var e=element.parentNode.parentNode.parentNode.parentNode;
//    var partIndex= e.getAttribute("id").toString().substr(9);
//    var slideParts=getSlideParts();
//
//    element.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(element.parentNode.parentNode.parentNode.parentNode);
//    var count=0;
//    for(var i=0;i<slideParts.length;i++){
//        if(i!=partIndex){
//            document.getElementById("slidePart"+i).setAttribute("id","slidePart"+count);
//            count++;
//        }
//    }
}
function addSlide(element,id,selectedSlides){
    var e=element.parentNode.parentNode;
    var index=e.getAttribute("id").toString().substr(13);
    var slideId=13;
    var slidePng="http://7xkmew.com1.z0.glb.clouddn.com/default/default1.PNG";
    var count=parseInt(index)+1;
    var slideTitles=getSlideTitles();
    for(var i=slideTitles.length-1;i>=count;i--){
        document.getElementById("slideTitleBox"+i).setAttribute("id","slideTitleBox"+(i+1));
        document.getElementById("slideTitle"+i).setAttribute("id","slideTitle"+(i+1));
        document.getElementById("slideImgBox"+i).setAttribute("id","slideImgBox"+(i+1));
        document.getElementById("slideImg"+i).setAttribute("id","slideImg"+(i+1));
    }
    var strTitleBox="<div id=\"slideTitleBox"+count+"\" class=\"slideTitleBox\" onMouseover=\"showDiv(this)\" onMouseout=\"hideDiv(this)\"><div class=\"col-xs-10 slideContent\">"+
                    "<li id=\"slideTitle"+count+"\" class=\"clickable\""+
                    "onclick=choose(this,"+id+",\""+selectedSlides+"\")> Insert content</li></div>"+
                    "<div class=\"col-xs-2 slideEditor\" style=\"display: none\"><a  class=\"btn-default glyphicon glyphicon-minus\" onclick=\"deleteSlide(this)\"></a>"+
                    "<a class=\"btn-default glyphicon glyphicon-plus\" onclick=addSlide(this,\""+id+"\",\""+selectedSlides+"\")></a></div></div>";
    var strImgBox="<div id=\"slideImgBox"+count+"\" slideId=\""+slideId+"\" class=\"col-xs-4\"><div class=\"thumbnail\">"+
            "<img  id=\"slideImg"+count+"\" class=\"slideImg\" src=\""+slidePng+"\" alt=\"...\" /></div></div>";
    document.getElementById('slideTitleBox'+index).insertAdjacentHTML("afterEnd",strTitleBox);
    document.getElementById('slideImgBox'+index).insertAdjacentHTML("afterEnd",strImgBox);

}
function addPart(element,id,selectedSlides){
    var e=element.parentNode.parentNode.parentNode.parentNode;
    var partIndex= e.getAttribute("id").toString().substr(9);
    var slideParts=getSlideParts();
    var count=parseInt(partIndex)+1;

    for(var i=slideParts.length-1;i>=count;i--){
        document.getElementById("slidePart"+i).setAttribute("id","slidePart"+(i+1));
    }
    var strPart="<div id=\"slidePart"+count+"\">"+
        "<li class=\"partTitle\"><div onMouseover=\"showDiv(this)\" onMouseout=\"hideDiv(this)\"><div class=\"col-xs-10 slideContent\">Undefine</div>" +
            "<div class=\"slideEditor\" style=\"display: none\"><a  class=\"btn-default glyphicon glyphicon-minus\" onclick=\"deletePart(this)\"></a>"+
            "<a class=\"btn-default glyphicon glyphicon-plus\" onclick=addPart(this,\""+id+"\",\""+selectedSlides+"\")></a></div></li>"+
        " <li class=\"partContent\"><ul class=\"\"></ul></li></div>";
    document.getElementById('slidePart'+partIndex).insertAdjacentHTML("afterEnd",strPart);

    var lis=element.parentNode.parentNode.parentNode.nextElementSibling.getElementsByTagName("li");
    var e=lis[lis.length-1].parentNode.parentNode;;
    var index=e.getAttribute("id").toString().substr(13);
    var slideId=13;
    var slidePng="http://7xkmew.com1.z0.glb.clouddn.com/default/default1.PNG";
    var count=parseInt(index)+1;
    var slideTitles=getSlideTitles();
    for(var i=slideTitles.length-1;i>=count;i--){
        document.getElementById("slideTitleBox"+i).setAttribute("id","slideTitleBox"+(i+1));
        document.getElementById("slideTitle"+i).setAttribute("id","slideTitle"+(i+1));
        document.getElementById("slideImgBox"+i).setAttribute("id","slideImgBox"+(i+1));
        document.getElementById("slideImg"+i).setAttribute("id","slideImg"+(i+1));
    }
    var strTitleBox="<div id=\"slideTitleBox"+count+"\" class=\"slideTitleBox\" onMouseover=\"showDiv(this)\" onMouseout=\"hideDiv(this)\"><div class=\"col-xs-10 slideContent\">"+
        "<li id=\"slideTitle"+count+"\" class=\"clickable \""+
        "onclick=choose(this,"+id+",\""+selectedSlides+"\")> Insert content</li></div>"+
        "<div class=\"col-xs-2 slideEditor\" style=\"display: none\"><a  class=\"btn-default glyphicon glyphicon-minus\" onclick=\"deleteSlide(this)\"></a>"+
        "<a class=\"btn-default glyphicon glyphicon-plus\" onclick=addSlide(this,\""+id+"\",\""+selectedSlides+"\")></a></div></div>";
    var strImgBox="<div id=\"slideImgBox"+count+"\" slideId=\""+slideId+"\" class=\"col-xs-4\"><div class=\"thumbnail\">"+
        "<img  id=\"slideImg"+count+"\" class=\"slideImg\" src=\""+slidePng+"\" alt=\"...\" /></div></div>";
    document.getElementById('slidePart'+(parseInt(partIndex)+1)).getElementsByTagName("ul")[0].insertAdjacentHTML("afterBegin",strTitleBox);
    document.getElementById('slideImgBox'+index).insertAdjacentHTML("afterEnd",strImgBox);


}
function back(){
    document.getElementById("slides-box").style.display="block";
    document.getElementById("apply-div").style.display="block";
    document.getElementById("imageShow").style.display="none";
    var slideTitles=getSlideTitles();
    for(var i=0; i< slideTitles.length;i++){
        $(slideTitles[i]).removeClass('selectedStoryLine');
    }

}
function getSlideParts(){
    var slideParts=[];
    var i=0;
    while(document.getElementById("slidePart"+i)!=null){
        slideParts[i]=(document.getElementById("slidePart"+i));
        i++;
    }
    return slideParts;
}
function getSlideTitles(){
    var slideTitles=[];
    var i=0;
    while(document.getElementById("slideTitle"+i)!=null){
        slideTitles[i]=(document.getElementById("slideTitle"+i));
        i++;
    }
    return slideTitles;
}
function getSlideImgBoxes(){
    var slideImgBoxes=[];
    var i=0;
    while(document.getElementById("slideImgBox"+i)!=null){
        slideImgBoxes[i]=(document.getElementById("slideImgBox"+i));
        i++;
    }
    return slideImgBoxes;
}
function getStoryLine(){

}
function getSlideIdStr(){
    var slideIdStr="";
    var slideImgBoxes=getSlideImgBoxes();
    for(var i=0;i<slideImgBoxes.length;i++){
        slideIdStr+=slideImgBoxes[i].getAttribute("slideId")+",";
    }
    return slideIdStr.substr(0,slideIdStr.length-1);
}
function showDiv(element){
    var e=element.getElementsByTagName("div")[1];
    e.style.display="block";
}
function hideDiv(element){
    var e=element.getElementsByTagName("div")[1];
    e.style.display="none";
}