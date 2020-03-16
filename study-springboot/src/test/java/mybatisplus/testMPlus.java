package mybatisplus;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.study.StudySpringboot;
import com.my.study.dao.ConsuilContentMapper;
import com.my.study.entity.ConsultContent;
import com.my.study.service.ConsultContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudySpringboot.class})
public class testMPlus {

    @Autowired
    private ConsuilContentMapper consuilContentMapper;

    @Autowired
    private ConsultContentService consultContentService;

    @Test
    public void test1() {
        //传空查所有
        List<ConsultContent> consultContents = consuilContentMapper.selectList(null);
        System.out.println(consultContents.get(0).toString());
    }

    @Test
    public void test2() {
        QueryWrapper<ConsultContent> wrapper = new QueryWrapper<>();
        wrapper.like("content", "内容").lt("state", 5).select("content", "state");
        List<ConsultContent> consultContents = consuilContentMapper.selectList(wrapper);
        consultContents.forEach(System.out::println);

    }


    @Test
    public void test3() {
        QueryWrapper<ConsultContent> wrapper = new QueryWrapper<>();
        wrapper.like("content", "内容").lt("state", 5).select("content", "state");
        //Page<ConsultContent> consultContentPage = new Page<>(1, 2);
        //false不查总记录数
        Page<ConsultContent> consultContentPage = new Page<>(1, 2, false);
        IPage<ConsultContent> consultContentIPage = consuilContentMapper.selectPage(consultContentPage, wrapper);
        consultContentIPage.getRecords().forEach(System.out::println);

    }

    @Test
    public void tes4() {
        List<ConsultContent> list = consultContentService.list();
        list.forEach(System.out::println);
    }
}