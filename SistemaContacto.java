// Integrantes: Lucas Robles, Benjamin Robles, Oscar Rojas

import java.io.*;

public class SistemaContacto {
	public static void main(String args[]) throws IOException {
		int capacidad = 10;
		int numPersonas = 0;
		int opcion = 0;
		boolean flag = true;
		
		Contacto arrContactos[] = new Contacto[capacidad];
		
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			System.out.println("1. Agregar contacto");
			System.out.println("2. Listar contactos");
			System.out.println("3. Calcular edad promedio");
			System.out.println("4. Buscar contacto");
			System.out.println("5. Salir");
			
			System.out.print("Ingrese opción: ");
			opcion = Integer.parseInt(lector.readLine());
			
			switch(opcion) {
				case 1:
					if(numPersonas == capacidad) {
						System.out.println("Error: no queda espacio en el arreglo\n");
						break;
					}
					
					System.out.print("Ingrese nombre: ");
					String nombre = lector.readLine();
					
					System.out.print("Ingrese teléfono: ");
					int fono = Integer.parseInt(lector.readLine());
					
					System.out.print("Ingrese edad: ");
					int edad = Integer.parseInt(lector.readLine());
					
					System.out.println();
					
					Contacto contacto = new Contacto(nombre, fono, edad);
					arrContactos[numPersonas] = contacto;
					numPersonas++;
					break;
					
				case 2:
					for(int i = 0; i < numPersonas; i++)
						arrContactos[i].mostrarDatos();
					break;
					
				case 3:
					if(numPersonas == 0) {
						System.out.println("Error: no se han agregado contactos\n");
						break;
					}
					
					int sumaEdad = 0;
					for(int i = 0; i < numPersonas; i++)
						sumaEdad += arrContactos[i].getEdad();
					
					int promedioEdad = sumaEdad / numPersonas;
					System.out.println("Edad promedio: " + promedioEdad + "\n");
					break;
					
				case 4:
					System.out.print("Ingrese número del contacto: ");
					int clave = Integer.parseInt(lector.readLine());
					
					boolean existe = false;
					
					for(int i = 0; i < numPersonas; i++) {
						if(arrContactos[i].getFono() == clave) {
							arrContactos[i].mostrarDatos();
							existe = true;
							break;
						}
					}
					
					if(!existe)
						System.out.println("No se encontraron datos de la persona\n");
					break;
					
				case 5:
					flag = false;
					break;
					
				default:
					System.out.println("Opción no válida\n");
					break;
			}
			
		} while(flag);
		
	}
	
}
