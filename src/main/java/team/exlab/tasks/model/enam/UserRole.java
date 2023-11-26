package team.exlab.tasks.model.enam;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    PM,
    LEAD,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }

    public static Optional<UserRole> find(String name) {
        return Arrays.stream(UserRole.values())
                .filter(role -> role.name().equals(name))
                .findFirst();
    }

    public String toString() {
        return this.name();
    }
}
