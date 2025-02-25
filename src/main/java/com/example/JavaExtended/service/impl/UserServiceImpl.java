package com.example.JavaExtended.service.impl;

import com.example.JavaExtended.exeption.CommonBackendException;
import com.example.JavaExtended.model.db.entity.User;
import com.example.JavaExtended.model.db.repository.UserRepository;
import com.example.JavaExtended.model.dto.request.UserInfoReq;
import com.example.JavaExtended.model.dto.response.UserInfoResp;
import com.example.JavaExtended.model.enums.UserStatus;
import com.example.JavaExtended.service.UserService;
import com.example.JavaExtended.utils.PaginationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j //для логирования
@Service
@RequiredArgsConstructor //созд. конструктора для инициализации бина
public class UserServiceImpl implements UserService {
    private final ObjectMapper mapper;
    private final UserRepository userRepository;

    @Override
    public UserInfoResp addUser(UserInfoReq req) {

        if (!EmailValidator.getInstance().isValid(req.getEmail())) { //проверка на валидность маила
            throw new CommonBackendException("Email invalid", HttpStatus.BAD_REQUEST);
        }

        userRepository.findByEmail(req.getEmail()).ifPresent(u -> { //проверка нет ли пользователя с таким маилом
            throw new CommonBackendException("User already exists", HttpStatus.CONFLICT);
        });

        User user = mapper.convertValue(req, User.class); //преобразуем запрос в Пользователя-сущность
        user.setStatus(UserStatus.CREATED); //присваиваем статус

        User save = userRepository.save(user); //сохранили в базу данных
        return mapper.convertValue(save, UserInfoResp.class);
    }

    @Override
    public UserInfoResp getUser(Long id) {
      User user = getUserFromDB(id);
      return mapper.convertValue(user, UserInfoResp.class);
    }

    @Override
    public User getUserFromDB(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        final String errMsg = String.format("User with id: %s not found", id);

        return optionalUser.orElseThrow(() -> new CommonBackendException(errMsg, HttpStatus.NOT_FOUND));
    }


    @Override
    public UserInfoResp updateUser(Long id, UserInfoReq req) {
        User userFromDB = getUserFromDB(id);

        User userReq = mapper.convertValue(req, User.class);

        userFromDB.setEmail(userReq.getEmail() == null ? userFromDB.getEmail() : userReq.getEmail());
        userFromDB.setPassword(userReq.getPassword() == null ? userFromDB.getPassword() : userReq.getPassword());
        userFromDB.setFirstName(userReq.getFirstName() == null ? userFromDB.getFirstName() : userReq.getFirstName());
        userFromDB.setLastName(userReq.getLastName() == null ? userFromDB.getLastName() : userReq.getLastName());
        userFromDB.setMiddleName(userReq.getMiddleName() == null ? userFromDB.getMiddleName() : userReq.getMiddleName());
        userFromDB.setAge(userReq.getAge() == null ? userFromDB.getAge() : userReq.getAge());
        userFromDB.setGender(userReq.getGender() == null ? userFromDB.getGender() : userReq.getGender());

        userFromDB.setStatus(UserStatus.UPDATED);
        userFromDB = userRepository.save(userFromDB);
        return mapper.convertValue(userFromDB, UserInfoResp.class);
    }


    @Override
    public void deleteUser(Long id) {
        User userFromDB = getUserFromDB(id);

        userFromDB.setStatus(UserStatus.DELETED);
        userFromDB = userRepository.save(userFromDB);
    }

    @Override
//    public List<UserInfoResp> getAllUsers() {
//        return userRepository.findAll().stream()
//                .map(user -> mapper.convertValue(user, UserInfoResp.class))
//                .collect(Collectors.toList());
//        }

        public Page<UserInfoResp> getAllUsers(Integer page, Integer perPage, String sort, Sort.Direction order, String filter){

        Pageable pageRequest = PaginationUtils.getPageRequest(page, perPage, sort, order);

        Page<User> users;

        if(StringUtils.hasText(filter)){
            users = userRepository.findAllFiltered(pageRequest, filter);
        } else {
            users = userRepository.findAll(pageRequest);
        }

        List<UserInfoResp> content = users.getContent().stream()
                .map(u -> mapper.convertValue(u, UserInfoResp.class))
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageRequest, users.getTotalElements());
    }

    @Override
    public User updateCarList(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    @Override
    public void invalidateSessions() {
        //logic

        String email = UserInfoReq.Fields.email;
        String age = UserInfoReq.Fields.age;
    }

}
