package simulacion.ClienteServidor.Models;

import java.util.List;

public interface Evento {
    List<Evento> avanzar();
    float getReloj();
    String toString();
}
