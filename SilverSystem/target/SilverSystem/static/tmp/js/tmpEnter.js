function scanner(obj) {

    //alert(obj.value);
    var goodsId = obj.value;
    var shopid =GetUrlParam("shopid");
    var tableBody = document.getElementById("myTbody");
    obj.value = "";
    var scanUrl = "./scanner"
    $.post(
        scanUrl,
        {
            goodsId:goodsId,
            merchantid:shopid
        },
        function (result) {

            if(result == 1)
            {
                alert("货品没有库存了！")
            }else if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }else if(result!=null)
            {
                var jsObj = JSON.parse(result);
                while(tableBody.hasChildNodes()) //当elem下还存在子节点时 循环继续
                {
                    tableBody.removeChild(tableBody.firstChild);
                }
                var moneyAmount =0;
                var count =0;
                var IntergralCount =0;

                for(i=0;i<jsObj.length;i++)
                {
                    var newtr = document.createElement("tr");
                    var goodsid = document.createElement("th");
                    goodsid.innerHTML = jsObj[i].goodsId;

                    var goodsname = document.createElement("th");
                    goodsname.innerHTML = jsObj[i].goodsName;

                    var goodsPrice = document.createElement("th");
                    goodsPrice.innerHTML = jsObj[i].goodsPrice;

                    var GoodsNum = document.createElement("th");
                    GoodsNum.innerHTML = jsObj[i].goodsCount;

                    var GoodsInteral = document.createElement("th");
                    GoodsInteral.innerHTML = jsObj[i].goodsIntergral;

                    var Remarks = document.createElement("th");
                    var opreations = document.createElement("th");

                    //operation组件内包含增、减、删除
                    var op_add = document.createElement("i");
                    var op_add_son = document.createElement("a");

                    op_add.setAttribute("class","cls-op-add");
                    op_add_son.setAttribute("class","fa fa-plus-square-o");
                    op_add_son.setAttribute("id","op-add");
                    op_add_son.addEventListener('click',function (ev) {

                        var tr = ev.path[3];
                        var id = tr.cells[0].innerHTML;
                        var addUrl = "./billAdd";
                        $.post(
                            addUrl,
                            {
                                goodsid:id,
                                merchantid:shopid
                            },
                            function (result) {
                                if (isTimeOut(result)){
                                    alert("登录超时，请重新登录！");
                                    window.location = "./login";
                                    return;
                                }
                                showAll();
                            }
                        )
                    });
                    op_add.appendChild(op_add_son);
                    //op_add.innerHTML = "   "

                    var op_dec = document.createElement("i");
                    var op_dec_son = document.createElement("a");

                    op_dec.setAttribute("class","cls-op-dec");
                    op_dec_son.setAttribute("class","fa fa-minus-square-o");
                    op_dec_son.setAttribute("id","op-dec");
                    op_dec_son.addEventListener('click',function (ev) {
                        var tr = ev.path[3];
                        var id = tr.cells[0].innerHTML;
                        var addUrl = "./billDec";
                        $.post(
                            addUrl,
                            {
                                goodsid:id,
                                merchantid:shopid
                            },
                            function (result) {
                                if (isTimeOut(result)){
                                    alert("登录超时，请重新登录！");
                                    window.location = "./login";
                                    return;
                                }
                                if(result == 1)
                                {
                                    alert ("数量为1不能减少，如不需要请删除");
                                }
                                else
                                {
                                    showAll();
                                }
                            }
                        );
                    });
                    op_dec.appendChild(op_dec_son);
                    //op_dec.innerHTML = "   ";

                    var op_del = document.createElement("i");
                    var op_del_son = document.createElement("a");
                    op_del.setAttribute("class","cls-op-del");
                    op_del_son.setAttribute("class","fa fa-trash");
                    op_del_son.setAttribute("id","op-del");
                    op_del_son.addEventListener('click',function (ev) {

                        var tr = ev.path[3];
                        var id = tr.cells[0].innerHTML;
                        var addUrl = "./billDel";
                        $.post(
                            addUrl,
                            {
                                goodsid:id,
                                merchantid:shopid
                            },
                            function (result) {
                                if (isTimeOut(result)){
                                    alert("登录超时，请重新登录！");
                                    window.location = "./login";
                                    return;
                                }
                                showAll();
                            }
                        );
                    });
                    op_del.appendChild(op_del_son);
                    //op_del.innerHTML = "删除"

                    opreations.appendChild(op_add);
                    opreations.appendChild(op_dec);
                    opreations.appendChild(op_del);

                    moneyAmount=Number(moneyAmount) + Number(jsObj[i].goodsPrice)*Number(jsObj[i].goodsCount);
                    count= Number(count) + Number(jsObj[i].goodsCount);
                    IntergralCount=Number(IntergralCount) + Number(jsObj[i].goodsIntergral);


                    newtr.appendChild(goodsid)
                    newtr.appendChild(goodsname)
                    newtr.appendChild(goodsPrice)
                    newtr.appendChild(GoodsNum)
                    newtr.appendChild(GoodsInteral)
                    newtr.appendChild(Remarks)
                    newtr.appendChild(opreations)
                    tableBody.appendChild(newtr)
                }

                var moneyAmountCom = document.getElementById("totalMoney");
                var countCom = document.getElementById("totalQuantity");
                var IntergralCountCom = document.getElementById("totalIntegral");

                moneyAmountCom.innerHTML = moneyAmount;
                countCom.innerHTML = count;
                IntergralCountCom.innerHTML =IntergralCount;

                //alert(jsObj.goodsName);
            }

        }
    )

}

