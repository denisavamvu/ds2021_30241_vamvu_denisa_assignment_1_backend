package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.config.JwtTokenUtil;
import ro.tuc.ds2020.dtos.CurrentUserDTO;
import ro.tuc.ds2020.entities.JwtRequest;
import ro.tuc.ds2020.entities.JwtResponse;
import ro.tuc.ds2020.services.JwtUserDetailsService;
import ro.tuc.ds2020.services.UserService;

@RestController
@CrossOrigin
@RequestMapping()
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        CurrentUserDTO currentUserDTO = userService.getCurrentUserDetails(authenticationRequest.getUsername());

        JwtResponse jwtResponse = new JwtResponse(token, currentUserDTO.getId(),currentUserDTO.getUsername(), currentUserDTO.getRole());
        return ResponseEntity.ok(jwtResponse);
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }
}