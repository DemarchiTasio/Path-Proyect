package com.Manager.pathExamination.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Manager.pathExamination.ServiceImplement.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth.inMemoryAuthentication()
    // .withUser("admin")
    // .password("{noop}1234")
    // .roles("ADMIN","USER")
    // .and()
    // .withUser("user")
    // .password("{noop}1234")
    // .roles("USER")
    // ;

    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/reports", "/students", "/exams", "/dayOfExams", "/institutions", "/certificates","/charts")
                .hasAnyRole("ADMINISTRADOR", "RECLUTADOR", "VENTAS", "ORGANIZADOR", "ENCARGADO")
                .antMatchers("/reports/**", "/reports/**/**")
                .hasAnyRole("VENTAS", "RECLUTADOR", "ADMINISTRADOR")
                .antMatchers( "/institutions/addInstitution","/institutions/deleteInstitution/**")
                .hasAnyRole("VENTAS", "ADMINISTRADOR")
                .antMatchers("/students/load", "/students/**/**", "/exams/**", "/exams/**/**")
                .hasAnyRole("ORGANIZADOR", "ADMINISTRADOR")
                .antMatchers("/dayOfExams/**", "/dayOfExams/**/**")
                .hasAnyRole("ENCARGADO", "ADMINISTRADOR")
                .antMatchers("/certificates/**/**")
                .hasAnyRole("ENCARGADO", "ADMINISTRADOR")
                .antMatchers("/users/**/**")
                .hasAnyRole("ADMINISTRADOR")
                .antMatchers("/institutions/editInstitution/**")
                .hasAnyRole("INSTITUCION","ADMINISTRADOR")
                .antMatchers("/institutions/editInstitution/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/reports")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();
    }

}
