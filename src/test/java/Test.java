import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private static final Logger LOG = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void test() {

        int i = 1;
        ++i;
        LOG.info("i:{}", i);

    }
}
