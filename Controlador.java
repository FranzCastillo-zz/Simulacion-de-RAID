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

    private int encontrarNumObjetivo(){
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
        return numObjetivo;
    }

    private void verificarMuerte(Combatientes objetivo, int numObjetivo){
        if(objetivo.getVida() <= 0){ //Se muere
            enemigos.remove(numObjetivo);
            v.mostrarDespedida(objetivo);
            ultimaRonda += "| " + jugadorActual.nombre + " -KILL> " + objetivo.nombre + " | ";
        }
    }
    private void verificarMuerteJugador(Jugador actual, int numJugador){
        if(actual.getVida() <= 0){ //Se muere
            jugadores.remove(numJugador);
            v.mostrarDespedida(actual);
            ultimaRonda += "| " + actual.nombre + " DIED | ";
        }
    }
    private void enemigoAtacaJugador(Enemigo enemigo){
        jugadorActual.bajarVida(enemigo.getAtaque());
        int ataque = enemigo.getAtaque();
        v.mostrarDanioRecibido(jugadorActual, ataque);
        ultimaRonda += "| " + enemigo.nombre + " -ATK> " + jugadorActual.nombre + " | ";
        verificarMuerteJugador(jugadorActual, 0);    
    }
    private void enemigoUsaHabilidad(Enemigo enemigo){
        String nombreClase = "";
        nombreClase = enemigo.habilidad.getClass().getName(); 
        if(nombreClase.equals("Terremoto")){
            for (Jugador jugador : jugadores) {
                enemigo.usarHabilidad(jugador);
                verificarMuerteJugador(jugador, jugadores.indexOf(jugador));
            }
            ultimaRonda += "| " + enemigo.nombre + " -EARTHQK> ALL PLAYERS | ";
            
        }else if(nombreClase.equals("ElSoporte")){
            for (Enemigo enemigo2 : enemigos) {
                enemigo2.subirVida(10);
            }
            ultimaRonda += "| " + enemigo.nombre + " -HEAL> ALL ENEMIES | ";
        }else if(nombreClase.equals("RobarVida")){
            enemigo.usarHabilidad(jugadorActual);
            ultimaRonda += "| " + enemigo.nombre + " -LIFESTEAL> " + jugadorActual.nombre +" | ";
        }else if(nombreClase.equals("GolpeLetal")){
            enemigo.usarHabilidad(jugadorActual);
            ultimaRonda += "| " + jugadorActual.nombre + " -CRIT> " + jugadorActual.nombre + " | ";
        }
        verificarMuerteJugador(jugadorActual, 0);
        v.mostrarHabilidadUsada(nombreClase);    
    }
    private void enemigoSaltaTurno(Enemigo enemigo){
        ultimaRonda += "| " + enemigo.nombre + " -SKIP- "  + " | ";
        v.mostrarSaltarRonda(enemigo, ronda);
    }
    private void jefeUsaSegundaHabilidad(Jefe enemigo){
        String nombreClase = "";
        nombreClase = enemigo.habilidad.getClass().getName(); 
        if(nombreClase.equals("Terremoto")){
            for (Jugador jugador : jugadores) {
                enemigo.usarSegundaHabilidad(jugador);
                verificarMuerteJugador(jugador, jugadores.indexOf(jugador));
            }
            ultimaRonda += "| " + enemigo.nombre + " -EARTHQK> ALL PLAYERS | ";
            
        }else if(nombreClase.equals("ElSoporte")){
            for (Enemigo enemigo2 : enemigos) {
                enemigo2.subirVida(10);
            }
            ultimaRonda += "| " + enemigo.nombre + " -HEAL> ALL ENEMIES | ";
        }else if(nombreClase.equals("RobarVida")){
            enemigo.usarSegundaHabilidad(jugadorActual);
            ultimaRonda += "| " + enemigo.nombre + " -LIFESTEAL> " + jugadorActual.nombre +" | ";
        }else if(nombreClase.equals("GolpeLetal")){
            enemigo.usarSegundaHabilidad(jugadorActual);
            ultimaRonda += "| " + jugadorActual.nombre + " -CRIT> " + jugadorActual.nombre + " | ";
        }
        verificarMuerteJugador(jugadorActual, 0);
        v.mostrarHabilidadUsada(nombreClase);     }
    private void jugarRonda(){
        ultimaRonda = "";
        v.mostrarNumeroDeRonda(ronda);
        v.mostrarEstadisticasEnemigos(enemigos);
        v.mostrarEstadisticasJugadores(jugadores);
        //TURNO DEL JUGADOR
        boolean opcionValida = false;
        int opcion;
        while(!opcionValida){
            opcion = v.mostrarMenuJugador();
            switch(opcion){
                case 1: // ATACAR
                    int numObjetivo = encontrarNumObjetivo();
                    Enemigo objetivo = enemigos.get(numObjetivo); 
                    int ataque = jugadorActual.getAtaque();
                    objetivo.bajarVida(ataque);
                    v.mostrarDanioRecibido(objetivo, ataque);
                    ultimaRonda += "| " + jugadorActual.nombre + " -ATK> " + objetivo.nombre + " | ";
                    verificarMuerte(objetivo, numObjetivo);
                    opcionValida = true;
                    break;
                case 2: // USAR ITEM
                    boolean itemValido = false;
                    ArrayList<Items> items = jugadorActual.getInventario();
                    if(!items.isEmpty()){
                        while(!itemValido){
                            items = jugadorActual.getInventario();
                            int posItem = v.mostrarMenuItems(items) - 1; //Devuelve una posicion adelante
                            if(posItem >= 0 && posItem <= items.size()){
                                Enemigo objetivoItem;
                                Items paraUsar = items.get(posItem);
                                String nombreClase = paraUsar.getClass().getName(); 
                                if(nombreClase.equals("DobleAtaque")){
                                    int numObjetivoItem = encontrarNumObjetivo();
                                    objetivoItem = enemigos.get(numObjetivoItem);
                                    paraUsar.usarItem(objetivoItem);
                                    ultimaRonda += "| " + jugadorActual.nombre + " -ATK²> " + objetivoItem.nombre + " | ";
                                    verificarMuerte(objetivoItem, numObjetivoItem);
                                }else if(nombreClase.equals("ShotDeFuerza")){
                                    paraUsar.usarItem(jugadorActual);
                                    ultimaRonda += "| " + jugadorActual.nombre + " -STRENGTH ↑- | ";
                                }else if(nombreClase.equals("NuevosMusculos")){
                                    paraUsar.usarItem(jugadorActual);
                                    ultimaRonda += "| " + jugadorActual.nombre + " -NEW MSCLS- | ";
                                }else if(nombreClase.equals("PocionCuracion")){
                                    paraUsar.usarItem(jugadorActual);
                                    ultimaRonda += "| " + jugadorActual.nombre + " -HEAL- | ";
                                }else if(nombreClase.equals("PocionDebilitar")){
                                    int numObjetivoItem = encontrarNumObjetivo();
                                    objetivoItem = enemigos.get(numObjetivoItem);
                                    paraUsar.usarItem(objetivoItem);
                                    ultimaRonda += "| " + jugadorActual.nombre + " -WEAK'D> " + objetivoItem.nombre + " |";
                                }
                                v.mostrarItemUsado(nombreClase);
                                items.remove(paraUsar);
                                itemValido = true;
                            }else{
                                v.mostrarOpcionInvalida();
                            }
                        }
                        opcionValida = true;                    
                    }else{
                        v.mostrarNoMasItems();
                    }
                    
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
        //TURNO DEL ENEMIGO
        
        for (Enemigo enemigo : enemigos) {
            String n = enemigo.getClass().getName();
            opcion = randomEntre(0, 100);
            if(n.equals("Comadre") || n.equals("Patron")){
                if(opcion < 50){
                    enemigoAtacaJugador(enemigo);
                }else if(opcion <= 50 && opcion <= 65){
                    enemigoUsaHabilidad(enemigo);
                }else if(opcion <= 65 && opcion <= 80){
                    jefeUsaSegundaHabilidad((Jefe)enemigo);
                }else{
                    enemigoSaltaTurno(enemigo);
                }
            }else{
                if(opcion < 50){ //Atacar al jugador
                    enemigoAtacaJugador(enemigo);
                }else if(opcion >= 50 && opcion <= 70){ //HABILIDAD 50-70
                       enemigoUsaHabilidad(enemigo);
                }else{ // saltar turno
                    enemigoSaltaTurno(enemigo);
                }
    
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
    
    /*private void jugarRAID(){
        //----------------CREANDO JUGADORES-----------------
        boolean cantidadDeJugadoresExtraValidos = false;
        int cantidadNuevosjugadores = v.pedirCantidadNuevosJugadores();
        if(cantidadNuevosjugadores >= 0 && cantidadNuevosjugadores <= 2){ //VERIFICA QUE HAYA ENTRE 0 Y 2 JUGADORES PARA CUMPLIR CON MAX 3
            for(int i = 0; i < cantidadNuevosjugadores; i++){
                v.mostrarQueJugadorSeEstaCreando(i + 2);
                jugadoresEnRaid.add(crearJugador());
            }
        }
        //------------------JUGABILIDAD------------------
    }*/
    
    public void ejecutar(){
        v.mostrarSaludo();
        crearJugador();
        boolean tipoRondaValido = false;
        while(!tipoRondaValido){
            int tipoRonda = v.pedirTipoRonda();
            switch(tipoRonda){
                case 1:
                    crearEnemigosDeBatallaNormal();
                    jugadorActual = jugadores.get(0);
                    do{
                        jugarRonda();
                        v.mostrarHistorial(historial);
                    }while(!jugadores.isEmpty() && !enemigos.isEmpty());
                    v.mostrarGG(ronda);
                    tipoRondaValido = true;
                    break;
                case 2:
                    //raid();
                    tipoRondaValido = true;
                    break;
                default:
                    v.mostrarOpcionInvalida();
                    break;
            }
        }
    }
}
