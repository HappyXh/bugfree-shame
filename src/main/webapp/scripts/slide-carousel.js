$(function() {
  var showSumImgCount = 6;
  var sumImgCount = $("#slides-grid .thumbnail").length;
  var b = true;
  var f = function(){
    showSumImgCount = 6
    sumImgCount = $("#slides-grid .thumbnail").length;
    if (showSumImgCount > sumImgCount){
        showSumImgCount=sumImgCount
    }
  }
  var reindex = function(){
    var slides_outlines = $("a[id^=slides-outline-]")
    var slides_storylines = $("li[id^=storyline-]")
    for(var i =0;i<slides_outlines.length;i++){
      $(slides_outlines[i]).children("span").text(i+1)
      $(slides_outlines[i]).attr("id","slides-outline-"+(i+1))
      $(slides_storylines[i]).attr("id","storyline-"+(i+1))
    }
  }
  var a = function () {
    $("#leftnav .navbar-right .glyphicon-plus").unbind("click");
    $("#leftnav .navbar-right .glyphicon-minus").unbind("click");
    $("#leftnav .nav a").unbind("click");
    $("#leftnav .nav .nav li").unbind("click");
    $("#leftnav .nav .nav a[href^='#'] span").unbind("click");

    $("#leftnav>.nav>li>a").click(function () {
      $("#leftnav .nav li").removeClass("active");
      $(this).parent().addClass("active");
      var e = $(this).next().children("li")[0];
      var id = $(e).attr("id");
      id = id.substr(10, id.length);

      $(e).addClass("active");
      $("a[id^=slides-outline-]").removeClass("active");
      $("a[id=slides-outline-" + id + "]").addClass("active")
      $("#slides-outline").scrollTop((parseInt(id) - 1) * 127)
    })
    $("#leftnav .nav .nav li").click(function () {
      var id = $(this).attr("id");
      id = id.substr(10, id.length);

      $("#leftnav .nav li").removeClass("active");
      $(this).addClass("active")
      $(this).parent().parent().addClass("active");

      $("a[id^=slides-outline-]").removeClass("active");
      $("a[id=slides-outline-" + id + "]").addClass("active")
      $("#slides-outline").scrollTop((parseInt(id) - 1) * 127)
    })

    $("#leftnav .nav .nav a[href^='#'] span").click(function () {

      var value = $(this).prop("innerText");
      var html_str = '<input type="text" class="nav-input" >';
      e = $(this);
      $(this).hide();
      this.insertAdjacentHTML("afterEnd", html_str);
      $(".active input").focus();
      $(".active input").val("").focus().val(value);
      $(".active input").blur(function () {
        var s = $(this).val();
        e.text($(this).val());
        e.show();
        $(this).remove();
      })
      $(".active input").bind('keypress', function (event) {
        if (event.keyCode == "13") {
          $(this).blur();
          $("#search-txt").val($(this).val());
          $("#search-btn").click();
        }
      });
    })
    $("#leftnav .navbar-right .glyphicon-plus").click(function () {
      var id = $(this).parent().parent().attr("id");
      id = id.substr(10, id.length);

      var story_str = $(this).parent().parent().prop("outerHTML");
      this.parentNode.parentNode.insertAdjacentHTML("afterEnd", story_str);
      var outline_str = $("a[id=slides-outline-" + id + "]").prop("outerHTML")
      document.getElementById("slides-outline-" + id).insertAdjacentHTML("afterEnd", outline_str)

      reindex()
      a();
    })
    $("#leftnav .navbar-right .glyphicon-minus").click(function () {
      var id = $(this).parent().parent().attr("id");
      id = id.substr(10, id.length);
      $(this).parent().parent().remove()
      $("a[id=slides-outline-" + id + "]").remove()
      reindex()
    })
  }

  var l = function (r, s) {
    var q = r;
    $("#slides-carousel .news-smallimg-mask").parent().removeClass("cur-span");
    $("#slides-carousel .img-bars span[num=" + q + "]").addClass("cur-span");
    if (s == true) {
      $("#slides-carousel .img-viewer-cell").css("left", -q * 719);

      $("#slides-carousel .img-viewer").attr("num", q);
      $("#slides-carousel .img-bars span").removeClass("click-span");
      $("#slides-carousel .img-bars span[num=" + q + "]").addClass("click-span");
      d(q);

    }
  }
  var o = function (p) {
    $("#slides-carousel .img-bars-content").animate(
        {left: p}, 300, function () {
          b = true;
        });
    //$("#slides-carousel .img-bars-content").css("left",p);
  }
  var d = function (t) {
    b = false;
    if (t == 0) {
      o(0);
    } else if (t == sumImgCount - 1) {
      o(-(sumImgCount - showSumImgCount) * 115)
    } else {
      var s = $("#slides-carousel .img-bars-content").position().left;
      var r = s;
      var q = 115, p = parseInt(Math.abs(r) / q);
      if (t == p) s = r + 115;
      else if (t == p + showSumImgCount - 1) s = r - 115;
      o(s)
    }
  }

  var c = function () {
    $("#slides-carousel").delegate(".news-smallimg-mask", "mouseover", function () {
      var v = $(this).parent(), t = -1;
      t = v.attr("num");
      l(t, false);
    }).delegate(".news-smallimg-mask", "mouseout", function () {
      t = $("#slides-carousel .img-viewer").attr("num");
      l(t, false)
    }).delegate(".news-smallimg-mask", "mousedown", function () {
      var w = $(this).parent(), t = w.attr("num");
      if (t != $("#slides-carousel .img-viewer").attr("num")) {
        l(t, true);
      }
    }).delegate(".change-bigimg", "mouseover", function () {
      var w = $(this);
      if (w.hasClass("pre-img")) {
        w.children(".arrow").addClass("is-hover");
      } else {
        if (w.hasClass("next-img")) {
          w.children(".arrow").addClass("is-hover");
        }
      }

    }).delegate(".change-bigimg", "mouseout", function () {
      var w = $(this);
      if (w.hasClass("pre-img")) {
        w.children(".arrow").removeClass("is-hover");
      } else {
        if (w.hasClass("next-img")) {
          w.children(".arrow").removeClass("is-hover");
        }
      }
    }).delegate(".change-bigimg", "click", function () {
      if (!b) {
        return;
      }
      f();
      var x = $(this);
      var w = $("#slides-carousel .img-viewer"), t = w.attr("num");
      if (x.hasClass("pre-img")) {
        if (parseInt(t) == 0) {
          l(sumImgCount - 1, true);
        }
        else l(parseInt(t) - 1, true);
      } else {
        if (x.hasClass("next-img")) {
          if (parseInt(t) == sumImgCount - 1) {
            l(0, true);
          }
          else l(parseInt(t) + 1, true);
        }
      }

    }).mouseenter(function () {
      $(this).addClass("hasarrow");
    }).mouseleave(function () {
      $(this).removeClass("hasarrow")
    });
    $(".img-viewer .img-viewer-cell a img").click(function () {
      var id = $("li[id^=storyline-][class=active]").attr("id");
      id = id.substr(10, id.length);
      var slides_id = $(this).attr("slides_id")
      var img_path = $(this).attr("src")
      $("a[id=slides-outline-" + id + "]").attr("slides_id", slides_id)
      var e = $("a[id=slides-outline-" + id + "]").children("img")
      $(e).attr("src", img_path)
    })
    $("#slides-grid .thumbnail img").click(function () {
      var id = $("li[id^=storyline-][class=active]").attr("id");
      id = id.substr(10, id.length);
      var slides_id = $(this).attr("slides_id")
      var img_path = $(this).attr("src")
      $("a[id=slides-outline-" + id + "]").attr("slides_id", slides_id)
      var e = $("a[id=slides-outline-" + id + "]").children("img")
      $(e).attr("src", img_path)
    })
  }

  $("#search-btn").click(function(){
    var query_str =$("#search-txt").val()
    if(query_str == ""){
      $("#search-div").attr("class","input-group has-error")
      $("#search-txt").attr("placeholder","Search box is blank")
    }
    else{
      $("#search-div").attr("class","input-group")
      $("#search-txt").attr("placeholder","Search for...")
      $.ajax({
        url: "slide/search",
        data: {"features":query_str},
        dataType: 'json',
        type: 'POST',
        success: function (data) {
          var objJson= JSON.parse(data[0]);
          var grid_img_str = $($("#slides-grid div[class=col-xs-4]")[0]).html()
          grid_img_str = "<div class=\"col-xs-4\">" + grid_img_str + "</div>"
          var carousel_img_str = $($("#img-viewer-cell a")[0]).html()
          carousel_img_str = "<a>" + carousel_img_str + "</a>"
          var bar_img_str = $($("#img-bars-content").children()[0]).html()
          bar_img_str = "<span num=\"0\">" + bar_img_str + "</span>"
          //document.getElementById("imagebg").innerHTML="";

          $("#slides-grid").children().remove()
          $("#img-viewer-cell").children().remove()
          $("#img-bars-content").children().remove()
          $("#img-viewer").attr("num",0)
          for(var i=0;i<objJson.length;i++){
            var obj=objJson[i];
            var file_path = obj._source.filePath.substring(0,obj._source.filePath.indexOf('.'))+"-"+obj._source.page+".jpg"
            $("#slides-grid").append(grid_img_str)
            $("#slides-grid .thumbnail img:last").attr("src","http://7xoiwj.com1.z0.glb.clouddn.com/" + file_path)
            $("#slides-grid .thumbnail img:last").attr("slides_id",obj._id)
            $("#img-viewer-cell").append(carousel_img_str)
            $("#img-viewer-cell img:last").attr("src","http://7xoiwj.com1.z0.glb.clouddn.com/" + file_path)
            $("#img-viewer-cell img:last").attr("slides_id",obj._id)
            $("#img-bars-content").append(bar_img_str)
            $("#img-bars-content").children("span").last().attr("num",i)
            $("#img-bars-content img:last").attr("src","http://7xoiwj.com1.z0.glb.clouddn.com/" + file_path)
          }
          c();
          l(0, true);

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
  $("#search-txt").bind('keypress', function (event) {
    if (event.keyCode == "13") {
      $("#search-btn").click();
    }
  })


  $("#slides-outline").mouseover(function(){
    $(document.body).attr("style","overflow:hidden;")
  }).mouseout(function(){
    $(document.body).attr("style","overflow:auto;")
  })
  $("a[id^=slides-outline-]").mousedown(function(){
    var id = $(this).attr("id")
    id = id.substr(15,id.length)
    $("li[id^=storyline-]").removeClass("active");
    $("li[id=storyline-" + id + "]").addClass("active");
    $("li[id=storyline-" + id + "]").parent().parent().addClass("active");
    $("a[id^=slides-outline-]").removeClass("active")
    $(this).addClass("active")
  })



  $("#grid-view").click(function(){
    $("#slides-carousel").hide();
    $("#slides-grid").show();
  })
  $("#carousel-view").click(function(){
    $("#slides-grid").hide();
    $("#slides-carousel").show();
  })


  a();
  c();
  l(0, true);

})

function createPPT(){
  var temp = document.createElement("form");
  temp.action = "slide/createPPT";
  temp.method = "post";
  temp.style.display = "none";
  var opt = document.createElement("textarea");
  opt.name = "slideIdArr";
  opt.value =getSlideIdArr();
  temp.appendChild(opt);
  document.body.appendChild(temp);
  temp.submit();
}

function getSlideIdArr(){
  var slideIdArr=[];
  var slideImgBoxes= $("a[id^=slides-outline-]");
  for(var i=0;i<slideImgBoxes.length;i++){
    slideIdArr.push(parseInt(slideImgBoxes[i].getAttribute("slides_id")));
  }
  return slideIdArr
}


 