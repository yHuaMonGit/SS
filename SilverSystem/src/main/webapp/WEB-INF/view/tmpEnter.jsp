<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2018/12/4
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0" />
    <meta name="author" content="siweiyong 2602812659@qq.com"/>
    <title>收银台</title>
    <link rel="stylesheet" href="./static/tmp/scripts/miniui/themes/default/miniui.css">
    <link rel="stylesheet" href="./static/tmp/css/common.css">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./static/tmp/layui/css/layui.css" />
    <link rel="stylesheet" href="./static/tmp/css/datepicker.css">
    <link rel="stylesheet" type="text/css" media="all" href="./static/tmp/css/tyle.css">


    <script src="./static/tmp/js/tmpEnter.js"></script>
    <script src="./static/tmp/js/store/store.js"></script>
    <script src="./static/tmp/js/member/member.js"></script>
    <script src="./static/tmp/js/member/memberRegister.js"></script>
    <script src="./static/tmp/js/member/memberPetRegister.js"></script>
    <script src="./static/tmp/js/member/cityTemplate.js"></script>
    <script src="./static/js/jquery-2.2.1.min.js"></script>
</head>
<body style="background: #F6F7F9;">
<div class="main">
    <embed src="./static/music/appointmentTips.mp3" autostart=true hidden="true"  loop=true>
    <div class="layui-row">
        <div class="main_left layui-col-xs12 layui-col-md3">
            <div class="layui-row">
                <div class="top" >
                    <i class="layui-icon layui-icon-app" id = "merchant-base-name"></i>
                </div>
                <div class="top">
                    <li>
                        <i class="fa fa-desktop" aria-hidden="true"></i>
                        <a href="#" class="inactive" onclick="showSilver()">收银台</a>
                        <ul style="display: none" id="silver-commander"></ul>
                    </li>


                    <li>
                        <i class="fa fa-fax" aria-hidden="true"></i>
                        <a href="#" class="inactive">服务预约</a>
                        <ul style="display: none" id="service-appointment">
                            <script>
                                //listAppointmentService();
                            </script>
                        </ul>
                    </li>

                    <li>
                        <i class="fa fa-user" aria-hidden="true"></i>
                        <a href="#" class="inactive" onclick="showMemberPage()">会员管理</a>
                        <ul style="display: none" id="service-member-center">
                            <script>
                                //listAppointmentService();
                            </script>
                        </ul>
                    </li>

                    <li>
                        <i class="fa fa-archive" aria-hidden="true"></i>
                        <a href="#" class="inactive" onclick="showStorePage()">库存管理</a>
                        <ul style="display: none" id="service-store-center">
                            <script>
                                //listAppointmentService();
                            </script>
                        </ul>
                    </li>

                    <li>
                        <i class="fa fa-list-alt" aria-hidden="true"></i>
                        <a href="#" class="inactive">订单管理</a>
                        <ul style="display: none" id="service-order-center">
                            <script>
                                //listAppointmentService();
                            </script>
                        </ul>
                    </li>

                    <li>
                        <i class="fa fa-bar-chart" aria-hidden="true"></i>
                        <a href="#" class="inactive">统计报表</a>
                        <ul style="display: none" id="service-statistical-form">
                            <script>
                                //listAppointmentService();
                            </script>
                        </ul>
                    </li>

                </div>


            </div>
        </div>
        <div class="main_right layui-col-xs12 layui-col-md9" id="silver-console">
            <div class="layui-row">
                <div class="top">
                    <label>商品编码：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="title" required lay-verify="required" placeholder="扫码或手动输入商品编码" id="goods-input-text" autocomplete="off" class="layui-input" onchange="scanner(this)">
                    </div>
                    <div class="layui-input-inline">
                        <button class="layui-btn layui-btn-primary" style="vertical-align: middle;margin-bottom: 2px;">添加</button>
                        <button class="layui-btn layui-btn-primary" style="vertical-align: middle;margin-bottom: 2px;" onclick="showAll()">显示所有</button>
                    </div>
                </div>
                <div class="goods_list">
                    <table class="layui-table">
                        <thead>
                        <tr>
                            <th lay-data="{field:'id'}">商品编码</th>
                            <th lay-data="{field:'name'}">商品名称</th>
                            <th lay-data="{field:'price', edit: 'text'}">零售价（元）</th>
                            <th lay-data="{field:'number', edit: 'text'}">数量</th>
                            <th lay-data="{field:'integral'}">积分</th>
                            <!--<th lay-data="{field:'staff', edit: 'text'}">提成员工</th>-->
                            <th lay-data="{field:'remarks', edit: 'text'}">备注</th>
                            <th lay-data="{field:'operation'}">操作</th>
                        </tr>

                        </thead>
                        <tbody id="myTbody">

                        </tbody>
                    </table>
                </div>
                <div class="total ft16">
                    合计：【<span> 消费总金额：<i class="totalMoney" id="totalMoney">0.00</i> </span>】【<span> 消费总数量：<i class="totalQuantity" id="totalQuantity">0</i> </span>】【 <span> 总积分：<i class="totalIntegral" id="totalIntegral">0</i> </span>】
                </div>
                <table class="reading layui-table" lay-even="">
                    <tbody>
                    <tr>
                        <td>会员查找</td>
                        <td colspan="3">
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="inputMember" placeholder="扫描或者手动输入会员卡号" />
                            </div>
                            <button class="card_reading layui-btn" onclick="getMember()">读卡</button>
                            <a href="#" class="registration layui-btn layui-btn-primary" onclick="memberAdd()">会员登记</a>
                            <a href="#" class="registration layui-btn layui-btn-primary" onclick="memberConCharge()">会员续充</a>
                        </td>
                    </tr>
                    <tr>
                        <td>会员卡号：</td>
                        <td id="member-mbid"></td>
                        <td>会员姓名：</td>
                        <td id="member-mbname"></td>
                    </tr>
                    <tr>
                        <td>会员级别：</td>
                        <td id="member-mblv"></td>
                        <td>会员验证：</td>
                        <td id="member-identity"></td>
                    </tr>
                    <tr>
                        <td>可用积分：</td>
                        <td id="member-mbintergral"></td>
                        <td>可用储值：</td>
                        <td id="member-mbbalance"></td>
                    </tr>
                    </tbody>
                </table>
                <div class="towbtn">
                    <input type="submit" id="settlement" onclick="getTotal()" value="结算">
                    <input type="reset" value="清空" />
                </div>
            </div >
        </div>
    </div>
