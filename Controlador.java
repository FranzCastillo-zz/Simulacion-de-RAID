import java.util.ArrayList;
import java.util.Random;
public class Controlador {
    private Vista v;
    private ArrayList<String> historial;
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private ArrayList<Enemigo> enemigos;   
    private String ultimaRonda; 
    private int ronda;

    public Controlador(){
        v = new Vista();
        ronda = 1;
        historial = new ArrayList<>();
        jugadores = new ArrayList<>();
        enemigos = new ArrayList<>();
        historial.add("");
        historial.add("");
        historial.add("");
    }
    private static int randomEntre(int min, int max) {
        Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

    private void crearJugador(){
        String nombre = v.pedirNombre();
        Jugador creado = null;
        boolean claseValida = false;
        while(!claseValida){
            int opcion = v.pedirClase();
            switch(opcion){
                case 1:
                    creado = new Guerrero(nombre);
                    claseValida = true;
                    break;
                case 2:
                    creado = new Explorador(nombre);
                    claseValida = true;
                    break;
                case 3:
                    creado = new Cazador(nombre);
                    claseValida = true;
                    break;
                default:
                    v.mostrarOpcionInvalida();
                    break;
            }
        }
        jugadores.add(creado);
        v.mostrarSaludo(creado);
    }

    private void crearEnemigosDeBatallaNormal(){
        int numEnemigos = randomEntre(1, 3);
        int probabilidadJefe = randomEntre(0, 100);
        int i = 1;
        if(probabilidadJefe <= 30){
            int tipoJefe = randomEntre(0, 100);
            if(tipoJefe < 50){
                enemigos.add(new Patron());
            }else{
                enemigos.add(new Comadre());
            }
            i++;
        }
        while(i <= numEnemigos){
            int tipoEnemigo = randomEntre(0, 100);
            if(tipoEnemigo < 50){
                enemigos.add(new K70());
            }else{
                enemigos.add(new Chorizo());
            }
            i++;
        }
        for (Enemigo enemigo : enemigos) {
            v.mostrarSaludo(enemigo);
        }
    }

    private void jugarRonda(){
        ultimaRonda = "";
        v.mostrarNumeroDeRonda(ronda);
        v.mostrarEstadisticasEnemigos(enemigos);
        v.mostrarEstadisticasJugadores(jugadores);
        //TURNO DEL JUGADOR
        boolean opcionValida = false;
        while(!opcionValida){
            int opcion = v.mostrarMenuJugador();
            switch(opcion){
                case 1: // ATACAR
                    int numObjetivo = -1;
                    boolean objetivoValido = false;
                    while(!objetivoValido){
                        numObjetivo = v.pedirObjetivo(enemigos) - 1; // Devuelve una posicion adelante
                        if(numObjetivo >= 0 && numObjetivo < enemigos.size()){
                            objetivoValido = true;
                        }else{
                            v.mostrarOpcionInvalida();
                        }
                    }
                    Enemigo objetivo = enemigos.get(numObjetivo); 
                    int ataque = jugadorActual.getAtaque();
                    objetivo.bajarVida(ataque);
                    v.mostrarDanioRecibido(objetivo, ataque);
                    ultimaRonda += "| " + jugadorActual.nombre + " -ATK> " + objetivo.nombre + " | ";
                    if(objetivo.getVida() <= 0){ //Se muere
                        enemigos.remove(numObjetivo);
                        v.mostrarDespedida(objetivo);
                        ultimaRonda += "| " + jugadorActual.nombre + " -KILL> " + objetivo.nombre + " | ";
                    }
                    opcionValida = true;
                    break;
                case 2: // USAR ITEM
                    /*boolean itemValido = false;
                    ArrayList<Items> items = new ArrayList<>();
                    while(!itemValido){
                        items = jugadorActual.getInventario();
                        int posItem = v.mostrarMenuItems(items) - 1; //Devuelve una posicion adelante
                        if(posItem >= 0 && posItem <= items.size()){
                            Items paraUsar = items.get(posItem);
                            
                            //v.mostrarItemUsado(jugador.usarItem(paraUsar));
                            items.remove(paraUsar);
                            ultimaRonda += "| " + jugadorActual.nombre + " -ITEM> " + objetivo.nombre + " | ";
                            opcionValida = true;
                        }else{
                            v.mostrarOpcionInvalida();
                        }
                    }*/
                    break;
                case 3: // SALTAR TURNO
                    ultimaRonda += "| " + jugadorActual.nombre + " -SKIP- "  + " | ";
                    v.mostrarSaltarRonda(jugadorActual, ronda);
                    opcionValida = true;
                break;
                case 4: // SALIR
                    System.exit(1);
                break;
                default:
                    v.mostrarOpcionInvalida();
                    break;
            }
        }

        //FIN DE RONDA
        for(int i = 0; i < historial.size(); i++){
            if(i <= 1){
                historial.set(i, historial.get(i + 1));
            }else if(i == 2){
                historial.set(2, ultimaRonda);
            }
        }
        ronda++;
    }

    private void batallaNormal(){
        crearEnemigosDeBatallaNormal();
        do{
            jugarRonda();
            v.mostrarHistorial(historial);
        }while(!jugadores.isEmpty() && !enemigos.isEmpty());
        v.mostrarGG(ronda);
    }
    private void raid(){

    }
    
    public void ejecutar(){
        v.mostrarSaludo();
        crearJugador();
        boolean tipoRondaValido = false;
        while(!tipoRondaValido){
            int tipoRonda = v.pedirTipoRonda();
            switch(tipoRonda){
                case 1:
                    jugadorActual = jugadores.get(0);
                    batallaNormal();
                    tipoRondaValido = true;
                    break;
                case 2:
                    raid();
                    tipoRondaValido = true;
                    break;
                default:
                    v.mostrarOpcionInvalida();
                    break;
            }
        }
    }
}
