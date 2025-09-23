package mundo;

public class Persona {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private double salarioBase;
    private double horasExtras;
    private double deducciones;
    private String cedula;
    private String cargo;
    
	public Persona(String nombre, String apellido, String cedula, String cargo, String email, 
	               String telefono, double salarioBase, double horasExtras, double deducciones) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.cargo = cargo;
		this.email = email;
		this.telefono = telefono;
		this.salarioBase = salarioBase;
		this.horasExtras = horasExtras;
		this.deducciones = deducciones;
	}
	
	public Persona(String nombre, String apellido, String email, String telefono, String sueldo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.cedula = "";
		this.cargo = "";
		try {
			this.salarioBase = Double.parseDouble(sueldo);
		} catch (NumberFormatException e) {
			this.salarioBase = 0.0;
		}
		this.horasExtras = 0.0;
		this.deducciones = 0.0;
	}
		
	public double calcularSalarioNeto() {
		return salarioBase + horasExtras - deducciones;
	}
	
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula + 
		       ", cargo=" + cargo + ", email=" + email + ", telefono=" + telefono + 
		       ", salarioNeto=" + calcularSalarioNeto() + "]";
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public double getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}
	public double getHorasExtras() {
		return horasExtras;
	}
	public void setHorasExtras(double horasExtras) {
		this.horasExtras = horasExtras;
	}
	public double getDeducciones() {
		return deducciones;
	}
	public void setDeducciones(double deducciones) {
		this.deducciones = deducciones;
	}
	
	public String getSueldo() {
		return String.valueOf(salarioBase);
	}
	
	public void setSueldo(String sueldo) {
		try {
			this.salarioBase = Double.parseDouble(sueldo);
		} catch (NumberFormatException e) {
			this.salarioBase = 0.0;
		}
	}
}
