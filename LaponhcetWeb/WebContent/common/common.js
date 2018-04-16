function showDialogLoading() {
	$('#divDialogLoading').modal({
		  backdrop: 'static',
		  keyboard: false,
		  show: true
	});
}

function showDialogHelp(helpPage) { 
	if(helpPage == "") {
		showDialogMessage("MSG_CLASS_ACTION_FAIL", "ACTION FAIL", "No Help File yet on this module");
	}
	else {
		/*$('#divDialog').html("<embed height='100%' width='100%' src='./files/help/" + helpPage + "' type='video/mp4'>").dialog({*/
		$('#divDialog').html("<embed height='100%' width='100%' src='" + helpPage + "' type='video/mp4'>").dialog({
			height: 600,
			width: 1000,
			modal: true
		});
	}
}       

function openLink(linkCode) {
	document.getElementById("txtSelectedLink").value = linkCode;
	showDialogLoading();
	document.getElementById("frmMain").action = "MainController";
	document.getElementById("frmMain").enctype="application/x-www-form-urlencoded";
	document.getElementById("frmMain").encoding="application/x-www-form-urlencoded";
	document.getElementById("frmMain").submit();
}

function recordAction(recordId, linkCode) {
	document.getElementById("txtSelectedRecord").value = recordId;
	openLink(linkCode);
}

function toggleDisplay(ele){
	var srcElement = document.getElementById(ele);
	if(srcElement!=null){
		if(srcElement.style.display == "none") {
			srcElement.style.display="block";
		}
		else {
			srcElement.style.display="none";
		}
	}
	return false;
}

function toggleEnable(ele){
	var srcElement = document.getElementById(ele);
	if(srcElement!=null){
		//srcElement.value = "";
		if(srcElement.disabled == true) {
			srcElement.disabled=false;
			//srcElement.focus();
		}
		else {
			srcElement.disabled=true;
		}
	}
	return false;
}

function hideFormElement(elementId) {
	document.getElementById(elementId).style.display = "none";
}

function showFormElement(elementId) {
	document.getElementById(elementId).style.display = "";
}

function setElementVal(elementId, val) {
	document.getElementById(elementId).value = val;
}

function setElementInnerHTML(elementId, val) {
	document.getElementById(elementId).innerHTML = val;
}

function removeOptions(selectbox, header, headerValue) {
	for(var i=selectbox.options.length-1; i>=0; i--) {
		selectbox.remove(0);
	}
	if(header != "") {
		var opt = document.createElement("option");
		opt.value = headerValue;
		opt.text = header;
		selectbox.appendChild(opt);
	}
}

function toggleCheckListByPrefixId(chkParent) {
	var element= document.getElementsByTagName("input");
    for (var i =0; i < element.length; i++){
        if (element[i].type == 'checkbox') {
        	if(element[i].id.includes(chkParent.id)) {
        		if(chkParent.checked) {
        			element[i].checked = true;
        		}
        		else {
        			element[i].checked = false;
        		}
        	}
        }
    } 
}

function toggleCheckParent(chkParent, chkChildrenCount) {
	var checkCount = 0;
	for (var i =0; i < chkChildrenCount; i++){
		var chkStr = chkParent + i;
		if(document.getElementById(chkStr).checked) {
			checkCount++;
		}
    } 
	if(checkCount == chkChildrenCount) {
		document.getElementById(chkParent).checked = true;
	}
	else {
		if(checkCount == 0) {
			document.getElementById(chkParent).checked = false;
		}
	}
}

function getPosX(obj) {
	var curleft = 0;
	if(obj.offsetParent) {
		while(1) {
			curleft += obj.offsetLeft;
			if(!obj.offsetParent)
				break;
			obj = obj.offsetParent;
	    }
	} 
	else if(obj.x) {
		curleft += obj.x;
	}
	obj.style.position = "static";

	return curleft;
}

function getPosY(obj) {
	var curtop = 0;
	if(obj.offsetParent) {
	    while(1) {
	    	curtop += obj.offsetTop;
	    	if(!obj.offsetParent)
	    		break;
	    	obj = obj.offsetParent;
	    }
	} 
	else if(obj.y) {
		curtop += obj.y;
	}
	return curtop;
}

function getCurrentCursorPosition(e) {
	var posX =0, posY = 0;
	if( !e ) { e = window.event; } if( !e || ( typeof( e.pageX ) != 'number' && typeof( e.clientX ) != 'number' ) ) { posX = 0; posY = 0; }
	if( typeof( e.pageX ) == 'number' ) { posX = e.pageX; posY = e.pageY; } else {
		var posX = e.clientX; var posY = e.clientY;
		if( !( ( window.navigator.userAgent.indexOf( 'Opera' ) + 1 ) || ( window.ScriptEngine && ScriptEngine().indexOf( 'InScript' ) + 1 ) || window.navigator.vendor == 'KDE' ) ) {
			if( document.documentElement && ( document.documentElement.scrollTop || document.documentElement.scrollLeft ) ) {
				posX += document.documentElement.scrollLeft; posY += document.documentElement.scrollTop;
			} 
			else if( document.body && ( document.body.scrollTop || document.body.scrollLeft ) ) {
				posX += document.body.scrollLeft; posY += document.body.scrollTop;
			}
		}
	}
	return [posX, posY];
}

