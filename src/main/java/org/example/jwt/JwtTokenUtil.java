//package org.example.jwt;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.impl.TextCodec;
//import org.example.model.Role;
//
//import org.springframework.stereotype.Service;
//
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Service
//public class JwtTokenUtil {
//    private final String SECRET_KEY = "secret";
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    private Claims extractAllClaims(String token) {
////        System.out.println("!!!!!!!!!!!!!!!!!!!!!!\n\n\n\n" + token);
////        System.out.println("!!!!!!!!!!!!!!!!!!!!!!\n\n\n\n" + Jwts.parser().parseClaimsJws(token).getBody().toString());
//          return Jwts.parser()
//
//                  .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
//                  .parseClaimsJws(token)
//                  .getBody();
//    }
//
//    public String extractAuthorities(String token){;
//        return extractClaim(token, claims -> (String) claims.get("authorities"));
//    }
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//    //!!!!!!!!!!!!!!!!!!!
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//
//        claims.put("authorities", userDetails.getAuthorities());
//        System.out.println("в generate token\n" + userDetails.getAuthorities().toString());
//        System.out.println(claims.get("authorities").toString());
//        return createToken(claims, userDetails.getUsername());
//    }
//    private String createToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(
//                        SignatureAlgorithm.HS256,
//                        SECRET_KEY.getBytes(StandardCharsets.UTF_8))
//                .compact();
//    }
//
//    private String signingKeyB64(String key){
//        return Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8));
//    }
//
//}
