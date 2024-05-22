package simulacion.ClienteServidor.Models;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Random;

@Data
public class Servidor {
    private boolean ocupado;
    private int inicioServicio;
    private int colaClientes = 0;

    public void liberar(){
        this.ocupado = false;
    }
    public void ocupar(){
        this.ocupado = true;
    }
    public void setColaClientes(int i){
        this.colaClientes = this.colaClientes + i;
    }
}

