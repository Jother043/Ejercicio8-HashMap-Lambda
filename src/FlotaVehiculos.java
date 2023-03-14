
import java.util.HashMap;
import java.util.List;

public class FlotaVehiculos {
	
	private HashMap<String, Vehiculo> vehiculos;

	/**
	 *  Variable que guarda donde se insertará el siguiente vehiculo
	 *  Empieza con 0 y si vale vehiculos.length el almacen esta lleno
	 */
	private int numeroRealDeVehiculos;

	public FlotaVehiculos() throws VehiculoException {

		vehiculos = new HashMap<>();
		numeroRealDeVehiculos = 0;
	}

	public void introducirVehiculo(Vehiculo vehiculo) throws VehiculoException {

		if(vehiculos.containsKey(vehiculo.getMatricula())){
			throw new VehiculoException("El vehiculo que intenta introducir ya existe.");
		}
		vehiculos.put(vehiculo.getMatricula(), vehiculo);
		numeroRealDeVehiculos++;
	}

	private Vehiculo buscarMatricula(String matricula) {
		return vehiculos.get(matricula);
	}

	public double precioAlquiler(String matricula, int dias) throws VehiculoException {
		Vehiculo v	= buscarMatricula(matricula);
		if(v == null){
			throw new VehiculoException("No existe el vehiculo");
		}
		return v.calcularAlquiler(dias);
	}

	public List<Vehiculo> listadoOrdenadoMatricula (){
		return vehiculos.values().stream().sorted((v1,v2) -> v1.getMatricula().compareTo(v2.getMatricula())).toList();
	}

	public List<Furgoneta> listadoFurgonetaOrdenadaPma(){
		return vehiculos.values().stream().filter(v -> v instanceof Furgoneta).map(v -> (Furgoneta)v).sorted((f1,f2) -> f1.getPma() - f2.getPma()).toList();
	}

	public List<TipoGama> listadoVehiculosOrdenadoGama(){
		return vehiculos.values().stream().map(Vehiculo::getGama).sorted(Enum::compareTo).toList();
	}
	
	public String toString() {
		
		return null;
	}
}
