package redis.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import redis.demo.model.Warehouse;
import redis.demo.repository.WarehouseRepository;

@Service
@AllArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public Warehouse save(Warehouse warehouse)
    {
        return warehouseRepository.save(warehouse);
    }
}