function getMember() {
    var inputText = document.getElementById("inputMember");

    var memberMsisdn = inputText.value;

    var getMemberUrl = "./getMember"

    $.post(
        getMemberUrl,
        {
            memberMsisdn:memberMsisdn
        },
        function (result) {

            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            if(result!=null)
            {
                var mbObj = JSON.parse(result);
                document.getElementById("member-mbid").innerHTML = mbObj.memberID;
                document.getElementById("member-mbname").innerHTML = mbObj.memberName;
                document.getElementById("member-mblv").innerHTML = mbObj.memberLevel;
                document.getElementById("member-identity").innerText = mbObj.identity;
                document.getElementById("member-mbintergral").innerHTML = mbObj.integral;
                document.getElementById("member-mbbalance").innerHTML = mbObj.balance;
            }else
            {
                alert("查询不到会员，请与管理员联系！");
            }
        }
    )

}

function getTotal() {

    //alert("is here");
    var time = getNowFormatDate();
    document.getElementById("transaction-time").value=time;
    var amount = document.getElementById("totalMoney").innerHTML;
    var memberID = document.getElementById("member-mbid").innerHTML;
    var memberMsisdn = document.getElementById("inputMember").value;
    var getOrderUrl = "./getOrder"

    var merchantid = GetUrlParam("shopid");

    if(memberID == "" || memberID ==null||memberID=="undefined")
    {
        $.post(
            getOrderUrl,
            {
                merchantid:merchantid
            },
            function (result) {
                if (isTimeOut(result)){
                    alert("登录超时，请重新登录！");
                    window.location = "./login";
                    return;
                }
                var orderObj = JSON.parse(result);
                document.getElementById("total-pay-id").value=getcookie("orderId_offline");
                document.getElementById("total-pay-amount").value = orderObj.moneyAmount+"元";
                document.getElementById("total-pay-off").value = "非会员";
                //alert("not member")
            }

        )

    }else
    {
        //获取会员折扣
        $.post(
            getOrderUrl,
            {
                memberMsisdn:memberMsisdn,
                merchantid:merchantid
            },
            function (result) {

                if (isTimeOut(result)){
                    alert("登录超时，请重新登录！");
                    window.location = "./login";
                    return;
                }
                var orderObj = JSON.parse(result);
                document.getElementById("total-pay-id").value=getcookie("orderId_offline");
                document.getElementById("total-pay-amount").value = orderObj.moneyAmount+"元";
                document.getElementById("total-pay-off").value = orderObj.memberAmount+"元";
                //alert("is member !")
            }
        )
    }



}

