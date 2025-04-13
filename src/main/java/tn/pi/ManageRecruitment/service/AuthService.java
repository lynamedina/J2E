package tn.pi.ManageRecruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.pi.ManageRecruitment.model.Role;
import tn.pi.ManageRecruitment.model.User;
import tn.pi.ManageRecruitment.repository.RoleRepository;
import tn.pi.ManageRecruitment.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User register(String name, String email, String password, String roleName) throws Exception {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Email déjà utilisé");
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new Exception("Rôle non trouvé"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // En production, hachez le mot de passe
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User login(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Utilisateur non trouvé"));

        if (!user.getPassword().equals(password)) {
            throw new Exception("Mot de passe incorrect");
        }

        return user;
    }
}