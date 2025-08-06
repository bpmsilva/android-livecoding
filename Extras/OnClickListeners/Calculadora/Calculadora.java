public class Calculadora implements Operacoes {
    @Override
    public int somar(int a, int b) {
        return a + b;
    }

    @Override
    public int subtrair(int a, int b) {
        return a - b;
    }

    @Override
    public int multiplicar(int a, int b) {
        return a * b;
    }

    @Override
    public double dividir(int a, int b) {
        if (b == 0) {
            return Double.NaN;
        }
        return (double) a / b;
    }
}