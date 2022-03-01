package com.denart.webapp.model;

import java.util.Objects;

public abstract class AbstractSection {
    private final String info;

    protected AbstractSection(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractSection that = (AbstractSection) o;

        return Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return info != null ? info.hashCode() : 0;
    }

}
