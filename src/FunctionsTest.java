import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    @org.junit.jupiter.api.Test
    void insertEmployerTest() {
        Assert.check(Functions.insertEmployer("testEmplo"));
    }
}