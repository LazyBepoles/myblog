package cn.liang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyblogApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyblogApplication.class, args);
  }
}
