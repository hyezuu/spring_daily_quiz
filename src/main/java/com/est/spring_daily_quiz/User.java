package com.est.spring_daily_quiz;

import lombok.*;

/**
 * 문제: User 클래스를 만들고, TestController에서 User 객체를 생성하여 모델에 추가한 후, Thymeleaf 템플릿에서 사용자 정보를 표시하세요.
 조건:

 - User 클래스는 username, email, isAdmin 필드를 가져야 합니다.
 - Controller에서 User 객체를 생성하고 모델에 추가하세요.
 - 템플릿에서 `${...}` 또는 `{*...}` 표현식을 사용하여 사용자 정보를 표시하세요.
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userName;
    private String email;
    private boolean isAdmin;
    private String password;
}
