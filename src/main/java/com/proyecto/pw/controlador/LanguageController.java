package com.proyecto.pw.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Controlador para gestionar el cambio de idioma en una aplicación Spring Boot.
 */
@Controller
public class LanguageController {

	private final SessionLocaleResolver localeResolver;

	/**
	 * Constructor que recibe un resolvedor de locales de sesión.
	 *
	 * @param localeResolver El resolvedor de locales de sesión utilizado para
	 *                       cambiar el idioma.
	 */
	public LanguageController(SessionLocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	/**
	 * Maneja la solicitud para cambiar el idioma de la aplicación.
	 *
	 * @param request La solicitud HTTP que contiene el parámetro 'lang' para
	 *                indicar el idioma deseado.
	 * @return La redirección a la página anterior visitada o a la página de inicio
	 *         por defecto.
	 */
	@GetMapping("/change-language")
	public String changeLanguage(HttpServletRequest request) {
		String lang = request.getParameter("lang");
		if (lang != null) {
			switch (lang) {
			case "es":
				localeResolver.setLocale(request, null, new Locale("es"));
				break;
			case "en":
				localeResolver.setLocale(request, null, new Locale("en"));
				break;
			default:
				// Valor predeterminado: inglés
				localeResolver.setLocale(request, null, new Locale("es"));
			}
		}
		// Obtiene la URL anterior visitada
		String referer = request.getHeader("Referer");
		if (referer != null && !referer.isEmpty()) {
			return "redirect:" + referer;
		} else {
			// Si no hay URL anterior, redirige al inicio por defecto
			return "redirect:/";
		}
	}
}