package com.tutorial.tutorial_userregister.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity        //spring security 를 적용한다는 Annotation
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        System.out.println("userDetailsService");

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(request ->
                request.requestMatchers("/", "/login/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login/login")
                                .usernameParameter("userid")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login-process")//서비스로직으로 연결 (유효성 검사 등)
                                .defaultSuccessUrl("/login/dashboard",true)
                                .failureUrl("/login/fail")
                                .permitAll()
                )
                .logout(Customizer.withDefaults());
                //.userDetailsService(userDetailsService(passwordEncoder()));
        return http.build();
    }

    // ============ 회원가입 하니까 로그인 페이지로 감---> Html 메소드 주소 잘못되어서 / 회원 가입 데이터 전송 주소 수정!!!
    //===================== 왜 문제 이였는지 분석
    //=======회원 가입 할때 (SignUp ) UserRequestDto 가 null값 줌

    //리다이렉션이 너무 많을때 -> 무한 루프 완성! , 해당 주소 api가 없어서

    //잡다한 실수 -> RequestBody 안씀 , static 으로 하면 빈 등록 안됨 final을 써야지

    //======================= 로그인 시 항상 실패
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception
    {

    }

    {
            /*
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        // ========== 이것때문에 페이지 텍스트만 뜨는 오류
        // ========== 인증 문제는 아닌거 같은데


        // 링크/* -> 바로 아래있는 경로만 ,  링크/** -> 모든 하위 경로
        http.csrf(t -> t.disable());
        http.authorizeHttpRequests((request) -> //요청에 의한 보안 검사
                request.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()//Resource 접근허용
                        .requestMatchers("/api/user/**", "/view/**", "/*").permitAll()//해당 링크 요청 허가
                        .anyRequest().authenticated()// 그외 요청 인증 요구
                )
                .formLogin(login -> login
                        .loginPage("/view/login")	// [A] 커스텀 로그인 페이지 지정

                        .loginProcessingUrl("/view/login-process")	// [B] submit 받을 url
                        .usernameParameter("userid")	// [C] submit할 아이디
                        .passwordParameter("pw")	// [D] submit할 비밀번호
                        .defaultSuccessUrl("/view/dashboard", true)
                        //.failureUrl("/")

                        .permitAll()
                )
                .logout(logout -> logout.permitAll());


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("userid")
                        .password("pw")
                        .roles("User")
                        .build();

        return  new InMemoryUserDetailsManager(user);
    }
    */
            /*
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/user/availability/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();*///legacy
    }



}