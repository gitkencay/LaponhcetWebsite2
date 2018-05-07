<link href="guest/FBC/style.css" rel="stylesheet">
<!-- TOP NAV -->
<div class="navbar-wrapper">
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	    <div class="container">
	        <div class="navbar-header page-scroll">
	            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	                <span class="sr-only">Toggle navigation</span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
	            <a class="navbar-brand" href="#">FELLOWSHIP BAPTIST COLLEGE</a>
	        </div>
	       <div id="navbar" class="navbar-collapse collapse" style="overflow: hidden;">
	            <ul class="nav navbar-nav navbar-right">
	                <li class="active"><a class="page-scroll" href="#page-top">Home</a></li>
	                <li><a class="page-scroll" href="#about-us">About</a></li>
	                <li><a class="page-scroll" href="#academics">Academics</a></li>
	                <li><a class="page-scroll" href="#admissions">Admissions</a></li>
	                <!-- <li><a class="page-scroll" href="#news">News</a></li>       
	                <li><a class="page-scroll" href="#events">Events</a></li>  -->                
	                <li><a class="page-scroll" href="#alumni">Alumni</a></li>
	                <li><a class="page-scroll" href="#research">Research</a></li>                            
	                <li><a class="page-scroll" href="#myFooter">Contact Us</a></li>
	            	<li><a data-toggle="modal" href="#modal-login">Login</a></li>
<!-- 	                <li><a class="page-scroll" href="http://103.47.210.50:8080/ScholastechFBC/MainController" target="_blank">Login</a></li> -->
	            </ul>
	        </div>
	    </div>
	</nav>
</div>

<div id="modal-login" class="modal fade" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-6 b-r">
						<h3 class="m-t-none m-b">Sign in</h3>
						<p>Sign in today for more experience.</p>
						<div class="form-group">
							<label>User Name</label> <input name="txtUsernName" type="text"
								placeholder="User Name" class="form-control">
						</div>
						<div class="form-group">
							<label>Password</label> <input name="txtPassword" type="password"
								placeholder="Password" class="form-control">
						</div>
						<div>
							<button class="btn btn-sm btn-primary pull-right m-t-n-xs"
								type="submit" onclick="openLink('G00002')">
								<strong>Log in</strong>
							</button>
							<label> <input type="checkbox" class="i-checks">
								Remember me
							</label>
						</div>
					</div>
					<div class="col-sm-6">
						<h4>Not yet a member?</h4>
						<p>Please Register</p>
						<p class="text-center">
							<a href="#register"
								onblur="$('#modal-form').modal('hide');$('.modal-backdrop').remove();"><i
								class="fa fa-sign-in big-icon"></i></a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- SLIDER -->
<div id="inSlider" class="carousel carousel-fade" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#inSlider" data-slide-to="0" class="active"></li>
        <li data-target="#inSlider" data-slide-to="1"></li>
        <li data-target="#inSlider" data-slide-to="2"></li>
        <li data-target="#inSlider" data-slide-to="3"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <div class="container">
                <div class="carousel-caption wow pulse">
                    <img src="/static/FBC/images/logo.png" alt="Logo"/><br>
                    <img src="/static/FBC/images/contacts.jpg" alt="Contacts" style="margin-top: -15px;" /><br>
                    <img src="/static/FBC/images/shaping-lives.png" alt="Shaping Lives"/>
                    <a class="page-scroll" href="http://103.47.210.50:8080/static/FBC/vtour/index.html" target="_blank"><img src="/static/FBC/images/vtour.png" alt="Shaping Lives"/></a>
                    
                </div>
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back one"></div>
        </div>
        <div class="item">
            <div class="container">
                <div class="carousel-caption blank wow fadeInLeft">
                    <h1 style="color:#f6e800">VISION</h1>
                    <h2>A globally-recognized and responsive institution <br>
                        of learning, shaping lives by providing excellent <br>
                        Christian education.</h2>                       
                </div>
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back two"></div>
        </div>
        <div class="item">
            <div class="container">
                <div class="carousel-caption blank wow fadeInLeft">
                    <h1 style="color:#f6e800">MISSION</h1>
                    <h2>Fellowship Baptist College commits to provide a <br>
                        Christ-centered learning environment conducive to <br>
                        the total development and competence of a person <br>
                        for service to humanity, for God's greatest glory.</h2>
                </div>                    
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back three"></div>
        </div>
        <div class="item">
            <div class="container">
                <div class="carousel-caption blank wow fadeInLeft">
                    <h1 style="color:#f6e800">WHY CHOOSE FBC?</h1>
                    <h2>FBC provides a Christ-centered learning environment <br>
                        conducive to the total development and competence of a <br>
                        person for service to humanity, for God's greatest glory.</h2>                       
                </div>                    
            </div>
            <!-- Set background for slide in css -->
            <div class="header-back four"></div>
        </div>
    </div>
    <a class="left carousel-control" href="#inSlider" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#inSlider" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<!-- ABOUT -->
