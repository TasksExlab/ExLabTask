package team.exlab.tasks.model.enam;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    PM,
    LEAD,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
