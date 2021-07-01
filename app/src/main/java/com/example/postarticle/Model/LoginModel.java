package com.example.postarticle.Model;

public class LoginModel {
    private String message;

    @Override
    public String toString() {
        return "LoginModel{" +
                "message='" + message + '\'' +
                ", access_token='" + access_token + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public LoginModel.user getUser() {
        return user;
    }

    public void setUser(LoginModel.user user) {
        this.user = user;
    }

    private String access_token;
    private user user;

    public class user{
        private int id;
        private String name;
        private String email;
        private String jenis;
        private String acting_as;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getJenis() {
            return jenis;
        }

        public void setJenis(String jenis) {
            this.jenis = jenis;
        }

        public String getActing_as() {
            return acting_as;
        }

        public void setActing_as(String acting_as) {
            this.acting_as = acting_as;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        private String created_at;
    }

}
