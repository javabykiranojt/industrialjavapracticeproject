<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/tld/jmesa.tld" prefix="jmesa"%>
<html>
	<head>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.8.2.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-ui.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jmesa.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.jmesa.js" /></script>

	<script type="text/javascript">
		function redeemRewards(){
				var rewardId=$("#rewardId").val();
				if(!isExist(rewardId)){
	 	 	return false;
	 	 } 
				popUpWithServerData("<%=request.getContextPath()%>/redeemRewardsInput.action?userRewards.rewardId="+$("#rewardId").val(),"Redeem Rewards",450,550);
			
			}

	</script>
		<script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
             function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                //alert(parameterString);
                location.href = '<%=request.getContextPath()%>/loadClient.action?' + parameterString;
            }
            function setId(id){
            	$("#rewardId").val(id.value);
            }
        </script>
	</head>
	<body>
		<h3 style="color: #fff"><s:text name="pageTitle"></s:text></h3>
		<form name="rewards_form">
			<jmesa:tableModel id="tag" items="${userRewardList}" maxRows="8"
				  maxRowsIncrements="4,8,12"
				stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="rewardId" title="Select" filterable="false">
							<input type="radio" name="productIdRadio" id="productIdRadio"
								value="${bean.rewardId}" onclick="setId(this);" />
						</jmesa:htmlColumn>
						<jmesa:htmlColumn property="userDetail.firstName" title="User FirstName" />
						<jmesa:htmlColumn property="userDetail.lastName" title="User LastName" />
						<jmesa:htmlColumn property="point" title="Reward Points"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="redeemPoint" title="Redeemed Points"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="requestRedeemPoint" title="Requested Points"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="createdDtm" title="Created On"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="modeOfPayment" title="Mode Of Payment"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="remarks" title="Remarks"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="modifiedDtm" title="Modified On"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="createdUIdName" title="created By"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="modifiedUIdName" title="modified By"></jmesa:htmlColumn>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			
			<input type="button" onclick="return redeemRewards()" value="Redeem Rewards" />
			<s:hidden name="userRewards.rewardId" id="rewardId" theme="simple" />
		</form>
	</body>
</html>