</div>
<div class="mask_box" >
    <div class="mask"></div>
    <div class="box">
        <div class="box_top" >结算</div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">订单号：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="total-pay-id"  value="0.0" readonly style="border: none;" /></div>
        </div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">应付金额：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="total-pay-amount"  value="0.0" readonly style="border: none;" /></div>
        </div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">会员价格：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="total-pay-off"  value="0.0" readonly style="border: none;" /></div>

        </div>
        <div class="box_block box_block2">
            <div class="block_left"><input type="checkbox" name="" />优惠券：</div>
            <div class="block_right">
                <select class="layui-select" name="" id="active" onchange="activeChange()">
                    <option value="0">请选择</option>
                    <option value="1">满50减8</option>
                    <option value="2">满100减20</option>
                    <option value="3">满200减50</option>
                </select>
            </div>

        </div>
        <div class="box_block box_block3">
            <div class="block_left"><input type="radio" name="payment" /><label for="use_coupon">减免支付：</label></div>
            <div class="block_right">
                <input type="text" id="dec-amount" class="layui-input" placeholder="纯数字.." onchange="checkDecNumber()" />
            </div>
            <br style="clear: both;">
            <div class="block_left"><input type="radio" name="payment" /><label for="use_coupon">授权人：</label></div>
            <div class="block_right">
                <select class="layui-select" name="" id="author-list" onchange="activeChange()">
                    <option value="0">请选择</option>
                    <option value="1">杨明晓</option>
                    <option value="2">姚华</option>
                    <option value="3">姚舜</option>
                    <option value="4">彭颖欢</option>
                </select>
            </div>
            <br style="clear: both;">
            <div class="block_left">还需支付：</div>
            <div class="block_right"><input type="text" class="layui-input" id="total-pay-active" value="0.0" readonly style="border: none;" /></div>
        </div>
        <div class="remark_mess">
            <div class="block_left">
                备注：
            </div>
            <div class="block_right">
                <textarea class="layui-textarea" id="trans-note" name="" rows="" cols="" maxlength="200" placeholder="最多输入200字符"></textarea>
            </div>
            <br style="clear: both;">
            <div class="block_left">
                交易时间：
            </div>
            <div class="block_right">
                <input type="text" id="transaction-time" class="layui-input" readonly style="border: none;" />
            </div>
        </div>
        <div class="bot">
            <input type="submit" id="submit-order" value="提交" onclick="submitOrder()">
            <input type="text" value="取消">
        </div>
    </div>
