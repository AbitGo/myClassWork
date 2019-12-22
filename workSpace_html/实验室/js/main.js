(function($) {
    "use strict";
    // Document ready function 
    $(function() {
   /*-------------------------------------
     Auto height for product listing
     -------------------------------------*/


    $(window).on('load resize', function() {
        var wHeight = $(window).height(),
            mLogoH = $('a.logo-mobile-menu').outerHeight();
        wHeight = wHeight - 50;
        $('.mean-nav > ul').css('height', wHeight + 'px');
        headerNsliderResize();
    });

        if ($('.zoom-gallery').length) {

            $('.zoom-gallery').each(function() { // the containers for all your galleries

                $(this).magnificPopup({

                    delegate: 'a.bst-zoom', // the selector for gallery item

                    type: 'image',

                    gallery: {

                        enabled: true

                    }

                });

            });

        }

        /* Fixing for hover effect at IOS */

        $('*').on('touchstart', function() {

            $(this).trigger('hover');

        }).on('touchend', function() {

            $(this).trigger('hover');

        });

        var priceSlider = document.getElementById('price-range-filter');

        if (priceSlider) {

            noUiSlider.create(priceSlider, {

                start: [20, 80],

                connect: true,

                /*tooltips: true,*/

                range: {

                    'min': 0,

                    'max': 100

                },

                format: wNumb({

                    decimals: 0

                }),

            });

            var marginMin = document.getElementById('price-range-min'),

                marginMax = document.getElementById('price-range-max');

            priceSlider.noUiSlider.on('update', function(values, handle) {

                if (handle) {

                    marginMax.innerHTML = "$" + values[handle];

                } else {

                    marginMin.innerHTML = "$" + values[handle];

                }

            });

        }

        if ($('.gallery-wrapper').length) {

            $('.gallery-wrapper').magnificPopup({
                type: 'image',
                delegate: 'a.zoom',
                gallery: {
                    enabled: true
                }
            });
        }

        if ($('.gallery-wrapper1').length) {

            $('.gallery-wrapper1').magnificPopup({
                type: 'image',
                delegate: 'a.zoom',
                gallery: {
                    enabled: true
                }
            });
        }

        if ($('.gallery-wrapper2').length) {

            $('.gallery-wrapper2').magnificPopup({
                type: 'image',
                delegate: 'a.zoom',
                gallery: {
                    enabled: true
                }
            });
        }
        /*-------------------------------------

         Popup

         -------------------------------------*/
        if ($(".popup-youtube").length) {



            $('.popup-youtube').magnificPopup({

                disableOn: 700,

                type: 'iframe',

                mainClass: 'mfp-fade',

                removalDelay: 160,

                preloader: false,

                fixedContentPos: false

            });
        }

        $('#product-filter-trigger').on("click", function() {

            if ($(window).width() > '990') {

                $('#filter-parameter-wrapper').addClass('open');

                $('#product-filter-trigger i').removeClass('fa-bars');

                $('#product-filter-trigger i').addClass('fa-times');

            }

            return false;

        });
        $('#filter-parameter-wrapper .close').on("click", function() {

            $('#filter-parameter-wrapper').removeClass('open');

            return false;

        });

        $('#product-search-btn').on("click", function() {

            if ($(window).width() > '990') {

                $('.search-panel').addClass('open');

            }

            return false;

        });

        $('.pbox-search-area .close').on("click", function() {

            $('.search-panel').removeClass('open');

            return false;

        });

        if($("#myContainer").length > 0){
          $('#myContainer').multiscroll({
            sectionsColor: ['#ffffff', '#ffffff', '#ffffff', '#ffffff'],
            anchors: ['first', 'second', 'third', 'fourth'],
            menu: '#menu',
            navigation: true,
            navigationTooltips: ['One', 'Two', 'Three', 'Four'],
            loopBottom: true,
            easing: 'easeOutBack',
            loopTop: true

            });  
        }

        /*-------------------------------------

        On click loadmore functionality 

        -------------------------------------*/

        $('.loadmore').on('click', 'a', function(e) {

            e.preventDefault();

            var _this = $(this),

                _parent = _this.parents('.menu-list-wrapper'),

                _target = _parent.find('.menu-list'),

                _set = _target.find('.menu-item.hidden').slice(0, 3); // Herre 2 is the limit

            if (_set.length) {

                _set.animate({ opacity: 0 });

                _set.promise().done(function() {

                    _set.removeClass('hidden');

                    _set.show().animate({ opacity: 1 }, 1000);

                });

            } else {

                _this.text('No more item to display');

            }

            return false;
        });

        /*-------------------------------------

         Input Quantity left & right activation code

         -------------------------------------*/
         $(".incr-btn").on("click", function (e) {
            var $button = $(this);
            var oldValue = $button.parent().find('.quantity').val();
            $button.parent().find('.incr-btn[data-action="decrease"]').removeClass('inactive');
            if ($button.data('action') == "increase") {
                var newVal = parseFloat(oldValue) + 1;
            } else {
                // Don't allow decrementing below 1
                if (oldValue > 1) {
                    var newVal = parseFloat(oldValue) - 1;
                } else {
                    newVal = 1;
                    $button.addClass('inactive');
                }
            }
            $button.parent().find('.quantity').val(newVal);
            e.preventDefault();
        });
         /*-------------------------------------

         Input Quantity Up & Down activation code

         -------------------------------------*/

        $('#quantity-holder,#quantity-holder2').on('click', '.quantity-plus', function() {



            var $holder = $(this).parents('.quantity-holder');

            var $target = $holder.find('input.quantity-input');

            var $quantity = parseInt($target.val(), 10);

            if ($.isNumeric($quantity) && $quantity > 0) {

                $quantity = $quantity + 1;

                $target.val($quantity);

            } else {

                $target.val($quantity);

            }

        }).on('click', '.quantity-minus', function() {



            var $holder = $(this).parents('.quantity-holder');

            var $target = $holder.find('input.quantity-input');

            var $quantity = parseInt($target.val(), 10);

            if ($.isNumeric($quantity) && $quantity >= 2) {

                $quantity = $quantity - 1;

                $target.val($quantity);

            } else {

                $target.val(1);

            }



        });
        /*-------------------------------------

         Jquery Serch Box

         -------------------------------------*/

        $('#product-search-btn').on('click', function(e) {

            e.preventDefault();

            $(this).prev('.search-panel').slideToggle('slow');

        });

    });
     /*-------------------------------------
     Jquery Serch Box
     -------------------------------------*/

        $(document).on('click', '#search-button', function(e) {
            e.preventDefault();

            var targrt = $(this).prev('.search-form');
            targrt.animate({
                width: ["toggle", "swing"],
                height: ["toggle", "swing"],
                opacity: "toggle"
            }, 500, "linear");

            return false;

        });

    /*-------------------------------------

     jQuery MeanMenu activation code

     --------------------------------------*/

    $('nav#dropdown').meanmenu({ siteLogo: "<a href='index.html' class='logo-mobile-menu'><img src='img/mobile-logo.png' /></a>" });

    /*-------------------------------------

     Wow js Active

     -------------------------------------*/

    new WOW().init();

   /*-------------------------------------

     Jquery Scollup

     -------------------------------------*/
    $.scrollUp({

        scrollText: '<i class="fa fa-angle-double-up"></i>',

        easingType: 'linear',

        scrollSpeed: 900,

        animation: 'fade'

    });
    /*-------------------------------------

     Window load function

     -------------------------------------*/

    $(window).on('load', function() {       
        $('#preloader').fadeOut('slow', function() {
            $(this).remove();
        });

        var container = $('.isotope-container');
            console.log(container.length);
        if (container.length > 0) {
            console.log('apple');
            container.each(function(){

                var wrapper = $(this),
                    isoWrap = wrapper.find(".featured-container"),
                    isoBtns = wrapper.find(".isotope-classes-tab"),
                    isotope = isoWrap.isotope({
                        animationOptions: {
                            duration: 750,
                            easing: 'linear',
                            queue: false
                        }
                    });
                    isoBtns.on('click', 'a', function (e) {
                        e.preventDefault();
                        var self = $(this),
                            wrapBtn = self.parent('.isotope-classes-tab'),
                            selector = self.attr('data-filter');
                        wrapBtn.find('a').removeClass('current');
                        self.addClass('current');
                        isotope.isotope({
                            filter: selector
                        });
                        return false;
                    });

            });
        }
        
        var testimonialCarousel = $('#rt-testimonial-slider-wrap');

        if (testimonialCarousel.length) {
            testimonialCarousel.find('.testimonial-sliders').slick({

                slidesToShow: 1,

                slidesToScroll: 1,

                arrows: true,

                autoplay: true,

                asNavFor: '.testimonial-carousel',

                prevArrow: '<span class="bst-prev bst-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></span>',

                nextArrow: '<span class="bst-next bst-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></span>'

            });

            testimonialCarousel.find('.testimonial-carousel').slick({

                slidesToShow: 3,

                slidesToScroll: 1,

                asNavFor: '.testimonial-sliders',

                dots: false,

                arrows: true,

                prevArrow: true,

                nextArrow: true,

                centerMode: true,

                centerPadding: '0px',

                focusOnSelect: true

            });



        }

    }); // end window load function

    /*-------------------------------------

     About Counter

     -------------------------------------*/

    var aboutContainer = $('.about-counter');

    if (aboutContainer.length) {

        aboutContainer.counterUp({

            delay: 50,

            time: 5000

        });
    }

    /*-------------------------------------

     Select2 activation code

     -------------------------------------*/

    if ($('#checkout-form  select.select2').length) {

        $('#checkout-form select.select2').select2({

            theme: 'classic',

            dropdownAutoWidth: true,

            width: '100%'

        });

    }

    $(window).on('load resize', function() {

        equalHeight();

        clickEqualHeight();

    });



    function clickEqualHeight() {

        $("#trigger button").on('click', function() {



            equalHeight();



        });

    }

    function equalHeight() {

        var imgH = 0,

            boxH = 0,

            wWidth = $(window).width(),

            allH;

        $('.equal-height-wrap .item-img,.equal-height-wrap .item-content').height('auto');

        if (wWidth > 767) {

            $('.equal-height-wrap').each(function() {

                var self = $(this);



                var TempImgH = self.find('.item-img').height();

                imgH = TempImgH > imgH ? TempImgH : imgH;

                var TempBoxH = self.find('.item-content').outerHeight();

                boxH = TempBoxH > boxH ? TempBoxH : boxH;

            });



            allH = imgH;

            allH = boxH > imgH ? boxH : imgH;

            $('.equal-height-wrap .item-img,.equal-height-wrap .item-content').height(allH + "px");

        }



    }

    /*-------------------------------------

     Contact Form processing

     -------------------------------------*/

    var contactForm = $('#contact-form');

    if (contactForm.length) {

        contactForm.validator().on('submit', function(e) {

            var _this = $(this),

                target = contactForm.find('.form-response');

            if (e.isDefaultPrevented()) {

                target.html("<div class='alert alert-danger'><p>Please select all required field.</p></div>");

            } else {

                $.ajax({

                    url: "vendor/php/form-process.php",

                    type: "POST",

                    data: contactForm.serialize(),

                    beforeSend: function() {

                        target.html("<div class='alert alert-info'><p>Loading ...</p></div>");

                    },

                    success: function(text) {

                        if (text === "success") {

                            _this[0].reset();

                            target.html("<div class='alert alert-success'><p><i class='fa fa-check' aria-hidden='true'></i>Message has been sent successfully.</p></div>");

                        } else {

                            target.html("<div class='alert alert-danger'><p>" + text + "</p></div>");

                        }

                    }

                });

                return false;

            }

        });

    }

    /*-------------------------------------

     Countdown activation code

     -------------------------------------*/

    $('#countdown').countdown('2017/6/01', function(e) {

        $(this).html(e.strftime("<div class='countdown-section'><h3>%D</h3> <p>Day%!D</p> </div><div class='countdown-section'><h3>%H</h3> <p>Hour%!H</p> </div><div class='countdown-section'><h3>%M</h3> <p>Min%!M</p> </div><div class='countdown-section'><h3>%S</h3> <p>Sec%!S</p> </div>"));


   });

    /*-------------------------------------

     Carousel slider initiation

     -------------------------------------*/

    $('.construction-carousel').each(function() {

        var carousel = $(this),

            loop = carousel.data('loop'),

            items = carousel.data('items'),

            margin = carousel.data('margin'),

            stagePadding = carousel.data('stage-padding'),

            autoplay = carousel.data('autoplay'),

            autoplayTimeout = carousel.data('autoplay-timeout'),

            smartSpeed = carousel.data('smart-speed'),

            dots = carousel.data('dots'),

            nav = carousel.data('nav'),

            navSpeed = carousel.data('nav-speed'),

            rXsmall = carousel.data('r-x-small'),

            rXsmallNav = carousel.data('r-x-small-nav'),

            rXsmallDots = carousel.data('r-x-small-dots'),

            rXmedium = carousel.data('r-x-medium'),

            rXmediumNav = carousel.data('r-x-medium-nav'),

            rXmediumDots = carousel.data('r-x-medium-dots'),

            rSmall = carousel.data('r-small'),

            rSmallNav = carousel.data('r-small-nav'),

            rSmallDots = carousel.data('r-small-dots'),

            rMedium = carousel.data('r-medium'),

            rMediumNav = carousel.data('r-medium-nav'),

            rMediumDots = carousel.data('r-medium-dots'),

            rLarge = carousel.data('r-large'),

            rLargeNav = carousel.data('r-large-nav'),

            rLargeDots = carousel.data('r-large-dots'),

            center = carousel.data('center');



        carousel.owlCarousel({

            loop: (loop ? true : false),

            items: (items ? items : 4),

            lazyLoad: true,

            margin: (margin ? margin : 0),

            autoplay: (autoplay ? true : false),

            autoplayTimeout: (autoplayTimeout ? autoplayTimeout : 1000),

            smartSpeed: (smartSpeed ? smartSpeed : 250),

            dots: (dots ? true : false),

            nav: (nav ? true : false),

            navText: ["<i class='icofont icofont-swoosh-left' aria-hidden='true'></i>", "<i class='icofont icofont-swoosh-right' aria-hidden='true'></i>"],

            navSpeed: (navSpeed ? true : false),

            center: (center ? true : false),

            responsiveClass: true,

            responsive: {

                0: {

                    items: (rXsmall ? rXsmall : 1),

                    nav: (rXsmallNav ? true : false),

                    dots: (rXsmallDots ? true : false)

                },

                480: {

                    items: (rXmedium ? rXmedium : 2),

                    nav: (rXmediumNav ? true : false),

                    dots: (rXmediumDots ? true : false)

                },

                768: {

                    items: (rSmall ? rSmall : 3),

                    nav: (rSmallNav ? true : false),

                    dots: (rSmallDots ? true : false)

                },

                992: {

                    items: (rMedium ? rMedium : 5),

                    nav: (rMediumNav ? true : false),

                    dots: (rMediumDots ? true : false)

                },

                1199: {

                    items: (rLarge ? rLarge : 6),

                    nav: (rLargeNav ? true : false),

                    dots: (rLargeDots ? true : false)

                }

            }

        });
    });
    /*-------------------------------------

     Window onLoad and onResize event trigger

     -------------------------------------*/

    $(window).on('load resize', function() {

        //Define the maximum height for mobile menu

        var wHeight = $(window).height(),

            mLogoH = $('a.logo-mobile-menu').outerHeight();

        wHeight = wHeight - 50;

        $('.mean-nav > ul').css('height', wHeight + 'px');



    });
   
     /*-------------------------------------
     Jquery Stiky Menu at window Load
     -------------------------------------*/
    $(window).on('scroll', function () {
        var s = $('#sticker'),
            w = $('body'),
            h = s.outerHeight(),
            windowpos = $(window).scrollTop(),
            windowWidth = $(window).width(),
            h3 = s.parent('#header-three'),
            h2 = s.parent('#header-two'),
            h1 = s.parent('#header-one'),
            h1H = h1.find('.header-top-bar').outerHeight(),
            topBar = s.prev('.header-top-bar'),
            tempMenu;
        if (windowWidth > 991) {
            w.css('padding-top', '');
            var topBarH, mBottom = 0;
            if (h3.length) {
                topBarH = h = 1;
                mBottom = 0;
            } else if (h2.length) {
                mBottom = h2.find('.main-menu-area').outerHeight();
                topBarH = w.find("#top-slider").outerHeight();
                topBarH = mBottom + topBarH;
            } else if (h1.length) {
                topBarH = topBar.outerHeight();
                if (windowpos <= topBarH) {
                    if (h1.hasClass('header-fixed')) {
                        h1.css('top', '-' + windowpos + 'px');
                    }
                }
            }
            if (windowpos >= topBarH) {
                if (h1.length || h3.length) {
                    s.addClass('stick');
                }
                if (h1.length) {
                    if (h1.hasClass('header-fixed')) {
                        h1.css('top', '-' + topBarH + 'px');
                    } else {
                        w.css('padding-top', h + 'px');
                    }
                } else if (h2.length) {
                    h2.addClass('hide-menu');
                    tempMenu = h2.find('.main-menu-area').clone();
                    tempMenu.addClass('temp-main-menu stick').attr("id", '');
                    tempMenu.css({opacity: 0});
                    if (h2.find('.temp-main-menu.stick').length == 0) {
                        h2.append(tempMenu);
                        h2.find('.temp-main-menu.stick').animate({opacity: 1}, 100);
                    }
                    if (h2.find('.temp-main-menu.stick').length > 1) {
                        h2.find('.temp-main-menu.stick').last().remove();
                    }
                }
            } else {
                s.removeClass('stick');
                if (h1.length) {
                    w.css('padding-top', 0);
                } else if (h2.length) {
                    h2.removeClass('hide-menu');
                    h2.find('.stick.temp-main-menu').remove();
                }
            }
        }
    });

    function headerNsliderResize() {
        var Hh1 = $('#header-one, #header-three'),
            wWidth = $(window).width(),
            Hh1slider = Hh1.parents('#wrapper').find("#header-area-space"),
            mHeight = Hh1.outerHeight();
        if (wWidth < 992) {
            mHeight = $('body > .mean-bar').outerHeight();
        }
        Hh1slider.css("margin-top", mHeight + 'px');
    }
   
    $('#review-form').on('click', '.rate-wrapper .rate .rate-item', function() {

        var self = $(this),

            target = self.parent('.rate');

        target.addClass('selected');

        target.find('.rate-item').removeClass('active');

        self.addClass('active');

    });
    /*-------------------------------------
     Accordion
     -------------------------------------*/
    var accordion = $('#accordion');
    accordion.children('.panel').children('.panel-collapse').each(function () {
        if ($(this).hasClass('in')) {
            $(this).parent('.panel').children('.panel-heading').addClass('active');
        }
    });
    accordion.on('show.bs.collapse', function (e) {
        $(e.target).prev('.panel-heading').addClass('active');
    }).on('hide.bs.collapse', function (e) {
        $(e.target).prev('.panel-heading').removeClass('active');
    });
    /*-------------------------------------

     Accordion

     -------------------------------------*/
    var accordion = $('#accordion');

    accordion.find('.panel-collapse').each(function() {

        if ($(this).hasClass('in')) {

            $(this).parents('.panel').find('.panel-heading').addClass('active');

        }

    });

    accordion

        .on('show.bs.collapse', function(e) {

        $(e.target).parents('.panel').find('.panel-heading').addClass('active');

    })

    .on('hide.bs.collapse', function(e) {

        $(e.target).parents('.panel').find('.panel-heading').removeClass('active');

    });
    /*----------------------------------- 

    login pop up foem 

    -------------------------------------*/
    $('#login-button').on('click', function(e) {

        e.preventDefault();

        var self = $(this),

            target = self.next('#login-form');

        if (self.hasClass('open')) {

            target.slideUp('slow');

            self.removeClass('open');

        } else {

            target.slideDown('slow');

            self.addClass('open');

        }

    });

    $('#login-form').on('click', '.form-cancel', function(e) {

        e.preventDefault();

        var self = $(this),

            parent = self.parents('#login-form'),

            loginButton = parent.prev('#login-button');

        parent.slideUp('slow');

        loginButton.removeClass('open');

    });
    /*----------------------------------- 

    Product View Trigger 

    -------------------------------------*/

    $('.product-view-trigger').on('click', function (e) {
        var self = $(this),
            data = self.attr("data-type"),
            target = $("#category-view");
        self.parents('.layout-switcher').find('li.active').removeClass('active');
        self.parent('li').addClass('active');
        target.children('.row').find('>div').animate({
            opacity: 0,
        }, 200, function () {
            if (data === "category-grid-layout1") {
                target.removeClass('category-list-layout1');
                target.addClass('category-grid-layout1');
            } else if (data === "category-list-layout1") {
                target.removeClass('category-grid-layout1');
                target.addClass('category-list-layout1');
            } 
            target.children('.row').find('>div').animate({
                opacity: 1,
            }, 100);
        });
        e.preventDefault();
        return false;
    });
    /*-------------------------------------

     Search & filter

     -------------------------------------*/
     $('#filter-btn').on("click", function() {

        if ($(window).width() > '990') {

            $('.filter-area').addClass('open');

            $('#filter-btn a').removeClass('fa-bars');

            $('#filter-btn a').addClass('fa-times');

        }

        return false;

    });
    $('.filter-area .close').on("click", function() {

        $('.filter-area').removeClass('open');

        return false;

    });

    $('#search-btn').on("click", function() {

        if ($(window).width() > '990') {

            $('.search-panel').addClass('open');

        }

        return false;

    });
    $('.pbox-search-area .close').on("click", function() {

        $('.search-panel').removeClass('open');

        return false;

    });
    /*-------------------------------------

     Contact Form processing

     -------------------------------------*/
   var contactForm = $('#contact-form');
    if (contactForm.length) {
        contactForm.validator().on('submit', function(e) {
            var _this = $(this),
                target = contactForm.find('.form-response');
            if (e.isDefaultPrevented()) {
                target.html("<div class='alert alert-danger'><p>Please select all required field.</p></div>");
            } else {
                $.ajax({
                    url: "vendor/php/form-process.php",
                    type: "POST",
                    data: contactForm.serialize(),
                    beforeSend: function() {
                        target.html("<div class='alert alert-info'><p>Loading ...</p></div>");
                    },
                    success: function(text) {
                        if (text === "success") {
                            _this[0].reset();
                            target.html("<div class='alert alert-success'><p><i class='fa fa-check' aria-hidden='true'></i>Message has been sent successfully.</p></div>");
                        } else {
                            target.html("<div class='alert alert-danger'><p>" + text + "</p></div>");
                        }
                    }
                });
                return false;
            }
        });
    }
    /*-------------------------------------
    Google Map
    -------------------------------------*/
    if ($('#googleMap').length) {

        //Map initialize

        var initialize = function() {

            var mapOptions = {

                zoom: 15,

                scrollwheel: false,

                center: new google.maps.LatLng(-37.81618, 144.95692)

            };

            var map = new google.maps.Map(document.getElementById("googleMap"),

                mapOptions);

            var marker = new google.maps.Marker({

                position: map.getCenter(),

                animation: google.maps.Animation.BOUNCE,

                icon: 'img/map-marker.png',

                map: map

            });
        }
        google.maps.event.addDomListener(window, "load", initialize);

    }
    $('#filter-btn').on("click", function() {

        if ($(window).width() > '990') {

            $('.filter-area').addClass('open');

            $('#filter-btn i').removeClass('fa-bars');

            $('#filter-btn i').addClass('fa-times');

        }

        return false;

    });
    $('.filter-area .close').on("click", function() {

        $('.filter-area').removeClass('open');

        return false;

    });
    $('#search-btn').on("click", function() {

        if ($(window).width() > '990') {

            $('.search-panel').addClass('open');

        }

        return false;

    });
    $('.pbox-search-area .close').on("click", function() {

        $('.search-panel').removeClass('open');

        return false;

    });
 $('.navbar-toggle').on('click', function() {
        $('body').removeClass('menu-is-closed').addClass('menu-is-opened');
    });

    $('.close-menu, .click-capture').on('click', function() {
        $('body').removeClass('menu-is-opened').addClass('menu-is-closed');
        $('.menu-list ul').slideUp(300);
    });

    var dropToggle = $('.menu-list > li').has('ul').children('a');
    dropToggle.on('click', function() {
        dropToggle.not(this).closest('li').find('ul').slideUp(200);
        $(this).closest('li').children('ul').slideToggle(200);
        return false;
    });


})(jQuery);
