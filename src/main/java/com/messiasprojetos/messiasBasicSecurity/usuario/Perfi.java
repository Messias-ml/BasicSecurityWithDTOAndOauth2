package com.messiasprojetos.messiasBasicSecurity.usuario;

import lombok.Getter;
import lombok.Setter;

public enum Perfi {
    ADMIN("ADMIN"),
    USER("USER");

    @Getter
    @Setter
    private String role;

    Perfi(String role) {
        this.role = role;
    }
}
