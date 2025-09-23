package mundo;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GestionNomina {
    private ArrayList<Persona> personal = new ArrayList<>();
    private FileReader fr = new FileReader();

    public GestionNomina() {
        try {
            setPersonal(fr.readPersonas("data/empleados.data"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Persona> getPersonal() {
        return personal;
    }

    public void setPersonal(ArrayList<Persona> personal) {
        this.personal = personal;
    }

    public void addPersona(Persona persona) {
        this.personal.add(persona);
        guardarDatos();
    }

    public void removePersona(Persona persona) {
        this.personal.remove(persona);
        guardarDatos();
    }
    
    private void guardarDatos() {
        try {
            fr.writePersonas("data/empleados.data", this.personal);
        } catch (Exception e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
