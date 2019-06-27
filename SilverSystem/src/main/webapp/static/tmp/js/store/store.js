function searchGoods() {

}

function checkStoreByName() {

    var tableBody = document.getElementById("myTbody");
    var shopid = getShopId();
    var goodsName = document.getElementById("store-search-by-name").value;
    //goodsName = encodeURI(encodeURI(goodsName));
    var findStoreByNameUrl = "./storeFindByName"

    if (goodsName == "" || goodsName == null || goodsName == "undefind"){
        alert("请输入要查询的商品名称！");
        return;
    }
    else {

        $.post(
            findStoreByNameUrl,
            {
                shopid:shopid,
                goodsName:goodsName
            },
            function (result) {
                if (isTimeOut(result)){
                    alert("登录超时，请重新登录！");
                    window.location = "./login";
                    return;
                }
                if(result == 3){
                    alert("该商品不存在!");
                }else{
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
                        GoodsNum.innerHTML = jsObj[i].goodsStore;

                        var GoodsInteral = document.createElement("th");
                        GoodsInteral.innerHTML = jsObj[i].goodsStander;


                        var opreations = document.createElement("th");

                        //operation组件仅包含编辑分类
                        var op_add = document.createElement("i");
                        var op_add_son = document.createElement("a");

                        op_add.setAttribute("class","cls-op-edtCls");
                        op_add_son.setAttribute("class","fa fa-pencil-square");
                        op_add_son.setAttribute("id","op-edtCls");
                        op_add_son.addEventListener('click',function (ev) {
                            var tr = ev.path[3];
                            var id = tr.cells[0].innerHTML;
                            //alert(id);

                            $(".mask_box_store_edt_cls").attr("goodsid",id);
                            $(".mask_box_store_edt_cls").show();

                        });
                        op_add_son.innerHTML = " 编辑分类"
                        op_add.appendChild(op_add_son);
                        opreations.appendChild(op_add);
                        moneyAmount=Number(moneyAmount) + Number(jsObj[i].goodsPrice)*Number(jsObj[i].goodsCount);
                        count= Number(count) + Number(jsObj[i].goodsCount);
                        IntergralCount=Number(IntergralCount) + Number(jsObj[i].goodsIntergral);


                        newtr.appendChild(goodsid)
                        newtr.appendChild(goodsname)
                        newtr.appendChild(goodsPrice)
                        newtr.appendChild(GoodsNum)
                        newtr.appendChild(GoodsInteral)

                        newtr.appendChild(opreations)
                        tableBody.appendChild(newtr)
                    }


                    document.getElementById("store-total-goods").innerText=jsObj.length+"类"

                    //alert(jsObj.goodsName);
                }

            }
        )

    }

}

function showAllGoods() {

    var tableBody = document.getElementById("myTbody");
    showAllGoodsUrl = "./showAllGoods";

    var shopid = getShopId();

    $.post(
        showAllGoodsUrl,
        {
            shopid:shopid
        },
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            if(result == 1){
                alert("没有查询到库存信息!");
            }
            else{
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
                    GoodsNum.innerHTML = jsObj[i].goodsStore;

                    var GoodsInteral = document.createElement("th");
                    GoodsInteral.innerHTML = jsObj[i].goodsStander;


                    var opreations = document.createElement("th");

                    //operation组件仅包含编辑分类
                    var op_add = document.createElement("i");
                    var op_add_son = document.createElement("a");

                    op_add.setAttribute("class","cls-op-edtCls");
                    op_add_son.setAttribute("class","fa fa-pencil-square");
                    op_add_son.setAttribute("id","op-edtCls");
                    op_add_son.addEventListener('click',function (ev) {
                        var tr = ev.path[3];
                        var id = tr.cells[0].innerHTML;
                        //alert(id);

                        $(".mask_box_store_edt_cls").attr("goodsid",id);
                        $(".mask_box_store_edt_cls").show();

                    });
                    op_add_son.innerHTML = " 编辑分类"
                    op_add.appendChild(op_add_son);
                    opreations.appendChild(op_add);
                    moneyAmount=Number(moneyAmount) + Number(jsObj[i].goodsPrice)*Number(jsObj[i].goodsCount);
                    count= Number(count) + Number(jsObj[i].goodsCount);
                    IntergralCount=Number(IntergralCount) + Number(jsObj[i].goodsIntergral);


                    newtr.appendChild(goodsid)
                    newtr.appendChild(goodsname)
                    newtr.appendChild(goodsPrice)
                    newtr.appendChild(GoodsNum)
                    newtr.appendChild(GoodsInteral)

                    newtr.appendChild(opreations)
                    tableBody.appendChild(newtr)
                }


                document.getElementById("store-total-goods").innerText=jsObj.length+"类"

                //alert(jsObj.goodsName);
            }

        }
    )

}

