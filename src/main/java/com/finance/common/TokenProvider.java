// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// import java.util.Date;

// public class TokenProvider {

//     private static final String SECRET_KEY = "yourSecretKey"; // 실제로는 보안상 안전한 방법으로 관리되어야 합니다.
//     private static final long EXPIRATION_TIME = 30 * 60 * 1000; // 30분

//     public static String generateToken(String userId) {
//         Date now = new Date();
//         Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

//         return Jwts.builder()
//                 .setSubject(userId)
//                 .setIssuedAt(now)
//                 .setExpiration(expiration)
//                 .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                 .compact();
//     }
// }
