package com.issues;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Optional;

/**
 * Recommended to change default Idea template for equals/hashCode/toString generation.
 * At minimum, we are expecting toString for all domain classes. Reflection methods are quite good due to they will
 * automatically pick up new fields (e.g. you can generate them and forget).
 */
public class User {
    private List<Permissions> permissions;

    private Optional<User> parent;

    private int id;

    private User(Builder builder) {
        setPermissions(builder.permissions);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Optional<User> getParent() {
        return parent;
    }

    public void setParent(Optional<User> parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
       return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final class Builder {
        private List<Permissions> permissions;
        private int id;

        private Builder() {
        }

        public Builder withPermissions(List<Permissions> val) {
            permissions = val;
            return this;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
