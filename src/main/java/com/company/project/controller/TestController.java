package com.company.project.controller;

import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.company.project.util.ExcelUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/list")
    public void list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
            , HttpServletResponse response) {
//        List<WerpPurchaseOrderDetails> list1 = werpPurchaseOrderDetailsService.findAll();


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dataList", "");
        TemplateExportParams params = new TemplateExportParams("");
        String temp = "detail.xls";

        try {
            ExcelUtil.export(response, ExcelUtil.getWorkbook(params, map, temp), "采购.xls");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
