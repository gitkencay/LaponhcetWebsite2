<div class="navbar-wrapper">
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Recoletos School of Agriculture</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a id="nav-button" class="page-scroll" href="#page-top">Home</a></li>
					<li><a id="nav-button" class="page-scroll" href="#survey">ROLEx Survey</a></li>
					<li><a id="nav-button" class="page-scroll" href="#streaming">Live Streaming</a></li>
					<li><a id="nav-button" class="page-scroll" href="#contactUs">Contact Us</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>

<div id="inSlider" class="carousel carousel-fade" data-ride="carousel">
	<ol class="carousel-indicators">
		<li data-target="#inSlider" data-slide-to="0" class="active"></li>
		<li data-target="#inSlider" data-slide-to="1"></li>
		<li data-target="#inSlider" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<div class="container">
				<div class="carousel-caption">
					<h1>
						RECOLETOS SCHOOL OF<br>
						<b style="font-size: 2em">AGRICULTURE</b>
					</h1>
					<p style="letter-spacing: 8.5px;">Agriculture of the 21st
						Century.</p>
					<p>
						Joins Panaad sa Negros 2018 | April 14-22 | Organic Village
					</p>
				</div>
				<div class="carousel-image-logo wow zoomIn text-right">
					<img src="/static/RSA/images/logo.png" alt="logo" width="450" />
				</div>
			</div>
		</div>
		<div class="item">
			<div class="container">
				<div class="carousel-caption blank">
					<h1>
						VIRTUAL FARM<br> The modern way to experience<br>
						agricultural farming at its best!
					</h1>
					<p>
						Provides a virtual world to experience "real-life" farming <br>
						to encourage and develop entrepreneurs and farmers to produce <br>
						healthy, quality and organic crops and livestock. Want to know
						how?
					</p>
					<p>
						<a class="btn btn-lg btn-primary" href="" target="_blank"
							role="button">Virtual Farm Tour</a>
					</p>
				</div>
			</div>
			<div class="carousel-image wow zoomIn">
				<img src="/static/RSA/images/slide1.png" alt="slide1" />
			</div>
		</div>
		<div class="item">
			<div class="container">
				<div class="carousel-caption blank">
					<h1>
						Farming Classes Online<br /> Learn new farming techniques <br>whenever
						&amp; wherever you are!
					</h1>
					<p>
						Enroll in our Virtual Classroom where you can attend <br>
						online classes by just using your personal computer or laptops. <br>
						Interact with our online teacher using chat and video calls.
					</p>
					<p>
						<a class="btn btn-lg btn-primary" href="" target="_blank"
							role="button">Learn more</a>
					</p>
				</div>
			</div>
			<div class="carousel-image wow zoomIn">
				<img src="/static/RSA/images/slide2.png" alt="slide2" />
			</div>
		</div>
	</div>
	<a class="left carousel-control" href="#inSlider" role="button"
		data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
		aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#inSlider" role="button"
		data-slide="next"> <span class="glyphicon glyphicon-chevron-right"
		aria-hidden="true"></span> <span class="sr-only">Next</span>
	</a>
</div>
<br><br>
<jsp:include page="bibleVerse.jsp"></jsp:include>
<br><br>
<section id="survey" class="container">
	<jsp:include page="survey.jsp"></jsp:include>
</section>
<br><br>
<section id="streaming" class="container">
	<jsp:include page="streaming.jsp"></jsp:include>
</section>
<br><br>
<section id="contactUs" class="gray-section contact">
	<jsp:include page="contactUs.jsp"></jsp:include>
</section>
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
        registerBack();
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

<script>
$(document).on('click', '#nav-button', function(event) { 
    $("#close").click(); 
});
</script>