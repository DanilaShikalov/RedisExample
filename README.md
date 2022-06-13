Для запуска Redis использовать команду
```
docker run --name our-redis -p 6379:6379 redis
```
Для подключения к Redis использовать команду
```
docker run -it --network container:our-redis redis redis-cli -h 127.0.0.1
```
[Документация](https://otus.ru/nest/post/715/)