public class Main implements OnClickListener {
    public static void main(String[] args) {
        System.out.println("Olá, mundo!");

        Main main = new Main();
        main.onClick();

        Botao botao = new Botao();
        botao.setOnClickListener(main);
        botao.onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Olá, onClick");
    }
}
