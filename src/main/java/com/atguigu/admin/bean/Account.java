package com.atguigu.admin.bean;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Account {
    private Long id  ;
    private String userId;
    private Integer money ;
}
