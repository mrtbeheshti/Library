package com.example.library.security;

import lombok.*;

import java.security.Principal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocketPrincipal implements Principal {
    private long ssoId;
    private String name;
}
