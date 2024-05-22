package simulacion.ClienteServidor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import simulacion.ClienteServidor.Dtos.TrabajoDto;
import simulacion.ClienteServidor.Services.Gestor;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerSimulacion {
    @Autowired
    Gestor gestor;

    @GetMapping("/simular")
    public void simular(@RequestBody int cantidad){
        gestor.simular(cantidad);
    }

    @PostMapping("/simular/setClientes")
    public void setClientes(@RequestBody List<TrabajoDto> trabajos){
        gestor.setClientes(trabajos);
    }

    @PostMapping("/simular/setVarianza")
    public void setVarianza(@RequestParam float varianza){
        gestor.setVarianza(varianza);
    }
}
