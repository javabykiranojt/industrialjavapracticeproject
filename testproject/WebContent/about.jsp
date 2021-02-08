<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>

<head>
<meta charset="UTF-8">
<title>Indian Mystery Shopper</title>
<style type="text/css">
<!--
@import url("css/common.css");
@import url("css/inner.css");
-->
</style>


<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<!--[if IE 6]>
<script type="text/javascript" src="js/supersleight.js"></script>
<script src="js/DD_belatedPNG.js"></script>

<script>
  /* EXAMPLE */
  DD_belatedPNG.fix('.maincontainer');
  /* string argument can be any CSS selector */
  /* .png_bg example is unnecessary */
  /* change it to what suits you! */
</script>
<![endif]-->

<script type="text/javascript">
jQuery.noConflict();
// Code that uses other library's $ can follow here.
</script>
<!--JQuerys-->

</head>


<div class="maincontainer">
	<div class="topband">
		<div class="logo"><a href="#"><img src="images/logo-mysytery-shopper.png" width="549" height="181" alt=""/></a></div>
        
        <div class="nav"><a href="index.jsp">Home</a> <img src="images/bullet-icon.png" width="12" height="11"/> <a href="#">Contact Us</a> <img src="images/bullet-icon.png" width="12" height="11"/> <s:a href="login.jsp">Login</s:a></div>
        
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
        
        <div class="menu"><a href="about.jsp">About Us</a> | <a href="mystery-shopping.jsp">Mystery Shopping</a> | <a href="solutions.jsp">Solutions</a> | <a href="how-it-works.jsp">How IT Works</a> |<s:a href="signUpPage">Register</s:a></div>
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


