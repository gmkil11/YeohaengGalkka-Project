window.addEventListener("DOMContentLoaded", function() {
    const checkAlls = document.getElementsByClassName("checkall");
    for (const el of checkAlls) {
        el.addEventListener("click", function() {
           const target = el.dataset.targetName;
           const chks = document.getElementsByName(target);
           for (const chk of chks) {
               chk.checked = this.checked;
           }
        });
    }
})