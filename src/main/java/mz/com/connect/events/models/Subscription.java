package mz.com.connect.events.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_number")
    private Integer subscriptionNumber;

    @ManyToOne
    @JoinColumn(name="event_id", nullable = true)
    private Event event;

    @ManyToOne
    @JoinColumn(name="subscriber_user_id")
    private User subscriber;

    @ManyToOne
    @JoinColumn(name="indication_user_id", nullable = false)
    private User indication;

    public Subscription(){

    }

    public Subscription(Event event, User subscriber, User indication) {
        this.event = event;
        this.subscriber = subscriber;
        this.indication = indication;
    }

    public Integer getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public void setSubscriptionNumber(Integer subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
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

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionNumber=" + subscriptionNumber +
                ", event=" + event +
                ", subscriber=" + subscriber +
                ", indication=" + indication +
                '}';
    }
}
