import biuoop.GUI;

/**
 * The type Quit game task.
 */
public class QuitGameTask implements Task {
    private GUI gui;

    /**
     * Instantiates a new Quit game task.
     *
     * @param gui the GUI
     */
    public QuitGameTask(GUI gui) {
        this.gui = gui;
    }

    @Override
    public Object run() {
        gui.close();
        System.exit(0);
        return null;
    }
}
