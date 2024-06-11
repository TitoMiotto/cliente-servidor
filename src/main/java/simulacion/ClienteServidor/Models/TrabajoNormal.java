package simulacion.ClienteServidor.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@AllArgsConstructor
@Data
public class TrabajoNormal extends Trabajo{
    private int id;
    private String nombre;
    private float probabilidad;
    public float tiempo;


    public float getTiempo() {
        return calcularTiempo(tiempo);
    }

    private float calcularTiempo(float tiempoBase) {
        if(this.id == 3)
            return tiempoBase;
        return (tiempoBase-varianza) + (varianza * (super.getRnd()) * 2 );
    }

}
