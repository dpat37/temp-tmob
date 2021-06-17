(function($) {
    'use strict';

    // App
    var app = (function() {
        var timing = 300,
            $w,
            $slider,
            $menu;

        // App: Start callback
        function start() {
            // Window: Resize Event
            $w.resize(function() {
                setTimeout(resize, timing);
            });

            // Default resizing trigger
            resize(function() {
                plugins();
                events();
            });
        }

        // App: Resize callback
        function resize(fn) {
            // Slider: Reset height
            $slider.height($w.height());

            // Resize Slides
            resizeSlides();

            // Callback
            if (fn && fn instanceof Function) {
                fn();
            }
        }

        // App: Resize Slides
        function resizeSlides() {
            $('.flex-viewport li', $slider).each(function() {
                $(this).height($w.height());
            });
        }

        // App: Embed plugins
        function plugins() {
            // Plugin: Flexslider
            $slider.flexslider({
                animation: 'slide',
                slideshow: false,
                controlNav: true,
                directionNav: true
            });

            resizeSlides();

            if (!isWebkit()) {
                $('.heading-title').addClass('nobg');
            }
        }

        // App: Events
        function events() {
            $('#menu-toggle').click(function(e) {
                e.preventDefault();

                if ($(this).hasClass('active')) {
                    $menu.removeClass('active');
                    $(this).removeClass('active');
                } else {
                    $menu.addClass('active');
                    $(this).addClass('active');
                }
            });
        }

        // App: Webkit
        function isWebkit() {
            return (navigator.userAgent.toLowerCase().indexOf('chrome') > -1) || (navigator.userAgent.toLowerCase().indexOf('safari') > -1);
        }

        // App: Initializing
        function initialize() {
            $w = $(window);
            $slider = $('#slider');
            $menu = $('#menu');

            // Start
            setTimeout(start, timing);
        }

        return {
            initialize: initialize
        };
    })();

    // Window: Load event
    window.onload = app.initialize;
}(jQuery));

var add10URL = '/add-ten/v1/';
var addOpURL = '/adder/v1/';

function doAdd10Form(){
    var num = document.getElementById('add10In').value;
    var url = add10URL + num;

    $.get(url, function(resp){document.getElementById('add10Result').value = resp.value;});

}

function doAddOpForm(){
    var num1 = document.getElementById('addNum1').value;
    var num2 = document.getElementById('addNum2').value;

    var url = addOpURL + num1 + '/' + num2;
    $.get(url, function(resp){document.getElementById('addOpResult').value = resp.value;});

}

var currCount = 0;
function doCounter(){
    currCount++;
    document.getElementById('counterResult').value = currCount;
}