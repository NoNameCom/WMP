package web.mentor.ru.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.filter.CharacterEncodingFilter;
import web.mentor.ru.configuration.handler.CustomAuthenticationSuccessHandler;
import web.mentor.ru.configuration.service.AuthenticationService;
import web.mentor.ru.util.SettingsUtil;

import javax.sql.DataSource;

@Configuration
@ComponentScan("web.mentor.ru")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final String days = new SettingsUtil().getProperty("remember-me.days");

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService);
    }

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.authorizeRequests()
                .antMatchers("/registration", "/").permitAll()
//                .antMatchers("/user/**").hasAnyAuthority("USER", "STUDENT", "MENTOR", "NO_NAME")
//                .antMatchers("/admin/**").hasAnyAuthority("Admin")
//                .antMatchers("/").hasAnyAuthority("NO_NAME")
                .and().formLogin().loginPage("/").successHandler(successHandler)
                .usernameParameter("login").passwordParameter("password")
                .and().rememberMe()
                .rememberMeParameter("remember-me")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(86400 * Integer.valueOf(days))
                .and().exceptionHandling().accessDeniedPage("/access_denied");

        http.authorizeRequests().antMatchers(
                "/resources/**"
        ).permitAll().antMatchers("/**").authenticated();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}