<section id="about-us" class="container features">
    <div id="features" class="row">
        <div class="col-lg-12 text-center">
            <div class="navy-line"></div>
            <h1>ABOUT<br/> <span class="navy m-t-lg" style="font-size: 1.4em"> FELLOWSHIP BAPTIST COLLEGE</span> </h1>
            <p><img style="margin:-35px auto 0; width: 30%;" src="/static/FBC/images/shaping-lives.png" alt="IT" class="img-responsive wow fadeInDown"></p>
        </div>
    </div>
    <div class="row features-block">
        <div class="col-lg-6 text-center wow fadeInLeft">
            <img style="margin-top:-20px; width: 100%; border-radius: 10px;" src="/static/FBC/images/FBC.jpg" alt="IT" class="img-responsive"><br><br>
            <button class="btn btn-danger btn-sm dim" data-toggle="modal" data-target="#MissionVission" type="button">MISSION &amp; VISION</button>
            <div class="modal inmodal" id="MissionVission" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated flipInY">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">MISSION &amp; VISION</h4>
                        </div>
                        <div class="modal-body text-left">
                            <h4>MISSION</h4>
                            <p class="text-justify">Fellowship Baptist College commits to provide a Christ-centered learning environment conducive to the total development and competence of a person for service to humanity, for God's greatest glory.</p><br>
                            <h4>VISION</h4>
                            <p class="text-justify">A globally-recognized and responsive institution of learning, shaping lives by providing excellent Christian education.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-danger btn-sm dim" data-toggle="modal" data-target="#Goals" type="button">OUR GOALS</button>
            <div class="modal inmodal" id="Goals" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated flipInY">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">OUR GOALS</h4>
                        </div>
                        <div class="modal-body text-left">
                           <p>
                            <ol>
                                <li>To cultivate sound spiritual and moral values, thus enabling the learners to become models of honesty, integrity, and morality in the community.</li>
                                <li>To give fair and equal educational opportunities regardless of social, political, economic, racial and religious affiliation.</li>
                                <li>To provide quality education for empowerment that focuses on the development of academic skills, Christian living and social-cultural awareness of students to become well-adjusted individuals through meaningful and integrated curriculum and research.</li>
                                <li>To contribute in the development of the national and global consciousness in support of the government educational programs.</li>
                                <li>To strengthen national identity by promoting political awareness, civic consciousness, pride and dignity of being a Filipino through education.</li>
                                <li>To supply the needed manpower of the industry.</li>
                                <li>To promote a strong relationship between the home and the school and strengthen relationships with Christian churches.</li>
                                <li>To develop the spiritual, intellectual, social and moral faculties of the youth with the emphasis on Christian character and service.</li>
                                <li>To take active involvement in ecological development and preservation of the environment.</li>
                                <li>To build a strong culture of excellence in research and community service.</li>
                            </ol>
                        </p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-danger btn-sm dim" data-toggle="modal" data-target="#CoreValues" type="button">CORE VALUES</button> 
            <div class="modal inmodal" id="CoreValues" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content animated flipInY">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">CORE VALUES</h4>
                        </div>
                        <div class="modal-body text-left col-sm-12" style="border-bottom: 1px solid #e5e5e5">
                            <div class="col-sm-4">
                                <h4><strong>Christ-Centered</strong></h4>  
                                <p>We are a mission school established to spread the message of salvation by grace through faith in Jesus Christ alone.</p><br>
                                <h4><strong>Bible-Based</strong></h4>  
                                <p>We believe the bible as the final authority in all decisions and actions that we make.</p><br>
                                <h4><strong>Student Welfare</strong></h4>  
                                <p>We regard the well-being of our students with respect to human dignity and consideration for their cultural, social and religious background.</p><br>
                                <h4><strong>Excellence</strong></h4>  
                                <p>We continuously aim for excellent education.</p><br>
                            </div>
                            <div class="col-sm-4">
                                <h4><strong>Linkages</strong></h4>  
                                <p>We believe that there must be cooperation and collaboration with and among students, faculty and staff, administrators, alumni and local and international communities.</p><br>
                                <h4><strong>Accessibility</strong></h4>  
                                <p>We admit students, regardless of gender, race, creed, color and religion.</p><br>
                                <h4><strong>Innovativeness and Flexibility</strong></h4>  
                                <p>We employ innovative strategies and measures responsive to the needs of our stakeholders.</p><br>
                                <h4><strong>Technology and Productivity</strong></h4>  
                                <p>We aim to produce competent individuals responsive to the needs of the society.</p><br>
                            </div>
                            <div class="col-sm-4">
                                <h4><strong>Transparency</strong></h4>  
                                <p>We uphold honesty and integrity in all our dealings.</p><br>
                                <h4><strong>Nation Building</strong></h4>  
                                <p>We support the government in all its undertakings that promote a better quality of life.</p><br>
                                <h4><strong>Stakeholder's Satisfaction</strong></h4>  
                                <p>We serve to the satisfaction of the school's stakeholders and clientele.</p><br>
                                <h4><strong>Holistic Development</strong></h4>  
                                <p>We provide avenues for spiritual, intellectual inquiry, analytical and critical thinking, moral, physical and aesthetic development of the academic community.</p><br>
                            </div>                                
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary m-t" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>               
            <button class="btn btn-danger btn-sm dim" data-toggle="modal" data-target="#BoardOfTrustees" type="button">BOARD OF TRUSTEES</button>
            <div class="modal inmodal" id="BoardOfTrustees" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated flipInY">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">BOARD of TRUSTEES</h4>
                        </div>
                        <div class="modal-body text-left col-sm-12" style="border-bottom: 1px solid #e5e5e5">
                           <div class="col-sm-6">
                               <div class="page-row text-justify">
                                     <h4><strong>Dr. Rene S. Sison</strong><br><i><small>Chairman</small></i></h4>
                                </div><!--//page-row-->
                                <div class="page-row text-justify">
                                     <h4><strong>Atty. Marsha S. Cordero</strong><br><i><small>Vice Chairman</small></i></h4>
                                </div><!--//page-row-->
                                 <div class="page-row text-justify">
                                     <h4><strong>Engr. Rudy G. Taquiso, MPA</strong><br><i><small>Secretary</small></i></h4>
                                </div><!--//page-row-->
                                 <div class="page-row text-justify">
                                     <h4><strong>Mr. Rolando M. Macasa</strong><br><i><small>Treasurer</small></i></h4>
                                </div><!--//page-row-->
                                <div class="page-row text-justify">
                                     <h4><strong>Mrs. Grace Ann J. Mendoza</strong><br><i><small>Ex-officio Member, President, FBA-FBC Alumni Association, Inc.</small></i></h4>
                                </div><!--//page-row-->
                                <div class="page-row text-justify">
                                     <h4><strong>Rev. Paquito T. Agudo</strong><br><i><small>Ex-officio Member, Vice Chairman, VFFBC</small></i></h4>
                                </div><!--//page-row-->
                            </div>
                            <div class="col-sm-6">
                                <div class="page-row text-justify">
                                	 <h4><i><small>Members</small></i></h4>
                                     <h4><B>Mr. Cesar O. Artesano</B></h4>
                                     <h4><B>Rev. Gospel A. Catanus</B></h4>
                                     <h4><B>Ms. Leah A. Catanus</B></h4>
                                     <h4><B>Mr. Joval V. Gargantiel</B></h4>
                                     <h4><B>Mr. Roger R. Gasataya</B></h4>
                                     <h4><B>Ms. Ma. Teresa P. Geroso</B></h4>
                                     <h4><B>Engr. Jared F. Jordan</B></h4>
                                     <h4><B>Mr. Romulo M. Mombay</B></h4>
                                     <h4><B>Dr. Joel A. Perez</B></h4>                                      
                                     <h4><B>Mrs. Marlene M. Selorio</B></h4>
                                     <h4><B>Engr. Rolly P. Villanueva</B></h4>
                                </div><!--//page-row-->
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary m-t" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-danger btn-sm dim" data-toggle="modal" data-target="#PresidentsMessage" type="button">PRESIDENT'S MESSAGE</button>
            <div class="modal inmodal" id="PresidentsMessage" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated flipInY">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">PRESIDENT'S MESSAGE</h4>
                        </div>
                        <div class="modal-body text-left">
                           <div class="page-row text-justify">
                                <img style="float: left; margin-right: 20px;" class="img-responsive" src="/static/FBC/images/president.gif" alt=""/>
                                <p>Welcome to Fellowship Baptist College! A school which is not only concerned with granting quality education, but also tries to instill and develop quality Christian lives among its scope.
                                </p>
                                <p>
                                There are three basic aspects which interplay in a man's being, equipping him to be versatile at the face of the sunniest and gloomiest circumstances and to be adaptable on any soil he steps upon, be it here or abroad.
                                 </p>
                                <p>
                                These are the superiority of skills his hands and mind reflects, the excellence of character inculcated in his persona and the quality of spirituality enwrapping the totality of his being. 
                                These are the aspects the school wants to develop in each one who enters the portals of FBC.
                                 </p>
                                <p>
                                It is one of the school's vision to produce such individuals that are competent; whether be placed locally or globally. The sort of individuals that will not only bring prestige to this school but also bring glory to the Master Above, the only strong rope which binds this school from falling apart despite the test of time, testings and travails.
                                 </p>
                                <p>
                                We owe the existence of this institution to the Almighty; our Great Provider, to the faith of our forefathers; the founders of this school, to the school"s teachers and staff; for their utmost loyalty despite many intriguing odds, and to your students; for always believing in this school's mission and visions.
                                 </p>
                                <p>
                                It takes people like you continue on bringing into life an institution such as this. Indeed, FBC will always bear in mind its principle of not paying much concern of its income, but of the outcome of its students.We pray that your stay here in Fellowship Baptist College will be memoir worth cherishing and being proud of!
                                 </p>
                                <p>
                                Our most heartfelt thank you to all of you!</p>
                                <br>
                                <h4><strong>DAVID V. GARGANTIEL, RN, MAN, MD, PhD.</strong>
                                    <br><small><i>President<br>
                                    Fellowship Baptist College</i></small></h4>
                            </div>  
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-danger btn-sm dim" data-toggle="modal" data-target="#AdministrativeOfficer" type="button">ADMINISTRATIVE OFFICERS</button>
            <div class="modal inmodal" id="AdministrativeOfficer" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content animated flipInY">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">ADMINISTRATIVE OFFICERS</h4>
                        </div>
                        <div class="modal-body text-left">
                           <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/dvgargantiel.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">DAVID V. GARGANTIEL, MD, RN, MAN<br>
                                    <small><i>President</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/mgvillegas.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">MARIETTA G. VILLEGAS, EdD<br>
                                    <small><i>Vice President for Academic Affairs<br>Dean, College of Teacher Education Arts &amp; Sciences</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/mcbepinoso.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">MELCHORIE C. BEPINOSO, MBA<br>
                                    <small><i>Vice President for Administration and Finance</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title"> TERRYFIN D. REYES, CPA, MBA<br>
                                    <small><i>Dean, College of Business and Accountancy</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/jchinoso.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">JUVY C. HINOSO, RM, RN, MN<br>
                                    <small><i>Dean, College of Nursing and Health Related Courses</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">ENGR. JENNEE FAITH JOY Y. RESANO, ECE, ME<br>
                                    <small><i>Dean, College of Engineering and Computer Science</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">AGNES S. BERMEO, RM, RN, MN<br>
                                    <small><i>Principal, Midwifery</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/mgchavez.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">MERRILL G. CHAVEZ, MAED<br>
                                    <small><i>Principal, Basic Education High School Department</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">RUTH A. LAVANDERA<br>
                                    <small><i>Principal, Basic Education Elementary Department</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">VENALYN S. TORESIS, MSMT<br>
                                    <small><i>Dean, Student Affairs and Services</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">RUTH JOY C. CARDIENTE, RN, MAN<br>
                                    <small><i>Registrar</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/mtalcala.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title"> MARILYN T. ALCALA, MS Bio<br>
                                    <small><i>Director, Research and Extension</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/gtrepique.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">GLENN T. REPIQUE, MS Psych<br>
                                    <small><i>Director, Institutional Advancement</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">DAFFODILS C. GARCENIEGO<br>
                                    <small><i>Human Resource Development Officer</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">EMEE ANN P. VALDEZ, MLIS<br>
                                    <small><i>College Librarian</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">JAN EDUARD J. IROG, CPA<br>
                                    <small><i>College Librarian</i></small></h4>
                                </div>                               
                            </div>
                            <div class="row page-row" >
                                <figure class="thumb col-md-3 col-sm-4 col-xs-6">
                                    <img class="img-responsive" src="/static/FBC/images/nopic.gif" height="100" width="100" alt="" />
                                </figure>
                                <div class="details col-md-9 col-sm-8 col-xs-6">
                                    <h4 class="title">PASTOR NILO B. ENGADA<br>
                                    <small><i>College Chaplain</i></small></h4>
                                </div>                               
                            </div>  
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6 features-text wow fadeInRight" style="margin-top:-20px;">
            <p class="text-justify"><span class="navy"><b>Fellowship Baptist Academy</b></span> is a fundamental Christian secondary school on Western Visayas founded in 1954 by a group of Baptist lay-leaders, ministers and missionaries of the Visayan Fellowship of Fundamental Baptist Churches. It is sectarian, non-stock, non-profit educational institution. Aside from student fees, it exists mainly upon benevolent donations from Fundamental Baptist churches organization, individual Christians and later from the alumni and its organization.</p>

            <p class="text-justify">It opened in 1954 with an enrollment of 212 students. It faithfully carried out its mission. In 1957, Fellowship Baptist Academy was given government recognition. Today, graduates of the school have spread far and wide not only in the Philippines but in other countries. Some leaders are occupying sensitive positions in various fields of human endeavors. Some accepted the highest calling as ministers, evangelists and missionaries here and abroad.</p>

            <p class="text-justify">Aware of the need for educational expansion of our Christian youths, decisive steps were taken by the administration to convert the school into a full-fledge college. The long cherished dream was at last realized. For with the Lord nothing is impossible.</p>

            <p class="text-justify">In June 1982, the school operated a post-secondary course in Midwifery. The following year, it opened two degree courses which are Bachelor of Science in Business administration and Bachelor of Arts. Upon conferring the government recognition to these two courses, DECS acted favorably in school year 1989-1990 on the change of the status of the school from "Academy" to "College". Additional courses were later offered such as BSED, BEED, BSA, BSN, AAS, HA, PA, Caregiver, Two-Year and One-Year Computer courses. If the Lord tarries, FBC will offer more courses.</p>

             <p class="text-center" style=""><span class="navy"><i><b>The school stands true to its commitment: To win Souls for Christ till He comes.</i></b></span></p>
        </div>
    </div>
