import com.my.study.StudyProducerApplication;
import com.my.study.dao.ConsultContentRepository;
import com.my.study.entity.ConsultContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudyProducerApplication.class})
public class testJpa {


    @Autowired
    private ConsultContentRepository contentRepository;


    @Test
    public  void  test(){

        /**
         * Root<ConsultContent> 查询的类型
         * CriteriaQuery<?> 添加查询条件
         * CriteriaBuilder 构建Predicate
         */
        contentRepository.findAll(new Specification<ConsultContent>() {
            @Override
            public Predicate toPredicate(Root<ConsultContent> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                Path state = root.get("state");
                return criteriaBuilder.gt(state,50);
            }
        } );
    }
}
