public class Botao {
    // Esta Variável guarda uma referência
    // para um objeto que implementa essa interface.
    OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    // Simula eventos de clique
    public void onClick() {
        if (listener != null) {
            listener.onClick();
        }
    }
}
