package com.finalChallenge.CreditCardSpringAPI.controller;

import java.util.List;

import com.finalChallenge.CreditCardSpringAPI.exceptions.CustomerNotFoundException;
import com.finalChallenge.CreditCardSpringAPI.models.Customer;
import com.finalChallenge.CreditCardSpringAPI.repo.ICustomerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
class CustomerController {

    private final ICustomerRepo repository;

    CustomerController(ICustomerRepo repository) {
        this.repository = repository;
    }

    @RequestMapping(method=RequestMethod.GET, value="/customers")
    List<Customer> allCustomers() {
        return repository.findAll();
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    Customer newItem(@RequestBody Customer item) {
        return repository.save(item);
    }

    // Single item

    @GetMapping("/customers/{id}")
    Customer getCustomerByID(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @GetMapping("/itemsrp")
    Customer getMusicItemrp(@RequestParam(value = "id",
            defaultValue = "1", required = false) Long id){
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PutMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    MusicItem updateMusicItem(@RequestBody MusicItem newItem, @PathVariable Long id) {
        return repository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setArtist_group(newItem.getArtist_group());
                    item.setGenre(newItem.getGenre());
                    return repository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return repository.save(newItem);
                });
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomerByID(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
