import auth.Role
import auth.User
import auth.UserRole

class BootStrap {

    def init = { servletContext ->
        //auth init
        def adminRole = new Role(authority: 'ROLE_ADMIN').save flush: true, failOnError: true
        def userRole = new Role(authority: 'ROLE_USER').save flush: true, failOnError: true

        def adminUser = new User(username: 'admin@sample.org', fullName: "John Doe", password: 'admin')
        def normalUser = new User(username: 'simple@sample.org', fullName: "Jack Miller", password: 'simple')
        [adminUser, normalUser]*.save flush: true, failOnError: true

        UserRole.create(adminUser, adminRole, true)
        UserRole.create(normalUser, userRole, true)

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2
    }
}
