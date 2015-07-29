<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<link rel="stylesheet" type="text/css"  href="<c:url value='../../../style/imgSlider.css' />" />
<script type="text/javascript" src="<c:url value='../../../scripts/selectSlide.js' />"></script>

<%@ include file="../header.jsp" %>
<div class="container">
    <div id="leftnav" class="col-xs-4">
        <p class="lead">${title}</p>
        <ul class="list-group">
            <c:set var="slideIdStr" value=""/>
            <c:forEach var="slide" items="${slides}" varStatus="status">
                <c:set var="slideIdStr" value="${slideIdStr},${slide.id}"/>
            </c:forEach>

            <c:set var="index" value='0'/>
            <c:forEach var="part" items="${parts}" varStatus="status">
                <li class="list-group-item">${part.number}: ${part.topic}</li>
                <li class="list-group-item">
                    <ul class="list-group">

                        <c:forEach var="subPart" items="${part.subParts}" varStatus="status">

                            <li class="list-group-item" onclick=choose(this,"${id}","${index}") >${subPart.subNumber}: ${subPart.subTopic}</li>
                            <c:set var="index" value="${index+1}" />
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
   <div class="col-xs-8">
       <div id="imageShow">
           <div id="imgshow_mask"></div>
           <ul class="imagebg" id="imagebg">
               <c:forEach var="slide" items="${slides}" varStatus="status">
                   <c:set var="string1" value="${slide.filePath}"/>
                   <li data-sPic="<c:url value='${fn:substringBefore(string1,".ppt")}${slide.page}.PNG' />">
                       <img  class="bannerbg_main" src="<c:url value='${fn:substringBefore(string1,".ppt")}${slide.page}.PNG' />"  style='width:100%; height:100%;' />
                   </li>
               </c:forEach>

           </ul>

           <div class="scrollbg">
               <div class="scroll">
                   <a id="left_img_btn" class="s_pre" href="javascript:void(0)"></a>
                   <div class="current" id="current"></div>
                   <div class="outScroll_pic">
                       <ul class="scroll_pic cls" id="small_pic"></ul>
                   </div>
                   <a id="right_img_btn" class="s_next" href="javascript:void(0)"></a>
               </div>
           </div>

       </div>
   </div>
    <script type="text/javascript" src="<c:url value='../../../scripts/imgSlider.js' />"></script>
    <script type="text/javascript">
        img.init();
        img.play(0);
        //阻止事件冒泡
        function estop(e){
            var e=arguments.callee.caller.arguments[0]||event;
            if (e && e.stopPropagation){
                //因此它支持W3C的stopPropagation()方法
                e.stopPropagation();
            }else{
                //否则，我们需要使用IE的方式来取消事件冒泡
                window.event.cancelBubble = true;
                return false;
            }
        }
    </script>
</div>
<%@ include file="../footer.jsp" %>