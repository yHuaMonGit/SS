package com.youga.silver.controller;




import com.alibaba.fastjson.JSONArray;
import com.youga.silver.dao.GoodsDao;
import com.youga.silver.dao.impl.GoodsDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AddGoodsController {

    @RequestMapping("/addGoods")
    public String addGoods() {
        return "addGoods";
    }

    @RequestMapping("/uploadGoods")
    public void uploadGoods(HttpServletRequest req, HttpServletResponse resp) {


        String GoodsListLine = req.getParameter("str");

        List<String> goodsListarr = JSONArray.parseArray(GoodsListLine,String.class);


        //Service层要做多扫码的判断；
        GoodsDao goodsDao = new GoodsDaoImpl();
        goodsDao.AddGoodsRecord(goodsListarr);

        //System.out.println("test");

    }

}