</section>

<!-- ACADEMICS -->
<section class="services text-center" style="margin-top: -20px">
    <div class="row" style=" background-image: url('/static/FBC/images/bg.jpg'); padding-bottom: 60px;">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="row"  id="academics" >
                <div class="col-lg-12">
                    <div class="navy-line"></div>
                    <h1> <span class="navy">ACADEMICS</span> </h1>
                    <p>Education at it's best</p>
                </div>
            </div><br><br><br>

            <div class="row">
                <div class="col-lg-6">
                    <button type="button" data-toggle="modal" data-target="#CECS" class="col-sm-12 btn btn-outline btn-danger"><img src="/static/FBC/images/cecs.jpg" style="margin: 20px auto;border-radius: 10px;" class="img-responsive" alt=""></button>
                </div>
                <div class="modal inmodal" id="CECS" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content animated flipInY">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">College of Engineering and Computer Studies</h4>
                            </div>
                            <div class="modal-body text-left">
                                <p class="featured-image page-row"><img class="img-responsive" style="width: 100%;" src="/static/FBC/images/courses/cecs.jpg" alt=""/></p>
                                <div class="page-row box box-border text-justify">
                                    <p>In consonance with the institutional mission statement of the College, the Engineering and Computer Study Programs aim to train students to have an integrated knowledge of the theory and applications of the engineering and computer science in order to serve the diverse needs of the industry.  Likewise, the programs aim to give the aspiring students a solid foundation in basic engineering sciences, physical sciences, programming languages and mathematics.  We further seek to prepare the students for service and leadership in the technological and industrial field.</p>
                                </div><!--//page-row-->
                                <div class="page-row text-justify">
                                    <p>In June 2004, FBC opened several programs. BS in Electronics and Communications Engineering, BS in Civil Engineering and BS in Computer Science were among the new programs offered by the college.  Prior to the opening of these programs, Short â€“ term courses were also offered such as 1 year PC Operations NC II and 2-year Programming NC IV were already being offered by the College. Among the first faculty members of the College were Engr. Jonathan M. Cansino who became the first Dean of the College, Engr. Joenard Urbanozo, Engr. Jennee Faith Joy Yunque and Ms. Rhea Soriano.  In 2005, the engineering department was established. It was called CECS (College of Engineering and Computer Science)</p>

                                    <p>In June 2006, additional program offering was opened, which was the BS in Computer Engineering. In March 2008, Fellowship Baptist College produced its first batch of BSCS graduates. Some of these graduates later became College Instructors of FBC and some were employed in offices and establishments in and out of Kabankalan City. A laddered program of the BS Computer Science started in June 2008. PC Operations NC II and Programming NC IV were lumped and integrated into the BSCS program, increasing the number of hours for competency-based subjects.</p>

                                    <p>The Lord is indeed faithful. He continues to manifest His goodness to the College. In March 2009 FBC produced its first Batch of BSECE and BSCE graduates. And in the same year, produced the first batch of engineers! They were: Engr. Jess Cantular, Engr. Nhoela Jane Arcedas and Engineer Michael Bocol. In March 2011, the first batch of BS Computer Engineering graduates was produced. The school is consistent in its passion of educating the hearts and minds of the community. So, In June 2011, FBC again opened another program which is the BS in Information Technology. With this, CECS stands for College of Engineering and Computer Studies.</p>

                                    <p>The College will continue in its passion of shaping lives of students to be locally and globally competitive.</p>
                                    <br>

                                    <h4><strong>MISSION</strong></h4>
                                    <p>The College of Business and Accountancy is committed to develop, mold and hone graduates, equipped with knowledge, skills and values that are requisites in producing successful manager. Decision-makers and competent entrepreneurs as our contribution to the socioeconomic stability of the country.</p>
                                    <br>
                                    <h4><strong>VISION</strong></h4>
                                    <p>The vision is to become preeminent in producing competent Engineers and Computer Scientist in Southern Negros.</p>
                                    <br> 
                                </div>
                                <div class="panel panel-danger">
                                    <div class="panel-heading">
                                         <h4><strong>COURSE OFFERINGS</strong></h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="tabbed-info page-row">             
                                            <ul class="nav nav-tabs">
                                              <li class="active"><a href="#tab1" data-toggle="tab">Five-Year Degree Programs</a></li>
                                              <li><a href="#tab2" data-toggle="tab">Four-Year Programs</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab1">
                                                    <div class="table-responsive">  
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>Program Name</th>
                                                                    <th>Accreditation</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>Bachelor of Science in Computer Engineering</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Bachelor of Science in Civil Engineering</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>Bachelor of Science in Electronics Engineering</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div><!--//table-responsive-->
                                                </div>
                                                <div class="tab-pane" id="tab2">
                                                    <div class="table-responsive">  
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>Program Name</th>
                                                                    <th>National Certification</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>Bachelor of Science in Computer Science <br><i>(Ladderized Program 2 Year Programming)</i></td>
                                                                    <td>NC IV</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Bachelor of Science in Information Technology</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div><!--//table-responsive-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <button type="button" data-toggle="modal" data-target="#CBA" class="col-sm-12 btn btn-outline btn-danger"><img src="/static/FBC/images/cba.jpg" style="margin: 20px auto;border-radius: 10px;" class="img-responsive" alt=""></button>
                </div>
                <div class="modal inmodal" id="CBA" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content animated flipInY">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">College of Business and Accountancy</h4>
                            </div>
                            <div class="modal-body text-left">
                                <p class="featured-image page-row"><img class="img-responsive" style="width: 100%;" src="/static/FBC/images/courses/cba.jpg" alt=""/></p>
                                <div class="page-row box box-border text-justify">
                                <p>Anchored on the institutional mission of the school, the College of Business and Accountancy is committed to develop, mold and hone graduates, equipped with knowledge, skills and values that are requisites in producing successful managers, decision-makers and competent entrepreneurs as our contribution to the socioeconomic stability of the country.</p>
                            </div><!--//page-row-->
                            <div class="page-row text-justify">
                                <h4><strong>MISSION</strong></h4>
                                <p>The College of Business and Accountancy is committed to develop, mold and hone graduates, equipped with knowledge, skills and values that are requisites in producing successful manager. Decision-makers and competent entrepreneurs as our contribution to the socioeconomic stability of the country.</p>
                                <br>

                                <h4><strong>PROGRAM OBJECTIVES</strong></h4>
                                <p>
                                    <ol>
                                        <li>To equip students with top notch managerial skills and entrepreneurial competence which are globally competitive.</li>
                                        <li>To develop and enhance potentials in order to prepare them into assets and leaders in the local and global business.</li>
                                        <li>To instill in learners the Christian value of concern to others as the foundation of their personal and professional commitment for service to humanity.</li>
                                        <li>To provide student with a broad knowledge and understanding of the business profession through approaches that assures to the needs of the times.</li>
                                        <li>To enhance basic skills in order to transform learners into effective and efficient practicums of the business profession.</li>
                                        <li>To ensure that learners have required adequate theoretical experienced knowledge in their respective area of specialization thereby transforming them into responsible member of society.</li>
                                        <li>To provide student with experiential learning through on the job training program.</li>
                                        <li>To develop students' knowledge, skills and attitude in discovery of new technology, new methods in the business industry through research.</li>
                                    </ol>
                                </p>
                                <br>
                                </div>
                                <div class="panel panel-danger">
                                    <div class="panel-heading">
                                         <h4><strong>COURSE OFFERINGS</strong></h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="tabbed-info page-row">             
                                            <ul class="nav nav-tabs">
                                              <li class="active"><a href="#tab3" data-toggle="tab">Four-Year Degree Programs</a></li>
                                              <li><a href="#tab4" data-toggle="tab">One-Year Programs</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab3">
                                                    <div class="table-responsive">  
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>Program Name</th>
                                                                    <th>Accreditation</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>Bachelor of Science in Accountancy</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Bachelor of Science in Hositality Management</td>
                                                                    <td>Accredited Level 2 | ACSCU-AAI</td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>Bachelor of Science in Business Administration</td>
                                                                    <td>Accredited Level 2 | ACSCU-AAI</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div><!--//table-responsive-->
                                                </div>
                                                <div class="tab-pane" id="tab4">
                                                    <div class="table-responsive">  
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>Program Name</th>
                                                                    <th>National Certification</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>Commercial Cooking</td>
                                                                    <td>NC II</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Housekeeping</td>
                                                                    <td>NC II</td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>Food &amp; Beverage Services</td>
                                                                    <td>NC II</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div><!--//table-responsive-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row m-t-lg">
                <div class="col-lg-6">
                    <button type="button" data-toggle="modal" data-target="#CTEAS" class="col-sm-12 btn btn-outline btn-danger"><img src="/static/FBC/images/cteas.jpg" style="margin: 20px auto;border-radius: 10px;" class="img-responsive" alt=""></button>
                </div>
                <div class="modal inmodal" id="CTEAS" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content animated flipInY">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">College of Teacher Education, Arts, &amp; Sciences</h4>
                            </div>
                            <div class="modal-body text-left">
                                <p class="featured-image page-row"><img class="img-responsive" style="width: 100%;" src="/static/FBC/images/courses/cteas.jpg" alt=""/></p>
                                <div class="page-row box box-border text-justify">
                                <p>The College of Teacher Education, Arts, and Sciences envisions itself to be a sanctuary of excellence in the Communication Arts, Liberal Arts, Natural Sciences, Social Sciences, and Teacher Education, forming graduates, complemented with research and community involvement, nurtured with nationalism, social responsibility, and leadership and enriched with Christian values</p></div>
                                <div class="panel panel-danger">
                                    <div class="panel-heading">
                                         <h4><strong>COURSE OFFERINGS</strong></h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="tabbed-info page-row">             
                                            <ul class="nav nav-tabs">
                                              <li class="active"><a href="#tab5" data-toggle="tab">Four-Year Degree Programs</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab5">
                                                    <div class="table-responsive">  
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>Program Name</th>
                                                                    <th>Major</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>Bachelor of Secondary Education (BSEd)</td>
                                                                    <td>Biological Science</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Bachelor of Secondary Education (BSEd)</td>
                                                                    <td>English</td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>Bachelor of Secondary Education (BSEd)</td>
                                                                    <td>Mathematics</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Bachelor of Science in Psychology</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Bachelor of Elementary Education (BEEd)</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                                 <tr>
                                                                    <td>Bachelor of Elementary Education (BEEd)</td>
                                                                    <td>Early Childhood Education (ECE)</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div><!--//table-responsive-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <button type="button" data-toggle="modal" data-target="#CPAHP" class="col-sm-12 btn btn-outline btn-danger"><img src="/static/FBC/images/cpahp.jpg" style="margin: 20px auto;border-radius: 10px;" class="img-responsive" alt=""></button>
                </div>
                <div class="modal inmodal" id="CPAHP" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content animated flipInY">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">College of Pharmacy and Allied Health Programs</h4>
                            </div>
                            <div class="modal-body text-left">
                                <p class="featured-image page-row"><img class="img-responsive" style="width: 100%;" src="/static/FBC/images/courses/cpahp.jpg" alt=""/></p>
                                <div class="page-row box box-border text-justify">
                                <p>Allied health professions are health care professions distinct from nursing, medicine, and pharmacy. They work in health care teams to make the health care system function by providing a range of diagnostic, technical, therapeutic and direct patient care and support services that are critical to the other health professionals they work with and the patients they serve.</p></div>
                                <div class="panel panel-danger">
                                    <div class="panel-heading">
                                         <h4><strong>COURSE OFFERINGS</strong></h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="tabbed-info page-row">             
                                            <ul class="nav nav-tabs">
                                              <li class="active"><a href="#tab6" data-toggle="tab">Four-Year Degree Programs</a></li>
                                              <li><a href="#tab7" data-toggle="tab">Two-Year Programs</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab6">
                                                    <div class="table-responsive">  
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>Program Name</th>
                                                                    <th>Accreditation</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>Bachelor of Science in Pharmacy</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Bachelor of Science in Radiologic Technology</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div><!--//table-responsive-->
                                                </div>
                                                <div class="tab-pane" id="tab7">
                                                    <div class="table-responsive">  
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>Program Name</th>
                                                                    <th>National Certification</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>Diploma in Midwifery</td>
                                                                    <td>N / A</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div><!--//table-responsive-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><br><br><br><br>
    </div>
