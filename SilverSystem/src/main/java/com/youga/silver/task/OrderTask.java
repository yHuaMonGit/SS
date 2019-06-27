package com.youga.silver.task;

import com.youga.silver.service.OrderService;
import com.youga.silver.service.impl.OrderServiceImpl;
import javazoom.jl.player.Player;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Component
public class OrderTask {

    OrderService orderService = new OrderServiceImpl();

    @Scheduled(cron = "0/10 * * * * ? ")
    public void taskCycle() throws Exception {

        System.out.println("check order!");

        if (orderService.checkNewOrder()){
            File file=new File("E:\\model\\baking\\music\\appointmentTips.mp3");
            FileInputStream fis=new FileInputStream(file);
            BufferedInputStream stream=new BufferedInputStream(fis);
            Player player=new Player(stream);
            player.play();
        }

    }

}
