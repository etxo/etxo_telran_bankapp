package com.etxo.bank_app.security.service;
import com.etxo.bank_app.exceptions.ClientNotFoundException;
import com.etxo.bank_app.exceptions.EmailExistsException;
import com.etxo.bank_app.security.dto.AuthRequest;
import com.etxo.bank_app.security.dto.AuthResponse;
import com.etxo.bank_app.security.dto.RegisterRequest;
import com.etxo.bank_app.security.entity.Role;
import com.etxo.bank_app.security.entity.User;
import com.etxo.bank_app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) throws EmailExistsException{

        if(repository.existsByEmail(request.getEmail())){
            throw new EmailExistsException(
                    "There is already a client with this email!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        repository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.generateToken(user));
        return authResponse;
    }

    public AuthResponse authenticate(AuthRequest request){

        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));

        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ClientNotFoundException("No user with this name!"));

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.generateToken(user));

        return authResponse;
    }
}
