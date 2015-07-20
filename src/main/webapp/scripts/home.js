function select(element){
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

