package com.softserveinc.ita.homeproject.homeservice.service.impl;

import com.softserveinc.ita.homeproject.homedata.entity.User;
import com.softserveinc.ita.homeproject.homedata.repository.RoleRepository;
import com.softserveinc.ita.homeproject.homedata.repository.UserRepository;
import com.softserveinc.ita.homeproject.homeservice.mapper.EntityMapper;
import com.softserveinc.ita.homeproject.homeservice.service.UserService;
import com.softserveinc.ita.homeproject.homeservice.dto.UserDto;
import com.softserveinc.ita.homeproject.homeservice.exception.AlreadyExistHomeException;
import com.softserveinc.ita.homeproject.homeservice.exception.NotFoundHomeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

import static com.softserveinc.ita.homeproject.homeservice.constants.Roles.USER_ROLE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityMapper mapper;

    @Transactional
    @Override
    public UserDto createUser(UserDto createUserDto) {
        if (userRepository.findByEmail(createUserDto.getEmail()).isPresent()) {
            throw new AlreadyExistHomeException("User with email" + createUserDto.getEmail() + " is already exists");
        } else {
            User toCreate =  mapper.convertToEntity(createUserDto, User.class);
            toCreate.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
            toCreate.setEnabled(true);
            toCreate.setExpired(false);
            toCreate.setRoles(Set.of(roleRepository.findByName(USER_ROLE)));
            toCreate.setCreateDate(LocalDateTime.now());

            userRepository.save(toCreate);

            return mapper.convertToDto(toCreate, UserDto.class);
        }
    }

    @Override
    public UserDto updateUser(Long id, UserDto updateUserDto) {
        if (userRepository.findById(id).isPresent()) {

            User fromDB = userRepository.findById(id).get();

            if (updateUserDto.getFirstName() != null) {
                fromDB.setFirstName(updateUserDto.getFirstName());
            }

            if (updateUserDto.getLastName() != null) {
                fromDB.setLastName(updateUserDto.getLastName());
            }

            if (updateUserDto.getContacts() != null) {
                fromDB.setContacts(updateUserDto.getContacts());
            }

            fromDB.setUpdateDate(LocalDateTime.now());
            userRepository.save(fromDB);
            return mapper.convertToDto(fromDB, UserDto.class);

        } else {
            throw new NotFoundHomeException("User with id:" + id + " is not found");
        }
    }

    @Override
    public Page<UserDto> getAllUsers(Integer pageNumber, Integer pageSize) {
        return userRepository.findAllByEnabledTrue(PageRequest.of(pageNumber - 1, pageSize))
                .map((users) -> mapper.convertToDto(users, UserDto.class));
    }

    @Override
    public UserDto getUserById(Long id) {
        User toGet = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundHomeException("User with id:" + id + " is not found"));
        return mapper.convertToDto(toGet, UserDto.class);
    }

    @Override
    public void deactivateUser(Long id) {
        User toDelete = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundHomeException("User with id:" + id + " is not found"));
        toDelete.setEnabled(false);
        userRepository.save(toDelete);
    }

}
