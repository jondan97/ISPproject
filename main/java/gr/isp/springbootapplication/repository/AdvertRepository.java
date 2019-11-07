package gr.isp.springbootapplication.repository;

import gr.isp.springbootapplication.entity.Advert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {

}
