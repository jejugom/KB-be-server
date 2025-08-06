package org.bank.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

// import org.bank.security.config.SecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
			// , SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Swagger 설정 클래스 추가
		return new Class[] {ServletConfig.class, SwaggerConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {
			"/",                        // 기본 매핑
			"/swagger-ui.html",         // Swagger UI 메인 페이지
			"/swagger-resources/**",    // Swagger 리소스
			"/v2/api-docs",            // API 명세 JSON
			"/webjars/**"              // WebJar 리소스 (CSS, JS 등)
		};
	}

	// Post body 문자 인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		
		// CORS 필터 설정
		CorsFilter corsFilter = createCorsFilter();
		
		return new Filter[] {encodingFilter, corsFilter};
	}
	
	/**
	 * CORS(Cross-Origin Resource Sharing) 설정을 위한 필터를 생성합니다.
	 * 
	 * @return CorsFilter 객체
	 */
	private CorsFilter createCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);       // 자격 증명(쿠키 등) 허용
		config.addAllowedOriginPattern("*");    // 모든 출처 허용
		config.addAllowedHeader("*");           // 모든 헤더 허용
		config.addAllowedMethod("*");           // 모든 HTTP 메소드 허용
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
