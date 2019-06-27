function newMemberRegister() {
    $(".mask_member_box3").show();
}

function checkCheckedChargeGift(obj) {

    var checked = obj.checked;

    if(checked == true) {
        document.getElementById("member-charge-gift").removeAttribute("readonly");
        //alert( "被选中")
    }
    if(checked == false) {
        document.getElementById("member-charge-gift").setAttribute("readonly","readonly");
        document.getElementById("member-charge-gift").value = "";
        //alert("去选中")
    }

}

function checkCheckedOff(obj) {

    var checked = obj.checked;

    if(checked == true) {
        document.getElementById("member-service-off").removeAttribute("readonly");
        document.getElementById("member-goods-off").removeAttribute("readonly");
        //alert( "被选中")
    }
    if(checked == false) {

        document.getElementById("member-service-off").setAttribute("readonly","readonly");
        document.getElementById("member-service-off").value = "";

        document.getElementById("member-goods-off").setAttribute("readonly","readonly");
        document.getElementById("member-goods-off").value = "";

        //alert("去选中")
    }

}



function submitNewMember() {

    alert("提交会员注册！");
}

function cancelBox(obj) {

    var div = document.getElementById(obj);

    div.style.display = "none";

}