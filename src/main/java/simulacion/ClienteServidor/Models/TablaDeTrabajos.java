package simulacion.ClienteServidor.Models;

import lombok.Data;
import lombok.Getter;
import simulacion.ClienteServidor.Dtos.TrabajoDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Data
public class TablaDeTrabajos {
    static Random r = new Random();
    private static List<Trabajo> trabajos = new ArrayList<>();
    private static List<String> nombres = new ArrayList<>(Arrays.asList("Cambio de placa", "Ampliacion de memoria", "Formateo de disco", "Agregar CD"));


    public static void inicializar(List<TrabajoDto> probabilidades) {
        trabajos.clear();
        float acum = 0f;
        for (int i = 0; i < probabilidades.size(); i++) {
            acum += probabilidades.get(i).getProbabilidad();
            if(i == 2){
                trabajos.add(new TrabajoC(i+1, nombres.get(i), acum, probabilidades.get(i).getTiempo()));
                continue;
            }
            trabajos.add(new TrabajoNormal(i+1, nombres.get(i), acum, probabilidades.get(i).getTiempo()));
        }
    }

    public static Trabajo getTrabajo(float aleatorio) {
        for (Trabajo x : trabajos) {
            if (aleatorio < x.getProbabilidad()) {
                return x;
            }
        }
        return trabajos.get(trabajos.size()-1);
    }
}
