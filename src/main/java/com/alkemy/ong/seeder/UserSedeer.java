package com.alkemy.ong.seeder;

import com.alkemy.ong.entity.RoleEntity;
import com.alkemy.ong.entity.UserEntity;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.utils.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserSedeer implements CommandLineRunner {

    private static final List<String> NAMES_ADMIN = List.of("Cristian", "Lautaro", "Luciano", "Juan", "Admin", "Kevin", "Santiago", "Antonella", "Arai", "Micaela");
    private static final List<String> LAST_NAMES_ADMIN = List.of("Monzon", "Furiasse", "Toledo", "Lopez", "Admin", "Pogonza", "Ibañez", "Castillo", "Gonzalez", "Perez");
    private static final List<String> EMAILS_ADMIN = List.of("cristian@gmail.com", "lautaro@gmail.com", "luciano@gmail.com", "admin@gmail.com",
            "mno123@gmail.com", "pqr123@gmail.com", "stu123@gmail.com", "vwy123@gmail.com", "zxc123@gmail.com", "asd123@gmail.com");
    private static final List<String> PASSWORDS_ADMIN = List.of("12345678", "12345678", "12345678", "12345678", "55555555", "66666666", "7777777", "88888888", "9999999", "123456789");
    private static final List<String> NAMES_USER = List.of("Cecilia", "Solange", "Laila", "Natalia", "Debora", "Jaquelin", "Ramona", "Jonatan", "David", "Nicolas");
    private static final List<String> LAST_NAMES_USER = List.of("Barrera", "Gimenez", "Paredes", "Estebanez", "Reyes", "Casco", "Martinez", "Soria", "Flores", "Ramirez");
    private static final List<String> EMAILS_USER = List.of("user@gmail.com", "rty123@gmail.com", "uio123@gmail.com", "pas123@gmail.com",
            "dfg123@gmail.com", "hjk123@gmail.com", "klñ123@gmail.com", "zas123@gmail.com", "xcv123@gmail.com", "bnm123@gmail.com");
    private static final List<String> PASSWORDS_USER = List.of("11111111", "2222222", "33333333",
            "44444444", "55555555", "66666666", "7777777", "88888888", "9999999", "123456789");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedUserTable();
    }

    private void seedUserTable() {
        if (userRepository.count() == 0) {
            createAdminUsers();
            createStandardUsers();
        }
    }

    private void createAdminUsers() {
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleType.ADMIN.getFullRoleName()));
        roles.add(roleRepository.findByName(RoleType.USER.getFullRoleName()));

        for (int index = 0; index < 10; index++) {
            createUser(NAMES_ADMIN.get(index),
                    LAST_NAMES_ADMIN.get(index),
                    EMAILS_ADMIN.get(index),
                    PASSWORDS_ADMIN.get(index),
                    roles);
        }
    }

    private void createStandardUsers() {
        List<RoleEntity> roleUser = Collections.singletonList(
                roleRepository.findByName(RoleType.USER.getFullRoleName()));

        for (int index = 0; index < 10; index++) {
            createUser(NAMES_USER.get(index),
                    LAST_NAMES_USER.get(index),
                    EMAILS_USER.get(index),
                    PASSWORDS_USER.get(index),
                    roleUser);
        }
    }

    private void createUser(String firstName, String lastName, String email, String password,
                            List<RoleEntity> role) {
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoto("image.jpg");
        user.setSoftDelete(false);
        userRepository.save(user);
        user.setRole(role);
        userRepository.save(user);
    }
}
