package redis.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import redis.demo.exception.EntityNotFoundException;
import redis.demo.model.Description;
import redis.demo.model.Unit;
import redis.demo.model.Warehouse;
import redis.demo.repository.WarehouseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {
    @Mock
    WarehouseRepository warehouseRepository;

    @InjectMocks
    WarehouseService warehouseService;

    @Captor
    ArgumentCaptor<Warehouse> warehouseArgumentCaptor;

    @Captor
    ArgumentCaptor<String> idCaptor;

    @Test
    void save() {
        //given
        Warehouse warehouse = new Warehouse("puk", "danila", LocalDate.now(), 1000, new Unit("кг"), List.of(new Description("bla bla", "5/10"),
                new Description("bli bli", "10/10")));
        given(warehouseRepository.save(warehouse)).willReturn(warehouse);
        //when
        warehouseService.save(warehouse);
        //then
        Assertions.assertEquals(warehouseService.save(warehouse), warehouse);
    }

    @Test
    void getAll() {
        //given
        List<Warehouse> list = List.of(new Warehouse("test", "test", LocalDate.now(), 1000, new Unit("кг"), List.of(new Description("test", "5/10"),
                new Description("test", "10/10"))), new Warehouse("puk", "danila", LocalDate.now(), 1000, new Unit("кг"), List.of(new Description("bla bla", "5/10"),
                new Description("bli bli", "10/10"))));
        given(warehouseRepository.findAll()).willReturn(list);
        //when
        warehouseService.getAll();
        //then
        Assertions.assertEquals(warehouseService.getAll(), list);
    }

    @Test
    void getById() {
        //given
        Warehouse warehouse = new Warehouse("test", "test", LocalDate.now(), 1000, new Unit("кг"), List.of(new Description("test", "5/10"),
                new Description("test", "10/10")));
        String id = "test";
        given(warehouseRepository.findById(id)).willReturn(Optional.of(warehouse));
        //when
        warehouseService.getById(id);
        //then
        verify(warehouseRepository).findById(idCaptor.capture());
        String testId = idCaptor.getValue();
        Assertions.assertEquals(id, testId);
        Assertions.assertEquals(warehouse, warehouseService.getById(id));
    }

    @Test
    void putById()
    {
        //given
        Warehouse warehouse = new Warehouse("test", "test", LocalDate.now(), 1000, new Unit("кг"), List.of(new Description("test", "5/10"),
                new Description("test", "10/10")));
        String id = "test";
        given(warehouseRepository.findById(id)).willReturn(Optional.of(warehouse));
        //when
        warehouseService.putById(id, warehouse);
        //then
        verify(warehouseRepository).save(warehouseArgumentCaptor.capture());
        Warehouse warehouseTest = warehouseArgumentCaptor.getValue();
        Assertions.assertEquals(id, warehouseTest.getId());
    }

    @Test
    void check_Put_on_EntityNotFoundException_when_Id_Not_Found()
    {
        //given
        String id = "test";
        //when

        //then
        Assertions.assertThrows(EntityNotFoundException.class, () -> warehouseService.putById(id, any()));
    }

    @Test
    void check_Exception_on_EntityNotFoundException_when_Id_Not_Found() {
        //given
        String id = "test";
        //when
        warehouseService.deleteById(id);
        //then
        Assertions.assertThrows(EntityNotFoundException.class, () -> warehouseService.getById(id));
    }

    @Test
    void deleteById() {
        //given
        String id = "test";
        //when
        warehouseService.deleteById(id);
        //then
        verify(warehouseRepository).deleteById(idCaptor.capture());
        String idTest = idCaptor.getValue();
        Assertions.assertEquals(id, idTest);
    }
}