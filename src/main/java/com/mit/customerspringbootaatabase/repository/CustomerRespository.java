package com.mit.customerspringbootaatabase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mit.customerspringbootaatabase.model.Customer;

@Transactional
public interface CustomerRespository extends CrudRepository<Customer, Long> {

	public Customer findByEmail(@Param("email") String email); // Valid

	@Query(value = "from Customer c where c.email=?1")
	public Customer findByEmailAddress(@Param("email") String email); // Valid

	@Query(value = "select c.name from Customer c")
	public List<String> findAllNames();

	@Query(value = "select c.name, c.contactNo from Customer c")
	public List<Object[]> findAllNamesNContactNo();

	@Modifying
	@Query(value = "delete from Customer c where c.email=?1") // HQL
	public void deleteCustomerByEmail(@Param("email") String email);

	@Modifying
	@Query(value = "update Customer c set c.name=?1 , c.email=?2, c.contactNo=?3,c.billingAddress=?4 where c.id=?5 ")
	public void updateCustomer(@Param("name") String name, @Param("email") String email,@Param("contactNo") Long contactNo, @Param("billingAddress") String billingAddress, @Param("id") Long id);

}
