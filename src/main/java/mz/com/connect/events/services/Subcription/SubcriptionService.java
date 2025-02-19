package mz.com.connect.events.services.Subcription;

import mz.com.connect.events.dto.SubcriberResponse;
import mz.com.connect.events.models.Subcription;
import mz.com.connect.events.models.User;

public interface SubcriptionService {

    SubcriberResponse createNewSubcription(String eventName, User user, Integer userId);
}
