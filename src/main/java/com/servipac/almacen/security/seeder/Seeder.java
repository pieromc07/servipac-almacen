package com.servipac.almacen.security.seeder;

import com.servipac.almacen.persistence.model.Role;
import com.servipac.almacen.persistence.model.User;
import com.servipac.almacen.persistence.repository.RoleRepository;
import com.servipac.almacen.persistence.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Log
@Configuration
public class Seeder {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

  @Bean
    CommandLineRunner initDatabase() {

      return args -> {
            log.info("Seeding database...");
            roles();
            log.info("Seeding database... Roles Done");
            users();
            log.info("Seeding database... Users Done");
            log.info("Database seeded.");
        };
    }



    public void users() {
        if(userRepository.count() == 0) {
            userRepository.saveAll(
                    List.of(
                            User.builder().username("pieromc".toUpperCase())
                                    .password(passwordEncoder.encode("abcd1234#"))
                                    .email("pieromc@gmail.com")
                                    .status(true)
                                    .role(roleRepository.findByName("ROLE_ADMIN"))
                                    .build(),
                            User.builder().username("eliastg".toUpperCase())
                                    .password(passwordEncoder.encode("abcd1234#"))
                                    .email("eliastg@gmail.com")
                                    .status(true)
                                    .role(roleRepository.findByName("ROLE_ALMACEN"))
                                    .build()
                    )
            );
        }
    }

    public void roles() {
        if(roleRepository.count() == 0) {
            roleRepository.saveAll(
                    List.of(
                            Role.builder().name("ROLE_ADMIN").description("ADMIN").state(true).build(),
                            Role.builder().name("ROLE_ALMACEN").description("ALMACEN").state(true).build()
                    )
            );
        }
    }

}
