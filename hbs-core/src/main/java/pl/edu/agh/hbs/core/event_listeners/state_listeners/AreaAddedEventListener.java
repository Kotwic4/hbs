package pl.edu.agh.hbs.core.event_listeners.state_listeners;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.edu.agh.hbs.core.model.Area;

@Component
public class AreaAddedEventListener implements EntryAddedListener<String, Area> {

    private static final Logger log = LoggerFactory.getLogger(AreaAddedEventListener.class);

    @Override
    public void entryAdded(EntryEvent<String, Area> event) {
        log.debug("Area: {} has been added", event.getKey());
    }
}