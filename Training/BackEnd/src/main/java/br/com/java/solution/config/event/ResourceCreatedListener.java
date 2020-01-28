package br.com.java.solution.config.event;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent> {

    @Override
    public void onApplicationEvent(ResourceCreatedEvent featureCreatedEvent) {
        HttpServletResponse response = featureCreatedEvent.getResponse();
        Long id = featureCreatedEvent.getId();
        addHeaderLocation(response, id);
    }

    private void addHeaderLocation(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }

}

