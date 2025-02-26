package base.example.demo.repository;

import base.example.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("SELECT c FROM CustomerEntity c WHERE c.archive = false")
    List<CustomerEntity> findAllNotActiveCustomers();

    @Query("SELECT c FROM CustomerEntity c WHERE c.archive = false AND c.id = :id")
    CustomerEntity findByIdNotArchive(Long id);

}
