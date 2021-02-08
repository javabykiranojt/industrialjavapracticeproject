<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<sx:head />
<style>
.label {
    font-style: normal;
    font-size: 15px;
    font-weight: 700;
    line-height: 1.5em;
}
.tdLabel {
    text-align: justify;
}

    
    
element.style {
    display: block;
    left: 19px;
    position: absolute;
    top: 114.75px;
}
.ui-timepicker-wrapper {
    background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #DDDDDD;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
    height: 150px;
    margin: 0;
    outline: medium none;
    overflow-y: auto;
    width: 6.5em;
    z-index: 10001;
}

.ui-timepicker-list {
    list-style: none outside none;
    margin: 0;
    padding: 0;
}
li.ui-timepicker-selected, .ui-timepicker-list li:hover, .ui-timepicker-list .ui-timepicker-selected:hover {
    background: none repeat scroll 0 0 #1980EC;
    color: #FFFFFF;
}
.ui-timepicker-list li {
    color: #000000;
    cursor: pointer;
    list-style: none outside none;
    margin: 0;
    padding: 3px 0 3px 5px;
    white-space: nowrap;
}

</style>	
		
	<script>
	
	 function checkdate()
	 {
	    //alert("Checking data 19");
	    var expiryDate=dojo.widget.byId("expiryDate").getValue();
	    var scheduleDate=dojo.widget.byId("scheduleDate").getValue();
	       if(expiryDate==''||scheduleDate==''){
    	         alert("Dates can not be blank.");
	             return false;
	       }
	       if(expiryDate<scheduleDate )
	       {
	             alert("Expiry date cannot be smaller than Scheduled date.");
	             return false;
	       }
	 }
	
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='showAllTask.action';
	}
	
	//JQuery Ajax code
	function differentUsersAndClients(locId1){
		var url="<%=request.getContextPath()%>/differentClients.action?location.locId="+locId1;
		$("#clientDropdown").load(url,{noncache: new Date().getTime()});
		url="<%=request.getContextPath()%>/differentUsers.action?location.locId="+locId1;
		$("#userDropdown").load(url,false);
	}
	
	
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.timepicker.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/base.js" /></script>
			<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-ui-timepicker-addon.js" /></script>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/jquery-ui.css" type="text/css">
			<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/jquery.timepicker.css" type="text/css">
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/base.css" type="text/css">
			

		<script>
		  $(function() {
			$('#tasks_time').timepicker({ 'scrollDefaultNow': true });
		  });
		</script>
		<script>
 			 $(function() {
   			 $( "#scheduleDate" ).datepicker();
 				 });
 		 </script>
 		 <script>
 			 $(function() {
   			 $( "#expiryDate" ).datepicker();
 				 });
 		 </script>
			
		<body>
		
		
		
		 <s:if test="hasActionMessages()">
				 <div class="infoHolder">
					<s:actionmessage />
				</div>
		</s:if>
		<s:if test="hasActionErrors()">
				 <div class="errorHolder, dynErrorHolder">
					<s:actionerror />
				</div>
		</s:if>
		<s:form id="add_task" name="add_task" 
			method="post">
			
			<s:select list="locationList" id="locId1" label="Select City"
				listKey="locId" listValue="city" name="locationId" headerKey="0"
				headerValue="-Select-" onchange="differentUsersAndClients(this.value)"></s:select>
				
			<s:select list="clientsList" id="clientDropdown" label="Client Store" listKey="clientId"
				listValue="clientName" name="frmClientsId" headerKey="0" 
				headerValue="-Select-"></s:select>	

			<s:select list="userList" id="userDropdown" label="Agent Name" listKey="userEmpId"
				listValue="firstName" name="userEmployeeId" headerKey="0"
				headerValue="-Select-" multiple="true" size="5" ></s:select>

			<s:textfield label="Schedule Date" name="tasks.scheduleDate"
				required="true" id="scheduleDate" />
			
			<s:textfield label="Expiry Date" name="tasks.expiryDate"
				required="true" id="expiryDate" />
			<s:textfield name="tasks.time" label="Time :" id="tasks_time" cssClass="time"></s:textfield>	
			<s:textfield name="tasks.taskUniqueName" required="true" label="Task Unique Name" id="tasks_taskUniqueName" maxlength="65" size="40"></s:textfield>	
			<s:textarea label="Venue/Landmark/Comments" name="tasks.venue" id="tasks_time"
				cssStyle="font-size: 13pt; important;" rows="5" cols="35" cssClass="required,validate[length[1,1200]]" />
			<tr align="left"><td>
			<s:if test="!LockedByFlg && !hasActionMessages()">
				
					<s:submit value="ADD task" onclick="return checkdate();checkClient();" action="addTask" theme="simple"/>
			</s:if>
					<s:submit value="Close"  onclick="self.parent.location.reload()" action="closeAndUnlockTask" theme="simple"/>
				</td>
		</tr></s:form>
</body>
