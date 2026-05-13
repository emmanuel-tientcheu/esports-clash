package com.example.esport_clash.auth.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.auth.domain.viewModel.LoggedUserViewModel;

public class LoginCommand implements Command<LoggedUserViewModel> {
    private String email;
    private String password;

    public LoginCommand() {}

    public LoginCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
