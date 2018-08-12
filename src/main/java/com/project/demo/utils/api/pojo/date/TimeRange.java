package com.project.demo.utils.api.pojo.date;

import java.io.Serializable;

public class TimeRange implements Serializable {

    private ShortTime from;

    private ShortTime to;

    // use by framework
    @Deprecated
    public TimeRange() {
    }

    private TimeRange(ShortTime from, ShortTime to) {
        this.from = from;
        this.to = to;
    }

    public static TimeRange create(ShortTime from, ShortTime to) {
        return new TimeRange(from, to);
    }

    public ShortTime getFrom() {
        return from;
    }

    public ShortTime getTo() {
        return to;
    }

    public void setFrom(ShortTime from) {
        this.from = from;
    }

    public void setTo(ShortTime to) {
        this.to = to;
    }

    public boolean contains(ShortTime shortTime) {
        return shortTime.compareTo(getFrom()) >= 0 && shortTime.compareTo(getTo()) <= 0;
    }

    @Override
    public String toString() {
        return "[" + from + "~" + to + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeRange timeRange = (TimeRange) o;

        if (from != null ? !from.equals(timeRange.from) : timeRange.from != null) return false;
        return to != null ? to.equals(timeRange.to) : timeRange.to == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}