public class Main {
    public static void main(String[] args) {
        Botao botao = new Botao();

        botao.setOnClickListener(() -> {
            System.out.println("Clique tratado por uma função anônima!");
        }); // Passa o ouvinte

        botao.clicar(); // Simula o clique
    }
}