</section>              

<!-- ADMISSION -->
<section id="admissions" class="services text-center" style="margin-top: -40px">
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="row"  >
                <div class="col-lg-12">
                    <div class="navy-line"></div>
                    <h1> <span class="navy">ADMISSIONS</span> </h1>
                    <p>Learn how to join our institution</p>
                </div>
            </div>
            <div class="row features-block">
                <div class="page-row text-justify col-lg-6">
                    <p>Admission to Fellowship Baptist College is open to all students who have complied with all the requirements for admission required by the Commission on Higher Education (CHED), the Technical Education, Skills and Development Authority (TESDA) and the school.</p>

                    <p>Students with a sincere desire to abide by the rules and regulations of the College are welcome to the Fellowship Baptist College. But the school reserves the right to refuse admission to applicants or require the withdrawal of students whose presence poses a threat or a hindrance to its normal operation.</p>

                    <p>In determining the admission of applicants, consideration shall be given to past scholastic records, character and the applicants, acceptance of the Mission Statement and all policies, procedures, rules, and regulations of the College.</p>
                </div><!--//page-row-->
                <div class="page-row box box-border text-justify col-lg-6">
                    <div class="panel panel-danger">
                        <div class="panel-body">
                            <h4><strong>REQUIREMENTS FOR ADMISSION</strong></h4>  
                            The following are the list of requirements for admission:
                            <ul class="list-unstyled m-t">
                                <li><i class="fa fa-check m-l m-r"></i> Placement Exam Result + 1 Photocopy</li>
                                <li><i class="fa fa-check m-l m-r"></i> Form 138 A + 1 Photocopy</li>
                                <li><i class="fa fa-check m-l m-r"></i> Certificate of Good Moral Character + 1 Photocopy</li>
                                <li><i class="fa fa-check m-l m-r"></i> NCAE Certificate of Rating + 1 Photocopy</li>
                                <li><i class="fa fa-check m-l m-r"></i> 2 pcs. Photocopy of Birth Certificate (by NSO)</li>
                                <li><i class="fa fa-check m-l m-r"></i> 2 pcs. Photocopy of Marriage Certificate (female only if married)</li>
                                <li><i class="fa fa-check m-l m-r"></i> 2 pcs. 2 x 2 and 1 x 1 ID Pictures</li>
                                <li><i class="fa fa-check m-l m-r"></i> 2 pcs. Long Brown Envelope</li>
                            </ul>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
    </div><br><br><br><br>
