package gui.command;

public class CommandInvoker {
    public void eseguiComando(CommandIF comando) {
        comando.esegui();
    }
}
