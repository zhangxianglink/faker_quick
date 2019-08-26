package myannotation;

@EasyAnnotation(username = "agz",value = "爱国者")
public class Student {
	
	@EasyAnnotation(username = "akg",value = "爱科技")
	public void show() {
		for (int i = 0; i < 50; i++) {
			System.out.println("星期五又到了");
		}
	}

}