</section>

<!-- NEWS -->
<!-- <section class="services text-center gray-section" style="margin-top: -20px">
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="row"  id="news" >
                <div class="col-lg-12">
                    <div class="navy-line"></div>
                    <h1> <span class="navy">LATEST NEWS</span> </h1>
                    <p>Be up to date of what is happening</p>
                </div>
            </div>
            <div class="row features-block">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="ibox">
                            <div class="ibox-content" style="border-radius: 10px;">
                                <a class="btn-link">
                                    <h2>News Title</h2>
                                </a>
                                <img src="/static/FBC/images/no-image.jpg" style="margin: 5px auto;border-radius: 10px;" class="img-responsive" alt="">
                                <div class="small m-b-xs">
                                    <strong>Mike Smith</strong> <span class="text-muted"><i class="fa fa-clock-o"></i> 11th May 2015</span>
                                </div>
                                <p class="text-justify m-t">
                                    The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of...
                                </p>
                                <div class="row text-center">
                                     <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#news1" type="button">Read More</button>
                                     <div class="modal inmodal" id="news1" tabindex="-1" role="dialog" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content animated flipInY">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                    <h4 class="modal-title">News Title</h4>
                                                </div>
                                                <div class="modal-body text-left">
                                                    <img src="/static/FBC/images/no-image.jpg" style="margin: 5px auto;border-radius: 10px;" class="img-responsive" alt="">
                                                    <div class="m-b m-t">
                                                        <strong>Mike Smith</strong> <span class="text-muted"><i class="fa fa-clock-o"></i> 11th May 2015</span>
                                                    </div>
                                                   <p>
                                                    One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. "What's happened to me?" he thought. It wasn't a dream.
                                                </p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="ibox">
                            <div class="ibox-content" style="border-radius: 10px;">
                                <a class="btn-link">
                                    <h2>News Title</h2>
                                </a>
                                <img src="/static/FBC/images/no-image.jpg" style="margin: 5px auto;border-radius: 10px;" class="img-responsive" alt="">
                                <div class="small m-b-xs">
                                    <strong>Mike Smith</strong> <span class="text-muted"><i class="fa fa-clock-o"></i> 11th May 2015</span>
                                </div>
                                <p class="text-justify m-t">
                                    The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of...
                                </p>
                                <div class="row text-center">
                                     <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#news2" type="button">Read More</button>
                                     <div class="modal inmodal" id="news2" tabindex="-1" role="dialog" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content animated flipInY">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                    <h4 class="modal-title">News Title</h4>
                                                </div>
                                                <div class="modal-body text-left">
                                                    <img src="/static/FBC/images/no-image.jpg" style="margin: 5px auto;border-radius: 10px;" class="img-responsive" alt="">
                                                    <div class="m-b m-t">
                                                        <strong>Mike Smith</strong> <span class="text-muted"><i class="fa fa-clock-o"></i> 11th May 2015</span>
                                                    </div>
                                                   <p>
                                                    One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. "What's happened to me?" he thought. It wasn't a dream.
                                                </p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="ibox">
                            <div class="ibox-content" style="border-radius: 10px;">
                                <a class="btn-link">
                                    <h2>News Title</h2>
                                </a>
                                <img src="/static/FBC/images/no-image.jpg" style="margin: 5px auto;border-radius: 10px;" class="img-responsive" alt="">
                                <div class="small m-b-xs">
                                    <strong>Mike Smith</strong> <span class="text-muted"><i class="fa fa-clock-o"></i> 11th May 2015</span>
                                </div>
                                <p class="text-justify m-t">
                                    The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of...
                                </p>
                                <div class="row text-center">
                                     <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#news3" type="button">Read More</button>
                                     <div class="modal inmodal" id="news3" tabindex="-1" role="dialog" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content animated flipInY">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                    <h4 class="modal-title">News Title</h4>
                                                </div>
                                                <div class="modal-body text-left">
                                                    <img src="/static/FBC/images/no-image.jpg" style="margin: 5px auto;border-radius: 10px;" class="img-responsive" alt="">
                                                    <div class="m-b m-t">
                                                        <strong>Mike Smith</strong> <span class="text-muted"><i class="fa fa-clock-o"></i> 11th May 2015</span>
                                                    </div>
                                                   <p>
                                                    One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. "What's happened to me?" he thought. It wasn't a dream.
                                                </p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="ibox">
                            <div class="ibox-content" style="border-radius: 10px;">
                                <a class="btn-link">
                                    <h2>News Title</h2>
                                </a>
                                <img src="/static/FBC/images/no-image.jpg" style="margin: 5px auto;border-radius: 10px;" class="img-responsive" alt="">
                                <div class="small m-b-xs">
                                    <strong>Mike Smith</strong> <span class="text-muted"><i class="fa fa-clock-o"></i> 11th May 2015</span>
                                </div>
                                <p class="text-justify m-t">
                                    The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of...
                                </p>
                                <div class="row text-center">
                                     <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#news4" type="button">Read More</button>
                                     <div class="modal inmodal" id="news4" tabindex="-1" role="dialog" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content animated flipInY">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                    <h4 class="modal-title">News Title</h4>
                                                </div>
                                                <div class="modal-body text-left">
                                                    <img src="/static/FBC/images/no-image.jpg" style="margin: 5px auto;border-radius: 10px;" class="img-responsive" alt="">
                                                    <div class="m-b m-t">
                                                        <strong>Mike Smith</strong> <span class="text-muted"><i class="fa fa-clock-o"></i> 11th May 2015</span>
                                                    </div>
                                                   <p>
                                                    One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. "What's happened to me?" he thought. It wasn't a dream.
                                                </p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>      
            </div>          
        </div>
    </div><br><br>
