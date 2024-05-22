package simulacion.ClienteServidor.Services;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simulacion.ClienteServidor.Models.Evento;
import simulacion.ClienteServidor.Models.EventoCliente;
import simulacion.ClienteServidor.Models.Servidor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Service
public class Gestor {
    @Getter
    private Servidor servidor = new Servidor();
    public List<Evento> ColaDeEventos = new ArrayList<>();

    public void simular(int cantidad){

        //primer evento de la simulacion
        ColaDeEventos.add(new EventoCliente(generarRandom(), servidor));

        for(int i = 0 ; i < cantidad ; i++){
            Evento Actual = ColaDeEventos.get(i);
            System.out.println(Actual.getReloj());
            List<Evento> agregados = Actual.avanzar();
            if(!agregados.isEmpty()){
                for(Evento x:agregados){
                    int index = i;
                    while ((index < ColaDeEventos.size()) && (x.getReloj() > ColaDeEventos.get(index).getReloj())) {
                        index++;
                    }
                    ColaDeEventos.add(index, x);
                }
            };

        }
    }
    public float generarRandom(){
        Random R = new Random();
        return R.nextFloat();
    }


}
