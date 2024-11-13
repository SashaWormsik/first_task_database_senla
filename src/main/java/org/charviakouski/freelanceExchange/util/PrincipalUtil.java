package org.charviakouski.freelanceExchange.util;


import org.charviakouski.freelanceExchange.model.entity.security.CredentialUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class PrincipalUtil {
    public Long getCurrentUserId() {
        CredentialUserDetails user = (CredentialUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return user.getId();
    }

    public boolean checkId(Long id) {
        return id.equals(getCurrentUserId());
    }
}
