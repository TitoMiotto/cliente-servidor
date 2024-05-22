package simulacion.ClienteServidor.Models;

import lombok.Data;
import simulacion.ClienteServidor.Dtos.TrabajoDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TablaDeTrabajos {
    static Random r = new Random();
    private static float varianza;
    private static List<Trabajo> trabajos = new ArrayList<>();
    private static List<String> nombres = new ArrayList<>(Arrays.asList("Cambio de placa", "Ampliacion de memoria", "Formateo de disco", "Agregar CD"));


    public static void inicializar(List<TrabajoDto> probabilidades) {
        trabajos.clear();
        float acum = 0f;
        for (int i = 0; i < probabilidades.size(); i++) {
            acum += probabilidades.get(i).getProbabilidad();
            trabajos.add(new Trabajo(nombres.get(i), acum, probabilidades.get(i).getTiempo()));
        }
    }

    public static Trabajo getTrabajo() {
        float aleatorio = r.nextFloat();
        for (Trabajo x : trabajos) {
            if (aleatorio < x.getProbabilidad()) {
                float tiempo = calcularTiempo(x.getTiempo());
                return new Trabajo(x.getNombre(), x.getProbabilidad(), tiempo);
            }
        }
        return null;
    }


    private static float calcularTiempo(float tiempoBase) {
        return (tiempoBase-varianza) + (varianza * (r.nextFloat()) * 2 );
    }


    public static void setVarianza(float varianza) {
       TablaDeTrabajos.varianza = varianza;
    }
}
