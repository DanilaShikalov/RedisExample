package redis.demo.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String id) {
        super("Entity with id = " + id + " is not found");
    }
}
