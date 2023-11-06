package io.github.vshnv.nortviz.filter;

import io.github.vshnv.nortviz.auth.BearerTokenExtractor;
import io.github.vshnv.nortviz.auth.jwt.JwtValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Handles JWT Authentication. Validates JWT Token and assigns Authentication claims for request
 */
@Qualifier("JwtFilter")
@Component
public final class JwtAuthenticatingFilter extends GenericFilterBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticatingFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtValidator validator;
    private final BearerTokenExtractor tokenExtractor;
    private final UserDetailsService detailsService;


    @Autowired
    public JwtAuthenticatingFilter(final JwtValidator validator, final BearerTokenExtractor tokenExtractor, @Qualifier("Nortviz") final UserDetailsService detailsService) {
        this.validator = validator;
        this.tokenExtractor = tokenExtractor;
        this.detailsService = detailsService;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            final String authValue = ((HttpServletRequest) request).getHeader(AUTHORIZATION_HEADER);
            // Extract JWT Token -> Fetch username claim -> load User with username -> create Spring AuthToken with user details
            tokenExtractor.extractToken(authValue)
                    .flatMap(validator::getUsernameIfValid)
                    .map(detailsService::loadUserByUsername)
                    .map(JwtAuthenticatingFilter::createAuthToken)
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        }
        chain.doFilter(request, response);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    @ResponseStatus (
            value = HttpStatus.FORBIDDEN,
            reason = "Failed to authenticate."
    )
    public void handleNonExistentUserLoginAttempt(final UsernameNotFoundException exception) {
        LOGGER.info("Received valid token from non-existent user: " + exception.getMessage(), exception.getCause());
    }

    private static UsernamePasswordAuthenticationToken createAuthToken(final UserDetails details) {
        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }
}