</div>


<div class="mask_box2" >
    <div class="mask"></div>
    <div class="box">
        <div class="box_top" >请选择寄养时间区间</div>

        <div class="box_block box_block1">
            <div class="block_left"><label for="">寄养ID：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="vos-service-id"  value="0.0" readonly style="border: none;" /></div>
        </div>

        <div class="box_block box_block1">
            <div class="block_left"><label for="">起始时间：</label></div>
            <div class="block_right">
                <div class="mt40">
                    <div class="c-datepicker-date-editor c-datepicker-single-editor J-datepicker-day mt10">
                        <i class="c-datepicker-range__icon kxiconfont icon-clock"></i>
                        <input type="text" autocomplete="off" name="" placeholder="选择入店日期" class="c-datepicker-data-input only-date" value="" id="vos-in-shop-time" >
                    </div>
                </div>
            </div>
        </div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">离店时间：</label></div>
            <div class="block_right">
                <div class="mt40">
                    <div class="c-datepicker-date-editor c-datepicker-single-editor J-datepicker-day mt10">
                        <i class="c-datepicker-range__icon kxiconfont icon-clock"></i>
                        <input type="text" autocomplete="off" name="" placeholder="选择离店日期" class="c-datepicker-data-input only-date" value="" id="vos-out-shop-time" >
                    </div>
                </div>
            </div>
        </div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">共计天数：</label></div>
            <div class="block_right">
                <input type="text" class="layui-input" id="total-vos-days"  value="0" readonly style="border: none;" />
            </div>
        </div>

        <div class="bot">
            <input type="submit" value="提交" onclick="submitVos()">
            <input type="text" value="取消"  id="vos-submit-cancel">
        </div>
    </div>
</div>

<div class="mask_box3" >
    <div class="mask"></div>
    <div class="box">
        <div class="box_top" >新会员注册</div>

        <div class="box_block box_block1">
            <div class="block_left"><label for="">会员姓名：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-name"  placeholder="请输入会员姓名"  style="border: none;" /></div>
        </div>

        <div class="box_block box_block1">
            <div class="block_left"><label for="">会员手机号码：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-msisdn"  placeholder="请输入会员电话"  style="border: none;" /></div>
        </div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">会员爱宠名称：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-pet-name"  placeholder="请输入会员宠物名称"  style="border: none;" /></div>
        </div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">首充金额：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-charge-amount"  placeholder="请输入会员充值金额"  style="border: none;" /></div>
        </div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">服务折扣：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-service-off"  placeholder="（非必填）输入服务折扣"  style="border: none;" /></div>
        </div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">商品折扣：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-goods-off"  placeholder="（非必填）输入商品折扣" style="border: none;" /></div>
        </div>

        <div class="bot">
            <input type="submit" id="submit-register-member" value="提交" onclick="submitMember()">
            <input type="text" value="取消"  id="member-submit-cancel">
        </div>
    </div>
</div>

<div class="mask_box4" >
    <div class="mask"></div>
    <div class="box">
        <div class="box_top" >会员续充</div>

        <div class="box_block box_block1">
            <div class="block_left"><label for="">会员手机号码：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-con-msisdn"  placeholder="请输入会员电话"  style="border: none;" /></div>
        </div>

        <div class="box_block box_block1">
            <div class="block_left"><label for="">续充金额：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-con-charge"  placeholder="请输入续充金额"  style="border: none;" /></div>
        </div>

        <div class="box_block box_block1">
            <div class="block_left"><label for="">服务折扣：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-con-service-off"  placeholder="（非必填）输入服务折扣"  style="border: none;" /></div>
        </div>
        <div class="box_block box_block1">
            <div class="block_left"><label for="">商品折扣：</label></div>
            <div class="block_right"><input type="text" class="layui-input" id="member-con-goods-off"  placeholder="（非必填）输入商品折扣" style="border: none;" /></div>
        </div>

        <div class="bot">
            <input type="submit" id="submit-charge" value="提交" onclick="submitConCharge()">
            <input type="text" value="取消"  id="member-con-cancel">
        </div>
    </div>