function activeChange() {
    var activeValue = document.getElementById("active").value;
    var payAmount = document.getElementById("total-pay-amount").value
    var payOff = document.getElementById("total-pay-off").value
    var memberID = document.getElementById("member-mbid").innerHTML;
    var decActive = getActiveNum(activeValue);
    var num= payAmount.replace(/元/ig,"");
    var memberNum= payOff.replace(/元/ig,"");
    var finalAmount ="";


    if(memberID == "" || memberID ==null||memberID=="undefined")
    {
        finalAmount = Number(num)-Number(decActive);
    }else{
        finalAmount = Number(memberNum)-Number(decActive);
    }

    //2018-12-30 新增减免支付
    var decAmount = document.getElementById("dec-amount").value;
    var decAuthor = document.getElementById("author-list").value;


    if (decAmount!=""||null!=decAmount)
    {
        if (finalAmount=="")
        {
            if(memberID == "" || memberID ==null||memberID=="undefined")
            {
                finalAmount = Number(num)-Number(decAmount);
            }else{
                finalAmount = Number(memberNum)-Number(decAmount);
            }
        }else {
            finalAmount=Number(finalAmount)-Number(decAmount);
        }
    }

    document.getElementById("total-pay-active").value = finalAmount+"元";


}

function getActiveNum(activeValue) {
    if(activeValue == 0)
    {
        return 0;
    }else if(activeValue == 1)
    {
        return 8;
    }else if(activeValue == 2)
    {
        return 20;
    }else if(activeValue == 3)
    {
        return 50;
    }
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}

function submitOrder() {

    //防止连点，点击后submit不可点击
    //$("#submit-order").attr("disabled","true");
    $("#submit-order").hide();
    var memberMsisdn = document.getElementById("inputMember").value;
    var orderid = document.getElementById("total-pay-id").value;
    var activeType = document.getElementById("active").value;
    var orderTime = document.getElementById("transaction-time").value;
    var activeOff = getActiveOff(activeType);
    var merchantid = GetUrlParam("shopid");

    //2018-12-29 新增可变更订单，新增责任人及Note添加
    var authorType = document.getElementById("author-list").value;
    function getAuthorOff(authorType) {
        if(authorType == 0)
        {
            return 0;
        }else if(authorType == 1)
        {
            return "杨明晓";
        }else if(authorType == 2)
        {
            return "姚华";
        }else if(authorType == 3)
        {
            return "姚舜";
        }else if(authorType == 4)
        {
            return "彭颖欢";
        }
    }
    var note = document.getElementById("trans-note").value;
    var decAuthor = getAuthorOff(authorType);
    var decAmount = document.getElementById("dec-amount").value;

    if((decAmount!="")&&decAuthor==0)
    {
        alert("请选择额外扣款授权人！");
        return;
    }

    var submitOrderUrl = "./submitOrder"
    $.post(
        submitOrderUrl,
        {
            memberMsisdn:memberMsisdn,
            orderId:orderid,
            activeType:activeType,
            activeOff:activeOff,
            orderTime:orderTime,
            decAmount:decAmount,
            decAuthor:decAuthor,
            note:note,
            merchantid:merchantid
        },
        function (result) {

            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                //2019-05-21 新增防止连点导致提交两次；
                //提交成功则恢复提交按钮可编辑
                $("#submit-order").show();
                return;
            }
            if(result == 1)
            {

                alert("success");
                $(".mask_box").hide();
                //2019-05-21 新增防止连点导致提交两次；
                //提交成功则恢复提交按钮可编辑
                $("#submit-order").show();
                location.reload();
            }else if(result == 2)
            {
                alert("会员卡余额不足，请先充值！");
                //2019-05-21 新增防止连点导致提交两次；
                //提交成功则恢复提交按钮可编辑
                $("#submit-order").show();
            }


        }
    )

}

function getActiveOff(activeType) {
    if(activeType == 0)
    {
        return 0;
    }else if(activeType == 1)
    {
        return 8;
    }else if(activeType == 2)
    {
        return 20;
    }else if(activeType == 3)
    {
        return 50;
    }

}

