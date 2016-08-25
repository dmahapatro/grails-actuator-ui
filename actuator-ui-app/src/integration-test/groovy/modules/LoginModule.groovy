package modules

import geb.Module

class LoginModule extends Module {
    static content = {
        loginForm {$("form")}
        loginButton {$("input", value: "Login")}
    }

    void login(String username = "admin@sample.org", String password = "admin") {
        loginForm.username = username
        loginForm.password = password
        loginButton.click()
    }
}
