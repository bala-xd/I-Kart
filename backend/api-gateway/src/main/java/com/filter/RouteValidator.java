package com.filter;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/user-api/register",
            "/user-api/login",
            "/product-api/products",
            "/eureka"
    );
    
    public static final List<String> adminEndPoints = List.of(
    		
    );

   /* public Predicate<HttpServletRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getRequestURI().contains(uri));*/

	public boolean requiresAuth(HttpServletRequest request) {
		return openApiEndpoints
                .stream()
                .noneMatch(uri -> request.getRequestURI().contains(uri));
	}

}