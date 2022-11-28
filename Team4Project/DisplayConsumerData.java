package Team4Project;

public class DisplayConsumerData{
	private int custId;
	private String custName;
	private String city;
	private String area;
	private String type;
	
	DisplayConsumerData(int custId,String custName,String city,String area, String type){
		this.custId=custId;
		this.custName=custName;
		this.city=city;
		this.area=area;
		this.type=type;
	}
	DisplayConsumerData(){
		
	}
	public String toString() {
		return this.custId+" "+this.custName+" "+this.city+" "+this.area+" "+this.type;
	}
	
}

