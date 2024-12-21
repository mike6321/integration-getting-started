package com.example.step01.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class Domain {

    private String name;
    private int age;

}
