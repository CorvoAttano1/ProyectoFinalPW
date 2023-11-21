package com.proyecto.pw.controlador;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador que gestiona las páginas web de la aplicación.
 */
@Controller
public class PaginaController {

    /**
     * Muestra la página principal.
     * @return Nombre de la vista de la página principal.
     */
    @GetMapping("/principal")
    public String mostrarPrincipal() {
        return "principal";
    }

    /**
     * Muestra la página de servicios.
     * @return Nombre de la vista de la página de servicios.
     */
    @GetMapping("/pag_servicios")
    public String mostrarPag_servicios() {
        return "pag_servicios";
    }

    /**
     * Controlador para manejar errores personalizados.
     */
    @Controller
    public class CustomErrorController implements ErrorController {

        /**
         * Maneja los errores y redirige a páginas personalizadas según el tipo de error.
         * @param request La solicitud HTTP que provocó el error.
         * @return Nombre de la vista de la página de error correspondiente.
         */
        @RequestMapping("/error")
        public String handleError(HttpServletRequest request) {
            Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

            if (status != null) {
                Integer statusCode = Integer.valueOf(status.toString());

                if (statusCode == HttpStatus.NOT_FOUND.value()) {
                    return "error"; // Página personalizada para el error 404
                } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                    return "errorServer"; // Página personalizada para el error 500
                }
            }

            // Si no se encuentra un código de error específico, redirige a una página genérica
            return "errorGeneral";
        }

        /**
         * Obtiene la ruta de error.
         * @return La ruta de error.
         */
        public String getErrorPath() {
            return "/error";
        }
    }
}
