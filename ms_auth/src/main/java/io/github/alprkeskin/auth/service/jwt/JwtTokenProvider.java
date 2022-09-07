package io.github.alprkeskin.auth.service.jwt;

import io.github.alprkeskin.auth.properties.JwtProperties;
import io.github.alprkeskin.common.model.role.AuthorityRole;
import io.github.alprkeskin.common.model.role.GrantedAuthorityRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import static java.time.temporal.ChronoField.MILLI_OF_DAY;
import static java.util.stream.Collectors.joining;

@Service
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "roles";

    @Autowired
    private JwtProperties props;

    private SecretKey secretKey;

    @PostConstruct
    private void afterInit() {
        String secret = Base64.getEncoder().encodeToString(props.getSecretKey().getBytes());
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Authentication authentication)
    {
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Claims claims = Jwts.claims().setSubject(username);
        if (!authorities.isEmpty())
            claims.put(AUTHORITIES_KEY, authorities.stream().map(GrantedAuthority::getAuthority).collect(joining(",")));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validity = now.plus(props.getValidityInMs(), MILLI_OF_DAY.getBaseUnit());

        return Jwts.builder().setClaims(claims).setIssuedAt(convertDate(now)).setExpiration(convertDate(validity))
                .signWith(secretKey, SignatureAlgorithm.HS256).compact();

    }

    public Authentication getAuthentication(String token)
    {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();

        Object authoritiesClaim = claims.get(AUTHORITIES_KEY);

        String email = claims.get("sub", String.class);
        Collection<? extends GrantedAuthority> authorities = authoritiesClaim == null
                ? AuthorityUtils.NO_AUTHORITIES
                : AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString());

        return new UsernamePasswordAuthenticationToken(email, token, authorities);
    }

    public AuthorityRole getAuthorityRole(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        String role = claims.get(AUTHORITIES_KEY, String.class);

        return GrantedAuthorityRole.getAuthorityRole(role);
    }

    public boolean validateToken(String token)
    {
        try
        {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }
        catch (Throwable t)
        {
            return false;
        }

    }

    private Date convertDate(LocalDateTime local)
    {
        return Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
    }

}