</section>

EVENTS
<section class="services text-center" style="margin-top: -20px">
    <div class="row"  style=" background-image: url('/static/FBC/images/bg-events.jpg'); padding-bottom: 30px;">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="row"  id="events" >
                <div class="col-lg-12">
                    <div class="navy-line"></div>
                    <h1> <span class="navy">EVENTS</span> </h1>
                    <p>Upcoming &amp; Recent events</p>
                </div>
            </div>
             <div class="row features-block">
                <div class="col-lg-10 col-lg-offset-1" style="background-color: white;">
                    <br><div id="calendar"></div> <br> 
                </div>
            </div>      
        </div>
    </div><br><br>
</section> -->

<!-- ALUMNI -->
<section class="services text-center" style="margin-top: -40px:">
    <div class="row" style=" background-image: url('/static/FBC/images/bg.jpg'); padding-bottom: 30px;">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="row"  id="alumni"  >
                <div class="col-lg-12">
                    <div class="navy-line"></div>
                    <h1> <span class="navy">ALUMNI</span> </h1>
                    <p>FBA-FBC Alumni Association, Inc. Officers</p>
                </div>
            </div>
            <div class="row features-block">
                <div class="page-row box box-border text-center col-lg-6 col-lg-offset-3">
                    <div class="panel panel-danger">
                        <div class="panel-body">
                           <div class="page-row text-center">
                            <h4><strong>Grace Ann J. Mendoza</strong><br><i><small>President</small></i></h4>
                            </div><!--//page-row-->
                            <div class="page-row text-center m-t">
                                 <h4><strong>Pastor Ebenezer E. Varon</strong><br><i><small>Vice President</small></i></h4>
                            </div><!--//page-row-->
                             <div class="page-row text-center m-t">
                                 <h4><strong>Gina Lisa B. Ramos</strong><br><i><small>Secretary</small></i></h4>
                            </div><!--//page-row-->
                             <div class="page-row text-center m-t">
                                 <h4><strong>Rev. Enrico E. Resano</strong><br><i><small>Treasurer</small></i></h4>
                            </div><!--//page-row-->
                            <div class="page-row text-center m-t">
                                 <h4><B>Joel T. Rufin</B></h4>
                                 <h4><B>Joshua M. Ormeo</B></h4>
                                 <h4><B>Revel J. Almaiz</B></h4>
                                 <h4><B>Dr. Roselle P. Serna</B></h4>
                                 <h4><B>Jun John P. Calamay</B></h4>
                                 <h4><B>Angel A. Sumadia</B></h4>
                                 <h4><B>Alma Ruth M. Maravilla</B></h4>
                                 <h4><B>Timothy A. Gargantiel</B></h4>
                                 <h4><B>Engr. Japheth R. Mapa</B></h4>
                                 <h4><i><small>Board of Trustees</small></i></h4>
                            </div><!--//page-row-->
                            <div class="page-row text-center m-t">
                                 <h4><strong>Pastor Glenn T. Repique</strong><br><i><small>Alumni Coordinator</small></i></h4>
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
    </div><br><br><br><br>
