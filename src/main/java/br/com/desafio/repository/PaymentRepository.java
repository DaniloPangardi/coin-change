package br.com.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.desafio.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	@Query(value = "Select count(*) from payment where payer_id = :payerId", nativeQuery = true)
	Integer totalPurchaseByPayerId(@Param("payerId") Long payerId);

	List<Payment> findByPayerId(Long payerId);

}
