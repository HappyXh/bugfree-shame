window.onload = function() {
    $("#search-txt").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            $(this).blur();
            $("#search-txt").val($(this).val());
            $("#search-btn").click();
        }
    });

    $("#search-btn").click(function(){
        var query_str =$("#search-txt").val()
        if(query_str == ""){
            $("#search-presentation").attr("class","input-group input-group-lg has-error")
            $("#search-txt").attr("placeholder","Search box is blank")
        }else {
            $("#search-div").attr("class", "input-group")
            $("#search-txt").attr("placeholder", "Search for...")
            $.ajax({
                url: "search",
                data: {"features": query_str},
                dataType: 'json',
                type: 'POST',
                success: function (data) {
                    var objJson = JSON.parse(data[0]);

                    var story_grid_str = $($("#story-grid div[class=col-xs-4]")[0]).html()
                    story_grid_str = "<div class=\"col-xs-4\">" + story_grid_str + "</div>"

                    $("#story-grid").children().remove()
                    if(objJson.length < 1){
                        $("#story-grid").append(story_grid_str)
                    }
                    for(var i=0;i<Math.min(6,objJson.length);i++){
                        var obj=objJson[i];
                        var file_path = obj._id.substring(0,obj._id.indexOf('.'))+"-1.jpg"
                        $("#story-grid").append(story_grid_str)
                        $("#story-grid .thumbnail img:last").attr("src","http://7xoiwj.com1.z0.glb.clouddn.com/" + file_path)
                        $("#story-grid .actions a:last").attr("href","attachment/" + obj._id)
                    }
                },
                error: function (data) {
                    console.log("get data error" + data);
                },
                complete: function () {
                    console.log("get data complete");
                }
            })

        }

    })

}


function focusOn(element){
    var e=document.getElementById("selected");
    if(e!=null){
        e.parentNode.removeChild(e);
    }
    var selectImg = document.createElement("img");     //创建一个img元素
    selectImg.id="selected"
    selectImg.src="images/success.png";   //给img元素的src属性赋值
    selectImg.style.position = "absolute";
    selectImg.style.left = "20px";
    selectImg.style.top = "5px";
    element.appendChild(selectImg);      //为dom添加子元素img
}
function select(element,id){

//    var xml =new XMLHttpRequest();
//    xml.open('POST', "/bugfree-shame/1/select" , true);
//    xml.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//    xml.send();
    var temp = document.createElement("form");
        temp.action = "/bugfree-shame/slide/"+id +"/select";
        temp.method = "post";
        temp.style.display = "none";
        document.body.appendChild(temp);
        temp.submit();

}

function downloadPPT(slideArr){
    var temp = document.createElement("form");
    temp.action = "slide/createPPT";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("textarea");
    opt.name = "slideIdArr";
    opt.value =slideArr;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
}
