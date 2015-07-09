package crossover.social.media.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * RoleName
 * Created by bazzoni on 09/07/2015.
 */
public enum RoleName {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_VIEWER("ROLE_VIEWER"),
    ROLE_AUTHOR("ROLE_AUTHOR"),
    ROLE_MANAGER("ROLE_MANAGER");

    public static final RoleName[] ALL = {ROLE_USER, ROLE_ADMIN, ROLE_MANAGER, ROLE_AUTHOR, ROLE_VIEWER};

    private final String name;

    RoleName(final String name) {
        this.name = name;
    }

    @JsonCreator
    public static RoleName forName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for role");
        }
        if (name.toUpperCase().equals("ROLE_USER")) {
            return ROLE_USER;
        } else if (name.toUpperCase().equals("ROLE_ADMIN")) {
            return ROLE_ADMIN;
        } else if (name.toUpperCase().equals("ROLE_VIEWER")) {
            return ROLE_VIEWER;
        } else if (name.toUpperCase().equals("ROLE_AUTHOR")) {
            return ROLE_AUTHOR;
        } else if (name.toUpperCase().equals("ROLE_MANAGER")) {
            return ROLE_MANAGER;
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
