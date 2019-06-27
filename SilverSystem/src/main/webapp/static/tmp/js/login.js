function submitLogin() {

    var acc = document.getElementById("entry_name").value;
    var pass = document.getElementById("entry_password").value;
    var veriUrl = "./submitLogin"

    $.post(
        veriUrl,
        {
            acc:acc,
            pass:pass
        },function (result) {

            if (result == 1){
                alert("用户名密码错误！");
            } else if (result!=null){
                location="./home?shopid="+result;
            }
        }
    )

}