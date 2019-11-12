package gr.isp.springbootapplication.repository;

import gr.isp.springbootapplication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
