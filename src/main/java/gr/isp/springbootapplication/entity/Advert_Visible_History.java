package gr.isp.springbootapplication.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Advert_Visible_History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDateTime timePosted;

    @Column
    private LocalDateTime timeUnposted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="advert_id")
    private Advert advert;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(LocalDateTime timePosted) {
        this.timePosted = timePosted;
    }

    public LocalDateTime getTimeUnposted() {
        return timeUnposted;
    }

    public void setTimeUnposted(LocalDateTime timeUnposted) {
        this.timeUnposted = timeUnposted;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }
}
