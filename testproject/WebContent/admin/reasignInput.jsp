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
	

</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.timepicker.js" /></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/base.js" /></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jquery.timepicker.css"
	type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css"
	type="text/css">


<script>
		  $(function() {
			$('#tasks_time').timepicker({ 'scrollDefaultNow': true });
		  });
		</script>
<s:if test="hasActionMessages()">
	<div class="infoHolder" style="max-height: 150px;">
		<s:actionmessage />
	</div>
</s:if>
<s:if test="hasActionErrors()">
	<div class="errorHolder, dynErrorHolder">
		<s:actionerror />
	</div>
</s:if>
<body>
	<s:form id="add_task" name="add_task" method="post">

		<s:hidden name="tasks.taskId"></s:hidden>
		<s:hidden name="tasks.taskUniqueName"></s:hidden>
		Task unique name : <s:property value="tasks.taskUniqueName"/>
		<s:select list="reAssignUserList" label="User" name="userEmployeeId"
			headerKey="0" listKey="userEmpId" listValue="nameAndLocation"
			headerValue="-Select-" multiple="true" size="10"></s:select>

		<sx:datetimepicker label="Schedule Date" name="tasks.scheduleDate"
			displayFormat="dd-MM-yyyy" required="true" id="scheduleDate" />

		<sx:datetimepicker label="Expiry Date" name="tasks.expiryDate"
			displayFormat="dd-MM-yyyy" required="true" id="expiryDate" />
		<s:textfield name="tasks.time" label="Time :" id="tasks_time"
			cssClass="time"></s:textfield>
		<%--<s:textfield name="tasks.taskUniqueName" required="true"
			label="How you uniquely Identify this task "
			id="tasks_taskUniqueName"></s:textfield>
		--%>
		<s:textarea label="Venue/Landmark/Comments" name="tasks.venue"
			id="tasks_time" cssClass="validate[required,length[1,400]]"
			cssStyle="font-size: 13pt; important;" rows="5" cols="35" />
		<tr align="left">
		<s:if test="!hasActionErrors()">
			<td>
				<s:submit value="ADD task " onclick="return checkdate();"
					action="addTaskToOther" theme="simple" />
			</td>
		</s:if>
			<td>
				<s:submit value="Close" onclick="self.parent.location.reload()"
					action="closeAndUnlockTask" theme="simple" />
			</td>
		</tr>
	</s:form>
</body>
