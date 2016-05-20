window.onload=function(){

  var u = function (p) {
    var h = $("#contentDIV").height() + 70;
    var page_list = $("#contentDIV .content_section");
    $(page_list.get(p)).animate(
        {top: -h}, 600, function () {

        });
  }
  var d = function (p) {
    var h = $("#contentDIV").height() + 70;
    var page_list = $("#contentDIV .content_section");
    $(page_list.get(p)).animate(
        {top: h}, 600, function () {

        });
  }
  var g = function(p){
    var page_list = $("#contentDIV .content_section");
    if( p == page_list.length-1){
      $("#nextA").css('display','none');
    }else{
      $("#nextA").css('display','block');
    }
    $(page_list.get(p)).animate(
        {top: 0}, 600, function () {

        });

  }
  $(document).bind('mousewheel',function(e){
    var orgEvent = e.originalEvent;
    var delta = orgEvent.wheelDelta;
    var w = parseInt($("#right_nav").attr("num"));
    var n = $("#right_nav li a").size();
    if(delta>0 & w > 0){
      //$("#right_nav").attr("num", w-1);
      //$("#right_nav li a").removeClass("side_nav_a_active");
      //$($("#right_nav li a").get(w-1)).addClass("side_nav_a_active");
      //d(w);g(w-1);
    }else if(delta <0 & w < n-1){
      //$("#right_nav").attr("num", w+1);
      //$("#right_nav li a").removeClass("side_nav_a_active");
      //$($("#right_nav li a").get(w+1)).addClass("side_nav_a_active");
      //u(w);g(w+1)
    }
  })
  $("#nextA").click(function(){
    var w = parseInt($("#right_nav").attr("num"));
    var n = $("#right_nav li a").size();
    if(w < n-1){
      $("#right_nav").attr("num", w+1);
      $("#right_nav li a").removeClass("side_nav_a_active");
      $($("#right_nav li a").get(w+1)).addClass("side_nav_a_active");
      u(w);g(w+1)
    }
  })

  $("#right_nav li a").click(function(){
    var w = parseInt($("#right_nav").attr("num"));
    var c = parseInt($(this).attr("num"));
    $("#right_nav").attr("num", c);
    $("#right_nav li a").removeClass("side_nav_a_active");
    $($("#right_nav li a").get(c)).addClass("side_nav_a_active");
    if(c > w){
      while(w<c){u(w);w=w+1;}g(c);
    }else if(c < w){
      while(w>c){d(w);w=w-1;}g(c);
    }
  })

  var xstep=1;
  var delay_time=60;
  //Begin of the unchangable area, please do not modify this area
  var YY=0;
  var ch=75;
  var oh=50;
  var yon=0;
  YY=oh
  $("#nextA").css("bottom", YY);

  var reloc1 = function(){
    if(yon==0){YY=YY+xstep;}
    else{YY=YY-xstep;}
    oh=$("#nextA").height();
    if(YY>ch){yon=1;YY=ch;}
    if(YY<=oh){yon=0;YY=oh;}
    $("#nextA").css("bottom", YY);
  }
  window.setInterval(reloc1, delay_time);

  $("#contentDIV .content_section:eq(0)").css("top",0);

}
 