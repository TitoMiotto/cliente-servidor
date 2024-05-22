package simulacion.ClienteServidor.Models;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
public class Servidor {
    private boolean ocupado;
    private int inicioServicio;
    private Queue<Cliente> colaClientes = new LinkedList<>();

    public void liberar(){
        this.ocupado = false;
    }
    public void ocupar(){
        this.ocupado = true;
    }
    public void addColaClientes(Cliente cliente){
        this.colaClientes.add(cliente);
    }
}
