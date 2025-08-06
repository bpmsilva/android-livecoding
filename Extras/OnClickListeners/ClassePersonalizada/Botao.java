public class Botao {
    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void clicar() {
        if (listener != null) {
            listener.onClick(); // Aqui está o "callback"
        }
    }
}