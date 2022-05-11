package com.atguigu.admin.bean;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class City {
    private Long id;
    private String name;
    private String state;
    private String country;
}
