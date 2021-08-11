package barManagement;

public interface GarnishStrategy
{
    String garnish();
}
class Lemon implements GarnishStrategy{
 public String garnish(){
     return "Lemon";
    }
}

class Lime implements GarnishStrategy{
    public String garnish(){
     return "Lime";   
    }
}

class Cherry implements GarnishStrategy{
    public String garnish(){
     return "Cherry";   
    }
}

class Umbrella implements GarnishStrategy{
 public String garnish(){
     return "Umbrella";
    }
}

class Olive implements GarnishStrategy{
 public String garnish(){
     return "Olive";
    }
}
