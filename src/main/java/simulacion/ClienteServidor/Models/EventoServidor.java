package simulacion.ClienteServidor.Models;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;


@Data
public class EventoServidor implements Evento {
    private float reloj;
    private Servidor servidor;
    private Cliente clienteActual;
    private Cliente proxcliente;


    public EventoServidor(float reloj, Servidor servidor, Cliente clienteActual){
        this.reloj = reloj;
        this.servidor = servidor;
        this.clienteActual = clienteActual;
    }
    @Override
    public List<Evento> avanzar() {

        List<Evento> ProxEventosAgenerar = new ArrayList<>();
        if(clienteActual.getTrabajo() instanceof TrabajoC tc){
            //"estamos dejande en stan by un trabajo tipo C la cola debe disminuir"
            System.out.println("_____________________________________");
            servidor.setLugaresDisponibles(-1);
            ProxEventosAgenerar.add(new EventoClienteC(reloj+ tc.getUltimaParteC(), servidor, clienteActual));
        }
        if(servidor.getColaClientes().isEmpty()){
            servidor.liberar();
            servidor.reporte.actualizarPorcOcupacionTecnico(reloj - servidor.getInicioServicio(), reloj);
        }else{
            proxcliente = servidor.getColaClientes().poll();
            ProxEventosAgenerar.add(new EventoServidor(reloj+ proxcliente.getTiempo(), servidor, proxcliente));
        }
        servidor.reporte.actualizarPromDeTiempoEnLab(reloj-clienteActual.getHoraLlegada());
        return ProxEventosAgenerar;
    }

    @Override
    public float getReloj(){
        return reloj;
    }


    public String toString(){
        return new StringBuilder().append("---Atencion servidor ").append(reloj).append(" cliente atendido: ").append(clienteActual).append("\n").toString();
    }
}
