package com.tiaotiaopoker.controller;

import com.tiaotiaopoker.pojo.ApplyOrder;
import com.tiaotiaopoker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("order")
public class ApplyOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("listPage")
    public ModelAndView listPage(ModelAndView mv, ApplyOrder order) {



        mv.addObject("order",order);
        mv.setViewName("admin/order/orderListPage");
        return mv;
    }
}
