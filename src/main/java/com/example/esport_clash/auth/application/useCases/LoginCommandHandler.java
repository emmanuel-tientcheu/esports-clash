package com.example.esport_clash.auth.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.application.services.jwtservice.JWTService;
import com.example.esport_clash.auth.application.services.passwordHasher.PasswordHasher;
import com.example.esport_clash.auth.domain.viewModel.LoggedUserViewModel;
import com.example.esport_clash.core.domain.exceptions.BadRequestException;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;

public class LoginCommandHandler implements Command.Handler<LoginCommand, LoggedUserViewModel> {

    private final UserRepository repository;
    private final JWTService jwtService;
    private final PasswordHasher passwordHasher;

    public  LoginCommandHandler(UserRepository repository, JWTService jwtService, PasswordHasher passwordHasher) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.passwordHasher = passwordHasher;
    }
    @Override
    public LoggedUserViewModel handle(LoginCommand loginCommand) {
        var user = this.repository.findByEmail(loginCommand.getEmail()).orElseThrow(
                () ->  new NotFoundException("User", "")
        );

        var match = this.passwordHasher.match(loginCommand.getPassword(), user.getPassword());
        if(!match) {
            throw new BadRequestException("Incorrect password");
        }
        var token = jwtService.tokenize(user);
        return new LoggedUserViewModel(user.getId(), user.getEmail(), token);
    }
}
