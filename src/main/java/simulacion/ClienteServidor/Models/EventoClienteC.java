package simulacion.ClienteServidor.Models;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data

public class EventoClienteC implements Evento {
    private float reloj;
    private Servidor servidor;
    private Cliente cliente;

    public EventoClienteC(float reloj, Servidor servidor, Cliente cliente) {
        this.cliente = cliente;
        this.reloj = reloj;
        this.servidor = servidor;
        TrabajoC FinTrabajoC = (TrabajoC) cliente.getTrabajo();
        cliente.setTrabajo(new TrabajoNormal(FinTrabajoC.getId(), FinTrabajoC.getNombre(), FinTrabajoC.getProbabilidad(),FinTrabajoC.getTiempo()));
    }


    @Override
    public List<Evento> avanzar() {
        List<Evento> ProxEventosAgenerar = new ArrayList<>();
        if(servidor.isOcupado()){
            servidor.addFirts(cliente);
        }
        else{
            servidor.ocupar(reloj);
            ProxEventosAgenerar.add(new EventoServidor(reloj + cliente.getTiempo() , servidor, cliente));
        }


        servidor.setLugaresDisponibles(1);
        return ProxEventosAgenerar;
    }
    public String toString(){
        return new StringBuilder().append("El Trabajo C del cliente que llego: ").append(cliente.getHoraLlegada()).append(" /Puede continuar desde: ").append(reloj).toString();


    }
}
