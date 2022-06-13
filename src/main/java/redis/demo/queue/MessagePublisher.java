package redis.demo.queue;

public interface MessagePublisher {
    void publish(final String message);
}
