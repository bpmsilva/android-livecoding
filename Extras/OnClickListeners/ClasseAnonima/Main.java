public class Main {
    public static void main(String[] args) {
        Botao botao = new Botao();

        botao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("Clique com classe anônima");
            }
        }); // Passa o ouvinte
        botao.clicar(); // Simula o clique
    }
}