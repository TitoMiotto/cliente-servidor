package simulacion.ClienteServidor.Models;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import simulacion.ClienteServidor.Services.Gestor;

@Data
public class EventoServidor implements Evento {
    private float reloj;
    private Servidor servidor;
    @Override
    public List<Evento> avanzar() {
        List<Evento> ProxEventosAgenerar = new ArrayList<>();
        if(servidor.getColaClientes() == 0){
            servidor.liberar();
        }else{
            servidor.setColaClientes(-1);
            ProxEventosAgenerar.add(new EventoServidor(servidor, reloj + generarRandom()));
        }
        return ProxEventosAgenerar;
    }
    public float generarRandom(){
        Random R = new Random();
        return R.nextFloat();
    }
    @Override
    public float getReloj(){
        return reloj;
    }
    public EventoServidor(Servidor servidor, float reloj){
        this.servidor = servidor;
        this.reloj = reloj;
    }
}
