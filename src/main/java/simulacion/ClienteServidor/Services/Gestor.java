package simulacion.ClienteServidor.Services;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simulacion.ClienteServidor.Dtos.TrabajoDto;
import simulacion.ClienteServidor.Models.*;

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
        ColaDeEventos.add(new EventoCliente(generarRandomLlegada(), servidor));

        for(int i = 0 ; i < cantidad ; i++){
            Evento Actual = ColaDeEventos.get(i);
            System.out.println(Actual);
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
    public float generarRandomLlegada(){
        Random R = new Random();
        return R.nextFloat() + 0.5f;
    }


    public void setClientes(List<TrabajoDto> trabajos) {
        TablaDeTrabajos.inicializar(trabajos);

    }

    public void setVarianza(float varianza) {
        TablaDeTrabajos.setVarianza(varianza);
    }


}
