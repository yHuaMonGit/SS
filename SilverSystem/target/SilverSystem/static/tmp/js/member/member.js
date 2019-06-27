function showAllMember() {

    var shopid = getShopId();
    var showAllMemberUrl = "./showAllMember";
    var tableBody = document.getElementById("myTbody");
    $.post(
        showAllMemberUrl,
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
                alert("没有查询到会员信息!");
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
                    goodsid.innerHTML = jsObj[i].memberName;

                    var goodsname = document.createElement("th");
                    goodsname.innerHTML = jsObj[i].msisdn;

                    var goodsPrice = document.createElement("th");
                    goodsPrice.innerHTML = jsObj[i].balance;

                    var GoodsNum = document.createElement("th");
                    GoodsNum.innerHTML = jsObj[i].petName;

                    var GoodsInteral = document.createElement("th");
                    GoodsInteral.innerHTML = jsObj[i].integral;


                    var opreations = document.createElement("th");

                    //operation组件仅包含编辑分类
                    var op_add = document.createElement("i");
                    var op_add_son = document.createElement("a");

                    op_add.setAttribute("class","cls-op-edtCls");
                    op_add_son.setAttribute("class","fa fa-pencil-square");
                    op_add_son.setAttribute("id","op-edtMember");
                    op_add_son.addEventListener('click',function (ev) {
                        var tr = ev.path[3];
                        var id = tr.cells[0].innerHTML;
                        //alert(id);

                        // $(".mask_box_member_detail_info").attr("goodsid",id);
                        $(".mask_box_member_detail_info").show();

                    });
                    op_add_son.innerHTML = " 详细信息"
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


                document.getElementById("member-total-number").innerText=jsObj.length+"位"

                //alert(jsObj.goodsName);
            }

            alert("ishere");

        }
    )



}


function comfirmMemberDetail() {
    $(".mask_box_member_detail_info").hide();
}

function cancelConfirmBtn(obj) {
    $(".mask_box_member_detail_info").hide();
}

/***
 * 公共方法 用于检测被屏蔽的input是否需要勾选复选框
 * @param obj
 */
function checkEditAble(obj) {

    if (true == obj.readOnly){
        alert("请先勾选对应复选框再进行编辑!");
    }

}

