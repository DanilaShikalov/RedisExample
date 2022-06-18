package redis.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.demo.model.Description;
import redis.demo.model.Unit;
import redis.demo.model.Warehouse;
import redis.demo.queue.RedisMessagePublisher;
import redis.demo.queue.RedisMessageSubscriber;
import redis.demo.service.WarehouseService;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@Slf4j
public class RunApp {
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
    }

    @Bean
    CommandLineRunner run(WarehouseService service)
    {
        return args -> {
//            for (int i = 0;; i++) {
//                String message = "Message " + i;
//                service.publish(message);
//                System.out.println("publish");
//                //Thread.sleep(2500);
//                System.out.println(RedisMessageSubscriber.messageList);
//            }
            //Для Warehouse просто сохранение в redis
            service.save(new Warehouse("puk", "danila", LocalDate.now(), 1000, new Unit("кг"), List.of(new Description("bla bla", "5/10"),
                    new Description("bli bli", "10/10"))));
            service.save(new Warehouse("test", "test", LocalDate.now(), 1000, new Unit("кг"), List.of(new Description("test", "5/10"),
                    new Description("test", "10/10"))));
            log.info(service.getAll().toString());
            log.info(service.getById("puk").toString());
        };
    }
}