function getcookie(name) {
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function showAll() {
    //alert(obj.value);

    var tableBody = document.getElementById("myTbody");
    var merchantid = GetUrlParam("shopid");

    var scanUrl = "./showAll"
    $.post(
        scanUrl,
        {
            merchantid:merchantid
        },

        function (result) {

            if(result == 1)
            {
                alert("货品没有库存了！")
            }else if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }else if(result!=null)
            {
                var jsObj = JSON.parse(result);
                while(tableBody.hasChildNodes()) //当elem下还存在子节点时 循环继续
                {
                    tableBody.removeChild(tableBody.firstChild);
                }
                var moneyAmount =0;
                var count =0;
                var IntergralCount =0;

                for(i=0;i<jsObj.length;i++)
                {
                    var newtr = document.createElement("tr");
                    var goodsid = document.createElement("th");
                    goodsid.innerHTML = jsObj[i].goodsId;

                    var goodsname = document.createElement("th");
                    goodsname.innerHTML = jsObj[i].goodsName;

                    var goodsPrice = document.createElement("th");
                    goodsPrice.innerHTML = jsObj[i].goodsPrice;

                    var GoodsNum = document.createElement("th");
                    GoodsNum.innerHTML = jsObj[i].goodsCount;

                    var GoodsInteral = document.createElement("th");
                    GoodsInteral.innerHTML = jsObj[i].goodsIntergral;

                    var Remarks = document.createElement("th");
                    var opreations = document.createElement("th");

                    //operation组件内包含增、减、删除
                    var op_add = document.createElement("i");
                    var op_add_son = document.createElement("a");

                    op_add.setAttribute("class","cls-op-add");
                    op_add_son.setAttribute("class","fa fa-plus-square-o");
                    op_add_son.setAttribute("id","op-add");
                    op_add_son.addEventListener('click',function (ev) {

                        var tr = ev.path[3];
                        var id = tr.cells[0].innerHTML;
                        var addUrl = "./billAdd";
                        $.post(
                            addUrl,
                            {goodsid:id},
                            function (result) {
                                if (isTimeOut(result)){
                                    alert("登录超时，请重新登录！");
                                    window.location = "./login";
                                    return;
                                }
                                showAll();
                            }
                        )
                        //alert("add");
                    });
                    op_add.appendChild(op_add_son);
                    //op_add.innerHTML = "   "

                    var op_dec = document.createElement("i");
                    var op_dec_son = document.createElement("a");

                    op_dec.setAttribute("class","cls-op-dec");
                    op_dec_son.setAttribute("class","fa fa-minus-square-o");
                    op_dec_son.setAttribute("id","op-dec");
                    op_dec_son.addEventListener('click',function (ev) {

                        var tr = ev.path[3];
                        var id = tr.cells[0].innerHTML;
                        var addUrl = "./billDec";
                        $.post(
                            addUrl,
                            {goodsid:id},
                            function (result) {
                                if (isTimeOut(result)){
                                    alert("登录超时，请重新登录！");
                                    window.location = "./login";
                                    return;
                                }
                                if(result == 1)
                                {
                                    alert ("数量为1不能减少，如不需要请删除");
                                }
                                else
                                {
                                    showAll();
                                }
                            }
                        );

                        //alert("dec");
                    });
                    op_dec.appendChild(op_dec_son);
                    //op_dec.innerHTML = "   ";

                    var op_del = document.createElement("i");
                    var op_del_son = document.createElement("a");
                    op_del.setAttribute("class","cls-op-del");
                    op_del_son.setAttribute("class","fa fa-trash");
                    op_del_son.setAttribute("id","op-del");
                    op_del_son.addEventListener('click',function (ev) {
                        var tr = ev.path[3];
                        var id = tr.cells[0].innerHTML;
                        var addUrl = "./billDel";
                        $.post(
                            addUrl,
                            {goodsid:id},
                            function (result) {
                                if (isTimeOut(result)){
                                    alert("登录超时，请重新登录！");
                                    window.location = "./login";
                                    return;
                                }
                                showAll();
                            }
                        );

                        //alert("del");
                    });
                    op_del.appendChild(op_del_son);
                    //op_del.innerHTML = "删除"

                    opreations.appendChild(op_add);
                    opreations.appendChild(op_dec);
                    opreations.appendChild(op_del);

                    moneyAmount=Number(moneyAmount) + Number(jsObj[i].goodsPrice)*Number(jsObj[i].goodsCount);
                    count= Number(count) + Number(jsObj[i].goodsCount);
                    IntergralCount=Number(IntergralCount) + Number(jsObj[i].goodsIntergral);


                    newtr.appendChild(goodsid)
                    newtr.appendChild(goodsname)
                    newtr.appendChild(goodsPrice)
                    newtr.appendChild(GoodsNum)
                    newtr.appendChild(GoodsInteral)
                    newtr.appendChild(Remarks)
                    newtr.appendChild(opreations)
                    tableBody.appendChild(newtr)
                }

                var moneyAmountCom = document.getElementById("totalMoney");
                var countCom = document.getElementById("totalQuantity");
                var IntergralCountCom = document.getElementById("totalIntegral");

                moneyAmountCom.innerHTML = moneyAmount;
                countCom.innerHTML = count;
                IntergralCountCom.innerHTML =IntergralCount;

                //alert(jsObj.goodsName);
            }

        }
    )


}

