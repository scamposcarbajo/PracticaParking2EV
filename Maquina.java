package ClasesPrincipales;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Clase que representa una máquina expendedora de tickets para el aparcamiento.
 * Gestiona el plano del aparcamiento, la asignación y liberación de plazas, y el cálculo de tarifas.
 */
public class Maquina {

    // Constantes para el número máximo de plantas y plazas por planta
    private static final int NUM_MAX_PLANTAS = 3; // Número máximo de plantas en el aparcamiento
    private static final int NUM_MAX_PLAZAS_POR_PLANTA = 20; // Número máximo de plazas por planta

    // Atributos estáticos y variables relacionadas con la máquina y los tickets
    private static int asignadorClave = 0; // Asignador de claves para los tickets
    private static double precioMinuto; // Precio por minuto del aparcamiento
    protected Deposito deposito = new Deposito(); // Objeto que gestiona el depósito de monedas
    private ArrayList<Ticket> listaTickets = new ArrayList<>(); // Lista que almacena los tickets generados

    // Plano del aparcamiento representado como una matriz
    private static Integer[][] plano = rellenarPlano();

    // Objeto Terminal que representa la interfaz con el usuario
    private Terminal terminal;

    /**
     * Constructor por defecto de la clase Maquina.
     * Este constructor es utilizado para acceder a la máquina desde la clase Terminal.
     */
    public Maquina() {
        System.out.println("Constructor por defecto");
    }

    /**
     * Constructor principal de la clase Maquina.
     * Recibe el precio por minuto para gestionar el coste del aparcamiento.
     * Además, inicializa el depósito y el plano del aparcamiento, y muestra la interfaz Terminal.
     *
     * @param precioMinuto El precio por minuto de estacionamiento en el aparcamiento.
     */
    public Maquina(double precioMinuto) {
        this.precioMinuto = precioMinuto; // Inicializa el precio por minuto
        System.out.println("constructor normal");

        // Rellenar el plano y mostrarlo (representa la distribución de las plantas y plazas)
        rellenarPlano();
        // Muestra el plano por consola
        mostrarPlano();

        // Inicializa el terminal para la interacción con el usuario
        terminal = new Terminal();
        terminal.setVisible(true); // Hace visible la ventana del terminal
    }

    /**
     * Obtiene la lista de tickets generados.
     *
     * @return La lista de tickets
     */
    public ArrayList<Ticket> getListaTickets() {
        return listaTickets;
    }

    /**
     * Obtiene el plano del aparcamiento.
     *
     * @return El plano del aparcamiento representado como una matriz de enteros
     */
    public Integer[][] getPlano() {
        return plano;
    }

    /**
     * Inicializa los valores de la matriz del plano a 0, indicando que todas las plazas están libres.
     *
     * @return El plano inicializado con 0 en cada plaza.
     */
    public static Integer[][] rellenarPlano() {
        plano = new Integer[NUM_MAX_PLANTAS][NUM_MAX_PLAZAS_POR_PLANTA];
        for (int i = 0; i < plano.length; i++) {
            for (int j = 0; j < plano[i].length; j++) {
                plano[i][j] = 0; // 0 indica que la plaza está libre
            }
        }
        return plano;
    }

    /**
     * Muestra el plano del aparcamiento en la consola.
     * Imprime la matriz de plazas de aparcamiento para visualizar el estado actual de cada plaza.
     */
    public void mostrarPlano() {
        for (int i = 0; i < plano.length; i++) {
            for (int j = 0; j < plano[i].length; j++) {
                System.out.print(plano[i][j]); // Imprime el valor de cada plaza
            }
            System.out.println(""); // Nueva línea después de cada planta
        }
    }

