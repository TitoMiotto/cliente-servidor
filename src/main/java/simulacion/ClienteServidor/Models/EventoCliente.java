package simulacion.ClienteServidor.Models;

import lombok.AllArgsConstructor;
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
    private Cliente cliente;

    public EventoCliente(float reloj, Servidor servidor){
        this.reloj = reloj;
        this.servidor = servidor;
        this.cliente = new Cliente(TablaDeTrabajos.getTrabajo(),reloj);

    }
    @Override
    public List<Evento> avanzar() {
        List<Evento> ProxEventosAgenerar = new ArrayList<>();
        if(servidor.isOcupado()){
            if(servidor.getColaClientes().size() < 10){
                servidor.addColaClientes(cliente);
            }
            else {
                System.out.println("El cliente decidio irse");
            }
        }else{
            servidor.ocupar();
            ProxEventosAgenerar.add(new EventoServidor(reloj + cliente.getTrabajo().getTiempo() , servidor, cliente));
        }

        ProxEventosAgenerar.add(new EventoCliente(reloj + generarRandomLlegada(), servidor));
        return ProxEventosAgenerar;

    }
    @Override
    public float getReloj(){
        return reloj;
    }
    public float generarRandomLlegada(){
        Random R = new Random();
        return R.nextFloat() + 0.5f;
    }
    public String toString(){
        return new StringBuilder().append("LLegada de Cliente ").append("hora de inicio: ").append(reloj).append(" Trabajo del cliente: ").append(cliente.getTrabajo()).toString();


    }

}
