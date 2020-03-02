import java.util.Random;
import java.util.Arrays;

public class ComputerCharacter extends Character {
    
    private String[] bossNames = {"Sphinx","Cyclops", "Chimera", "Empusa", "Hydra", "Charybdis", "Scylla", "Cerberus", "Minotaur", "Medusa", "Thypon"};

    public String[] getBossNames() {
        return this.bossNames;
    }

    public String getBossNamesAsString() {
        return Arrays.toString(this.bossNames);
    }

    
}
