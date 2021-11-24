package com.instagramgaul.demo.controller;

import com.instagramgaul.demo.common.exception.UserNotFoundException;
import com.instagramgaul.demo.facade.Encryption;
import com.instagramgaul.demo.facade.JwtToken;
import com.instagramgaul.demo.model.object.BaseResponse;
import com.instagramgaul.demo.model.object.dto.auth.request.JwtRequest;
import com.instagramgaul.demo.model.object.dto.auth.response.JwtResponse;
import com.instagramgaul.demo.model.user.User;
import com.instagramgaul.demo.services.UserServices;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/auth",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private JwtToken jwtToken;

    @GetMapping(value = "/me")
    public BaseResponse<User> validateToken(@RequestHeader("Authorization") String token) {

        String username = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                username = jwtToken.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            System.out.println("JWT Token does not begin with Bearer String");
        }

        return new BaseResponse (
                true,
                "200",
                "Success",
                userServices.getUserByUsername(username)
        );
    }

    @PostMapping(value = "/login")
    public BaseResponse<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final Boolean isValidCredential = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if(!isValidCredential){
            // @todo Throw Invalid Credential
            throw new UserNotFoundException("");
        }

        final String token = jwtToken.generateToken(
                userServices.getUserByUsername(authenticationRequest.getUsername())
        );

        final JwtResponse response = new JwtResponse(token);

        return new BaseResponse<JwtResponse>(
                true,
                "200",
                "Token Generated!",
                response
        );
    }

    private Boolean authenticate(String username, String password) throws Exception {
        try {
            final User userValidate = userServices.getUserByUsername(username);
            if (userValidate == null) {
                throw new UserNotFoundException("User not found!");
            }

            if(!new Encryption().checkHash(password, userValidate.getPassword())){
                // @todo Throw password salah
                throw new UserNotFoundException("Wrong Password!");
            }

            return true;
        } catch(Exception e){
            System.out.println("Authentication failed with message: "+ e.getMessage());
            return false;
        }
    }
}