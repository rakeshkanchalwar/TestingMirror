package com.bitwise.app.graph.components.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Root {

    private final List<Element> elements = new ArrayList<>();
    private final Collection<Runnable> listeners = new HashSet<>();

    public List<Element> getNotes() {
        return Collections.unmodifiableList(elements);
    }

    public void clear() {
        elements.clear();
        fireChangedEvent();
    }

    public void addElement(Element e) {
        elements.add(e);
        fireChangedEvent();
    }

    public void removeElement(Element e) {
        elements.remove(e);
        fireChangedEvent();
    }

    public void addListener(Runnable r) {
        listeners.add(r);
    }

    public void removeListener(Runnable r) {
        listeners.remove(r);
    }

    private void fireChangedEvent() {
        for (Runnable l : listeners)
            l.run();
    }

}
