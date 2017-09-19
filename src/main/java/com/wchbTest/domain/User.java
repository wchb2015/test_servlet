package com.wchbTest.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String userName;

    private String email;

    private Integer age;
}
