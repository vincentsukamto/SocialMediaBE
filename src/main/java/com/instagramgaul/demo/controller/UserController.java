package com.instagramgaul.demo.controller;

import com.instagramgaul.demo.common.Pagination;
import com.instagramgaul.demo.common.exception.EmailIsAlreadyRegisteredException;
import com.instagramgaul.demo.common.exception.UserNotFoundException;
import com.instagramgaul.demo.common.exception.UsernameIsAlreadyRegisteredException;
import com.instagramgaul.demo.facade.Encryption;
import com.instagramgaul.demo.model.object.BaseResponse;
import com.instagramgaul.demo.model.object.factory.user.RegisterUserFactory;
import com.instagramgaul.demo.model.user.User;
import com.instagramgaul.demo.repository.UserRepository;
import com.instagramgaul.demo.services.impl.UserServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

        @Autowired
        UserServicesImpl userServices;

        @Autowired
        private UserRepository userRepository;

        @GetMapping(value = "/getAllUsers")
        public BaseResponse<List<User>> getAllUsers(){
            List<User> users = null;
            try {
                users = userServices.getAllUsers();
            } catch (Exception ex) {
                ex.getMessage();
            }
            return new BaseResponse(
                    true,
                    "200",
                    "Success getting all users data.",
                    users
            );
        }

        @GetMapping(value = "/getByUsername")
        public BaseResponse<User> getUserByUsername(@Valid @RequestBody RegisterUserFactory registerUserFactory) {
            return new BaseResponse(
                    true,
                    "200",
                    "Success",
                    userServices.getUserByUsername(registerUserFactory.getUsername())
            );
        }

        @GetMapping(value = "/getById")
        public BaseResponse<User> getUserById(@Valid @RequestBody RegisterUserFactory registerUserFactory) {
            User user = null;
            try {
                user = userServices.getUserById(registerUserFactory.getId());
            } catch (Exception ex) {
                throw new UserNotFoundException("User not found! " + ex.getMessage());
            }
            return new BaseResponse(
                    true,
                    "200",
                    "Success",
                    user
            );
        }

        @PutMapping (value = "/delete")
        public BaseResponse<User> deleteUser(@Valid @RequestBody RegisterUserFactory registerUserFactory) {
            User deletedUser = null;
            try {

                deletedUser = userServices.getUserById(registerUserFactory.getId());
                if (deletedUser == null) {
                    throw new UserNotFoundException("User not found!");
                } else {
                    deletedUser.setDeletedAt(OffsetDateTime.now());
                    deletedUser.setStatus(0);
                    userServices.deleteUser(registerUserFactory.getId());
                    return new BaseResponse<>(
                            true,
                            "200",
                            "User Delete Success",
                            null
                    );
                }
            } catch (Exception ex) {
                throw new UserNotFoundException("User not found!");
            }
        }

        @PostMapping(value = "/register")
        public BaseResponse<User> registerUser(
                @Valid @RequestBody RegisterUserFactory registerUserFactory,
                BindingResult bindingResult) throws EmailIsAlreadyRegisteredException, UsernameIsAlreadyRegisteredException {

            if (bindingResult.hasErrors()) {
                FieldError fieldError = (FieldError) bindingResult.getAllErrors().get(0);

                return new BaseResponse<>(
                        false,
                        "417",
                        fieldError.getField() + " field " + fieldError.getDefaultMessage(),
                        null
                );
            }

            if (userServices.getUserByEmail(registerUserFactory.getEmail().toLowerCase()) != null) {
                throw new EmailIsAlreadyRegisteredException(registerUserFactory.getEmail().toLowerCase());
            }

            if (userServices.getUserByUsername(registerUserFactory.getUsername().toLowerCase()) != null) {
                throw new UsernameIsAlreadyRegisteredException(registerUserFactory.getUsername().toLowerCase());
            }

            User user = new User();
            user.setUsername(registerUserFactory.getUsername().toLowerCase());
            user.setPassword(new Encryption().make(registerUserFactory.getPassword()));
            user.setEmail(registerUserFactory.getEmail().toLowerCase());
            user.setStatus(1);

            userServices.registerUser(user);

            return new BaseResponse<>(
                    true, "200", "Data in!", user
            );
        }
    }

