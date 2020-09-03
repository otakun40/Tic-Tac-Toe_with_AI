public class Main {
    public static void main(String[] args) {
        while (true) {
            Game game = new Game();
            if (game.isRun()) {
                game.play();
            } else {
                break;
            }
        }
    }
}