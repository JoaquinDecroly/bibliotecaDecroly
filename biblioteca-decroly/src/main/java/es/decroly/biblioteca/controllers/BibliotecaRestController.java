package es.decroly.biblioteca.controllers;

import es.decroly.biblioteca.dto.BibliotecaInfoDto;
import es.decroly.biblioteca.services.Biblioteca.BibliotecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ================== AUXILIARES ==================
// @RestController -> Clase que devuelve JSON
// @RequiredArgsConstructor -> Lombok crea el constructor con los atributos final (inyección por constructor)

@Tag(name = "Biblioteca", description = "Información general de la biblioteca")
@RequestMapping("api/biblioteca")
@RestController
@RequiredArgsConstructor
public class BibliotecaRestController {
    /** Servicio con la información de la biblioteca */
    private final BibliotecaService bibliotecaService;

    // ================== ENDPOINTS BIBLIOTECA ==================
    // Ruta base: /api/biblioteca

    // GET    /api/biblioteca/info   -> Nombre, máximo de préstamos y días de préstamo configurados

    // Respuestas: 200 -> OK

    /**
     * Endpoint para obtener la información general de la biblioteca.
     * GET /api/biblioteca/info
     *
     * @return Nombre de la biblioteca, máximo de préstamos y días de préstamo
     */
    //----GET PARA OBTENER LA INFORMACIÓN DE LA BIBLIOTECA-----
    @Operation(summary = "Información de la biblioteca",
            description = "Devuelve el nombre de la biblioteca, el máximo de préstamos y los días de préstamo configurados")
    @ApiResponse(responseCode = "200", description = "Información de la biblioteca")
    @GetMapping("/info")
    public BibliotecaInfoDto obtenerInfoBiblioteca() {
        return bibliotecaService.obtenerInfoBiblio();
    }
}
