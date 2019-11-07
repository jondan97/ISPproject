package gr.isp.springbootapplication.repository;

import java.util.Optional;

import gr.isp.springbootapplication.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
    public Optional<User> findByUsername(String username);
}
