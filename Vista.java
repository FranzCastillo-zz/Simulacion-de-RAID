import java.util.Scanner;
import java.util.ArrayList;

public class Vista {
    private Scanner scan;

    public Vista(){
        scan = new Scanner(System.in);
    }

    private void prnt(String texto){
        System.out.println(texto);
    }

    public void mostrarOpcionInvalida(){
        prnt("Ha ingresado una opcion no valida, por favor pruebe de nuevo.");
    }

    public void mostrarSaludo(){
        prnt("Narrador: Hola! Bienvenido al simulador de pelea!");
    }
    public String pedirNombre(){
        prnt("Narrador: Cual es tu nombre?");
        return scan.nextLine();
    }
    public int pedirClase(){
        prnt("Narrador: Ahora dime, que clase prefieres? (Ingresa el numero)\n1. Guerrero\n2. Explorador\n3. Cazador");
        try{
            int opcion = scan.nextInt();
            scan.nextLine();
            return opcion;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    public void mostrarSaludo(Combatientes c){
        prnt(c.getSaludo());
    }
    public void mostrarDespedida(Combatientes c){
        prnt(c.getMuerte());
    }
    public int pedirTipoRonda(){
        prnt("Narrador: Que tipo de ronda deseas jugar? (Ingresa el numero)\n1. Batalla normal\n2. RAID");
        try{
            int opcion = scan.nextInt();
            scan.nextLine();
            return opcion;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    public void mostrarNumeroDeRonda(int num){
        prnt("Narrador: Demos inicio a la ronda " + num);
    }
    public void mostrarEstadisticasEnemigos(ArrayList<Enemigo> enemigos){
        prnt("Los enemigos se encuentran actualmente con las siguientes estadisticas:");
        prnt("Nombre\t\tVida\t\tAtaque");
        for (Enemigo enemigo : enemigos) {
            prnt(enemigo.nombre + "\t\t" + enemigo.vida + "\t\t" + enemigo.ataque);
        }
    }
    public void mostrarEstadisticasJugadores(ArrayList<Jugador> jugadores){
        prnt("Los jugadores se encuentran actualmente con las siguientes estadisticas:");
        prnt("Nombre\t\tVida\t\tAtaque");
        for (Jugador jugador : jugadores) {
            prnt(jugador.nombre + "\t\t" + jugador.vida + "\t\t" + jugador.ataque);
        }
    }
    public void mostrarHistorial(ArrayList<String> historial){
        prnt("Los ultimos movimientos han sido:");
        for (String string : historial) {
            prnt("-" + string);
        }
    }
    public void mostrarGG(int ronda){
        prnt("Narrador: Ha sido un largo camino! La batalla acaba tras " + ronda + " rondas.");
    }
    public int mostrarMenuJugador(){
        try{
            prnt("\nQue deseas hacer?\n");
            prnt("1. Atacar");
            prnt("2. Usar item");
            prnt("3. Saltar turno");
            prnt("4. Salir");

            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    public int pedirObjetivo(ArrayList<Enemigo> arregloEnemigos){
        try{
            prnt("\nA quien deseas atacar? (Ingresa el numero)\n");
            int i = 1;
            for (Enemigo enemigo : arregloEnemigos) {
                prnt(i + ". " + enemigo.nombre);
                i++;
            }
            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    public void mostrarDanioRecibido(Combatientes c, int cantidad){
        prnt(c.nombre + " ha recibido " + cantidad + "pts de danio!");
    }
    public int mostrarMenuItems(ArrayList<Items> inventario){
        try{
            prnt("\nQue item deseas usar? (Ingresa el numero)\n");
            int i = 1;
            for (Items item : inventario) {
                prnt(i + ". " + item.getNombre());
                i++;
            }
            int temp = scan.nextInt();
            scan.nextLine();
            return temp;
        }catch(Exception e){
            scan.next();
            return -1;
        }
    }
    public void mostrarSaltarRonda(Combatientes c, int ronda){
        prnt(c.nombre + " se ha saltado la ronda " + ronda);
    }
}
