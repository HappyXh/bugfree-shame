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
    var e = $(element);
    e.addClass("selectedStoryLine");
    e.siblings().removeClass('selectedStoryLine');
    var parent=e.parent().parent().siblings()
    for(var i=0; i< parent.length;i++){
        if(parent[i].childElementCount>0){
            for(var j=0; j<  parent[i].children[0].childElementCount;j++){
                $(parent[i].children[0].children[j]).removeClass('selectedStoryLine');
            }
        }
    }

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
            document.getElementById("imagebg").innerHTML="";
            for(var i=0;i<objJson.length;i++){
                var obj=objJson[i];
                var str="<li data-sPic=\""+obj.filePath.substring(0,obj.filePath.indexOf('.ppt'))+obj.page+".PNG\">" +
                    "<img src=\""+obj.filePath.substring(0,obj.filePath.indexOf('.ppt'))+""+obj.page+".PNG\"  style='width:100%; height:100%;' " +
                    "ondblclick=\"select("+id+",'"+slideIdStr+"',"+index+","+obj.id+")\" /></li>";
                document.getElementById("imagebg").innerHTML+=str;
            }
            var dd=document.getElementById("imagebg").innerHTML;
            $.getScript('../../../scripts/imgSlider.js',img.init());
        },
        error: function(data) {
            console.log("get data error" + data);
        },
        complete: function() {console.log("get data complete");}
    });
}
function select(id,slideIdStr,index,slideId){

    var slideArr=slideIdStr.substring(1).split(',');
    slideArr[index]=slideId.toString();
    slideIdStr=slideArr.toString();
    var temp = document.createElement("form");
    temp.action = "/bugfree-shame/slide/"+id +"/reSelect";
    temp.method = "post";
    temp.style.display = "none";

    var opt = document.createElement("textarea");
    opt.name = "slideIdStr";
    opt.value =slideIdStr;
    temp.appendChild(opt);

    document.body.appendChild(temp);
    temp.submit();

}