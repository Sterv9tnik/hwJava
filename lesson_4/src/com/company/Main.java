package com.company;

import Entities.User;
import Services.UserService;
import Services.impl.UserServiceImpl;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<User> Users = new ArrayList<>();
        Users.add(new User("1","Sasha","Ivanov","Sergeev","8999123","alo@ya.ry"));
        Users.add(new User("2","Andrey","Pupin","Antonov","893412345","AAP@gmail.com"));

        UserRepository userRepository = new UserRepositoryImpl(Users);
        System.out.println(userRepository.getAll());

        User sasha = new User("1","Sasha","Ivanov","Sergeev","8999123","alo@ya.ru");
        userRepository.save(sasha);
        System.out.println(userRepository.getAll());

        User masha = new User("5","Masha","Rogova","Vatalievna","123145","MRV@lol.ru");
        userRepository.save(masha);
        System.out.println(userRepository.getAll());

        Users.add(new User("3","Nastya","Lupina","Vladimirovna","8123345","NLP@gmail.com"));
        Users.add(new User("4","Ksusha","Vishnevskaya","Vladislavovna","8123345","NLPgnail.ru"));
        System.out.println(userRepository.saveAll(Users));

        System.out.println(userRepository.getBy("2"));
        System.out.println(userRepository.getBy("6"));

        UserService userService = new UserServiceImpl(userRepository);

        System.out.println(userService.getUsers("8123345"));
        System.out.println(userService.getUsers("Masha Rogova Vatalievna"));
        System.out.println(userService.getUsers("alo@ya.ry"));
        System.out.println(userService.getUsers("alo@ya.ru"));

        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("5");
        userService.deleteUsers(ids);

        userService.updateUser("5","Petya","Rogov","Vatalievna","123145","MRV@lol.ru");
        userService.updateUser("2","Petya","Rogov","Vatalievna","123145","MRV@lol.ru");
        System.out.println(userRepository.getAll());

        System.out.println(userService.createUser("Biba","Bobov","Lupov","1231456789","BBL@lol.ru"));
        System.out.println(userRepository.getAll());
    }
}
