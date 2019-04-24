package tw.noah.spring.boot.vue.example.model;

/**
 * 制式 JsonModel用的 Message
 */
public enum JsonMsg {
	Success("SUCCESS"),
	Failure("FAILURE"),
	Error("ERROR"),
	Null(null);
	
	private String desc;
	
	JsonMsg(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
}
