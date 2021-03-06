<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Project</title>


<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/common.css" type="text/css">
<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/inner.css" type="text/css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<style type="text/css">
<%--body{
	 background:#202020;
	 font:bold 12px Arial, Helvetica, sans-serif;
	 margin:0;
	 padding:0;
	 min-width:960px;
	 color:#bbbbbb; 
}--%>
body {
    background: url("common/../images/pg-bg-new.jpg") no-repeat scroll center top #000000;
    font-family: 'PT Sans',sans-serif,Verdana,Arial;
    font-size: 14px;
    line-height: 1.5em;
    margin: 0;
    padding: 0;
    text-align: left;
}

a { 
	text-decoration:none; 
	color:#00c6ff;
}

h1 {
	font: 4em normal Arial, Helvetica, sans-serif;
	padding: 20px;	margin: 0;
	text-align:center;
}

h1 small{
	font: 0.2em normal  Arial, Helvetica, sans-serif;
	text-transform:uppercase; letter-spacing: 0.2em; line-height: 5em;
	display: block;
}

h2 {
    color:#bbb;
    font-size:3em;
	text-align:right;
	text-shadow:0 1px 3px #161616;
}

.container {width: 960px; margin: 0 auto; overflow: hidden;}

.nav1{position:absolute; top:10px; right:50px; text-align:right; font-size:15px;}
.nav1 a{color:#FFC; padding:0 0; margin:0 0; text-decoration:none;}
.nav1 a:hover{color:#ffffff; text-decoration:underline;}
.nav1 img{ vertical-align:middle; padding:0 5px; margin:0 0; border:none;}
#content {	float: left; width: 100%;}

#header {
    background: url("common/../images/inner-top-bg.jpg") no-repeat scroll  top transparent;
    height: 254px;
    margin: 0 auto;
    padding: 0;
    width: 969px;
    position: relative;
}
<%--.btn-sign {
	width:200px;
	margin-bottom:10px;
	margin:0 auto;
	padding:10px;
	border-radius:2px;
	background: -moz-linear-gradient(center top, #00c6ff, #018eb6);
    background: -webkit-gradient(linear, left top, left bottom, from(#00c6ff), to(#018eb6));
	background:  -o-linear-gradient(top, #00c6ff, #018eb6);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='#00c6ff', EndColorStr='#018eb6');
	text-align:center;
	font-size:20px;
	color:#fff;
	text-transform:uppercase;
}
--%>
.btn-sign a { color:#fff; text-shadow:0 1px 2px #161616; }

#mask {
	display: none;
	background: #000; 
	position: fixed; left: 0; top: 0; 
	z-index: 10;
	width: 100%; height: 100%;
	opacity: 0.8;
	z-index: 999;
}

.login-popup{
	display:none;
	background: #DBD8D5;
	padding: 10px; 	
	border: 2px solid #ddd;
	float: left;
	font-size: 1.2em;
	position: fixed;
	top: 20%; left: 72%;
	z-index: 99999;
	box-shadow: 0px 0px 20px #999;
	-moz-box-shadow: 0px 0px 20px #999; /* Firefox */
    -webkit-box-shadow: 0px 0px 20px #999; /* Safari, Chrome */
	border-radius:3px 3px 3px 3px;
    -moz-border-radius: 3px; /* Firefox */
    -webkit-border-radius: 3px; /* Safari, Chrome */
}

img.btn_close {
	float: right; 
	margin: -28px -28px 0 0;
	height:25px;
	width:25px
}

fieldset { 
	border:none; 
}

form.signin .textbox label { 
	display:block; 
	padding-bottom:7px; 
}

form.signin .textbox span { 
	display:block;
}

form.signin p, form.signin span { 
	color:#999; 
	font-size:11px; 
	line-height:18px;
} 

form.signin .textbox input { 
	background:#666666; 
	border-bottom:1px solid #333;
	border-left:1px solid #000;
	border-right:1px solid #333;
	border-top:1px solid #000;
	color:#fff; 
	border-radius: 3px 3px 3px 3px;
	-moz-border-radius: 3px;
    -webkit-border-radius: 3px;
	font:13px Arial, Helvetica, sans-serif;
	padding:6px 6px 4px;
	width:200px;
}

form.signin input:-moz-placeholder { color:#bbb; text-shadow:0 0 2px #000; }
form.signin input::-webkit-input-placeholder { color:#bbb; text-shadow:0 0 2px #000;  }

.button { 
	background: -moz-linear-gradient(center top, #f3f3f3, #dddddd);
	background: -webkit-gradient(linear, left top, left bottom, from(#f3f3f3), to(#dddddd));
	background:  -o-linear-gradient(top, #f3f3f3, #dddddd);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='#f3f3f3', EndColorStr='#dddddd');
	border-color:#000; 
	border-width:1px;
	border-radius:4px 4px 4px 4px;
	-moz-border-radius: 4px;
    -webkit-border-radius: 4px;
	color:#333;
	cursor:pointer;
	display:inline-block;
	padding:6px 6px 4px;
	margin-top:10px;
	font:12px; 
	width:214px;
}

.button:hover { background:#ddd; }
.btn {
	-moz-box-shadow:inset 0px 1px 0px 0px #cae3fc;
	-webkit-box-shadow:inset 0px 1px 0px 0px #cae3fc;
	box-shadow:inset 0px 1px 0px 0px #cae3fc;
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #79bbff), color-stop(1, #4197ee) );
	background:-moz-linear-gradient( center top, #79bbff 5%, #4197ee 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#79bbff', endColorstr='#4197ee');
	background-color:#79bbff;
	-webkit-border-top-left-radius:16px;
	-moz-border-radius-topleft:16px;
	border-top-left-radius:16px;
	-webkit-border-top-right-radius:16px;
	-moz-border-radius-topright:16px;
	border-top-right-radius:16px;
	-webkit-border-bottom-right-radius:16px;
	-moz-border-radius-bottomright:16px;
	border-bottom-right-radius:16px;
	-webkit-border-bottom-left-radius:16px;
	-moz-border-radius-bottomleft:16px;
	border-bottom-left-radius:16px;
	text-indent:0px;
	border:2px solid #469df5;
	display:inline-block;
	color:#ffffff;
	font-family:Arial;
	font-size:14px;
	font-weight:bold;
	font-style:normal;
	height:25px;
	line-height:20px;
	width:70px;
	text-decoration:none;
	text-align:center;
	text-shadow:1px 14px 6px #287ace;
}
.btn:hover {
	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #4197ee), color-stop(1, #79bbff) );
	background:-moz-linear-gradient( center top, #4197ee 5%, #79bbff 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#4197ee', endColorstr='#79bbff');
	background-color:#4197ee;
}.btn:active {
	position:relative;
	top:1px;
}
.forget{
font:Arial, Helvetica, sans-serif;
font-size:14px;
color:#333;	
}
.forget a{
	font:Arial, Helvetica, sans-serif;
font-size:14px;
color:#333;
text-decoration:none;
}
.forget a:hover{
	font:Arial, Helvetica, sans-serif;
font-size:14px;
color:#CE4422;
text-decoration:none;
}

</style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('a.login-window').click(function() {
		
		// Getting the variable's value from a link 
		var loginBox = $(this).attr('href');
		//Fade in the Popup and add close button
		$(loginBox).fadeIn(300);
		
		//Set the center alignment padding + border
		var popMargTop = ($(loginBox).height() + 24) / 2; 
		var popMargLeft = ($(loginBox).width() + 24) / 2; 
		
		$(loginBox).css({ 
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});
		
		// Add the mask to body
		$('body').append('<div id="mask"></div>');
		$('#mask').fadeIn(300);
		
		return false;
	});
	
	// When clicking on the button close or the mask layer the popup closed
	$('a.close, #mask').live('click', function() { 
	  $('#mask , .login-popup').fadeOut(300 , function() {
		$('#mask').remove();  
	}); 
	return false;
	});
});
		$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});
</script>
<script type="text/javascript">
function signUp(){
	document.signIn.action="http://www.kjnextdemo.com/dmall/signUpPage.action";
	document.signIn.submit();	
}

function login(form) {
	var x=document.forms["signIn"]["users.uname"].value;
if (x==null || x=="")
  {
  alert("UserName name must be filled out");
  return false;
  }	
  var y=document.forms["signIn"]["users.upass"].value;
if (y==null || y=="")
  {
  alert("Password name must be filled out");
  return false;
  }		
		document.signIn.action="http://www.kjnextdemo.com/dmall/login.action";
		document.signIn.submit();		
		}
</script>

</head>

<body>
<div class="maincontainer">
	<div class="topband">
		<div class="logo"><a href="#"><img src="images/logo-mysytery-shopper.png" width="549" height="181" alt=""/></a></div>
        
        <div class="nav1"><a href="about.html">Home</a> <img src="images/bullet-icon.png" width="12" height="11"/> <a href="http://www.kjnextdemo.com/dmall/aplicationForm.action">Contact Us</a> <img src="images/bullet-icon.png" width="12" height="11"/> <a href="#login-box" class="login-window">Login</a>
        </div>
        <div id="login-box" class="login-popup">
        <a href="#" class="close"><img src="common/../images/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>
      
         <table><fieldset class="textbox"><h3 style="color: #786FD0; text-align:left; margin:5px; font-size:18px;  ">User Login</h3>
          <form method="post" class="signin" name="signIn">
           
               <tr style="height:25px">
               <td style="font-size:13px;font-family:Arial, Helvetica, sans-serif;vertical-align:middle">User Name</td><td>
                <input type="text" name="users.uname" key="label.username" id="username" placeholder="Username" required />
                </td></tr>
                <tr style="height:25px">
                <td style="font-size:13px;font-family:Arial, Helvetica, sans-serif;vertical-align:middle">Password</td><td>
                <input type="text" name="users.upass" key="label.password" id="password" placeholder="Password" />
                </td></tr>
                
                <tr><td></td><td>
					<input type="button" value="Login" onClick="return login(signIn)" class="btn"/>
					<input type="button" value="SignUp" onClick=" signUp();"  class="btn"/>
						</td></tr>
                        <tr>
                        <td colspan="2"><p>
               <span class="forget"> <a  href="http://www.kjnextdemo.com/dmall/userpassforget.action">Forgot your password?</a></span>
                </p></td>
                        </tr>
           </form>
          		
          </fieldset>
          </table>
		</div>
        <div class="boy"><img src="images/boy.png" width="312" height="450" /></div>
        <div class="girl" style="display:none;"><img src="images/girl.png" width="251" height="453" /></div>
        <div class="imgband">
    	<div class="main_view">
            <div class="window">	
               <div class="image_reel">
               <img src="images/imgband-01.jpg" width="959" height="365"/>
               <img src="images/imgband-02.jpg" width="959" height="365"/>
			   <img src="images/imgband-03.jpg" width="959" height="365"/>
			   <img src="images/imgband-04.jpg" width="959" height="365"/>
			   <img src="images/imgband-05.jpg" width="959" height="365"/>
            </div>
            </div>
            <div class="paging">
                <a href="#" rel="1"></a>
                <a href="#" rel="2"></a>
				<a href="#" rel="3"></a>
				<a href="#" rel="4"></a>
				<a href="#" rel="5"></a>
            </div>
        </div>
    	</div>
        
        <div class="menu"><a href="http://www.kjnextdemo.com/about.html">About Us</a> | <a href="http://www.kjnextdemo.com/mystery-shopping.html">Mystery Shopping</a> | <a href="http://www.kjnextdemo.com/solutions.html">Solutions</a> | <a href="http://www.kjnextdemo.com/how-it-works.html">How IT Works</a> | <a href="http://www.kjnextdemo.com/dmall/signUpPage.action">Register</a></div>
    </div>
    
    <div class="ca-area">
		<div class="col01">
        <h2>About Us</h2>
        <p>At Pinnacle India Mystery Shopper we influence brand value in to a firm belief by providing support with our research insight and industry expertise in customer service.</p>

        <p>We are young spirited though dynamic consulting firm focusing on day to day keen Customer Satisfaction Management and Assessment.</p>
        
        <p>We align your organization to enhance every customer experience at every touch point by helping you appraise and deliver exceptional customer service.</p>
        
        <p>Our agenda measure, evaluate and improve customer experiences across all your customer aspects; leading to greater customer retention, improved average transaction values and increased customer loyalty.</p>
        
        <p>Our evaluation and training programs will enlighten your focus on the performance of set values of your organization. Expert feedback through our advanced approach to experience evaluation, help you monitor and consider your progress towards achieving ultimate customer satisfaction.</p>
        </div>
        <div class="col02"><img src="images/banners.jpg" width="273" height="226" /></div>
        <div class="clear"><!--whitespace--></div>
	</div>


</div>

<div class="footer-area">
     <div class="footer-ca">&copy; Indian Mystery Shopper   <div class="social-media"><a href="#"><img src="images/icon-fb.png" width="24" height="23" /></a> <a href="#"><img src="images/icon-tw.png" width="24" height="23" /></a> <a href="#"><img src="images/icon-in.png" width="24" height="23" /></a></div></div>
        	
</div>

<script type="text/javascript">

jQuery(document).ready(function() {

	//Set Default State of each portfolio piece
	jQuery(".paging").show();
	jQuery(".paging a:first").addClass("active");
		
	//Get size of images, how many there are, then determin the size of the image reel.
	var imageWidth = jQuery(".window").width();
	var imageSum = jQuery(".image_reel img").size();
	var imageReelWidth = imageWidth * imageSum;
	
	//Adjust the image reel to its new size
	jQuery(".image_reel").css({'width' : imageReelWidth});
	
	//Paging + Slider Function
	rotate = function(){	
		var triggerID = jQueryactive.attr("rel") - 1; //Get number of times to slide
		var image_reelPosition = triggerID * imageWidth; //Determines the distance the image reel needs to slide

		jQuery(".paging a").removeClass('active'); //Remove all active class
		jQueryactive.addClass('active'); //Add active class (the $active is declared in the rotateSwitch function)
		
		//Slider Animation
		jQuery(".image_reel").animate({ 
			left: -image_reelPosition
		}, 500 );
		
	}; 
	
	//Rotation + Timing Event
	rotateSwitch = function(){		
		play = setInterval(function(){ //Set timer - this will repeat itself every 3 seconds
			jQueryactive = jQuery('.paging a.active').next();
			if ( jQueryactive.length === 0) { //If paging reaches the end...
				jQueryactive = jQuery('.paging a:first'); //go back to first
			}
			rotate(); //Trigger the paging and slider function
		}, 5000); //Timer speed in milliseconds (3 seconds)
	};
	
	rotateSwitch(); //Run function on launch
	
	//On Hover
	jQuery(".image_reel a").hover(function() {
		clearInterval(play); //Stop the rotation
	}, function() {
		rotateSwitch(); //Resume rotation
	});	
	
	//On Click
	jQuery(".paging a").click(function() {	
		jQueryactive = jQuery(this); //Activate the clicked paging
		//Reset Timer
		clearInterval(play); //Stop the rotation
		rotate(); //Trigger rotation immediately
		rotateSwitch(); // Resume rotation
		return false; //Prevent browser jump to link anchor
	});	
	
});
</script>
</body>
</html>
