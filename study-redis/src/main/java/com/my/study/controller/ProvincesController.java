package com.my.study.controller;

import com.my.study.entity.Provinces;
import com.my.study.service.YqGwProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value = "/provinces")
public class ProvincesController {

    private static final Logger logger = LoggerFactory.getLogger(ProvincesController.class);

	@Autowired
	private YqGwProvinceService provincesService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Provinces> query(HttpServletRequest request){
        try {
            return provincesService.list();
        } catch (Exception e) {
            return null;
        }
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public int add(HttpServletRequest request, @RequestBody Provinces bean){
        try {
            provincesService.add(bean);
            return 1;
        } catch (Exception e) {
            logger.error("新增地市失败",e);
            return 0;
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public int update( @PathVariable("id") String id,@RequestBody Provinces bean){
       bean.setId(id);
        try {
            provincesService.update(bean);
            return 1;
        } catch (Exception e) {
            logger.error("更新地市信息失败",e);
            return 0;
        }
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public int delete(@RequestParam("provinceid") String provinceid){
        try {
            provincesService.delete(provinceid);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

}