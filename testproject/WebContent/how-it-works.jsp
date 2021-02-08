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


<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js">
</script>
<link rel="stylesheet" type="text/css" href="http://cdn.webrupee.com/font"/>
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
        
        <div class="nav"><a href="index.jsp">Home</a> <img src="images/bullet-icon.png" width="12" height="11"/> <a href="#">Contact Us</a> <img src="images/bullet-icon.png" width="12" height="11"/> <a href="#">Login</a></div>
        
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
            </div>
        </div>
        
        <div class="menu"><a href="about.html">About Us</a> | <a href="mystery-shopping.jsp">Mystery Shopping</a> | <a href="solutions.jsp">Solutions</a> | <a href="how-it-works.jsp">How IT Works</a> | <a href="#">Register</a></div>
    </div>
    
    <div class="ca-area">
		<div class="col01">
        <h2>How It Works</h2>
        <p>If you are thinking, it is too good to be true to get paid to shop, below are a few facts about what it is like being a mystery shopper:

		<p>Every assignment can be different, depending on the clients need. The one thing you must remember not to reveal yourself as a mystery shopper - pretending as a normal customer is the key of success.</p>


        <h3>Getting paid to do shopping</h3>
        
        <p>Yes of course! We will provide you an assignment at a specified store, or outlet, or hotel and to act out a scenario whilst posing as an ordinary customer.</p>
        
        <p>Most of the assignments involve you to make a purchase to assess customer service and efficiency at the service counter and some may ask you to return a purchase to evaluate the stores return/refund policy.</p> 
        
        <p>The assignment will usually involve monitoring staff competency, customer service and venue cleanliness and atmosphere with in the outlet. You might also be asked to check on merchandising or asked to audit a store in some way.</p>
        
        <h3>To make your assignment successful, the qualities you should posses;</h3>
        
        <p>Superb observational skills<br/>
        Computer literacy<br/>
        Competency in regional languages<br/>
        Compel strict deadlines<br/>
        Flexibility and enthusiasm</p>
        
        <p>You will be expected to upload your findings of each assignment, generally by the same day or the day after at the latest.</p>
        
        
        <h3>How much perk will I get?</h3>
        
        <p>On average assignments pay <span class="WebRupee">Rs.</span> 250 – <span class="WebRupee">Rs.</span> 500 along with additional reimbursement towards any purchases you are required to make</p> 
        
        <p>The pay rates differ from assignment to assignment depending on the depth of the survey.</p> 
        
        <h3>Selection and assignment allocation</h3>
        
        <p>Without any delay, register your interest online with thrives for excellence and professionalism. We use this information to hand out you the assignments which you are most likely to be suited to. In certain circumstances you may be conducting the assignments in respect to your specific field expertise. Also as per the clients need at instance you are required to undertake a test assignment for training purposes.</p>
        
        <p>Once your registration approved, we contact you with appropriate assignments line up for you to accept. You will be offered a choice to select assignments carefully that will fit your time and location criteria.</p>
        
        <h3>Assignment submission:</h3>
        
        <p>The deadlines for all the assignments are very tight and the assignment should be accepted only when you are totally convinced about completing it in the given strict time frame. Your competency in report writing can play a vital role in submitting your findings with accuracy.
        
        <p>All the reports and findings of each and every assignments should be fill in the specific On-line form only.</p>
        
        <p>So if you want to become a mystery shopper with Market Force <a href="#">click here</a> </p>
        </div>
        <div class="col02"><img src="images/home-pg-img.jpg" width="285" height="286" /></div>
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
		}, 3000); //Timer speed in milliseconds (3 seconds)
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


