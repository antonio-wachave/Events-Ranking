package mz.com.connect.events.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_subcription")
public class Subcription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcription_number")
    private Integer subcriptionNumber;

    @ManyToOne
    @JoinColumn(name="event_id", nullable = true)
    private Event event;

    @ManyToOne
    @JoinColumn(name="subcriber_user_id")
    private User subscriber;

    @ManyToOne
    @JoinColumn(name="indecation_user_id", nullable = true)
    private User indication;

    public Integer getSubcriptionNumber() {
        return subcriptionNumber;
    }

    public void setSubcriptionNumber(Integer subcriptionNumber) {
        this.subcriptionNumber = subcriptionNumber;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public User getIndication() {
        return indication;
    }

    public void setIndication(User indication) {
        this.indication = indication;
    }
}
