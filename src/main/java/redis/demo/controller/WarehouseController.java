package redis.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redis.demo.model.Warehouse;
import redis.demo.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@AllArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getAllWarehouse()
    {
        return warehouseService.getAll();
    }

    @GetMapping("/{id}")
    public Warehouse getById(@PathVariable String id)
    {
        return warehouseService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id)
    {
        warehouseService.deleteById(id);
    }

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse)
    {
        return warehouseService.save(warehouse);
    }
}
