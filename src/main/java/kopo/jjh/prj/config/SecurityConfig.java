package kopo.jjh.prj.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       //메모리에 관리자권한 아이디 root/root 부여
        auth.inMemoryAuthentication()
                .withUser("root").password(passwordEncoder().encode("root")).roles("admin");
//        String password = passwordEncoder().encode("1111");
//
//        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
//        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER");
//        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
//        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Override
    // js, css, image 설정은 보안 설정의 영향 밖에 있도록 만들어주는 설정.
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    // BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체입니다.
    // Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
          .csrf().disable()

                .headers()

                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()



                .antMatchers("/","/loginUser","/passupdate","/list",
                        "/user/login/login-form","/user/login/callback",
                        "/naverlogin","/Infolist","/culture","/find_id_form","/find_id","/member/find_id.html",
                        "/covid","/air","/chinese","/mails","/verifyCode"
                        ,"/check/findPw","/check/findPw/sendEmail","/member/findpw"
                        ,"/mailCheck","/idCheck","/emailCheck","/newss","/exchange","/mailCheck1"
                      ,"/ws","/book","/chat/**","/find_id_form1" ,"/user/**" ,"/find_password_form1","/comment/update"
            ,"/exchange","/travel","/news","/Crawl","keywords","/find**","/rule","/comment/list","/comment/insert","/comment/delete/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .loginPage("/login")                    // controller mapping
                .loginProcessingUrl("/login_proc")      // th:action="@{/login_proc}"
                .defaultSuccessUrl("/")

                .permitAll();

    }


}