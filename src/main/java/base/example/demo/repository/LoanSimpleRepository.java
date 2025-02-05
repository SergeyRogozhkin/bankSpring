package base.example.demo.repository;

import base.example.demo.entity.LoanSimple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanSimpleRepository extends JpaRepository<LoanSimple, Integer> {
}
