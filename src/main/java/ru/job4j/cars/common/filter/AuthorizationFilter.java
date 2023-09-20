package ru.job4j.cars.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static java.util.Objects.isNull;

/**
 * Проверка доступа
 */
@Component
public class AuthorizationFilter extends HttpFilter {
    private static final String USER = "user";
    private static final String LOGIN_PAGE = "/user/authorization";

    /** Общедоступные адреса */
    private final Set<String> addresses = Set.of(
            "user/registration",
            "user/create",
            "user/success",
            "user/fail",
            "user/login",
            "user/authorization",
            "user/logout",
            "post/list",
            "post/list/day",
            "post/list/withMileage",
            "post/list/new",
            "post/list/all",
            "post/details",
            "photo"
    );

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        String uri = request.getRequestURI();
        if (addressPresent(uri)) {
            chain.doFilter(request, response);
            return;
        }
        if (isNull(request.getSession().getAttribute(USER))) {
            response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean addressPresent(String uri) {
        return addresses.stream()
                .anyMatch(uri::contains);
    }
}
