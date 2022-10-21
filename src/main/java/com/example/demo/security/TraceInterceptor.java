package com.example.demo.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@AllArgsConstructor
public class TraceInterceptor implements HandlerInterceptor {
    private TraceRepository traceRepository;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String user = "Visitor";

        // Droit droit = droitRepository.findByUriLikeAndMethodEquals(request.getRequestURI(), request.getMethod());
        Trace t = new Trace(user,
                request.getRequestURI(),
                request.getRequestURL().toString(),
                null,
                request.getMethod(),
                response.getStatus(),
                null);

        if (request.getHeader("x-forwarded-for") == null) {
            t.setIp(request.getRemoteAddr());
        } else {
            t.setIp(request.getHeader("x-forwarded-for"));
        }

        if (request.getUserPrincipal() == null) {
            traceRepository.save(t);
        } else {
            t.setUsername(request.getUserPrincipal().getName());
            traceRepository.save(t);
        }
    }
}

@Entity
@Getter
@Setter
@NoArgsConstructor
class Trace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;
    private String uri;
    private String url;
    private String ip;
    private String method;
    private int response;
    private String description;

    public Trace(String username, String uri, String url, String ip, String method, int response, String description) {
        this.username = username;
        this.uri = uri;
        this.url = url;
        this.ip = ip;
        this.method = method;
        this.response = response;
        this.description = description;
    }
}

interface TraceRepository extends JpaRepository<Trace, Long> {
}