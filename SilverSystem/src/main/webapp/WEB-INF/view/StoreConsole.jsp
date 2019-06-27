<%--
  Created by IntelliJ IDEA.
  User: ZHENG
  Date: 2019/1/8
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <div class="layui-row">
        <div class="top">
            <label>商品名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="title" required lay-verify="required" placeholder="扫码或手动输入商品名称或编码" id="store-search-by-name" autocomplete="off" class="layui-input" onchange="searchGoods(this)">
            </div>
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn-primary" style="vertical-align: middle;margin-bottom: 2px;" onclick="checkStoreByName()">名称快速查询</button>
                <button class="layui-btn layui-btn-primary" style="vertical-align: middle;margin-bottom: 2px;" onclick="showAllGoods()">列出所有商品</button>
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
                    <th lay-data="{field:'remarks', edit: 'text'}">规格</th>
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
                <td>有效期：</td>
                <td>永久</td>
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
    </div>

</head>
<body>

</body>
</html>
