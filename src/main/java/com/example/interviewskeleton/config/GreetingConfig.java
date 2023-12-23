package com.example.interviewskeleton.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "greeting")
public class GreetingConfig {

    private Map<String, String> morning;
    private Map<String, String> afternoon;
    private Map<String, String> evening;

    public Map<String, String> getMorning() {
        return morning;
    }

    public void setMorning(Map<String, String> morning) {
        this.morning = morning;
    }

    public Map<String, String> getAfternoon() {

        return afternoon;
    }

    public void setAfternoon(Map<String, String> afternoon) {
        this.afternoon = afternoon;
    }
    public Map<String, String> getEvening() {
        return evening;
    }
    public void setEvening(Map<String, String> evening) {
        this.evening = evening;
    }
}
