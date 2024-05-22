package simulacion.ClienteServidor.Models;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import simulacion.ClienteServidor.Services.Gestor;
@AllArgsConstructor
@Data
public class EventoServidor implements Evento {
    private float reloj;
    private Servidor servidor;
    private Cliente cliente;
    @Override
    public List<Evento> avanzar() {
        List<Evento> ProxEventosAgenerar = new ArrayList<>();
        if(servidor.getColaClientes().isEmpty()){
            servidor.liberar();
        }else{
            cliente = servidor.getColaClientes().poll();
            ProxEventosAgenerar.add(new EventoServidor(reloj+cliente.getTrabajo().getTiempo(), servidor, cliente));
        }
        return ProxEventosAgenerar;
    }

    @Override
    public float getReloj(){
        return reloj;
    }


    public String toString(){
        return new StringBuilder().append("Atencion servidor ").append("hora de fin: ").append(reloj).append(" Trabajo del cliente: ").append(cliente.getTrabajo()).toString();
    }
}
