package mundo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class FileReader {

    public FileReader() {}

    public ArrayList<Persona> readPersonas(String path) {
        ArrayList<Persona> personas = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path)) {
            Properties props = new Properties();
            props.load(fis);

            int i = 1;
            while (true) {
                String prefix = "persona" + i;

                String nombre = props.getProperty(prefix + ".nombre");
                String apellido = props.getProperty(prefix + ".apellido");
                String cedula = props.getProperty(prefix + ".cedula");
                String cargo = props.getProperty(prefix + ".cargo");
                String email = props.getProperty(prefix + ".email");
                String telefono = props.getProperty(prefix + ".telefono");
                String salarioBase = props.getProperty(prefix + ".salarioBase");
                String horasExtras = props.getProperty(prefix + ".horasExtras");
                String deducciones = props.getProperty(prefix + ".deducciones");
                
                String sueldo = props.getProperty(prefix + ".sueldo");

                if (nombre == null && apellido == null && email == null && telefono == null) {
                    break;
                }

                Persona p;
                try {
                    if (cedula != null && cargo != null && salarioBase != null && horasExtras != null && deducciones != null) {
                        p = new Persona(
                            nombre != null ? nombre : "",
                            apellido != null ? apellido : "",
                            cedula,
                            cargo,
                            email != null ? email : "",
                            telefono != null ? telefono : "",
                            Double.parseDouble(salarioBase),
                            Double.parseDouble(horasExtras),
                            Double.parseDouble(deducciones)
                        );
                    } else {
                        p = new Persona(
                            nombre != null ? nombre : "",
                            apellido != null ? apellido : "",
                            email != null ? email : "",
                            telefono != null ? telefono : "",
                            sueldo != null ? sueldo : "0"
                        );
                        p.setCedula(cedula != null ? cedula : "");
                        p.setCargo(cargo != null ? cargo : "");
                    }
                } catch (NumberFormatException e) {
                    p = new Persona(
                        nombre != null ? nombre : "",
                        apellido != null ? apellido : "",
                        email != null ? email : "",
                        telefono != null ? telefono : "",
                        sueldo != null ? sueldo : "0"
                    );
                    p.setCedula(cedula != null ? cedula : "");
                    p.setCargo(cargo != null ? cargo : "");
                }
                personas.add(p);

                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return personas;
    }
    
    public void writePersonas(String path, ArrayList<Persona> personas) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            Properties props = new Properties();
            
            for (int i = 0; i < personas.size(); i++) {
                Persona p = personas.get(i);
                String prefix = "persona" + (i + 1);
                
                props.setProperty(prefix + ".nombre", p.getNombre() != null ? p.getNombre() : "");
                props.setProperty(prefix + ".apellido", p.getApellido() != null ? p.getApellido() : "");
                props.setProperty(prefix + ".cedula", p.getCedula() != null ? p.getCedula() : "");
                props.setProperty(prefix + ".cargo", p.getCargo() != null ? p.getCargo() : "");
                props.setProperty(prefix + ".email", p.getEmail() != null ? p.getEmail() : "");
                props.setProperty(prefix + ".telefono", p.getTelefono() != null ? p.getTelefono() : "");
                props.setProperty(prefix + ".salarioBase", String.valueOf(p.getSalarioBase()));
                props.setProperty(prefix + ".horasExtras", String.valueOf(p.getHorasExtras()));
                props.setProperty(prefix + ".deducciones", String.valueOf(p.getDeducciones()));
                props.setProperty(prefix + ".sueldo", String.valueOf(p.getSalarioBase()));
            }
            
            props.store(fos, "Archivo de empleados");
            
        } catch (IOException e) {
            System.err.println("Error al escribir archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}