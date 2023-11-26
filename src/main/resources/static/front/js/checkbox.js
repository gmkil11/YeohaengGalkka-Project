
function toggleAll(checkbox) {
    const checkboxes = document.querySelectorAll('.agree');
    checkboxes.forEach((box) => {
        box.checked = checkbox.checked;
    });
}

function checkSelectAll() {
    const checkboxAge = document.getElementsByName('ageAgree');
    const checkboxTerm = document.getElementsByName('termsOfUser');
    const checkboxPri = document.getElementsByName('privacy');
    const checkboxThird = document.getElementsByName('thirdPartyAgree');
    const selectAll = document.getElementById('selectAll');

    const allCheckboxes = [...checkboxAge, ...checkboxTerm, ...checkboxPri, ...checkboxThird];

    let allChecked = true;

    for (const checkbox of allCheckboxes) {
        if (!checkbox.checked) {
            allChecked = false;
            break;
        }
    }

    selectAll.checked = allChecked;
}

function toggleCheckbox() {
    var checkboxAll = document.getElementById("checkboxAll")
    var checkbox1 = document.getElementById("checkbox1")
    var checkbox2 = document.getElementById("checkbox2")
    var checkbox3 = document.getElementById("checkbox3")
    var checkbox4 = document.getElementById("checkbox4")
    var agreeCheckboxAll = document.getElementById('selectAll');
    var agreeCheckbox1 = document.getElementById('ageAgree');
    var agreeCheckbox2 = document.getElementById('termsOfUser');
    var agreeCheckbox3 = document.getElementById('privacy');
    var agreeCheckbox4 = document.getElementById('thirdPartyAgree');

    if (agreeCheckboxAll.checked) {
        checkboxAll.classList.add('checked');
    } else {
        checkboxAll.classList.remove('checked');
    }

    if (agreeCheckbox1.checked) {
        checkbox1.classList.add('checked');
    } else {
        checkbox1.classList.remove('checked');
    }

    if (agreeCheckbox2.checked) {
        checkbox2.classList.add('checked');
    } else {
        checkbox2.classList.remove('checked');
    }

    if (agreeCheckbox3.checked) {
        checkbox3.classList.add('checked');
    } else {
        checkbox3.classList.remove('checked');
    }

    if (agreeCheckbox4.checked) {
        checkbox4.classList.add('checked');
    } else {
        checkbox4.classList.remove('checked');
    }

}

