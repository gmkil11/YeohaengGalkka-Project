$(document).ready(function () {
    $.datepicker.setDefaults($.datepicker.regional['ko']);

    $("#startDate").datepicker({
        // 시작일 설정...
        onClose: function (selectedDate) {
            $("#endDate").datepicker("option", "minDate", selectedDate);
        }
    });

    $("#endDate").datepicker({
        // 종료일 설정...
        onClose: function (selectedDate) {
            $("#startDate").datepicker("option", "maxDate", selectedDate);
        }
    });

    $("#startDate, #endDate").on("focus", function () {
        $(".ui-datepicker").addClass("custom-datepicker");
    });
});