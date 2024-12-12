package org.yourcompany.yourproject.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.yourcompany.yourproject.model.User;
 

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

}