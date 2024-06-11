package simulacion.ClienteServidor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simulacion.ClienteServidor.Dtos.EntradaDto;
import simulacion.ClienteServidor.Dtos.SalidaDto;
import simulacion.ClienteServidor.Dtos.TrabajoDto;
import simulacion.ClienteServidor.Services.Gestor;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerSimulacion {
    @Autowired
    Gestor gestor;

    @GetMapping("/simular")
    public void simular(@RequestBody float tiempoFinal){
        gestor.simular(tiempoFinal);
    }

    @PostMapping("/simular/setClientes")
    public void setClientes(@RequestBody List<TrabajoDto> trabajos){
        gestor.setClientes(trabajos);
    }

    @PostMapping("/simular/setVarianza")
    public void setVarianza(@RequestParam float varianza){
        gestor.setVarianza(varianza);
    }

    @PostMapping("/simular/setTiempoTrabajoC")
    public void setTiempoTrabajoC(@RequestParam float tiempoTrabajoC){
        gestor.setTiempoTrabajoC(tiempoTrabajoC);
    }

    @GetMapping("/reporte1")
    public ResponseEntity<Float> getReporte1(){
        if(gestor.getReportePorcOcupacionTecnico()!=0)
            return ResponseEntity.ok(gestor.getReportePorcOcupacionTecnico());
        return null;
    }
    @GetMapping("/reporte2")
    public ResponseEntity<Float> getReporte2(){
        if(gestor.getReportePromDeTiempoEnLab()!=0)
            return ResponseEntity.ok(gestor.getReportePromDeTiempoEnLab());
        return null;
    }

    @GetMapping("/simularCompleto")
    public List<SalidaDto> simularCompleto(@RequestBody EntradaDto dto){
        List<TrabajoDto> trabajos = new ArrayList<>();
        trabajos.add(new TrabajoDto(dto.probTA,dto.timeTA));
        trabajos.add(new TrabajoDto(dto.probTB,dto.timeTB));
        trabajos.add(new TrabajoDto(dto.probTC,dto.timeTC));
        trabajos.add(new TrabajoDto(dto.probTD,dto.timeTD));
        gestor.setClientes(trabajos);
        gestor.setVarianza(dto.varianza);
        gestor.setTiempoTrabajoC(dto.timeTrabajoC);
        return gestor.getTabla(dto.cantTimeSim, dto.initTimeView,dto.cantSimIterations);
    }
}
