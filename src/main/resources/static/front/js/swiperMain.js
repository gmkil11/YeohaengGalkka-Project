let Arrow1, Arrow2, Arrow3;

window.addEventListener("DOMContentLoaded", (event) => {
    var swiper = new Swiper(".mySwiper", {
        slidesPerView: 5,
        centeredSlides: false,
        spaceBetween: 70,
        pagination: {
            el: ".swiper-pagination",
            type: "fraction",
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
    });

    Arrow1 = document.getElementById("ArrowSwiper1");
    Arrow2 = document.getElementById("ArrowSwiper2");
    Arrow3 = document.getElementById("ArrowSwiper3");
});



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

