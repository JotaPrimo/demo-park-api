package com.mballem.demoparkapi.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SprinJpaAuditingConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // pegando nome do usuario logado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return Optional.of(authentication.getName());
        }
        return null;
    }
}
