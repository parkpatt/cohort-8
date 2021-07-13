package learn.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("dependency-configuration.xml");

        Book snowCrash = context.getBean("snowCrash", Book.class);
        Book cryptonomicon = context.getBean("crypto", Book.class);

        Book protoBook = context.getBean("proto", Book.class);

        System.out.println(snowCrash);
        System.out.println(cryptonomicon);
    }
}
