package simulacion.ClienteServidor.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Cliente {
    private Trabajo trabajo;
    private float HoraLlegada;
    private float tiempo;

    public Cliente(Trabajo trabajo, float horaLlegada){
        this.trabajo = trabajo;
        this.HoraLlegada = horaLlegada;
        this.tiempo = trabajo.getTiempo();

    }
    public String toString(){
        return "Hora de llegada: " + this.HoraLlegada + " /tiempo de su trabajo: " + this.tiempo + " /su trabajo: " + this.trabajo.getNombre();
    }
}
