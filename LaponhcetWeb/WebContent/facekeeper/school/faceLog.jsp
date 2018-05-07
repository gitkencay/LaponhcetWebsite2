<%@ page import="com.laponhcet.util.*" %>

<div id="myCamera" style="display: none"></div>
	<input type="hidden" id="txtGroupNum" value="1" />
	<input type="hidden" id="txtRFID" />
	<div class="container" style="margin-top: 15px; height: 70%;">
		<div id='pict1' class="col-sm-6 pull-left"></div>
		<div class="col-sm-6 pull-right no-padding"
			style="height: 80%; overflow: hidden; position: relative;">
			<div class=""
				style="width: 107%; height: 85%; padding-right: 8%; overflow: hidden;">
				<div id='pict2' class='container col-sm-12 text-center'></div>
				<div id='pict3' class='container col-sm-12 text-center'></div>
				<div id='pict4' class='container col-sm-12 text-center'></div>
			</div>
		</div>
	</div>
	<div id="divPreviousLog" class="col-sm-12 previous-students text-center"></div>
	<div class="footer navbar-fixed-bottom"
		style="background:<%=SettingsUtil.OWNER_PRIMARY_COLOR%>">
		<marquee>

			<h1 style="color: white;">
				<%=SettingsUtil.OWNER_CODE%>
				FaceKeeper Powered by Technopal Software
			</h1>
		</marquee>
	</div>
</body>

<script>
	var logCount = 1;
	var previousLog = ["", "", "", "", "", "", "", "", "", ""];
	Webcam.set({
		width: 320,
		height: 250,
		image_format: 'jpg',
		jpeg_quality: 90,
	});
	Webcam.attach('#myCamera');
</script>

<script>
	function showAlert(msg, duration) {
		var el = document.createElement("div");
		el.setAttribute("style","text-align:center;position:absolute;top:75%;left:70%;padding:20px 50px;border-radius:10px;background-color:<%=SettingsUtil.OWNER_PRIMARY_COLOR%>;box-shadow: 2px 3px 3px 1px rgba(0, 0, 0, 0.2);font-weight:600;color:white; opacity:0.8;");
		 //}
		 el.innerHTML = "<h4>" + msg + "</h4>";
		 setTimeout(function() {
		 	el.parentNode.removeChild(el);
		 	}, duration);
		 document.body.appendChild(el);
	}
	
	function tap() {
		displayFace();
	}
	
	function displayFace() {
		if($('#txtRFID').val().length == 0) {
			showAlert("RFID is Empty", 5000);
		}
		else if($('#txtRFID').val().length < 10) {
			showAlert("RFID is Invalid", 5000);
		}
		else {
			Webcam.snap(function(dataUri) {
				$.ajax({
			    	url: 'AjaxController?txtSelectedLink=G00006',
			    	data: {
			    		rfid: $('#txtRFID').val().substring(0, 10),
			    		groupNum: $('#txtGroupNum').val(),
			    		pict: dataUri
			    	},
			    	method: 'POST',
			    	dataType: 'JSON',
			    	success: function(response) {
			    		if(response.errMsg) {
			    			showAlert(response.errMsg, 5000);
			    		}
			    		else {
			    			$("#txtGroupNum").val(response.nextGroupNum);
				    		$("#pict4").html($("#pict3").html());
					    	$("#pict3").html($("#pict2").html());
					    	$("#pict2").html($("#pict1").html());
					    	$("#pict1").html(getHTMLStr(response.isIn, response.timeInPict, response.profilePict, response.firstName, response.lastName, response.timestamp));
					    	appendLog(response.firstName, response.isIn, response.timeLog);
			    		}
			    	}
				});	
			});
		}
		$('#txtRFID').val("");
	}
	
	function getHTMLStr(isIn, timeInPict, profilePict, firstName, lastName, timestamp) {
		inOutStr = "OUT";
		inOutClass = "is_out";
		if(isIn) {
			inOutStr = "IN";
			inOutClass = "is_in";
		}
		return "<div class='pict_base'>"
				+ "		<div class='face_log'>"
				+ "			<img id='face_log' src='" +  timeInPict +"'>"
				+ "		</div>"
				+ "		<div class='bottom_part'>"
				+ "			<div class='avatar'>"
				+ "				<img id='avatar' src='" + profilePict +"'>"
				+ "			</div>"
				+ "			<div class='details carded'>"
				+ "   			<p class='firstname'>" + firstName + "</p>"
				+ "   			<p class='lastname'>" + lastName + "</p>"
				+ "   			<p class='timestamp'>" + timestamp + "</p>"
				+ "			</div>"
				+ "		</div>"
				+ "		<div class='log_count'>" + logCount + "</div>"
				+ "		<div class='" + inOutClass + "'>" + inOutStr + "</div>"
				
				+ "</div>";
	}
	
	function appendLog(firstName, isIn, time) {
		inOutStr = "OUT";
		if(isIn) {
			inOutStr = "IN";
		}
		
		previousHTMLStr = "<div class='students-log col-sm-2'>#" 
						+ logCount 
						+ " "
						+ firstName
						+ "	<b class='time-log'>"
						+ inOutStr
						+"	<i class='fa fa-clock-o fa-sm' aria-hidden='true'></i> " + time + "</b>"
						+ "	</div>";
			
		if(previousLog.length>=1) {
			for(i=9; i>0; i--) {
				previousLog[i] = previousLog[i-1];
			}
		}
		previousLog[0] = previousHTMLStr;
		
		
		$("#divPreviousLog").html("");
		if(logCount >= 4) {
			for(i=4; i<10; i++) {
				$("#divPreviousLog").html($("#divPreviousLog").html() + previousLog[i]);
			} 
		}
		logCount++;
	}
	
	$(document).on('keyup', function(e){
		if(e.keyCode == 13) {
			tap();
		}
		else {
			$("#txtRFID").val($("#txtRFID").val() + String.fromCharCode(e.keyCode));
		}
	})
</script>