function listAllService() {
    var parent = document.getElementById("service-list");

    //创建大类
    //1.洗澡项目
    var wash_li = document.createElement("li");
    var wash_icon = document.createElement("li");
    wash_icon.setAttribute("id","wash-icon");
    wash_icon.setAttribute("class","fa fa-shower");
    wash_icon.setAttribute("aria-hidden","true");
    var wash_a = document.createElement("a");
    wash_a.setAttribute("href","#");
    wash_a.setAttribute("class","inactive active");
    wash_a.innerHTML="洗澡项目";
    var wash_ul = getServiceUl("was");
    wash_li.appendChild(wash_icon);
    wash_li.appendChild(wash_a);
    wash_li.appendChild(wash_ul);
    parent.appendChild(wash_li);

    //2.寄养项目
    var fos_li = document.createElement("li");
    fos_li.setAttribute("class","last");
    var fos_icon = document.createElement("li");
    fos_icon.setAttribute("id","fos-icon");
    fos_icon.setAttribute("class","fa fa-paw");
    fos_icon.setAttribute("aria-hidden","true");
    var fos_a = document.createElement("a");
    fos_a.setAttribute("href","#");
    fos_a.setAttribute("class","inactive active");
    fos_a.innerHTML="寄养项目";
    var fos_ul = getServiceUl("fos");

    fos_li.appendChild(fos_icon);
    fos_li.appendChild(fos_a);
    fos_li.appendChild(fos_ul);
    parent.appendChild(fos_li);

    //3.美容项目
    var cos_li = document.createElement("li");
    cos_li.setAttribute("class","last");

    var cos_icon = document.createElement("li");
    cos_icon.setAttribute("id","cos-icon");
    cos_icon.setAttribute("class","fa fa-scissors");
    cos_icon.setAttribute("aria-hidden","true");

    var cos_a = document.createElement("a");
    cos_a.setAttribute("href","#");
    cos_a.setAttribute("class","inactive active");
    cos_a.innerHTML="美容项目";
    var cos_ul = getServiceUl("cos");

    cos_li.appendChild(cos_icon);
    cos_li.appendChild(cos_a);
    cos_li.appendChild(cos_ul);
    parent.appendChild(cos_li);

    //4.疫苗项目
    var vac_li = document.createElement("li");
    vac_li.setAttribute("class","last");

    var vac_icon = document.createElement("li");
    vac_icon.setAttribute("id","vac-icon");
    vac_icon.setAttribute("class","fa fa-medkit");
    vac_icon.setAttribute("aria-hidden","true");
    var vac_a = document.createElement("a");
    vac_a.setAttribute("href","#");
    vac_a.setAttribute("class","inactive active");
    vac_a.innerHTML="疫苗项目";
    var vac_ul = getServiceUl("vac");

    vac_li.appendChild(vac_icon);
    vac_li.appendChild(vac_a);
    vac_li.appendChild(vac_ul);
    parent.appendChild(vac_li);

    //5.其他项目
    var mis_li = document.createElement("li");
    mis_li.setAttribute("class","last");

    var mis_icon = document.createElement("li");
    mis_icon.setAttribute("id","mis-icon");
    mis_icon.setAttribute("class","fa fa-paperclip");
    mis_icon.setAttribute("aria-hidden","true");

    var mis_a = document.createElement("a");
    mis_a.setAttribute("href","#");
    mis_a.setAttribute("class","inactive active");
    mis_a.innerHTML="附加项目";
    var mis_ul = getServiceUl("mis");

    mis_li.appendChild(mis_icon);
    mis_li.appendChild(mis_a);
    mis_li.appendChild(mis_ul);
    parent.appendChild(mis_li);

}