function classifyGoods(obj) {

    var tableBody = document.getElementById("myTbody");
    var classifyType = obj.type;
    var shopid = getShopId();

    var classifyTypeOffGoodsUrl = "./classifyGoodsByType"

    $.post(
        classifyTypeOffGoodsUrl,
        {
            shopid:shopid,
            clsType:classifyType
        },function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            if(result == 2){
                alert("没有查询到对应库存信息!");
            }else{
                var jsObj = JSON.parse(result);
                //清空列表
                while(tableBody.hasChildNodes()) //当elem下还存在子节点时 循环继续
                {
                    tableBody.removeChild(tableBody.firstChild);
                }
                //添加数据
                var moneyAmount =0;
                var count =0;
                var IntergralCount =0;

                for(i=0;i<jsObj.length;i++) {
                    var newtr = document.createElement("tr");
                    var goodsid = document.createElement("th");
                    goodsid.innerHTML = jsObj[i].goodsId;

                    var goodsname = document.createElement("th");
                    goodsname.innerHTML = jsObj[i].goodsName;

                    var goodsPrice = document.createElement("th");
                    goodsPrice.innerHTML = jsObj[i].goodsPrice;

                    var GoodsNum = document.createElement("th");
                    GoodsNum.innerHTML = jsObj[i].goodsStore;

                    var GoodsInteral = document.createElement("th");
                    GoodsInteral.innerHTML = jsObj[i].goodsStander;


                    var opreations = document.createElement("th");

                    //operation组件仅包含编辑分类
                    var op_add = document.createElement("i");
                    var op_add_son = document.createElement("a");

                    op_add.setAttribute("class", "cls-op-edtCls");
                    op_add_son.setAttribute("class", "fa fa-pencil-square");
                    op_add_son.setAttribute("id", "op-edtCls");
                    op_add_son.addEventListener('click', function (ev) {
                        var tr = ev.path[3];
                        var id = tr.cells[0].innerHTML;
                        //alert(id);

                        $(".mask_box_store_edt_cls").attr("goodsid", id);
                        $(".mask_box_store_edt_cls").show();

                    });
                    op_add_son.innerHTML = " 编辑分类"
                    op_add.appendChild(op_add_son);
                    opreations.appendChild(op_add);
                    moneyAmount = Number(moneyAmount) + Number(jsObj[i].goodsPrice) * Number(jsObj[i].goodsCount);
                    count = Number(count) + Number(jsObj[i].goodsCount);
                    IntergralCount = Number(IntergralCount) + Number(jsObj[i].goodsIntergral);


                    newtr.appendChild(goodsid)
                    newtr.appendChild(goodsname)
                    newtr.appendChild(goodsPrice)
                    newtr.appendChild(GoodsNum)
                    newtr.appendChild(GoodsInteral)

                    newtr.appendChild(opreations)
                    tableBody.appendChild(newtr)
                }
                document.getElementById("store-total-goods").innerText=jsObj.length+"类"
            }
        }

    )

    alert(classifyType);

}

function modifyClsGoods(obj) {

    var classifyType = obj.type;
    $(".mask_box_store_edt_cls").attr("clsType",classifyType);
    document.getElementById("store-cls-box-has-select").innerText = getClsNameByType(classifyType);

}

function getClsNameByType(obj) {

    if (obj == "dfood") {
        return "犬用主粮"
    }else if (obj == "cfood"){
        return "猫用主粮"
    } else if (obj == "can"){
        return "罐头类"
    }else if (obj == "snacks"){
        return "零食类"
    }else if (obj == "ec"){
        return "环境清洁类"
    }else if (obj == "csm"){
        return "消耗品"
    }else if (obj == "toy"){
        return "玩具类"
    }else if (obj == "med"){
        return "药品类"
    }else if (obj == "nut"){
        return "营养品"
    }else {
        return "非法"
    }


}

function cancelSubmitBtn(obj) {

    var ui = document.getElementsByClassName(obj);
    ui[0].style.display="none";

}

function submitModifyCls() {
    var classifyType ;
    $(".mask_box_store_edt_cls").attr("clsType", function(i,origValue){
        classifyType =  origValue;
    });
    var id;
    $(".mask_box_store_edt_cls").attr("goodsid", function(i,origValue){
        id =  origValue;
    });
    var modifyClsUrl = "./modifyCls"
    var shopid = getShopId();
    $.post(
        modifyClsUrl,
        {
            shopid:shopid,
            goodsid:id,
            clsType:classifyType
        },
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            if (result==1){
                alert("数据更新失败！请联系服务商；");
                $(".mask_box_store_edt_cls").hide();
            } else if (result == 0){
                alert("商品类型设置成功！");
                $(".mask_box_store_edt_cls").hide();
            }else {
                alert("未知系统异常！请联系服务商；");
                $(".mask_box_store_edt_cls").hide();
            }
        }

    );


}

