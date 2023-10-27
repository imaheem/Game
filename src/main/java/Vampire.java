public class Vampire implements Monster{
    @Override
    public char getSymbol() {
        return 'V';
    }

    @Override
    public String greet() {
        return "The Vampire hisses!";
    }
}
