package simulacion.ClienteServidor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simulacion.ClienteServidor.Services.Gestor;

@RestController
@RequestMapping("/api")
public class ControllerSimulacion {
    @Autowired
    Gestor gestor;

    @GetMapping("/simular")
    public void simular(@RequestBody int cantidad){
        gestor.simular(cantidad);
    }
}
