package me.velfinvelasquez.cqrs.core.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.velfinvelasquez.cqrs.core.events.BaseEvent;

public abstract class AggregateRoot {
    protected String id;
    private int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>();
    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public String getId() {
        return this.id;
    }

    public int getVerion() {
        return this.version;
    }

    public void serVersion(int version) {
        this.version = version;
    }

    public List<BaseEvent> getUncommitedChanges() {
        return this.changes;
    }

    public void markChangesAsCommited() {
        this.changes.clear();
    }

    protected void applyChanges(BaseEvent event, Boolean isNewEvent) {
        try {
            var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            logger.log(Level.WARNING,
                    MessageFormat.format("El método apply no fue encontrado para {0}", event.getClass().getName()));

        } catch (Exception ex) {
            logger.log(Level.SEVERE, MessageFormat.format("errores aplicando el evento al agregate ", ex));

        } finally {
            if (isNewEvent) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChanges(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(e -> applyChanges(e, false));
    }

    
}
