package com.denart.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListTextSection<T> extends AbstractSection {

    private final List<T> expiriences;

    protected ListTextSection(String info, List<T> expiriences) {
        super(info);
        Objects.requireNonNull(expiriences, "expiriences must not be null");
        this.expiriences = expiriences;
    }

    public List<T> getExpiriences() {
        return expiriences;
    }
}
