let Arrow1, Arrow2, Arrow3;

window.onload = function() {
    var swiper = new Swiper(".mySwiper1", {
        slidesPerView: 5,
        centeredSlides: false,
        spaceBetween: 70,
        pagination: {
            el: ".mySwiper1 .swiper-pagination",
            type: "fraction",
        },
        navigation: {
            nextEl: ".mySwiper1 .swiper-button-next",
            prevEl: ".mySwiper1 .swiper-button-prev",
        },
    });

    var swiper2 = new Swiper(".mySwiper2", {
        slidesPerView: 5,
        centeredSlides: false,
        spaceBetween: 70,
        pagination: {
            el: ".mySwiper2 .swiper-pagination",
            type: "fraction",
        },
        navigation: {
            nextEl: ".mySwiper2 .swiper-button-next",
            prevEl: ".mySwiper2 .swiper-button-prev",
        },
    });

    var swiper3 = new Swiper(".mySwiper3", {
        slidesPerView: 5,
        centeredSlides: false,
        spaceBetween: 70,
        pagination: {
            el: ".mySwiper3 .swiper-pagination",
            type: "fraction",
        },
        navigation: {
            nextEl: ".mySwiper3 .swiper-button-next",
            prevEl: ".mySwiper3 .swiper-button-prev",
        },
    });

    Arrow1 = document.getElementById("ArrowSwiper1");
    Arrow2 = document.getElementById("ArrowSwiper2");
    Arrow3 = document.getElementById("ArrowSwiper3");
};



    function isMouseOn1() {
        if (Arrow1) {
            Arrow1.style.opacity = "1";
        }
    }

    function isMouseOn2() {
        if (Arrow2) {
            Arrow2.style.opacity = "1";
        }
    }

    function isMouseOn3() {
        if (Arrow3) {
            Arrow3.style.opacity = "1";
        }
    }

    function isMouseOut1() {
        if (Arrow1) {
            Arrow1.style.opacity = "0";
        }
    }

    function isMouseOut2() {
        if (Arrow2) {
            Arrow2.style.opacity = "0";
        }
    }

    function isMouseOut3() {
        if (Arrow3) {
            Arrow3.style.opacity = "0";
        }
    }

