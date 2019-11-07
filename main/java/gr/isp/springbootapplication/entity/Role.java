package gr.isp.springbootapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String authority) {
        this.role = authority;
    }
}
