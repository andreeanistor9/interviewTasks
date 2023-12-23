package com.example.interviewskeleton.controller;

import com.example.interviewskeleton.config.GreetingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Map;

@RestController
public class GreetingController {
    @Autowired
    private GreetingConfig greetingConfig;

    @GetMapping("/greet/{name}")
    public String getGreeting(
            @PathVariable String name,
            @RequestParam(defaultValue = "en") String locale
    ) {
        String timeOfDay = getTimeOfDay();
        Map<String, String> greetings;

        switch (timeOfDay) {
            case "morning":
                greetings = greetingConfig.getMorning();
                break;
            case "afternoon":
                greetings = greetingConfig.getAfternoon();
                break;
            case "evening":
                greetings = greetingConfig.getEvening();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + timeOfDay);
        }

        String greetingMessage = greetings.get(locale);
        return greetingMessage.replace("{name}", name);
    }

    private String getTimeOfDay() {
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.NOON)) {
            return "morning";
        } else if (now.isBefore(LocalTime.of(17, 0))) {
            return "afternoon";
        } else {
            return "evening";
        }
    }
    /** I tryed to implement the bonus task but I did not find hasHeader for requestMatchers() method;
     * authorizeRequests() is deprecated so now we have to use authorizeHttpRequests and
     * antMatchers() is also deprecated so now we have to use requestMatchers() which does not have a hasHeader() method.
     * Also if I want to implement security, i introduced this depencdency in pom.xml but I deleted because I did not
     * succeed to finish the bonus task, so the dependency is this:
     **** <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-security</artifactId>
     * </dependency> ********
     and this is the code about I talked above.
     *  @Bean
    public UserDetailsService userDetailsService() {
    return username -> {
    throw new UsernameNotFoundException(username);
    };
    }

     @EnableWebSecurity
     public class WebSecurityConfig {

     @Autowired
     private UserDetailsService userDetailsService;

     @Autowired
     private CustomAuthenticationFilter customAuthenticationFilter;

     protected void configure(HttpSecurity http) throws Exception {
     http
     .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
     .authorizeRequests(authorize -> authorize
     .antMatchers("/greet/**").hasHeader("X-Auth-Token", "such-secure-much-wow")
     .anyRequest().permitAll()
     )
     .csrf().disable();
     }
     }**/
}
