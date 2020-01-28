package br.com.java.solution.config.cors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // TODO ADICIONAR BLOQUEIO DE CORS SE NECESSÁRIO
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, clienteId, clienteProjecao");
        response.setHeader("Access-Control-Max-Age", "3600");

        response.setStatus(HttpServletResponse.SC_OK);
//            } else {
        chain.doFilter(req, resp);
//            }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
