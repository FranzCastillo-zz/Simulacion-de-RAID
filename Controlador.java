import java.util.ArrayList;
import java.util.Random;
public class Controlador {
    private Vista v;
    private ArrayList<String> historial;
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private ArrayList<Enemigo> enemigos;   
    private String ultimaRonda;
    private RaidBoss raidBoss;
    private ArrayList<Acompaniantes> acompaniantes;
    private Acompaniantes mascotaActual;
    private int ronda;

    public Controlador(){
        v = new Vista();
        ronda = 1;
        historial = new ArrayList<>();
        jugadores = new ArrayList<>();
        enemigos = new ArrayList<>();
        acompaniantes = new ArrayList<>();
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
                    acompaniantes.add(creado.getMascota());
                    mascotaActual = creado.getMascota();
                    claseValida = true;
                    break;
                case 2:
                    creado = new Explorador(nombre);
                    acompaniantes.add(creado.getMascota());
                    mascotaActual = creado.getMascota();
                    claseValida = true;
                    break;
                case 3:
                    creado = new Cazador(nombre);
                    acompaniantes.add(creado.getMascota());
                    mascotaActual = creado.getMascota();
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
    private void crearEnemigos(int li, int ls){
        int numEnemigos = randomEntre(li, ls);
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
        if(actual.getVida() <= 0 && actual != null){ //Se muere
            if(!jugadores.isEmpty()){
                jugadores.remove(numJugador);
                v.mostrarDespedida(actual);
                ultimaRonda += "| " + actual.nombre + " DIED | ";    
            }
        }
    }
    private void verificarMuerteMascota(){
        if(mascotaActual.getVida() <= 0 && mascotaActual != null){ //Se muere
            v.mostrarDespedida(mascotaActual);
            ultimaRonda += "| " + mascotaActual.nombre + " DIED | ";
            mascotaActual.setRondasMuerto(4);
        }
    }

    private void escogerJugadorObjetivoEnemigo(){
        int numObjetivo = randomEntre(0, jugadores.size() - 1);
        jugadorActual = jugadores.get(numObjetivo);
    }
    private void escogerMascotaObjetivoEnemigo(){
        int numObjetivo = randomEntre(0, acompaniantes.size() - 1);
        mascotaActual = acompaniantes.get(numObjetivo);
    }
    private void enemigoAtacaJugador(Enemigo enemigo){
        int jugadorOMascota = randomEntre(0, 100);
        escogerJugadorObjetivoEnemigo();
        escogerMascotaObjetivoEnemigo();
        if(jugadorOMascota > 0 && mascotaActual != null){ //CAMBIAR A 65 PARA MASCOTA
            if(jugadorOMascota > 0 && !mascotaActual.isDead()){ //mascota 65
                mascotaActual.bajarVida(enemigo.getAtaque());
                int ataque = enemigo.getAtaque();
                v.mostrarDanioRecibido(jugadorActual, ataque);
                ultimaRonda += "| " + enemigo.nombre + " -ATK> " + mascotaActual.nombre + " | ";
                verificarMuerteMascota();
            }else{
                jugadorActual.bajarVida(enemigo.getAtaque());
                int ataque = enemigo.getAtaque();
                v.mostrarDanioRecibido(jugadorActual, ataque);
                ultimaRonda += "| " + enemigo.nombre + " -ATK> " + jugadorActual.nombre + " | ";
                verificarMuerteJugador(jugadorActual, 0);
            }
        }else{
            jugadorActual.bajarVida(enemigo.getAtaque());
            int ataque = enemigo.getAtaque();
            v.mostrarDanioRecibido(jugadorActual, ataque);
            ultimaRonda += "| " + enemigo.nombre + " -ATK> " + jugadorActual.nombre + " | ";
            verificarMuerteJugador(jugadorActual, 0);
        }
        
    }
    private void enemigoUsaHabilidad(Enemigo enemigo){
        int jugadorOMascota = randomEntre(0, 100);
        String nombreClase = "";
        nombreClase = enemigo.habilidad.getClass().getName(); 
        escogerJugadorObjetivoEnemigo();
        escogerMascotaObjetivoEnemigo();
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
            if(jugadorOMascota > 65){
                if(mascotaActual != null && !mascotaActual.isDead()){
                    enemigo.usarHabilidad(mascotaActual);
                    ultimaRonda += "| " + enemigo.nombre + " -LIFESTEAL> " + mascotaActual.nombre +" | ";
                    verificarMuerteMascota();
                }else{
                    enemigo.usarHabilidad(jugadorActual);
                    ultimaRonda += "| " + enemigo.nombre + " -LIFESTEAL> " + jugadorActual.nombre +" | ";
                }
            }else{
                enemigo.usarHabilidad(jugadorActual);
                ultimaRonda += "| " + enemigo.nombre + " -LIFESTEAL> " + jugadorActual.nombre +" | ";
            }
        }else if(nombreClase.equals("GolpeLetal")){
            if(jugadorOMascota > 65){
                if(mascotaActual != null && !mascotaActual.isDead()){
                    enemigo.usarHabilidad(mascotaActual);
                    ultimaRonda += "| " + jugadorActual.nombre + " -CRIT> " + mascotaActual.nombre + " | ";
                    verificarMuerteMascota();
                }else{
                    enemigo.usarHabilidad(jugadorActual);
                    ultimaRonda += "| " + enemigo.nombre + " -CRIT> " + jugadorActual.nombre + " | ";
                }
            }else{
                enemigo.usarHabilidad(jugadorActual);
                ultimaRonda += "| " + enemigo.nombre + " -CRIT> " + jugadorActual.nombre + " | ";
            }
        }
        verificarMuerteJugador(jugadorActual, 0);
        v.mostrarHabilidadUsada(nombreClase);    
    }
    private void turnoMascota(Combatientes objetivo){
        if(mascotaActual != null){
            if(mascotaActual.getRondasMuerto() == 0){
                mascotaActual.usarHabilidad(jugadorActual);
                ultimaRonda += "| " + mascotaActual.nombre + " -LIFESTEAL> " + objetivo.nombre + " | ";    
            }else{
                v.mostrarMascotaRegenerandose(mascotaActual.getRondasMuerto());
            }
        }
    }
    private void enemigoSaltaTurno(Enemigo enemigo){
        ultimaRonda += "| " + enemigo.nombre + " -SKIP- "  + " | ";
        v.mostrarSaltarRonda(enemigo, ronda);
    }
    private void jefeUsaSegundaHabilidad(Jefe enemigo){
        int jugadorOMascota = randomEntre(0, 100);
        String nombreClase = "";
        escogerJugadorObjetivoEnemigo();
        escogerMascotaObjetivoEnemigo();
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
            if(jugadorOMascota > 65){
                if(mascotaActual != null && !mascotaActual.isDead()){
                    enemigo.usarHabilidad(mascotaActual);
                    ultimaRonda += "| " + enemigo.nombre + " -LIFESTEAL> " + mascotaActual.nombre +" | ";
                    verificarMuerteMascota();    
                }else{
                    enemigo.usarHabilidad(jugadorActual);
                    ultimaRonda += "| " + enemigo.nombre + " -LIFESTEAL> " + jugadorActual.nombre +" | ";
                }
            }else{
                enemigo.usarHabilidad(jugadorActual);
                ultimaRonda += "| " + enemigo.nombre + " -LIFESTEAL> " + jugadorActual.nombre +" | ";
            }
        }else if(nombreClase.equals("GolpeLetal")){
            if(jugadorOMascota > 65){
                if(mascotaActual != null && !mascotaActual.isDead()){
                    enemigo.usarHabilidad(mascotaActual);
                    ultimaRonda += "| " + jugadorActual.nombre + " -CRIT> " + mascotaActual.nombre + " | ";
                    verificarMuerteMascota();
                }else{
                    enemigo.usarHabilidad(jugadorActual);
                    ultimaRonda += "| " + enemigo.nombre + " -CRIT> " + jugadorActual.nombre + " | ";
                }
            }else{
                enemigo.usarHabilidad(jugadorActual);
                ultimaRonda += "| " + enemigo.nombre + " -CRIT> " + jugadorActual.nombre + " | ";
            }
        }
        verificarMuerteJugador(jugadorActual, 0);
        v.mostrarHabilidadUsada(nombreClase);     
    }
    private void jugadorAtacaEnemigo(){
        if(!enemigos.isEmpty()){
            int numObjetivo = encontrarNumObjetivo();
            Enemigo objetivo = enemigos.get(numObjetivo); 
            int ataque = jugadorActual.getAtaque();
            objetivo.bajarVida(ataque);
            v.mostrarDanioRecibido(objetivo, ataque);
            ultimaRonda += "| " + jugadorActual.nombre + " -ATK> " + objetivo.nombre + " | ";
            turnoMascota(objetivo);
            verificarMuerte(objetivo, numObjetivo);
        }
    }
    private boolean jugadorUtilizaItem(){
        boolean fuePosible = false;
        boolean itemValido = false;
        if(!enemigos.isEmpty()){
            
            ArrayList<Items> items = jugadorActual.getInventario();
            if(!items.isEmpty()){
                while(!itemValido){
                    items = jugadorActual.getInventario();
                    int posItem = v.mostrarMenuItems(items) - 1; //Devuelve una posicion adelante
                    if(posItem >= 0 && posItem <= items.size()){
                        Combatientes objetivoItem = null;
                        Items paraUsar = items.get(posItem);
                        String nombreClase = paraUsar.getClass().getName(); 
                        if(nombreClase.equals("DobleAtaque")){
                            int numObjetivoItem = encontrarNumObjetivo();
                            objetivoItem = enemigos.get(numObjetivoItem);
                            paraUsar.usarItem(objetivoItem);
                            ultimaRonda += "| " + jugadorActual.nombre + " -ATK²> " + objetivoItem.nombre + " | ";
                            verificarMuerte(objetivoItem, numObjetivoItem);
                        }else if(nombreClase.equals("ShotDeFuerza")){
                            boolean objetivoValido = false;
                            while(!objetivoValido){
                                switch(v.pedirObjetivoItem()){
                                    case 1:
                                        paraUsar.usarItem(jugadorActual);
                                        ultimaRonda += "| " + jugadorActual.nombre + " -STRENGTH ↑- | ";
                                        objetivoValido = true;
                                        break;
                                    case 2:
                                        try{
                                            if(mascotaActual != null && !mascotaActual.isDead()){
                                                int r = mascotaActual.getRondasMuerto();
                                                if(r == 0){
                                                    paraUsar.usarItem(mascotaActual);
                                                    ultimaRonda += "| " + mascotaActual.nombre + " -STRENGTH ↑- | ";
                                                    objetivoValido = true;
                                                }else{
                                                    v.mostrarMascotaRegenerandose(r);
                                                }
                                            }else{
                                                v.mostrarNingunaMascota();
                                            }
                                        }catch(Exception e){
                                            
                                        }
                                        break;
                                    default:
                                        v.mostrarOpcionInvalida();
                                        break;
                                }
                            }
                        }else if(nombreClase.equals("NuevosMusculos")){
                            boolean objetivoValido = false;
                            while(!objetivoValido){
                                switch(v.pedirObjetivoItem()){
                                    case 1:
                                        paraUsar.usarItem(jugadorActual);
                                        ultimaRonda += "| " + jugadorActual.nombre + " -NEW MSCLS- | ";
                                        objetivoValido = true;
                                        break;
                                    case 2:
                                        try{
                                            if(mascotaActual != null && !mascotaActual.isDead()){
                                                int r = mascotaActual.getRondasMuerto();
                                                if(r == 0){
                                                    paraUsar.usarItem(mascotaActual);
                                                    ultimaRonda += "| " + mascotaActual.nombre + " -NEW MSCLS- | ";
                                                    objetivoValido = true;
                                                }else{
                                                    v.mostrarMascotaRegenerandose(r);
                                                }
                                            }else{
                                                v.mostrarNingunaMascota();
                                            }
                                        }catch(Exception e){
                                        }
                                        break;
                                    default:
                                        v.mostrarOpcionInvalida();
                                        
                                }
                            }                        
                        }else if(nombreClase.equals("PocionCuracion")){
                            boolean objetivoValido = false;
                            while(!objetivoValido){
                                switch(v.pedirObjetivoItem()){
                                    case 1:
                                        paraUsar.usarItem(jugadorActual);
                                        ultimaRonda += "| " + jugadorActual.nombre + " -HEAL- | ";
                                        objetivoValido = true;
                                        break;
                                    case 2:
                                        try{
                                            if(mascotaActual != null && !mascotaActual.isDead()){
                                                int r = mascotaActual.getRondasMuerto();
                                                if(r == 0){
                                                    paraUsar.usarItem(mascotaActual);
                                                    ultimaRonda += "| " + mascotaActual.nombre + " -HEAL- | ";
                                                    objetivoValido = true;
                                                }else{
                                                    v.mostrarMascotaRegenerandose(r);
                                                }                                        
                                            }else{
                                                v.mostrarNingunaMascota();
                                            }
                                        }catch(Exception e){
                                            
                                        }
                                        break;
                                    default:
                                        v.mostrarOpcionInvalida();
                                        
                                }
                            }
                        }else if(nombreClase.equals("PocionDebilitar")){
                            int numObjetivoItem = encontrarNumObjetivo();
                            objetivoItem = enemigos.get(numObjetivoItem);
                            paraUsar.usarItem(objetivoItem);
                            ultimaRonda += "| " + jugadorActual.nombre + " -WEAK'D> " + objetivoItem.nombre + " |";
                        }
                        v.mostrarItemUsado(nombreClase);
                        items.remove(paraUsar);
                        itemValido = true;
                        fuePosible = true;
    
                    }else{
                        v.mostrarOpcionInvalida();
                    }
                }
            }else{
                v.mostrarNoMasItems();
            }
        }
        return fuePosible;
    }
    private void jugadorSaltaRonda(){
        ultimaRonda += "| " + jugadorActual.nombre + " -SKIP- "  + " | ";
        v.mostrarSaltarRonda(jugadorActual, ronda);
    }
    private void turnoJugador(){
        boolean opcionValida = false;
        int opcion;
        while(!opcionValida){
            opcion = v.mostrarMenuJugador();
            switch(opcion){
                case 1: // ATACAR
                    jugadorAtacaEnemigo();
                    opcionValida = true;
                    break;
                case 2: // USAR ITEM
                    if(jugadorUtilizaItem()){
                        opcionValida = true;
                    }
                    break;
                case 3: // SALTAR TURNO
                    jugadorSaltaRonda();
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
        
    }
    private void turnoEnemigo(){
        for (Enemigo enemigo : enemigos) {
            String n = enemigo.getClass().getName();
            int opcion = randomEntre(0, 100);
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
    }
    private void finDeRonda(){
        for(int i = 0; i < historial.size(); i++){
            if(i <= 1){
                historial.set(i, historial.get(i + 1));
            }else if(i == 2){
                historial.set(2, ultimaRonda);
            }
        }
        if(!acompaniantes.isEmpty()){
            for (Acompaniantes acompaniante : acompaniantes) {
                if(acompaniante != null){
                    if(acompaniante.getAsociado().getVida() > 0){
                        if(acompaniante.isDead()){
                            acompaniante.setRondasMuerto(acompaniante.getRondasMuerto() - 1);
                            if(!acompaniante.isDead()){
                                acompaniante.regenerar();
                                v.mostrarMascotaRegenerada(); 
                            }
                        }
                    }else{
                        acompaniantes.remove(acompaniante);
                    }                    
                }
            }        
        }
        ronda++;
    }
    private void jugarRonda(){
        ultimaRonda = "";
        v.mostrarNumeroDeRonda(ronda);
        v.mostrarEstadisticasEnemigos(enemigos);
        v.mostrarEstadisticasJugadores(jugadores);
        v.mostrarEstadisticasAcompaniantes(acompaniantes);
        //TURNO DEL JUGADOR
        turnoJugador();
        //TURNO DEL ENEMIGO
        turnoEnemigo();
        //FIN DE RONDA
        finDeRonda();
    }
    

    private void crearCombatientesRaid(){
        //JUGADORES
        boolean cantidadDeJugadoresExtraValidos = false;
        while(!cantidadDeJugadoresExtraValidos){
            int cantidadNuevosjugadores = v.pedirCantidadNuevosJugadores();
            if(cantidadNuevosjugadores >= 0 && cantidadNuevosjugadores <= 2){ //VERIFICA QUE HAYA ENTRE 0 Y 2 JUGADORES PARA CUMPLIR CON MAX 3
                for(int i = 0; i < cantidadNuevosjugadores; i++){
                    v.mostrarQueJugadorSeEstaCreando(i + 2);
                    crearJugador();
                }
                cantidadDeJugadoresExtraValidos = true;
            }
        }
        //---------------
        // ENEMIGOS
        crearEnemigos(0, 2);
        //------------------
        //RAID BOSS
        raidBoss = new RaidBoss();
        enemigos.add(raidBoss);
        v.mostrarSaludo(raidBoss);
    }
    
    
    private void jugarRAID(){
        ultimaRonda = "";
        v.mostrarNumeroDeRonda(ronda);
        v.mostrarEstadisticasEnemigos(enemigos);
        v.mostrarEstadisticasJugadores(jugadores);
        v.mostrarEstadisticasAcompaniantes(acompaniantes);
        //TURNO DEL JUGADOR
        for (Jugador jugador : jugadores) {
            jugadorActual = jugador;
            mascotaActual = jugador.getMascota();
            turnoJugador();
        }
        //TURNO ENEMIGOS
        turnoEnemigo();
        //FIN
        finDeRonda();
    }
    
    public void ejecutar(){
        v.mostrarSaludo();
        crearJugador();
        boolean tipoRondaValido = false;
        while(!tipoRondaValido){
            int tipoRonda = v.pedirTipoRonda();
            switch(tipoRonda){
                case 1:
                    crearEnemigos(1, 3);
                    jugadorActual = jugadores.get(0);
                    do{
                        jugarRonda();
                        v.mostrarHistorial(historial);
                    }while(jugadorActual.vida > 0 && !enemigos.isEmpty());
                    v.mostrarGG(ronda);
                    tipoRondaValido = true;
                    break;
                case 2:
                    crearCombatientesRaid();
                    do{
                        jugarRAID();
                        v.mostrarHistorial(historial);
                    }while(!jugadores.isEmpty() && raidBoss.vida > 0);
                    v.mostrarGG(ronda);
                    tipoRondaValido = true;                    
                    break;
                default:
                    v.mostrarOpcionInvalida();
                    break;
            }
        }
    }
}
