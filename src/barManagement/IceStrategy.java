package barManagement;

public interface IceStrategy
{
    String ice();
}
class Cube implements IceStrategy{
 public String ice(){
     return "Ice cubes";
    }
}

class Frozen implements IceStrategy{
    public String ice(){
     return "Slush";   
    }
}

class Strained implements IceStrategy{
    public String ice(){
     return "Strain ice out";   
    }
}