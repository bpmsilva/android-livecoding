public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // Example usage of the Calculadora class
        Calculadora calculadora = new Calculadora();

        // Performing some operations
        int sum = calculadora.somar(5, 3);
        int difference = calculadora.subtrair(10, 4);
        int product = calculadora.multiplicar(6, 7);
        double quotient = calculadora.dividir(8, 2);

        // Displaying the results
        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);
    }
}
