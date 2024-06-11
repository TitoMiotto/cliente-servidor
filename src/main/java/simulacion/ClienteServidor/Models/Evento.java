package simulacion.ClienteServidor.Models;

import simulacion.ClienteServidor.Dtos.SalidaDto;

import java.util.List;

public interface Evento {
    List<Evento> avanzar();
    float getReloj();
    String toString();

}
