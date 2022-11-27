package Team4Project;

public class InvalidTypeException  extends Exception  
{  
    public InvalidTypeException (String str)  
    {  
    	super(str);  
    }  
    public InvalidTypeException ()  
    {  
    	super("Please Enter Valid Type!");  
    }  
}