function getStyle(oElm, strCssRule){
	var strValue = "";
	if(document.defaultView && document.defaultView.getComputedStyle){
		strValue = document.defaultView.getComputedStyle(oElm, "").getPropertyValue(strCssRule);
	}
	else if(oElm.currentStyle){
		strCssRule = strCssRule.replace(/\-(\w)/g, function (strMatch, p1){
			return p1.toUpperCase();
		});
		strValue = oElm.currentStyle[strCssRule];
	}
	return strValue;
}

function numbersonly(myField, e, dec, maxChar) {
	if(isWithinLength) {
		var key;
		var keychar;
		
		if (window.event)
			key = window.event.keyCode;
		else if (e)
			key = e.which;
		else
			return true;
			keychar = String.fromCharCode(key);
			//control keys
			if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) )
				return true;
			// numbers
			else if ((("-0123456789").indexOf(keychar) > -1) && myField.value.length<maxChar)
			   return true;
			// decimal point jump
			else if (dec && (keychar == ".")) {
				myField.form.elements[dec].focus();
			   return false;
			}
			else
			   return false;
	}
	else {
		return false;
	}
}

/*function integeronly(e) {
	var key;
	var keychar;
	
	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;
		keychar = String.fromCharCode(key);
		//control keys
		if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) )
			return true;
		//numbers
		else if ((("0123456789").indexOf(keychar) > -1))
		   return true;
		//decimal point jump
		else
		   return false;
}*/

function getTimeByAddedMinutes(timeStr, minute) {
	var d1 = new Date(); 
	d1.setHours(timeStr.split(":")[0], timeStr.split(":")[1], 0, 0);

	var d2 = new Date();
	d2.setTime(d1.getTime() + (minute * 60 * 1000));

	var newTime = "";
	var hour = d2.getHours();
	var minute = d2.getMinutes();
	if(d2.getHours()<10) {
		hour = "0" + d2.getHours();
	}
	
	if(d2.getMinutes()<10) {
		minute = "0" + d2.getMinutes();
	}
	
	newTime = newTime.concat(hour, ":", minute);
	return newTime;
}

function integeronly(myField, e, maxChar) {
	var key;
	var keychar;
	
	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;
		keychar = String.fromCharCode(key);
		//control keys
		if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) )
			return true;
		//numbers
		else if ((("0123456789").indexOf(keychar) > -1) && myField.value.length<maxChar)
		   return true;
		//decimal point jump
		else
		   return false;
}

function lettersonly(e) {
	var key;
	var keychar;
	
	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;
		keychar = String.fromCharCode(key);
		//control keys
		if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) )
			return true;
		//letters
		else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
		   return true;
		else
		   return false;
}

function isWithinLength(myField, e, maxChar) {
	var key;
	var keychar;
	
	if (window.event)
		key = window.event.keyCode;
	else if (e)
		key = e.which;
	else
		return true;
		keychar = String.fromCharCode(key);
		//control keys
		if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) )
			return true;
		//letters
		if(myField.value.length<maxChar) {
			return true;
		}
		else {
			return false;
		}
}

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}

String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}

String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}

function searchRecord(linkCode) {
	openLink(linkCode);
	if(document.getElementById("txtSearchValue") != null) {
		document.getElementById("txtSearchValue").focus(); 
	}
}

function readDataURI(fileInput, maxSize, fileExtensionList, name) {
	if (fileInput.files && fileInput.files[0]) {
		if(isFileExtensionExist(fileInput.value, fileExtensionList)) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	        	if(maxSize>0 && (fileInput.files[0].size/maxSize > 1)) {
	        		swal("Upload " + name, "File is too large. Please upload a file not more than " + maxSize/1024000 + " Mb", "error");
	        		$(".fileinput").fileinput('clear');
					setElementVal('txt'+name, '');
	        	}
	        	else {
	        		document.getElementById("txt" + name).value = e.target.result;
	        	}
	        }
	        reader.readAsDataURL(fileInput.files[0]);
	    }
		else {
			swal("Upload " + name, "Invalid File Type", "error");
			$(".fileinput").fileinput('clear');
			setElementVal('txt'+name, '');
		}
	}
}

function readDataURIAndSubmit(fileInput, maxSize, fileExtensionList, name) {
	if (fileInput.files && fileInput.files[0]) {
		if(isFileExtensionExist(fileInput.value, fileExtensionList)) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	        	if(maxSize>0 && (fileInput.files[0].size/maxSize > 1)) {
	        		swal("Upload " + name, "File is too large. Please upload a file not more than " + maxSize/1024000 + " Mb", "error");
	        		$(".fileinput").fileinput('clear');
					setElementVal('txt'+name, '');
	        	}
	        	else {
	        		document.getElementById("txt" + name).value = e.target.result;
	        		openLink(onChangeLinkCode);
	        	}
	        }
	        reader.readAsDataURL(fileInput.files[0]);
	    }
		else {
			swal("Upload " + name, "Invalid File Type", "error");
			$(".fileinput").fileinput('clear');
			setElementVal('txt'+name, '');
		}
	}
}

