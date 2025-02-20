package mz.com.connect.events.services.Subscription;

import mz.com.connect.events.dto.SubscriberResponse;
import mz.com.connect.events.models.Subscription;
import mz.com.connect.events.models.User;

public interface SubscriptionService {

    Subscription createNewSubscription(String eventName, User user);

}
