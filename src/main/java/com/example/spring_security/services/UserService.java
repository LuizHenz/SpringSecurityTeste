package com.example.spring_security.services;

import com.example.spring_security.entity.User;
import com.example.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user != null){
            return (UserDetails) user;
        }

        throw new UsernameNotFoundException("Não encontrado.");
    }


    public String registrar(User user) throws Exception {
        User userNameBanco = userRepository.findByUsername(user.getUsername());
            if(!userNameBanco.getUsername().equals(user.getUsername())){
                User userNew = new User();
                userNew.setId(user.getId());
                userNew.setUsername(user.getUsername());
                userNew.setPassword(passwordEncoder.encode(user.getPassword()));
                userNew.setRole(user.getRole());

                userRepository.save(userNew);

                return "Usuário salvo com sucesso.";
            }else{

                throw new Exception("Usuario já existe.");
            }

    }

}