function getFileExtension(filename) {
  var ext = /^.+\.([^.]+)$/.exec(filename);
  return ext == null ? "" : ext[1];
}

function isFileExtensionExist(filename, fileExtensionList) {
	var ext = getFileExtension(filename);
	for(i=0; i<fileExtensionList.length; i++) {
	   if(fileExtensionList[i].toUpperCase() == ext.toUpperCase()) {
		   return true;
	   }
	}
	return false;
}

//Printing
function printContent(elementId, title){
	str=document.getElementById(elementId).innerHTML;
	newwin=window.open('','printwin');
	newwin.document.write('<HTML>\n<HEAD>\n');
	newwin.document.write('<link href="common/adminLTE/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">\n');
	newwin.document.write('<link href="common/adminLTE/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">\n');
	newwin.document.write('<link href="common/adminLTE/plugins/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css">\n');
	newwin.document.write('<link href="common/adminLTE/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css">\n');
	newwin.document.write('<script src=\"common/adminLTE/plugins/jQuery/jQuery-2.1.4.min.js\" type=\"text/javascript\"></script>');
	newwin.document.write('<TITLE>Ready to Print - ' + title + '</TITLE>\n');
	newwin.document.write('</HEAD>\n');
	newwin.document.write('<BODY style="font-size:xx-small;" onload="window.print();">\n');
	newwin.document.write(str);
	newwin.document.write('</BODY>\n');
	newwin.document.write('</HTML>\n');
	newwin.document.close();
}

//Sprite Animation
(function() {
    var lastTime = 0;
    var vendors = ['ms', 'moz', 'webkit', 'o'];
    for(var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
        window.requestAnimationFrame = window[vendors[x]+'RequestAnimationFrame'];
        window.cancelAnimationFrame = window[vendors[x]+'CancelAnimationFrame'] 
                                   || window[vendors[x]+'CancelRequestAnimationFrame'];
    }
 
    if (!window.requestAnimationFrame)
        window.requestAnimationFrame = function(callback, element) {
            var currTime = new Date().getTime();
            var timeToCall = Math.max(0, 16 - (currTime - lastTime));
            var id = window.setTimeout(function() { callback(currTime + timeToCall); }, 
              timeToCall);
            lastTime = currTime + timeToCall;
            return id;
        };
 
    if (!window.cancelAnimationFrame)
        window.cancelAnimationFrame = function(id) {
            clearTimeout(id);
        };
	}()
);

function animate(divId, imgFile, spriteWidth, imgWidth, imgHeight) {	
	var obj,
		objImage,
		canvas;					
	function move () {
	  window.requestAnimationFrame(move);
	  obj.update();
	  obj.render();
	}
	
	function sprite (options) {
		var that = {},
			frameIndex = 0,
			tickCount = 0,
			ticksPerFrame = options.ticksPerFrame || 0,
			numberOfFrames = options.numberOfFrames || 1;
		
		that.context = options.context;
		that.width = options.width;
		that.height = options.height;
		that.image = options.image;
		
		that.update = function () {
            tickCount += 1;
            if (tickCount > ticksPerFrame) {
				tickCount = 0;
                // If the current frame index is in range
                if (frameIndex < numberOfFrames - 1) {	
                    // Go to the next frame
                    frameIndex += 1;
                } 
                else {
                    frameIndex = 0;
                }
            }
		};
		
		that.render = function () {
		  // Clear the canvas
		  that.context.clearRect(0, 0, that.width, that.height);
		  
		  // Draw the animation
		  that.context.drawImage(
		    that.image,
		    frameIndex * that.width / numberOfFrames,
		    0,
		    that.width / numberOfFrames,
		    that.height,
		    0,
		    0,
		    that.width / numberOfFrames,
		    that.height);
		};
		return that;
	}
	
	// Get canvas
	canvas = document.getElementById(divId);
	canvas.width = imgWidth;
	canvas.height = imgHeight;
	
	// Create sprite sheet
	objImage = new Image();	
	
	// Create sprite
	obj = sprite({
		context: canvas.getContext("2d"),
		width: spriteWidth,
		height: imgHeight,
		image: objImage,
		numberOfFrames: 20,
		ticksPerFrame: 10
	});
	
	// Load sprite sheet
	objImage.addEventListener("load", move);
	objImage.src = imgFile;
}

function refreshAt(hours, minutes, seconds) {
    var now = new Date();
    var then = new Date();

    if(now.getHours() > hours ||
       (now.getHours() == hours && now.getMinutes() > minutes) ||
        now.getHours() == hours && now.getMinutes() == minutes && now.getSeconds() >= seconds) {
        then.setDate(now.getDate() + 1);
    }
    then.setHours(hours);
    then.setMinutes(minutes);
    then.setSeconds(seconds);

    var timeout = (then.getTime() - now.getTime());
    setTimeout(function() { window.location.reload(true); }, timeout);
}