import tech.icoding.samples.jpademo.entity.*;
import tech.icoding.sbc.code.generator.SimpleGenerator;

/**
 * @author : Joe
 * @date : 2022/5/5
 */
public class GeneratorApplication {
    public static void main(String[] args) throws Exception {
        SimpleGenerator simpleGenerator = new SimpleGenerator("Data", "Form", "Repository",
                "Service", "Facade","Controller");
        simpleGenerator.setOverwrite(true);
//        simpleGenerator.generateALl(Company.class);
//        simpleGenerator.generateALl(User.class);


//        simpleGenerator.generateALl(Course.class);
        simpleGenerator.generateALl(Category.class);
        simpleGenerator.generateALl(Product.class);
    }
}
