
package br.com.service.project.authentication.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    @Value("${projeto.jwtSecret}")
    private String jwtSecret;
    @Value("${projeto.jwtExpirationMs}")
    private int jwtExpirationMs;

    public JwtUtils() {
    }

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetail) {
        return ((JwtBuilder)((JwtBuilder)((JwtBuilder)Jwts.builder().setSubject(userDetail.getUsername())).setIssuedAt(new Date())).setExpiration(new Date((new Date()).getTime() + (long)this.jwtExpirationMs))).signWith(this.getSigninKey(), SignatureAlgorithm.HS512).compact();
    }

    public Key getSigninKey() {
        SecretKey key = Keys.hmacShaKeyFor((byte[])Decoders.BASE64.decode(this.jwtSecret));
        return key;
    }

    public String getUsernameToken(String token) {
        return ((Claims)Jwts.parserBuilder().setSigningKey(this.getSigninKey()).build().parseClaimsJws(token).getBody()).getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(this.getSigninKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException var3) {
            MalformedJwtException e = var3;
            System.out.println("Token inválido " + e.getMessage());
        } catch (ExpiredJwtException var4) {
            ExpiredJwtException e = var4;
            System.out.println("Token expirado " + e.getMessage());
        } catch (UnsupportedJwtException var5) {
            UnsupportedJwtException e = var5;
            System.out.println("Token não suportado " + e.getMessage());
        } catch (IllegalArgumentException var6) {
            IllegalArgumentException e = var6;
            System.out.println("Token argumento inválido " + e.getMessage());
        }

        return false;
    }
}
