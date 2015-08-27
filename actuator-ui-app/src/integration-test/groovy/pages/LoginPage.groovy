package pages

import geb.Page
import modules.LoginModule

class LoginPage extends Page {
    static url = "login/auth"

    static at = { title == "Login" }

    static content = {
        loginModule { module LoginModule }
    }
}
