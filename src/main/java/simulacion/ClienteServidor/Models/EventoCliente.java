package simulacion.ClienteServidor.Models;

import lombok.Data;
import lombok.Getter;
import simulacion.ClienteServidor.Services.Gestor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Data
public class EventoCliente implements Evento {

    private float reloj;
    private Servidor servidor;

    public EventoCliente( float reloj, Servidor servidor) {
        this.reloj = reloj;
        this.servidor = servidor;
    }
    @Override
    public List<Evento> avanzar() {
        List<Evento> ProxEventosAgenerar = new ArrayList<>();
        if(servidor.isOcupado()){
            servidor.setColaClientes(1);
        }else{
            servidor.ocupar();
            ProxEventosAgenerar.add(new EventoServidor(servidor, reloj + generarRandom()));
        }
        ProxEventosAgenerar.add(new EventoCliente(reloj + generarRandom(), servidor));
        return ProxEventosAgenerar;

    }
    @Override
    public float getReloj(){
        return reloj;
    }
    public float generarRandom(){
        Random R = new Random();
        return R.nextFloat();
    }

}
