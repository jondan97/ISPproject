package gr.isp.springbootapplication.repository;

import gr.isp.springbootapplication.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
    Optional<User> findByEmail(String email);
    Optional<User>findById(Long id);
    User findFirstByEmail(String email);
    User findFirstById(Long id);
}
