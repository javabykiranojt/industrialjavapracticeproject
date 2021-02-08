<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<style>

body {
    
    font-family: 'PT Sans',sans-serif,Verdana,Arial;
    font-size: 14px;
    line-height: 1.5em;
    margin: 0;
    padding: 0;
    text-align: left;
}

#wrap {
    height: 100%;
}
* html #wrap { height:100% }

#inner-wrap {
    padding-bottom:5%;
    min-height: 100%;
    padding-top:7%;
    width: 969px;
    margin: 0 auto;
}
#inner-wrap:after {
    content:" ";
    display:block;
    clear:both;

}
* html #inner-wrap {
    height:100%;
}
#header {
    background: url("common/../images/inner-top-bg.jpg") no-repeat scroll  top transparent;
    height: 254px;
    margin: 0 auto;
    padding: 0;
    width: 969px;
    position: relative;
}
#headerclient {
    background: url("common/../images/inner-top-bg-client.jpg") no-repeat scroll  top transparent;
    height: 254px;
    margin: 0 auto;
    padding: 0;
    width: 969px;
    position: relative;
}
.logo{position:absolute; z-index:1; top:10px; left:10px; width:315px;}
.logo img{float:left; border:none;}

.urtitle{position:absolute; z-index:1; top:38px; left:350px; width:245px;}
.urtitle img{float:left; border:none;}

.nav1{position:absolute; top:10px; right:50px; text-align:right;}
.nav1 a{color:#FFC; padding:0 0; margin:0 0; text-decoration:none;}
.nav1 a:hover{color:#ffffff; text-decoration:underline;}
.nav1 img{ vertical-align:middle; padding:0 5px; margin:0 0; border:none;}

<%--#header
{
    width: 100%;
    background-color: #190707;
    height: 50px;
    color: White;
    text-align: center;
    position: relative;
    top:0px;
}--%>
#menuheader
{	background:url(common/../images/inner-menu-bg.jpg) repeat-x top left;
    height: 26px;
    color: White;
    width: 960px;
    margin: 0 auto;
}
#footer
{
    width: 100%;
    background-color: #190707;
    height: 25px;
    position:absolute;
    bottom: 0;
    color: White;
    text-align: center;
}
.content
{
  	background: none repeat scroll 0 0 #8D5824;
    margin: 50px 0 0;
    padding: 20px 0 30px;
    width: 960px;
}
.content td.title{
	color: #FFFFCC;
    font-size: 13px;
    font-weight: 700;
    margin: 0;
    padding: 6px 0 6px 20px;
    }
.content td.detail{
    color: #FFFFFF;
    font-size: 13px;
    font-weight: 400;
    margin: 0;
    padding: 6px 0 6px 20px;
    }
	div.cell{
	float: none;
	padding-top: 0px;
	padding-right: 40px;
	padding-left: 0;
	list-style: square;
	}
.footer-area {
    background: none repeat scroll 0 0 #000000;
    color: #FFFFFF;
    font-family: 'PT Sans',sans-serif,Verdana,Arial;
    font-size: 13px;
    margin: 0;
    padding: 10px 0;
    width: 100%;
}
.footer-ca {
    margin: 0 auto;
    padding: 0 20px;
    width: 929px;
}
.social-media {
    float: right;
    text-align: right;
}
.social-media a {
    margin: 0 0 0 5px;
    padding: 0;
}
.ca-area {
   background: none repeat scroll 0 0 #8D5824;
   margin: 50px 0 0;
   padding: 20px 0 30px;
   width: 960px;
}
h3 {
    border-bottom: 1px solid #FFFFCC;
    color: #FFFFCC;
    font-size: 18px;
    font-weight: 400;
    margin: 0 20px 10px;
    padding: 0 0 10px;
}
input[type="button"], input[type="submit"] { 
    background: none repeat scroll 0 0 #AF8A66;
    border: medium none;
    color: #FFFFFF;
    cursor: pointer;
    font-size: 14px;
    font-weight: 700;
    padding: 4px 8px;
    text-decoration: none;
    text-transform: uppercase;
} 
</style>

	<head>
		<title><tiles:insertAttribute name="title" ignore="true" />
		</title>
	</head>


	<body>
		<div id="wrap">
		<s:if test="#session.usersType == 'CA'">
			<div id="headerclient">
		</s:if>
		<s:else>
			<div id="header">
		</s:else>
				<div class="logo">
					<img width="312" height="98"
						src="common/../images/logo-mysytery-shopper-inner.png">
				</div>
				<div class="urtitle">
					<s:if test="#session.usersType == 'UA'">
						<img width="243" height="63"
							src="common/../images/title-agent-page.png">
					</s:if>
					<s:if test="#session.usersType == 'CA'">
						<img width="243" height="63"
							src="common/../images/title-client-page.png">
					</s:if>
					<s:if test="#session.usersType == 'AE'">
						<img width="243" height="63"
							src="common/../images/title-admin-page.png">
					</s:if>
				</div>
				<div class="nav1">
					<%--<a href="#">Home</a>
					<img width="12" height="11" src="common/../images/bullet-icon.png">
					<a href="aplicationForm">Contact Us</a>
					<img width="12" height="11" src="common/../images/bullet-icon.png">
					<%--<a href="#">Login</a>
					--%><%--
					 <div class="nav1"><a href="about.html">Home</a> <img src="images/bullet-icon.png" width="12" height="11"/> <a href="http://www.kjnextdemo.com/dmall/aplicationForm.action">Contact Us</a> <img src="images/bullet-icon.png" width="12" height="11"/> <a href="#login-box" class="login-window">Login</a>
        </div>
					
					--%><s:if test="%{#session.UserName!=null}">
						<%if("M".equals(session.getAttribute("gender").toString())){ %>
							<i><font color="white"> Welcome Mr. <s:property
										value="%{#session.UserName}" /> </font> </i>
						<%} else if("F".equals(session.getAttribute("gender").toString())){%>

							<i><font color="white">Welcome Ms. <s:property
										value="%{#session.UserName}" /> </font> </i>
										<%}else{ %>
							<i><font color="white">Welcome <s:property
										value="%{#session.UserName}" /> </font> </i>
										<%} %>
						<br>
						<i><font color="white">Last accessed on :<s:property
									value="%{#session.lastAccesedTime}" /> </font> </i>
					</s:if>
				</div>
				<tiles:insertAttribute name="header" />
			</div>
			<div id="menuheader">
				<tiles:insertAttribute name="menuDropDown" />
			</div>

			<div id="inner-wrap">
				<tiles:insertAttribute name="body" />
			</div>
			<%--<div id="footer">
				<tiles:insertAttribute name="footer" />
			</div>
			--%>
			<div class="footer-area">
				<div class="footer-ca">
					&copy; Mystery Hotel
					<div class="social-media">
						<a href="#"><img src="common/../images/icon-fb.png" width="24"
								height="23" /> </a>
						<a href="#"><img src="common/../images/icon-tw.png" width="24"
								height="23" /> </a>
						<a href="#"><img src="common/../images/icon-in.png" width="24"
								height="23" /> </a>
					</div>
				</div>

			</div>
		</div>
	</body>