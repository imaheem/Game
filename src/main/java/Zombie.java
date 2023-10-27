public class Zombie implements Monster{
    @Override
    public char getSymbol() {
        return 'Z';
    }

    @Override
    public String greet() {
        return "The Zombie groans!";
    }
}
