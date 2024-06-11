package simulacion.ClienteServidor.Models;

import lombok.Data;

import java.util.*;

@Data
public class Servidor {
    private boolean ocupado;
    private float inicioServicio;
    private int lugaresDisponibles = 10;
    private LinkedList<Cliente> colaClientes = new LinkedList<>();
    public Reportes reporte = new Reportes();


    public void liberar(){
        this.ocupado = false;
    }
    public void ocupar(float inicio){
        this.ocupado = true;
        this.inicioServicio = inicio;
    }
    public void addColaClientes(Cliente cliente) {
        colaClientes.add(cliente);
    }

    public boolean lugarEnColaCliente(){
        if(colaClientes.size()<lugaresDisponibles)
            return true;
        return false;
    }

    public void addFirts(Cliente cliente) {
        this.colaClientes.addFirst(cliente);
    }

    public void setLugaresDisponibles(int agregado) {
        this.lugaresDisponibles += agregado;
    }

    public String toString(){
        return "ocupado: " + this.ocupado + " /lugares disponibles: " + this.lugaresDisponibles + " /clientes en cola " + this.colaClientes;
    }
}

