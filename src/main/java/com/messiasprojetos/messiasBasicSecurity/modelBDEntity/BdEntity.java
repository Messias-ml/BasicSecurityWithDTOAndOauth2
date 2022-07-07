package com.messiasprojetos.messiasBasicSecurity.modelBDEntity;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class BdEntity {

    @Column(name = "DAT_INC", nullable = false)
    private final LocalDateTime dataInclusao = LocalDateTime.now();

}
