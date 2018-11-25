// package ooad.project.cws.security;

// import static ooad.project.cws.security.SecurityConstants.SIGN_UP_URL;

// import java.util.Arrays;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.header.writers.StaticHeadersWriter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;

// import ooad.project.cws.service.UserDetailsServiceImpl;

// @Configuration
// @EnableWebSecurity
// public class WebSecurity extends WebSecurityConfigurerAdapter {
//     private UserDetailsServiceImpl userDetailsService;
//     private BCryptPasswordEncoder bCryptPasswordEncoder;

//     public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//         this.userDetailsService = userDetailsService;
//         this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
//         filter.setFilterProcessesUrl("/login");
//         http.cors().disable()
//         .csrf().disable()
//         .authorizeRequests()
//                 .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
//                 .antMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
//                 .antMatchers(HttpMethod.POST, "/api/auth/check").permitAll()
//                 .antMatchers(HttpMethod.POST, "/login").permitAll()
//                 .antMatchers(HttpMethod.OPTIONS, "/login").permitAll()
//                 .antMatchers(HttpMethod.GET, "/api/user").permitAll()
//                 .antMatchers(HttpMethod.GET, "/api/node").permitAll()
//                 .and()
//                 // .antMatchers(HttpMethod.OPTIONS,"/login").permitAll()
//                 // .antMatchers(HttpMethod.POST, "/login").permitAll()
//                 .addFilter(filter)
//                 .addFilter(new JWTAuthorizationFilter(authenticationManager()));
//                 // .anyRequest().authenticated()
//                 // .and()
//                 // // .openidLogin().and()
//                 // .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                 // .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                 // // this disables session creation on Spring Security
//                 // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
           
         
//     }

   

//     @Override
//     public void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//     }

 


// }