function getServiceUl(s_type) {

    var getUlUrl = "./getUl";
    var ul = document.createElement("ul");

    $.post(
        getUlUrl,
        {
            ulType:s_type
        },
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            if(result == 1)
            {

            }
            else
            {
                var jsObj = JSON.parse(result);

                for(i=0;i<jsObj.length;i++)
                {
                    //添加组件/若组件为寄养，在添加事件时要做区分
                    var headli = document.createElement("li");
                    var bodya = document.createElement("a");
                    bodya.setAttribute("class","service-cls");
                    bodya.setAttribute("href","#");
                    bodya.setAttribute("id",jsObj[i].serviceId);
                    bodya.innerHTML = jsObj[i].serviceName;
                    if(jsObj[i].family == "fos")
                    {
                        bodya.addEventListener("click",function (ev) {

                            $(".mask_box2").show();

                            document.getElementById("vos-service-id").value=ev.currentTarget.id;

                            //alert("点击寄养项目:"+ev.currentTarget.innerHTML+" ,id = "+ev.currentTarget.id);
                        })
                    }else{
                        bodya.addEventListener("click",function f(ev) {

                            var addServeiceUrl = "./addService";
                            $.post(
                                addServeiceUrl,
                                {
                                    serviceId:ev.currentTarget.id,
                                    serviceNum:1
                                },
                                function (result) {
                                    if (isTimeOut(result)){
                                        alert("登录超时，请重新登录！");
                                        window.location = "./login";
                                        return;
                                    }
                                    if(result ==1 )
                                    {
                                        alert("没有相关服务，请检查服务库！");
                                    }else if(result == 2)
                                    {
                                        alert("请不要重复添加服务！");
                                    }else {

                                        showAll();

                                    }
                                }
                            )
                            //alert("点击服务项目:"+ev.currentTarget.innerHTML+" ,id = "+ev.currentTarget.id);
                        })
                    }

                    headli.appendChild(bodya);
                    ul.appendChild(headli);

                }

            }



        }
    );
    return ul;
}

function caculVosDay() {


    var intime = document.getElementById("vos-in-shop-time").value;
    var outtime = document.getElementById("vos-out-shop-time").value;

    //若从in进入
    if(intime != null && intime != "")
    {
        if((outtime!= null&&outtime!= ""))
        {
            //时间计算
            var vosDays = calcuDate(intime,outtime);
            document.getElementById("total-vos-days").value=vosDays;

        }else
        {
            alert("请选择离店日期");
        }
    }else
    {
        if((outtime == null||outtime == ""))
        {

        }else{
            alert("入店日期不得为空");
        }

    }

}

function calcuDate(start,end) {
    var begindate = start;
    begindate = new Date(Date.parse(begindate.replace(/-/g, "/")));
    var myDate =end;
    myDate = new Date(Date.parse(myDate.replace(/-/g, "/")));
    var startDate = begindate.getTime();
    var endDate = myDate.getTime();
    var day = parseInt((endDate-startDate)/1000/3600/24);
    return day;
}

function submitVos() {

    var subVosUrl = "./addService"

    var serviceId = document.getElementById("vos-service-id").value;

    var serviceDay = document.getElementById("total-vos-days").value;

    $.post(
        subVosUrl,
        {
            serviceId:serviceId,
            serviceNum:serviceDay
        },
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            if(result ==1 )
            {
                alert("没有相关服务，请检查服务库！");
            }else if(result == 2)
            {
                alert("请不要重复添加服务！");
            }else {

                showAll();

            }
        }
    )



    $(".mask_box2").hide();


}

function memberAdd() {

    $(".mask_box3").show();



}

