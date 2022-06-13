package redis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@RedisHash("warehouse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse implements Serializable {
    @Id
    private String id;
    private String name;
    private LocalDate date;
    private int price;
    private Unit unit;
    private List<Description> descriptionList;
}
