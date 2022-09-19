package com.apiamiciback.service;

import com.apiamiciback.model.Role;
import com.apiamiciback.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role getRole(int id){
        Optional<Role> role = roleRepository.findById(id);
        return role.get();
    }
}