    /**
     * Encuentra la primera plaza libre en el plano donde el valor sea 0.
     *
     * @return Un objeto Ubicacion que representa la plaza libre o null si no se encuentra una plaza libre.
     */
    public Ubicacion encontrarEspacioLibre() {
        for (int i = 0; i < plano.length; i++) {
            for (int j = 0; j < plano[i].length; j++) {
                if (plano[i][j] == 0) { // La plaza está libre (valor 0)
                    Ubicacion ubicacion = new Ubicacion(i, j);
                    return ubicacion;
                }
            }
        }
        return null; // No hay plazas libres
    }

    /**
     * Asigna una plaza libre al coche y genera un ticket para el vehículo.
     * Actualiza el plano con el ID del ticket y guarda el ticket en la lista de tickets.
     *
     * @param ubicacionPlaza La ubicación de la plaza a asignar
     * @param matricula La matrícula del coche
     * @return El ticket generado con la plaza asignada
     */
    public Ticket asignarPlaza(Ubicacion ubicacionPlaza, String matricula) {
        // Genera un nuevo ticket con un ID único
        asignadorClave++;
        int id = asignadorClave;
        System.out.println("ID " + id);
        Ticket ticket = new Ticket(id, matricula, LocalDateTime.now(), ubicacionPlaza);
        this.plano[ubicacionPlaza.getPlanta()][ubicacionPlaza.getPlaza()] = id; // Asigna el ticket a la plaza
        System.out.println("ticket del metodo " + ticket.toString());
        this.listaTickets.add(ticket); // Añade el ticket a la lista de tickets
        mostrarPlano(); // Muestra el plano actualizado
        return ticket; // Devuelve el ticket generado
    }

    /**
     * Libera una plaza en el aparcamiento cuando un coche sale.
     * Actualiza el plano y pone el valor de la plaza a 0 (plaza libre).
     *
     * @param ticket El ticket asociado a la plaza que se va a liberar
     */
    public void liberarPlaza(Ticket ticket) {
        Ubicacion ubicacion = ticket.getUbicacion(); // Obtiene la ubicación del ticket
        this.plano[ubicacion.getPlanta()][ubicacion.getPlaza()] = 0; // Marca la plaza como libre (valor 0)
    }

    /**
     * Calcula el tiempo transcurrido desde que un coche entró al aparcamiento.
     * Devuelve la diferencia en minutos entre la hora de entrada del ticket y la hora actual.
     *
     * @param ticket El ticket del coche para calcular el tiempo transcurrido
     * @return El tiempo transcurrido en minutos
     */
    public int calcularTiempoTranscurrido(Ticket ticket) {
        LocalDateTime fechaEntrada = ticket.getFechaHora();
        LocalDateTime fechaSalida = LocalDateTime.now();
        LocalDateTime tiempoTranscurrido = fechaSalida.minusMinutes((int) fechaEntrada.getMinute());
        return (int) tiempoTranscurrido.getMinute() + 1; // Redondea hacia arriba en caso de tener segundos
    }

    /**
     * Calcula el dinero a pagar por un coche en función del tiempo que estuvo estacionado.
     * Multiplica el tiempo transcurrido por el precio por minuto.
     *
     * @param tiempo El tiempo transcurrido en minutos
     * @return El total a pagar en euros
     */
    public double totalDineroDevolver(int tiempo) {
        System.out.println("precio por minuto " + this.precioMinuto);
        return (double) (tiempo * this.precioMinuto); // Calcula el total a pagar
    }

    /**
     * Verifica si ya existe un coche con la matrícula proporcionada en el aparcamiento.
     * No se puede asignar una plaza si el coche ya está en el aparcamiento.
     *
     * @param matricula La matrícula a comprobar
     * @return true si la matrícula no está en el aparcamiento, false si ya está
     */
    public boolean comprobarMatricula(String matricula) {
        for (Ticket ticket : listaTickets) {
            if (ticket.getMatricula().equals(matricula) && ticket.isActivo()) {
                return false; // Ya existe un coche con esta matrícula en el aparcamiento
            }
        }
        return true; // La matrícula no está en el aparcamiento
    }
}
