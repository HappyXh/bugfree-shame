<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<%@ include file="../header.jsp" %>
<div class="container">
   <div id="leftnav" class="col-xs-4">
       <ul class="list-group">
         <li class="list-group-item">Cras justo odio</li>
         <li class="list-group-item">Dapibus ac facilisis in</li>
         <li class="list-group-item">
            <ul class="list-group">
                <li class="list-group-item">Cras justo odio</li>
                <li class="list-group-item">Dapibus ac facilisis in</li>
                <li class="list-group-item">Morbi leo risus</li>
                <li class="list-group-item">Porta ac consectetur ac</li>
                <li class="list-group-item">Vestibulum at eros</li>
            </ul>
         </li>
         <li class="list-group-item">Porta ac consectetur ac</li>
         <li class="list-group-item">Vestibulum at eros</li>
       </ul>
   </div>
   <div class="col-xs-8">
        <div class="col-lg-10">
            <div class="input-group">
              <span class="input-group-addon" >PPT Slides</span>
              <input type="text" class="form-control" placeholder="Search for...">
              <span class="input-group-btn">
                <button class="btn btn-default" type="button">Go!</button>
              </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-10 -->
        <div id="slides-box" class="">
          <div class="col-xs-4 " onclick="focusOn(this)" ondblclick="select(this)">
            <div class="thumbnail">
              <img src="images/page.png" alt="..."   />
            </div>
          </div>
          <div class="col-xs-4" onclick="focusOn(this)" ondblclick="select(this)">
              <div class="thumbnail">
                <img src="images/page.png" alt="..."   />
              </div>
          </div>
          <div class="col-xs-4 " onclick="focusOn(this)" ondblclick="select(this)">
               <div class="thumbnail">
                 <img src="images/page.png" alt="..."   />
               </div>
          </div>
          <div class="col-xs-4 " onclick="focusOn(this)" ondblclick="select(this)">
              <div class="thumbnail">
                <img src="images/page.png" alt="..."   />
              </div>
          </div>
          <div class="col-xs-4" onclick="focusOn(this)" ondblclick="select(this)">
                <div class="thumbnail">
                  <img src="images/page.png" alt="..."   />
                </div>
          </div>
          <div class="col-xs-4 " onclick="focusOn(this)" ondblclick="select(this)">
                 <div class="thumbnail">
                   <img src="images/page.png" alt="..."   />
                 </div>
          </div>

          <div class="col-xs-4 " onclick="focusOn(this)" ondblclick="select(this)">
              <div class="thumbnail">
                <img src="images/page.png" alt="..."   />
              </div>
          </div>
          <div class="col-xs-4" onclick="focusOn(this)" ondblclick="select(this)">
                <div class="thumbnail">
                  <img src="images/page.png" alt="..."   />
                </div>
          </div>
          <div class="col-xs-4 " onclick="focusOn(this)" ondblclick="select(this)">
                 <div class="thumbnail">
                   <img src="images/page.png" alt="..."   />
                 </div>
          </div>
        </div>
   </div>
</div>
<%@ include file="../footer.jsp" %>