</section>

<!-- RESEARCH -->
<section class="services text-center" style="margin-top: -100px;">
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="row"  id="research"  >
                <div class="col-lg-12">
                    <div class="navy-line"></div>
                    <h1> <span class="navy">RESEARCH</span> </h1>
                    <p>All about our researches and the person behind it</p>
                </div>
            </div>
            <div class="row features-block">
                <div class="page-row box box-border text-center">
                    <div class="panel panel-danger">
                        <div class="panel-body">
                           <div class="page-row text-justify">
                                 <p>Research Department was established in the year 2005.  Though young, yet, Fellowship Baptist College aspires for exemplary performance and outstanding results as it strives to be an effective instrument of the institution in improving the quality of people's life through its relevant research and extension programs and projects. Fellowship Baptist College also adopts the progressing evolution of scientific knowledge and technological skills to meet the ever-changing and dynamic needs of the present society thereby contributing to development and advancement of the human race in general, hence, the need for a College Research Center.</p>
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
    </div><br><br><br><br>
</section>

<!-- FOOTER -->
<footer id="myFooter">
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <h2 class="logo wow fadeInLeft"><a href="#"><img src="/static/FBC/images/footer-logo.png" style="width: 100%;" /></a></h2>
            </div>
            <div class="col-sm-3 wow fadeInUp">
                <h5>Contact Us</h5>
                <ul>
                    <li><i class="fa fa-mobile fa-lg"></i>&nbsp;&nbsp;&nbsp;&nbsp;+63 34 4712156<br>
                    	Fax +63 34 4712167<br>
                        <a href="mailto:info@fbc.edu.ph"><i class="fa fa-envelope"></i>&nbsp;&nbsp;info@fbc.edu.ph</a><br>
                        <a href="https://www.fbc.edu.ph" target="_blank"><i class="fa fa-globe"></i>&nbsp;&nbsp;www.fbc.edu.ph</a></li>
                </ul>
            </div>
            <div class="col-sm-3 wow fadeInUp">
                <h5>Address</h5>
                <ul>
                    <li><i class="fa fa-map-marker fa-lg"></i>&nbsp; Rizal Str., Kabankalan City 6111<br>
                        Negros Occidental, Philippines</li>
                </ul>
            </div>
            <div class="col-sm-3 wow fadeInUp">
                <h5>Like us on Facebook</h5>
                <ul>
                    <li><a href="https://www.facebook.com/fellowshipbaptistcollege/" target="_blank" style="font-size: 1em;"><i class="fa fa-facebook-square fa-lg"></i>&nbsp; Fellowship Baptist College</a><br>
                        <a href="https://www.facebook.com/messages/t/user:175041185900134" target="_blank" style="font-size: 1em;"><i class="fa fa-comments fa-lg"></i>&nbsp; Let's chat in Messenger!</a></li>
                </ul>
            </div>
            <div class="col-sm-9 m-t wow fadeInUp">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3930.2260313714132!2d122.85522551434184!3d9.915123892909373!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x33ac1255b3fcea65%3A0x6eac8c43d7410dcc!2sFellowship+Baptist+College!5e0!3m2!1sen!2sph!4v1515123270730" frameborder="0" style="border:0; width: 100%; height: 250px;" allowfullscreen></iframe>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <p>� 2018 Copyright <a href="https://www.fbc.edu.ph">Fellowship Baptis College</a>. Powered by <a href="https://www.mytechnopal.com" target="_blank">Technopal Software</a></p>
    </div>