function submitMember() {

    $('#submit-register-member').hide();
    memberName = document.getElementById("member-name").value;
    msisdn = document.getElementById("member-msisdn").value;
    petName = document.getElementById("member-pet-name").value;
    firstCharge = document.getElementById("member-charge-amount").value;
    serviceOff = document.getElementById("member-service-off").value;
    goodsOff = document.getElementById("member-goods-off").value;

    addMemberUrl = "./register"

    if(memberName==""||memberName==null)
    {
        alert("会员姓名不能为空");
        $('#submit-register-member').show();
        return;
    }else if(msisdn == ""||msisdn==null)
    {
        alert("会员手机号码不能为空");
        $('#submit-register-member').show();
        return;
    }else if(petName == ""||petName==null)
    {
        alert("会员宠物名称不能为空");
        $('#submit-register-member').show();
        return;
    }else if(firstCharge == ""||firstCharge==null)
    {
        alert("会员首冲金额不能为空");
        $('#submit-register-member').show();
        return;
    }

    $.post(
        addMemberUrl,
        {
            memberName:memberName,
            memberMsisdn:msisdn,
            petName:petName,
            firstCharge:firstCharge,
            serviceOff:serviceOff,
            goodsOff:goodsOff
        },
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                $('#submit-register-member').show();
                return;
            }
            if(result == 1)
            {
                alert("会员已存在，请勿重复注册！");
                $('#submit-register-member').show();
            }
            else if(result == 2)
            {
                alert ("会员注册成功！");
                $('#submit-register-member').show();
                $(".mask_box3").hide();

            }else if(result == 3)
            {
                alert("Insert Sql Statmemt ERROR ;");
                $('#submit-register-member').show();
            }
        }
    )



}

function memberConCharge() {

    $(".mask_box4").show();

}

function submitConCharge() {

    $('#submit-charge').hide();
    msisdn = document.getElementById("member-con-msisdn").value;
    chargeAmount = document.getElementById("member-con-charge").value;
    serviceOff = document.getElementById("member-con-service-off").value;
    goodsOff = document.getElementById("member-con-goods-off").value;

    chargeUrl = "./memberCharge"

    if(msisdn == ""||msisdn==null)
    {
        alert("会员手机号码不能为空");
        $('#submit-charge').hide();
        return;
    }else if(chargeAmount == ""||chargeAmount==null)
    {
        alert("会员首冲金额不能为空");
        $('#submit-charge').hide();
        return;
    }

    $.post(
        chargeUrl,
        {
            memberMsisdn:msisdn,
            chargeAmount:chargeAmount,
            serviceOff:serviceOff,
            goodsOff:goodsOff
        },
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                $('#submit-charge').hide();
                window.location = "./login";
                return;
            }
            if(result == 1)
            {
                alert("会员不存在，请检查后再试！");
                $('#submit-charge').hide();
            }
            else if(result == 2)
            {
                alert ("会员充值成功！");
                $('#submit-charge').hide();
                $(".mask_box4").hide();
            }else if(result == 3)
            {
                alert("Insert Sql Statmemt ERROR ;");
                $('#submit-charge').hide();
            }
        }
    )



}

function checkDecNumber() {

    var inputtxt = document.getElementById("dec-amount").value;

    if(!isNumber(inputtxt))
    {
        alert("请输入纯数字！不可携带字符！");
        document.getElementById("dec-amount").value = "";
        return;
    }

    activeChange();



}

function isNumber(val){

    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)){
        return true;
    }else{
        return false;
    }

}

function showStorePage() {

    $("#silver-console").empty();

    $("#silver-console").load('./static/tmp/html/StoreConsole.html');

}

function showMemberPage(){

    $("#silver-console").empty();

    $("#silver-console").load('./static/tmp/html/memberConsole.html');
}

function showSilver() {
    window.location.reload();
}

function InitalPages() {

    var shopId = getcookie("shopidCookie");
    var pagesInitalUrl = "./initalPages"
    $.post(
        pagesInitalUrl,
        {shopid:shopId},
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            var jsObj = JSON.parse(result);
            document.getElementById("merchant-base-name").innerText = jsObj.merchantName;
        }
    )
}

function GetUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");

    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;

        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");

            if (arr != null && arr[0] == paraName) {
                return arr[1];
            }
        }
        return "";
    }
    else {
        return "";
    }
}