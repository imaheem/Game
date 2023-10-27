public class Ghost implements Monster{
    @Override
    public char getSymbol() {
        return 'G';
    }

    @Override
    public String greet() {
        return "The Ghost wails!";
    }
}
