window.addEventListener("DOMContentLoaded", function(){
    const userId = document.getElementsByName('userId');
    console.log(userId);


});

document.addEventListener("DOMContentLoaded", function () {
    var payBox = document.querySelector(".pay-box");
    var roomExpArea = document.getElementById("roomExpArea");

    window.addEventListener("scroll", function () {
        var scrollPosition = window.scrollY;
        var roomExpAreaTop = roomExpArea.offsetTop;
        var roomExpAreaHeight = roomExpArea.clientHeight;

        // room-exp-area 범위 내에 있을 때
        if (scrollPosition + 20 >= roomExpAreaTop && scrollPosition <= roomExpAreaTop + roomExpAreaHeight) {
            payBox.style.position = "fixed";
            payBox.style.top = 20 + "px";
        } else {
            // room-exp-area 범위를 벗어나면 원래 위치로
            payBox.style.position = "absolute";
            payBox.style.top = "initial";
        }
    });
});
