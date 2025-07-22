import com.elementoj.module.problem.mapper.EleProblemMapper;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class test {
    @Resource
    EleProblemMapper mapper;

    public void test(String[] args) {
        System.out.println(mapper.getTitleById(1000L));
    }
}
