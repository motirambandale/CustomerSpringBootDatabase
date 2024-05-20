package com.mit.customerspringbootaatabase.restcontroller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mit.customerspringbootaatabase.model.Customer;
import com.mit.customerspringbootaatabase.repository.CustomerRespository;

@RestController //
public class CustomerRestController {

	@Autowired
	CustomerRespository customerRespository;

	@PostMapping(value = "/saveCustomer")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = customerRespository.save(customer);
		return savedCustomer;
	}

	@PostMapping(value = "/saveAllCustomer")
	public List<Customer> saveAllCustomer(@RequestBody List<Customer> customers) {
		List<Customer> savedCustomerList = (List<Customer>) customerRespository.saveAll(customers);
		return savedCustomerList;
	}

	@GetMapping(value = "/findAllCustomers")
	public List<Customer> findAllCustomers() {
		List<Customer> customerList = (List<Customer>) customerRespository.findAll();
		return customerList;
	}
	
	@GetMapping(value = "/findAllNames")
	public List<String> findAllNames(){
		List<String> customerList = customerRespository.findAllNames();
		return customerList;
	}
	
	@GetMapping(value = "/findAllNamesNContactNo")
	public List<Object[]> findAllNamesNContactNo(){
		List<Object[]> customerList = customerRespository.findAllNamesNContactNo();
		return customerList;
	}
	
	
	@GetMapping(value = "/findCustomerById/{id}")
	public Customer findCustomerById(@PathVariable Long id) {
		// Optional<Customer> optionalCustomer = customerRespository.findById(id);
		// Customer customer=optionalCustomer.get();
		Customer customer = customerRespository.findById(id).get();
		return customer;
	}

	// http://localhost:8080/deleteCustomerById/1

	@DeleteMapping(value = "/deleteCustomerById/{id}")
	public String deleteCustomerById(@PathVariable Long id) {
		
		// customerRespository.deleteById(id);
		Customer customer = customerRespository.findById(id).get();
		customerRespository.delete(customer);	
		return "Customer is deleted with Id:" + id;
	}
	
	@GetMapping(value = "/findCustomerByEmail/{email}")
	public Customer findCustomerByEmail(@PathVariable String email) {
	  Customer customer	=customerRespository.findByEmail(email);	  
	  return customer;
	}

	
	@PutMapping(value = "/updateCustomer")
	public String updateCustomer(@RequestBody Customer customer) {
	          customerRespository.updateCustomer(customer.getName(), customer.getEmail(), customer.getContactNo(), customer.getBillingAddress(), customer.getId());
		return  "Customer record is updated with Id"+customer.getId();
	}
	
	
	@DeleteMapping(value = "/deleteCustomerByEmail/{email}")
	public String deleteCustomerByEmail(@PathVariable String email) {
		
		customerRespository.deleteCustomerByEmail(email);
		
		return "Customer is deleted with Email Id:"+email;
	
	}	
	
	
	
	
	
}
