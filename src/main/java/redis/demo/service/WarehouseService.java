package redis.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import redis.demo.exception.EntityNotFoundException;
import redis.demo.model.Warehouse;
import redis.demo.repository.WarehouseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public Warehouse save(Warehouse warehouse)
    {
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getAll()
    {
        return (List<Warehouse>) warehouseRepository.findAll();
    }

    public Warehouse getById(String id)
    {
        return warehouseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public void deleteById(String id)
    {
        warehouseRepository.deleteById(id);
    }

    public Warehouse putById(String id, Warehouse warehouse)
    {
        warehouseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        warehouse.setId(id);
        return warehouseRepository.save(warehouse);
    }
}
