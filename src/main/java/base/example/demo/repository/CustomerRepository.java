package base.example.demo.repository;

import base.example.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    //  кастомный запрос на случай сложной логики
    //запрос Апи напрямую с БД рабоатем через репозиторий
    @Query("SELECT c FROM CustomerEntity c WHERE c.archive = false")
    List<CustomerEntity> findAllNotActiveCustomers();

}
