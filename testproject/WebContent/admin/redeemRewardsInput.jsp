<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
	<head>

		<script>
	
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='showAllRewards';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#redeem_Rewards").validationEngine()
		});
	

</script>
		
		
	</head>
	<body>
		<s:if test="hasActionMessages()">
		<div class="infoHolder">
				<s:actionmessage />
		</s:if>
		<s:if test="hasActionErrors()">
		<div class="errorHolder, dynErrorHolder">
				<s:actionerror />
		</s:if>
		
		<s:form id="redeem_Rewards" name="redeem_Rewards" cssClass="formular"
			method="post">
			
			<h3>Redeem Points to	<span style="color:#6A0888"> <s:property value="userRewards.userDetail.firstName"  />
				<s:property value="userRewards.userDetail.lastName" /></span> </h3>
				
				User Name : <s:property value="userRewards.userDetail.firstName"  />
				<s:property value="userRewards.userDetail.lastName" /> <br>
				Total Points Accrued : <s:property value="userRewards.point"/> <br>
				Total points Redeemed So far : <s:property value="userRewards.redeemPoint"/> <br>
				Requested Redeem Points : <s:property value="userRewards.requestRedeemPoint"/>
			<s:textfield label="Enter Points" name="gTaskPoints"
				cssClass="required,validate[length[1,5]]" />
			
  		
  		<s:select label="Select Mode Of payment" headerKey="-1" headerValue="Select Mode Of payment"
				list="#{'Cheque':'Cheque','Cash':'Cash'}" name="userRewards.modeOfPayment" />
		<s:textarea name="userRewards.remarks" label="Remarks"></s:textarea>
			<s:submit value="Accept Redeem" action="redeemRewards"/>
			<s:hidden name="userRewards.rewardId" ></s:hidden>
			

			
		</s:form>
	</body>
</html>
