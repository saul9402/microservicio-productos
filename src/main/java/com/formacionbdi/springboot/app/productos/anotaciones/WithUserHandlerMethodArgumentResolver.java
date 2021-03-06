package com.formacionbdi.springboot.app.productos.anotaciones;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * 
 * @author saul_
 *         {@docRoot https://medium.com/@Baimurzin/how-to-get-the-current-user-in-spring-cloud-microservices-c876e1c6fc65}
 * 
 */
public class WithUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// // We check if our parameter is exactly what we need:
		return parameter.hasParameterAnnotation(WithUser.class) && parameter.getParameterType().equals(UserInfo.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// take the request
		HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
		// take our Authorization header from request and get access token
		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		authorizationHeader = authorizationHeader.replace("Bearer ", "");
		// Using jjwt library parse our token and create Claim object
		Claims claims = Jwts.parser().setSigningKey(
				"omfospmfomwpoPDMSPEOFMPOMPOmpomfp.seomfpowsmpfoampovmrpovmpo.spfonvpompvioqempvin.dfinvpinfpvinaspivnpid.fnvpidnf.pvindfpivnpdifnvpin76543567"
						.getBytes()) // todo just example
				.parseClaimsJws(authorizationHeader).getBody();
		// create UserInfo object which we need to inject in our method
		String name = (String) claims.get("user_name");
		System.out.println(name);
		return new UserInfo(name);
	}

}
