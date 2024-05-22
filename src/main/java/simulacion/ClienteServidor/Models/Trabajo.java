package simulacion.ClienteServidor.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Trabajo {
    private String nombre;
    private float probabilidad;
    private float tiempo;

}