</footer>

<script>
	$(document).ready(function () {
    	$('body').scrollspy({
            target: '.navbar-fixed-top',
            offset: 80
        });
        // Page scrolling feature
        $('a.page-scroll').bind('click', function(event) {
            var link = $(this);
            $('html, body').stop().animate({
                scrollTop: $(link.attr('href')).offset().top - 50
            }, 500);
            event.preventDefault();
            $("#navbar").collapse('hide');
        });
    });

    var cbpAnimatedHeader = (function() {
        var docElem = document.documentElement,
                header = document.querySelector( '.navbar-default' ),
                didScroll = false,
                changeHeaderOn = 200;
        function init() {
            window.addEventListener( 'scroll', function( event ) {
                if( !didScroll ) {
                    didScroll = true;
                    setTimeout( scrollPage, 250 );
                }
            }, false );
        }
        function scrollPage() {
            var sy = scrollY();
            if ( sy >= changeHeaderOn ) {
                $(header).addClass('navbar-scroll')
            }
            else {
                $(header).removeClass('navbar-scroll')
            }
            didScroll = false;
        }
        function scrollY() {
            return window.pageYOffset || docElem.scrollTop;
        }
        init();

    })();

    // Activate WOW.js plugin for animation on scrol
    new WOW().init(); 
</script>
    
<!-- For Event use -->
<!-- <script>
    $(document).ready(function () {
        /* initialize the calendar
         -----------------------------------------------------------------*/
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();

        $('#calendar').fullCalendar({
           header: {
            left: 'prev,next today',
            center: 'title',
            right: 'listWeek,listMonth,month'
          },

          // customize the button names,
          // otherwise they'd all just say "list"
          views: {
            listWeek: { buttonText: 'list week' },
            listMonth: { buttonText: 'list month' }
          },

          defaultView: 'listMonth',
            editable: false,
            droppable: false,
            events: [
                {
                    title: 'All Day Event',
                    start: new Date(y, m, 1),
                    url: '#events'
                },
                {
                    title: 'Long Event',
                    start: new Date(y, m, d-5),
                    end: new Date(y, m, d-2),
                    url: '#events'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: new Date(y, m, d-3, 16, 0),
                    allDay: false,
                    url: '#events'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: new Date(y, m, d+4, 16, 0),
                    allDay: false,
                    url: '#events'
                },
                {
                    title: 'Meeting',
                    start: new Date(y, m, d, 10, 30),
                    allDay: false,
                    url: '#events'
                },
                {
                    title: 'Lunch',
                    start: new Date(y, m, d, 12, 0),
                    end: new Date(y, m, d, 14, 0),
                    allDay: false,
                    url: '#events'
                },
                {
                    title: 'Birthday Party',
                    start: new Date(y, m, d+1, 19, 0),
                    end: new Date(y, m, d+1, 22, 30),
                    allDay: false,
                    url: '#events'
                },
                {
                    title: 'Click for Google',
                    start: new Date(y, m, 28),
                    end: new Date(y, m, 29),
                    url: '#events'
                }
            ],
            eventColor: '#e62118'
        });
    });

</script> -->
