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

function redeem(){
	
	popUpWithServerData("<%=request.getContextPath()%>/redeemUserPointsInput.action","Redeem",450,600);
}
</script>

	</head>
	<form name="user_points">
		<div class="content">
			<h3 style="width: 300px;">
			
				My Points Table
			</h3><table>
			<tr class="title">
			<td width="50%" class="title">
				Net Points Available :
				</td>
				<td class="detail">
					<s:property value="userRewards.point" />
				</td>
			</tr>
			<tr><td width="50%" class="title">
				Points redeemed so far :</td>
				<td class="detail">
					<s:property value="userRewards.redeemPoint" />
				</td>
			</tr>
			<tr><td width="50%" class="title">
				Remarks From Administrator :</td>
				<td class="detail">
					<s:property value="userRewards.remarks" />
				</td>
			</tr>
		
			<tr><td width="50%" class="title">
				Mode Of Payment For Redeemed Points :</td>
				<td class="detail">
					<s:property value="userRewards.modeOfPayment" />
				</td>
			</tr>
			</table>
		</div>
		<s:if test="points>0"></s:if><br>
		<input type="button" value="Redeem" onclick="redeem()" />
	</form>