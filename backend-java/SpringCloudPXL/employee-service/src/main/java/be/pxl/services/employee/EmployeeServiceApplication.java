package be.pxl.services.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * EmployeeServiceApplication
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }
}
