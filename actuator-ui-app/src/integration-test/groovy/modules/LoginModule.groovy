package modules

import geb.Module

class LoginModule extends Module {
    static content = {
        loginForm {$("form")}
        loginButton {$("input", value: "Login")}
    }

    void login(String username = "admin@sample.org", String password = "admin") {
        loginForm.j_username = username
        loginForm.j_password = password
        loginButton.click()
    }
}
