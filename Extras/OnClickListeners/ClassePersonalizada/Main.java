public class Main {
    public static void main(String[] args) {
        Botao botao = new Botao();

        // Criando um objeto da classe que IMPLEMENTA a interface
        ClickPersonalizado meuListener = new ClickPersonalizado();

        botao.setOnClickListener(meuListener); // Passa o ouvinte
        botao.clicar(); // Simula o clique
    }
}