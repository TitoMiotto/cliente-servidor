package simulacion.ClienteServidor.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class TrabajoC extends Trabajo{
    private int id;
    private String nombre;
    private float probabilidad;
    private float tiempo;
    @Setter
    @Getter
    private static float tiempoEnTrabajoC;


    public float getTiempo() {
        return tiempoEnTrabajoC;
    }

    public float getUltimaParteC(){
        return (tiempo-varianza) + (varianza * (super.getRnd()) * 2 ) - tiempoEnTrabajoC * 2;
    }
}
