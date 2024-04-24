//package org.example.springbootdev.config;
//
//import lombok.RequiredArgsConstructor;
//import org.example.springbootdev.service.UserDetailService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
////인증을 위한 도메인과 리포지터리, 서비스가 완성된 후 실제 인증 처리를 하는 시큐리티 설정 파일
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//    private final UserDetailService userService;
//
//    //1. 스프링 시큐리티 기능 비활성화, 인증-인가 서비스를 모든곳에 적용하지 않는다
//    // 일반적으로 정적 리소스(이미지,html 파일)에 설정한다. 정적 리소스만 스프링 시큐리티 사용을 비활성화 하는 데 static 하위 경로에 있는 리소스와
//    //h2의 데이터를 확인하는 데 사용하는 h2-console 하위 url 대상으로 ignoring() 메서드 사용한다.
//    @Bean
//    public WebSecurityCustomizer configure(){
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers(new AntPathRequestMatcher("/static/**"));
//
//    }
//
//    //2.특정 http 요청에 대한 웹 기반 보안을 구성한다. 이 메서드에서 인증/ 인가 및 로그인, 로그아웃 관련 설정을 할 수 있다.
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests(auth -> auth //3.인증, 인가 설정 , 특정 경로에 대한 액세스 설정
//                        .requestMatchers( //특정 요청과 일치하는 url에 대한 액세스를 설정한다.
//                                new AntPathRequestMatcher("/login"),
//                                new AntPathRequestMatcher("/signup"),
//                                new AntPathRequestMatcher("/user")
//                        ).permitAll() // 누구나 접근이 가능하게 설정한다. 즉, login/ signup/ user로 요청이 오면 인증/ 인가없이도 접근할 수 있다.
//                        .anyRequest().authenticated()) //anyRequest()위에 설정한 url 이외의 요청에 대해 설정,
//                                                    // authenticated())별도의 인가는 필요하지 않지만 인증이 성공된 상태여야 접근할 수 있다.
//                .formLogin(formLogin -> formLogin //4. 폼 기반 로그인 설정
//                        .loginPage("/login") //로그인 페이지 경로를 설정한다
//                        .defaultSuccessUrl("/articles") //로그인이 완료되었을때 이동할 경로를 설정한다.
//                )
//                .logout(logout -> logout  //5. 로그아웃 설정
//                        .logoutSuccessUrl("/login")  //로그아웃이 완료되었을때 이동할 경로를 설정한다.
//                        .invalidateHttpSession(true) //로그아웃 이후에 세션을 전체 삭제할 지 여부를 설정한다.
//                )
//                .csrf(AbstractHttpConfigurer::disable) // 6. csrf 비활성화 , csrf 공격을 방지하기 위해 활성화하는게 좋다. 실습을 위해 비활성화.
//                .build();
//    }
//
//    //7. 인증 관리자 관련 설정, 사용자 정보를 가져올 서비스를 재정의 하거나, 인증 방법, 예를들어 LDAP, JDBC 기반 인증 설정할 때 사용
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http,
//                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
//                                                       UserDetailService userDetailService)
//            throws Exception {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);//8.사용자 정보 서비스 설정
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder); // 비밀번호를 암호화하기 위한 인코더 설정
//        return new ProviderManager(authProvider);
//    }
//
//    //9. 패스워드 인코더로 사용할 빈 등록
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
