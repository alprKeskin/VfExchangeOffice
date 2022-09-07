package io.github.alprkeskin.swagger.redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletContext;

@RestController
@RequestMapping("/swagger")
class SwaggerRedirector {
    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public RedirectView openSwaggerUI() {
        return new RedirectView(servletContext.getContextPath() + "/swagger-ui/index.html");
    }
}
