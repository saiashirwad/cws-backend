// package ooad.project.cws.security;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Date;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.ServletInputStream;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.User;
// import com.auth0.jwt.JWT;

// import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

// import static ooad.project.cws.security.SecurityConstants.HEADER_STRING;
// import static ooad.project.cws.security.SecurityConstants.TOKEN_PREFIX;
// import static ooad.project.cws.security.SecurityConstants.SECRET;
// import static ooad.project.cws.security.SecurityConstants.EXPIRATION_TIME;

// // import ooad.project.cws.model.User;
// import ooad.project.cws.types.UserRegisterType;

// public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//     private AuthenticationManager authenticationManager;

//     public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
//         this.authenticationManager = authenticationManager;
//     }

//     @Override
//     public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
//         System.out.println("Auth filter");
//         System.out.println(req.getContentLength());
//         try {
//             UserRegisterType creds = new ObjectMapper().readValue(req.getInputStream(), UserRegisterType.class);

//             System.out.println("HERE");
//             return authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                     creds.getName(),
//                     creds.getPassword(),
//                     new ArrayList<>()
//                 )
//             );
//         } catch (IOException e) {
//             System.out.println("HERE IN ZE EKSUPTION");
//             throw new RuntimeException(e);
//         }
//     }

//     @Override
//     protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {

//         String token = JWT.create()
//                         .withSubject(((User) auth.getPrincipal()).getUsername())
//                         .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                         .sign(HMAC512(SECRET.getBytes()));
//         res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
//     }
// }