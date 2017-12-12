package fi.hh.BookStore.domain;

import org.springframework.data.repository.CrudRepository;

import fi.hh.BookStore.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
    User findByUsername(String username);
    
}