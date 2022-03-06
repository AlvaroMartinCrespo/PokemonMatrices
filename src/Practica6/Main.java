package Practica6;

import java.util.*;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int opcion;

//		MENU

		boolean salir = false;
		do {
			System.out.println(" \n.:MENÚ:.");
			System.out.println("1. Mostrar debilidades");
			System.out.println("2. Mostrar eficacias");
			System.out.println("3. Mostrar toda la informacion de un tipo");
			System.out.println("4. Eficacia de un tipo a otro");
			System.out.println("5. Salir");
			System.out.println("Escoga opción: ");
			opcion = sc.nextInt();
			tratarMenu(opcion);
		} while (opcion == 1 || opcion == 2 | opcion == 3 | opcion == 4);
	}

	public static void tratarMenu(int opcion) {

		int tipo, tipo1, tipo2, tipo3, cantTipos;

		switch (opcion) {

		case 1:
			mostrarDebilidades();
			break;

		case 2:
			mostrarEficacias();
			break;

		case 3:
			mostrarInformacionAtacante();
			break;

		case 4:

			do {
				// ELEGIMOS UNO O DOS TIPOS
				System.out.println("¿Cuantos tipos tiene el oponente? 1 o 2");
				cantTipos = sc.nextInt();

				if (cantTipos != 1 && cantTipos != 2) {
					System.out.println("Introduce 1 o 2");
				}

			} while (cantTipos != 1 && cantTipos != 2);

//			INTRODUCIMOS TIPO ATACANTE
			System.out.println("Pokemon atacante");
			tipo1 = tipo();

//			INTRODUCIMOS TIPO OPONENTE
			System.out.println("Primer tipo del pokemon oponente");
			tipo2 = tipo();

//			IF PARA INTRODUCIR EL SEGUNDO MÉTODO, Y EN CASO DE QUE SE INTRODUZCA, COMPROBAR QUE NO ES EL MISMO.
			if (cantTipos == 2) {

				do {

					System.out.println("Segundo tipo del pokemon oponente");
					tipo3 = tipo();
					if (tipo3 == tipo2) {
						System.out.println("Los tipos no deben ser iguales");
					}

				} while (tipo2 == tipo3);

//				LLAMAMOS AL MÉTODO PARA QUE NOS DEVULVA LA EFICACIA
				mostrarEficaciaTipo(tipo1, tipo2, tipo3);

			} else {

//				LLAMAMOS AL MÉTODO PARA QUE NOS DEVULVA LA EFICACIA
				mostrarEficaciaTipo(tipo1, tipo2);

			}
			break;

		case 5:
			System.out.println("Se apaga el sistema.");
			break;
		default:
			System.out.println("Error, se apaga el sistema.");
			break;
		}
	}

