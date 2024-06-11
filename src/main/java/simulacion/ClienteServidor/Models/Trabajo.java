package simulacion.ClienteServidor.Models;

import lombok.Data;

import java.util.Random;

@Data
public abstract class Trabajo {
    static Random r = new Random();
    protected static float varianza = 0;
    private float rnd = r.nextFloat();

    public abstract float getProbabilidad();

    public abstract int getId();

    public abstract String getNombre();

    public abstract float getTiempo();

    public static float getVarianza() {
        return varianza;
    }

    public static void setVarianza(float varianza) {
        Trabajo.varianza = varianza;
    }
}

