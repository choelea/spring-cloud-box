import tech.icoding.samples.generator.entity.Company;
import tech.icoding.samples.generator.entity.Course;
import tech.icoding.samples.generator.entity.User;
import tech.icoding.sbc.code.generator.SimpleGenerator;

import java.io.IOException;

/**
 * @author : Joe
 * @date : 2022/5/5
 */
public class GeneratorApplication {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SimpleGenerator simpleGenerator = new SimpleGenerator("Data", "Form", "Repository",
                "Service", "Facade","Controller");
        simpleGenerator.generateALl(Company.class);
        simpleGenerator.generateALl(User.class);
        simpleGenerator.generateALl(Course.class);
    }
}
