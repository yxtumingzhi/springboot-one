package com.hope.one.common;

import java.io.Serializable;

public class Pair<F, S> implements Serializable {
    private F first;
    private S second;

    public Pair() {
    }

    public Pair(F first) {
        this.set(first,  null);
    }

    public Pair(F first, S second) {
        this.set(first, second);
    }

    public Pair(Pair<F, S> other) {
        this.set(other.first, other.second);
    }

    public F getFirst() {
        return this.first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return this.second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public void set(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Pair) {
            Pair<?, ?> other = (Pair) obj;
            if (this.first == other.first && this.second == other.second) {
                return true;
            } else if (this.first != null && !this.first.equals(other.first)) {
                return false;
            } else if (this.first == null && other.first != null) {
                return false;
            } else if (this.second != null && !this.second.equals(other.second)) {
                return false;
            } else {
                return this.second != null || other.second == null;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (this.first != null ? this.first.hashCode() : 0) ^ (this.second != null ? this.second.hashCode() : 0);
    }

    public String toString() {
        return String.format("{%s, %s}", this.first, this.second);
    }
}
