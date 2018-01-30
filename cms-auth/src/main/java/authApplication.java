import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ganster.cms.core.dao.mapper")
public class authApplication {
    public static void main(String[] args){
        SpringApplication.run(authApplication.class,args);
    }
}
