package com.example.reactiverestapp.initialiser;

import com.example.reactiverestapp.entity.Department;
import com.example.reactiverestapp.entity.User;
import com.example.reactiverestapp.repository.DepartmentRepository;
import com.example.reactiverestapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Component
@Profile("!test")
public class UserInitialiser implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger( UserInitialiser.class.getName() );

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) {
        initialDataSetup();
    }

    private List<User> getData(){
        return Arrays.asList(new User("Suman Das",30,10000),
                new User("Arjun Das",5,1000),
                new User("Saurabh Ganguly",40,1000000));
    }

    private List<Department> getDepartments(){
        return Arrays.asList(new Department("Mechanical",1,"Mumbai"),
                new Department("Computer",2,"Bangalore"));
    }

    private void initialDataSetup() {
        userRepository.deleteAll()
                .thenMany(Flux.fromIterable(getData()))
                .flatMap(userRepository::save)
                .thenMany(userRepository.findAll())
                .subscribe(user -> {
                    LOGGER.info("User Inserted from CommandLineRunner " + user);
                });

        departmentRepository.deleteAll()
                .thenMany(Flux.fromIterable(getDepartments()))
                .flatMap(departmentRepository::save)
                .thenMany(departmentRepository.findAll())
                .subscribe(user -> {
                    LOGGER.info("Department Inserted from CommandLineRunner " + user);
                });

    }

}