function getGoodsInfoByID() {

    var goodsid = document.getElementById("store-search-by-id").value;
    var shopid = getShopId();
    var searchGoodsByIdUrl = "./searchGoodsById";
    if (goodsid == null || goodsid == "" || goodsid == "undefind") {

        alert("请输入商品编号!");
    }

    $.post(
        searchGoodsByIdUrl,
        {
            shopid:shopid,
            goodsid:goodsid
        },
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            if (result == 4)
            {
                alert("没有查询到该商品，请检查编号！");
            } else {

                var jsObj = JSON.parse(result);
                document.getElementById("store-goods-code").innerText = jsObj.goodsId;
                document.getElementById("store-goods-name").innerText = jsObj.goodsName;
                document.getElementById("store-goods-inventory").innerText = jsObj.goodsStore;
                document.getElementById("store-goods-brand").innerText = jsObj.goodsClassify;
                document.getElementById("store-goods-price").innerText = jsObj.goodsPrice;
                document.getElementById("store-goods-stander").innerText = jsObj.goodsStander;

            }


        }
    );


}

function submitAddStoreSingle() {

    var shopid = getShopId();
    var addSingleStoreUrl = "./addSingleStore";

    //获取商品信息
    var goodsid = document.getElementById("store-add-single-id").value;
    if (goodsid == null || goodsid == "" || goodsid == "undefind") {
        alert("商品编码不能为空！");
        return;
    }

    var goodsname = document.getElementById("store-add-single-name").value;
    if (goodsname == null || goodsname == "" || goodsname == "undefind") {
        alert("商品名称不能为空！");
        return;
    }

    var goodscount = document.getElementById("store-add-single-count").value;
    if (goodscount == null || goodscount == "" || goodscount == "undefind") {
        alert("商品数量不能为空！");
        return;
    }

    var goodsprice = document.getElementById("store-add-single-price").value;
    if (goodsprice == null || goodsprice == "" || goodsprice == "undefind") {
        alert("商品价格不能为空！");
        return;
    }

    var goodsbrand = document.getElementById("store-add-single-brand").value;

    var goodsstander = document.getElementById("store-add-single-stander").value;
    if (goodsstander == null || goodsstander == "" || goodsstander == "undefind") {
        alert("商品规格不能为空！");
        return;
    }

    $.post(
        addSingleStoreUrl,
        {
            shopid:shopid,
            goodsid:goodsid,
            goodsname:goodsname,
            goodscount:goodscount,
            goodsprice:goodsprice,
            goodsbrand:goodsbrand,
            goodsstander:goodsstander
        },
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            if (result == 1){
                alert("添加商品失败，请联系管理员！");
            } else if (result == 2) {
                alert("商品已经存在，请不要重复添加！");
            }else {
                alert("商品添加成功!");
                $(".mask_box_store_add").hide();
            }
        }
    )

}

function cancelAddStore() {
    $(".mask_box_store_add").hide();
}


function newGoodsAddSingle() {

    $(".mask_box_store_add").show();

}

function oldGoodsAppend() {
//mask_box_store_continue_increase
    $(".mask_box_store_continue_increase").show();
}

function submitStoreConInc() {
    var shopid = getShopId();
    var conSingleStoreUrl = "./conSingleStore";

    //获取商品信息
    var goodsid = document.getElementById("store-con-code").value;
    if (goodsid == null || goodsid == "" || goodsid == "undefind") {
        alert("商品编码不能为空！");
        return;
    }

    var goodscount = document.getElementById("store-con-number").value;
    if (goodscount == null || goodscount == "" || goodscount == "undefind") {
        alert("商品数量不能为空！");
        return;
    }


    $.post(
        conSingleStoreUrl,
        {
            shopid:shopid,
            goodsid:goodsid,
            goodscount:goodscount
        },
        function (result) {
            if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
                window.location = "./login";
                return;
            }
            if (result == 1){
                alert("商品续货失败，请联系管理员！");
            } else if (result == 2){
                alert("没有查询到商品，请检查编码是否正确！");
            }else if (isTimeOut(result)){
                alert("登录超时，请重新登录！");
            } else {
                alert("商品续货成功!");
                $(".mask_box_store_continue_increase").hide();
            }
        }
    )
}

function isTimeOut(result) {

    if (result.length > 10){
        if (isJSON(result)){
            return false;
        } else{
            return true;
        }
    }else{
        return false;
    }

}

function isJSON(str) {
    if (typeof str == 'string') {
        try {
            var obj=JSON.parse(str);
            if(typeof obj == 'object' && obj ){
                return true;
            }else{
                return false;
            }
        } catch(e) {
            return false;
        }
    }

}

function cancelConInc() {
    $(".mask_box_store_continue_increase").hide();
}

function getShopId() {
    return getcookie("shopidCookie");
}

function batchGoodsAdd() {

    //新页面跳转
    window.open("./addGoods");

}