</div>

<script src="./static/tmp/layui/layui.js"></script>

<script src="./static/tmp/js/jquery.min.js"></script>
<script src="./static/tmp/js/plugins/moment.min.js"></script>
<script src="./static/tmp/js/datepicker.all.js"></script>

<script>

    $(document).ready(function() {
        InitalPages();
        $('.inactive').click(function(){
            if($(this).siblings('ul').css('display')=='none'){
                $(this).parent('li').siblings('li').removeClass('inactives');
                $(this).addClass('inactives');
                $(this).siblings('ul').slideDown(100).children('li');
                if($(this).parents('li').siblings('li').children('ul').css('display')=='block'){
                    $(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
                    $(this).parents('li').siblings('li').children('ul').slideUp(100);

                }
            }else{
                //控制自身变成+号
                $(this).removeClass('inactives');
                //控制自身菜单下子菜单隐藏
                $(this).siblings('ul').slideUp(100);
                //控制自身子菜单变成+号
                $(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
                //控制自身菜单下子菜单隐藏
                $(this).siblings('ul').children('li').children('ul').slideUp(100);

                //控制同级菜单只保持一个是展开的（-号显示）
                $(this).siblings('ul').children('li').children('a').removeClass('inactives');
            }
        })
    });

    $(document).keydown(function(event){
　　　　if(event.keyCode == 27){ 
	    $('#goods-input-text').focus();
　　　　}

        if(event.keyCode == 32){ 
	    window.scrollTo(x-coord, y-coord);  
	    window.scrollTo(0,0); 
　　　　}
　　});

    $(function() {
        $("#settlement").click(function(){
            $(".mask_box").show();
        })
        $(".bot input:nth-child(2)").click(function(){
            $(".mask_box").hide();
        })
    })

    $(function () {
        $("#vos-submit-cancel").click(function () {
            $(".mask_box2").hide();
        })
    })

    $(function () {
        $("#member-submit-cancel").click(function () {
            $(".mask_box3").hide();
        })
    })

    $(function () {
        $("#member-con-cancel").click(function () {
            $(".mask_box4").hide();
        })
    })


    $(function () {
        $("#vos-in-shop-time").blur(function () {
            setTimeout(function(){
                caculVosDay();
            },300)
        })

        $("#vos-out-shop-time").blur(function () {
            setTimeout(function(){
                caculVosDay();
            },300)
        })

    })




    var totalQuantity = 0;    //总数量
    var totalMoney = 0;       //总金额
    var totalIntegral = 0;    //总积分
    function onNodeClick(){
        //获取选中节点的值
        var flag = false;
        var tree=mini.get("tree1");
        node=tree.getSelectedNode();

        if(node.end){
            /*树结构选中商品，table列表变化 - 开始*/
            totalQuantity++;
            $('.totalQuantity').html(totalQuantity);

            this_price = node.price; //获取单价
            this_price = parseFloat(this_price);
            totalMoney += this_price;
            $('.totalMoney').html(totalMoney.toFixed(2));

            this_integral = node.integral; //获取积分
            this_integral = parseFloat(this_integral);
            totalIntegral += this_integral;
            $('.totalIntegral').html(totalIntegral.toFixed(0));
            /*树结构选中商品，table列表变化 - 结束*/

            if($("#myTbody tr").length <= 0){
                var addtr = '<tr class="mytr">';
                addtr += '<td>'+node.id+'</td>';
                addtr += '<td>'+node.name+'</td>'	;
                addtr += '<td class="kbj danjia">'+node.price+'</td>';
                addtr += '<td class="numberTd"><div class="jiajian"><span class="jian" onclick="num_sub(this)">-</span><input type="text" value="1" class="num"><span class="jia" onclick="num_add(this)">+</span></div></td>';
                addtr += '<td class="jifen">'+node.integral+'</td>';
                addtr += '<td class="kbj remarks"></td>';
                addtr += '<td><button class="delete_btn">删除</button></td>';
                addtr += '</tr>';
                $("#myTbody").append(addtr);
                return;
            }else{
                $("#myTbody tr").each(function () {
                    //找到商品的名称与上面获取到的商品名称进行对比
                    if ($(this).children("td:eq(0)").html() == node.id) {
                        //找到此商品的数量
                        var count = parseInt($(this).children("td:eq(3)").find("input").val());
                        count++;
                        $(this).children("td:eq(3)").find("input").val(count); //对商品的数量进行重新赋值
                        flag = true;
                        return false;
                    }else {
                        flag = false;
                    }
                })
            }
            //如果为默认值也就是说里面没有此商品，所以添加此商品。
            if (flag == false) {
                var addtr = '<tr class="mytr">';
                addtr += '<td>'+node.id+'</td>';
                addtr += '<td>'+node.name+'</td>'	;
                addtr += '<td class="danjia">'+node.price+'</td>';
                addtr += '<td><div class="jiajian"><span class="jian" onclick="num_sub(this)">-</span><input type="text" value="1" class="num"><span class="jia" onclick="num_add(this)">+</span></div></td>';
                addtr += '<td class="jifen">'+node.integral+'</td>';
                addtr += '<td class="remarks"></td>';
                addtr += '<td><button class="delete_btn">删除</button></td>';
                addtr += '</tr>';
                $("#myTbody").append(addtr);
            }
        }
    }
    /*miniui - tree 插件 - 结束*/

    //加的效果
    function num_add(on_this){

        var totalQuantity = 0;    //总数量
        var totalMoney = 0;       //总金额
        var totalIntegral = 0;    //总积分
        $("#myTbody tr").each(function(){

            //获取当前行的单价
            this_price = $(this).children(".danjia").text();
            this_price = parseFloat(this_price);

            //获取当前行的积分
            this_integral = $(this).children(".jifen").text();
            this_integral = parseFloat(this_integral);

            //获取当前行的数量
            this_num = $(this).find(".num").val();
            this_num = parseInt(this_num);

            //获取当前行的总价格、总积分
            var trmoney = this_price*this_num;
            var trIntegral = this_integral*this_num;

            //总金额、总数量、总积分
            totalQuantity += this_num*1;   //总数量
            totalMoney += trmoney*1        //总金额
            totalIntegral += trIntegral*1  //总积分
        })
        $(".totalQuantity").html(totalQuantity);
        $(".totalMoney").html(totalMoney);
        $(".totalIntegral").html(totalIntegral);

        this_price = $(on_this).parents("td").siblings("td.danjia").text();//获取单价
        this_price = parseFloat(this_price);
        console.log(totalMoney);
        totalMoney += this_price;
        $('.totalMoney').html(totalMoney.toFixed(2));
        console.log(totalMoney);

        this_integral = $(on_this).parents("td").siblings("td.jifen").text();//获取积分
        this_integral = parseFloat(this_integral);
        totalIntegral += this_integral;
        $('.totalIntegral').html(totalIntegral.toFixed(0));

        //当前商品数量
        this_num = $(on_this).siblings('.num');
        var get_this_num = parseInt(this_num.val())+1;
        this_num.val(get_this_num);

        totalQuantity++;
        $('.totalQuantity').html(totalQuantity);
    }

    //减的效果
    function num_sub(on_this){

        var totalQuantity = 0;    //总数量
        var totalMoney = 0;       //总金额
        var totalIntegral = 0;    //总积分
        $("#myTbody tr").each(function(){

            //获取当前行的单价
            this_price = $(this).children(".danjia").text();
            this_price = parseFloat(this_price);

            //获取当前行的积分
            this_integral = $(this).children(".jifen").text();
            this_integral = parseFloat(this_integral);

            //获取当前行的数量
            this_num = $(this).find(".num").val();
            this_num = parseInt(this_num);

            //获取当前行的总价格、总积分
            var trmoney = this_price*this_num;
            var trIntegral = this_integral*this_num;

            //总金额、总数量、总积分
            totalQuantity += this_num*1;   //总数量
            totalMoney += trmoney*1        //总金额
            totalIntegral += trIntegral*1  //总积分
        })
        $(".totalQuantity").html(totalQuantity);
        $(".totalMoney").html(totalMoney);
        $(".totalIntegral").html(totalIntegral);

        //当前商品数量
        this_num = $(on_this).siblings('.num');
        if(this_num.val() <= 1){
            this_num.siblings('.jian').removeAttr('onclick');
            return;
        }else{
            var get_this_num = this_num.val()-1;
            this_num.val(get_this_num);

            this_price = $(on_this).parents("td").siblings("td.danjia").text();//获取单价
            totalMoney -= this_price;
            $('.totalMoney').html(totalMoney.toFixed(2));

            this_integral = $(on_this).parents("td").siblings("td.jifen").text();//获取积分
            totalIntegral -= this_integral;
            $('.totalIntegral').html(totalIntegral.toFixed(0));

            totalQuantity--;
            $('.totalQuantity').html(totalQuantity);
        }
    }

    //输入商品数量时改变合计的内容
    $("#myTbody").on("keyup",".num",function(){
        if($(this).val()==''){
            $(this).val('1');
        }
        $(this).val($(this).val().replace(/\D|^0/g,''));
        setTotal();
    })

    //点击 - 备注 - 可编辑
    $("#myTbody").on("click","td.remarks",function(){
        if(!$(this).is('.bj')){
            $(this).addClass('bj').html('<textarea class="layui-textarea" value="'+$(this).val()+'" />').find('textarea').focus().blur(function(){$(this).parent().removeClass('bj').html($(this).val())});
        }
    })

    //点击 - 单价 - 可编辑
    $("#myTbody").on("click","td.danjia",function(){
        if(!$(this).is('.bj')){
            var currentPrice = $(this).html();
            $(this).focus();
            $(this).addClass('bj').html('<input type="tel" class="layui-input" value="'+$(this).text()+'" />').find('input').focus().blur();
        }
    })
    //输入商品价格时改变合计的内容
    $("#myTbody").on("change",".danjia input",function(){
        var $this = $(this).val();
        var reg = /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/;
        if(!reg.test($this)){
            alert("请输入非负浮点数");
            $(this).parent().removeClass('bj').html($this||"1.00");
            $(this).val(currentPrice);
        }else{
            $(this).parent().removeClass('bj').html($this||"1.00");
            setTotal();
        }
    })

    //table tr 点击删除
    $("#myTbody").on("click",".delete_btn",function(){
        if($("#myTbody tr").length < 1){
            $(".totalQuantity").html("0");
            $(".totalMoney").html("0");
            $(".totalIntegral").html("0");
            return;
        }

        $(this).parents("#myTbody tr").remove();
        setTotal();
    })

    function setTotal(){
        var totalQuantity = 0;    //总数量
        var totalMoney = 0;       //总金额
        var totalIntegral = 0;    //总积分
        $("#myTbody tr").each(function(){

            //获取当前行的单价
            this_price = $(this).children(".danjia").text();
            this_price = parseFloat(this_price);

            //获取当前行的积分
            this_integral = $(this).children(".jifen").text();
            this_integral = parseFloat(this_integral);

            //获取当前行的数量
            this_num = $(this).find(".num").val();
            this_num = parseInt(this_num);

            //获取当前行的总价格、总积分
            var trmoney = this_price*this_num;
            var trIntegral = this_integral*this_num;

            //总金额、总数量、总积分
            totalQuantity += this_num*1;   //总数量
            totalMoney += trmoney*1        //总金额
            totalIntegral += trIntegral*1  //总积分
        })
        $(".totalQuantity").html(totalQuantity);
        $(".totalMoney").html(totalMoney);
        $(".totalIntegral").html(totalIntegral);
    }
</script>

<script type="text/javascript">
    $(function(){
        var DATAPICKERAPI = {
// 默认input显示当前月,自己获取后填充
            activeMonthRange: function () {
                return {
                    begin: moment().set({ 'date': 1, 'hour': 0, 'minute': 0, 'second': 0 }).format('YYYY-MM-DD HH:mm:ss'),
                    end: moment().set({ 'hour': 23, 'minute': 59, 'second': 59 }).format('YYYY-MM-DD HH:mm:ss')
                }
            },
            shortcutMonth: function () {
// 当月
                var nowDay = moment().get('date');
                var prevMonthFirstDay = moment().subtract(1, 'months').set({ 'date': 1 });
                var prevMonthDay = moment().diff(prevMonthFirstDay, 'days');
                return {
                    now: '-' + nowDay + ',0',
                    prev: '-' + prevMonthDay + ',-' + nowDay
                }
            },
// 注意为函数：快捷选项option:只能同一个月份内的
            rangeMonthShortcutOption1: function () {
                var result = DATAPICKERAPI.shortcutMonth();
                return [{
                    name: '昨天',
                    day: '-1,-1',
                    time: '00:00:00,23:59:59'
                }, {
                    name: '这一月',
                    day: result.now,
                    time: '00:00:00,'
                }, {
                    name: '上一月',
                    day: result.prev,
                    time: '00:00:00,23:59:59'
                }];
            },
// 快捷选项option
            rangeShortcutOption1: [{
                name: '最近一周',
                day: '-7,0'
            }, {
                name: '最近一个月',
                day: '-30,0'
            }, {
                name: '最近三个月',
                day: '-90, 0'
            }],
            singleShortcutOptions1: [{
                name: '今天',
                day: '0'
            }, {
                name: '昨天',
                day: '-1',
                time: '00:00:00'
            }, {
                name: '一周前',
                day: '-7'
            }]
        };
//十分秒年月日单个
        $('.J-datepicker').datePicker({
            hasShortcut:true,
            min:'2018-01-01 04:00:00',
            max:'2019-04-29 20:59:59',
            shortcutOptions:[{
                name: '今天',
                day: '0'
            }, {
                name: '昨天',
                day: '-1',
                time: '00:00:00'
            }, {
                name: '一周前',
                day: '-7'
            }],
            hide:function(){
                console.info(this)
            }
        });

//年月日单个
        $('.J-datepicker-day').datePicker({
            hasShortcut: true,
            format:'YYYY-MM-DD',
            shortcutOptions: [{
                name: '今天',
                day: '0'
            }, {
                name: '昨天',
                day: '-1'
            }, {
                name: '一周前',
                day: '-7'
            }]
        });

//年月日范围
        $('.J-datepicker-range-day').datePicker({
            hasShortcut: true,
            format: 'YYYY-MM-DD',
            isRange: true,
            shortcutOptions: DATAPICKERAPI.rangeShortcutOption1
        });

//十分年月日单个
        $('.J-datepickerTime-single').datePicker({
            format: 'YYYY-MM-DD HH:mm'
        });

//十分年月日范围
        $('.J-datepickerTime-range').datePicker({
            format: 'YYYY-MM-DD HH:mm',
            isRange: true
        });

//十分秒年月日范围，包含最大最小值
        $('.J-datepicker-range').datePicker({
            hasShortcut: true,
            min: '2018-01-01 06:00:00',
            max: '2019-04-29 20:59:59',
            isRange: true,
            shortcutOptions: [{
                name: '昨天',
                day: '-1,-1',
                time: '00:00:00,23:59:59'
            },{
                name: '最近一周',
                day: '-7,0',
                time:'00:00:00,'
            }, {
                name: '最近一个月',
                day: '-30,0',
                time: '00:00:00,'
            }, {
                name: '最近三个月',
                day: '-90, 0',
                time: '00:00:00,'
            }]
        });
//十分秒年月日范围，限制只能选择同一月，比如2018-10-01到2018-10-30
        $('.J-datepicker-range-betweenMonth').datePicker({
            isRange: true,
            between:'month',
            hasShortcut: true,
            shortcutOptions: DATAPICKERAPI.rangeMonthShortcutOption1()
        });

//十分秒年月日范围，限制开始结束相隔天数小于30天
        $('.J-datepicker-range-between30').datePicker({
            isRange: true,
            between: 30
        });

//年月单个
        $('.J-yearMonthPicker-single').datePicker({
            format: 'YYYY-MM',
            min: '2018-01',
            max: '2019-04'
        });

//选择年
        $('.J-yearPicker-single').datePicker({
            format: 'YYYY',
            min: '2018',
            max: '2020'
        });


    });
</script>
</body>
</html>
