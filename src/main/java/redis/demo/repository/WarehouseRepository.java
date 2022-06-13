package redis.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redis.demo.model.Warehouse;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, String> {
}
