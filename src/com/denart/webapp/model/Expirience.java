package com.denart.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Expirience {
    private final String name;
    private final String link;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String position;
    private final String info;

    public Expirience(String name, String link, LocalDate startDate, LocalDate endDate, String position, String info) {
        Objects.requireNonNull(name,"name must not be null");
        Objects.requireNonNull(link,"link must not be null");
        Objects.requireNonNull(startDate,"startDate must not be null");
        Objects.requireNonNull(endDate,"endDate must not be null");
        Objects.requireNonNull(position,"position must not be null");
        Objects.requireNonNull(info,"info must not be null");
        this.name = name;
        this.link = link;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPosition() {
        return position;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expirience that = (Expirience) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!link.equals(that.link)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!position.equals(that.position)) return false;
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + link.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + info.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Expirience{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", position='" + position + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
