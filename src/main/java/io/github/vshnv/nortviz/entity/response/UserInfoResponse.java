package io.github.vshnv.nortviz.entity.response;

public class UserInfoResponse {
    private final String name;
    private final String email;
    private final String username;

    public UserInfoResponse(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
