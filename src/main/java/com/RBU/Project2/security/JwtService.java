	package com.RBU.Project2.security;
	
	import java.util.Date;
	
	import javax.crypto.SecretKey;
	
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Component;
	
	import io.jsonwebtoken.Jwts;
	import io.jsonwebtoken.security.Keys;
	import io.jsonwebtoken.Claims;
	
	
	@Component
	public class JwtService {
		private final SecretKey key;
		private final long expirationTime;
		
		public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long expiration) {
		
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.expirationTime = expiration;
		}
		
		public String generateToken(String email, Long userId) {
			return Jwts.builder()
					.subject(email)
					.claim("userId", userId)
					.issuedAt(new Date())
					.expiration(new Date(System.currentTimeMillis()+expirationTime))
					.signWith(key).compact();
		}
		
		public String extractEmail(String token) {
			return getClaims(token).getSubject();
		}
		
		public Long extractUserId(String token) {
			return getClaims(token).get("userId",Long.class);
		}
		
		private Claims getClaims(String token) {
			return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
			
		}
		
		public boolean isTokenValid(String token) {
			try {
				return getClaims(token).getExpiration().after(new Date());
			}
			catch (Exception e){
				return false;
				
			}
		}
	}
