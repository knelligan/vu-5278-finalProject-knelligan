package barManagement;

public interface GlassStrategy
{
    String glass();
}
class Mug implements GlassStrategy{
 public String glass(){
     return "Mug";
    }
}

class Pilsner implements GlassStrategy{
    public String glass(){
     return "Pilsner";   
    }
}

class Martini implements GlassStrategy{
    public String glass(){
     return "Martini";   
    }
}
class Margarita implements GlassStrategy{
 public String glass(){
     return "Margarita";
    }
}

class Rocks implements GlassStrategy{
    public String glass(){
     return "Rocks";   
    }
}

class RedGlass implements GlassStrategy{
    public String glass(){
     return "Red Wine";   
    }
}

class WhiteGlass implements GlassStrategy{
    public String glass(){
     return "White Wine";   
    }
}

class Collins implements GlassStrategy{
    public String glass(){
     return "Collins";   
    }
 }
 
 class Pint implements GlassStrategy{
    public String glass(){
     return "Pint";   
    }
 }
 
  class Shot implements GlassStrategy{
    public String glass(){
     return "Shot";   
    }
 }
