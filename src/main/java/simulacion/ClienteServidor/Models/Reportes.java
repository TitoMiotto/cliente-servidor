package simulacion.ClienteServidor.Models;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class Reportes {
    private float sumatoriaDeTiempoEnLab = 0;
    private int countEquiposEnLab = 0;
    private float tiempoOcupacionTecnico = 0;
    private float tiempoTotal = 0;


    public void actualizarPromDeTiempoEnLab(float duracion){
        this.sumatoriaDeTiempoEnLab += duracion;
        this.countEquiposEnLab++;
    }

    public void actualizarPorcOcupacionTecnico(float duracion, float tiempoActual){
        this.tiempoOcupacionTecnico += duracion;
        this.tiempoTotal = tiempoActual;
    }

    public float getPromDeTiempoEnLab(){
        if(this.countEquiposEnLab!=0) {
            return this.sumatoriaDeTiempoEnLab/this.countEquiposEnLab;
        }
        return 0;
    }
    public float getPorcOcupacionTecnico() {
        if (this.countEquiposEnLab != 0) {
            return this.tiempoOcupacionTecnico / this.tiempoTotal;
        }
        return 0;
    }
}
