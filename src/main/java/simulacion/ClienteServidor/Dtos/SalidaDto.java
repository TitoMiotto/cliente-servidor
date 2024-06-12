package simulacion.ClienteServidor.Dtos;

import lombok.Data;

import java.util.List;
@Data
public class SalidaDto {
    public String evento;
    public float reloj;
    public float rndReloj;
    public float tiempoEntrellegadas;
    public float horaProxllegada;
    public float rndTipoTrabajo;
    public String tipoTrabajo;
    public String cola;
    public int colaC;
    public int lugaresDisponibles;
    public int countEquipos;
    public float horaCambioTrabajo;
    public float horaReanudacionTrabajo;
    public float rndFinTrabajo;
    public float tiempoTrabajo;
    public float horaFinTrabajo;
    public boolean estadoTecnico;
    public float horainicioOcupacion;
    public float horaFinOcupacion;
    public float tiempoOcupacion;
    public float tiempopermanencia;
}
