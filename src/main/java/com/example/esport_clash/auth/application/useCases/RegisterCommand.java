package com.example.esport_clash.auth.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.player.domain.viewModel.IdResponse;

public class RegisterCommand implements Command<IdResponse> {
    private String email;
    private String password;

    public RegisterCommand() {}

    public RegisterCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
