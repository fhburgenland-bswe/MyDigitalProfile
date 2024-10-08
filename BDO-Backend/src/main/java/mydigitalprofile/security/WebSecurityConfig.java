package mydigitalprofile.security;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsServiceService")
    private UserDetailsService customUserDetailsServiceService;

    @Autowired
    @Qualifier("customUserDetailsServiceSecurity")
    private UserDetailsService customUserDetailsServiceSecurity;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Bean
    public UserDetailsService userDetailsService() {
        return this.customUserDetailsServiceService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter authenticationFilter = new JsonUsernamePasswordAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final CorsConfiguration configurationForCors = new CorsConfiguration();
        configurationForCors.applyPermitDefaultValues();
        configurationForCors.setAllowedOrigins(List.of("http://localhost:5432/", "http://localhost:5173/"));
        configurationForCors.setAllowedHeaders(List.of("*"));
        configurationForCors.setExposedHeaders(List.of("*"));
        configurationForCors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configurationForCors.setAllowCredentials(true);

        http.cors().configurationSource(request -> configurationForCors)
                .and().sessionManagement();

        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/teamleader/**").hasRole("TEAMLEADER")
                .antMatchers("/api/user/**").hasRole("USER")
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.formLogin().loginProcessingUrl("/login").permitAll();
        http.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)).permitAll();

        http.addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling()
                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) ->
                        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN))
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_TEAMLEADER > ROLE_USER");
        return roleHierarchy;
    }
}