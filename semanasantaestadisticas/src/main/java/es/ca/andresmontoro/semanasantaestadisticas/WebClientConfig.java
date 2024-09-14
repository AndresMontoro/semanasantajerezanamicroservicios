package es.ca.andresmontoro.semanasantaestadisticas;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  @LoadBalanced
  // Returns builder cause it's a diferent config for each service
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder(); 
  }

}
