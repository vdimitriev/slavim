package mk.vedmak.slavim.authentication.api

import mk.vedmak.slavim.authentication.domain.model.User
import mk.vedmak.slavim.authentication.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

@Controller
class AuthenticationController {

    @Autowired
    private lateinit var userService: UserService

    @RequestMapping("/")
    fun login(): String {
        return "login"
    }

    @RequestMapping("/new-account")
    fun newAccount(model: Model): String {
        model.addAttribute("user", User())
        return "new-account"
    }

    @RequestMapping(path = ["/new-account"], method = [RequestMethod.POST])
    fun createAccount(user: @Valid User): String {
        userService.createUser(user)
        return "redirect:/"
    }

}