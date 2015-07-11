package crossover.social.media.plugin.search;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * SearchIndex
 * Created by bazzoni on 11/07/2015.
 */
public enum SearchIndex {
    CONTENT("CONTENT"), CUSTOMER("CUSTOMER"), USER("USER");

    public static final SearchIndex[] ALL = {CONTENT, CUSTOMER, USER};

    private final String name;

    SearchIndex(final String name) {
        this.name = name;
    }

    @JsonCreator
    public static SearchIndex forName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for role");
        }
        if (name.toUpperCase().equals("CONTENT")) {
            return CONTENT;
        } else if (name.toUpperCase().equals("CUSTOMER")) {
            return CUSTOMER;
        } else if (name.toUpperCase().equals("USER")) {
            return USER;
        }
        throw new IllegalArgumentException("Name \"" + name + "\" does not correspond to any Feature");
    }

    public String getName() {
        return this.name;
    }

    @JsonValue
    @Override
    public String toString() {
        return getName();
    }
}
