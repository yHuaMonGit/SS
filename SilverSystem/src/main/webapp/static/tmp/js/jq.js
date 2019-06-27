/**
 *
 * @authors Your Name (you@example.org)
 * @date    2016-08-12 18:10:19
 * @version $Id$
 */


// $(function () {
// 	$('.Floor_comlu .slideTd input').click(function(){
// 		$(this).next('searchbar').show(300);
// 	});
//
//
// 	$('.invoice h3').click(function(){
// 		$('.innerbox').stop().fadeToggle('fast');
// 		$(this).find('input').stop().click();
//
// 	});
//
// 	$('.opends').click(function(){
// 		$('.invoice').stop().fadeIn('fast');
// 	});
// 	$('.clogds').click(function(){
// 		$('.invoice').stop().fadeOut('fast');
// 	});
// });
//
// $(function () {
// 	$(".SEbtn").click(function(){
// 		$(this).parents('tr').next(".hiddenDIv").fadeIn(400);
// 	});
//
// 	$(".deletNJ").click(function(){
// 		$(this).parents(".hiddenDIv").fadeOut(400);
// 	});
//
// });
// $(function(){
// 	$('.innerbtn').click(function(){
// 		$(this).next('.pop_box').slideDown('400');
// 	});
// 	$('.closepop').click(function(){
// 		$('.pop_box').slideUp('400');
// 	});
//
// 	$('.tbody1').on("click",".alonTr .innerbtn",function(){
// 		$(this).next('.pop_box').slideDown('400');
// 	})
// 	$('.tbody1').on("click",".alonTr .closepop",function(){
// 		$('.pop_box').slideUp('400');
// 	})
//
//
// });
//
// $(function () {
// // 新增表单
// 	var show_count = 20;
// 	var count = 1;
// 	$(".AddTr").click(function () {
// 		var length = $(".tabInfo .tbody1>tr").length;
// 		//alert(length);
// 		if (length < show_count)
// 		{
// 			$(".model1 tbody .alonTr").clone().appendTo(".tabInfo .tbody1");
// 		}
// 	});
// });
// function deltr(opp) {
// 	var length = $(".tabInfo .tbody1>tr").length;
// 	//alert(length);
// 	if (length <= 1) {
// 		alert("至少保留一行");
// 	} else {
// 		$(opp).parent().parent().remove();//移除当前行
//
// 	}
// }
// // ----
//
// $(function () {
// // 新增内件
// 	var show_count2 = 20;
// 	var count2 = 1;
// 	$(".addtr2").click(function () {
// 		var length = $(this).parent('.btn_a1').prev('.neijian').children('.tbody2 tr').length;
// 		//alert(length);
// 		if (length < show_count2)
// 		{
// 			$(".model2 tbody tr").clone().appendTo($(this).parent('.btn_a1').prev('.neijian').children('.tbody2'));
// 		}
// 	});
// });
// function deltr2(opp) {
// 	var length = $(this).parent('.btn_a1').prev('.neijian').children('.tbody2 tr').length;
// 	//alert(length);
// 	if (length <= 1) {
// 		alert("至少保留一行");
// 	} else {
// 		$(opp).parent().parent().remove();//移除当前行
//
// 	}
// }
// // ----
// $(function () {
// // 动态的新增内件
// 	var show_count3 = 20;
// 	var count3 = 1;
// 	$(".tbody1").on("click",".dtadd",function () {
// 		var length = $(".neijian .tbody2 tr").length;
// 		//alert(length);
// 		if (length < show_count3)
// 		{
// 			$('.model2 tbody tr').clone().appendTo($(this).parent('.btn_a1').prev('.neijian').children('.tbody2'));
// 		}
// 	});
// });
// function deltr3(opp) {
// 	var length = $('.neijian .tbody2 tr').length;
// 	//alert(length);
// 	if (length <= 1) {
// 		alert("至少保留一行");
// 	} else {
// 		$(opp).parent().parent().remove();//移除当前行
//
// 	}
// }

function addGoods()
{


    var loadUrl = "./batchStoreAdd";

    var length = $(".tabInfo .tbody1>tr").length;

    var test = $(".tabInfo .tbody1>tr");

    var goodsInfoArr = new Array();

    var shopid = getShopId()


    for(i=0;i<length;i++)
    {
        cells = test[i].cells;
        goodsid = cells[0].firstChild.value;
        if (goodsid == null || goodsid == ""||goodsid == "undefined"){
            alert("商品ID不能为空！");
            return;
        }
        goodsname = cells[1].firstChild.value;
        if (goodsname == null || goodsname == ""||goodsname == "undefined"){
            alert("商品名称不能为空！");
            return;
        }
        goodsstander = cells[2].firstChild.value;
        if (goodsstander == null || goodsstander == ""||goodsstander == "undefined"){
            alert("商品规格不能为空！");
            return;
        }
        goodsprice = cells[3].firstChild.value;
        if (goodsprice == null || goodsprice == ""||goodsprice == "undefined"){
            alert("商品价格不能为空！");
            return;
        }
        if (!isNumber(goodsprice)){
            alert("商品价格请输入数字！");
        }
        goodsnum = cells[4].firstChild.value;
        if (goodsnum == null || goodsnum == ""||goodsnum == "undefined"){
            alert("商品数量不能为空！");
            return;
        }
        if (!isNumber(goodsnum)){
            alert("商品数量请输入数字！");
        }
        goodsclassify = cells[5].firstChild.value;
        goodscls = cells[6].firstChild.nextSibling.value;
        if (goodscls == null || goodscls == "0"||goodscls == "undefined"){
            alert("请选择商品分类！");
            return;
        }
        goodsline = goodsid+","+goodsname+","
            +goodsstander+","+goodsprice+","
            +goodsnum+","+goodsclassify+","+goodscls;
        goodsInfoArr.push(goodsline);
    }

    $.post(
        loadUrl,
        {
            shopid:shopid,
            str:JSON.stringify(goodsInfoArr)
        },
        function (result) {
            if(result == 1)
            {
                alert("商品批量添加成功");
            }else{

                var jsObj = JSON.parse(result);
                var outlist = "";
                for(i=0;i<jsObj.length;i++)
                {
                    var goodsname = jsObj[i].goodsName;

                    if (i != jsObj.len()){
                        outlist+=goodsname+",";
                    } else{
                        outlist+=goodsname;
                    }
                }
                alert("商品：【"+outlist+"】已存在，不需要添加，其他商品已经添加成功！ ");

            }
        }
    )


    //alert("ishere");
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

function getcookie(name) {
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function getShopId() {
    return getcookie("shopidCookie");
}