import com.google.gson.Gson;
import consultas.request;
import modelos.ConversorDeMoneda;
import modelos.Monedas;

import java.sql.SQLSyntaxErrorException;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import static modelos.ConversorDeMoneda.convertirMoneda;


public class Main {
    static request consultas = new request();
    public static void main(String[] args) throws IOException, InterruptedException {

        MenuPrincipal();
    }

    public static void MenuPrincipal() throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String input;

        Monedas monedas = new Monedas();
        Gson gson = new Gson();
        monedas =  gson.fromJson(consultas.obtenerconversiones(), Monedas.class);


        while (true){
            System.out.println("Menu principal");
            System.out.println("1 - Lista de monedas");
            System.out.println("2 - Conversion");
            System.out.println("3 - Salir");
            input = scanner.nextLine();

            switch (input){
                case "1":
                    ListaMonedas(monedas);
                    break;
                case "2":
                    Conversion();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    public static void ListaMonedas(Monedas m){
        for (Map.Entry<String, Float> entry : m.getConversionRates().entrySet()){
            String nombre = entry.getKey();
            System.out.println(nombre);
        }
    }

    public static void Conversion() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresar moneda origen");
        String origen = scanner.nextLine();
        System.out.println("Ingresar moneda destino");
        String destino = scanner.nextLine();
        System.out.println("Ingresar cantidad");
        float cantidad = 0.0f;
        try {
            cantidad = scanner.nextFloat();
        }catch (Exception e){
            System.out.println("Cantidad no valida");
            return;
        }

        float resultado = convertirMoneda(origen, destino, cantidad);
        System.out.println("Resultado de la conversion: "+ destino + " " + resultado);

    }

}