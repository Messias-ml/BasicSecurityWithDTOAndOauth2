package com.messiasprojetos.messiasBasicSecurity.seguranca_oauth2;

import com.messiasprojetos.messiasBasicSecurity.usuario.UsuarioRepository;
import com.messiasprojetos.messiasBasicSecurity.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*CONFIGURAÇÃO PARA HABILITAR OAUTH2
@EnableAuthorizationServer
@EnableResourceServer*/
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UsuarioService usuarioService = new UsuarioService(repository, passwordEncoder());
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/usuarios/buscar").hasRole("ADMIN")
                .antMatchers("/usuarios**").permitAll()
                .antMatchers("/cliente").authenticated()
                .antMatchers("/**").authenticated()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "configuration/security",
                "/h2-console/**"
        );
    }

    /**AUTHENTICATION IN MEMORY(AUTENTICAÇÃO EM MEMORIA)**/

 /*       @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            UserDetails user = User.builder()
                    .username("senhor&senhoraTI")
                    .password(passwordEncoder().encode("devML"))
                    .roles("ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(user);
        }*/

}
