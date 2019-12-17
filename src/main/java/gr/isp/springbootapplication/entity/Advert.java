package gr.isp.springbootapplication.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Lob
    @Column
    private String body;

    @Column
    private Integer salary;

    @Column
    private String industry;

    @Column
    private String status;

    @Column
    private LocalDateTime timeCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy="advert", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Advert_Visible_History> dates;

    @Transient
    private Long daysPosted;

    @Transient
    private Long daysCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getDaysPosted() {
        return daysPosted;
    }

    public void setDaysPosted(Long daysPosted) {
        this.daysPosted = daysPosted;
    }

    public Long getDaysCreated() {
        return daysCreated;
    }

    public void setDaysCreated(Long daysCreated) {
        this.daysCreated = daysCreated;
    }

    public Set<Advert_Visible_History> getDates() {
        return dates;
    }

    public void setDates(Set<Advert_Visible_History> dates) {
        this.dates = dates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return Objects.equals(id, advert.id) &&
                Objects.equals(title, advert.title) &&
                Objects.equals(body, advert.body) &&
                Objects.equals(salary, advert.salary) &&
                Objects.equals(industry, advert.industry) &&
                Objects.equals(status, advert.status) &&
                Objects.equals(user, advert.user);
    }
}