//	ESTE MÉTODO SIRVE PARA INTRODUCIR EL TIPO

	public static int tipo() {

//		VARIABLES
		int tipo;

//		IMPRIMIMOS LOS TIPOS Y LE DAMOS A ELEGIR UNO, COMPROBAMOS QUE INTRODUCE UN NÚMERO DENTRO DEL RANGO

		do {

			System.out.println("Elige el tipo:");
			for (int i = 0; i < Constantes.tiposPokemon.length; i++) {
				System.out.println(i + ".-" + Constantes.tiposPokemon[i]);
			}
			tipo = sc.nextInt();

			if (tipo < 0 || tipo > 17) {
				System.out.println("Tiene que estar entre 0 y 17");
			}

		} while (tipo < 0 || tipo > 17);

		return tipo;

	}

	public static void mostrarDebilidades() {

//		VARIABLES
		int eleccion;
		int tipo1, tipo2;
		double efectoTotal;

//		ELEGIMOS SI TIENE UN TIPO O DOS
		do {

			System.out.println("1.- Un solo tipo  \n2.- Dos tipos");
			eleccion = sc.nextInt();

			if (eleccion != 1 && eleccion != 2) {
				System.out.println("Elige 1 o 2");
			}
		} while (eleccion != 1 && eleccion != 2);

//		MOSTRAMOS DEBILIDADES
		if (eleccion == 1) {

//			ELEGIMOS EL TIPO
			tipo1 = tipo();

//			RECORREMOS LA ARRAY EFECTIVIDADES POKEMON, INTRODUCIMOS DE COLUMNA EL TIPO ELEGIDO Y LO COMPARAMOS PARA
//			QUE NOS MUESTRE SI ES EFICAZ
			
			for (int i = 0; i < Constantes.efectividadesPokemon.length; i++) {

				if (Constantes.efectividadesPokemon[i][tipo1] == Constantes.EFICACIA_EME) {

					System.out.println("- " + Constantes.tiposPokemon[i]);

				}
			}

		} else {
//			ELIGIMOS LOS DOS TIPOS
			System.out.println("Tipo 1:");
			tipo1 = tipo();
			System.out.println("Tipo 2:");
			tipo2 = tipo();

//			RECORREMOS LA ARRAY EFECTIVIDADES POKEMON, INTRODUCIMOS DE COLUMNA TIPO ELEGIDO Y LO MULTIPLICAMOS,
//			COMPARAMOS PARA QUE NOS MUESTRE SI ES EFICAZ 
			
			for (int i = 0; i < Constantes.efectividadesPokemon.length; i++) {

				efectoTotal = Constantes.efectividadesPokemon[i][tipo1] * Constantes.efectividadesPokemon[i][tipo2];

				if (efectoTotal >= Constantes.EFICACIA_EME) {

					System.out.println("- " + Constantes.tiposPokemon[i] + ": x " + efectoTotal);

				}
			}
		}
	}

	public static void mostrarEficacias() {

		int tipo;
		// ELEGIMOS EL TIPO
		tipo = tipo();

		// SE MUESTRAN LOS TIPOS
		for (int i = 0; i < Constantes.efectividadesPokemon.length; i++) {

			if (Constantes.efectividadesPokemon[tipo][i] == Constantes.EFICACIA_EME) {
				System.out.println("- " + Constantes.tiposPokemon[i]);
			}

		}

	}

	public static void mostrarInformacionAtacante() {

		int tipo;
		// ELEGIMOS EL TIPO
		tipo = tipo();

		// MOSTRAMOS LA INFORMACIÓN
		for (int i = 0; i < Constantes.efectividadesPokemon.length; i++) {
			System.out.println(
					"- " + Constantes.tiposPokemon[i] + " " + eficacia(Constantes.efectividadesPokemon[tipo][i]));
		}

	}

	public static String eficacia(double eficaz) {

//		VARIABLES

		String debilidad;

//		ESTE MÉTODO SIRVE PARA CONVERTIR LA ARRAY EFECTIVIDADES POKEMON A UN MENSAJE, INTRODUCIMOS EL NUMERO QUE NOS 
//		HA DEVUELTO LA ARRAY EFECTIVIDADES POKEMON (0, 0.5, 1, 2) Y LA MULTIPLICACIÓN DEL SEGUNDO MÉTODO DE 
//		MOSTRAR SOBRECARGA TIPO (0.25, 4)

//		COMPARAMOS EL NÚMERO CON LAS EFICACIAS, DESPUÉS LE PASAMOS EL ÍNDICE DE LA EFICACIA A LA ARRAY DE MENSAJES
//		EFICACIAS Y NOS DEVOLVERÁ EL MENSAJE DE LA EFICACIA

		if (eficaz == Constantes.EFICACIA_DE) {

			debilidad = Constantes.MENSAJES_EFICACIAS[Constantes.INDICE_DE];

		} else if (eficaz == Constantes.EFICACIA_EME) {

			debilidad = Constantes.MENSAJES_EFICACIAS[Constantes.INDICE_EME];

		} else if (eficaz == Constantes.EFICACIA_NEUTRO) {

			debilidad = Constantes.MENSAJES_EFICACIAS[Constantes.INDICE_NEUTRO];

		} else if (eficaz == Constantes.EFICACIA_NME) {

			debilidad = Constantes.MENSAJES_EFICACIAS[Constantes.INDICE_NME];

		} else if (eficaz == Constantes.EFICACIA_DNE) {

			debilidad = Constantes.MENSAJES_EFICACIAS[Constantes.INDICE_DNE];

		} else {

			debilidad = Constantes.MENSAJES_EFICACIAS[Constantes.INDICE_NA];

		}
		return (debilidad);

	}

//	SOBRECARGAMOS ESTOS DOS MÉTODOS, SE PODRIA HACER PIDIENDO EN CADA METODO EL TIPO DEL ATACANTE Y EL TIPO DEL OPONENTE,
//	EN CASO DEL SEGUNDO METODO PIDIENDO EL TIPO DEL ATACANTE Y LOS DOS TIPOS DEL OPONENTE, LLAMANDO AL METODO TIPO().
//	ELEGIMOS DESDE EL SWITCH DEL MENU SI EL OPONENTE TIENE UN TIPO O DOS TIPOS Y LOS INTRODUCIMOS DESDE EL MENU.

	public static void mostrarEficaciaTipo(int Atacante, int Oponente) {

//		VARIABLES
		double eficaz;

//	INTRODUCIMOS EL TIPO DEL ATACAnTE Y EL TIPO DEL OPONENTE EN LA ARRAY DE EFECTIVIDADES POKEMON, ASI HALLAMOS 
//	EL NUMERO QUE NOS DICE SI ES EFICAZ, MUY EFICAZ,... Y SE LO PASAMOS AL METODO EFICACIA E IMPRIMIMOS LA EFICACACIA.

		eficaz = Constantes.efectividadesPokemon[Atacante][Oponente];

		System.out.println(eficacia(eficaz));

	}

	public static void mostrarEficaciaTipo(int Atacante, int Oponente1, int Oponente2) {

//		VARIABLES
		double eficaz;

//		ESTE MÉTODO ES IGUAL QUE EL ANTERIOR, PERO CON LA DIFERENCIA QUE PRIMERO BUSCAMOS EN LA ARRAY EFECTIVIDADES POKEMON
//		LO EFICAZ QUE ES CONTRA EL PRIMER TIPO, DESPUÉS BUSCAMOS LO EFICAZ QUE ES CONTRA EL SEGUNDO TIPO Y LO MULTIPLICAMOS

		eficaz = Constantes.efectividadesPokemon[Atacante][Oponente1]
				* Constantes.efectividadesPokemon[Atacante][Oponente2];

		System.out.println(eficacia(eficaz));
	}

}
