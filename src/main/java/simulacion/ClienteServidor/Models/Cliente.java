package simulacion.ClienteServidor.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Cliente {
    private Trabajo trabajo;
    private float HoraLlegada;
}
