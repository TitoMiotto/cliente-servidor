package simulacion.ClienteServidor.Services;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simulacion.ClienteServidor.Dtos.SalidaDto;
import simulacion.ClienteServidor.Dtos.TrabajoDto;
import simulacion.ClienteServidor.Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Service
public class Gestor {
    @Getter
    private Servidor servidor;
    public List<Evento> ColaDeEventos;
    static Random R = new Random();


    public void simular(float tiempoFinal){
        ColaDeEventos = new ArrayList<>();
        servidor = new Servidor();

        //primer evento de la simulacion
        ColaDeEventos.add(new EventoCliente(generarRandomLlegada(), servidor));
        Evento Actual = ColaDeEventos.get(0);
        for(int i = 0 ; Actual.getReloj() <= tiempoFinal ; i++){
            Actual = ColaDeEventos.get(i);
            List<Evento> agregados = Actual.avanzar();
            System.out.println(Actual);
            System.out.println(servidor);
            if(!agregados.isEmpty()){
                for (Evento x : agregados) {
                    int index = i + 1;
                    while (index < ColaDeEventos.size() && x.getReloj() >= ColaDeEventos.get(index).getReloj()) {
                        index++;
                    }
                    ColaDeEventos.add(index, x);
                }

            };
        }
    }
    public float generarRandomLlegada(){
        return 60 * R.nextFloat() + 30f;
    }


    public void setClientes(List<TrabajoDto> trabajos) {
        TablaDeTrabajos.inicializar(trabajos);

    }

    public void setVarianza(float varianza) {
        Trabajo.setVarianza(varianza);
    }


    public void setTiempoTrabajoC(float tiempoTrabajoC) {
        TrabajoC.setTiempoEnTrabajoC(tiempoTrabajoC);
    }

    public float getReportePorcOcupacionTecnico(){
        return getServidor().reporte.getPorcOcupacionTecnico();
    }
    public float getReportePromDeTiempoEnLab(){
        return getServidor().reporte.getPromDeTiempoEnLab();
    }

    public List<SalidaDto> getTabla(float tiempoFinal, float initTimeView, float cantSimIterations) {
        ColaDeEventos = new ArrayList<>();
        List<SalidaDto> ColaSalida = new ArrayList<>();
        servidor = new Servidor();
        float rndEvento = R.nextFloat();
        int equipos = 0;

        //primer evento de la simulacion
        ColaDeEventos.add(new EventoCliente(30 + rndEvento * 60, servidor));
        rndEvento = R.nextFloat();
        Evento Actual = ColaDeEventos.get(0);

        for(int i = 0 ; Actual.getReloj() <= tiempoFinal ; i++){
            Actual = ColaDeEventos.get(i);
            List<Evento> agregados = Actual.avanzar();
            System.out.println(Actual);
            System.out.println(servidor);
            if(!agregados.isEmpty()){
                for (Evento x : agregados) {
                    int index = i + 1;
                    while (index < ColaDeEventos.size() && x.getReloj() >= ColaDeEventos.get(index).getReloj()) {
                        index++;
                    }
                    ColaDeEventos.add(index, x);
                    rndEvento = R.nextFloat();
                }

            };
            if(i >= initTimeView && i <= cantSimIterations + initTimeView){
                if(Actual instanceof EventoCliente){
                    equipos++;
                    EventoCliente a = (EventoCliente) Actual;
                    SalidaDto salidaDto = new SalidaDto();
                    salidaDto.setRndReloj(rndEvento);
                    salidaDto.setEvento("Llegada Cliente");
                    salidaDto.setReloj(a.getCliente().getHoraLlegada());
                    salidaDto.setTiempoEntrellegadas(30 + a.getRndProxLlegada()*60);
                    salidaDto.setHoraProxllegada(a.getReloj()+30 + a.getRndProxLlegada()*60);
                    salidaDto.setRndTipoTrabajo(a.getRndTrabajo());
                    salidaDto.setTipoTrabajo(a.getCliente().getTrabajo().getNombre());
                    salidaDto.setCola(servidor.getColaClientes().toString());
                    salidaDto.setLugaresDisponibles(servidor.getLugaresDisponibles()-servidor.getColaClientes().size());
                    salidaDto.setCountEquipos(equipos);
                    if(a.getCliente().getTrabajo().getId() == 3) {
                        TrabajoC tr = (TrabajoC) a.getCliente().getTrabajo();
                        salidaDto.setHoraCambioTrabajo(a.getCliente().getTiempo() + a.getCliente().getHoraLlegada());
                        salidaDto.setHoraReanudacionTrabajo(tr.getUltimaParteC());
                    }
                    salidaDto.setRndFinTrabajo(a.getCliente().getTrabajo().getRnd());
                    salidaDto.setTiempoTrabajo(a.getCliente().getTrabajo().getTiempo());
                    salidaDto.setHoraFinTrabajo(a.getCliente().getTrabajo().getTiempo()+a.getReloj());
                    salidaDto.setEstadoTecnico(servidor.isOcupado());
                    salidaDto.setHorainicioOcupacion(servidor.getInicioServicio());
                    salidaDto.setTiempoOcupacion(a.getReloj()-servidor.getInicioServicio());
                    ColaSalida.add(salidaDto);
                } else if (Actual instanceof EventoServidor) {

                }else {

                }
            }
        }
        return ColaSalida;
    }
}
