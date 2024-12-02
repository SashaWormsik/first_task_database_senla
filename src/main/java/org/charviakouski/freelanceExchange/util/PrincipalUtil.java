package org.charviakouski.freelanceExchange.util;


import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.entity.security.CredentialUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
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
