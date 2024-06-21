import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String palabra = "estrella";
        int intentosMaximos = 10;
        int intento = 0;
        boolean esAdivinada = false;

        char[] letrasAdivinadas = new char[palabra.length()];
        
        for (int i = 0; i < letrasAdivinadas.length; i++) {
            letrasAdivinadas[i] = '_';

        }

        while(!esAdivinada && intento < intentosMaximos) {
            System.out.println("Palabra a adivinar: " + String.valueOf(letrasAdivinadas) + " (" + palabra.length() + ") letras");

            System.out.print("Dame una letra: ");
            char letra = Character.toLowerCase(sc.next().charAt(0));

            boolean letraCorrecta = false;

            for (int i = 0; i < palabra.length(); i++) {
                if (letra == palabra.charAt(i)) {
                    letrasAdivinadas[i] = letra;
                    letraCorrecta = true;
                }
            }

            if (!letraCorrecta) {
                intento++;
                System.out.println("¡La letra '" + letra + "' es incorrecta, te quedan " + (intentosMaximos - intento) + " intentos!");
            } 

            if (String.valueOf(letrasAdivinadas).equals(palabra)) {
                esAdivinada = true;
                System.out.println("!Felicidades has adivinado la palabra " + palabra + "!");
            }
        }


        if (!esAdivinada) System.out.println("¡Lo siento, se te han acabado los intentos :( !");
    
        sc.close();
    }
}
